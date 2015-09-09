<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="GBK"%>
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
								<i class="icon-list-alt"></i>
								������Ϣ
							</small>
						</h1>
					</div> 
					<div class="row-fluid">
						<div class="span12">
							<!--PAGE CONTENT BEGINS-->
							<form class="form-inline" method="get" action="<%=request.getContextPath() %>/manager/department">
								<input type="hidden" name="pagenum" value="${pagenum}">
								&nbsp;&nbsp;���ƣ�<input type="text" name="name" value="${classes.name}"  class="input-medium search-query">&nbsp;&nbsp;&nbsp;&nbsp;
								<button  type="submit" class="btn btn-purple btn-small">
									����
									<i class="icon-search icon-on-right bigger-110"></i>
								</button>
						<%--		<button  type="button" onclick="location.href='<%=request.getContextPath() %>/manager/adddepartmentpage'" class="btn btn-purple btn-small">
									��Ӳ���
									<i class="icon-plus-sign icon-on-right bigger-110"></i>
								</button>--%>
								<a href="#myModal"  role="button"  class="btn btn-purple btn-small" data-toggle="modal"><i class="icon-plus-sign icon-on-right bigger-110"></i>��Ӳ���</a>


							</form>
							
							<table id="sample-table-1" class="table table-striped table-bordered table-hover">
								<thead>
									<tr>
										<th width="20%">���ű��</th>
										<th width="20%">��������</th>
										<th width="20%">Ա������</th>
										<th width="20%">��������</th>
										<th width="20%" >����</th>
									</tr>
								</thead>
								<tbody>
								<c:forEach items="${classesList}"  var="classes"  >
									<tr>
										<td>${classes.id}</td>
										<td>${classes.name}</td>
										<td>${classes.employeecount}</td>
										<td>${classes.manager}</td>
										<td >
											<button class="btn btn-mini btn-primary" onclick="location.href='<%=request.getContextPath() %>/manager/departmentnewspage?departmentid=${classes.id}'" ><i class=" icon-envelope"></i>&nbsp;������̬</button>
											<button class="btn btn-mini btn-primary" onclick="location.href='<%=request.getContextPath() %>/manager/manageremployeepage?departmentid=${classes.id}'" ><i class="icon-user"></i>&nbsp;����Ա��</button>
										</td>
									</tr>
								</c:forEach>
								</tbody>
							</table>
					 		<div class="dataTables_paginate paging_bootstrap pagination">
							  <button class="btn btn-success btn-mini" onclick="location.href='<%=request.getContextPath() %>/manager/classes?pagenum=${pagenum-1}'" <c:if test="${pagenum <= 1}">disabled="disabled"</c:if>    >&laquo;</button>
							  <button class="btn btn-success btn-mini" disabled="disabled">�� ${pagenum} ҳ</button>
							  <button class="btn btn-success btn-mini" onclick="location.href='<%=request.getContextPath() %>/manager/classes?pagenum=${pagenum+1}'" <c:if test="${length < 10}">disabled="disabled"</c:if> >&raquo;</button>
					 		</div>

							<div id="myModal" class="modal hide fade" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
								<div class="modal-header">
									<button type="button" class="close" data-dismiss="modal" aria-hidden="true">��</button>
									<h3 id="myModalLabel">��Ӳ���</h3>
								</div>
								<form action="adddepartment" id="adddepartment" method="post"  class="form-inline">
									<div class="modal-body">
										<label class="control-label"   >���ű��:&nbsp;&nbsp;</label>
										<div class="controls">
											<input type="text" name="id" id="id" placeholder=���ű��>*
											<span class="help-inline"><c:if test="${param.notice==null}">�������Բ��ű��Ϊ302</c:if>  <font color="red"><c:if test="${param.notice!=null}">${param.notice}</c:if></font></span>
										</div>
										<div class="control-group">
											<label class="control-label"  for="name">��������</label>
											<div class="controls">
												<input type="text" id="name" value="${param.name}" name="name" placeholder="��������">*
											</div>
										</div>
										<div class="control-group">
											<label class="control-label"  for="manager">��������</label>
											<div class="controls">
												<input type="text" value="${param.manager}" name="manager" id="manager" placeholder="��������">*
											</div>
										</div>
										<div class="modal-footer">
											<button  type="button" id="add" class="btn btn-small btn-primary">����</button>
										</div>
										</div>

								</form>
							</div>
					 		
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
					if($.trim($("#id").val())==''||isNaN($('#id').val())){
						alert('�����벿�ţ�');
						return;
					}else if($.trim($("#name").val())==''){
						alert('�����벿�����ƣ�');
						return;
					}else if($.trim($("#manager"))==''){
						alert('�������������֣�');
						return;
					}
					else{
						$("#adddepartment").submit();
					}
				});
			});
		</script>
	</body>
</html>