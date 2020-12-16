package gameClient;

import api.*;
import gameClient.util.Point3D;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.jupiter.api.Test;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

class AgentTest {
    private FunctionForTests _func = new FunctionForTests();
    private String[] _array = _func.getArrayOfScenariosPath();
    private List<Pokemon> _pokemonsList;
    private String _fileAgents = "/GameAgents.json";
    private String __fileGraph = "/GameGraph.json";
    Pokemon _pokemon ;
    private List<Agent> _agents = new ArrayList<>();
    private directed_weighted_graph _graph;


    @Test
    void constructor() {
        for (String str : _array){
            String jsonAgents = _func.readJsonFromFileAndGetAsString(str + _fileAgents);
            String jsonGraph = _func.readJsonFromFileAndGetAsString(str + __fileGraph);
            _graph = _func.graphJsonToGraph(jsonGraph);
            _agents = _func.getAgents(jsonAgents,_graph);
            for(Agent a : _agents){
                Agent newA = new Agent(_graph, a.getSrcNode());
                assertEquals(a.getID(), newA.getID());
                assertEquals(a.getSrcNode(), newA.getSrcNode());
                assertEquals(a.getNextNode(), newA.getNextNode());
                assertEquals(a.getSpeed(), newA.getSpeed());
                assertEquals(a.getValue(), newA.getValue());
                assertEquals(a.get_curr_fruit(), newA.get_curr_fruit());
                assertEquals(a.getLocation(), newA.getLocation());
                assertEquals(a.get_curr_edge(), newA.get_curr_edge());
                assertEquals(a.get_sg_dt(), newA.get_sg_dt());
                assertEquals(a.getQ(), newA.getQ());
            }
        }
    }
    @Test
    void getSrcNode() {
        for (String str : _array){
            String jsonAgents = _func.readJsonFromFileAndGetAsString(str + _fileAgents);
            String jsonGraph = _func.readJsonFromFileAndGetAsString(str + __fileGraph);
            _graph = _func.graphJsonToGraph(jsonGraph);
            _agents = _func.getAgents(jsonAgents,_graph);
            for(Agent a : _agents) {
                int getSrcNode = a.getSrcNode();
                int node = 1;
                a.setCurrNode(_graph.getNode(node).getKey());
                assertEquals(a.getSrcNode(), node);
                assertNotEquals(a.getSrcNode(),0);
            }
        }
    }

    @Test
    void setCurrNode() {
        for (String str : _array){
            String jsonAgents = _func.readJsonFromFileAndGetAsString(str + _fileAgents);
            String jsonGraph = _func.readJsonFromFileAndGetAsString(str + __fileGraph);
            _graph = _func.graphJsonToGraph(jsonGraph);
            _agents = _func.getAgents(jsonAgents,_graph);
            for(Agent a : _agents) {
                Random r = new Random();
                int node = r.nextInt(_graph.nodeSize());
                int node2 = (node - 1 < 0) ? node + 1 : node - 1;
                //System.out.println("node = " + node + "  node2 =" + node2);
                a.setCurrNode(node);
                assertEquals(a.getSrcNode(), node);
                assertNotEquals(a.getSrcNode(),node2);
                //break;
            }
            //break;
        }
    }

    @Test
    void getID() {
        for (String str : _array){
            String jsonAgents = _func.readJsonFromFileAndGetAsString(str + _fileAgents);
            String jsonGraph = _func.readJsonFromFileAndGetAsString(str + __fileGraph);
            _graph = _func.graphJsonToGraph(jsonGraph);
            _agents = _func.getAgents(jsonAgents,_graph);
            for(Agent a : _agents) {
                int getId = a.getID();
                a.setID(getId+1);
                assertEquals(a.getID(), getId+1);
                assertNotEquals(a.getID(),getId);
            }
        }
    }

    @Test
    void setID() {
        for (String str : _array){
            String jsonAgents = _func.readJsonFromFileAndGetAsString(str + _fileAgents);
            String jsonGraph = _func.readJsonFromFileAndGetAsString(str + __fileGraph);
            _graph = _func.graphJsonToGraph(jsonGraph);
            _agents = _func.getAgents(jsonAgents,_graph);
            for(Agent a : _agents) {
                Random r = new Random();
                int node = r.nextInt(_graph.nodeSize());
                int node2 = (node - 1 < 0) ? node + 1 : node - 1;
                a.setID(node);
                assertEquals(a.getID(), node);
                assertNotEquals(a.getID(),node2);
            }
        }
    }

