import java.util.ArrayList;


public class WeatherStation {
    private ArrayList<Observer> ObserverList; 

    public WeatherStation(){
        this.ObserverList = new ArrayList<Observer>();
    }

    public void Subscribe(Observer ob){
        this.ObserverList.add(ob);
    }

    public void Remove(Observer ob){
        this.ObserverList.remove(ob);
    }

    public void updateTemperature(float temp){
        for(Observer ob : ObserverList){
            ob.update(temp);
        }
    }
}