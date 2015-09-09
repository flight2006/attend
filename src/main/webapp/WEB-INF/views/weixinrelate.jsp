<%--
  Created by IntelliJ IDEA.
  User: Flight
  Date: 2015/7/31
  Time: 21:47
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="GBK"%>
<html lang="en">
<head>
  <meta charset="utf-8" />
  <title>΢��ID����</title>
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
            ΢��ID����Ա�����
          </small>
        </h1>
        <br>
      </div>

      <div class="row-fluid">

        <div class="span12">
              <form action="<%=request.getContextPath()%>/manager/messagesenderrelatepage" id="messagesenderrelatepage" method="post" class="form-inline">
                <input type="hidden" name="weixinid" value="${weixinid}">

                  <label class="control-label">Ϊ΢��ID:${weixinid}����Ա�����:</label>
                <br>
                  <div>
                    <br>
                    <input type="text" name="employeeid" id="employeeid" placeholder="Ա�����">*
                    <span class="help-inline">����ϵͳ������Ա�����</span>
                  </div>
                <br/>

                <button class="btn btn-info" id="add" type="button">
                  <i class="icon-inbox"></i>
                  ����
                </button>
                <button class="btn btn-info" type="button" onclick="location.href='<%=request.getContextPath() %>/manager/messages'" >
                  <i class="icon-arrow-left"></i>
                  ����
                </button>
                <br><br>
                <c:if test="${param.notice != null}">
                  <div class="alert alert-info">
                    <button type="button" class="close" data-dismiss="alert">
                      <i class="icon-remove"></i>
                    </button>
                    <i class="icon-ok"></i>
                    <strong>${param.notice}</strong>
                  </div>
                </c:if>
              </form>
          </div>
        </div>
      </div>
    </div>
  <%@include file="/WEB-INF/views/common/js.jsp" %>
  <script type="text/javascript">
    $(function () {
      $('#add').on('click',function(){
        if($.trim($('#employeeid').val()) == '' || isNaN($('#employeeid').val())){
          alert('������Ա�����!');
          return;
        }else{
          $("#messagesenderrelatepage").submit();
        }
      });
    });

  </script>
</body>
</html>
