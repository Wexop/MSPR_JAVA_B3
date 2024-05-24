package fr.mspr_java_b3.dto;

import fr.mspr_java_b3.entities.Annonce;
import fr.mspr_java_b3.entities.Utilisateur;

import java.util.Date;

public class PropositionPostDTO {
    private Date date;
    private String message;
    private String etat;
    private Annonce annonce;
    private Utilisateur utilisateur;
}
