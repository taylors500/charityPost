package com.charity.spring_boot_post_upload_tools.repository;


import com.charity.spring_boot_post_upload_tools.model.Post;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface PostRepository extends MongoRepository<Post, ObjectId> {

    Optional<Post> findById(String id);
}