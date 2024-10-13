// 模拟登录和上传的逻辑

document.getElementById("loginButton").addEventListener("click", () => {
    const employeeId = document.getElementById("employeeId").value;
    const employeeName = document.getElementById("employeeName").value;

    // 简单的模拟逻辑：假设只有ID "E001" 和 "Alice Johnson" 能登录
    if (employeeId === "E001" && employeeName === "Alice Johnson") {
        document.getElementById("login-container").style.display = "none";
        document.getElementById("upload-container").style.display = "block";
    } else {
        const errorMessageDiv = document.getElementById("error-message");
        errorMessageDiv.innerText = "Invalid credentials. Please try again.";
        document.getElementById("employeeId").value = "";
        document.getElementById("employeeName").value = "";
    }
});

// 模拟上传 Post 的逻辑
document.getElementById("postButton").addEventListener("click", () => {
    const title = document.getElementById("title").value;
    const content = document.getElementById("postContent").value;
    const file = document.getElementById("file").files[0];

    if (title && content && file) {
        alert("Post uploaded successfully!");
        // 重置表单
        document.getElementById("title").value = "";
        document.getElementById("postContent").value = "";
        document.getElementById("file").value = "";
    } else {
        alert("Please fill in all fields and select a file.");
    }
});
