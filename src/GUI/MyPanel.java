package GUI;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.net.URL;
import java.util.List;

import api.*;
import gameClient.*;
import gameClient.util.Point3D;
import gameClient.util.Range;
import gameClient.util.Range2D;
import org.json.JSONException;
import org.json.JSONObject;

public class MyPanel extends JPanel{

//    private BufferedImage img;
    JLabel t = new JLabel();
    private JCheckBox show_edges = new JCheckBox("edges");
    private JCheckBox show_nodes = new JCheckBox("nodes");
    private JCheckBox show_nodes_numbers = new JCheckBox("nodes numbers");
    private JCheckBox show_pokemons_values = new JCheckBox("pokemons values");
    double time;
    double duration = -2;
    double grade;
    double moves;
    int level;
    private Arena _ar;
    static gameClient.util.Range2Range _w2f;
    private Color[] _agents_colors = new Color[]{
            new Color(255, 0, 221),
            new Color(27, 255, 0),
            new Color(0, 255, 247),
            new Color(255, 0, 0),
            new Color(23,4,68),
            new Color(23,4,68)
    };
    private Image[] _pokemons_images = new Image[]{
            Toolkit.getDefaultToolkit().getImage(getClass().getResource("/GUI/Icons/pika.png")),
            Toolkit.getDefaultToolkit().getImage(getClass().getResource("/GUI/Icons/pika.png")),
            Toolkit.getDefaultToolkit().getImage(getClass().getResource("/GUI/Icons/pika.png")),
            Toolkit.getDefaultToolkit().getImage(getClass().getResource("/GUI/Icons/pika.png")),
            Toolkit.getDefaultToolkit().getImage(getClass().getResource("/GUI/Icons/pika.png")),
            Toolkit.getDefaultToolkit().getImage(getClass().getResource("/GUI/Icons/pika.png")),
            Toolkit.getDefaultToolkit().getImage(getClass().getResource("/GUI/Icons/poke1.png")),
            Toolkit.getDefaultToolkit().getImage(getClass().getResource("/GUI/Icons/poke1.png")),
            Toolkit.getDefaultToolkit().getImage(getClass().getResource("/GUI/Icons/poke2.png")),
            Toolkit.getDefaultToolkit().getImage(getClass().getResource("/GUI/Icons/poke2.png")),
            Toolkit.getDefaultToolkit().getImage(getClass().getResource("/GUI/Icons/poke3.png")),
            Toolkit.getDefaultToolkit().getImage(getClass().getResource("/GUI/Icons/poke4.png")),
            Toolkit.getDefaultToolkit().getImage(getClass().getResource("/GUI/Icons/poke4.png")),
            Toolkit.getDefaultToolkit().getImage(getClass().getResource("/GUI/Icons/poke4.png")),
            Toolkit.getDefaultToolkit().getImage(getClass().getResource("/GUI/Icons/poke4.png")),
            Toolkit.getDefaultToolkit().getImage(getClass().getResource("/GUI/Icons/poke4.png"))
    };

    MyPanel() {
        super();
        add(t);
        show_nodes.setSelected(true);
        show_edges.setSelected(true);
        show_nodes_numbers.setSelected(true);
        show_pokemons_values.setSelected(true);

        show_nodes.setOpaque(false);
        show_edges.setOpaque(false);
        show_nodes_numbers.setOpaque(false);
        show_pokemons_values.setOpaque(false);

        add(show_edges);
        add(show_nodes);
        add(show_nodes_numbers);
        add(show_pokemons_values);
        setOpaque(true);
    }
    public void update(Arena ar) {
        this._ar = ar;
        updateFrame();
    }

    private void updateFrame() {
        time = Ex2._game.timeToEnd()/1000.0;
        Range rx = new Range(50, this.getWidth()-50);
        Range ry = new Range(this.getHeight()-30, 80);
        Range2D frame = new Range2D(rx,ry);
        directed_weighted_graph g = _ar.getGraph();
        _w2f = Arena.w2f(g, frame);
    }


    @Override
    public void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D)g;
        super.paintComponent(g);
        int w = this.getWidth();
        int h = this.getHeight();
        Image img = Toolkit.getDefaultToolkit().getImage(getClass().getResource("/GUI/Icons/background.png"));
        g2.drawImage(img, 0,0, w, h, this);

//        g2.clearRect(0, 0, w-100, h-100);

        fetchData();
        t.setText("Level: "+ level +" Timer: "+ time+" Grade: "+grade+" Moves: "+moves+" Duration: "+duration+" maxMoves: "+duration*10+"     Display:");
//        MyFrame.t.setText("Level: "+ level +"Timer: "+ time+"   Grade: "+grade+"Moves: "+moves+"     Display:");
//        MyFrame.t.setLayout(null);
//        MyFrame.info[0].setName("Level: " + level);
//        MyFrame.info[1].setName("Timer: " + time);
//        MyFrame.info[2].setName("Grade: " + grade);
//        MyFrame.info[3].setName("Moves: " + moves);

        updateFrame();
        drawGraph(g2);
        drawAgents(g2);
        drawPokemons(g2);