    @Test
    void getNextNode() {
        for (String str : _array){
            String jsonAgents = _func.readJsonFromFileAndGetAsString(str + _fileAgents);
            String jsonGraph = _func.readJsonFromFileAndGetAsString(str + __fileGraph);
            _graph = _func.graphJsonToGraph(jsonGraph);
            _agents = _func.getAgents(jsonAgents,_graph);
            for(Agent a : _agents) {
                int getId = a.getNextNode();
                a.setID(getId+1);
                assertEquals(a.getID(), getId+1);
                assertNotEquals(a.getID(),getId);
            }
        }
    }

    @Test
    void setNextNode() {
        for (String str : _array){
            String jsonAgents = _func.readJsonFromFileAndGetAsString(str + _fileAgents);
            String jsonGraph = _func.readJsonFromFileAndGetAsString(str + __fileGraph);
            _graph = _func.graphJsonToGraph(jsonGraph);
            _agents = _func.getAgents(jsonAgents,_graph);
            for(Agent a : _agents) {
                int src = a.getSrcNode();
                edge_data e = _graph.getEdge(src, src+1);
                if(e != null){
                    src = a.getSrcNode();
                    a.setNextNode(Integer.MAX_VALUE);
                    assertEquals(a.getSrcNode(),src);
                    a.setNextNode(src+1);
                    assertNotEquals(a.getSrcNode(),src+1);
                }
            }
        }

    }

    @Test
    void getSpeed() {
        for (String str : _array){
            String jsonAgents = _func.readJsonFromFileAndGetAsString(str + _fileAgents);
            String jsonGraph = _func.readJsonFromFileAndGetAsString(str + __fileGraph);
            _graph = _func.graphJsonToGraph(jsonGraph);
            _agents = _func.getAgents(jsonAgents,_graph);
            for(Agent a : _agents) {
                double speed = a.getSpeed();
                a.setSpeed(Double.MAX_VALUE);
                assertEquals(a.getSpeed(), Double.MAX_VALUE);
                assertNotEquals(a.getSpeed(),speed);
            }
        }
    }

    @Test
    void setSpeed() {
        for (String str : _array){
            String jsonAgents = _func.readJsonFromFileAndGetAsString(str + _fileAgents);
            String jsonGraph = _func.readJsonFromFileAndGetAsString(str + __fileGraph);
            _graph = _func.graphJsonToGraph(jsonGraph);
            _agents = _func.getAgents(jsonAgents,_graph);
            for(Agent a : _agents) {
                double speed = a.getSpeed();
                a.setSpeed(Double.MAX_VALUE);
                assertEquals(a.getSpeed(), Double.MAX_VALUE);
                assertNotEquals(a.getSpeed(),speed);
            }
        }
    }

    @Test
    void getValue() {
        for (String str : _array){
            String jsonAgents = _func.readJsonFromFileAndGetAsString(str + _fileAgents);
            String jsonGraph = _func.readJsonFromFileAndGetAsString(str + __fileGraph);
            _graph = _func.graphJsonToGraph(jsonGraph);
            _agents = _func.getAgents(jsonAgents,_graph);
            for(Agent a : _agents) {
                double value = a.getValue();
                a.setMoney(Double.MAX_VALUE);
                assertEquals(a.getValue(), Double.MAX_VALUE);
                assertNotEquals(a.getValue(),value);
            }
        }
    }

    @Test
    void setMoney() {
        for (String str : _array){
            String jsonAgents = _func.readJsonFromFileAndGetAsString(str + _fileAgents);
            String jsonGraph = _func.readJsonFromFileAndGetAsString(str + __fileGraph);
            _graph = _func.graphJsonToGraph(jsonGraph);
            _agents = _func.getAgents(jsonAgents,_graph);
            for(Agent a : _agents) {
                Random r = new Random();
                double value = a.getValue();
                double newValue = r.nextDouble();
                a.setMoney(newValue);
                assertEquals(a.getValue(), newValue);
                assertNotEquals(a.getValue(),value);
            }
        }
    }

    @Test
    void get_curr_fruit() {
        for (String str : _array){
            String jsonAgents = _func.readJsonFromFileAndGetAsString(str + _fileAgents);
            String jsonGraph = _func.readJsonFromFileAndGetAsString(str + __fileGraph);
            _graph = _func.graphJsonToGraph(jsonGraph);
            _agents = _func.getAgents(jsonAgents,_graph);
            for(Agent a : _agents) {
                Pokemon p = a.get_curr_fruit();
                Point3D point = new Point3D(Integer.MAX_VALUE,Integer.MIN_VALUE,Integer.MAX_VALUE);
                _pokemon = new Pokemon(point,100,Integer.MIN_VALUE,Integer.MIN_VALUE,new EdgeData(-10,-10,0.0));
                a.set_curr_fruit(_pokemon);
                assertEquals(a.get_curr_fruit(), _pokemon);
                assertNotEquals(a.get_curr_fruit(),p);
            }
        }
    }

