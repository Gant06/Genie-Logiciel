public class RapportAplication extends Rapport{
    @Override 
    public Document CreateDocument(){
        return new JSON();
    }
}