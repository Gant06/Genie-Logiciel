public class SortContext {
    private SortingStrategy strat;

    public void setStrat(SortingStrategy strat){
        this.strat = strat;
    }

    public int[] executeStrat(int[] nums){
        return strat.sort(nums);
    }
}