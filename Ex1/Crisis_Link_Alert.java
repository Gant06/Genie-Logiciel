/**
 * Code de réfactorisé — Exercice 1 : principes SOLID.
 * Le programme respecte les principes solid OCP et ISP
 */
public class CrisisLink_Alert {

    public static void main(String[] args) {
        Alert alert = new Alert(
                "CRITICAL",
                "Évacuation immédiate du secteur Nord");

        AlertService service = new AlertService();
        // MODIFICATION OCP
        service.publish(alert, new SmsChannel());
        service.publish(alert, new SirenChannel());
        service.publish(alert, new PaperChannel());
    }
}

class Alert {
    private final String severity;
    private final String message;

    public Alert(String severity, String message) {
        this.severity = severity;
        this.message = message;
    }

    public String getSeverity() {
        return severity;
    }

    public String getMessage() {
        return message;
    }
}

//MODIFICATION ISP

interface CommunicationChannel {
    void send(Alert alert);
}

// Interfaces créees au cas óu on ajoute des nouvelles options avec ces methodes
// Si elles ne son pas utilisées a l'exterieur de la classe ne sont pas nécéssaires
interface Acknoledgable{
    void requestAcknowledgement(Alert alert);
}

interface Printable{
    public void printEvacuationMap(Alert alert);
}




class SmsChannel implements CommunicationChannel, Acknoledgable {
    @Override
    public void send(Alert alert) {
        System.out.println("SMS envoyé : " + alert.getMessage());
    }

    @Override
    public void requestAcknowledgement(Alert alert) {
        System.out.println("Accusé de réception demandé par SMS.");
    }
}

class SirenChannel implements CommunicationChannel {
    @Override
    public void send(Alert alert) {
        System.out.println("Sirène activée pour l'alerte " + alert.getSeverity());
    }
}

class PaperChannel implements CommunicationChannel, Printable {
    @Override
    public void send(Alert alert) {
        printEvacuationMap(alert);
    }

    @Override
    public void printEvacuationMap(Alert alert) {
        System.out.println("Alerte et carte d'évacuation imprimées : "
                + alert.getMessage());
    }
}

// MODIFICATION OCP

class AlertService {
    public void publish(Alert alert, CommunicationChannel channel) {
        channel.send(alert);
    }
}