package api;

public class NodeW {

    private double tag;
    private int key;

    public NodeW(double tag, int key) {
        this.tag = tag;
        this.key = key;
    }

    public double getTag() {
        return tag;
    }

    public void setTag(double tag) {
        this.tag = tag;
    }

    public int getKey() {
        return key;
    }

    public void setKey(int key) {
        this.key = key;
    }
}
