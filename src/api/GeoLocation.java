package api;

public class GeoLocation implements geo_location {

    private double x,y,z;

    public GeoLocation(double x, double y, double z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    @Override
    public double x() {
        return x;
    }

    @Override
    public double y() {
        return y;
    }

    @Override
    public double z() {
        return z;
    }

    @Override
    public double distance(geo_location g) {
        double t1 = Math.pow(x - g.x(),2);
        double t2 = Math.pow(y - g.y(),2);
        double t3 = Math.pow(z - g.z(),2);
        return Math.sqrt(t1 +t2 +t3);
    }
}