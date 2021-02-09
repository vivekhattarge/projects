package com.example.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class UserController {
    @Autowired
    tutorial.user.UserRepository userRepository;

    @CrossOrigin()
    @PostMapping("/users/register")
    public Status registerUser(@Valid @RequestBody User newUser) {
        User dbUser = userRepository.findOnebyUsername(newUser.getUsername());
        if (dbUser != null) {
            System.out.println("User Already exists!");
            return Status.USER_ALREADY_EXISTS;
        }
        userRepository.save(newUser);
        return Status.SUCCESS;
    }

    @CrossOrigin()
    @PostMapping("/users/login")
    public Status loginUser(@Valid @RequestBody User user) {
        User saveduser = userRepository.findOnebyUsername(user.getUsername());
        if(null != saveduser){
            return Status.SUCCESS;
        }
        return Status.FAILURE;
    }

    @CrossOrigin()
    @PostMapping("/users/logout")
    public Status logUserOut(@Valid @RequestBody User user) {
        User saveduser = userRepository.findOnebyUsername(user.getUsername());
        if(null != saveduser){
            user.setLoggedIn(false);
            userRepository.save(saveduser);
            return Status.SUCCESS;
        }
        return Status.FAILURE;
    }


}