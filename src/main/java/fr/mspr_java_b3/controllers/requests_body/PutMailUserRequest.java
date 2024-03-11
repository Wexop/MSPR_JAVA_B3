package fr.mspr_java_b3.controllers.requests_body;

public class PutMailUserRequest {

    String newMail;
    String mdp;

    public PutMailUserRequest() {
    }

    public PutMailUserRequest(String newMail, String mdp) {
        this.newMail = newMail;
        this.mdp = mdp;
    }

    public String getNewMail() {
        return newMail;
    }

    public void setNewMail(String newMail) {
        this.newMail = newMail;
    }

    public String getMdp() {
        return mdp;
    }

    public void setMdp(String mdp) {
        this.mdp = mdp;
    }
}