    @Test
    void set_curr_fruit() {
        for (String str : _array){
            String jsonAgents = _func.readJsonFromFileAndGetAsString(str + _fileAgents);
            String jsonGraph = _func.readJsonFromFileAndGetAsString(str + __fileGraph);
            _graph = _func.graphJsonToGraph(jsonGraph);
            _agents = _func.getAgents(jsonAgents,_graph);
            for(Agent a : _agents) {
                Pokemon p = a.get_curr_fruit();
                Point3D point = new Point3D(Integer.MAX_VALUE,Integer.MIN_VALUE,Integer.MAX_VALUE);
                _pokemon = new Pokemon(point,100,Integer.MIN_VALUE,Integer.MIN_VALUE,new EdgeData(-10,-10,0.0));
                a.set_curr_fruit(_pokemon);
                assertEquals(a.get_curr_fruit(), _pokemon);
                assertNotEquals(a.get_curr_fruit(),p);
            }
        }
    }

    @Test
    void getLocation() {
        for (String str : _array){
            String jsonAgents = _func.readJsonFromFileAndGetAsString(str + _fileAgents);
            String jsonGraph = _func.readJsonFromFileAndGetAsString(str + __fileGraph);
            _graph = _func.graphJsonToGraph(jsonGraph);
            _agents = _func.getAgents(jsonAgents,_graph);
            for(Agent a : _agents) {
                geo_location l = a.getLocation();
                geo_location newL = new GeoLocation(Integer.MAX_VALUE,Integer.MAX_VALUE,Integer.MAX_VALUE);
                a.setLocation(newL);
                assertEquals(a.getLocation(), newL);
                assertNotEquals(a.getLocation(),l);
            }
        }
    }

    @Test
    void setLocation() {
        for (String str : _array){
            String jsonAgents = _func.readJsonFromFileAndGetAsString(str + _fileAgents);
            String jsonGraph = _func.readJsonFromFileAndGetAsString(str + __fileGraph);
            _graph = _func.graphJsonToGraph(jsonGraph);
            _agents = _func.getAgents(jsonAgents,_graph);
            for(Agent a : _agents) {
                geo_location l = a.getLocation();
                geo_location newL = new GeoLocation(Integer.MAX_VALUE,Integer.MAX_VALUE,Integer.MAX_VALUE);
                a.setLocation(newL);
                assertEquals(a.getLocation(), newL);
                assertNotEquals(a.getLocation(),l);
            }
        }
    }

    @Test
    void get_curr_edge() {
        for (String str : _array){
            String jsonAgents = _func.readJsonFromFileAndGetAsString(str + _fileAgents);
            String jsonGraph = _func.readJsonFromFileAndGetAsString(str + __fileGraph);
            _graph = _func.graphJsonToGraph(jsonGraph);
            _agents = _func.getAgents(jsonAgents,_graph);
            for(Agent a : _agents) {
                edge_data e = a.get_curr_edge();
                edge_data newE = new EdgeData(Integer.MAX_VALUE,Integer.MAX_VALUE,Double.MAX_VALUE);
                a.set_curr_edge(newE);
                assertEquals(a.get_curr_edge(), newE);
                assertNotEquals(a.get_curr_edge(),e);
            }
        }
    }
/*
    @Test
    void get_sg_dt() {
        for (String str : _array){
            String jsonAgents = _func.readJsonFromFileAndGetAsString(str + _fileAgents);
            String jsonGraph = _func.readJsonFromFileAndGetAsString(str + __fileGraph);
            _graph = _func.graphJsonToGraph(jsonGraph);
            _agents = _func.getAgents(jsonAgents,_graph);
            for(Agent a : _agents) {
                Random r = new Random();
                int node = r.nextInt(_graph.nodeSize());
                int node2 = (node - 1 < 0) ? node + 1 : node - 1;
                a.setID(node);
                assertEquals(a.getID(), node);
                assertNotEquals(a.getID(),node2);
            }
        }
    }
/*
    @Test
    void set_sg_dt() {
        for (String str : _array){
            String jsonAgents = _func.readJsonFromFileAndGetAsString(str + _fileAgents);
            String jsonGraph = _func.readJsonFromFileAndGetAsString(str + __fileGraph);
            _graph = _func.graphJsonToGraph(jsonGraph);
            _agents = _func.getAgents(jsonAgents,_graph);
            for(Agent a : _agents) {
                Random r = new Random();
                int node = r.nextInt(_graph.nodeSize());
                int node2 = (node - 1 < 0) ? node + 1 : node - 1;
                a.setID(node);
                assertEquals(a.getID(), node);
                assertNotEquals(a.getID(),node2);
            }
        }
    }

    @Test
    void set_SDT() {
        for (String str : _array){
            String jsonAgents = _func.readJsonFromFileAndGetAsString(str + _fileAgents);
            String jsonGraph = _func.readJsonFromFileAndGetAsString(str + __fileGraph);
            _graph = _func.graphJsonToGraph(jsonGraph);
            _agents = _func.getAgents(jsonAgents,_graph);
            for(Agent a : _agents) {
                Random r = new Random();
                int node = r.nextInt(_graph.nodeSize());
                int node2 = (node - 1 < 0) ? node + 1 : node - 1;
                a.setID(node);
                assertEquals(a.getID(), node);
                assertNotEquals(a.getID(),node2);
            }
        }
    }
*/

