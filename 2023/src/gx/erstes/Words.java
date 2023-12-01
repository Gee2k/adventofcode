package gx.erstes;

public enum Words {
    ONE("ONE", 1),
    TWO("TWO", 2),
    THREE("THREE", 3),
    FOUR("FOUR", 4),
    FIVE("FIVE", 5),
    SIX("SIX", 6),
    SEVEN("SEVEN", 7),
    EIGHT("EIGHT", 8),
    NINE("NINE", 9),
    ZERO("ZERO", 0);

    String name;
    int value;

    Words(String name, int value) {
        this.name = name;
        this.value = value;
    }
}
