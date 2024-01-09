package cz.uhk.boardsappspring.persistence.dao;

import cz.uhk.boardsappspring.persistence.entity.User;
import org.springframework.stereotype.Repository;

@Repository
public class UserDAO extends AbstractJpaDAO<User,String>{
    public UserDAO() {
        setClazz(User.class);
    }

    @Override
    public User findOne(String id) {
        return entityManager.createQuery("select u from Users u join fetch u.authorities where u.username=:usernameParam", User.class)
                .setParameter("usernameParam", id)
                .getSingleResult();
    }
}