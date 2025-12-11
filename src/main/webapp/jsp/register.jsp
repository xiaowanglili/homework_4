<%@ page contentType="text/html;charset=UTF-8" %>

<html>
<head>
    <title>用户注册</title>
    <link rel="stylesheet" href="../css/style.css">
</head>

<body>

<div class="login-container">

    <div class="login-title">用户注册</div>

    <form action="../register" method="post" class="login-form">

        <label>用户名：</label>
        <input type="text" name="username" required>

        <label>密码：</label>
        <input type="password" name="password" required>

        <label>确认密码：</label>
        <input type="password" name="confirmPassword" required>

        <label>验证码：</label>
        <div class="captcha-box">
            <input type="text" name="captcha" required>
            <img src="../captcha" onclick="this.src='../captcha?' + Math.random()">
        </div>

        <button type="submit" class="login-btn">注册</button>

        <div class="to-register">
            已有账号？ <a href="login.jsp">立即登录</a>
        </div>

    </form>

</div>

</body>
</html>
