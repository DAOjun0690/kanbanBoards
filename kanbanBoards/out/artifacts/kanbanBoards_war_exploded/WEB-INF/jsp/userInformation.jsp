<%--
  Created by IntelliJ IDEA.
  User: Satellite
  Date: 2018/11/11
  Time: 上午 10:02
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
    <script>
        var loadFile = function(event) {
            var output = document.getElementById('preview');
            output.src = URL.createObjectURL(event.target.files[0]);
        };
        var loadFile1 = function(event) {
            var output = document.getElementById('preview1');
            output.src = URL.createObjectURL(event.target.files[0]);
        };
        var reset = function(){
            var output = document.getElementById('preview');
            output.src = "";
            var input = document.getElementById('image');
            input.value = "";
        };
        var reset1 = function(){
            var output1 = document.getElementById('preview1');
            output1.src = "";
            var input1 = document.getElementById('image1');
            input1.value = "";
        };
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
            <div class="panel-heading"><h4 align="center"><span class="glyphicon glyphicon-user">　使用者資訊</span></h4></div>
            <div class="panel-body">
                <table class="table table-bordered">
                    <thead>
                    <tr>
                        <th colspan="2" class="text-center">基本資料</th>
                    </tr>
                    </thead>
                    <tbody>
                    <tr class="text-center">
                        <td>大頭貼</td>
                        <c:if test = "${fn:length(imagePath) == 0}">
                            <td class="text-center"><button type='button' data-toggle='modal' data-target='#uploadImage' class='btn btn-success' onclick="reset()">上傳圖片</button></td>
                        </c:if>
                        <c:if test = "${fn:length(imagePath) > 0}">
                            <td><img src="${imagePath}" width="150" height="150"></td>
                        </c:if>
                    </tr>
                    <tr class="text-center">
                        <td>職員編號</td>
                        <td>${account.getRegNumber()}</td>
                    </tr>
                    <tr class="text-center">
                        <td>電子郵件</td>
                        <td>${account.getEmail()}</td>
                    </tr>
                    <tr class="text-center">
                        <td>電話</td>
                        <td>${account.getPhone()}</td>
                    </tr>
                    <tr class="text-center">
                        <td>英文姓名</td>
                        <td>${account.getEnglishName()}</td>
                    </tr>
                    <tr class="text-center">
                        <td>中文姓名</td>
                        <td>${account.getChineseName()}</td>
                    </tr>
                    <tr class="text-center">
                        <td>暱稱</td>
                        <td>${account.getNickname()}</td>
                    </tr>
                    <tr class="text-center">
                        <td>參與專案(role)</td>
                        <td>${account.getRoleName()}</td>
                    </tr>
                    </tbody>
                </table>
                <%--<button type='button' data-toggle='modal' data-target='#editinformation' class='btn btn-danger' onclick="reset1()">修改帳戶資訊</button>--%>
                <button type='button' data-toggle='modal' data-target='#editinformation' class='btn btn-danger' onclick="" >修改帳戶資訊</button>
                <br><br>
                <ul class="drop-down-menu">
                    <li><a href="#">關於我們</a>
                        <ul>
                            <li><a href="#">服務據點</a></li>
                        </ul>
                    </li>
                </ul>
            </div>
        </div>
    </div>
</div>
<form name="myForm" action="userInformation" enctype="multipart/form-data" method="POST">
    <div class="modal fade" id="uploadImage" tabindex="-1" role="dialog" aria-labelledby="exampleModalCenterTitle" aria-hidden="true">
        <div class="modal-dialog modal-dialog-centered" role="document">
            <div class="modal-content">
                <div class="modal-header">
                    <h5 class="modal-title" id="title">上傳圖片</h5>
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                        <span aria-hidden="true">&times;</span>
                    </button>
                </div>
                <div class="modal-body">
                    <div class="form-group">
                        <label for="image" class="col-form-label">圖片檔案：</label>
                        <input type="file" class="form-control" id="image" name="image" accept="image/*" onchange="loadFile(event)" required>
                        <img id="preview" width="250" height="250" src=""/>
                    </div>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-secondary" data-dismiss="modal">取消</button>
                    <input type="submit" class="btn btn-info" value="上傳">
                </div>
            </div>
        </div>
    </div>
</form>

<form name="myForm" action="userInformation" enctype="multipart/form-data" method="POST">
    <input type=hidden name="checknumber" value="1">
    <div class="modal fade bd-example-modal-lg" id="editinformation" tabindex="-1" role="dialog" aria-labelledby="exampleModalCenterTitle" aria-hidden="true">
        <div class="modal-dialog modal-dialog-centered" role="document">
            <div class="modal-content">
                <div class="modal-header">
                    <h5 class="modal-title" id="title1">修改帳戶資料</h5>
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                        <span aria-hidden="true">&times;</span>
                    </button>
                </div>
                <div class="col-md-10">
                    <div class="panel-body">
                        <table class="table table-bordered">
                            <tbody>
                            <tr class="text-center">
                                <td>大頭貼</td>
                                <td>
                                    <div class="form-group">
                                        <label for="image1" class="col-form-label">圖片檔案：</label>

                                        <img id="preview1" width="300" height="300" src="${imagePath}">
                                        <input type="file" class="form-control" id="image1" name="image1" accept="image/*" size="50" onchange="loadFile1(event)" required>
                                        <%--<img id="preview1" width="300" height="300" src=""/>--%>
                                    </div>
                                </td>
                                <%--<c:if test = "${fn:length(imagePath) == 0}">--%>
                                <%--<td class="text-center"><button type='button' data-toggle='modal' data-target='#uploadImage' class='btn btn-success' onclick="reset()">上傳圖片</button></td>--%>
                                <%--</c:if>--%>
                                <%--<c:if test = "${fn:length(imagePath) > 0}">--%>
                                <%--<td><img src="${imagePath}"></td>--%>
                                <%--</c:if>--%>
                            </tr>
                            <input type=hidden class="form-control" id="account" name="account" value="${account.getIdNumber()}">
                            <tr class="text-center">
                                <td>職員編號</td>
                                <td><input type="text" class="form-control" id="regNumber" name="regNumber" value="${account.getRegNumber()}" readonly="readonly" required></td>
                            </tr>
                            <tr class="text-center">
                                <td>電子郵件</td>
                                <td><input type="email" class="form-control" name="email" value="${account.getEmail()}"></td>
                            </tr>
                            <tr class="text-center">
                                <td>電話</td>
                                <td><input type="text" class="form-control" name="phone" value="${account.getPhone()}"></td>
                            </tr>
                            <tr class="text-center">
                                <td>英文姓名</td>
                                <td><input type="text" class="form-control" name="english_name" value="${account.getEnglishName()}"></td>
                            </tr>
                            <tr class="text-center">
                                <td>中文姓名</td>
                                <td><input type="text" class="form-control" name="chinese_name" value="${account.getChineseName()}"></td>
                            </tr>
                            <tr class="text-center">
                                <td>暱稱</td>
                                <td><input type="text" class="form-control" name="nickname" value="${account.getNickname()}"></td>
                            </tr>
                            <tr class="text-center">
                                <td>參與專案(role)</td>
                                <td>${account.getRoleName()}</td>
                            </tr>
                            </tbody>
                        </table>
                    </div>
                </div>

                <div class="modal-footer">
                    <input type="submit" class="btn btn-info" value="確認">
                    <button type="button" class="btn btn-secondary" data-dismiss="modal">取消</button>
                </div>
            </div>
        </div>
    </div>
</form>
</body>
</html>

