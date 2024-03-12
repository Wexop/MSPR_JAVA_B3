package fr.mspr_java_b3.controllers.requests_body;

public class DeleteUserRequest {
    public String mdp;

    public DeleteUserRequest() {
    }

    public DeleteUserRequest(String mdp) {
        this.mdp = mdp;
    }

    public String getMdp() {
        return mdp;
    }

    public void setMdp(String mdp) {
        this.mdp = mdp;
    }
}
