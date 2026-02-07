package app.SpringBoot.services;

import app.SpringBoot.entities.UserEntity;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface UserServiceInter {
    UserEntity userAdd(UserEntity user);
    void delete(Long id);

    List<UserEntity> usersT(List<UserEntity> users);

    List<UserEntity> getAllUsers();

    UserEntity updateUserPut(Long id, UserEntity user);

    UserEntity updateUserPost(Long id, UserEntity user);
    UserEntity getUserById(Long id);
    List<UserEntity> getUsersP(String un);
    ResponseEntity<UserEntity> login(String username, String password);

}
