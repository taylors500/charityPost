// 后端路径 (根据你的后端路径修改)
const loginApiUrl = "http://localhost:8080/login/user";
const postApiUrl = "http://localhost:8080/post/upload";

// 登录逻辑
document.getElementById("loginButton").addEventListener("click", async () => {
    const employeeId = document.getElementById("employeeId").value;
    const employeeName = document.getElementById("employeeName").value;

    const response = await fetch(loginApiUrl, {
        method: "POST",
        headers: { "Content-Type": "application/x-www-form-urlencoded" },
        body: new URLSearchParams({ employeeId, employeeName })
    });

    const result = await response.text();
    if (response.ok) {
        document.getElementById("login-container").style.display = "none";
        document.getElementById("upload-container").style.display = "block";
    } else {
        const errorMessageDiv = document.getElementById("error-message");
        errorMessageDiv.innerText = result;  // 显示错误消息
        document.getElementById("employeeId").value = "";
        document.getElementById("employeeName").value = "";
    }
});

// 上传 Post 逻辑
document.getElementById("postButton").addEventListener("click", async () => {
    const title = document.getElementById("title").value;
    const content = document.getElementById("postContent").value;
    const file = document.getElementById("file").files[0];

    const formData = new FormData();
    formData.append("title", title);
    formData.append("content", content);
    formData.append("file", file);

    const response = await fetch(postApiUrl, {
        method: "POST",
        body: formData
    });

    const result = await response.text();
    if (response.ok) {
        alert("Post uploaded successfully!");
        // 重置表单
        document.getElementById("title").value = "";
        document.getElementById("postContent").value = "";
        document.getElementById("file").value = "";
    } else {
        alert("Failed to upload post: " + result);
    }
});
