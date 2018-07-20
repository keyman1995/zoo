package com.zoo.house.service;

import com.zoo.house.dao.UserDao;
import com.zoo.house.model.po.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    @Override
    public List<Student> findAll() {
       List<Student> students = userDao.getAll();
        return students;
    }
}
