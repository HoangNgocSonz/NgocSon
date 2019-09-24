package game;

public class Rectangle {
    public Vector2D position;
    public int width;
    public int height;

    public Rectangle() {
        this(new Vector2D(0, 0), 1, 1);
    }

    public Rectangle(double x, double y, int width, int height) {
        this(new Vector2D(x, y), width, height);
    }

    public Rectangle(Vector2D position, int width, int height) {
        this.position = position;
        this.width = width;
        this.height = height;
    }

    /**
     * @return true if this rectangle intersects with the other rectangle
     * else return false
     * @param other: the rectangle need to check intersects with this rectangle
     * @return
     */

    public boolean intersects(Rectangle other) {
        // TODO: 1. remove default return statement and fill logic check intersects 'this' and 'other'
        return false;
    }

    public static void main(String[] args) {
        // TODO: 2. Run this file to test the result
        Rectangle rect1 = new Rectangle(0, 0, 5, 5);
        Rectangle rect2 = new Rectangle(3, 3, 5, 5);
        Rectangle rect3 = new Rectangle(6, 6, 5, 5);

        System.out.println(rect1.intersects(rect2) + " - " + true);
        System.out.println(rect2.intersects(rect3) + " - " + true);
        System.out.println(rect1.intersects(rect3) + " - " + false);
        // the result must be:
        // true - true
        // true - true
        // false - false
    }
}
