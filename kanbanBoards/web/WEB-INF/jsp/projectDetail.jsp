<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%--
  Created by IntelliJ IDEA.
  User: Satellite
  Date: 2018/11/13
  Time: 上午 11:48
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>ISLab Kanban Board</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
    <%--<link rel="stylesheet" media="all" href="https://islab1221.kanbantool.com/assets/kanbantool-base.css" />--%>
    <link rel="stylesheet" media="all" href="https://islab1221.kanbantool.com/assets/kanbantool-sdk.css" />
    <%--<script src="https://islab1221.kanbantool.com/assets/kanbantool-base.js"></script>--%>
    <script src="https://islab1221.kanbantool.com/assets/kanbantool-sdk.js">
        KT.init('islab1221', 'B2LUYYZNK6C9', {locale:'en'});
        KT.onInit(function(){
            console.log(KT.currentUser);
        })
    </script>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
    <link rel="stylesheet" type="text/css" href="resources/css/login.css">
    <%--GET https://islab1221.kanbantool.com/api/v1/boards/467625.xml?api_token=B2LUYYZNK6C9--%>
</head>
<body>
<div class="container">
    <jsp:include page="menuBar/PoBar.jsp"></jsp:include>
    <kt-board data-board-id="467625"></kt-board>
</div>

</body>
</html>
