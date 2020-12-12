package GUI;

import javax.swing.*;
import java.awt.*;
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
    static gameClient.util.Range2Range _w2f;

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
        for(int i=0; i<str.size(); i++) {
            g.drawString(str.get(i)+" dt: "+dt,100,60+i*20);
        }

    }
    private void drawGraph(Graphics g) {
        Graphics2D g2 = (Graphics2D)g;
        directed_weighted_graph gg = _ar.getGraph();
        for (node_data n : gg.getV()) {
            for (edge_data e : gg.getE(n.getKey())) {
                g2.setColor(new Color(43, 43, 43));
                drawEdge(e, g);
            }
        }
        g2.setColor(Color.BLUE);
        for (node_data n : gg.getV()) {
            drawNode(n, g);
        }
    }
    private void drawPokemons(Graphics g) {
        Graphics2D g2 = (Graphics2D)g;
        List<Pokemon> fs = _ar.getPokemons();
        if(fs != null) {
            for (Pokemon f : fs) {
                Point3D c = f.getLocation();
                int r = (int) (0.02 * this.getHeight());
                g2.setColor(Color.RED);
                g2.setFont(new Font("Arial", Font.BOLD, 16));
                if (c != null) {
                    geo_location fp = _w2f.world2frame(c);

                    Image img1 = Toolkit.getDefaultToolkit().getImage(getClass().getResource("/Icons/pika.png"));
                    g2.drawImage(img1, (int) fp.x() - r, (int) fp.y() - r, 2 * r, 2 * r, this);
                    g.drawString(String.format("%.0f", f.getValue()), (int)fp.x()-2*r, (int)fp.y()-2*r);
                }
            }
        }
    }
    private void drawAgents(Graphics g) {
        List<Agent> rs = _ar.JsonToAgents();
        for(Agent ag: rs){
            geo_location c = ag.getLocation();
            int r = (int)(0.02 * this.getHeight());
            if(c!=null) {
                geo_location fp = _w2f.world2frame(c);
                Image img1 = Toolkit.getDefaultToolkit().getImage(getClass().getResource("/Icons/pokeball.png"));
                g.drawImage(img1, (int)fp.x()-r, (int)fp.y()-r, 2*r, 2*r,this);
            }
            g.setColor(Color.MAGENTA);
            for(edge_data e : ag.getPathAsEdges()){
                drawEdge(e, g);
            }
        }
    }

    private void drawNode(node_data n, Graphics g) {
        geo_location pos = n.getLocation();
        geo_location fp = _w2f.world2frame(pos);
        int r = (int)(0.009 * this.getHeight());
        g.fillOval((int)fp.x()-r, (int)fp.y()-r, 2*r, 2*r);
        g.drawString(""+n.getKey(), (int)fp.x()-2*r, (int)fp.y()-2*r);
    }

    private void drawEdge(edge_data e, Graphics g) {
        Graphics2D g2 = (Graphics2D)g;
        directed_weighted_graph gg = _ar.getGraph();
        geo_location s = gg.getNode(e.getSrc()).getLocation();
        geo_location d = gg.getNode(e.getDest()).getLocation();
        geo_location s0 = _w2f.world2frame(s);
        geo_location d0 = _w2f.world2frame(d);
        g2.drawLine((int)s0.x(), (int)s0.y(), (int)d0.x(), (int)d0.y());
        //	g.drawString(""+n.getKey(), fp.ix(), fp.iy()-4*r);
    }
}
