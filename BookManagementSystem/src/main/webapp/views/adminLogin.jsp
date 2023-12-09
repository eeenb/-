<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <title>管理员登录</title>
    <link rel="stylesheet" href="https://cdn.staticfile.org/twitter-bootstrap/4.3.1/css/bootstrap.min.css">
    <style>
        body {
            margin-top: 50px;
            background: #f7f7f7;
        }
        .form-container {
            background: #fff;
            padding: 30px;
            border-radius: 8px;
            box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
            max-width: 400px;
            margin: 0 auto;
        }
        .form-title {
            margin-bottom: 20px;
            text-align: center;
        }
        .form-group {
            margin-bottom: 15px;
        }
        .form-container .btn {
            width: 100%;
            padding: 10px;
        }
    </style>
</head>
<body>
    <div class="container">
        <div class="form-container">
            <h2 class="form-title">管理员登录</h2>
            <form action="${pageContext.request.contextPath}/loginController" method="post">
                <div class="form-group">
                    <label for="username">用户名:</label>
                    <input type="text" class="form-control" name="username" id="username" required>
                </div>
                <div class="form-group">
                    <label for="password">密码:</label>
                    <input type="password" class="form-control" name="password" id="password" required>
                </div>
                <input type="hidden" name="userType" value="admin">
                <button type="submit" class="btn btn-primary">登录</button>
            </form>
        </div>
    </div>
    <script src="https://cdn.staticfile.org/jquery/3.2.1/jquery.min.js"></script>
    <script src="https://cdn.staticfile.org/popper.js/1.15.0/umd/popper.min.js"></script>
    <script src="https://cdn.staticfile.org/twitter-bootstrap/4.3.1/js/bootstrap.min.js"></script>
</body>
</html>
