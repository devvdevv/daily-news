<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ include file="/common/taglib.jsp"%>
<!DOCTYPE html>
<html>

<head>
    <meta charset="ISO-8859-1">
    <title>Login</title>

</head>

<body>
    <div class="container-login100"
        style="background-image: url(<c:url value='/style/login/images/background.jpg' />);">
        <div class="wrap-login100 p-l-55 p-r-55 p-t-80 p-b-30">
            <form class="login100-form validate-form" id="formLogin" method="POST">
                <span class="login100-form-title p-b-37">
                    Welcome back!
                </span>

                <div class="wrap-input100 validate-input m-b-20" data-validate="Enter username or email">
                    <input class="input100" type="text" name="username" placeholder="username">
                    <span class="focus-input100"></span>
                </div>

                <div class="wrap-input100 validate-input m-b-25" data-validate="Enter password">
                    <input class="input100" type="password" name="password" placeholder="password">
                    <span class="focus-input100"></span>
                </div>

                <c:if test="${not empty message}">
                    <div class="alert alert-danger" role="alert">
                        <p style="color: red;">${message}</p>
                    </div>
                </c:if>

                <div class="container-login100-form-btn">
                    <button type="submit" class="login100-form-btn">
                        Sign In
                    </button>
                </div>

                <!-- <div class="text-center">
                    <a href="#" class="txt2 hov1">
                        Sign Up
                    </a>
                </div> -->
            </form>


        </div>
    </div>

    <div id="dropDownSelect1"></div>
</body>

</html>