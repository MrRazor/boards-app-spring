package cz.uhk.boardsappspring.dto;

import cz.uhk.boardsappspring.dto.model.AbstractUserContentDTO;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class PostDTO extends AbstractUserContentDTO {

    private String title;
}
