package api;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
class DWGraph_AlgoTest {

    static dw_graph_algorithms ga = new DWGraph_Algo();
    static directed_weighted_graph g1 = new DWGraph_DS();
    static directed_weighted_graph g2 = new DWGraph_DS();
    static directed_weighted_graph g3 = new DWGraph_DS();

    static void createG(int size, directed_weighted_graph g) {
        for(int i = 1; i <= size; i++){
            g.addNode(new NodeData(i));
        }
    }
    @BeforeAll
    static void setUp() {
        createG(10,g1);
        g1.connect(1,2,0.5);
        g1.connect(1,3,2.5);
        g1.connect(3,4,1.98);
        g1.connect(2,5,8.3);
        g1.connect(5,7,4.1);
        g1.connect(9,7,2.4);
        g1.connect(6,7,3.1);
        g1.connect(7,6,3.1);
        g1.connect(8,9,1.8);
        g1.connect(4,9,9.6);
        g1.connect(2,6,5.6);

        createG(10,g2);
        g2.connect(1,6,5);
        g2.connect(1,5,20);
        g2.connect(1,4,20);
        g2.connect(1,2,10);
        g2.connect(1,7,15);
        g2.connect(2,4,10);
        g2.connect(2,3,5);
        g2.connect(3,4,5);
        g2.connect(3,2,15);
        g2.connect(4,5,10);
        g2.connect(5,6,5);
        g2.connect(7,6,10);
        g2.connect(8,7,5);
        g2.connect(8,1,5);
        g2.connect(8,2,20);
        g2.connect(9,8,20);
        g2.connect(9,2,15);
        g2.connect(9,10,10);
        g2.connect(10,3,15);
        g2.connect(10,2,5);
    }

    @Test
    void isConnected() {
        assertFalse(ga.isConnected());
    }

    @Test
    void shortestPathDistGraph1() {
        ga.init(g1);
        assertEquals(5.6,ga.shortestPathDist(2,6));
        assertEquals(0.5,ga.shortestPathDist(1,2));
        assertEquals(4.48,ga.shortestPathDist(1,4));
        assertEquals(7.300000000000001,ga.shortestPathDist(8,6));
        assertEquals(-1.0,ga.shortestPathDist(9,4));
        assertEquals(0.0,ga.shortestPathDist(3,3));
        assertEquals(-1.0,ga.shortestPathDist(20,20));
        assertEquals(-1.0,ga.shortestPathDist(1,20));
    }
    @Test
    void shortestPathDistGraph2() {
        ga.init(g2);
        assertEquals(25.0,ga.shortestPathDist(2,6));
        assertEquals(10.0,ga.shortestPathDist(1,2));
        assertEquals(20.0,ga.shortestPathDist(1,4));
        assertEquals(10.0,ga.shortestPathDist(8,6));
        assertEquals(25.0,ga.shortestPathDist(9,4));
        assertEquals(25.0,ga.shortestPathDist(9,1));
        assertEquals(-1.0,ga.shortestPathDist(10,1));
        assertEquals(0.0,ga.shortestPathDist(3,3));
        assertEquals(-1.0,ga.shortestPathDist(20,20));
        assertEquals(-1.0,ga.shortestPathDist(1,20));
        assertEquals(5.0,ga.shortestPathDist(3,4));
        assertEquals(30.0,ga.shortestPathDist(10,6));
    }

    @Test
    void shortestPathGraph1() {
        ga.init(g1);
        assertEquals("[#4, #9, #7, #6]", ga.shortestPath(4,6).toString());
        assertEquals("[#1, #2, #6, #7]", ga.shortestPath(1,7).toString());
        assertEquals("[#3, #4, #9, #7, #6]", ga.shortestPath(3,6).toString());
        assertEquals("[#1]", ga.shortestPath(1,1).toString());
        assertEquals("[#2, #6]", ga.shortestPath(2,6).toString());
        assertEquals("[#4, #9, #7]", ga.shortestPath(4,7).toString());
        assertNull(ga.shortestPath(0,3));
        assertNull(ga.shortestPath(20,4));
        assertNull(ga.shortestPath(8,4));
        assertNull(ga.shortestPath(7,1));
    }
    @Test
    void shortestPathGraph2() {
        ga.init(g2);
        assertEquals("[#8, #1, #6]", ga.shortestPath(8,6).toString());
        assertEquals("[#8, #1, #5]", ga.shortestPath(8,5).toString());
        assertEquals("[#9, #2, #4, #5]", ga.shortestPath(9,5).toString());
        assertEquals("[#10, #2]", ga.shortestPath(10,2).toString());
        assertEquals("[#10]", ga.shortestPath(10,10).toString());
        assertEquals("[#10]", ga.shortestPath(10,10).toString());
        assertNull(ga.shortestPath(11,3));
        assertNull(ga.shortestPath(11,11));
        assertNull(ga.shortestPath(6,1));
        assertNull(ga.shortestPath(6,2));
        assertNull(ga.shortestPath(6,3));
        assertNull(ga.shortestPath(6,4));
        assertNull(ga.shortestPath(6,5));


    }

    @Test
    void saveAndLoad() {
        ga.save("e");
        dw_graph_algorithms new_ga = new DWGraph_Algo();
//        new_ga.load("e.json");
//        System.out.println(new_ga.getGraph());
    }
}