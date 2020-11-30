package api;

import java.util.Collection;
import java.util.HashMap;

public class DWGraph_DS implements directed_weighted_graph {

    private HashMap<Integer, node_data> V = new HashMap<>();
    private HashMap<Integer, HashMap<Integer, edge_data>> E = new HashMap<>();
    //    private HashMap<Integer, HashMap<Integer, node_data>> ni = new HashMap<>();
    private int nodeSize;
    private int edgeSize;
    private int mc;

    /************************ CONSTRUCTORS *************************/
    public DWGraph_DS() {
        nodeSize = 0;
        edgeSize = 0;
        mc = 0;
    }

    public DWGraph_DS(directed_weighted_graph other){
        for (node_data v : other.getV()){
            node_data newNode = new NodeData(v);
            this.addNode(newNode);
        }
        for (node_data v : other.getV()){
            for (edge_data ni : other.getE(v.getKey())){
                connect(v.getKey(), ni.getDest(), ni.getWeight());
            }
        }
        this.nodeSize = other.nodeSize();
        this.edgeSize = other.edgeSize();
    }

    /* ********************* CLASS METHODS ********************** */

    /**
     * returns the node_data by the node_id,
     *
     * @param key - the node_id
     * @return the node_data by the node_id, null if none.
     */
    @Override
    public node_data getNode(int key) {
        return V.get(key);
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
        return  V.containsKey(src) &&
                V.containsKey(dest) &&
                E.containsKey(src) &&
                E.containsKey(dest) &&
                E.get(src).containsKey(dest) &&
                E.get(dest).containsKey(src) &&
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
        return E.get(src).get(dest);
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
        if(V.putIfAbsent(key,n) == null){
            E.put(key, new HashMap<>());
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
            E.get(src).putIfAbsent(dest ,new EdgeData(src,dest,w));
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
    public Collection<node_data> getV(int key) {
        return V.values();
    }

    /**
     * This method returns a pointer (shallow copy) for the
     * collection representing all the edges getting out of
     * the given node (all the edges starting (source) at the given node).
     * Note: this method should run in O(k) time, k being the collection size.
     * @param src
     * @return Collection<edge_data>
     */
    @Override
    public Collection<edge_data> getE(int src) {
        return E.get(src).values();
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
        if (V.containsKey(key)) {
            Collection<edge_data> t = getE(key);
            while (!t.isEmpty())
                removeEdge(key, t.iterator().next().getDest());
            mc++;
            nodeSize--;
            return V.remove(key);
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
        edge_data t = E.get(src).remove(dest);
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

    /**
     * Returns a string representation of this graph as an adjacency list.
     * @return A string representation of this graph
     */
    @Override
    public String toString() {
        String s = "Vertices: " + nodeSize() + " Edges: " + edgeSize() + " MC: " + getMC() + "\n";
        for (int key : V.keySet()) {
            s += key + ": ";
            for (edge_data e : getE(key)) {
                s += e;
            }
            s += "\n";
        }
        return s;
    }
}
