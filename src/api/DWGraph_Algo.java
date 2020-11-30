package api;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;

public class DWGraph_Algo implements dw_graph_algorithms {

    private directed_weighted_graph g;
    private static final int VISITED = 1;
    private static final int NOT_VISITED = 0;
    private static final double INFINITY = Double.POSITIVE_INFINITY;
    private HashMap<node_data,Double> tags = new HashMap<>();
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
        return new DWGraph_DS(g);
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

        for(node_data v : g.getV()){
            setAllTags(NOT_VISITED);
            BFS(v);
            for (node_data n : g.getV()) {
                if (n.getTag() == NOT_VISITED)
                    return false;
            }
        }
        return true;
    }

    private void BFS(node_data node) {
        node_data v = node;
        Queue<node_data> q = new LinkedList<>();
        v.setTag(VISITED);
        q.add(v);
        while (!q.isEmpty()) {
            v = q.remove();
            for (edge_data e : g.getE(v.getKey())) {
                node_data t = g.getNode(e.getDest());
                if (t.getTag() == NOT_VISITED) {
                    q.add(t);
                    t.setTag(VISITED);
                }
            }
        }
    }

    //Sets al nodes' tags value to a given integer number t
    private void setAllTags(double t) {
        for (node_data n : g.getV())
            tags.put(n, t);
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
        if (src == dest) return 0;
        node_data source = g.getNode(src);
        node_data destination = g.getNode(dest);
        if (source == null || destination == null)
            return -1;

        setAllTags(INFINITY);
        Queue<node_data> q = new PriorityQueue<>(new NodesComparator());
        tags.put(source, 0.0);
        q.add(source);
        while (!q.isEmpty()) {
            node_data v = q.poll();
            for (edge_data e : g.getE(v.getKey())) {
                node_data n = g.getNode(e.getDest());
                double w = g.getEdge(v.getKey(), n.getKey()).getWeight(); //weight (v<->n)
                double weightFromSrc = tags.get(v) + w;
                if (weightFromSrc < tags.get(n)) {
                    q.add(n);
                    tags.put(n, weightFromSrc);
                }
            }
        }
        double t = tags.get(destination);
        return (t == INFINITY)? -1 : t;
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
     * @param file_name - the file name (may include a relative path).
     * @return true - iff the file was successfully saved
     */
    @Override
    public boolean save(String file_name) {
        Gson gson = new Gson();
        String graph_string = gson.toJson(this.g);
        System.out.println(graph_string);
        try {
            Files.writeString(Path.of(file_name), graph_string, StandardCharsets.UTF_8);
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
        return true;
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
        Gson gson = new Gson();
        try {
            String in = Files.readString(Path.of(file));
            g = gson.fromJson(in, DWGraph_DS.class);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return false;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return true;
    }

    class NodesComparator implements Comparator<node_data>{
        @Override
        public int compare(node_data n1, node_data n2) {
            if(tags.get(n1) < tags.get(n2)){
                return -1;
            }
            else if(tags.get(n1) > tags.get(n2)){
                return 1;
            }
            return 0;
        }
    }
}