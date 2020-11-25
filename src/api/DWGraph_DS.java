package api;

import java.util.Collection;
import java.util.HashMap;

public class DWGraph_DS implements directed_weighted_graph {

    private HashMap<Integer, node_data> v = new HashMap<>();
    private HashMap<Integer, HashMap<Integer, edge_data>> e = new HashMap<>();
//    private HashMap<Integer, HashMap<Integer, node_data>> ni = new HashMap<>();
    private int nodeSize;
    private int edgeSize;
    private int mc;
    /**
     * returns the node_data by the node_id,
     *
     * @param key - the node_id
     * @return the node_data by the node_id, null if none.
     */
    @Override
    public node_data getNode(int key) {
        return v.get(key);
    }


    /**
     * return true iff (if and only if) there is an edge between node1 and node2
     * Note: this method should run in O(1) time.
     * check if has two Vertex in the graph by src && dest
     * check if has two Vertex in the edge by src && dest
     * if the two nodes are exist we check if to src has neighbor to dest && to dest has neighbor src
     * @param src key
     * @param dest key
     * @return true if are they neighbors from both sides, false if they don't have
     */
    public boolean hasEdge(int src, int dest) {
        return  v.containsKey(src) &&
                v.containsKey(dest) &&
                e.containsKey(src) &&
                e.containsKey(dest) &&
                e.get(src).containsKey(dest) &&
                e.get(dest).containsKey(src) &&
//                ni.containsKey(src) &&
//                ni.containsKey(dest) &&
//                ni.get(src).containsKey(dest) &&
//                ni.get(dest).containsKey(src) &&
                src != dest;
    }
    /**
     * returns the data of the edge (src,dest), null if none.
     * Note: this method should run in O(1) time.
     *
     * @param src
     * @param dest
     * @return
     */
    @Override
    public edge_data getEdge(int src, int dest) {
        return e.get(src).get(dest);
    }

    /**
     * adds a new node to the graph with the given node_data.
     * Note: this method should run in O(1) time.
     *
     * @param n
     */
    @Override
    public void addNode(node_data n) {
        int key = n.getKey();
        if(v.putIfAbsent(key,n) == null){
            e.put(key, new HashMap<>());
            nodeSize++;
            mc++;
        }
    }

    /**
     * Connects an edge with weight w between node src to node dest.
     * * Note: this method should run in O(1) time.
     *
     * @param src  - the source of the edge.
     * @param dest - the destination of the edge.
     * @param w    - positive weight representing the cost (aka time, price, etc) between src-->dest.
     */
    @Override
    public void connect(int src, int dest, double w) {
        if (src != dest) {
            e.get(src).putIfAbsent(dest ,new EdgeData(src,dest,w));
            //ni.get(src).putIfAbsent(v.get(dest).getKey() ,v.get(dest));
            edgeSize++;
            mc++;
        }
    }

    /**
     * This method returns a pointer (shallow copy) for the
     * collection representing all the nodes in the graph.
     * Note: this method should run in O(1) time.
     *
     * @return Collection<node_data>
     */
    @Override
    public Collection<node_data> getV() {
        return v.values();
    }

    /**
     * This method returns a pointer (shallow copy) for the
     * collection representing all the edges getting out of
     * the given node (all the edges starting (source) at the given node).
     * Note: this method should run in O(k) time, k being the collection size.
     *
     * @param src
     * @return Collection<edge_data>
     */
    @Override
    public Collection<edge_data> getE(int src) {
        return e.get(src).values();
    }

    /**
     * Deletes the node (with the given ID) from the graph -
     * and removes all edges which starts or ends at this node.
     * This method should run in O(k), V.degree=k, as all the edges should be removed.
     *
     * @param key
     * @return the data of the removed node (null if none).
     */
    @Override
    public node_data removeNode(int key) {
        if (v.containsKey(key)) {
            Collection<edge_data> t = getE(key);
            while (!t.isEmpty())
                removeEdge(key, t.iterator().next().getDest());
            mc++;
            nodeSize--;
            return v.remove(key);
        }
        return null;
    }

    /**
     * Deletes the edge from the graph,
     * Note: this method should run in O(1) time.
     *
     * @param src
     * @param dest
     * @return the data of the removed edge (null if none).
     */
    @Override
    public edge_data removeEdge(int src, int dest) {
        edge_data t = e.get(src).remove(dest);
        if(t != null) {
            edgeSize--;
            mc++;
        }
        return t;
    }

    /**
     * Returns the number of vertices (nodes) in the graph.
     * Note: this method should run in O(1) time.
     *
     * @return
     */
    @Override
    public int nodeSize() {
        return nodeSize;
    }

    /**
     * Returns the number of edges (assume directional graph).
     * Note: this method should run in O(1) time.
     *
     * @return
     */
    @Override
    public int edgeSize() {
        return edgeSize;
    }

    /**
     * Returns the Mode Count - for testing changes in the graph.
     *
     * @return
     */
    @Override
    public int getMC() {
        return mc;
    }
    /*********************************** EDGE DATA CLASS ***************************************/
    public static class EdgeData implements edge_data {

        private int src, dst;
        private double weight;
        private String info;
        private int tag;

        public EdgeData(int src, int dst, double weight) {
            this.src = src;
            this.dst = dst;
            this.weight = weight;
            this.info = "";
            this.tag = 0;
        }

        /**
         * The id of the source node of this edge.
         *
         * @return
         */
        @Override
        public int getSrc() {
            return src;
        }

        /**
         * The id of the destination node of this edge
         *
         * @return
         */
        @Override
        public int getDest() {
            return dst;
        }

        /**
         * @return the weight of this edge (positive value).
         */
        @Override
        public double getWeight() {
            return weight;
        }

        /**
         * Returns the remark (meta data) associated with this edge.
         *
         * @return
         */
        @Override
        public String getInfo() {
            return info;
        }

        /**
         * Allows changing the remark (meta data) associated with this edge.
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
         * This method allows setting the "tag" value for temporal marking an edge - common
         * practice for marking by algorithms.
         *
         * @param t - the new value of the tag
         */
        @Override
        public void setTag(int t) {
            tag = t;
        }
    }
    /*********************************** NODE DATA CLASS ***************************************/
    public static class NodeData implements node_data {

        private int key;
        private int tag;
        private String info;
        private geo_location location;
        private double weight;

        public NodeData(int key) {
            this.key = key;
            tag = 0;
            info = "";
            location = null;
            weight = 0;
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
}