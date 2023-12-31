package cz.uhk.boardsappspring.persistence.dao;

import cz.uhk.boardsappspring.persistence.entity.Comment;
import jakarta.persistence.TypedQuery;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CommentDAO extends AbstractJpaDAO<Comment> {
    public CommentDAO() {
        setClazz(Comment.class);
    }

    public List<Comment> findVisibleCommentsByPostId(Long postId) {
        return getVisibleCommentsByPostIdSelectQuery(postId)
                .getResultList();
    }

    public List<Comment> findVisibleCommentsByPostId(Long postId, int pageNumber, int pageSize) {
        return getVisibleCommentsByPostIdSelectQuery(postId)
                .setFirstResult((pageNumber-1)*pageSize)
                .setMaxResults(pageSize)
                .getResultList();
    }

    private TypedQuery<Comment> getVisibleCommentsByPostIdSelectQuery(Long postId) {
        return entityManager
                .createQuery("from Comments where removed=:removedParam and post.id=:postIdParam order by createdAt asc", Comment.class)
                .setParameter("removedParam", true)
                .setParameter("postIdParam", postId);
    }
}
