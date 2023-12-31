package cz.uhk.boardsappspring.persistence.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity(name="Authorities")
@IdClass(AuthoritiesId.class)
public class Authority {

    @Id
    @Column(length = 50)
    private String username;

    @Id
    @Column(name="authority", length = 50)
    private String authorityName;
}
