package iterators;

/**
 * Итератор (Iterator)
 * Шаблон итератора используется в качестве основного шаблона во многих фреймворках,
 * включая Java Collection Framework. Он используется для доступа ко всем элементам темы коллекций,
 * скрывая базовую реализацию.
 * Пример итератора для печати имен сотрудников. Все сотрудники работают в своём отделе (interface Sector).
 * Таким образом, работа в секторе также включает итератор для всех из них
 */

public interface Iterator {
    public boolean hasNext();

    public Object next();
}
//  Этот итератор будет храниться в своего рода контейнере. В нашем случае это работа Сектор :

interface Sector {
    public Iterator getIterator();
}
//        Теперь давайте определим хранилище для наших сотрудников:

class EmployeeRepository implements Sector {
    public String[] employees = {"Леонид", "Евгений", "Сергей", "Яна", "Юлия"};

    @Override
    public Iterator getIterator() {
        return new EmployeeIterator();
    }

    private class EmployeeIterator implements Iterator {
        int index;

        @Override
        public boolean hasNext() {
            if (index < employees.length) {
                return true;
            }
            return false;
        }

        @Override
        public Object next() {
            if (this.hasNext()) {
                return employees[index++];
            }
            return null;
        }
    }
}

/**
 * Код выведет сптсок сотрудников
 */

class Main {
    public static void main(String[] args) {

        EmployeeRepository employeeRepository = new EmployeeRepository();

        for (Iterator iterator = employeeRepository.getIterator();
             iterator.hasNext(); ) {
            String employee = (String) iterator.next();
            System.out.println("Сотрудник: " + employee);
        }
    }
}


