package app.SpringBoot.services;

import app.SpringBoot.entities.Comment;
import app.SpringBoot.entities.Post;
import app.SpringBoot.entities.UserEntity;
import app.SpringBoot.repository.CommentRepository;
import app.SpringBoot.repository.PostRepository;
import app.SpringBoot.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;

@Service
public class CommentServiceImpl implements  CommentServiceInter{

    @Autowired
    PostRepository postRepository;
    @Autowired
    CommentRepository commentRepository;
    @Autowired
    UserRepository userRepository;
    @Override
    public Comment createComment(Long userId, Long postId, Comment comment) {
        Optional< UserEntity> user= userRepository.findById(userId);
        if(user.isPresent())
        {
            Optional<Post> post=postRepository.findById(postId);

            if(post.isPresent()){
                Post p=post.get();

                comment.setPost(p);
                comment.setUser(user.get());
                comment.setCreated(new Date());
                return commentRepository.save(comment);
            }else{
                throw new IllegalArgumentException("post not found");
            }


        }else{
            throw new IllegalArgumentException("post not found");
        }

    }

}
