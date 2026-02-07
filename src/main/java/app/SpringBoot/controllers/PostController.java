package app.SpringBoot.controllers;

import app.SpringBoot.entities.Post;
import app.SpringBoot.services.PostServiceInter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/posts")
public class PostController {
@Autowired
PostServiceInter postServiceInter;

    @PostMapping("/add")
    public Post addPost(@RequestBody Post p){
        p.setCreated(new Date());
        return postServiceInter.addPost(p);
    }

    @PostMapping("/createPost/{id}")
    public Post createPost(@PathVariable Long id,@RequestBody Post p ){
        return postServiceInter.createPost(id,p);
    }


    @GetMapping("/listPosts")
    public List<Post> listPosts(){
        return postServiceInter.listPosts();
    }

    @GetMapping("/user/{userId}")
    public List<Post> getpostsByUsrId( @PathVariable Long userId){
        return postServiceInter.getpostsByUsrId(userId);
    }

}
