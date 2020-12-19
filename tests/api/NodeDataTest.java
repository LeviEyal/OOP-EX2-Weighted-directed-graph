package api;


import org.junit.jupiter.api.Test;

import static org.junit.Assert.*;

class NodeDataTest {
    node_data[] arrayNodes;

    @Test
    void constructor() {
        arrayNodes = new NodeData[100];
        for (int i = 0; i < 100; i++) {
            arrayNodes[i] = new NodeData(i);
        }
        assertTrue(arrayNodes.length == 100 && arrayNodes[99] != null);
    }


    @Test
    void getKey() {
        arrayNodes = new NodeData[100];
        for (int i = 0; i < 100; i++) {
            arrayNodes[i] = new NodeData(i);
            assertTrue(arrayNodes[i].getKey() == i);
        }
    }

    @Test
    void getLocation() {
        arrayNodes = new NodeData[100];
        for (int i = 0; i < 100; i++) {
            arrayNodes[i] = new NodeData(i);
            geo_location g = new GeoLocation(i,i+1,i+2);
            arrayNodes[i].setLocation(g);
            assertTrue(arrayNodes[i].getLocation() == g);
        }
    }

    @Test
    void setLocation() {
        arrayNodes = new NodeData[100];
        for (int i = 0; i < 100; i++) {
            arrayNodes[i] = new NodeData(i);
            geo_location g = arrayNodes[i].getLocation();
            geo_location g2 = new GeoLocation(i,i+1,i+2);
            arrayNodes[i].setLocation(g2);
            assertEquals(arrayNodes[i].getLocation(), g2);
            assertNotEquals(arrayNodes[i].getLocation(),g);
        }
    }

    @Test
    void getWeight() {
        arrayNodes = new NodeData[100];
        for (int i = 0; i < 100; i++) {
            arrayNodes[i] = new NodeData(i);
            double weight = Double.MAX_VALUE;
            double getW = arrayNodes[i].getWeight();
            arrayNodes[i].setWeight(weight);

            assertTrue(arrayNodes[i].getWeight() == Double.MAX_VALUE);
            assertTrue(arrayNodes[i].getWeight() != getW);
        }
    }

    @Test
    void setWeight() {
        arrayNodes = new NodeData[100];
        for (int i = 0; i < 100; i++) {
            arrayNodes[i] = new NodeData(i);
            double getW = arrayNodes[i].getWeight();
            double weight = Double.MAX_VALUE;
            arrayNodes[i].setWeight(weight);
            assertTrue(arrayNodes[i].getWeight() == Double.MAX_VALUE);
            assertTrue(arrayNodes[i].getWeight() != getW);
        }
    }

    @Test
    void getInfo() {
        arrayNodes = new NodeData[100];
        for (int i = 0; i < 100; i++) {
            arrayNodes[i] = new NodeData(i);
            String str = "Boaz the egg and the brain fuck";
            arrayNodes[i].setInfo(str);
            assertFalse(arrayNodes[i].getInfo().equals(""));
            assertTrue(arrayNodes[i].getInfo().equals(str));

        }
    }

    @Test
    void setInfo() {
        arrayNodes = new NodeData[100];
        for (int i = 0; i < 100; i++) {
            arrayNodes[i] = new NodeData(i);
            String str = "Boaz the egg and the brain fuck";
            arrayNodes[i].setInfo(str);
            assertFalse(arrayNodes[i].getInfo().equals(""));
            assertTrue(arrayNodes[i].getInfo().equals(str));
        }
    }

    @Test
    void getTag() {
        arrayNodes = new NodeData[100];
        for (int i = 0; i < 100; i++) {
            arrayNodes[i] = new NodeData(i);
            int num = Integer.MAX_VALUE;
            arrayNodes[i].setTag(num);
            assertFalse(arrayNodes[i].getTag() == 0);
            assertTrue(arrayNodes[i].getTag() == num );
        }
    }

    @Test
    void setTag() {
        arrayNodes = new NodeData[100];
        for (int i = 0; i < 100; i++) {
            arrayNodes[i] = new NodeData(i);
            int num = Integer.MAX_VALUE;
            arrayNodes[i].setTag(num);
            assertFalse(arrayNodes[i].getTag() == 0);
            assertTrue(arrayNodes[i].getTag() == num );
        }
    }

    @Test
    void testToString() {
        arrayNodes = new NodeData[100];
        for (int i = 0; i < 100; i++) {
            arrayNodes[i] = new NodeData(i);
            assertTrue(arrayNodes[i].toString().equals("#"+i));
            assertFalse(arrayNodes[i].toString().equals("#"+(i+1)));
        }
    }
}