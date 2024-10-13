package com.charity.spring_boot_post_upload_tools.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "post")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Post {
    @Id
    private ObjectId id;
    private String title;
    private String postContent;
    private String docsUrl;  // 上传到腾讯 COS 的文档 URL

    public Post(String title, String postContent, String docsUrl) {
        this.title = title;
        this.postContent = postContent;
        this.docsUrl = docsUrl;
    }
}
