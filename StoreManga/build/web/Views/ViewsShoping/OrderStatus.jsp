<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Trạng thái đơn hàng</title>
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
        <link rel="stylesheet" href="styles.css">
    </head>
    <body>
        <%-- Include header --%>
        <%@include file="/Views/header.jsp" %>

        <div class="container mt-5">
            <c:if test="${not empty requestScope.name}">
                <div class="jumbotron">
                    <div class="row align-items-center">
                        <!-- Cột nội dung bên trái -->
                        <div class="col-md-8">
                            <h4 class="display-6">Xin chào: ${requestScope.name}</h4>
                            <h1 class="display-4">Cảm ơn bạn đã mua hàng!</h1>
                            <p class="lead">Đơn hàng của bạn đã được đặt thành công!</p>
                            <hr class="my-4">
                            <p>Chúng tôi sẽ xử lý đơn đặt hàng của bạn trong thời gian ngắn.</p>
                            <a class="btn btn-primary btn-lg" href="home" role="button">Tiếp tục mua hàng</a>
                        </div>

                        <!-- Cột hình ảnh bên phải -->
                        <div class="col-md-4 text-center">
                            <img src="Images/thankyou.gif" alt="Thank You" class="img-fluid" style="max-width: 100%; height: auto;">
                        </div>
                    </div>
                </div>

            </c:if>

            <c:if test="${not empty requestScope.list}">
                <table class="table">
                    <thead>
                        <tr>
                            <th>Mã đơn hàng</th>
                            <th>Ngày Order</th>
                            <th>Total Money</th>
                            <th>Trạng thái</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach items="${requestScope.list}" var="order">
                            <tr>
                                <td>${order.orderID}</td>
                                <td>${order.getDate()}</td>
                                <td>${order.getFormattedPrice()}</td>
                                <td>
                                    <c:choose>
                                        <c:when test="${order.status}">
                                            <span class="text-success">Đã xác nhận </span>
                                        </c:when>
                                        <c:otherwise>
                                            <span class="text-danger">Đang chờ xác nhận</span>
                                        </c:otherwise>
                                    </c:choose>
                                </td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
            </c:if>
            <p class="text-center text-danger">${requestScope.error}</p>
        </div>

        <%-- Include footer --%>
        <%@include file="/Views/footer.jsp" %>

        <script src="JS/app.js"></script>
        <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.5.4/dist/umd/popper.min.js"></script>
        <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
    </body>
</html>
