package app.SpringBoot.repository;

import app.SpringBoot.entities.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository  extends JpaRepository<UserEntity,Long> {


    @Query(value = " select * from users u where u.username like :ch%" ,nativeQuery = true)
    List<UserEntity> getUsersP(@Param("ch") String un);

    UserEntity findByUsername(String username);


}