//        drawInfo(g2);
    }

    private void fetchData() {
        try {
            JSONObject line = new JSONObject(Ex2._game.toString());
            JSONObject ttt = line.getJSONObject("GameServer");
            grade = ttt.getDouble("grade");
            moves = ttt.getDouble("moves");
            level = ttt.getInt("game_level");
            time = Ex2._game.timeToEnd() / 1000;
            if(duration == -2)
                duration = time;
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    private void drawInfo(Graphics2D g) {
        List<String> str = _ar.get_info();
        String dt = "none";
        for(int i=0; i<str.size(); i++) {
            g.drawString(str.get(i)+" dt: "+dt,100,60+i*20);
        }

    }
    private void drawGraph(Graphics2D g) {
        directed_weighted_graph gg = _ar.getGraph();
        g.setStroke(new BasicStroke(2));
        if(show_edges.isSelected()) {
            for (node_data n : gg.getV()) {
                for (edge_data e : gg.getE(n.getKey())) {
                    g.setColor(new Color(0, 0, 0, 32));
                    drawEdge(e, g);
                }
            }
        }
        g.setColor(Color.blue);
        for (node_data n : gg.getV()) {
            drawNode(n, g);
        }
    }
    private void drawPokemons(Graphics2D g) {
        List<Pokemon> fs = _ar.getPokemons();
        if(fs != null) {
            int i=0;
            for (Pokemon f : fs) {
                int val = (int)f.getValue();
                Point3D c = f.getLocation();
                int r = (int) (0.03 * this.getHeight());
                g.setColor(Color.RED);
                g.setFont(new Font("Arial", Font.BOLD, 16));
                if (c != null) {
                    geo_location fp = _w2f.world2frame(c);

                    Image img1 = _pokemons_images[val%_pokemons_images.length];
                    g.drawImage(img1, (int) fp.x() - r, (int) fp.y() - r, 2 * r, 2 * r, this);
                    if(show_pokemons_values.isSelected())
                        g.drawString(""+val, (int)fp.x()-2*r, (int)fp.y()-2*r);
                }
            }
        }
    }
    private void drawAgents(Graphics2D g) {
        List<Agent> rs = _ar.JsonToAgents();
        g.setStroke(new BasicStroke(3));
        for(Agent ag: rs){
            g.setColor(_agents_colors[ag.getID()]);
            List<node_data> path = Arena.paths.get(ag.getID());
            for(int i=0; i< path.size()-1; i++){
                edge_data e = new EdgeData(path.get(i).getKey(), path.get(i+1).getKey(),0);
                drawEdge(e, g);
            }
            geo_location c = ag.getLocation();
            int r = (int)(0.03 * this.getHeight());
            if(c!=null) {
                geo_location fp = _w2f.world2frame(c);
                Image img1 = Toolkit.getDefaultToolkit().getImage(getClass().getResource("/GUI/Icons/pokeball.png"));
                g.drawImage(img1, (int)fp.x()-r, (int)fp.y()-r, 2*r, 2*r,this);
                g.drawString(""+ag.getID(), (int)fp.x()-2*r, (int)fp.y()-2*r);
            }
        }
    }

    private void drawNode(node_data n, Graphics2D g) {
        geo_location pos = n.getLocation();
        geo_location fp = _w2f.world2frame(pos);
        int r = (int)(0.009 * this.getHeight());
        Image img1 = Toolkit.getDefaultToolkit().getImage(getClass().getResource("/GUI/Icons/node.png"));
        if(show_nodes.isSelected()) {
//            g.drawImage(img1, (int)fp.x()-r, (int)fp.y()-r, 2*r, 2*r,this);
            g.fillOval((int)fp.x()-r, (int)fp.y()-r, 2*r, 2*r);
            g.fill3DRect((int)fp.x()-r, (int)fp.y()-r, 2*r, 2*r, true);
        }
        if(show_nodes_numbers.isSelected())
            g.drawString(""+n.getKey(), (int)fp.x()-2*r, (int)fp.y()-2*r);
    }

    private void drawEdge(edge_data e, Graphics2D g) {
        directed_weighted_graph gg = _ar.getGraph();
        geo_location s = gg.getNode(e.getSrc()).getLocation();
        geo_location d = gg.getNode(e.getDest()).getLocation();
        geo_location s0 = _w2f.world2frame(s);
        geo_location d0 = _w2f.world2frame(d);

        g.drawLine((int)s0.x(), (int)s0.y(), (int)d0.x(), (int)d0.y());
        //	g.drawString(""+n.getKey(), fp.ix(), fp.iy()-4*r);
    }

}
