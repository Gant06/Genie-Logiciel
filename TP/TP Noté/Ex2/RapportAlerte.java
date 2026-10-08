public class RapportAlerte extends Rapport{
    @Override 
    public Document CreateDocument(){
        return new XML();
    }
}