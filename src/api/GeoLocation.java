package api;

import gameClient.util.Point3D;
/**
 * This interface represents a geo location <x,y,z>, aka Point3D
 */
public class GeoLocation implements geo_location {

    private Point3D p;

    /**
     * constructor - create new geo location
     * @params: x,y,z - aka Point3D
     * */
    public GeoLocation(double x, double y, double z) {
        p = new Point3D(x,y,z);
    }
    /**
     * return the value of x represent the location
     * @params: x - aka Point3D
     * */
    @Override
    public double x() {
        return p.x();
    }
    /**
     * return the value of y represent the location
     * @params: y - aka Point3D
     * */
    @Override
    public double y() {
        return p.y();
    }
    /**
     * return the value of z represent the location
     * @params: z - aka Point3D
     * */
    @Override
    public double z() {
        return p.z();
    }
    /**
     * return the distance of between other geo_location
     * @params: g - object data of geo location
     * */
    @Override
    public double distance(geo_location g) {
        double t1 = Math.pow(p.x() - g.x(),2);
        double t2 = Math.pow(p.y() - g.y(),2);
        double t3 = Math.pow(p.z() - g.z(),2);
        return Math.sqrt(t1 +t2 +t3);
    }

    @Override
    public String toString() {
        return "GeoLocation{p="+ p.x() +"," + p.y() +"," + p.z() +"}";
    }
}