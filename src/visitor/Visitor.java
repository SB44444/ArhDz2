package visitor;
/**
 * Посетитель (Visitor)
 * Шаблон посетителя используется для перемещения логики из каждого отдельного элемента группы в новый класс,
 * который выполняет для них операции, используя данные из каждого отдельного элемента.
 * Это делается путем того, что все элементы принимают “посетителя”. Этот посетитель будет выполнять изменения в отдельном классе,
 * не изменяя структуру посещаемого класса.
 * Это позволяет легко добавлять новые функциональные возможности без изменения посещаемых классов.
 * При этом объекты не обязательно должны быть одинаковыми и могут быть несвязанными, реализующими разные интерфейсы.
 * В примере реализации патерна каждый товар в магазине сможет "принять" посетителя.
 * Класс реализации товара имеет свою собственную логику расчета цены товаров, а не самих товаров.
*/

public interface Visitor {
    int visit(Pen pen);
    int visit(Notebook notebook);
}


class Pen implements Item {
    private int price;
    private String model;

    public Pen(int price, String model) {
        this.price = price;
        this.model = model;
    }

    public int getPrice() {
        return price;
    }

    public String getModel() {
        return model;
    }

    @Override
    public int accept(Visitor visitor) {
        return visitor.visit(this);
    }
}

class Notebook implements Item {
    private int price;
    private int numberOfPages;

    public Notebook(int price, int numberOfPages) {
        this.price = price;
        this.numberOfPages = numberOfPages;
    }

    public int getPrice() {
        return price;
    }

    public int getNumberOfPages() {
        return numberOfPages;
    }
    @Override
    public int accept(Visitor visitor) {
        return visitor.visit(this);
    }
}


class VisitorImpl implements Visitor {

    @Override
    public int visit(Pen pen) {
        int price = pen.getPrice();
        System.out.println(pen.getModel() + " Стоимость  " + price);
        return price;
    }

    @Override
    public int visit(Notebook notebook) {
        int price = 0;
        if(notebook.getNumberOfPages() > 250) {
            price = notebook.getPrice()-5;
        } else {
            price = notebook.getPrice();
        }
        System.out.println("Стоимость блокнота " + price);

        return price;
    }
}


class StackAbuseJavaDesignPatterns {
    public static void main(String[] args) {
        Item[] items = new Item[]{new Pen(10, "Parker"), new Pen(5, "Pilot"),
                new Notebook(50, 150), new Notebook(75, 300)};

        int total = getTotalPrice(items);
        System.out.println("Общая сумма по списку товаров: " + total);
    }

    private static int getTotalPrice(Item[] items) {
        Visitor visitor = new VisitorImpl();
        int result = 0;
        for(Item item : items) {
            result = result + item.accept(visitor);
        }
        return result;
    }
}
