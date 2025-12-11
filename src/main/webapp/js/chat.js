window.onload = function () {
    loadMessages();
    setInterval(loadMessages, 1000);

    document.getElementById("sendBtn").onclick = sendMessage;

    document.getElementById("inputMsg").addEventListener("keypress", function(e) {
        if (e.key === "Enter") {
            e.preventDefault();
            sendMessage();
        }
    });
};

/* 加载消息 */
function loadMessages() {
    fetch(ROOT + "/chat")
        .then(resp => resp.text())
        .then(html => {
            document.getElementById("chat-box").innerHTML = html;
            let box = document.getElementById("chat-box");
            box.scrollTop = box.scrollHeight; // 自动滚动到底部
        });
}

/* 发送消息 */
function sendMessage() {
    let content = document.getElementById("inputMsg").value.trim();
    if (content === "") {
        alert("请输入消息内容");
        return;
    }

    fetch(ROOT + "/chat", {
        method: "POST",
        headers: {"Content-Type": "application/x-www-form-urlencoded"},
        body: "content=" + encodeURIComponent(content)
    }).then(() => {
        document.getElementById("inputMsg").value = "";
        loadMessages();
    });
}
