package com.charity.spring_boot_post_upload_tools.repository;


import com.charity.spring_boot_post_upload_tools.model.User;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends MongoRepository<User, ObjectId> {
    User findByEmployeeIdAndEmployeeName(String employeeId, String employeeName);
}