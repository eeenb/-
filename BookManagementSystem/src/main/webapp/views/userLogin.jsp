<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <title>用户登录</title>
    <link rel="stylesheet" href="https://cdn.staticfile.org/twitter-bootstrap/4.3.1/css/bootstrap.min.css">
    <style>
        body {
            padding-top: 20px;
            background: #f7f7f7;
        }
        .login-container {
            max-width: 400px; /* 增加最大宽度 */
            padding: 15px;
            margin: auto;
        }
        .form-signin {
            background: #fff;
            padding: 30px; /* 增加内边距 */
            border-radius: 15px; /* 增大圆角 */
            box-shadow: 0 4px 8px rgba(0, 0, 0, 0.15); /* 增强阴影 */
        }
        .form-signin .form-control {
            position: relative;
            box-sizing: border-box;
            height: auto;
            padding: 10px;
            font-size: 16px;
        }
        .form-signin .form-control:focus {
            z-index: 2;
        }
        .form-signin input[type="text"], .form-signin input[type="password"] {
            margin-bottom: 15px; /* 增加输入框间距 */
        }
        .form-signin-heading {
            text-align: center; /* 居中标题 */
            margin-bottom: 20px; /* 增加标题和输入框间的距离 */
        }
    </style>
</head>
<body>
    <div class="container">
        <div class="login-container">
            <form class="form-signin" action="<%= request.getContextPath() %>/loginController" method="post">
                <h2 class="form-signin-heading">用户登录</h2>
                <label for="inputUsername" class="sr-only">用户名</label>
                <input type="text" id="inputUsername" class="form-control" placeholder="用户名" required autofocus name="username">
                <label for="inputPassword" class="sr-only">密码</label>
                <input type="password" id="inputPassword" class="form-control" placeholder="密码" required name="password">
                <button class="btn btn-lg btn-primary btn-block" type="submit">登录</button>
            </form>
        </div>
    </div>
    <script src="https://cdn.staticfile.org/jquery/3.2.1/jquery.min.js"></script>
    <script src="https://cdn.staticfile.org/popper.js/1.15.0/umd/popper.min.js"></script>
    <script src="https://cdn.staticfile.org/twitter-bootstrap/4.3.1/js/bootstrap.min.js"></script>
</body>
</html>
