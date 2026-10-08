package TP.TP5;

public class EmailService implements EventListener<Event> {
    public void onEvent(Event event) {
        System.out.println("EmailService sent for the event: " + event);
    }
}
