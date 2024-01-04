package cz.uhk.boardsappspring.persistence.dao;

import cz.uhk.boardsappspring.persistence.entity.Post;
import jakarta.persistence.TypedQuery;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class PostDAO extends AbstractJpaDAO<Post,Long> {
    public PostDAO() {
        setClazz(Post.class);
    }

    public Long createAndReturnId(Post post) {
        entityManager.persist(post);
        entityManager.flush();
        return post.getId();
    }

    public List<Post> findVisiblePosts() {
        return getVisiblePostsSelectQuery()
                .getResultList();
    }

    public List<Post> findVisiblePosts(int pageNumber, int pageSize) {
        return getVisiblePostsSelectQuery()
                .setFirstResult((pageNumber-1)*pageSize)
                .setMaxResults(pageSize)
                .getResultList();
    }

    private TypedQuery<Post> getVisiblePostsSelectQuery() {
        return entityManager
                .createQuery("from Posts where removed=false order by createdAt desc", Post.class);
    }
}