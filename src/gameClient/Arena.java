package gameClient;

import api.*;
import gameClient.util.Point3D;
import gameClient.util.Range;
import gameClient.util.Range2D;
import gameClient.util.Range2Range;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

/**
 * This class represents a multi Agents Arena which move on a graph - grabs Pokemons and avoid the Zombies.
 * @author boaz.benmoshe
 *
 */
public class Arena {

	private game_service _game;
	public static final double EPS1 = 0.001, EPS2=EPS1*EPS1;
	private directed_weighted_graph _graph;
	private List<Agent> _agents = new ArrayList<>();
	private List<Pokemon> _pokemons;
	private List<String> _info;

	//========================= CONSTRUCTORS ===========================

	public Arena(game_service game) {
		_info = new ArrayList<>();
		_game = game;
		_graph = graphJsonToGraph(game.getGraph());
		_pokemons = json2Pokemons(game.getPokemons());
		_agents = JsonToAgents(game.toString());

		exportJsonToFile("GameJSON", game.toString());
		exportJsonToFile("GameGraph", game.getGraph());
		exportJsonToFile("GameAgents", game.getAgents());
		exportJsonToFile("GamePokemons", game.getPokemons());
	}

	//========================== JSON CONVERTING ==============================

	private ArrayList<Agent> JsonToAgents(String json) {
		ArrayList<Agent> ans = new ArrayList<>();
		try{

			JSONObject object = new JSONObject(json);
			JSONObject game_server = object.getJSONObject("GameServer");
			int numOfAgents = game_server.getInt("agents");

//			JSONObject ttt = new JSONObject(json);
			JSONArray ags = object.getJSONArray("Agents");

//			for (int i = 0; i < ags.length(); i++) {
			for (int i = 0; i < numOfAgents; i++) {
				Agent agent;
				int startNode;
				try{
					startNode = _pokemons.get(i).get_edge().getDest();
				}catch(Exception e){
					startNode = 0;
				}
				agent = new Agent(_graph, startNode);
				agent.update(ags.get(i).toString());
				ans.add(agent);
				_game.addAgent(startNode);
			}
		} catch (JSONException e) {
			e.printStackTrace();
		}

		return ans;
	}
	private ArrayList<Agent> JsonToAgents2(String json) {
		ArrayList<Agent> ans = new ArrayList<>();
		try{

//			JSONObject object = new JSONObject(json);
//			JSONObject game_server = object.getJSONObject("GameServer");
//			int numOfAgents = game_server.getInt("agents");

			JSONObject ttt = new JSONObject(json);
			JSONArray ags = ttt.getJSONArray("Agents");

			for (int i = 0; i < ags.length(); i++) {
//			for (int i = 0; i < numOfAgents; i++) {
				Agent agent;
				int startNode;
				try{
					startNode = _pokemons.get(i).get_edge().getDest();
				}catch(Exception e){
					startNode = 0;
				}
				agent = new Agent(_graph, startNode);
				ans.add(agent);
				_game.addAgent(startNode);
			}
		} catch (JSONException e) {
			e.printStackTrace();
		}

		return ans;
	}

