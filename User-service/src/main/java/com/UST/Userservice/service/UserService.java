package com.UST.Userservice.service;

import com.UST.Userservice.VO.Department;
import com.UST.Userservice.VO.ResponseTemplateVO;
import com.UST.Userservice.entity.User;
import com.UST.Userservice.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class UserService {
    @Autowired
    private UserRepository repo;
    @Autowired
    private RestTemplate restTemplate;
    public User saveUser(User user) {
        return repo.save(user);
    }

    public ResponseTemplateVO getUserWithdepartment(Long userId) {
        ResponseTemplateVO vo= new ResponseTemplateVO();
        User user=repo.findByUserId(userId);
        Department department=restTemplate.getForObject("http://DEPARTMENT-SERVICE/department/"+user.getDepartmentId(),Department.class);
        vo.setUser(user);
        vo.setDepartment(department);
        return vo;
    }


}
