package digitalClock;

import java.util.ArrayList;
import java.util.List;

public class Subject {
    private List<Observer> itsObservers = new ArrayList<>();

    public void registerObserver(Observer observer) {
        itsObservers.add(observer);
    }

    public void notifyObservers() {
        for (Observer observer : itsObservers) {
            observer.update();
        }
    }
}
