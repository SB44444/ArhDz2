package strategies;

/**
 * Стратегия (Strategy)
 * Шаблон стратегии используется когда алгоритмы или поведение класса должны быть изменяемыми на основе входных данных.
 * Целевой класс реализует свои алгоритмы и поведение на основе этих стратегий.
 * Эта стратегия будет использоваться для строительства различных типов зданий в разных местах.
 * Эти типы зданий реализуют стратегию каждый по – своему.
 * Создаём интерфейс, доступный для другого поведения, и классы, которые его реализуют только на некоторых объектах
 */

public interface Strategy {
    String build(String location);
}

class Skyscraper implements Strategy {
    @Override
    public String build(String location) {
        return "Строительство небоскреба" + location + " города.";
    }
}

class House implements Strategy {
    @Override
    public String build(String location) {
        return "Строю дом" + location + " города.";
    }
}

class Mall implements Strategy {
    @Override
    public String build(String location) {
        return "Строительство торгового центра" + location + " города.";
    }
}

class BuildContext {
    private final Strategy strategy;

    public BuildContext(Strategy strategy) {
        this.strategy = strategy;
    }

    public String executeStrategy(String location) {
        return strategy.build(location);
    }
}

class Main {
    public static void main(String[] args) {
        BuildContext buildContext = new BuildContext(new Skyscraper());
        System.out.println("Запрос на строительство небоскреба: " + buildContext.executeStrategy(" в спальном районе"));

        buildContext = new BuildContext(new House());
        System.out.println("Запрос на загородный дом: " + buildContext.executeStrategy(" на окраине"));

        buildContext = new BuildContext(new Mall());
        System.out.println("Запрос на строительство торгового центра: " + buildContext.executeStrategy(" в центре"));
    }
}
