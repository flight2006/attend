<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="GBK"%>
<!DOCTYPE html>
<html lang="en">
	<head>
		<meta charset="utf-8" />
		<title>����Ա��</title>
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
								����Ա��
							</small>
						</h1>
					</div> 
					<div class="row-fluid">
						
						<div class="span12">
							<!--PAGE CONTENT BEGINS-->
							<label for="form-field-8"> ���� <b>${cls.name}</b> Ա�������ܣ�${cls.manager }�� </label>
							<br>
							<form  class="form-inline" id="saveemployee" method="post" action="addemployee" >
									<input type="hidden"  name="departmentid" value="${cls.id}">
									<label class="control-label"  >���:&nbsp;&nbsp;</label>
									<input type="text" id="id" name="id" value="${cls.id}${length+1}" >&nbsp;&nbsp;
                                     <label class="control-label"   >����:&nbsp;&nbsp;</label>
                                     <input type="text" name="name" id="name"  placeholder="����">
									&nbsp;&nbsp;<label class="control-label"    >��ע:&nbsp;&nbsp;</label>
									<input type="text" name="remark" id="remark"  class="input-xlarge"   placeholder="��ע">
									<button class="btn btn-small btn-info" id="addemployee" type="button">
										<i class="icon-ok"></i>
										���Ա��
									</button>
									<button class="btn btn-small btn-info"  onclick="location.href='<%=request.getContextPath() %>/manager/department'" type="button">
										<i class="icon-arrow-left"></i>
										����
									</button>
							</form>

							<c:if test="${param.notice != null}">
								<div class="alert alert-info">
									<button type="button" class="close" data-dismiss="alert">
										<i class="icon-remove"></i>
									</button>
									<i class="icon-ok"></i>
									<strong>${param.notice}</strong>
								</div>
							</c:if>
							<hr>
							<table id="sample-table-1" class="table table-striped table-bordered table-hover">
								<thead>
									<tr>
										<th width="5%">#</th>
										<th width="10%">Ա�����</th>
										<th width="15%">Ա������</th>
										<th>��ע</th>
										<th width="15%">����</th>
									</tr>
								</thead>
								<tbody>
									<c:forEach items="${stlist}"  var="student" varStatus="sta" >
									<tr>
										<td>${sta.index+1}</td>
										<td>${student.id}</td>
										<td>${student.name}</td>
										<td>${student.remark}</td>
										<td>
											<a href="#myModal"  role="button" onclick="setvalue('${student.id}','${student.name}','${student.remark}')" class="btn  btn-mini btn-info" data-toggle="modal"><i class="icon-edit"></i>�༭</a>
											<button class="btn btn-mini btn-danger" onclick="if(window.confirm('ȷ��ɾ��Ա��${student.name}��')==true)location.href='<%=request.getContextPath() %>/manager/deletestudent?studentid=${student.id}&departmentid=${student.department_id}'"><i class="icon-remove"></i>ɾ��</button>
										</td>
									</tr>
								</c:forEach>
								</tbody>
							</table>
							
							<!-- Modal -->
							<div id="myModal" class="modal hide fade" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
							  	<form action="updatestudent" id="updatestudent" method="post"  class="form-inline">
								  <div class="modal-header">
								    <button type="button" class="close" data-dismiss="modal" aria-hidden="true">��</button>
								    <h3 id="myModalLabel">�༭Ա��</h3>
								  </div>
								  <div class="modal-body">
								  		<input type="hidden"  name="departmentid" value="${cls.id}">
								  		<input type="hidden"  name="id"  id="editid" >
										<label class="control-label"   >����:&nbsp;&nbsp;</label>
										<input type="text" name="name" class="input-medium"  id="editname"  >
										<br><br><label class="control-label"    >��ע:&nbsp;&nbsp;</label>
										<input type="text" name="remark" id="editremark"  class="input-xlarge" >
								  </div>
								  <div class="modal-footer">
								    <button  type="button" id="modify" class="btn btn-small btn-primary">����</button>
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
			$('#addemployee').on('click', function() {
				if($.trim($("#id").val())==''||isNaN($('#id').val())){
					alert('��������ȷ��Ա����ţ�');
					return;
				}else if($.trim($("#name").val())==''){
					alert('������Ա��������');
					return;
				}else{
					$("#saveemployee").submit();
				}
			});
			$('#modify').on('click', function() {
				if($.trim($("#editname").val())==''){
					alert('������Ա��������');
					return;
				}else{
					$("#updatestudent").submit();
				}
			});
		});
		
		function setvalue(id,name,remark){
			$("#editid").val(id);
			$("#editname").val(name);
			$("#editremark").val(remark);
		}
		
		</script>
	</body>
</html>