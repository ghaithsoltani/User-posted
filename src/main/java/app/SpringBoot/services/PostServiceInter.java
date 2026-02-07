package app.SpringBoot.services;

import app.SpringBoot.entities.Post;

import java.util.List;

public interface PostServiceInter {
    Post addPost(Post p);

    Post createPost(Long id, Post p);
    List<Post> listPosts();
    List<Post> getpostsByUsrId(Long userId);

}
