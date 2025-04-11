<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Dashboard</title>

        <!-- jQuery -->
        <script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>

        <!-- Bootstrap CSS -->
        <link href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" rel="stylesheet">
        <!-- Font Awesome CSS -->
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css">

        <style>
            .icon {
                font-size: 1.6rem;
                margin-right: 10px;
            }
            .icon-user {
                color: #007bff;
            }
            .icon-products {
                color: #28a745;
            }
            .icon-cart {
                color: #dc3545;
            }
            .icon-revenue {
                color: #ffc107;
            }
        </style>
    </head>
    <body>
        <%@include file="/Views/headerAdmin.jsp" %>
        <div class="container mt-4">
            <h1>Dashboard</h1>
            <div class="mt-2">
                <table class="table">
                    <tr>
                        <td><i class="fas fa-user icon icon-user"></i> Total Users:</td>
                        <td><c:out value="${user}" default="0"/></td>
                    </tr>
                    <tr>
                        <td colspan="2"><canvas id="userChart" width="400" height="200"></canvas></td>
                    </tr>
                    <tr>
                        <td><i class="fas fa-shopping-cart icon icon-products"></i> Total Products:</td>
                        <td><c:out value="${product}"/></td>
                    </tr>
                    <tr>
                        <td colspan="2"><canvas id="productChart" width="400" height="200"></canvas></td>
                    </tr>

                    <tr>
                        <td><i class="fas fa-cart-plus icon icon-cart"></i> Total Items in Cart:</td>
                        <td><c:out value="${cart}"/></td>
                    </tr>
                    <tr>
                        <td colspan="2"><canvas id="cartChart" width="400" height="200"></canvas></td>
                    </tr>

                    <tr>
                        <td><i class="fas fa-dollar-sign icon icon-revenue"></i> Estimated total revenue:</td>
                        <td><c:out value="${money}"/></td>
                    </tr>
                    <tr>
                        <td colspan="2"><canvas id="revenueChart" width="400" height="200"></canvas></td>
                    </tr>
                </table>
            </div>
        </div>

        <!-- Tải Bootstrap JS sau khi jQuery -->
        <script src="https://cdn.jsdelivr.net/npm/chart.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.bundle.min.js"></script>

        <!-- Script Chart.js và các biểu đồ của bạn -->

        <script>
            // Dữ liệu mẫu cho từng năm (thay đổi thành dữ liệu từ backend)
            const years = ['2019', '2020', '2021', '2022', '2023'];
            const userData = [120, 150, 170, 200, 220]; // Số lượng người dùng mỗi năm
            const productData = [300, 320, 340, 360, 380]; // Số lượng sản phẩm mỗi năm
            const cartData = [60, 70, 75, 85, 90]; // Số lượng mặt hàng trong giỏ mỗi năm
            const revenueData = [15000, 20000, 25000, 30000, 35000]; // Doanh thu mỗi năm

            // Biểu đồ số người dùng theo năm
            const ctxUser = document.getElementById('userChart').getContext('2d');
            new Chart(ctxUser, {
                type: 'bar',
                data: {
                    labels: years,
                    datasets: [{
                            label: 'Users',
                            data: userData,
                            backgroundColor: 'rgba(54, 162, 235, 0.5)',
                            borderColor: 'rgba(54, 162, 235, 1)',
                            borderWidth: 1
                        }]
                },
                options: {scales: {y: {beginAtZero: true}}}
            });

            // Biểu đồ số sản phẩm theo năm
            const ctxProduct = document.getElementById('productChart').getContext('2d');
            new Chart(ctxProduct, {
                type: 'bar',
                data: {
                    labels: years,
                    datasets: [{
                            label: 'Products',
                            data: productData,
                            backgroundColor: 'rgba(75, 192, 192, 0.5)',
                            borderColor: 'rgba(75, 192, 192, 1)',
                            borderWidth: 1
                        }]
                },
                options: {scales: {y: {beginAtZero: true}}}
            });

            // Biểu đồ số mặt hàng trong giỏ theo năm
            const ctxCart = document.getElementById('cartChart').getContext('2d');
            new Chart(ctxCart, {
                type: 'bar',
                data: {
                    labels: years,
                    datasets: [{
                            label: 'Items in Cart',
                            data: cartData,
                            backgroundColor: 'rgba(255, 99, 132, 0.5)',
                            borderColor: 'rgba(255, 99, 132, 1)',
                            borderWidth: 1
                        }]
                },
                options: {scales: {y: {beginAtZero: true}}}
            });

            // Biểu đồ doanh thu ước tính theo năm
            const ctxRevenue = document.getElementById('revenueChart').getContext('2d');
            new Chart(ctxRevenue, {
                type: 'bar',
                data: {
                    labels: years,
                    datasets: [{
                            label: 'Revenue',
                            data: revenueData,
                            backgroundColor: 'rgba(255, 206, 86, 0.5)',
                            borderColor: 'rgba(255, 206, 86, 1)',
                            borderWidth: 1
                        }]
                },
                options: {scales: {y: {beginAtZero: true}}}
            });
        </script>

    </body>
</html>
