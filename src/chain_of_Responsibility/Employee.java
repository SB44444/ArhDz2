package chain_of_Responsibility;

/**Цепочка ответственности (Chain of Responsibility)
 Модель цепочка ответственности широко используется и принимается.
 Она определяет цепочку объектов, которые один за другим,  обрабатывают запрос,
 где каждый процесс в цепочке имеет свою логику обработки.
 Каждый  процесс определяет, кто должен продолжить обработку запроса следующим,
 и каждое из них имеет ссылку на следующий процесс.
 Удобно для разделения логики процессов.*/


public abstract class Employee {    // Создаем абстрактный класс:
    public static int PROGRAMER = 1;
    public static int LEAD_PROGRAMER = 2;
    public static int MANAGER = 3;

    protected int authorityLevel;

    protected Employee nextEmployee;

    public void setNextEmployee(Employee employee) {
        this.nextEmployee = employee;
    }

    public void doWork(int authorityLevel, String message) {
        if(this.authorityLevel <= authorityLevel) {
            write(message);
        }
        if(nextEmployee != null) {
            nextEmployee.doWork(authorityLevel, message);
        }
    }

    abstract protected void write(String message);
}

/**    Этот абстрактный класс содержит уровни полномочий для всех сотрудников.
 Программист занимает меньшее место в иерархии, чем ведущий программист, который, в свою очередь, находится ниже менеджера.
 Определена ссылка на следующего сотрудника.
 Определен общий метод для всех этих классов с проверкой полномочий.
 Если у определенного класса нет полномочий, он передает запрос следующему в цепочке ответственности.*/


class Programmer extends Employee {        // Расширение класса

    public Programmer(int authorityLevel) {
        this.authorityLevel = authorityLevel;
    }

    @Override
    protected void write(String message) {
        System.out.println("Programmer is working on project: " + message);
    }
}

class LeadProgrammer extends Employee {

    public LeadProgrammer(int authorityLevel) {
        this.authorityLevel = authorityLevel;
    }

    @Override
    protected void write(String message) {
        System.out.println("Lead programmer is working on project: " + message);
    }
}

class Manager extends Employee {

    public Manager(int authorityLevel) {
        this.authorityLevel = authorityLevel;
    }

    @Override
    protected void write(String message) {
        System.out.println("Manager is working on project: " + message);
    }
}

/** Каждый из этих блоков обеспечивает свою собственную логику обработки.*/

class Main {
    private static Employee getChainOfEmployees() {
        Employee programmer = new Programmer(Employee.PROGRAMER);
        Employee leadProgrammer = new LeadProgrammer(Employee.LEAD_PROGRAMER);
        Employee manager = new Manager(Employee.MANAGER);

        programmer.setNextEmployee(leadProgrammer);
        leadProgrammer.setNextEmployee(manager);

        return programmer;
    }

    public static void main(String[] args) {
        Employee employeeChain = getChainOfEmployees();

        employeeChain.doWork(Employee.PROGRAMER, "This is basic programming work.");
        employeeChain.doWork(Employee.LEAD_PROGRAMER, "This is marginally more sophisticated programming work.");
        employeeChain.doWork(Employee.MANAGER, "This is the work for a manager.");
    }
}
