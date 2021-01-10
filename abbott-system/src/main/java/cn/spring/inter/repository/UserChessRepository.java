package cn.spring.inter.repository;

import cn.spring.inter.bean.UserChess;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserChessRepository extends JpaRepository<UserChess, Integer>{
    //根据id查
    UserChess findUserChessById(Integer id);

    List<UserChess> findByUsername(String username);

    UserChess findByUsernameAndPassword(String username,String password);
}
