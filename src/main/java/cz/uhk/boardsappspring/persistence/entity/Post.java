package cz.uhk.boardsappspring.persistence.entity;

import cz.uhk.boardsappspring.persistence.entity.model.AbstractUserContent;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@Entity(name="Posts")
public class Post extends AbstractUserContent {

    @Column(length = 255)
    private String title;

    @OneToMany(mappedBy = "post")
    private List<Comment> comments;
}
