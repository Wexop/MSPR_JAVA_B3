package fr.mspr_java_b3.controllers.requests_body;

public class LoginUserRequest {

    String mail;
    String mdp;

    public LoginUserRequest() {
    }

    public LoginUserRequest(String mail, String mdp) {
        this.mail = mail;
        this.mdp = mdp;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getMdp() {
        return mdp;
    }

    public void setMdp(String mdp) {
        this.mdp = mdp;
    }
}
