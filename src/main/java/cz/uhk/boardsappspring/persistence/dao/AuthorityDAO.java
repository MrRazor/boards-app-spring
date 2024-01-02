package cz.uhk.boardsappspring.persistence.dao;

import cz.uhk.boardsappspring.persistence.entity.model.AuthoritiesId;
import cz.uhk.boardsappspring.persistence.entity.Authority;
import org.springframework.stereotype.Repository;

@Repository
public class AuthorityDAO extends AbstractJpaDAO<Authority, AuthoritiesId> {
    public AuthorityDAO() {
        setClazz(Authority.class);
    }
}
