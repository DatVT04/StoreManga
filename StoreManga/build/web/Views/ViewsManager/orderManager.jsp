<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Order</title>

        <link href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet">
        <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.4/css/all.min.css" rel="stylesheet">
    </head>
    <body>
        <%@include file="/Views/headerAdmin.jsp"%>
        <script>
            var error = "${requestScope.error}";
            if (error) {
            alert(error);
            window.location.href = "order";
            }
        </script>

        <div class="container">

            <h3 style="text-align: center">DANH SÁCH ĐƠN HÀNG</h3>
            <table class="table mt-4">

                <thead class="thead-dark">
                    <tr>
                        <th scope="col">#</th>
                        <th scope="col">Order ID</th>
                        <th scope="col">Account</th>
                        <th scope="col">Date</th>
                        <th scope="col">Total Amount</th>
                        <th scope="col">Status</th>
                        <th scope="col">Actions</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach  items="${requestScope.list}" var="order" varStatus="loop">
                        <tr>
                            <th scope="row">${loop.index + 1}</th>                           
                            <td>${order.getOrderID()}</td>
                            <td>${order.getUser().getUsername()}</td>
                            <td>${order.getDate()}</td>
                            <td>${order.getFormattedPrice()}</td>
                            <td>
                                <span class="badge badge-${order.status == 'Not Confirmed' ? 'warning':'success' }">${order.status == '' ? 'Not Confirmed' : 'Confirmed'}</span>

                            </td>

                            <td>
                                <a href="#" class="btn btn-sm btn-primary"><i class="fas fa-eye"></i> View</a>
                                <c:if test="${order.status == 'Confirmed'}" >
                                    <a  id="${order.getOrderID()}" href="updateOrder?id=${order.getOrderID()}"  class="btn btn-sm btn-success"><i class="fas fa-check-circle"></i> Confirm</a>
                                </c:if>
                                <a href="deleteOrder?id=${order.getOrderID()}" class="btn btn-sm btn-danger"><i class="fas fa-trash"></i> Delete</a>
                            </td>
                        </tr>
                    </c:forEach>
                </tbody>

            </table>
            <p class="text-center" style="color: red">${requestScope.error1}</p>
        </div>


        <%-- Include footer --%>
        <%@include file="/Views/footer.jsp" %>
        <!-- Bootstrap JS -->
        <script src="https://code.jquery.com/




