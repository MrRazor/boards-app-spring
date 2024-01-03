package cz.uhk.boardsappspring.persistence.entity.model;

import cz.uhk.boardsappspring.persistence.entity.User;
import jakarta.persistence.*;
import lombok.Data;

import java.sql.Timestamp;

@Data
@MappedSuperclass
public abstract class AbstractUserContent implements UserContent {

    @Id
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "author")
    private User author;

    private String content;

    @Column(insertable = false)
    private boolean removed;

    @Column(insertable = false, updatable = false)
    private Timestamp createdAt;

    @Column(insertable = false, updatable = false)
    private Timestamp updatedAt;
}
