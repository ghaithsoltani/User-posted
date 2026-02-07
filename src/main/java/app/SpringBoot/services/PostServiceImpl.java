package app.SpringBoot.services;

import app.SpringBoot.entities.Post;
import app.SpringBoot.entities.UserEntity;
import app.SpringBoot.repository.PostRepository;
import app.SpringBoot.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class PostServiceImpl implements PostServiceInter{
    @Autowired
    PostRepository  postRepository;
    @Autowired
    UserRepository userRepository;
    @Override
    public Post addPost(Post p) {

        return postRepository.save(p);
    }

    @Override
    public Post createPost(Long id, Post p) {
       Optional <UserEntity> user=userRepository.findById(id);
       if(user.isPresent())
       {
           p.setUser(user.get());
       p.setCreated(new Date());
       return postRepository.save(p);
       }else{
           throw new IllegalArgumentException("user Not found");
       }
    }

    @Override
    public List<Post> listPosts(){
        return postRepository.findAll();
    }

    @Override
   public List<Post> getpostsByUsrId(Long userId){
return postRepository.findByUserId(userId);
    }


}
