package cz.uhk.boardsappspring.persistence.entity;

import lombok.Data;

import java.io.Serializable;

@Data
public class AuthoritiesId implements Serializable {

    private String username;

    private String authorityName;
}
