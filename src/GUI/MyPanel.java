package GUI;

import javax.swing.*;
import java.awt.*;
import java.util.Iterator;
import java.util.List;

import api.*;
import gameClient.*;
import gameClient.util.Point3D;
import gameClient.util.Range;
import gameClient.util.Range2D;
import org.json.JSONException;
import org.json.JSONObject;

public class MyPanel extends JPanel {

    JLabel t = new JLabel();
    double time;
    double grade;
    double moves;
    int level;
    private Arena _ar;
    private gameClient.util.Range2Range _w2f;

    MyPanel() {
        super();
        add(t);
    }
    public void update(Arena ar) {
        this._ar = ar;
        updateFrame();
    }

    private void updateFrame() {
        time = Ex2._game.timeToEnd()/1000;
        Range rx = new Range(20, this.getWidth()-20);
        Range ry = new Range(this.getHeight()-15, 50);
        Range2D frame = new Range2D(rx,ry);
        directed_weighted_graph g = _ar.getGraph();
        _w2f = Arena.w2f(g, frame);
    }

    @Override
    public void paintComponent(Graphics g) {
        setBackground(Color.GREEN);
        int w = this.getWidth();
        int h = this.getHeight();
        g.clearRect(0, 0, w, h);

        fetchData();
        t.setText("Level: "+ level +"   Timer: "+ time+"   Grade: "+grade+"   Moves: "+moves);

        updateFrame();
        drawGraph(g);
        drawPokemons(g);
        drawAgents(g);
        drawInfo(g);

    }

    private void fetchData() {
        try {
            JSONObject line = new JSONObject(Ex2._game.toString());
            JSONObject ttt = line.getJSONObject("GameServer");
            grade = ttt.getDouble("grade");
            moves = ttt.getDouble("moves");
            level = ttt.getInt("game_level");
            time = Ex2._game.timeToEnd() / 1000;
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    private void drawInfo(Graphics g) {
        List<String> str = _ar.get_info();
        String dt = "none";
        for(int i=0;i<str.size();i++) {
            g.drawString(str.get(i)+" dt: "+dt,100,60+i*20);
        }

    }
    private void drawGraph(Graphics g) {
        directed_weighted_graph gg = _ar.getGraph();
        Iterator<node_data> iter = gg.getV().iterator();
        while(iter.hasNext()) {
            node_data n = iter.next();
            Iterator<edge_data> itr = gg.getE(n.getKey()).iterator();
            while(itr.hasNext()) {
                edge_data e = itr.next();
                g.setColor(Color.gray);
                drawEdge(e, g);
            }
            g.setColor(Color.blue);
            drawNode(n, g);
        }
    }
    private void drawPokemons(Graphics g) {
        Graphics2D g2 = (Graphics2D)g;
        List<Pokemon> fs = _ar.getPokemons();
        if(fs != null) {
            for (Pokemon f : fs) {
                Point3D c = f.getLocation();
                int r = (int) (0.01 * this.getHeight());
                g2.setColor(Color.green);
                if (f.getType() < 0) {
                    g2.setColor(Color.orange);
                }
                if (c != null) {
                    geo_location fp = this._w2f.world2frame(c);

                    Image img1 = Toolkit.getDefaultToolkit().getImage(getClass().getResource("/Icons/pika.png"));
                    g2.drawImage(img1, (int) fp.x() - r, (int) fp.y() - r, 2 * r, 2 * r, this);
                }
            }
        }
    }
    private void drawAgents(Graphics g) {
        List<Agent> rs = _ar.JsonToAgents();
        g.setColor(Color.red);
        int i=0;
        while(rs!=null && i<rs.size()) {
            geo_location c = rs.get(i).getLocation();
            int t=8;
            int r = (int)(0.01 * this.getHeight());
            i++;
            if(c!=null) {

                geo_location fp = this._w2f.world2frame(c);
//                g.fillOval((int)fp.x()-t, (int)fp.y()-t, 2*t, 2*t);
                Image img1 = Toolkit.getDefaultToolkit().getImage(getClass().getResource("/Icons/pokeball.png"));
                g.drawImage(img1, (int)fp.x()-r, (int)fp.y()-r, 2*r, 2*r,this);
            }
        }
    }

    private void drawNode(node_data n, Graphics g) {
        geo_location pos = n.getLocation();
        geo_location fp = this._w2f.world2frame(pos);
        int r = (int)(0.009 * this.getHeight());
        g.fillOval((int)fp.x()-r, (int)fp.y()-r, 2*r, 2*r);
        g.drawString(""+n.getKey(), (int)fp.x(), (int)fp.y()-4*r);
    }

    private void drawEdge(edge_data e, Graphics g) {
        directed_weighted_graph gg = _ar.getGraph();
        geo_location s = gg.getNode(e.getSrc()).getLocation();
        geo_location d = gg.getNode(e.getDest()).getLocation();
        geo_location s0 = this._w2f.world2frame(s);
        geo_location d0 = this._w2f.world2frame(d);
        g.drawLine((int)s0.x(), (int)s0.y(), (int)d0.x(), (int)d0.y());
        //	g.drawString(""+n.getKey(), fp.ix(), fp.iy()-4*r);
    }

}
