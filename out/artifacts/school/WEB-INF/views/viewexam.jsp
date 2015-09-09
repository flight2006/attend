<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="GBK"%>
<!DOCTYPE html>
<html lang="en">
	<head>
		<meta charset="utf-8" />
		<title>��������</title>
		<meta name="description" WebContent="" />
		<meta name="viewport" WebContent="width=device-width, initial-scale=1.0" />
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
								��������
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
									<label for="form-field-8"> &nbsp;&nbsp;&nbsp; <b>${exam.classid}</b> �� <b>${exam.course}</b> ��������  
									������<b>${exam.fullmarks}</b>�֣� &nbsp;&nbsp;
									��ƽ����<b>${exam.average}</b>�֣�&nbsp;&nbsp;
									����߷�<b>${exam.topmark}</b>�֣�&nbsp;&nbsp;
									����ͷ�<b>${exam.lowestmark}</b>�֣�&nbsp;&nbsp;
									����ע��${exam.remark}��
									</label><br>
								 	<table  class="table table-striped table-bordered table-hover">
										<thead>
											<tr>
												<th>ѧ�����</th>
												<th>ѧ������</th>
												<th>����</th>
												<th>����</th>
												<th>��ע</th>
											</tr>
										</thead>
										<tbody>
										 	<c:forEach items="${exam.examMarks}"  var="em" >
											<tr>
												<td>${em.studentid}</td>
												<td>${em.studentname}</td>
												<td>${em.rank}</td>
												<td>${em.mark}</td>
												<td>${em.remark}</td>
											</tr>
										 	</c:forEach>
										</tbody>
									</table>
								 		
									<button class="btn btn-info" type="button" onclick="location.href='<%=request.getContextPath() %>/manager/exams'" >
										<i class="icon-arrow-left"></i>
										����
									</button>
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