package TP.TP5;
import java.awt.EventQueue;

public class EventPublisher {
    private EventQueue queue;

    public EventPublisher (){
        this.queue = new EventQueue();
    }

    public EventQueue listen(){
        return queue;
    }

    public void postEvent(Event event){
        queue.postEvent(event);
    }
}
