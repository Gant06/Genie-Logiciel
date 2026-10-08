public class RapportCommendement extends Rapport{
    @Override 
    public Document CreateDocument(){
        return new PDF();
    }
}