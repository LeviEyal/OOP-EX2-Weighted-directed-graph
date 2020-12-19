package api;

import gameClient.Config;
import gameClient.FunctionForTests;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;

class DWGraph_DSTest {
    private Config con = new Config();
    private FunctionForTests _func = new FunctionForTests();
    private directed_weighted_graph _graph;
    private String _pathFolder = con.PATH_FOLDER;
    private String _fileGraph = con.GameGraph;
    private String _jsonGraph;
    private String[] _array = _func.getArrayOfScenariosPath();

    @Test
    void constructor() {
        int i = 0;
        for (String str : _array) {
            _jsonGraph = _func.readJsonFromFileAndGetAsString(str + _fileGraph);
            _graph = _func.graphJsonToGraph(_jsonGraph);
            assertNotNull(_graph);
        }
    }

    @Test
    void getNode() {

    }

    @Test
    void hasEdge() {
    }

    @Test
    void getEdge() {
    }

    @Test
    void addNode() {
    }

    @Test
    void connect() {
    }

    @Test
    void getV() {
    }

    @Test
    void getVByKey() {
    }

    @Test
    void getE() {
    }

    @Test
    void removeNode() {
    }

    @Test
    void removeEdge() {
    }

    @Test
    void nodeSize() {
    }

    @Test
    void edgeSize() {
    }

    @Test
    void getMC() {
    }

    @Test
    void testToString() {
    }
}