<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Quản lý sản phẩm</title>

        <link href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet">
        <link href="https://fonts.googleapis.com/css2?family=Merriweather&display=swap" rel="stylesheet">
        <link rel="stylesheet" href="CSS/style_header.css"/>
        <link rel="stylesheet" href="CSS/style_gif.css"/>
        <style>

            .content {
                display: none;
            }
            iframe {
                display: block;

            }
            .ani-fire {
                background-image: url(https://media.licdn.com/dms/image/D4D22AQHYReUG4Q8LFg/feedshare-shrink_2048_1536/0/1709545843533?e=2147483647&v=beta&t=Td7oTKvBOGiBvjBT7gxw3pdwfsHs9yJOtXWSmiUa0IM);
                background-position: 0 -1000px;
                -webkit-background-clip: text;
                -webkit-text-fill-color: transparent;
                animation: fire 4s linear infinite;
            }


        </style>
    </head>
    <body>
        <header class="sticky-top">
    <nav class="navbar navbar-expand-lg navbar-light bg-light">
        <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="navbarSupportedContent">
            <ul class="navbar-nav mx-auto"> <!-- Sử dụng mx-auto để căn giữa -->
                <li class="nav-item">
                    <a class="nav-link" href="home">Home</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="dashBroad">Thống kê</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="account">Tài khoản</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="managerProduct">Truyện</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="category">Thể loại</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="order">Đơn hàng</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="status">Trạng thái đơn hàng</a>
                </li>
            </ul>
        </div>
    </nav>
</header>


    </body>
</html>
