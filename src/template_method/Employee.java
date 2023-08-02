package template_method;

/**
 * Метод шаблона (template method) часто называют методом Шаблон шаблона.
 * Это сводится к определению абстрактного класса, который предоставляет предопределенные способы запуска своих методов.
 * Подклассы, которые наследуют эти методы, также должны следовать пути, определенному в абстрактном классе.
 * В некоторых случаях абстрактный класс может уже включать реализацию метода, а не только инструкции,
 * если это функциональность, которая будет использоваться совместно всеми или большинством подклассов.
 * В компании у всех сотрудников есть несколько общих обязанностей: Выполнять обязанности, отдыхать, получать зарплату.
 * Сотрудники занимают разные должности и выполняют разные виды работ.
 */

public abstract class Employee {
    abstract void named();

    abstract void work();

    abstract void takePause();

    abstract void getPaid();

    public final void doWork() {
        named();
        work();
        takePause();
        getPaid();
    }
}

class Programmer extends Employee {
    @Override
    void named() {
        System.out.println("Должность Разработчик");
    }

    @Override
    void work() {
        System.out.println("Пишет код");
    }

    @Override
    void takePause() {
        System.out.println("Перерыв на час в написании кода");
    }

    @Override
    void getPaid() {
        System.out.println("Получает деньги за разработку проекта");
    }
}

class Manager extends Employee {
    @Override
    void named() {
        System.out.println("Должность Менеджер");
    }

    @Override
    void work() {
        System.out.println("Управление другими сотрудниками");
    }

    @Override
    void takePause() {
        System.out.println("Перерыв на час в управлении сотрудниками");
    }

    @Override
    void getPaid() {
        System.out.println("Получает деньги за выполненный проект");
    }
}

class Main {
    public static void main(String[] args) {
        Employee employee = new Programmer();
        employee.doWork();
        System.out.println();
        employee = new Manager();
        employee.doWork();
    }
}
