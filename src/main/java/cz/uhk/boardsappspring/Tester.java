package cz.uhk.boardsappspring;

import cz.uhk.boardsappspring.persistence.dao.PostDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class Tester implements CommandLineRunner {

    @Autowired
    private PostDAO postDAO;

    @Override
    public void run(String... args) throws Exception {
        postDAO.findVisiblePosts();
    }
}
