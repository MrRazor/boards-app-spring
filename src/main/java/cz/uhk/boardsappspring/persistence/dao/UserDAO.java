package cz.uhk.boardsappspring.persistence.dao;

import cz.uhk.boardsappspring.persistence.entity.User;
import org.springframework.stereotype.Repository;

@Repository
public class UserDAO extends AbstractJpaDAO<User>{
    public UserDAO() {
        setClazz(User.class);
    }
}
