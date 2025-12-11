<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<!DOCTYPE html>
<html>
<head>
    <title>在线聊天室</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">


    <!-- 传递 contextPath 给外部 JS -->
    <script>
        const ROOT = "${pageContext.request.contextPath}";
    </script>

    <script src="${pageContext.request.contextPath}/js/chat.js"></script>
</head>
<body>

<div class="chat-container">

    <div class="chat-header">
        <h2>在线聊天室</h2>
        <a class="logout" href="${pageContext.request.contextPath}/logout">退出</a>
    </div>

    <div id="chat-box"></div>

    <form id="sendForm" onsubmit="return false;">
        <div class="input-row">
            <input type="text" id="inputMsg" placeholder="请输入消息…" required>
            <button type="button" id="sendBtn" class="send-btn">发送</button>
        </div>
    </form>

</div>

</body>
</html>
