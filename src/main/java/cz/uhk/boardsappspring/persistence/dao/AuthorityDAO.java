package cz.uhk.boardsappspring.persistence.dao;

import cz.uhk.boardsappspring.persistence.entity.Authority;
import org.springframework.stereotype.Repository;

@Repository
public class AuthorityDAO extends AbstractJpaDAO<Authority> {
    public AuthorityDAO() {
        setClazz(Authority.class);
    }
}
