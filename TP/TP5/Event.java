package TP.TP5;
import java.awt.AWTEvent;

public class Event extends AWTEvent {
    private String t;

    public Event(String type, int id){
        super(new Object(), id);
        this.t=type;
    }

    public String type(){
        return t;
    }
}
