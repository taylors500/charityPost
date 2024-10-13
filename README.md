# 项目说明

## 项目简介

该项目是一个用于验证员工身份并上传文档到腾讯云 COS 的简易应用，使用 Spring Boot 作为后端，MongoDB Atlas 作为数据库，前端采用 HTML、CSS 和 JavaScript。

---

## 准备工作

### 1. 替换 `TencentCosService` 中的必要字段

在 `backend/src/main/java/com/charity/spring_boot_post_upload_tools/service/TencentCosService.java` 文件中，找到以下字段，并根据你的腾讯云账号信息进行替换：

```java
private static final String secretId = "your-secret-id";      // 腾讯云的 secretId
private static final String secretKey = "your-secret-key";    // 腾讯云的 secretKey
private static final String bucketName = "your-bucket-name";  // 存储桶名称
private static final String regionName = "your-region";       // 地区名称，例如 ap-guangzhou
```

请务必替换为你自己的信息，以确保文件可以上传到正确的腾讯云存储桶中。

---

### 2. 替换 `application.properties` 的 URL

在 `backend/src/main/resources/application.properties` 文件中，找到以下字段：

```properties
spring.data.mongodb.uri=mongodb+srv://<username>:<password>@cluster0.mongodb.net/<dbname>?retryWrites=true&w=majority
```

请替换其中的 `<username>`、`<password>` 和 `<dbname>` 为你在 MongoDB Atlas 中设置的用户名、密码和数据库名称。

---

### 3. MongoDB Atlas 配置

#### 3.1 创建 Atlas Cluster
1. 访问 [MongoDB Atlas](https://www.mongodb.com/cloud/atlas) 网站并登录。
2. 创建一个新的 Atlas 集群，选择适合你的集群类型。
3. 配置你的 IP 访问规则，并确保应用能连接你的数据库。

#### 3.2 创建必要的 Collection
在 Atlas 中，创建两个集合：
- `user`
- `post`

请确保集合名称准确无误。

#### 3.3 插入测试用户数据
在 `user` 集合中插入以下测试数据：

```json
[
    {
        "employeeId": "E001",
        "employeeName": "Alice Johnson"
    },
    {
        "employeeId": "E002",
        "employeeName": "Bob Smith"
    },
    {
        "employeeId": "E003",
        "employeeName": "Charlie Brown"
    },
    {
        "employeeId": "E004",
        "employeeName": "Diana Prince"
    },
    {
        "employeeId": "E005",
        "employeeName": "Ethan Hunt"
    }
]
```

这些数据将用于调试和测试登录功能。

---

### 4. 前端测试脚本的替换

目前前端使用的是 `mock_script.js` 进行模拟登录和上传功能，文件位于 `frontend/mock_script.js`。在调试完项目后，请将前端 HTML 文件中的 `mock_script.js` 替换为正式的 `script.js`，该文件将真正连接到后端 API。

```html
<!-- 调试时的前端 -->
<script src="mock_script.js"></script>

<!-- 正式上线时 -->
<script src="script.js"></script>
```

你可以使用以下账户进行测试登录：
- **Employee ID**: `E001`
- **Employee Name**: `Alice Johnson`

---

## 将项目打包成 EXE 档案

为了方便慈善组织使用，我们希望将这个应用打包成一个可以运行的 `.exe` 文件，用户双击运行时可以启动本地服务器，前后端自动加载并占用本地端口。你可以参考以下步骤：

### 1. 使用 `JPackage` 打包 Spring Boot 应用

`JPackage` 是 JDK 自带的工具，可以将 Java 应用打包成可执行文件，适合将 Spring Boot 项目打包成 `.exe`。

#### 步骤：
1. **打包 Spring Boot 应用为 JAR**：
   进入 `backend` 文件夹，执行以下 Maven 命令将 Spring Boot 项目打包为 JAR 文件：

   ```bash
   mvn clean package
   ```

   打包后，你会在 `target` 文件夹下找到 `.jar` 文件。

2. **使用 JPackage 打包为 EXE**：
   在打包完 JAR 文件后，使用以下命令打包成 `.exe`：

   ```bash
   jpackage --input target --name CharityApp --main-jar charity-springboot-app.jar --type exe --main-class com.charity.spring_boot_post_upload_tools.YourMainClass
   ```

   - `--input`：指定生成的 JAR 文件所在的目录。
   - `--name`：生成的 EXE 文件的名称。
   - `--main-jar`：Spring Boot 项目的 JAR 文件名。
   - `--main-class`：你项目的主类所在路径（通常是包含 `main` 方法的类）。

   这将生成一个 `.exe` 文件，双击即可运行。

---

### 2. 部署前端

生成的 EXE 文件会自动启动 Spring Boot 项目，占用本地的一个端口（例如 `http://localhost:8080`）。**但是默认情况下，Spring Boot 项目只会启动后端，不会自动提供前端页面的静态资源。**

因此，**你需要手动将前端文件部署到 Spring Boot 项目的静态资源目录中**。可以按照以下步骤进行操作：

#### 步骤：

1. 在 Spring Boot 项目中创建一个静态资源文件夹。通常情况下，你可以在 `src/main/resources/` 路径下创建一个 `static` 文件夹：
   ```
   backend/src/main/resources/static/
   ```

2. 将前端的所有 HTML、CSS 和 JavaScript 文件放入这个 `static` 文件夹中。例如：
   ```
   backend/src/main/resources/static/index.html
   backend/src/main/resources/static/style.css
   backend/src/main/resources/static/script.js
   ```

3. 配置 Spring Boot 以加载这些静态资源。Spring Boot 会自动将 `static` 文件夹下的文件作为静态资源提供。启动后，访问 `http://localhost:8080/index.html` 即可加载前端页面。

---

## 其他打包成 EXE 的工具选项

除了 `JPackage`，你还可以使用其他工具来将 Spring Boot 和前端打包成一个完整的 EXE 文件。以下是一个常用的工具推荐：

### 1. **Launch4J**

`Launch4J` 是一个可以将 JAR 文件打包成 EXE 文件的工具。你可以使用 `Launch4J` 将你的 Spring Boot 项目和前端打包成一个独立的 EXE 应用。

### 2. **Packer**

`Packer` 也是一种打包工具，可以将完整的应用打包成一个单独的 EXE 文件。`Packer` 的优势是能够将所有依赖项打包在一个 EXE 中，用户无需单独配置运行环境。

---

## 总结

该项目在前后端功能上非常简单，通过合理配置后即可部署运行：
- 确保后端的腾讯云 COS 配置和 MongoDB Atlas 配置正确。
- 测试完成后记得将 `mock_script.js` 替换为正式的 `script.js`。
- 按照指南使用 JPackage 或其他工具打包成 EXE 文件，方便慈善组织使用。

希望这个指南能帮助你顺利完成部署和发布应用！
