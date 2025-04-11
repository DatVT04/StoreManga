<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Đổi mật khẩu</title>
        <!-- Bootstrap CSS -->
        <link href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" rel="stylesheet">
    </head>
    <style>

        .form-control {
            max-width: 300px; /* Thu nhỏ ô input */
        }
    </style>

    <body>

        <%@include file="/Views/header.jsp" %>
        <div class="container">

            <!-- Error message -->
            <c:if test="${not empty requestScope.error}">
                <div id="error-message" style="color: red">
                    ${requestScope.error}
                </div>
               
            </c:if>

            <!-- Success message -->
            <c:if test="${not empty requestScope.success}">
                <div id="error-message" style="color: green">
                    ${requestScope.success}
                </div>
             
            </c:if>

            <form class="mt-3" action="changepass" method="post">

                <div class="form-group row">
                    <label for="oldPassword" class="col-sm-2 col-form-label">Old Password:</label>
                    <div class="col-sm-10">
                        <input type="password" class="form-control" id="oldPassword" name="oldPassword" width="50%" required>
                    </div>
                </div>
                <div class="form-group row">
                    <label for="newPassword" class="col-sm-2 col-form-label">New Password:</label>
                    <div class="col-sm-10">
                        <input type="password" class="form-control" id="newPassword" name="newPassword" maxlength="50" required>
                    </div>
                </div>
                <div class="form-group row">
                    <label for="confirmPassword" class="col-sm-2 col-form-label">Confirm New Password:</label>
                    <div class="col-sm-10">
                        <input type="password" class="form-control" id="confirmPassword" name="confirmPassword" maxlength="50" required>
                    </div>
                </div>
                <div class="form-group row">
                    <div class="col-sm-10 offset-sm-2">
                        <input type="submit" class="btn btn-primary mr-3" value="Save change">
                    </div>
                </div>
            </form>
<!--            <button type="button" class="btn btn-primary" onclick="window.location.href = 'profile'">Trở lại</button>-->
            <br>
        </div>
        <br>
        <%@include file="/Views/footer.jsp" %>
        <script src="JS/app.js"></script>
        <!-- Bootstrap JS -->
        <script src="https://code.jquery.com/jquery-3.2.1.slim.min.js"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"></script>
    </body>
</html>
