package com.charity.spring_boot_post_upload_tools.service;

import com.charity.spring_boot_post_upload_tools.model.User;
import com.charity.spring_boot_post_upload_tools.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public boolean validateUser(String employeeId, String employeeName) {
        // 查询数据库中是否存在该用户
        User user = userRepository.findByEmployeeIdAndEmployeeName(employeeId, employeeName);
        return user != null;
    }
}
