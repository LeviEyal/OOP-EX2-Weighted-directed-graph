package api;

import gameClient.Agent;
import gameClient.Pokemon;
import gameClient.graph_data;
import gameClient.util.Point3D;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class FunctionForTests {
    private Config con = new Config();
    public static HashMap<Integer, HashMap<Integer, graph_data>> _graphData = new HashMap<>();
    private directed_weighted_graph _graph;
    private List<Agent> _agents = new ArrayList<>();
    private List<Pokemon> _pokemons;
    private List<String> _info;
    private final dw_graph_algorithms _algo = new DWGraph_Algo();
    private String[] arrayOfScenariosPath = getArrayOfAllScenariosPath();


    //========================= CONSTRUCTORS ===========================

    public FunctionForTests(){
        arrayOfScenariosPath = getArrayOfAllScenariosPath();
    }


    //=========================== GETTERS & SETTERS ================================


    public static HashMap<Integer, HashMap<Integer, graph_data>> get_graphData() {
        return _graphData;
    }
    public directed_weighted_graph get_graph() {
        return _graph;
    }
    public List<Agent> get_agents() {
        return _agents;
    }
    public List<Pokemon> get_pokemons() {
        return _pokemons;
    }
    public List<String> get_info() {
        return _info;
    }
    public dw_graph_algorithms get_algo() {
        return _algo;
    }
    public String[] getArrayOfScenariosPath() {
        return arrayOfScenariosPath;
    }

    //=========================== GETTERS & SETTERS ================================

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
    public String getAllDataByTheGraph(directed_weighted_graph g) {
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
        return str;
    }
    public ArrayList<Pokemon> json2Pokemons(String json) {
        ArrayList<Pokemon> ans = new ArrayList<>();
        try {
            JSONObject jsonOb = new JSONObject(json);
            JSONArray ags = jsonOb.getJSONArray("Pokemons");
            for(int i=0; i<ags.length(); i++) {
                JSONObject pp = ags.getJSONObject(i);
                JSONObject pk = pp.getJSONObject("Pokemon");
                int t = pk.getInt("type");
                double v = pk.getDouble("value");
                String p = pk.getString("pos");
                Pokemon f = new Pokemon(new Point3D(p), t, v, 0, null);
                ans.add(f);
            }
        }
        catch (JSONException e) {
            e.printStackTrace();
        }
        ans.sort((o1, o2) -> {
            if(o1.getValue() > o2.getValue())
                return 1;
            else if(o1.getValue() < o2.getValue())
                return -1;
            else return 0;
        });
        return ans;
    }
    public static String readJsonFromFileAndGetAsString(String path) {
        String str = "";
        try {
            File file = new File(path);
            Scanner scanner = new Scanner(file, "UTF-8");
            while (scanner.hasNextLine()) {
                String data = scanner.nextLine();
                str += data;
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
        return str;
    }
    public String getPathAllScenario(int scenario) {
        return System.getProperty("user.dir") + "/tests/gameClient/scenarios/"+ scenario;
    }
    public String[] getArrayOfAllScenariosPath() {
        String [] array = new String[0];
        for (int i = 0; i < 24; i++) {
            array = Arrays.copyOf(array, array.length + 1);
            array[i] = getPathAllScenario(i);
        }
        return array;
    }
    public List<Agent> getAgents(String json, directed_weighted_graph g){
        ArrayList<Agent> ans = new ArrayList<>();
        try {
            JSONObject ttt = new JSONObject(json);
            JSONArray ags = ttt.getJSONArray("Agents");
            for(int i=0;i<ags.length();i++) {
                Random r = new Random();
                r.nextInt(g.nodeSize());
                //System.out.println(r.nextInt(g.nodeSize()));
                Agent c = new Agent(g ,r.nextInt(g.nodeSize()));
                ans.add(c);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return ans;
    }
    public directed_weighted_graph graphJsonToGraph(String json){
        dw_graph_algorithms ga = new DWGraph_Algo();
        ga.load("jsonsFiles/graph.json");
        return ga.getGraph();
    }
    private ArrayList<Pokemon> json2Pokemons2(String json) {
        ArrayList<Pokemon> ans = new ArrayList<>();
        try {
            JSONObject ttt = new JSONObject(json);
            JSONArray ags = ttt.getJSONArray("Pokemons");
            for(int i=0; i<ags.length(); i++) {
                JSONObject pp = ags.getJSONObject(i);
                JSONObject pk = pp.getJSONObject("Pokemon");
                int t = pk.getInt("type");
                double v = pk.getDouble("value");
                String p = pk.getString("pos");
                Pokemon f = new Pokemon(new Point3D(p), t, v, 0, null);
                ans.add(f);
            }
        }
        catch (JSONException e) {
            e.printStackTrace();
        }
        ans.sort((o1, o2) -> {
            if(o1.getValue() > o2.getValue())
                return 1;
            else if(o1.getValue() < o2.getValue())
                return -1;
            else return 0;
        });
        return ans;
    }
}