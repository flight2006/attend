<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="GBK"%>
<!DOCTYPE html>
<html lang="en">
	<head>
		<meta charset="utf-8" />
		<title>��ӿ���</title>
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
					<div class="page-header position-relative">
						<h1>
							<small>
								<i class="icon-file"></i>
								��ӿ���
							</small>
						</h1>
					</div> 
					<div class="row-fluid">
						
						<div class="span12">
							<!--PAGE CONTENT BEGINS-->
							<form action="addexammark" id="addexammark" class="form-horizontal" method="post">
									<input type="hidden" name="classid" value="${exam.classid }" />
									<input type="hidden" name="course" value="${exam.course }" />
									<input type="hidden" name="fullmarks" value="${exam.fullmarks }" />
									<input type="hidden" name="remark" value="${exam.remark }" />
									<label for="form-field-8"> &nbsp;&nbsp;&nbsp;¼�� <b>${exam.classid}</b> �� <b>${exam.course}</b> ���Գɼ� �� ����<b>${exam.fullmarks}</b>�� �� </label>
								 	<hr>
								 	<table  class="table table-striped table-bordered table-hover">
										<thead>
											<tr>
												<th width="10%">ѧ�����</th>
												<th width="10%">ѧ������</th>
												<th width="10%">����</th>
												<th  >��ע</th>
											</tr>
										</thead>
										<tbody>
										 	<c:forEach items="${stlist}"  var="student" varStatus="sta"  >
											<tr>
												<td>${student.id }<input type="hidden" name="examMarks[${sta.index}].studentid" value="${student.id}"></td>
												<td>${student.name }<input type="hidden" name="examMarks[${sta.index}].studentname" value="${student.name}"><input type="hidden" name="examMarks[${sta.index}].classid" value="${student.classid}"></td>
												<td><input type="text" class="mark" name="examMarks[${sta.index}].mark" ></td>
												<td><input type="text" class="input-xxlarge" name="examMarks[${sta.index}].remark" ></td>
											</tr>
										 	</c:forEach>
										</tbody>
									</table>
								 		
									<div class="form-actions">
									<button class="btn btn-info"  id="add"  type="button">
										<i class="icon-ok bigger-110"></i>
										����
									</button>
									&nbsp; &nbsp; &nbsp;
									<button class="btn btn-info" type="button" onclick="location.href='<%=request.getContextPath() %>/manager/exams'" >
										<i class="icon-arrow-left"></i>
										����
									</button>
								</div>
							</form>
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
				var flag = true;
				$(".mark").each(function(){
					if($(this).val() == ""||isNaN($(this).val())||$(this).val() < 0||$(this).val()> ${exam.fullmarks}) {
						alert('������д����');
						flag = false;
						return false;
					}
				});
				if(flag)$("#addexammark").submit();
			});
		});
		</script>
	</body>
</html>