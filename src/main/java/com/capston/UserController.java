package com.capston;

import com.capston.dao.UserDao;
import com.capston.entities.ParentEntity;
import com.capston.entities.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class UserController {

    @Autowired
    private UserDao userDao;

    @RequestMapping(path = "/users", method = RequestMethod.GET)
    public ModelAndView getAllUsers() {
        List<UserEntity> users = userDao.findAll();
        ModelAndView mav = new ModelAndView("users");
        mav.addObject("users", users);
        mav.addObject("user", new UserEntity());
        return mav;
    }

    @RequestMapping(path = "/users", method = RequestMethod.POST)
    public ModelAndView addUser(UserEntity userEntity) {
        userDao.save(userEntity);
        return getAllUsers();
    }

    @RequestMapping(method = RequestMethod.DELETE, path = "/users/{userId}")
    public ModelAndView deleteUser(@PathVariable Integer userId) {
        UserEntity userToDelete = userDao.findById(userId);
        userDao.delete(userToDelete);
        return getAllUsers();
    }

    @RequestMapping(path = "/login", method = RequestMethod.GET)
    public ModelAndView loginPage() {

        ModelAndView mav = new ModelAndView("login");
        return mav;
    }

    @RequestMapping(path = "/login", method = RequestMethod.POST)
    public ModelAndView login() {

        ModelAndView mav = new ModelAndView("login");
        return mav;
    }

}
