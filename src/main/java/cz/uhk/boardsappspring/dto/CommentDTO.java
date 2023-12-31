package cz.uhk.boardsappspring.dto;

import cz.uhk.boardsappspring.dto.model.AbstractUserContentDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
public class CommentDTO extends AbstractUserContentDTO {

}
