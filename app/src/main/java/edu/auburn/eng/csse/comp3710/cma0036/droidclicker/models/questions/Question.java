package edu.auburn.eng.csse.comp3710.cma0036.droidclicker.models.questions;

import java.util.ArrayList;

import edu.auburn.eng.csse.comp3710.cma0036.droidclicker.models.alternatives.Alternative;
import edu.auburn.eng.csse.comp3710.cma0036.droidclicker.models.alternatives.JsonAlternative;
import edu.auburn.eng.csse.comp3710.cma0036.droidclicker.models.quiz.JsonQuiz;

/**
 * Created by caioa_000 on 23/04/2015.
 */
public class Question {

    private String enunciate;
    private ArrayList<Object> extra;
    private ArrayList<Alternative> alternatives;
    private String id_right_alternative;
    private String id;

    public Question() {
    }

    public Question(String enunciate, ArrayList<Object> extra, ArrayList<Alternative> alternatives) {
        this.enunciate = enunciate;
        this.extra = extra;
        this.alternatives = alternatives;
    }

    public Question(String enunciate, ArrayList<Object> extra, ArrayList<Alternative> alternatives, String id_right_alternative, String id) {
        this.enunciate = enunciate;
        this.extra = extra;
        this.alternatives = alternatives;
        this.id_right_alternative = id_right_alternative;
        this.id = id;
    }

    public ArrayList<Object> getExtra() {
        return extra;
    }

    public void setExtra(ArrayList<Object> extra) {
        this.extra = extra;
    }

    public ArrayList<Alternative> getAlternatives() {
        return alternatives;
    }

    public void setAlternatives(ArrayList<Alternative> alternatives) {
        this.alternatives = alternatives;
    }

    public String getIdRightAlternative() {
        return id_right_alternative;
    }

    public void setIdRightAlternative(String id_right_alternative) {
        this.id_right_alternative = id_right_alternative;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getEnunciate() {
        return enunciate;
    }

    public void setEnunciate(String enunciate) {
        this.enunciate = enunciate;
    }


}
