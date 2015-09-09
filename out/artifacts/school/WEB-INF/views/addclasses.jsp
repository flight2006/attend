<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="GBK"%>
<!DOCTYPE html>
<html lang="en">
	<head>
		<meta charset="utf-8" />
		<title>��Ӱ༶</title>
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
								<i class="icon-list-alt"></i>
								��Ӳ���
							</small>
						</h1>
					</div> 
					<div class="row-fluid">
						
						<div class="span12">
							<!--PAGE CONTENT BEGINS-->
							<form action="adddepartment" id="adddepartment" class="form-horizontal" method="post">
								<div class="control-group">
									<label class="control-label" for="id">���ű��</label>
									<div class="controls">
										<input type="text" name="id" id="id" placeholder=���ű��>*
										<span class="help-inline"><c:if test="${param.notice==null}">�������Բ��ű��Ϊ302</c:if>  <font color="red"><c:if test="${param.notice!=null}">${param.notice}</c:if></font></span>
									</div>
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
									<div class="form-actions">
									<button class="btn btn-info" id="add" type="button">
										<i class="icon-ok bigger-110"></i>
										��Ӳ���
									</button>
									&nbsp; &nbsp; &nbsp;
									<button class="btn btn-info" type="button" onclick="location.href='<%=request.getContextPath() %>/manager/department'" >
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
				if($.trim($("#id").val())==''||isNaN($('#id').val())){
					alert('��������ȷ�İ༶��ţ�');
					return;
				}else if($.trim($("#name").val())==''){
					alert('������༶���ƣ�');
					return;
				}else if($.trim($("#manager").val())==''){
					alert('��������������ƣ�');
					return;
				}else{
					$("#adddepartment").submit();
				}
			});
			
		});
		</script>
	</body>
</html>