<%--
  Created by IntelliJ IDEA.
  User: Satellite
  Date: 2018/11/8
  Time: 上午 10:51
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>ISLab Kanban Board</title>
    <link rel="stylesheet" href="resources/css/bootstrap.min.css">
    <link rel="stylesheet" type="text/css" href="resources/css/login.css">
</head>
<body>
<div class="container">
    <div class="card card-container">
        <img id="profile-img" class="profile-img-card" src="https://upload.wikimedia.org/wikipedia/commons/3/31/Taipeitech.jpg"/>
        <p id="profile-name" class="profile-name-card"></p>
        <form class="form-signin" action="" method="POST">
            <span id="reauth-email" class="reauth-email"></span>
            <input type="text" id="inputID" name="inputID" class="form-control" placeholder="學號" required autofocus>
            <input type="password" id="inputPassword" name="inputPassword" class="form-control" placeholder="密碼" >
            <p style="color:red;"> ${error}</p>
            <div id="remember" class="checkbox">
            </div>
            <button class="btn btn-lg btn-primary btn-block btn-signin" type="submit">登入</button>
        </form>
    </div><!-- /card-container -->

</div><!-- /container -->
</body>
</html>
