public class RapportApplication extends Rapport{
    @Override 
    public Document CreateDocument(){
        return new JSON();
    }
}