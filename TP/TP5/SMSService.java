package TP.TP5;

public class SMSService implements EventListener<Event> {
    public void onEvent(Event event) {
        System.out.println("SMS sent for the event: " + event);
    }
}