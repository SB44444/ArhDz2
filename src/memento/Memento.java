package memento;

import java.util.ArrayList;
import java.util.List;

/**
 * Сувенир (Memento)
 Шаблон памятки связан с предыдущими состояниями объекта.
 Этот шаблон используется, когда нужно сохранить какое-либо состояние объекта,
 на тот случай, если потребуется восстановить объект в это же состояние позже.
 Этот шаблон основан на работе трех классов, также известных как классы актеров (сувенир, создатель и хранитель)
 Объект Memento содержит состояние, которое мы хотим сохранить для последующего использования.
 Создатель объект создает и сохраняет состояния в Памятных объектах
 Хранитель заботится о процессе восстановления объекта.
 */

public class Memento {
    private String state;

    public Memento(String state) {
        this.state = state;
    }

    public String getState() {
        return state;
    }
}


class Originator {
    private String state;

    public void setState(String state) {
        this.state = state;
    }

    public String getState() {
        return state;
    }

    public Memento saveStateToMemento() {
        return new Memento(state);
    }

    public void getStateFromMemento(Memento memento) {
        state = memento.getState();
    }
}

class CareTaker {
    private List mementoList = new ArrayList<>();

    public void add(Memento memento) {
        mementoList.add(memento);
    }
    public Memento get(int index) {
        return (Memento) mementoList.get(index);
    }
}

class Main {
    public static void main(String[] args) {
        Originator originator = new Originator();
        CareTaker careTaker = new CareTaker();

        originator.setState("State 1 at " + System.currentTimeMillis());
        originator.setState("State 2 at " + System.currentTimeMillis());
        careTaker.add(originator.saveStateToMemento());

        originator.setState("State 3 at " + System.currentTimeMillis());
        careTaker.add(originator.saveStateToMemento());

        System.out.println("Current state: " + originator.getState());

        originator.getStateFromMemento(careTaker.get(0));
        System.out.println("First saved state: " + originator.getState());
        originator.getStateFromMemento(careTaker.get(1));
        System.out.println("Second saved state: " + originator.getState());
    }
}

