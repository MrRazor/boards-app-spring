package cz.uhk.boardsappspring.persistence.entity.model;

import cz.uhk.boardsappspring.persistence.entity.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Data
@NoArgsConstructor
@AllArgsConstructor
@MappedSuperclass
public abstract class AbstractUserContent implements UserContent {

    @Id
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "author")
    private User author;

    private String content;
    private boolean removed;
    private Timestamp createdAt;
    private Timestamp updatedAt;
}
