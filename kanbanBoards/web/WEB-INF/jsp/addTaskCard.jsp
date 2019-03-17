<%--
  Created by IntelliJ IDEA.
  User: WHM
  Date: 2018/12/24
  Time: 下午 07:56
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<html>
<head>
    <title>IS Kanban Board</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
    <link rel="stylesheet" type="text/css" href="resources/css/login.css">

    <script src="http://code.jquery.com/jquery-1.7.1.min.js"></script>
    <%--<script src="https://api.trello.com/1/client.js?key=1e5893584182dc02c7ea4bb06bc14e7d"></script>--%>
    <script src="https://api.trello.com/1/client.js?key=c9ec55bd91ac6733c0410aeec42045e0"></script>

    <script>
        var authenticationSuccess = function() {
            console.log('Successful authentication');
        };

        var authenticationFailure = function() {
            console.log('Failed authentication');
        };


        <%--<c:if test = "${taskIDjsp == 1}">--%>
            <%--var url="https://trello.com/b/UHCLf177/test";--%>
            <%--var myList = "5c2047e2971011688cd4d823";--%>
            <%--// alert("111111!!");--%>
        <%--</c:if>--%>
        <%--<c:if test = "${taskIDjsp >= 2}">--%>
            <%--var url="https://trello.com/b/m5WqGvX3/test1";--%>
            <%--var myList = "5c21a3d2ddccf93847ee7bbd";--%>
             <%--// alert("2222!!");--%>
        <%--</c:if>--%>
        <c:if test = "${taskIDjsp == 1}">
            var url="https://trello.com/b/uSHd2Skm/test";
            var myList = "5c21b3446288032647b618f4";
            alert("1111!!");
        </c:if>
        <c:if test = "${taskIDjsp >= 2}">
            var url="https://trello.com/b/Wy1I9Ekc/islab";
            var myList = "5bbc5fe79f8a2a2c7a2bb084";
            alert("2222!!");
        </c:if>

        window.Trello.authorize({
            type: 'popup',
            name: 'Getting Started Application',
            scope: {
                read: 'true',
                write: 'true' },
            expiration: 'never',
            success: authenticationSuccess,
            error: authenticationFailure
        });

        // var myList1 = "5c2047e2971011688cd4d823";
        // var myList2 = "5bbc5fe79f8a2a2c7a2bb084";

        var creationSuccess = function (data) {
            console.log('Card created successfully.');
            console.log(JSON.stringify(data, null, 2));
        };

        function UploadTrello()
        {
            var TaskName = document.getElementById("TaskName").value;
            var TaskDetail = document.getElementById("TaskDetail").value;
            var TaskDeadline = document.getElementById("TaskDeadline").value;
            var newCard = {
                idList: myList,
                name: TaskName,
                desc: TaskDetail,
                due: TaskDeadline,
                pos: 'top'
            };

            window.Trello.post('/cards/', newCard, creationSuccess);

            alert("上傳成功!!");
        }
    </script>
</head>
<body>
    <div class="container">
        <c:if test = "${account.getRoleCode() == 0}">
            <jsp:include page="menuBar/adminBar.jsp"></jsp:include>
        </c:if>
        <c:if test = "${account.getRoleCode() == 1}">
            <jsp:include page="menuBar/PoBar.jsp"></jsp:include>
        </c:if>
        <c:if test = "${account.getRoleCode() == 2}">
            <jsp:include page="menuBar/developerBar.jsp"></jsp:include>
        </c:if>
        <div class="col-md-13">
            <div class="panel panel-danger">
                <div class="panel-heading"><h4 align="center"><span class="glyphicon glyphicon-credit-card">　新增卡片</span></h4></div>
                <div class="panel-body">
                    <form>
                        <table class="table table-bordered">
                            <tbody>
                            <tr class="text-center">
                                <td>任務名稱</td>
                                <td><input type="text" id="TaskName" name="name" value="${project.getId()}" size="30" /></td>
                            </tr>
                            <tr class="text-center">
                                <td>任務描述</td>
                                <td><input type="text" id="TaskDetail" name="name" value="" size="30" /></td>
                            </tr>
                            <tr class="text-center">
                                <td>任務截止日期</td>
                                <td>
                                    <div class="col-md-4"></div>
                                    <div class="col-lg-4 center" >
                                        <input type="date" id="TaskDeadline"  class="form-control" name="name" value="2019-01-11" min="2018-01-01" max="2019-12-31"  />
                                    </div>
                                    <div class="col-md-4"></div>
                                </td>
                            </tr>
                            </tbody>
                        </table>
                        <input type="reset" value="上傳" style="font-size:30px;" onClick="UploadTrello()">
                    </form>
                </div>
            </div>
        </div>
    </div>
</body>
</html>
