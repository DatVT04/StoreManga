<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Products</title>
        
        <link rel="stylesheet" href="CSS/style_productsM.css"/>
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
        <link rel="stylesheet" type="text/css" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.1/css/all.min.css">
        <script src="https://code.jquery.com/jquery-3.6.0.min.js" integrity="sha256-/xUj+3OJU5yExlq6GSYGSHk7tPXikynS7ogEvDej/m4=" crossorigin="anonymous"></script>

    </head>
    <style>
        .pagination a {
            display: inline-block;
            padding: 5px 10px;
            margin: 0 5px;
            color: #333;
            text-decoration: none;
            border: 1px solid #ccc;
            border-radius: 5px;
        }
        .pagination a.active{
            background-color: #333;
            color: #fff;
        }
    </style>

    <body> 
        <script>
            var error = "${requestScope.errorDel}";
            if (error) {
                alert(error);
                window.location.href = "managerProduct";
            }
        </script>

        <%-- Include header --%>
        <%@include file="/Views/headerAdmin.jsp" %>

        <c:if test="${not empty requestScope.listStock}">
            <div class="alert alert-danger alert-dismissible fade show" role="alert">
                <strong>Thông báo!</strong> Các sản phẩm sau đây đã hết hàng:
                <ul>
                    <c:forEach items="${requestScope.listStock}" var="productName">
                        <li>${productName}</li>
                        </c:forEach>
                </ul>
                <button type="button" class="close" data-dismiss="alert" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>
        </c:if>
        <div class="container">
            <div id="content-wrapper">

                <h3 style="text-align: center">DANH SÁCH TRUYỆN</h3>
                <button type="button" class="search-btn btn btn-primary btn-add floating-add-button" onclick="location.href = 'addProducts'">
                    <i class="fa fa-plus"></i> Add
                </button>

                <table class="table table-striped">
                    <thead class="thead-dark">
                        <tr>
                            <th>ID</th>
                            <th>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Ảnh</th>
                            <th>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Tên sản phẩm</th>
                            <th>Số lượng</th>
                            <th>&nbsp;&nbsp;Giá gốc</th>
                            <th>Giá bán</th>
                            <th>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Option</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach items="${requestScope.product2}" var="p">
                            <tr>
                                <td>${p.id}</td>
                                <td><img src="${p.image}" alt="img"/></td>
                                <td>${p.name}</td>
                                <td>&nbsp;&nbsp;&nbsp;${p.quanity}</td>
                                <td style="color: red; text-decoration: line-through;">${p.getFormattedPrice(1.1)}</td>
                                <td>${p.getFormattedPrice(1)}</td>
                                <td>
                                    <a class="btn-edit btn btn-warning" href="updateProducts?id=${p.id}">
                                        <i class="fa fa-pencil-alt"></i> Edit
                                    </a>
                                    <a class="btn-dle btn btn-danger" href="#" onclick="HandleDelete('${p.id}', '${p.name}')">
                                        <i class="fa fa-trash"></i> Delete
                                    </a>
                                </td>
                            </tr>
                        </c:forEach>

                    </tbody>

                </table>

                <c:if test="${not empty requestScope.error}">
                    <p class="text-center"  style="color: red" id="error-message">
                        ${requestScope.error}
                    </p>
                </c:if>
                <!-- Pagination -->
                <c:if test="${requestScope.numPages > 1}">
                    <div class="pagination">
                        <c:forEach begin="1" end="${requestScope.numPages}" var="i">
                            <c:url value="/managerProduct" var="url">
                                <c:param name="page" value="${i}"/>
                            </c:url>
                            <c:choose>
                                <c:when test="${i eq requestScope.currentPage}">
                                    <a href="${url}" class="active">${i}</a>
                                </c:when>
                                <c:otherwise>
                                    <a href="${url}" >${i}</a>
                                </c:otherwise>
                            </c:choose>
                        </c:forEach>
                    </div>
                </c:if>

            </div>
        </div>

        <script>
            function HandleDelete(id, name) {
                if (confirm("Do you want to delete Product: " + name + "?")) {
                    window.location = "deleteProducts?id=" + id + "&name=" + encodeURIComponent(name);
                }
            }
        </script>
        <br>
        <%-- Include footer --%>
        <%@include file="/Views/footer.jsp" %>

    </body>
</html>
