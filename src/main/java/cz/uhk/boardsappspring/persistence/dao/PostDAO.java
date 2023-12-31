package cz.uhk.boardsappspring.persistence.dao;

import cz.uhk.boardsappspring.persistence.entity.Post;
import jakarta.persistence.TypedQuery;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class PostDAO extends AbstractJpaDAO<Post> {
    public PostDAO() {
        setClazz(Post.class);
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
                .createQuery("from Posts where removed=:removedParam order by createdAt desc", Post.class)
                .setParameter("removedParam", true);
    }
}
