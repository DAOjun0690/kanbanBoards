<%--
  Created by IntelliJ IDEA.
  User: Satellite
  Date: 2018/11/13
  Time: 上午 09:38
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<br>
<nav class="navbar navbar-default">
    <div class="container-fluid">
        <div class="navbar-header">
            <a class="navbar-brand" href="PoMain">IS Kanban Board</a>
        </div>
        <ul class="nav navbar-nav">
            <li class="active"><a href="PoMain">首頁</a></li>
            <%--<li class="dropdown">--%>
                <%--<a href="addTaskCard">新增任務卡</a>--%>
            <%--</li>--%>
            <%--<li class="dropdown">--%>
                <%--<a class="dropdown-toggle" data-toggle="dropdown" href="#"> 任務搜尋<span class="caret"></span></a>--%>
                <%--<ul class="dropdown-menu dropdown-menu-right">--%>
                    <%--<li>--%>
                        <%--<label for="exampleDropdownFormEmail1">欲搜尋任務</label>--%>
                        <%--<input type="email" class="form-control" id="exampleDropdownFormEmail1" placeholder="編號001">--%>
                        <%--<button type="submit" class="btn btn-primary">開始搜尋</button>--%>
                    <%--</li>--%>
                <%--</ul>--%>
            <%--</li>--%>
        </ul>

        <ul class="nav navbar-nav navbar-right">
            <li class="dropdown">
                <a class="dropdown-toggle glyphicon glyphicon-user" data-toggle="dropdown" href="#"> ${account.getChineseName()}
                    <span class="caret"></span>
                </a>
                <ul class="dropdown-menu dropdown-menu-right">
                    <li><a href="userInformation">我的帳戶</a></li>
                    <li><a href="#">修改密碼</a></li>

                </ul>
            </li>
            <li><a href="login"><span class="glyphicon glyphicon-log-in"></span> 登出</a></li>
        </ul>
    </div>
</nav>

