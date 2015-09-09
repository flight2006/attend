<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="GBK"%>
<!DOCTYPE html>
<html lang="en">
	<head>
		<meta charset="utf-8" />
		<title>Ա����Ϣ</title>
		<meta name="description" ontent="" />
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
								Ա����Ϣ
							</small>
						</h1>
					</div> 
					<div class="row-fluid">
						<div class="span12">
							<!--PAGE WebContent BEGINS-->
							<form class="form-inline" method="get" action="<%=request.getContextPath() %>/manager/employee">
								<input type="hidden" name="pagenum" value="${pagenum}">
								&nbsp;&nbsp;������<input type="text" name="name" value="${student.name}"  class="input-medium search-query">&nbsp;&nbsp;&nbsp;&nbsp;
								&nbsp;&nbsp;	&nbsp;&nbsp;	&nbsp;&nbsp;	&nbsp;&nbsp;
								<select name="department_id">
									<option value="0">ѡ����</option>
									<c:forEach items="${clsList}"  var="cls"  >
										<option <c:if test="${student.department_id == cls.id}">selected="selected"</c:if> value="${cls.id}">${cls.name}</option>
									</c:forEach>
								</select>&nbsp;&nbsp;
								<button  type="submit" class="btn btn-purple btn-small">
									����
									<i class="icon-search icon-on-right bigger-110"></i>
								</button>
							</form>
							<table id="sample-table-1" class="table table-striped table-bordered table-hover">
								<thead>
									<tr>
										<th width="15%">Ա�����</th>
										<th width="15%">Ա������</th>
										<th width="15%">��������</th>
										<th>��ע</th>
										<th width="30%" >����</th>
									</tr>
								</thead>
								<tbody>
								<c:forEach items="${studentList}"  var="student"  >
									<tr>
										<td><a href="#">${student.id}</a></td>
										<td>${student.name}</td>
										<td>${student.department_id}</td>
										<td>${student.remark}</td>
										<td >
											<button class="btn btn-mini btn-primary" onclick="location.href='<%=request.getContextPath() %>/manager/leavemessage?studentid=${student.id}'" ><i class="icon-comment"></i>΢������</button>
											<button class="btn btn-mini btn-primary" onclick="location.href='<%=request.getContextPath() %>/manager/attendlist?studentid=${student.id}'"><i class="icon-file"></i>&nbsp;�������</button>
										</td>
									</tr>
								</c:forEach>
								</tbody>
							</table>
							
					 		<div class="dataTables_paginate paging_bootstrap pagination">
							  <button class="btn btn-success btn-mini" onclick="location.href='<%=request.getContextPath() %>/manager/employee?pagenum=${pagenum-1}'" <c:if test="${pagenum <= 1}">disabled="disabled"</c:if>    >&laquo;</button>
							  <button class="btn btn-success btn-mini" disabled="disabled">�� ${pagenum} ҳ</button>
							  <button class="btn btn-success btn-mini" onclick="location.href='<%=request.getContextPath() %>/manager/employee?pagenum=${pagenum+1}'" <c:if test="${length < 8}">disabled="disabled"</c:if> >&raquo;</button>
					 		</div>
							 
							<!--PAGE WebContent ENDS-->
					</div><!--/.span-->
				</div><!--/.row-fluid-->
			</div><!--/.page-WebContent-->
		</div><!--/.main-WebContent-->
	</div><!--/.main-container-->

		<%@include file="/WEB-INF/views/common/js.jsp" %>
		
	</body>
</html>