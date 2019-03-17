<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%--
  Created by IntelliJ IDEA.
  User: Satellite
  Date: 2018/11/13
  Time: 上午 09:35
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
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
    <jsp:include page="menuBar/PoBar.jsp"></jsp:include>
    <div class="col-md-13">
        <div class="panel panel-info">
            <div class="panel-heading"><span class="glyphicon glyphicon-exclamation-sign">　</span>通知</div>
            <div class="panel-body">
                <table class="table table-bordered">
                    <tr>
                        <th class="text-center">通知編號</th>
                        <th class="text-center">通知事項</th>
                    </tr>
                    <c:forEach items="${infoList}" var="info" varStatus="status">
                        <tr class="text-center">
                            <td>${info.getId()}</td>
                            <td>${info.getContent()}</td>
                        </tr>
                    </c:forEach>
                    <c:if test = "${fn:length(infoList) == 0}">
                        <tr class="text-center">
                            <td colspan="2">查無通知</td>
                        </tr>
                    </c:if>
                </table>
            </div>
        </div>
    </div>
    <div class="col-md-13">
        <div class="panel panel-primary">
            <div class="panel-heading"><span class="glyphicon glyphicon-folder-open">　</span>專案</div>
            <div class="panel-body">
                <table class="table table-bordered">
                    <tr>
                        <th class="text-center">專案編號</th>
                        <th class="text-center">專案名稱</th>
                        <th class="text-center">專案人數</th>
                        <th class="text-center">剩餘天數</th>


                    </tr>
                    <c:forEach items="${projectList}" var="project" varStatus="status">
                        <tr class="text-center">
                            <td>${project.getId()}</td>
                            <td>${project.getProjectName()}</td>
                            <td>${project.getNumberOfMember()}</td>
                            <td>${project.getProjectRemainDay()}</td>
                        </tr>
                    </c:forEach>
                    <c:if test = "${fn:length(projectList) == 0}">
                        <tr class="text-center">
                            <td colspan="4">您尚無負責專案，請加把勁</td>
                        </tr>
                    </c:if>
                    <tr class="text-center">
                        <td colspan="5"><button data-toggle='modal' data-target='#insert' class="btn btn-danger">新增專案</button></td>
                    </tr>
                </table>
            </div>
        </div>
    </div>
</div>
<form action="PoMain" method="POST">
    <div class="modal fade" id="insert" tabindex="-1" role="dialog" aria-labelledby="exampleModalCenterTitle" aria-hidden="true">
        <div class="modal-dialog modal-dialog-centered" role="document">
            <div class="modal-content">
                <div class="modal-header">
                    <h5 class="modal-title" id="title">新增專案</h5>
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                        <span aria-hidden="true">&times;</span>
                    </button>
                </div>
                <div class="modal-body">
                    <div class="form-group">
                        <label for="project_name" class="col-form-label">專案名稱：</label>
                        <input type="text" class="form-control" id="project_name" name="project_name" required>
                        <label for="po_id" class="col-form-label">專案負責人ID：</label>
                        <input type="text" class="form-control" id="po_id" name="po_id" value="${account.getIdNumber()}" readonly="readonly" required>
                        <label for="start_date" class="col-form-label">開始日期：</label>
                        <input type="date" class="form-control" id="start_date" name="start_date" required>
                        <label for="end_date" class="col-form-label">結束日期：</label>
                        <input type="date" class="form-control" id="end_date" name="end_date" required>
                        <label for="developer" class="col-form-label">參與人員：</label>
                        <select class="form-control" id="developer" name="developer" multiple="multiple">
                            <c:forEach items="${developerList}" var="developer" varStatus="status">
                                <option value="${developer.getIdNumber()}">${developer.getIdNumber()}</option>
                            </c:forEach>
                        </select>
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-secondary" data-dismiss="modal">取消</button>
                        <input type="submit" class="btn btn-info" value="新增">
                    </div>
                </div>
            </div>
        </div>
    </div>
</form>
</body>
</html>
