package fr.mspr_java_b3.dto;

public class DeleteUtilisateurDTO {
    public String mdp;

    public DeleteUtilisateurDTO() {
    }

    public DeleteUtilisateurDTO(String mdp) {
        this.mdp = mdp;
    }

    public String getMdp() {
        return mdp;
    }

    public void setMdp(String mdp) {
        this.mdp = mdp;
    }
}
