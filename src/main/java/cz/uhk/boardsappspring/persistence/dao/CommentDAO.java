package cz.uhk.boardsappspring.persistence.dao;

import cz.uhk.boardsappspring.persistence.entity.Comment;
import jakarta.persistence.TypedQuery;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CommentDAO extends AbstractJpaDAO<Comment,Long> {
    public CommentDAO() {
        setClazz(Comment.class);
    }

    public Long createAndReturnId(Comment comment) {
        entityManager.persist(comment);
        entityManager.flush();
        return comment.getId();
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
                .createQuery("from Comments where removed=false and post.id=:postIdParam and post.removed=false order by createdAt asc", Comment.class)
                .setParameter("postIdParam", postId);
    }
}