<%--
  Created by IntelliJ IDEA.
  User: Satellite
  Date: 2018/11/11
  Time: 上午 10:39
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<html>
<head>
    <title>ISLab Kanban Board</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
    <link rel="stylesheet" type="text/css" href="resources/css/login.css">
</head>
<body>
<div class="container">
    <jsp:include page="menuBar/adminBar.jsp"></jsp:include>
    <div class="col-md-13">
        <div class="panel panel-info">
            <div class="panel-heading"><h4><span class="glyphicon glyphicon-user">　帳戶管理</span></h4></div>
            <div class="panel-body">
                <table class="table table-bordered">
                    <thead>
                    <tr>
                        <th colspan="5" class="text-center">帳戶管理</th>
                    </tr>
                    </thead>
                    <tbody>
                    <tr>
                        <th class="text-center">職員編號</th>
                        <th class="text-center">中文名</th>
                        <th class="text-center">電話</th>
                        <th class="text-center">電子郵件</th>
                        <th class="text-center">參與專案(role)</th>
                    </tr>
                    <c:forEach items="${memberList}" var="member" varStatus="status">
                        <tr class="text-center">
                            <td>${member.getIdNumber()}</td>
                            <td>${member.getChineseName()}</td>
                            <td>${member.getPhone()}</td>
                            <td>${member.getEmail()}</td>
                            <td>${member.getRoleName()}</td>
                        </tr>
                    </c:forEach>
                    <c:if test = "${fn:length(memberList) == 0}">
                        <tr class="text-center">
                            <td colspan="5">查無資料</td>
                        </tr>
                    </c:if>
                    </tbody>
                </table>
                <button type='button' data-toggle='modal' data-target='#insert' class='btn btn-primary'>新增</button>
            </div>
        </div>
    </div>
</div><!-- /container -->
<form name="myForm" action="accountManage" method="POST">
    <div class="modal fade" id="insert" tabindex="-1" role="dialog" aria-labelledby="exampleModalCenterTitle" aria-hidden="true">
        <div class="modal-dialog modal-dialog-centered" role="document">
            <div class="modal-content">
                <div class="modal-header">
                    <h5 class="modal-title" id="title">新增帳戶</h5>
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                        <span aria-hidden="true">&times;</span>
                    </button>
                </div>
                <div class="modal-body">
                    <div class="form-group">
                        <label for="account" class="col-form-label">職員編號：</label>
                        <input type="text" class="form-control" id="account" name="account" value="${latestId}" readonly="readonly" required>
                        <label for="chinese_name" class="col-form-label">中文名：</label>
                        <input type="text" class="form-control" id="chinese_name" name="chinese_name" required>
                        <label for="english_name" class="col-form-label">英文名：</label>
                        <input type="text" class="form-control" id="english_name" name="english_name" required>
                        <label for="role_code" class="col-form-label">角色代碼：</label>
                        <select class="form-control" id="role_code" name="role_code">
                            <option value="1">1：Product Owner</option>
                            <option value="2">2：Developer</option>
                        </select>
                    </div>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-secondary" data-dismiss="modal">取消</button>
                    <input type="submit" class="btn btn-info" value="新增">
                </div>
            </div>
        </div>
    </div>
</form>
</body>
</html>
