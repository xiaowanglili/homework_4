let privateTarget = null;

// 切换到私聊模式
function startPrivateChat(username) {
    privateTarget = username;
    const mode = document.getElementById("chatMode");
    const exitBtn = document.getElementById("exitPrivateBtn");

    if (mode) mode.innerText = "私聊对象：" + username;
    if (exitBtn) exitBtn.style.display = "inline-block";
}

// 退出私聊
function exitPrivateChat() {
    privateTarget = null;
    const mode = document.getElementById("chatMode");
    const exitBtn = document.getElementById("exitPrivateBtn");

    if (mode) mode.innerText = "公共聊天";
    if (exitBtn) exitBtn.style.display = "none";
}

// 拉取消息
function loadMessages() {
    const xhr = new XMLHttpRequest();
    xhr.open("GET", CTX + "/chat");

    xhr.onload = function () {
        if (xhr.status === 401) {
            window.location.href = CTX + "/jsp/login.jsp";
            return;
        }

        const tmp = document.createElement("div");
        tmp.innerHTML = xhr.responseText;

        document.getElementById("messages").innerHTML =
            tmp.querySelector("#message-area").innerHTML;

        document.getElementById("userList").innerHTML =
            tmp.querySelector("#user-list-area").innerHTML;
    };

    xhr.send();
}

window.addEventListener("load", () => {
    loadMessages();
    setInterval(loadMessages, 1000);
});

// 发送消息
function sendMessage() {
    const input = document.getElementById("inputBox");
    const content = input.value.trim();
    if (!content) return;

    const xhr = new XMLHttpRequest();
    xhr.open("POST", CTX + "/chat");
    xhr.setRequestHeader("Content-Type", "application/x-www-form-urlencoded;charset=UTF-8");

    let params = "content=" + encodeURIComponent(content);
    if (privateTarget) {
        params += "&toUser=" + encodeURIComponent(privateTarget);
    }

    xhr.onload = function () {
        if (xhr.status === 401) {
            window.location.href = CTX + "/jsp/login.jsp";
            return;
        }
        input.value = "";
    };

    xhr.send(params);
}
