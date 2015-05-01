package edu.auburn.eng.csse.comp3710.cma0036.droidclicker.models.alternatives;

import java.util.ArrayList;

/**
 * Created by caioa_000 on 23/04/2015.
 */
public class Alternative {

    private String description;
    private String id_quiz;
    private String id;

    public Alternative() {
    }

    public Alternative(String description, String id_quiz, String id) {
        this.description = description;
        this.id_quiz = id_quiz;
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getId_quiz() {
        return id_quiz;
    }

    public void setId_quiz(String id_quiz) {
        this.id_quiz = id_quiz;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
