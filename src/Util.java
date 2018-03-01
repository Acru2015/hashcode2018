public class Util {
    public static int getDistance(Point a, Point b) {
        return Math.abs(a.getX() - b.getX()) + Math.abs(a.getY() - b.getY());
    }
}
