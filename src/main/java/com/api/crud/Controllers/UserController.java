package com.api.crud.Controllers;

import com.api.crud.Models.UserModel;
import com.api.crud.Services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/user")
public class UserController {

    private UserService userService;

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public List<UserModel> getUsers(){
        return this.userService.getUsers();
    }

    @PostMapping
    public ResponseEntity<UserModel> createUser(@RequestBody UserModel user) {
        UserModel savedUser = this.userService.saveUser(user);
        return ResponseEntity.ok(savedUser);
    }

    @GetMapping(path = "/{id}")
    public Optional<UserModel> getUserById(@PathVariable("id") Long id){
        return this.userService.getById(id);
    }

    @PutMapping(path = "/{id}")
    public Optional<UserModel> updateUserById(@RequestBody UserModel request, @PathVariable("id") Long id){
        return this.userService.updateById(request,id);
    }

    @DeleteMapping(path = "/{id}")
    public String deleteById(@PathVariable("id") Long id){
        boolean ok = this.userService.deleteUser(id);
        return ok ? ("User with id " + id + "delete") : "Error";
    }


}
