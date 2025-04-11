<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Store Manga</title>
        <!-- Bootstrap CSS -->
        <link href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet">
        <link href="https://fonts.googleapis.com/css2?family=Merriweather&display=swap" rel="stylesheet">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.1/css/all.min.css"/>
        <link rel="stylesheet" href="CSS/style_header.css"/>
        <link rel="stylesheet" href="CSS/style_gif.css"/>
    </head>
    <body>

        <style>
            .navbar-nav .nav-item .nav-link .far.fa-heart {
                color: red;
                margin-right: 5px;
            }


            .navbar-nav .nav-item .nav-link .fas.fa-shopping-cart {
                color: #007bff;
                margin-right: 5px;
            }

            .ani-fire {
                background-image: url(https://media.licdn.com/dms/image/D4D22AQHYReUG4Q8LFg/feedshare-shrink_2048_1536/0/1709545843533?e=2147483647&v=beta&t=Td7oTKvBOGiBvjBT7gxw3pdwfsHs9yJOtXWSmiUa0IM);
                background-position: 0 -1000px;
                -webkit-background-clip: text;
                -webkit-text-fill-color: transparent;
                animation: fire 4s linear infinite;
            }
        </style>

        <header class="sticky-top">
            <nav class="navbar navbar-expand-lg navbar-dark bg-dark" style="align-items: flex-start">
                
                <a class="navbar-brand" href="home">N4GManga Store</a>
                <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
                    <span class="navbar-toggler-icon"></span>
                </button>

                <!-- Form tìm kiếm -->
                <form class="form-inline ml-auto" id="s1" action="search" onsubmit="return validateSearch()">
                    <div class="form-group mr-2">
                        <select class="form-control" id="key" name="key" onchange="change()">
                            <option value="0">Danh mục</option>
                            <option value="0">Tất cả sản phẩm</option>
                            <c:forEach items="${requestScope.data1}" var="c">
                                <option value="${c.id}">${c.name}</option>
                            </c:forEach>
                        </select>
                    </div>
                    <div class="form-group mr-2">
                        <input type="text" class="form-control" id="key2" name="key2" placeholder="Search...">
                    </div>
                    <button type="submit" class="btn btn-light">Tìm kiếm</button>
                </form>

                <div class="collapse navbar-collapse" id="navbarSupportedContent">
                    <!-- Các tùy chọn trước -->
                    <ul class="navbar-nav ml-auto">
                        
                        <li class="nav-item">
                            <a class="nav-link" href="viewCart">
                                <i class="fas fa-shopping-cart" style="color: white;"></i> Giỏ hàng

                                <c:if test="${sessionScope.countCart != null}">
                                    <span class="badge badge-pill badge-danger">
                                        ${sessionScope.countCart}
                                    </span>
                                </c:if>
                            </a>
                        </li>
                        <!-- Phần người dùng -->
                        <c:choose>
                            <c:when test="${empty sessionScope.account}">
                                <!-- Nếu không có người dùng đăng nhập -->
                                <li class="nav-item">
                                    <a class="nav-link" href="login">Login</a>
                                </li>
                            </c:when>
                            <c:otherwise>
                                <li class="nav-item dropdown">
                                    <a class="nav-link dropdown-toggle" href="#" id="navbarDropdown" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                                        <i class="fas fa-user"></i> ${sessionScope.account.username}
                                    </a>
                                    <div class="dropdown-menu dropdown-menu-right" aria-labelledby="navbarDropdown">
                                        <a class="dropdown-item" href="profile">Profile</a>
                                        <!-- Nếu là admin -->
                                        <c:if test="${sessionScope.account.getRole().getRoleID()==1}">
                                            <a class="dropdown-item" href="dashBroad">Quản lí</a> 
                                        </c:if>
                                        <!-- Nếu là user -->
                                        <c:if test="${sessionScope.account.getRole().getRoleID()==2}">
                                            <a class="dropdown-item" href="status">Trạng thái đơn hàng</a>
                                        </c:if>
                                        <div class="dropdown-divider"></div>
                                        <a class="dropdown-item" href="#" onclick="confirmLogout()"><i class="fas fa-sign-out-alt"></i> Logout</a>
                                    </div>
                                </li>
                            </c:otherwise>
                        </c:choose>
                    </ul>
                </div>
            </nav>
        </header>
        <script>

            function change() {
                document.getElementById("s1").submit();
                var selectBox = document.getElementById("key");

            }


            function validateSearch() {
                var keyInput = document.getElementById("key2").value;
                if (keyInput.trim() === "") {
                    alert("Vui lòng nhập tên hoặc miêu tả sản phẩm trước khi tìm kiếm.");
                    return false;
                }
                return true;
            }


            function confirmLogout() {
                var confirmLogout = confirm("Bạn muốn đăng xuất tài khoản ?");
                if (confirmLogout) {
                    window.location.href = "<c:url value='/logout'/>";
                }
            }


        </script>

    </script>
    <script src="JS/toast_msg.js"></script>
    <!-- Bootstrap JS and jQuery (optional, for dropdowns) -->
    <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.2/dist/umd/popper.min.js"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</body>
</html>