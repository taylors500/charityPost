package com.charity.spring_boot_post_upload_tools.service;

import com.charity.spring_boot_post_upload_tools.model.Post;
import com.charity.spring_boot_post_upload_tools.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Service
public class PostService {

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private TencentCosService tencentCosService;

    public List<Post> getAllPost(){
        return postRepository.findAll();
    }

    public Post uploadPost(String title, String content, MultipartFile relatedFile) {
        // 上传文件到腾讯 COS 并获取文件的 URL
        String docsUrl = tencentCosService.uploadFile(relatedFile);

        // 将新的 Post 保存到数据库
        Post newPost = postRepository.insert(new Post(title, content, docsUrl));
        System.out.println("New Post Uploaded: " + newPost);
        return newPost;
    }
}
