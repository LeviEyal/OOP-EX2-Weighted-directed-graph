package gameClient;

import api.directed_weighted_graph;
import api.node_data;

import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;

public class FunctionForTests {
    public static HashMap<Integer, HashMap<Integer, graph_data >> _graphData = new HashMap<>();

    public boolean createNewTextFileFromString(String str, String nameFile) {
        try {
            FileWriter myWriter = new FileWriter(nameFile + ".txt");
            myWriter.write(str);
            myWriter.close();
            System.out.println("Successfully wrote to the file "+ nameFile + ".txt" );
            return true;
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
            return false;
        }
    }
    public void getAllDataByTheGraph(directed_weighted_graph g) {
        for (node_data v : g.getV()){
            _graphData.put(v.getKey(),new HashMap<>());
        }
        for (node_data v : g.getV()){
            for (node_data ni : g.getV()){
                graph_data data = new graph_data(g, v.getKey(),ni.getKey());
                _graphData.get(v.getKey()).put(ni.getKey(), data);
            }
        }
        String str = "";
        for (node_data v : g.getV()){
            for (node_data ni : g.getV()){
                str += v.getKey() + " --> " + ni.getKey() + "\n";
                str += _graphData.get(v.getKey()).get(ni.getKey()).get_list() + "\n";
                str += _graphData.get(v.getKey()).get(ni.getKey()).get_path() + "\n\n";
            }
        }
        createNewTextFileFromString(str, "graphData");
    }
}