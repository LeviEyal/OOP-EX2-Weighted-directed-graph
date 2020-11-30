package api;

public class NodeData implements node_data {

    private int key;
    private int tag;
    private String info;
    private geo_location location;
    private double weight;

    public NodeData() {
        key = -1;
        tag = 0;
        info = "";
        location = null;
        weight = 0;
    }

    public NodeData(int key) {
        this.key = key;
        tag = 0;
        info = "";
        location = null;
        weight = 0;
    }

    public NodeData(int key, int tag, String info, geo_location location, double weight) {
        this.key = key;
        this.tag = tag;
        this.info = info;
        this.location = location;
        this.weight = weight;
    }

    public NodeData(node_data o) {
        this(o.getKey(), o.getTag(), o.getInfo(), o.getLocation(), o.getWeight());
    }

    /**
     * Returns the key (id) associated with this node.
     *
     * @return
     */
    @Override
    public int getKey() {
        return key;
    }

    /**
     * Returns the location of this node, if
     * none return null.
     *
     * @return
     */
    @Override
    public geo_location getLocation() {
        return location;
    }

    /**
     * Allows changing this node's location.
     *
     * @param p - new new location  (position) of this node.
     */
    @Override
    public void setLocation(geo_location p) {
        location = p;
    }

    /**
     * Returns the weight associated with this node.
     *
     * @return
     */
    @Override
    public double getWeight() {
        return weight;
    }

    /**
     * Allows changing this node's weight.
     *
     * @param w - the new weight
     */
    @Override
    public void setWeight(double w) {
        weight = w;
    }

    /**
     * Returns the remark (meta data) associated with this node.
     *
     * @return
     */
    @Override
    public String getInfo() {
        return info;
    }

    /**
     * Allows changing the remark (meta data) associated with this node.
     *
     * @param s
     */
    @Override
    public void setInfo(String s) {
        info = s;
    }

    /**
     * Temporal data (aka color: e,g, white, gray, black)
     * which can be used be algorithms
     *
     * @return
     */
    @Override
    public int getTag() {
        return tag;
    }

    /**
     * Allows setting the "tag" value for temporal marking an node - common
     * practice for marking by algorithms.
     *
     * @param t - the new value of the tag
     */
    @Override
    public void setTag(int t) {
        tag = t;
    }

    @Override
    public String toString() {
        return "NodeData{" +
                "key=" + key +
                ", tag=" + tag +
                ", info='" + info + '\'' +
                ", location=" + location +
                ", weight=" + weight +
                '}';
    }
}
