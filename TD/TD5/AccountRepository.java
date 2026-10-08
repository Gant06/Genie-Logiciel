import java.util.HashMap;

public class AccountRepository {
    // Simulation bbdd avec une HashMap
    private HashMap<String, Double> comptes = new HashMap<>();


    public void stockerSolde(String compte, double solde) {
        // Simulation du code pour stocker le solde
        comptes.put(compte, solde);
    }

    public Double getSolde(String compte) {
        // Simulation du code pour récupérer le solde
        if (!comptes.containsKey(compte)) {
            comptes.put(compte, 0.0);
        }
        return comptes.get(compte);
    }
}