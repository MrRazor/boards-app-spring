package cz.uhk.boardsappspring.persistence.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AuthoritiesId implements Serializable {

    private String username;

    private String authorityName;
}
