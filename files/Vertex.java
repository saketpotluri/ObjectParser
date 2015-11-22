package Trees;

/**
 * Created by odegaard on 11/21/15.
 */
public class Vertex implements Comparable<Vertex> {
    private double x;
    private double y;
    private double z;
    private double red;
    private double blue;
    private double green;

    public Vertex(double x, double y, double z, double red, double blue, double green) {
        this.x = x;
        this.y = y;
        this.z = z;
        this.red = red;
        this.blue = blue;
        this.green = green;
    }

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }

    public double getZ() {
        return z;
    }

    public void setZ(double z) {
        this.z = z;
    }

    public double getRed() {
        return red;
    }

    public void setRed(double red) {
        this.red = red;
    }

    public double getBlue() {
        return blue;
    }

    public void setBlue(double blue) {
        this.blue = blue;
    }

    public double getGreen() {
        return green;
    }

    public void setGreen(double green) {
        this.green = green;
    }

    public double getDiff() {
        return red - blue - green;
    }

    public double computeEuclidDistance(double idealRed, double idealGreen, double idealBlue) {
        double redDiff = Math.pow(idealRed - getRed(), 2);
        double blueDiff = Math.pow(idealGreen - getGreen(), 2);
        double greenDiff = Math.pow(idealBlue - getBlue(), 2);
        return Math.sqrt(redDiff + blueDiff + greenDiff);
    }

    @Override
    public int compareTo(Vertex vertex) {
        double diff1 = getDiff();
        double diff2 = vertex.getDiff();
        if(diff1 - diff2 < 0) {
            //current vertex is closer to ideal balance than the other vertex
            return -1;
        } else {
            return 1;
        }
    }
}
