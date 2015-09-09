<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="GBK"%>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="utf-8" />
  <title>�������</title>
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
            �������
          </small>
        </h1>
      </div>
      <div class="row-fluid">

        <div class="span12">
          <!--PAGE CONTENT BEGINS-->
          <label for="form-field-8">&nbsp;&nbsp;&nbsp; <b>${student.name}</b> �����:<b>${student.id}</b>��Ա���ĳ������  </label>
          <br>
          <table id="sample-table-1" class="table table-striped table-bordered table-hover">
            <thead>
            <tr>
              <th width="6%">���</th>
              <th>Ա��ID</th>
              <th>��������ID</th>
              <th>��ʱ��</th>
              <th>��ע</th>

            </tr>
            </thead>
            <tbody>
            <c:forEach items="${attendslist}"  var="em" varStatus="sta" >
              <tr>
                <td>${sta.index+1}</td>
                <td>${em.emp_id}</td>
                <td>${em.department_id}</td>
                <td>${em.punchtime}</td>
                <td><b>${em.remark}</b></td>
              </tr>
            </c:forEach>
            </tbody>
          </table>
          <button class="btn btn-info" type="button" onclick="location.href='<%=request.getContextPath() %>/manager/employee'" >
            <i class="icon-arrow-left"></i>
            ����
          </button>
          <!--PAGE CONTENT ENDS-->
        </div><!--/.span-->
      </div><!--/.row-fluid-->
    </div><!--/.page-content-->
  </div><!--/.main-content-->
</div><!--/.main-container-->

<%@include file="/WEB-INF/views/common/js.jsp" %>
<script type="text/javascript">
  $(function() {
    $('#leavemessage').on('click', function() {
      if($.trim($("#content").val())==''){
        alert('��������������');
        return;
      }else{
        $("#addmessage").submit();
      }
    });

  });
</script>
</body>
</html>