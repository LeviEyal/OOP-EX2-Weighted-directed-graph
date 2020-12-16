package gameClient;

import api.directed_weighted_graph;
import api.game_service;
import api.node_data;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

class Ex2Test {
    private Config con = new Config();
    private FunctionForTests _func = new FunctionForTests();
    private game_service _game;
    private directed_weighted_graph _graph;

    private List<Agent> _agents = new ArrayList<>();
    private List<Pokemon> _pokemons = new ArrayList<Pokemon>();
    private List<String> _info;
    private static HashMap<Integer, ArrayList<node_data>> paths = new HashMap<>();
    private HashMap<Integer, Pokemon> map = new HashMap<>();

    private String _pathFolder = con.PATH_FOLDER;
    private String _fileAgents = con.Agents;
    private String __fileGraph = con.GameGraph;
    private String __fileGame = con.Game;
    private String __filePokemons = con.Pokemons;
    private String _jsonAgents;
    private String _jsonGraph;
    private String _jsonPokemons;
    private String[] _array = _func.getArrayOfScenariosPath();
    private final long id = 1111111111;

    @Test
    void main() {
        int i = 0;
        for (String str : _array){
            _jsonAgents = _func.readJsonFromFileAndGetAsString(str + _fileAgents);
            _jsonGraph = _func.readJsonFromFileAndGetAsString(str + __fileGraph);
            _jsonPokemons = _func.readJsonFromFileAndGetAsString(str + __filePokemons);

            Ex2.main(new String[]{"" + id, "" + i++});
        }


    }

}