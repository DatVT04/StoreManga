<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib  prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

        <link href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet">
        <link rel="stylesheet" href="CSS/style_home.css"/>
        <link rel="stylesheet" href="CSS/style_gif.css"/>

    </head>
    <body>

        <!--        <script>
                    var error = "${requestScope.errorf}";
                    if (error) {
                        alert(error);
                        window.location.href = "home";
                    }
                </script>-->

        <style>
            .navbar-nav .nav-item .nav-link .far.fa-heart  {
                color: red;
                margin-right: 5px;
            }


            .navbar-nav .nav-item .nav-link .fas.fa-shopping-cart {
                color: #007bff;
                margin-right: 5px;
            }
            .blue {
                background-image: url(https://img.wattpad.com/7441d82c9a6e245917b42ba2db2b336f3220e322/68747470733a2f2f73332e616d617a6f6e6177732e636f6d2f776174747061642d6d656469612d736572766963652f53746f7279496d6167652f567a6930516f6b38443935484e673d3d2d313238343238343735322e313738383265663935303066343031323239343036333032353336332e676966);
                background-position: 0 -1000px;
                -webkit-background-clip: text;
                -webkit-text-fill-color: transparent;
                animation: fire 4s linear infinite;
            }
        </style>

        <%-- Include header --%>
        <%@include file="header.jsp" %>


        <%-- Display Slider --%>
        <div id="anhbia" class="carousel slide" data-ride="carousel">
            <div class="carousel-inner">
                <div class="carousel-item active">
                    <img class="d-block w-100" src="Images/slider/banner-fix1.jpg" alt="First slide">
                </div>
                <div class="carousel-item">
                    <img class="d-block w-100" src="Images/slider/banner-fix2.jpg" alt="Second slide">
                </div>
                <div class="carousel-item">
                    <img class="d-block w-100" src="Images/slider/banner-fix3.jpg" alt="Third slide">
                </div>
                <div class="carousel-item">
                    <img class="d-block w-100" src="Images/slider/banner-fix5.jpg" alt="Fourth slide">
                </div>
                <div class="carousel-item">
                    <img class="d-block w-100" src="Images/slider/banner-fix6.jpg" alt="Fiveth slide">
                </div>

                <div class="carousel-item">
                    <img class="d-block w-100" src="Images/slider/banner-fix7.jpg" alt="Sixnth slide">
                </div>
                <div class="carousel-item">
                    <img class="d-block w-100" src="Images/slider/banner-fix8.jpg" alt="Seventh slide">
                </div>

            </div>
        </div>
        <a class="carousel-control-prev" href="#anhbia" role="button" data-slide="prev">
            <span class="carousel-control-prev-icon" aria-hidden="true"></span>
            <span class="sr-only">Previous</span>
        </a>
        <a class="carousel-control-next" href="#anhbia" role="button" data-slide="next">
            <span class="carousel-control-next-icon" aria-hidden="true"></span>
            <span class="sr-only">Next</span>
        </a>
    </div>

    <script src="JS/app.js"></script>

    <br>

    <!-- Manga Section -->
    <section class="py-5">
        <div class="container">
            <h2 class="text-center mb-4">Truyện Nổi Bật</h2>
            <div class="row">
                <c:forEach items="${requestScope.home}" var="p">
                    <div class="col-lg-3 col-md-4 col-sm-6 mb-4">
                        <div class="card h-100" style="position: relative;">
                            <img src="${p.image}" class="card-img-top" alt="${p.name}" style="width: 100%; height: 420px; object-fit: cover;" />
                            <div class="card-body">
                                <h5 class="card-title">${p.name}</h5>
                                <p class="card-text">${p.describe}</p>

                            </div>
                        </div>
                    </div>
                </c:forEach>
            </div>
        </div>
    </section>

    <!-- Filter -->
    <div class="container mt-4">
        <div class="row">
            <!-- Filter Section -->
            <div class="col-md-3">
                <form action="filter1" id="f1" class="form-inline">
                    <!-- Add filter options here -->
                    <div class="form-group fix-colum mr-3">
                        <label for="sortOption" class="mr-2">Sắp xếp theo giá:</label>
                        <select id="sortOption" name="sortOption" class="form-control" onchange="changef()">
                            <option value="invalid">Chọn thứ tự sắp xếp</option>
                            <option value="ascending">Thấp đến cao</option>
                            <option value="descending">Cao đến thấp</option>
                        </select>
                    </div>
                </form>
                <form action="filter2" method="GET" class="form-inline mt-3" onsubmit="return validateFilter()">
                    <!-- Add search form here -->
                    <div class="fix-colum form-group mr-3 " id="key3" >
                        <label class="mr-2">Khoảng giá:</label>
                        <label class="mr-2"><input type="radio" name="priceRange" value="1"> 10,000₫ - 30,000₫</label>
                        <label class="mr-2"><input type="radio" name="priceRange" value="2"> 30,000₫ - 50,000₫</label>
                        <label class="mr-2"><input type="radio" name="priceRange" value="3"> 50,000₫ - 100,000₫</label>
                        <label class="mr-2"><input type="radio" name="priceRange" value="4"> 100,000₫ - 300,000₫</label>
                    </div>
                    <button type="submit" class="btn btn-primary" >Tìm kiếm</button>
                </form>
            </div>
            <!-- Product Display Section -->
            <div class="col-md-9">
                <div id="wrapper">
                    <div id="content-wrapper">
                        <h3  style="text-align: center">CÁC DÒNG TRUYỆN </h3>
                        <!-- Add product display here -->
                        <c:if test="${not empty requestScope.count}">
                            <p class="text-center" style="color: red">Đã tìm thấy ${requestScope.count} sản phẩm</p>
                        </c:if>
                        <div class="row">
                            <c:forEach items="${requestScope.data2}" var="p" varStatus="loop">
                                <div class="col-md-3">
                                    <!-- Mã HTML để hiển thị sản phẩm -->
                                    <div class="phone-item">
                                        <div class="icon-container">
                                            <a style="text-decoration: none;"href="addCart?id=${p.id}">
                                                <img src="${p.image}" alt="img"/>
                                                <p>${p.name}</p>
                                                <p style="color: red">Giá gốc: <span class="old" style="text-decoration: line-through;">${p.getFormattedPrice(1.1)}</span></p>
                                                <p class="blue">Giá bán: ${p.getFormattedPrice(1)}</p>
                                            </a>
                                            <button class="shopping-cart-btn" onclick="window.location.href = 'addCart?id=${p.id}'">
                                                <i class="fas fa-shopping-cart"> Buy Now</i>
                                            </button>

                                            
                                        </div>
                                    </div>
                                </div>

                            </c:forEach>
                        </div>
                        <p style="color: red; text-align: center;">${requestScope.errorMessage}</p>
                        <!-- Pagination -->
                        <c:if test="${requestScope.numPages > 1}">
                            <div class="pagination">
                                <c:forEach begin="1" end="${requestScope.numPages}" var="i">
                                    <c:url value="/home" var="url">
                                        <c:param name="page" value="${i}"/>
                                    </c:url>
                                    <c:choose>
                                        <c:when test="${i eq requestScope.currentPage}">
                                            <a href="${url}" class="active">${i}</a>
                                        </c:when>
                                        <c:otherwise>
                                            <a href="${url}">${i}</a>
                                        </c:otherwise>
                                    </c:choose>
                                </c:forEach>
                            </div>
                        </c:if>
                    </div>
                </div>
            </div>
        </div>
    </div>

    <!-- Nhân vật nổi bật -->
    <section class="py-5">
        <div class="container">
            <h2 class="text-center mb-4">Nhân Vật Truyện HOT</h2>
            <div id="mangaCarousel" class="carousel slide" data-bs-ride="carousel">
                <div class="carousel-inner">
                    <!-- Manga 1 -->
                    <div class="carousel-item active">
                        <div class="card h-100">
                            <img src="Images/gif1.gif" class="card-img-top" alt="OnePiece" style="width: 100%; height: 500px; object-fit: cover;" />

                        </div>
                    </div>
                    <!-- Manga 2 -->
                    <div class="carousel-item">
                        <div class="card h-100">
                            <img src="Images/gif2.gif" class="card-img-top" alt="Kimetsu no Yaiba" style="width: 100%; height: 500px; object-fit: cover;" />

                        </div>
                    </div>
                    <!-- Manga 3 -->
                    <div class="carousel-item">
                        <div class="card h-100">
                            <img src="Images/gif3.gif" class="card-img-top" alt="Naruto" style="width: 100%; height: 500px; object-fit: cover;" />

                        </div>
                    </div>
                    <!-- Manga 4 -->
                    <div class="carousel-item">
                        <div class="card h-100">
                            <img src="Images/gif4.gif" class="card-img-top" alt="7 viên ngọc rồng" style="width: 100%; height: 500px; object-fit: cover;" />

                        </div>
                    </div>
                </div>

            </div>
        </div>
    </section>


    <br>
    <%-- Include footer --%>
    <%@include file="footer.jsp" %>

    <script>


        function changef() {
            var sortOption = document.getElementById("sortOption").value;
            if (sortOption !== "invalid") {
                document.getElementById("f1").submit();
            }
        }


        var slideIndex = 0;
        function showSlides() {
            var i;
            var slides = document.getElementsByClassName("slides");
            for (i = 0; i < slides.length; i++) {
                slides[i].style.display = "none";
            }
            slideIndex++;
            if (slideIndex > slides.length) {
                slideIndex = 1;
            }
            slides[slideIndex - 1].style.display = "block";
            setTimeout(showSlides, 5000);
        }

        showSlides();


        function validateFilter() {
            var selectedPriceRange = document.querySelector('input[name="priceRange"]:checked');
            if (!selectedPriceRange) {
                alert("Vui lòng chọn khoảng giá tiền trước khi tìm kiếm.");
                return false;
            }

            return true;
        }

    </script>


    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</body>
</html>
