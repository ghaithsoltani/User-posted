package app.SpringBoot.controllers;

import app.SpringBoot.entities.UserEntity;
import app.SpringBoot.services.UserServiceInter;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.graphql.ConditionalOnGraphQlSchema;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {


    @Autowired
    UserServiceInter userServiceInter;

    @GetMapping("/test")
    public String test(){
        return "Heey User";
    }


    @PostMapping("/add")
    public UserEntity addUser(@RequestBody UserEntity user ){
        return userServiceInter.userAdd(user);
    }


    @DeleteMapping("/delete/{id}")
    public void deleteUser(@PathVariable Long id )
    {
        userServiceInter.delete(id);
    }


    @PostMapping("/addUsers")
    public List<UserEntity> users(@RequestBody List<UserEntity> users)
    {
        return userServiceInter.usersT(users);

    }


    @GetMapping("/getAllUsers")
    public List<UserEntity> getAllUsers()
    {
        return userServiceInter.getAllUsers();

    }


    @PutMapping("updateUser/{id}")
    public UserEntity updateUserPut(@PathVariable Long id ,@RequestBody UserEntity user)
    {
        return userServiceInter.updateUserPut(id,user);
    }


    @PostMapping("updateUserPost/{id}")
    public UserEntity updateUserPost(@PathVariable Long id ,@RequestBody UserEntity user)
    {
        return userServiceInter.updateUserPost(id,user);
    }


    @GetMapping("getUserById/{id}")
    public UserEntity getUserById(@PathVariable Long id)
    {
        return userServiceInter.getUserById(id);

    }

    //persooo
    @GetMapping("getUsersP/{un}")
    public List<UserEntity> getUsersP(@PathVariable String un)
    {
        return userServiceInter.getUsersP(un);
    }


    @GetMapping("/login")
    public ResponseEntity<UserEntity> login(@RequestParam String username, @RequestParam String password){
        return userServiceInter.login(username,password);


    }

}
