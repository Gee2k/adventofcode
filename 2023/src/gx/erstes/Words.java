package gx.erstes;

public enum Words {
    ONE("one", 1),
    TWO("two", 2),
    THREE("three", 3),
    FOUR("four", 4),
    FIVE("five", 5),
    SIX("six", 6),
    SEVEN("seven", 7),
    EIGHT("eight", 8),
    NINE("nine", 9),
    ZERO("zero", 0);

    final String name;
    final int value;

    Words(String name, int value) {
        this.name = name;
        this.value = value;
    }
}
