package fr.mspr_java_b3.dto;

import java.sql.Blob;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Builder
public class ImageDTO {
    private Blob image;
}