	private ArrayList<Pokemon> json2Pokemons(String json) {
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
				updateEdge(f, _graph);
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

	private directed_weighted_graph graphJsonToGraph(String json){
		dw_graph_algorithms ga = new DWGraph_Algo();
		exportJsonToFile("graph",_game.getGraph());
		ga.load("jsonsFiles/graph.json");
		return ga.getGraph();
	}
	private void exportJsonToFile(String path, String json) {
		try {
			String j = (new JSONObject(json)).toString(4);
			File file = new File("jsonsFiles/" + path + ".json");
			FileWriter myWriter = new FileWriter(file);
			myWriter.write(j);
			myWriter.close();
		}catch ( IOException | JSONException e) {
			e.printStackTrace();
		}
	}

	//================================ EDGES ====================================

	public static void updateEdge(Pokemon fr, directed_weighted_graph g) {
		Iterator<node_data> itr = g.getV().iterator();
		while(itr.hasNext()) {
			node_data v = itr.next();
			Iterator<edge_data> iter = g.getE(v.getKey()).iterator();
			while(iter.hasNext()) {
				edge_data e = iter.next();
				boolean f = isOnEdge(fr.getLocation(), e,fr.getType(), g);
				if(f) {fr.set_edge(e);}
			}
		}
	}

	private static boolean isOnEdge(geo_location p, geo_location src, geo_location dest ) {
		boolean ans = false;
		double dist = src.distance(dest);
		double d1 = src.distance(p) + p.distance(dest);
		if(dist>d1-EPS2) {ans = true;}
		return ans;
	}
	private static boolean isOnEdge(geo_location p, int s, int d, directed_weighted_graph g) {
		geo_location src = g.getNode(s).getLocation();
		geo_location dest = g.getNode(d).getLocation();
		return isOnEdge(p,src,dest);
	}
	private static boolean isOnEdge(geo_location p, edge_data e, int type, directed_weighted_graph g) {
		int src = g.getNode(e.getSrc()).getKey();
		int dest = g.getNode(e.getDest()).getKey();
		if(type<0 && dest>src) {return false;}
		if(type>0 && src>dest) {return false;}
		return isOnEdge(p,src, dest, g);
	}

	//============================= GRAPH RANGE =================================

	private static Range2D GraphRange(directed_weighted_graph g) {
		Iterator<node_data> itr = g.getV().iterator();
		double x0=0,x1=0,y0=0,y1=0;
		boolean first = true;
		while(itr.hasNext()) {
			geo_location p = itr.next().getLocation();
			if(first) {
				x0=p.x(); x1=x0;
				y0=p.y(); y1=y0;
				first = false;
			}
			else {
				if(p.x()<x0) {x0=p.x();}
				if(p.x()>x1) {x1=p.x();}
				if(p.y()<y0) {y0=p.y();}
				if(p.y()>y1) {y1=p.y();}
			}
		}
		Range xr = new Range(x0,x1);
		Range yr = new Range(y0,y1);
		return new Range2D(xr,yr);
	}
	public static Range2Range w2f(directed_weighted_graph g, Range2D frame) {
		Range2D world = GraphRange(g);
		Range2Range ans = new Range2Range(world, frame);
		return ans;
	}

	//=========================== GETTERS & SETTERS ================================

	public void setPokemons(List<Pokemon> f) {this._pokemons = f;}
	public void setAgents(List<Agent> f) {this._agents = f;}
	public void setGraph(directed_weighted_graph g) {this._graph =g;}
	public void set_info(List<String> _info) {this._info = _info;}

	public List<Agent> JsonToAgents() {return _agents;}
	public List<Pokemon> getPokemons() {return _pokemons;}
	public directed_weighted_graph getGraph() {return _graph;}
	public List<String> get_info() { return _info;}

	//=============================== TO STRING ====================================

//	@Override
//	public String toString() {
//		try {
//			return "Arena{\n" +
//					"_gg=" + (new JSONObject(_game.getGraph())).toString(4) +
//				"\n_agents=" + (new JSONObject(_game.getAgents())).toString(4) +
//				"\n_pokemons=" + (new JSONObject(_game.getPokemons())).toString(4) +
//				"\n_info=" + _info +
//				'}';
//		} catch (JSONException e) {
//			e.printStackTrace();
//		}
//		return "";
//	}

	public void moveAgents() {
		String lg = _game.move();
		_agents = JsonToAgents2(lg);
//		this.setAgents(log);

		String fs =  _game.getPokemons();
		List<Pokemon> ffs = json2Pokemons(fs);
		this.setPokemons(ffs);

		for(Agent ag : _agents) {
			int id = ag.getID();
			int dest = ag.getNextNode();
			int src = ag.getSrcNode();
			double v = ag.getValue();
			if(dest == -1) {
				dest = nextNode(_graph, src);
				_game.chooseNextEdge(ag.getID(), dest);
				System.out.println("Agent: "+id+", val: "+v+" turned to node: "+dest);
			}
			ag.setNextNode(dest);
		}
	}
	private static int nextNode(directed_weighted_graph g, int src) {
		int ans = -1;
		Collection<edge_data> ee = g.getE(src);
		Iterator<edge_data> itr = ee.iterator();
		int s = ee.size();
		int r = (int)(Math.random()*s);
		int i=0;
		while(i<r) {
			itr.next();
			i++;
		}
		ans = itr.next().getDest();
		return ans;
	}
}
