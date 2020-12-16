package gameClient;


import Server.Game_Server_Ex2;
import api.directed_weighted_graph;
import api.dw_graph_algorithms;
import api.game_service;
import api.node_data;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

class ArenaTest {
    private FunctionForTests _func = new FunctionForTests();
    private String[] _array = _func.getArrayOfScenariosPath();
    private List<Pokemon> _pokemonsList;
    private Pokemon _pokemon ;
    private List<Agent> _agents = new ArrayList<>();
    private static final double EPS1 = 0.001, EPS2=EPS1*EPS1;
    private game_service _game;
    private directed_weighted_graph _graph;

    private List<Pokemon> _pokemons = new ArrayList<Pokemon>();
    private List<String> _info;
    private dw_graph_algorithms _algo;
    private static HashMap<Integer, ArrayList<node_data>> paths = new HashMap<>();
    private HashMap<Integer, Pokemon> map = new HashMap<>();

    private String _pathFolder = "/jsonsFiles";
    private String _fileAgents = "/GameAgents.json";
    private String __fileGraph = "/GameGraph.json";
    private String __fileGame = "/GameJSON.json";
    private String __filePokemons = "/GamePokemons.json";
    private final long id = 1111111111;
    @Test
    void constructor() {
        int i = 0;
        for (String str : _array){
            String jsonAgents = _func.readJsonFromFileAndGetAsString(str + _fileAgents);
            String jsonGraph = _func.readJsonFromFileAndGetAsString(str + __fileGraph);
            String jsonGame = _func.readJsonFromFileAndGetAsString(str + __fileGame);
            String jsonPokemons = _func.readJsonFromFileAndGetAsString(str + __filePokemons);


            _game = Game_Server_Ex2.getServer(i++);
            _game.login(id);

            Arena a = new Arena(_game);

            _graph = _func.graphJsonToGraph(jsonGraph);
            _agents = _func.getAgents(jsonAgents,_graph);
            _pokemons = _func.json2Pokemons(_game.getPokemons());
            _pokemons = _func.json2Pokemons(jsonPokemons);

            assertEquals((_info = a.get_info()).toString(),"[]");
            assertEquals(a.getGraph().toString(),_graph.toString());
            assertEquals(a.getPokemons(),_pokemons);
            //System.out.println(_pathFolder + _fileAgents);
            assertFalse(new File(_pathFolder + _fileAgents).isFile());
            assertFalse(new File(_pathFolder + __fileGraph).isFile());
            assertFalse(new File(_pathFolder + __fileGame).isFile());
            assertFalse(new File(_pathFolder + __filePokemons).isFile());
            break;
        }
    }

    @Test
    void initAgents() {
    }

    @Test
    void getAgents() {
    }

    @Test
    void updateEdge() {
    }

    @Test
    void w2f() {
    }

    @Test
    void jsonToAgents() {
    }

    @Test
    void testJsonToAgents() {
    }

    @Test
    void getPokemons() {
    }

    @Test
    void setPokemons() {
    }

    @Test
    void getGraph() {
    }

    @Test
    void setGraph() {
    }

    @Test
    void get_info() {
    }

    @Test
    void set_info() {
    }

    @Test
    void moveAgents() {
    }
}