    @Test
    void getQ() {
        for (String str : _array){
            String jsonAgents = _func.readJsonFromFileAndGetAsString(str + _fileAgents);
            String jsonGraph = _func.readJsonFromFileAndGetAsString(str + __fileGraph);
            _graph = _func.graphJsonToGraph(jsonGraph);
            _agents = _func.getAgents(jsonAgents,_graph);
            Queue<node_data> newQueue = new LinkedList<>();
            for (int i = 0; i < 100; i++)
                newQueue.add(new NodeData(i));
            for(Agent a : _agents) {
                Queue<node_data> queue = a.getQ();
                a.setQ(newQueue);
                assertEquals(a.getQ(), newQueue);
                assertNotEquals(a.getQ(),queue);
            }
        }
    }

    @Test
    void setQ() {
        for (String str : _array){
            String jsonAgents = _func.readJsonFromFileAndGetAsString(str + _fileAgents);
            String jsonGraph = _func.readJsonFromFileAndGetAsString(str + __fileGraph);
            _graph = _func.graphJsonToGraph(jsonGraph);
            _agents = _func.getAgents(jsonAgents,_graph);
            Queue<node_data> newQueue = new LinkedList<>();
            for (int i = 0; i < 100; i++)
                newQueue.add(new NodeData(i));
            for(Agent a : _agents) {
                Queue<node_data> queue = a.getQ();
                a.setQ(newQueue);
                assertEquals(a.getQ(), newQueue);
                assertNotEquals(a.getQ(),queue);
            }
        }
    }

    @Test
    void update() {
        for (String str : _array){
            String jsonAgents = _func.readJsonFromFileAndGetAsString(str + _fileAgents);
            String jsonGraph = _func.readJsonFromFileAndGetAsString(str + __fileGraph);
            _graph = _func.graphJsonToGraph(jsonGraph);
            //_agents = _func.getAgents(jsonAgents,_graph);
            try {
                JSONObject ttt = new JSONObject(jsonAgents);
                JSONArray ags = ttt.getJSONArray("Agents");
                for(int i = 0; i < ags.length(); i++) {
                    Agent c = new Agent(_graph ,0);
                    String ans = "{\"Agent\":{"
                            + "\"id\":"+0+","
                            + "\"value\":"+0.0+","
                            + "\"src\":"+9+","
                            + "\"dest\":"+-1+","
                            + "\"speed\":"+1.0+","
                            + "\"pos\":\"35.19597880064568,32.10154696638656,0.0\""
                            + "}"
                            + "}";
                    c.update(ans);
                    assertEquals(c.toString(),ans);
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }



    }


    @Test
    void isMoving() {
        for (String str : _array){
            String jsonAgents = _func.readJsonFromFileAndGetAsString(str + _fileAgents);
            String jsonGraph = _func.readJsonFromFileAndGetAsString(str + __fileGraph);
            _graph = _func.graphJsonToGraph(jsonGraph);
            try {
                JSONObject ttt = new JSONObject(jsonAgents);
                JSONArray ags = ttt.getJSONArray("Agents");
                for(int i = 0; i < ags.length(); i++) {
                    Agent c = new Agent(_graph ,0);
                    assertFalse(c.isMoving());
                    //assertFalse(c.isMoving());
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }


    @Test
    void toString1() {
        for (String str : _array){
            String jsonAgents = _func.readJsonFromFileAndGetAsString(str + _fileAgents);
            String jsonGraph = _func.readJsonFromFileAndGetAsString(str + __fileGraph);
            _graph = _func.graphJsonToGraph(jsonGraph);
            //_agents = _func.getAgents(jsonAgents,_graph);
            try {
                JSONObject ttt = new JSONObject(jsonAgents);
                JSONArray ags = ttt.getJSONArray("Agents");
                for(int i = 0; i < ags.length(); i++) {
                    Agent c = new Agent(_graph ,0);
                    String ans = "{\"Agent\":{"
                            + "\"id\":"+0+","
                            + "\"value\":"+0.0+","
                            + "\"src\":"+9+","
                            + "\"dest\":"+-1+","
                            + "\"speed\":"+1.0+","
                            + "\"pos\":\"35.19597880064568,32.10154696638656,0.0\""
                            + "}"
                            + "}";
                    c.update(ans);
                    assertEquals(c.toString(),ans);
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }

}