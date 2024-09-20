package me.rightnow.springbootdeveloper.api.repository.user;

import me.rightnow.springbootdeveloper.api.entity.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    User findByUserId(String userId);
}
