package cz.uhk.boardsappspring;

import cz.uhk.boardsappspring.persistence.dao.AuthorityDAO;
import cz.uhk.boardsappspring.persistence.entity.model.AuthoritiesId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class Tester implements CommandLineRunner {

    @Autowired
    private AuthorityDAO authorityDAO;

    @Override
    public void run(String... args) throws Exception {
        AuthoritiesId authoritiesId = new AuthoritiesId();
        authoritiesId.setUsername("haha");
        authoritiesId.setAuthorityName("haha");
        authorityDAO.findOne(new AuthoritiesId());
    }
}
