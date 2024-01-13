package cz.uhk.boardsappspring.dto.mapper;

import cz.uhk.boardsappspring.dto.AuthorityDTO;
import cz.uhk.boardsappspring.persistence.entity.Authority;
import org.mapstruct.Mapper;

@Mapper
public interface AuthorityMapper {
    AuthorityDTO authorityToAuthorityDTO(Authority authority);
    Authority authorityDTOToAuthority(AuthorityDTO authorityDTO);
}
