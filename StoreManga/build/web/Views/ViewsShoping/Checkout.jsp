<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Checkout</title>
        
        <link href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet">
    </head>
    <body>
        <%@include file="/Views/header.jsp" %>
        <script>
     var error = "${requestScope.error}";
     if (error) {
         alert(error);
         window.location.href = "viewCart";
     }
        </script>
        <c:set var="c" value="${requestScope.dataProfile}"></c:set>
            <div class="container">
                <div class="row">

                    <div class="col-md-6">
                        <h4 class="text-center">Thông tin nhận hàng</h4>
                        <form action="checkout" method="post">
                            <div class="form-group">
                                <label for="name">Tên:</label>
                                <input type="text" class="form-control"  name="name"  value=" ${c.firstName} ${c.lastName} " required>
                        </div>
                        <div class="form-group">
                            <label for="address">Địa chỉ:</label>
                            <input type="text" class="form-control"  name="address" value="${c.adress}" required>
                        </div>
                        <div class="form-group">
                            <label for="phone">Số điện thoại:</label>
                            <input type="text" class="form-control"  name="phone"  value="${c.phone}"required>
                        </div>
                        <div class="form-group">
                            <label for="phone">Email:</label>
                            <input type="email" class="form-control" name="email"  value="${c.getUser().email}"required>
                        </div>
                        <p style="color:  red; font-style: italic">(*)Thanh toán khi nhận hàng</p>
                        <button type="submit" class="btn-secondary ">Xác nhận đơn hàng</button>



                </div>

                <div class="col-md-6">
                    <h4 class="text-center">Danh sách sản phẩm thanh toán</h4>
                    <table class="table">
                        <thead>
                            <tr>
                                <th>Ảnh</th>
                                <th>Tên sản phẩm</th>
                                <th>Số lượng</th>
                                <th>Giá</th>
                            </tr>
                        </thead>
                        <tbody>

                            <c:forEach items="${requestScope.cartList}" var="o">
                                <tr>
                                    <td><img src="${o.getProduct().getImage() }" alt="product" style="width: 50px;"></td>
                                    <td>${o.getProduct().getName()}</td>
                                    <td>${o.quantity}</td>
                                    <td>${o.getProduct().getFormattedPrice(1)}</td>


                                </tr>
                            </c:forEach>

                        </tbody>


                    </table>
                    </form>
                    <div class="container">
                        <c:if test="${not empty requestScope.error}">
                            <center  style="color: red" id="error-message">
                                ${requestScope.error}
                            </center>
                        </c:if>
                        <c:if test="${not empty requestScope.total}">
                            <p style="color: red"class="text-center">
                                Tổng tiền: ${total}</p> </c:if>

                        </div>

                    </div>

                </div><br>
<!--                <div> 
                    <button type="button" class="btn btn-primary" onclick="window.location.href = 'viewCart'">Trở lại</button>
                </div>-->
                <br>

            </div>
        <%-- Include footer --%>
        <%@include file="/Views/footer.jsp" %>
        <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.5.4/dist/umd/popper.min.js"></script>
        <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
    </body>
</html>
