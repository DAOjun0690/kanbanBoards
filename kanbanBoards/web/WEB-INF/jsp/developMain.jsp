<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%--
  Created by IntelliJ IDEA.
  User: Satellite
  Date: 2018/10/9
  Time: 下午 03:43
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
    <jsp:include page="menuBar/developerBar.jsp"></jsp:include>
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
</div><!-- /container -->

</body>
</html>
