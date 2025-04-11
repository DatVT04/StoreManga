<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Mua Sản Phẩm</title>
    <link rel="stylesheet" href="CSS/style_buy.css"/>
    <link rel="stylesheet" href="CSS/style_log.css"/>
    
</head>
<body>
<%@include file="/Views/header.jsp" %>

<div class="container">
    <c:if test="${not empty requestScope.error}">
        <center  style="color: red" id="error-message">
            ${requestScope.error}
        </center>
    </c:if>
    <c:set var="product" value="${requestScope.products}" />
    <div class="product-info">
        <div class="product-image">
            <img src="${product.image}" alt="Product Image">
        </div>
        <div class="product-details">
            <h4 class="product-name">${product.name}</h4>
            <p class="product-description">Mô tả: ${product.describe}</p>
            <p class="product-price" style="text-decoration: line-through; color: red; font-weight: bold;">Giá gốc: ${product.getFormattedPrice(1.1)}</p>
            <p class="product-price" style="font-weight: bold;">Giá bán: ${product.getFormattedPrice(1)}</p>
            <form action="addCart" method="post" class="buy-form">
                <table> 
                    <c:if test="${requestScope.stock > 0}">
                        <tr> 
                            <p>Số lượng hiện tại: ${requestScope.stock}</p>
                            <td>
                                <input type="number" id="quantity" name="quantity" min="1" max="${requestScope.stock}" required >
                            </td>
                        </tr>
                        <tr>
                            <td colspan="2">
                                <input type="submit" value="Thêm vào giỏ hàng" class="btn-buy">
                            </td>
                        </tr>
                    </c:if>
                    <c:if test="${requestScope.stock == 0}">
                        <tr>
                            <td colspan="2">
                                <p style="color: red; font-style: italic">Hết hàng </p>
                            </td>
                        </tr>
                    </c:if>
                </table>
                <input type="hidden" name="productId" value="${product.id}">
                <input type="hidden" name="price" value="${product.price}">
            </form>
        </div>
    </div>
    <button type="button" class="button-back" onclick="window.location.href = 'home'">Trở lại</button>
</div>
<br>
<%@include file="/Views/footer.jsp" %>
</body>
</html>
