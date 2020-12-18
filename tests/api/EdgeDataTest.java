package api;

import org.junit.jupiter.api.Test;

import static org.junit.Assert.*;

class EdgeDataTest {
    edge_data[] arrayEdges = new edge_data[1000];


    @Test
    void constructor() {

        for (int i = 0; i < 1000; i++) {
            arrayEdges[i] = new EdgeData(i,i+1,Double.MAX_VALUE);
            assertNotNull(arrayEdges[i]);
        }
    }
    @Test
    void getSrc() {
        for (int i = 0; i < 1000; i++) {
            arrayEdges[i] = new EdgeData(i,i+1,Double.MAX_VALUE);
            assertTrue(arrayEdges[i].getSrc() == i);
            assertFalse(arrayEdges[i].getSrc() == (i+1));
        }
    }

    @Test
    void getDest() {
        for (int i = 0; i < 1000; i++) {
            arrayEdges[i] = new EdgeData(i,i+1,Double.MAX_VALUE);
            assertTrue(arrayEdges[i].getDest() == (i+1));
            assertFalse(arrayEdges[i].getDest() == i);
        }
    }

    @Test
    void getWeight() {
        for (int i = 0; i < 1000; i++) {
            arrayEdges[i] = new EdgeData(i,i+1,Double.MAX_VALUE);
            assertTrue(arrayEdges[i].getWeight() == Double.MAX_VALUE);
            arrayEdges[i] = new EdgeData(i,i+1,i);
            assertTrue(arrayEdges[i].getWeight() == i);
            assertFalse(arrayEdges[i].getWeight() == i+1);
        }
    }

    @Test
    void getInfo() {
        for (int i = 0; i < 1000; i++) {
            arrayEdges[i] = new EdgeData(i,i+1,Double.MAX_VALUE);
            arrayEdges[i].setInfo("Boaz the egg and the brain fuck");
            assertTrue(arrayEdges[i].getInfo().equals("Boaz the egg and the brain fuck"));
        }
    }

    @Test
    void setInfo() {
        for (int i = 0; i < 1000; i++) {
            arrayEdges[i] = new EdgeData(i,i+1,Double.MAX_VALUE);
            String str = arrayEdges[i].getInfo();
            arrayEdges[i].setInfo("Boaz the egg and the brain fuck");

            assertTrue(arrayEdges[i].getInfo().equals("Boaz the egg and the brain fuck"));
            assertFalse(arrayEdges[i].getInfo().equals(str));
        }
    }

    @Test
    void getTag() {
        for (int i = 0; i < 1000; i++) {
            arrayEdges[i] = new EdgeData(i,i+1,Double.MAX_VALUE);
            int get = arrayEdges[i].getTag();
            arrayEdges[i].setTag(Integer.MAX_VALUE);

            assertTrue(arrayEdges[i].getTag() == Integer.MAX_VALUE);
            assertFalse(arrayEdges[i].getTag() == get);
        }
    }

    @Test
    void setTag() {
        for (int i = 0; i < 1000; i++) {
            arrayEdges[i] = new EdgeData(i,i+1,Double.MAX_VALUE);
            int get = arrayEdges[i].getTag();
            arrayEdges[i].setTag(Integer.MAX_VALUE);

            assertTrue(arrayEdges[i].getTag() == Integer.MAX_VALUE);
            assertFalse(arrayEdges[i].getTag() == get);
        }
    }

    @Test
    void testToString() {
        for (int i = 0; i < 1000; i++) {
            arrayEdges[i] = new EdgeData(i,i+1,Double.MAX_VALUE);
            String str = "("+i+"->"+(i+1)+")w="+Double.MAX_VALUE;
            assertEquals(arrayEdges[i].toString(),str);
            assertFalse(arrayEdges[i].toString().equals("("+(i+1)+"->"+(i)+")w="+Double.MAX_VALUE));
        }
    }
}