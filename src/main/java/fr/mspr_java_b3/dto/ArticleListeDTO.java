package fr.mspr_java_b3.dto;

import java.util.List;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class ArticleListeDTO {
    private List<ArticleGetDTO> articleList;
}
