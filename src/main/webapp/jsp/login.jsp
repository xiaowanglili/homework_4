<%@ page contentType="text/html;charset=UTF-8" %>

<link rel="stylesheet" href="../css/style.css">

<div class="login-container">

    <div class="login-title">用户登录</div>

    <form action="../login" method="post" class="login-form">

        <label>用户名：</label>
        <input type="text" name="username" value="${param.username}">

        <label>密码：</label>
        <input type="password" name="password">

        <label>验证码：</label>
        <div class="captcha-box">
            <input type="text" name="captcha">
            <img src="../captcha" onclick="this.src='../captcha?'+Math.random()">
        </div>

        <button type="submit" class="login-btn">登录</button>

        <div class="to-register">
            还没有账号？ <a href="register.jsp">立即注册</a>
        </div>
    </form>
</div>
