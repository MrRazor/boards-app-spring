package cz.uhk.boardsappspring.persistence.dao;

import cz.uhk.boardsappspring.persistence.entity.User;
import org.springframework.stereotype.Repository;

@Repository
public class UserDAO extends AbstractJpaDAO<User,String>{
    public UserDAO() {
        setClazz(User.class);
    }

    public User findUserByUsernameAndPassword(String username, String password) {
        return entityManager.createQuery("from Users where username=:usernameParam and password=:passwordParam", User.class)
                .setParameter("usernameParam", username)
                .setParameter("passwordParam", password)
                .getSingleResult();
    }
}