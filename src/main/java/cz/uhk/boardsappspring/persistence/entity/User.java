package cz.uhk.boardsappspring.persistence.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity(name="Users")
public class User {

    @Id
    @Column(length = 50)
    private String username;

    @Column(length = 500)
    private String password;

    private boolean enabled;

    @OneToMany(mappedBy = "username")
    private List<Authority> authorities;
}