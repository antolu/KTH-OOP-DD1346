package key;

public class Key {
    private final int x;
    private final int y;

    public Key(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public boolean equals(Object o) {
        if (!(o instanceof Key)) return false;
        if (o == this) return true;
        Key key = (Key) o;
        if (this.x == key.x && this.y == key.y) return true;
        return false;
    }

    public int hashCode() {
        return 41 * x + y;
    }
}