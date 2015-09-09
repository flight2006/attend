<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="GBK"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>
<!DOCTYPE html>
<html lang="en">
	<head>
		<meta charset="utf-8" />
		<title>������Ϣ</title>
		<meta name="description" content="" />
		<meta name="viewport" content="width=device-width, initial-scale=1.0" />
		<%@include file="/WEB-INF/views/common/css.jsp" %>
	</head>

	<body>
		<%@ include file="/WEB-INF/views/common/navbar.jsp" %>
		<div class="main-container container-fluid">
			<a class="menu-toggler" id="menu-toggler" href="#">
				<span class="menu-text"></span>
			</a>
			<%@ include file="/WEB-INF/views/common/sidebar.jsp" %>
			<div class="main-content">
				 
				<div class="page-content">
					<br>
					<br>
					<div class="page-header position-relative">
						<h1>
							<small>
								<i class="icon-user"></i>
								������Ϣ
							</small>
						</h1>
					</div> 
					<div class="row-fluid">
						<div class="span12">
							<!--PAGE CONTENT BEGINS-->
							<form class="form-inline" method="get" action="<%=request.getContextPath() %>/manager/attend">
								<input type="hidden" name="pagenum" value="${pagenum}">
								&nbsp;&nbsp;
								<select name="department_id">
									<option value="0">ѡ����</option>
									<c:forEach items="${clsList}"  var="cls"  >
										<option <c:if test="${exam.department_id == cls.id}">selected="selected"</c:if> value="${cls.id}">${cls.name}</option>
									</c:forEach>
								</select>&nbsp;&nbsp;
								<button  type="submit" class="btn btn-purple btn-small">
									����
									<i class="icon-search icon-on-right bigger-110"></i>
								</button>
								<%--<a href="#myModal"  role="button"  class="btn btn-purple btn-small" data-toggle="modal"><i class="icon-plus-sign icon-on-right bigger-110"></i>��ӿ��ڼ�¼</a>--%>

							</form>
							<table id="sample-table-1" class="table table-striped table-bordered table-hover">
								<thead>
									<tr>
										<th width="10%">���</th>
										<th width="10%">Ա��ID</th>
										<th width="10%">����ID</th>
										<th width="25%">��ʱ��</th>
										<th>��ע</th>
										<th width="12%" >���ڱ��</th>
									</tr>
								</thead>
								<tbody>
								<c:forEach items="${examList}"  var="exam"  >
									<tr>
										<td>${exam.id}</td>
										<td>${exam.emp_id}</td>
										<td>${exam.department_id}</td>
										<td>${exam.punchtime}</td>
										<td>${exam.remark}</td>
								<%--		<td >
											<button class="btn btn-mini btn-purple"  onclick="location.href='<%=request.getContextPath() %>/manager/viewattend?id=${exam.id}'" ><i class="icon-file"></i>��������</button>
										</td>--%>
										<td>����</td>
									</tr>
								</c:forEach>
								</tbody>
							</table>
							
					 		<div class="dataTables_paginate paging_bootstrap pagination">
							  <button class="btn btn-success btn-mini" onclick="location.href='<%=request.getContextPath() %>/manager/students?pagenum=${pagenum-1}'" <c:if test="${pagenum <= 1}">disabled="disabled"</c:if>    >&laquo;</button>
							  <button class="btn btn-success btn-mini" disabled="disabled">�� ${pagenum} ҳ</button>
							  <button class="btn btn-success btn-mini" onclick="location.href='<%=request.getContextPath() %>/manager/students?pagenum=${pagenum+1}'" <c:if test="${length < 10}">disabled="disabled"</c:if> >&raquo;</button>
					 		</div>
							 <%--
							<div id="myModal" class="modal hide fade" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
								  <div class="modal-header">
								    <button type="button" class="close" data-dismiss="modal" aria-hidden="true">��</button>
								    <h3 id="myModalLabel">��ӿ��ڼ�¼</h3>
								  </div>
							  	<form action="addexam" id="addexam" method="post"  class="form-inline">
								  <div class="modal-body">
										<label class="control-label"   >��Ŀ:&nbsp;&nbsp;</label>
										<input type="text" name="course" class="input-medium"  id="addcourse"  >
										&nbsp;&nbsp;&nbsp;&nbsp;<label class="control-label"   >����:&nbsp;&nbsp;</label>
										<input type="text" name="fullmarks" class="input-medium"  id="addfullmarks"  >
										<br><br><label class="control-label"   >�༶:&nbsp;&nbsp;</label>
										<select name="department_id">
											<c:forEach items="${clsList}"  var="cls"  >
												<option   value="${cls.id}">${cls.name}</option>
											</c:forEach>
										</select>
										<br><br><label class="control-label"    >��ע:&nbsp;&nbsp;</label>
										<input type="text" name="remark" id="addremark"  class="input-xlarge" >
								  </div>
								  <div class="modal-footer">
								    <button  type="button" id="add" class="btn btn-small btn-primary">����</button>
								  </div>
							  	</form>
							</div> --%>
							 
							<!--PAGE CONTENT ENDS-->
					</div><!--/.span-->
				</div><!--/.row-fluid-->
			</div><!--/.page-content-->
		</div><!--/.main-content-->
	</div><!--/.main-container-->

		<%@include file="/WEB-INF/views/common/js.jsp" %>
		<script type="text/javascript">
		$(function() {
			$('#add').on('click', function() {
				if($.trim($("#addfullmarks").val())==''||isNaN($('#addfullmarks').val())){
					alert('�������Ծ����֣�');
					return;
				}else if($.trim($("#addcourse").val())==''){
					alert('�����뿼�Կ�Ŀ��');
					return;
				}else{
					$("#addexam").submit();
				}
			});
		});
		</script>
	</body>
</html>