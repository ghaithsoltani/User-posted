package app.SpringBoot.services;

import app.SpringBoot.entities.Role;
import app.SpringBoot.entities.UserEntity;
import app.SpringBoot.repository.RoleRepository;
import app.SpringBoot.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserServiceInter{

    @Autowired
    UserRepository userRepository;

    @Autowired
    RoleRepository roleRepository;
    @Override
    public UserEntity userAdd(UserEntity user) {

        Role role =roleRepository.findById(1L).orElse(null);
user.getRole().add(role);
        BCryptPasswordEncoder passwordEncoder= new BCryptPasswordEncoder();
        String encryptedPassword= passwordEncoder.encode(user.getPassword());
user.setPassword(encryptedPassword);
        return userRepository.save(user);



    }

    @Override
    public void delete(Long id)
    {
        userRepository.deleteById(id);

    }


    @Override
    public List<UserEntity> usersT(List<UserEntity> us)
    {

    return   userRepository.saveAll(us);

    }


    @Override
    public List<UserEntity> getAllUsers()
    {
        List<UserEntity> users= userRepository.findAll();
        return  users;
        //return userRepository.findAll();
    }

    @Override
    public UserEntity updateUserPut(Long id, UserEntity user) {
        UserEntity usr = userRepository.findById(id).get();

        usr.setEmail(user.getEmail());
        usr.setFirstName(user.getFirstName());
        return  userRepository.save(usr);
    }
    @Override
    public UserEntity updateUserPost(Long id, UserEntity user) {
        UserEntity usr = userRepository.findById(id).get();

        usr.setEmail(user.getEmail());
        usr.setFirstName(user.getFirstName());
        return  userRepository.save(usr);
    }

    @Override
    public UserEntity   getUserById(Long id){
        return userRepository.findById(id).get();
    }

    @Override
    public List<UserEntity> getUsersP(String un) {
        return userRepository.getUsersP(un);
    }



    @Override
    public ResponseEntity<UserEntity>login(String username,String password)
    {

        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

        UserEntity user=userRepository.findByUsername(username);
        //if((user!=null) && (user.getPassword().matches(password) ) )
if (user!=null && passwordEncoder.matches(password,user.getPassword()))
        {
            return  ResponseEntity.ok(user);
        }else
        {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

    }

}
