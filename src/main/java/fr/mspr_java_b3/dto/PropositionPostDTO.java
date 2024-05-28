package fr.mspr_java_b3.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import fr.mspr_java_b3.entities.PropositionEnum;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString
public class PropositionPostDTO {
    private String message;
}
