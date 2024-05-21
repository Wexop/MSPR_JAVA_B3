package fr.mspr_java_b3.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class ArticleGetDTO {
    private int id;
    private String titre;
    private String image_url;
    private String contenue;
}
