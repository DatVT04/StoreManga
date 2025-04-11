<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Profile</title>
    
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
</head>
<body>
<%@include file="/Views/header.jsp" %>
<c:set var="c" value="${requestScope.dataProfile}"></c:set>
<c:set var="profileExists" value="${requestScope.profileExists}" />
<div class="container mt-5">
    <br>
    <h4 class="text-center">My Profile</h4>
    <form action="profile" method="post">
        <div class="row">
            <div class="col-md-6 offset-md-3">
                <div class="form-group">
                    <label for="username">Username:</label>
                    <input type="text" class="form-control" name="username" value="${sessionScope.username}" readonly>
                </div>
                <div class="form-row">
                    <div class="form-group col-md-6">
                        <label for="firstname">First Name:</label>
                        <input type="text" class="form-control" name="firstname" value="${c.firstName}" >
                    </div>
                    <div class="form-group col-md-6">
                        <label for="lastname">Last Name:</label>
                        <input type="text" class="form-control" name="lastname" value="${c.lastName}" >
                    </div>
                </div>
                <div class="form-group">
                    <label for="email">Email:</label>
                    <input type="email" class="form-control" name="email" value="${c.getUser().email}" required>
                </div>
                <div class="form-group">
                    <label for="gender">Gender:</label>
                    <select class="form-control" name="gender">
                        <option value="true" ${c.gender == true ? 'selected' : ''}>Male</option>
                        <option value="false" ${c.gender == false ? 'selected' : ''}>Female</option>
                    </select>
                </div>
                <div class="form-group">
                    <label for="address">Address:</label>
                    <input type="text" class="form-control" name="address" value="${c.adress}">
                </div>
                <div class="form-group">
                    <label for="phone">Phone Number:</label>
                    <input type="text" class="form-control" name="phone" value="${c.phone}">
                </div>
                <div>
                    <button type="button" class="btn-secondary" onclick="window.location.href = 'changepass'">Change password</button>
                </div>
                <br>
                <button type="submit" class="btn btn-primary btn-block">Save</button>
                <input type="hidden"  name="profileID" value="${c.profileID}">
            </div>
        </div>
    </form>
<!--    <button type="button" class="btn btn-primary" onclick="window.location.href = 'home'">Trở lại</button>-->
</div>
<br>
<script src="JS/app.js"></script>
<%@include file="/Views/footer.jsp" %>
</body>
</html>
