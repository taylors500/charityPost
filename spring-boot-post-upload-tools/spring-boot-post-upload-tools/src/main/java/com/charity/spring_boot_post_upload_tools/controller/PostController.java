package com.charity.spring_boot_post_upload_tools.controller;

import com.charity.spring_boot_post_upload_tools.model.Post;
import com.charity.spring_boot_post_upload_tools.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/post")
@CrossOrigin(origins = "*")
public class PostController {

    @Autowired
    private PostService postService;

    @GetMapping("/all")
    public ResponseEntity<List<Post>> getAllPost() {
        List<Post> posts = postService.getAllPost();

        if (posts.isEmpty()) {
            return ResponseEntity.noContent().build();  // HTTP 204: No Content
        }
        return ResponseEntity.ok(posts);  // HTTP 200: OK
    }

    @PostMapping("/upload")
    public ResponseEntity<String> uploadNewPost(@RequestParam("title") String title,
                                                @RequestParam("content") String content,
                                                @RequestParam("file") MultipartFile file) {
        try {
            postService.uploadPost(title, content, file);
            return ResponseEntity.status(HttpStatus.CREATED).body("Post uploaded successfully");  // HTTP 201: Created
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Failed to upload post");  // HTTP 400: Bad Request
        }
    }
}
