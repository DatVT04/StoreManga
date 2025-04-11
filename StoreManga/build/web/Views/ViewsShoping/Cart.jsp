<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Giỏ Hàng</title>
        
        <link href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet">
    </head>
    <body>
        <%@include file="/Views/header.jsp" %>


        <div class="container">
            <form action="checkout" method="get">
                <table class="table table-bordered">
                    <thead class="thead-dark">
                        <tr>
                            <th scope="col">Select</th>
                            <th scope="col">Images</th>
                            <th scope="col">Name</th>
                            <th scope="col">Quantity</th>
                            <th scope="col">Unit Price</th>
                            <th scope="col">Option</th>

                        </tr>
                    </thead>
                    <tbody   
                        <c:forEach items="${requestScope.carts}" var="c" >
                            <tr>
                        <td class="align-middle text-center" >
                            <input style="transform: scale(1.5);" type="checkbox" value="${c.cardId}" name="checkboxName"  >
                        </td>
                        <td><img src="${c.getProduct().getImage() }" alt="img" class="img-thumbnail" style="max-width: 100px;"></td>
                        <td>${c.getProduct().getName()}</td>
                        <td>
                            <button class="btn btn-sm btn-success increase">+</button>
                            <span class="quantity">${c.quantity}</span>
                            <button class="btn btn-sm btn-danger decrease">-</button>


                        </td>
                        <td>
                            <span class="unit-price" style="display: none;">${c.getProduct().getPrice()}</span>
                            ${c.getProduct().getFormattedPrice(1)}
                        </td>

                        <td>
                            <a href="deleteCart?id=${c.cardId}" class="btn btn-danger btn-sm">Delete</a>
                        </td>
                        </tr>
                    </c:forEach>
                    </tbody>

                </table>


                <p class="text-center" style="color: red">${requestScope.error}</p>
                <a href="home" class="btn btn-primary">
                    <i class="fas fa-shopping-cart"></i> Tiếp tục mua sắm
                </a>
                <button type="submit" class="btn btn-primary" style="float: right">
                    <i class="fas fa-shopping-bag"></i> Thanh toán
                </button>

            </form>
        </div>


        <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.5.4/dist/umd/popper.min.js"></script>
        <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
        <script>
            document.getElementById("checkoutButton").onclick = function () {
                window.location.href = "checkout";
            };

            $(document).ready(function () {
                $(".increase").click(function () {
                    var quantitySpan = $(this).parent().find(".quantity");
                    var quantity = parseInt(quantitySpan.text());
                    quantity++;
                    quantitySpan.text(quantity);
                });

                $(".decrease").click(function () {
                    var quantitySpan = $(this).parent().find(".quantity");
                    var quantity = parseInt(quantitySpan.text());
                    if (quantity > 0) {
                        quantity--;
                        quantitySpan.text(quantity);
                    }
                });
            });
        </script>
        <br>
        <%-- Include footer --%>
        <%@include file="/Views/footer.jsp" %>
    </body>
</html>
