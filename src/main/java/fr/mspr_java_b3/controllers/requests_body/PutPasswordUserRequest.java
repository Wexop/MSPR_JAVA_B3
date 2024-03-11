package fr.mspr_java_b3.controllers.requests_body;

public class PutPasswordUserRequest {

    String newMdp;
    String mdp;

    public PutPasswordUserRequest() {
    }

    public PutPasswordUserRequest(String newMdp, String mdp) {
        this.newMdp = newMdp;
        this.mdp = mdp;
    }

    public String getNewMdp() {
        return newMdp;
    }

    public void setNewMdp(String newMdp) {
        this.newMdp = newMdp;
    }

    public String getMdp() {
        return mdp;
    }

    public void setMdp(String mdp) {
        this.mdp = mdp;
    }
}
