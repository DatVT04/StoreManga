
<%@taglib  prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Đăng nhập</title>


        <link rel="stylesheet" href="CSS/style_toast.css"/>
        <link rel="stylesheet" href="CSS/style_log.css"/>
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.1/css/all.min.css"/>

    </head>


    <body >
        <style>
            #togglePassword {
                font-size: 18px;

            }
            .login-wrap {
                background-image: url('Images/zoroLogin.gif'); /* Đường dẫn ảnh nền */
                background-size: cover;
                background-position: center;
                background-repeat: no-repeat;
                position: relative;
            }

            .login-wrap::before {
                content: "";
                position: absolute;
                top: 0;
                left: 0;
                right: 0;
                bottom: 0;
                background-image: url("Images/zoroLogin.gif"); /* Đường dẫn đến ảnh background */
                background-size: cover;
                opacity: 0.7;
                z-index: -1; /* Đặt lớp phủ phía sau nội dung */
                pointer-events: none; /* Cho phép các tương tác trên phần tử form bên trên */
            }


            .login-form {
                position: relative;
                z-index: 2; /* Giữ nội dung form ở phía trên lớp phủ */
                color: white; /* Đổi màu chữ nếu cần */
            }

        </style>


        <div id="toast"></div>
        <div class="login-wrap">

            <div class="login-html">
                <a  class="link-imglogin"href="home">
                    <img src="Images/logo_rmbackground.png" alt="Home" width="30px" height="30px">
                </a>
                <input id="tab-1" type="radio" name="tab" class="sign-in" checked><label for="tab-1" class="tab">Sign In</label>
                <input id="tab-2" type="radio" name="tab" class="sign-up"><label for="tab-2" class="tab">Sign Up</label>
                <div class="login-form">
                    <div class="sign-in-htm">


                        <form action="login" method="post">
                            <div class="group">
                                <label for="user" class="label">Username</label>
                                <input id="user" type="text" class="input" name="user" required>
                            </div>
                            <div class="group">
                                <label for="pass" class="label">Password</label>
                                <input id="pass" type="password" class="input" name="pass" required>
                                <br>
                                <!--                                <span id="togglePassword" class="icon" onclick="togglePasswordVisibility()"><i class="fas fa-eye"></i></span>-->
                            </div>
                            <div class="group">
                                <input id="check" type="checkbox" class="check" name="remember">
                                <label for="check"><span class="icon"></span> Remember me</label>
                            </div>
                            <div class="group">
                                <input type="submit" class="button"  value="Sign In">
                            </div>

                            <a href="https://accounts.google.com/o/oauth2/auth?scope=email profile openid

                               &redirect_uri=http://localhost:9999/StoreManga/login

                               &response_type=code

                               &client_id=105339922938-smsrn8ojb134bktdrghqqtl90vpdsdqu.apps.googleusercontent.com

                               &approval_prompt=force" class="btn btn-lg btn-danger">
                                <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-google" viewBox="0 0 16 16">
                                <path d="M15.545 6.558a9.42 9.42 0 0 1 .139 1.626c0 2.434-.87 4.492-2.384 5.885h.002C11.978 15.292 10.158 16 8 16A8 8 0 1 1 8 0a7.689 7.689 0 0 1 5.352 2.082l-2.284 2.284A4.347 4.347 0 0 0 8 3.166c-2.087 0-3.86 1.408-4.492 3.304a4.792 4.792 0 0 0 0 3.063h.003c.635 1.893 2.405 3.301 4.492 3.301 1.078 0 2.004-.276 2.722-.764h-.003a3.702 3.702 0 0 0 1.599-2.431H8v-3.08h7.545z" />
                                </svg>
                                <span class="ms-2 fs-6">Sign in with Google</span>
                            </a>
                            <!--                            <button type="button" class="button-back" onclick="window.location.href = 'home'">Back to Home</button>-->
                            <div class="hr"></div>
                            <div class="foot-lnk">
                                <a href="#forgot">Forgot Password?</a>
                            </div>

                        </form>
                    </div>

                    <div class="sign-up-htm">
                        <form action="signup" method="post"> 
                            <div class="group">
                                <label for="signup-user" class="label">Username</label>
                                <input id="signup-user" type="text" class="input" name="signup-user" required>
                            </div>
                            <div class="group">
                                <label for="signup-pass" class="label">Password</label>
                                <input id="signup-pass" type="password" class="input" name="signup-pass" data-type="password" required>
                            </div>
                            <div class="group">
                                <label for="repeat-pass" class="label">Repeat Password</label>
                                <input id="repeat-pass" type="password" class="input" name="repeat-pass" data-type="password" required>
                            </div>
                            <div class="group">
                                <label for="email" class="label">Email Address</label>
                                <input id="email" type="email" class="input" name="email" required>
                            </div>
                            <div class="group">
                                <input type="submit" class="button" value="Sign Up">
                            </div>
                            <!--                            <button type="button" class="button-back" onclick="window.location.href = 'home'">Back to Home</button>-->
                        </form>
                        <div class="hr"></div>
                        <div class="foot-lnk">
                            <label for="tab-1">Already a member? <a href="login">Sign In</a></label>
                        </div>


                    </div>

                </div>
            </div>

            <script src="JS/toast_msg.js"></script>


            <script>
                function showErrorToast(msg) {
                    toast({
                        title: "Thất bại!",
                        message: msg,
                        type: "error",
                        duration: 5000
                    });
                }

                function showSuccessToast(msg) {
                    toast({
                        title: "Thành công!",
                        message: msg,
                        type: "success",
                        duration: 5000
                    });
                }
            </script>
            <script>
                function togglePasswordVisibility() {
                    var passwordInput = document.getElementById('pass');
                    var toggleIcon = document.getElementById('togglePassword').querySelector('i');

                    if (passwordInput.type === 'password') {
                        passwordInput.type = 'text';
                        toggleIcon.classList.remove('fa-eye');
                        toggleIcon.classList.add('fa-eye-slash');
                    } else {
                        passwordInput.type = 'password';
                        toggleIcon.classList.remove('fa-eye-slash');
                        toggleIcon.classList.add('fa-eye');
                    }
                }

            </script>


            <c:if test="${ requestScope.error1 != null}">
                <script>
                    showErrorToast("${requestScope.error1}");
                </script>

            </c:if>
            <c:if test="${requestScope.error2 != null}">
                <script>
                    showErrorToast("${requestScope.error2}");
                </script>

            </c:if>
            <c:if test="${requestScope.success != null}">
                <script>
                    showSuccessToast("${requestScope.success}");
                </script>

            </c:if>
    </body>
</html>
