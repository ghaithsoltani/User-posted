package app.SpringBoot.controllers;

import app.SpringBoot.entities.Comment;
import app.SpringBoot.services.CommentServiceInter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/comments")
public class CommentController {

    @Autowired
    CommentServiceInter commentServiceInter;

    @PostMapping("/add/user/{userId}/post/{postId}")
    public Comment createComment(@PathVariable Long userId, @PathVariable Long postId, @RequestBody Comment comment)
    {
        return commentServiceInter.createComment(userId,postId,comment);
    }

}
