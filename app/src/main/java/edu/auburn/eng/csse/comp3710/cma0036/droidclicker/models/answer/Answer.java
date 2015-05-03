package edu.auburn.eng.csse.comp3710.cma0036.droidclicker.models.answer;

/**
 * Created by caioa_000 on 23/04/2015.
 */
public class Answer {

    private String id_question;
    private String id_alternative;
    private String id_user;

    public String getId_alternative() {
        return id_alternative;
    }

    public void setId_alternative(String id_alternative) {
        this.id_alternative = id_alternative;
    }

    public String getId_user() {
        return id_user;
    }

    public void setId_user(String id_user) {
        this.id_user = id_user;
    }


    public String getId_question() {
        return id_question;
    }

    public void setId_question(String id_question) {
        this.id_question = id_question;
    }

    public Answer() {
    }

    public Answer(String id_question, String id_alternative, String id_user) {
        this.id_question = id_question;
        this.id_alternative = id_alternative;
        this.id_user = id_user;
    }
}
