package api;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
class DWGraph_AlgoTest {

    static dw_graph_algorithms ga = new DWGraph_Algo();
    static directed_weighted_graph g1 = new DWGraph_DS();

    @BeforeAll
    static void setUp() {
        for(int i=0; i<10; i++){
            g1.addNode(new NodeData(i));
        }
        g1.connect(1,2,2);
        g1.connect(1,3,2);
        g1.connect(3,4,2);
        g1.connect(2,5,2);
        g1.connect(5,7,2);
        g1.connect(9,7,2);
        g1.connect(6,7,2);
        g1.connect(7,6,2);
        g1.connect(8,9,2);
        ga.init(g1);
        System.out.println(ga.getGraph());
    }

    @Test
    void isConnected() {
        assertFalse(ga.isConnected());
    }

    @Test
    void shortestPathDist() {
    }

    @Test
    void shortestPath() {
    }

    @Test
    void saveAndLoad() {
        ga.save("e.json");
        dw_graph_algorithms new_ga = new DWGraph_Algo();
        new_ga.load("e.json");
        System.out.println(new_ga.getGraph());
    }
}