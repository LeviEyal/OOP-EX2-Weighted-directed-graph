package api;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class DWGraph_Algo implements dw_graph_algorithms {

    private directed_weighted_graph g;
    private static final int VISITED = 1;
    private static final int NOT_VISITED = 0;
    /**
     * Init the graph on which this set of algorithms operates on.
     *
     * @param g
     */
    @Override
    public void init(directed_weighted_graph g) {
        this.g = g;
    }

    /**
     * Return the underlying graph of which this class works.
     *
     * @return
     */
    @Override
    public directed_weighted_graph getGraph() {
        return g;
    }

    /**
     * Compute a deep copy of this weighted graph.
     *
     * @return
     */
    @Override
    public directed_weighted_graph copy() {
        return null;
    }

    /**
     * Returns true if and only if (iff) there is a valid path from each node to each
     * other node. NOTE: assume directional graph (all n*(n-1) ordered pairs).
     *
     * @return
     */
    @Override
    public boolean isConnected() {
        if (g.nodeSize() == 0 || g.nodeSize() == 1) return true;

        setAllTags(NOT_VISITED);
        Queue<node_data> q = new LinkedList<>();
        node_data v = g.getV().iterator().next(); //pick some node
        v.setTag(VISITED);
        q.add(v);
        while (!q.isEmpty()) {
            v = q.remove();
            for (edge_data n : g.getE(v.getKey())) {
                node_data t = g.getNode(n.getDest());
                if (t.getTag() == NOT_VISITED) {
                    q.add(t);
                    t.setTag(VISITED);
                }
            }
        }
        for (node_data n : g.getV())
            if (n.getTag() == 0)
                return false;
        return true;
    }

    //Sets al nodes' tags value to a given integer number t
    private void setAllTags(int t) {
        for (node_data n : g.getV())
            n.setTag(t);
    }
    /**
     * returns the length of the shortest path between src to dest
     * Note: if no such path --> returns -1
     *
     * @param src  - start node
     * @param dest - end (target) node
     * @return
     */
    @Override
    public double shortestPathDist(int src, int dest) {
        return 0;
    }

    /**
     * returns the the shortest path between src to dest - as an ordered List of nodes:
     * src--> n1-->n2-->...dest
     * see: https://en.wikipedia.org/wiki/Shortest_path_problem
     * Note if no such path --> returns null;
     *
     * @param src  - start node
     * @param dest - end (target) node
     * @return
     */
    @Override
    public List<node_data> shortestPath(int src, int dest) {
        return null;
    }

    /**
     * Saves this weighted (directed) graph to the given
     * file name - in JSON format
     *
     * @param file - the file name (may include a relative path).
     * @return true - iff the file was successfully saved
     */
    @Override
    public boolean save(String file) {
        return false;
    }

    /**
     * This method load a graph to this graph algorithm.
     * if the file was successfully loaded - the underlying graph
     * of this class will be changed (to the loaded one), in case the
     * graph was not loaded the original graph should remain "as is".
     *
     * @param file - file name of JSON file
     * @return true - iff the graph was successfully loaded.
     */
    @Override
    public boolean load(String file) {
        return false;
    }
}