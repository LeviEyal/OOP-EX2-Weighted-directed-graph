package gameClient;
import api.edge_data;
import gameClient.util.Point3D;
import org.json.JSONObject;

import java.util.Objects;

public class Pokemon {
	private edge_data _edge;
	private double _value;
	private int _type;
	private Point3D _pos;
	private double min_dist;
	private int from;
	private int to;
	private double worth;

	public Pokemon(Point3D p, int t, double v, double s, edge_data e) {
		_type = t;
		_value = v;
		_edge = e;
		_pos = p;
		min_dist = -1;
		from = -1;
	}
	public static Pokemon init_from_json(String json) {
		Pokemon ans = null;
		try {
			JSONObject p = new JSONObject(json);
			int id = p.getInt("id");

		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return ans;
	}

	public edge_data get_edge() {return _edge;}
	public void set_edge(edge_data _edge) {this._edge = _edge;}
	public Point3D getLocation() { return _pos;}
	public int getType() {return _type;}
	public double getValue() {return _value;}
	public double getMin_dist() {return min_dist;}
	public void setMin_dist(double mid_dist) {this.min_dist = mid_dist;}
	public int getFrom() {return from;}
	public void setFrom(int from) {this.from = from;}
	public int getTo() {return to;}
	public void setTo(int from) {this.to = from;}

	public double getWorth() {
		return worth;
	}

	public void setWorth(double worth) {
		this.worth = worth;
	}

	@Override
	public String toString() {
		return "Pokemon{" +
				"_edge=" + _edge +
				", _value=" + _value +
				", _type=" + _type +
				", _pos=" + _pos +
				", min_dist=" + min_dist +
				", min_ro=" + from +
				'}';
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Pokemon pokemon = (Pokemon) o;
		return _type == pokemon._type && Objects.equals(_pos, pokemon._pos);
	}

	@Override
	public int hashCode() {
		return Objects.hash(_type, _pos);
	}
}
