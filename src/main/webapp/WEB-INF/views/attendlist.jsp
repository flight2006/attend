<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="GBK"%>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="utf-8" />
  <title>出勤情况</title>
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
            出勤情况
          </small>
        </h1>
      </div>
      <div class="row-fluid">

        <div class="span12">
          <!--PAGE CONTENT BEGINS-->
          <label for="form-field-8">&nbsp;&nbsp;&nbsp; <b>${student.name}</b> （编号:<b>${student.id}</b>）员工的出勤情况  </label>
          <br>
          <table id="sample-table-1" class="table table-striped table-bordered table-hover">
            <thead>
            <tr>
              <th width="6%">编号</th>
              <th>员工ID</th>
              <th>所属部门ID</th>
              <th>打卡时间</th>
              <th>备注</th>

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
            返回
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
        alert('请输入留言内容');
        return;
      }else{
        $("#addmessage").submit();
      }
    });

  });
</script>
</body>
</html>