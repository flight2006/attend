<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="GBK"%>
<!DOCTYPE html>
<html lang="en">
	<head>
		<meta charset="utf-8" />
		<title>΢�Ž�����Ϣ</title>
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
								<i class="icon-comment"></i>
								΢�Ž�����Ϣ
							</small>
						</h1>
					</div>
					<button class="btn btn-mini btn-primary"  align="right" onclick="location.href='<%=request.getContextPath() %>/manager/MenuManager'" ><i class="icon-user"></i>&nbsp;���ں��Զ���˵�����</button>
					<c:if test="${param.notice != null}">
						<div class="alert alert-info">
							<button type="button" class="close" data-dismiss="alert">
								<i class="icon-remove"></i>
							</button>
							<i class="icon-ok"></i>
							<strong>${param.notice}</strong>
						</div>
					</c:if>
					<div class="row-fluid">
						<br>
						<div class="span12">
							<!--PAGE CONTENT BEGINS-->
							<c:if test="${notice != null}">
								${notice}
							</c:if>
							<table id="sample-table-1" class="table table-striped table-bordered table-hover">
								<thead>
									<tr >
								      <th  width="5%">#</th>
								      <th width="15%">MsgId</th>
								      <th width="10%">MsgType</th>
								      <th width="15%">FromUserName</th>
								      <th width="15%">CreateTime</th>
								      <th width="15%">Content</th>
										<th width="15%">JoinIn</th>
								    </tr>
								  </thead>
								  <tbody>
								  	<c:forEach items="${messageList}"  var="message"   varStatus="st" >
								  		<tr>
								  		<td>${st.index+1}</td>
								  		<td>${message.msgId}</td>
								  		<td>${message.msgType}</td>
								  		<td>${message.fromUserName}</td>
								  		<td>${message.createTime}</td>
								  		<td>${message.content}</td>
											<td>
												<button class="btn btn-mini btn-primary" onclick="location.href='<%=request.getContextPath() %>/manager/messagesenderrelate?weixinid=${message.fromUserName}'" ><i class="icon-user"></i>&nbsp;΢��ID����Ա��</button>
											</td>
								  		</tr>
								  	</c:forEach>
								  </tbody>
							</table>
							<div id="myModal" class="modal hiden fade" tabindex="-1" role="dialog" aria-describedby="myModalLabel" aria-hidden="true">
								<div class="modal-header">
									<button type="button" class="close" data-dismiss="modal" aria-hidden="true">��</button>
									<h3 id="myModalLabel">��ӹ���</h3>
								</div>

							</div>

					 		<div class="dataTables_paginate paging_bootstrap pagination">
							  <button class="btn btn-success btn-mini" onclick="location.href='<%=request.getContextPath() %>/manager/messages?pagenum=${pagenum-1}'" <c:if test="${pagenum <= 1}">disabled="disabled"</c:if>    >&laquo;</button>
							  <button class="btn btn-success btn-mini" disabled="disabled">�� ${pagenum} ҳ</button>
							  <button class="btn btn-success btn-mini" onclick="location.href='<%=request.getContextPath() %>/manager/messages?pagenum=${pagenum+1}'" <c:if test="${length < 10}">disabled="disabled"</c:if> >&raquo;</button>
					 		</div>
							 
							<!--PAGE CONTENT ENDS-->
					</div><!--/.span-->
				</div><!--/.row-fluid-->
			</div><!--/.page-content-->
		</div><!--/.main-content-->
	</div><!--/.main-container-->

		<%@include file="/WEB-INF/views/common/js.jsp" %>


	</body>
</html>