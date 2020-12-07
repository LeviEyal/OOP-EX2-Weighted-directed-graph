package gameClient;

import Server.Game_Server_Ex2;
import api.*;
import gameClient.util.Point3D;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;

public class Ex2 implements Runnable{

    private static Config con = new Config();
    private static game_service _game;
    private static directed_weighted_graph _graph;
    private static dw_graph_algorithms _ga;
    private static GameGUI _gui;
    private static ArrayList<Agent> _agents = new ArrayList<>();
    private static ArrayList<Pokemon> _pokemons = new ArrayList<>();

    /**
     *  Parameters for file paths
     */
    private static String _gameServiceJSON;
    private static String _pokemonsJSON;
    private static String _agentsJSON;
    private static String _graphJSON;

    public static void main(String[] args) {
        Thread client = new Thread(new Ex2());
        client.start();
    }

    /**
     * run function for the Thread
     * */
    @Override
    public void run() {
        int id = 203249073;
        int scenario_num = 23;
        _game = Game_Server_Ex2.getServer(scenario_num);
        _game.login(id);
        _ga = new DWGraph_Algo();

        _gameServiceJSON = _game.toString();
        _pokemonsJSON = _game.getPokemons();
        _graphJSON = _game.getGraph();
        _agentsJSON = _game.getAgents();

        _ga.load(con.loadGraphFromJSON);
        _graph = _ga.getGraph();

        importPokemonsFromJSON();
        importAgentsFromJSON();

//        placeAgents();
//        drawGraph();

        drawGraph();
        _agentsJSON = _game.getAgents();

        exportJsonToFile(con.saveGraphToJSON,_game.getGraph());
        exportJsonToFile(con.game,_gameServiceJSON);
        exportJsonToFile(con.savePokemonsToJSON,_pokemonsJSON);
        exportJsonToFile("agents",_agentsJSON);

        System.out.println(_agentsJSON);
        System.out.println(_agents);
        System.out.println(_pokemons);

        _game.startGame();
    }

    /**
     * import Agents From JSON when when starting the game
     * @params: none
     * @return: none
     * */
    private void importAgentsFromJSON() {
        int startNode = 100;
        try{
            JSONObject object = new JSONObject(_gameServiceJSON);
            JSONObject gameserver = object.getJSONObject("GameServer");
            int numberOfAgents = gameserver.getInt("agents");
            for (int i = 0; i < numberOfAgents; i++) {
                Agent agent;
                try{
                    startNode = compareVertexForStartNode(i);
                    //startNode =_pokemons.get(i).get_edge().getDest();
                }catch(Exception e){
                    startNode = 0;
                }

                agent = new Agent(_graph, startNode);
                _agents.add(agent);
                _game.addAgent(startNode);
            }
        } catch (JSONException e) {
            System.out.println(e.toString());
        }
        System.out.println("startNode = " + startNode);

    }

    /**
     * import Pokemons From format of JSON and add it to _pokemons ArrayList
     * */
    private void importPokemonsFromJSON() {
        try {
            JSONObject ttt = new JSONObject(_pokemonsJSON);
            JSONArray ags = ttt.getJSONArray("Pokemons");
            for(int i=0;i<ags.length();i++) {
                JSONObject pp = ags.getJSONObject(i);
                JSONObject pk = pp.getJSONObject("Pokemon");
                int t = pk.getInt("type");
                double v = pk.getDouble("value");
                String p = pk.getString("pos");
                Pokemon f = new Pokemon(new Point3D(p), t, v, 0, null);
                _pokemons.add(f);
            }
        }
        catch (JSONException e) {
            System.out.println(e.toString());
        }
        _pokemons.sort(new Comparator<Pokemon>() {
            @Override
            /**
             * compare Vertex Of Edge By Key
             * @params: index of the edge in _agents
             * @return: 1 if src big then dest
             * -1 if dest big then src
             * 0 if there are equal
             * */
            public int compare(Pokemon o1, Pokemon o2) {
                if(o1.getValue() > o2.getValue())
                    return 1;
                else if(o1.getValue() < o2.getValue())
                    return -1;
                else return 0;
            }
        });
    }
    /**
     * draw Graph
     * */
    private void drawGraph() {

    }

    /**
     * compare Vertex Of Edge By Key
     * @params: index of the edge in _agents
     * @return: 1 if src big then dest
     * -1 if dest big then src
     * 0 if there are equal
     * */
    public int compareVertexOfEdgeByKey(int index) {
        if(_pokemons.get(index).get_edge().getSrc() > _pokemons.get(index).get_edge().getDest()){
            return 1;
        }else if(_pokemons.get(index).get_edge().getSrc() < _pokemons.get(index).get_edge().getDest()){
            return -1;
        }else{
            return 0;
        }
    }

    /**
     * compare Vertex For Start Node
     * @params: index of the edge in _agents
     * @return: 1 if src big then dest
     * -1 if dest big then src
     * 0 if there are equal
     * */
    public int compareVertexForStartNode(int index) {
        int bigKey = _pokemons.get(index).get_edge().getSrc();
        int smallKey = _pokemons.get(index).get_edge().getDest();
        int type = _pokemons.get(index).getType();
        System.out.println("bigKey = " + bigKey + ", smallKey = " + smallKey + ", type = " + type  );
        if(type == 1) {
            return smallKey;
        }else if(type == -1){
            return bigKey;
        }else{
            return smallKey;
        }
    }

    /**
     * export Json To a new file
     * @param1: nameFile of the new JSON file
     * @param2: Json the string of  the object
     * @return: -1 if dest big then src
     * 0 if there are equal
     * */
    private void exportJsonToFile(String nameFile, String Json) {
        try {
            File file = new File(con.JsonFolder + nameFile + ".json");
            FileWriter myWriter = new FileWriter(file);
            myWriter.write(Json);
            myWriter.close();
        }catch ( IOException e) {
            e.printStackTrace();
        }
    }
}
