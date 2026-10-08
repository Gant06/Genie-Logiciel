import java.util.Scanner;

public class BankingController {
    public static void main(String[] args) {
        BankingService bankingService = new BankingService(new AccountRepository());

        Scanner scanner = new Scanner(System.in);
        
        while(true){
            System.out.println("Selectionnez une opération (D, R) : ");
            String operation = scanner.nextLine();
            
            if(operation.equalsIgnoreCase("END")){
                scanner.close();
                System.out.println("Fin du programme.");
                break;
            }

            System.out.println("Entrez le numéro de compte : ");
            String compte = scanner.next();
            System.out.println("Entrez le montant : ");
            double montant = scanner.nextDouble();

            if(operation.equalsIgnoreCase("R")){
                bankingService.retirer(montant, compte);
                System.out.println("Retrait effectué. Nouveau solde : " + bankingService.getSolde(compte));
            } else if(operation.equalsIgnoreCase("D")){
                bankingService.deposer(montant, compte);
                System.out.println("Dépôt effectué. Nouveau solde : " + bankingService.getSolde(compte));
            } else {
                System.out.println("Opération invalide.");
            }
        }
    }
}
