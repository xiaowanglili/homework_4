<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!-- 未登录跳转 -->
<c:if test="${empty sessionScope.currentUser}">
    <c:redirect url="/jsp/login.jsp"/>
</c:if>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>在线聊天室</title>

    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">

    <!-- JS 全局上下文 -->
    <script>
        const CTX = '${pageContext.request.contextPath}';
    </script>

    <script src="${pageContext.request.contextPath}/js/chat.js" defer></script>
</head>

<body>
<div class="chat-container">

    <div class="chat-header">
        <h2>在线聊天室</h2>

        <div class="user-info">
            当前用户：${sessionScope.currentUser.username}
            <a class="logout-btn" href="${pageContext.request.contextPath}/logout">退出</a>
        </div>
    </div>

    <!-- 聊天模式显示（公共 / 私聊） -->
    <div class="chat-mode" id="chatModeContainer">
        <span id="chatMode">公共聊天</span>

        <button id="exitPrivateBtn"
                class="exit-private-btn"
                style="display:none;"
                onclick="exitPrivateChat()">
            退出私聊
        </button>
    </div>


    <div class="chat-main">

        <!-- 消息区域（与 chat.js 完全匹配） -->
        <div class="chat-messages" id="messages"></div>

        <!-- 在线用户区域（与 chat.js 完全匹配） -->
        <div class="user-list-box">
            <div id="userList"></div>
        </div>
    </div>

    <!-- 输入框 -->
    <div class="chat-input">
        <input type="text" id="inputBox" placeholder="请输入消息..." autocomplete="off">
        <button onclick="sendMessage()">发送</button>
    </div>

</div>
</body>
</html>
