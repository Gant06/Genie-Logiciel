public class BankingService {
    private AccountRepository accountRepository;

    public BankingService(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    public void deposer(double montant, String compte) {
        // Code pour déposer le montant sur le compte spécifié
        double soldeActuel = accountRepository.getSolde(compte);
        accountRepository.stockerSolde(compte, soldeActuel + montant);
    }

    public void retirer(double montant, String compte) {
        // Code pour retirer le montant du compte spécifié
        double soldeActuel = accountRepository.getSolde(compte);
        if (soldeActuel < montant) {
            System.out.println("Solde insuffisant pour le retrait.");
            return;
        }
        accountRepository.stockerSolde(compte, soldeActuel - montant);
    }

    public double getSolde(String compte) {
        // Code pour récupérer le solde du compte spécifié
        return accountRepository.getSolde(compte);
    }
}