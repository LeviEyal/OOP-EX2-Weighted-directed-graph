package api;

import gameClient.util.Point3D;

public class GeoLocation implements geo_location {

    private Point3D p;

    public GeoLocation(double x, double y, double z) {
        p = new Point3D(x,y,z);
    }

    @Override
    public double x() {
        return p.x();
    }

    @Override
    public double y() {
        return p.y();
    }

    @Override
    public double z() {
        return p.z();
    }

    @Override
    public double distance(geo_location g) {
        double t1 = Math.pow(p.x() - g.x(),2);
        double t2 = Math.pow(p.y() - g.y(),2);
        double t3 = Math.pow(p.z() - g.z(),2);
        return Math.sqrt(t1 +t2 +t3);
    }
}