package GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.geom.Point2D;
import java.util.ArrayList;

public class panel_skiza extends JPanel implements MouseListener, MouseMotionListener {

    private int _x = 0;
    private int _y = 0;
    private Point2D _prev = null;
    ArrayList<Point2D> _points = new ArrayList<>();

    public panel_skiza() {
        this.addMouseListener(this);
        this.addMouseMotionListener(this);
    }

    @Override
    public void paintComponent(Graphics g){
        for(int i=0; i<_points.size(); i++){
            Point2D p1 = _points.get(i);
            int x = (int) p1.getX();
            int y = (int) p1.getY();
            g.fillOval(x-5, y-5, 10, 10);
            g.drawString("("+x+","+y+")", x, y-20);
            try{
                Point2D p2 = _points.get(i+1);
                g.drawLine((int)p1.getX(), (int)p1.getY(), (int)p2.getX(), (int)p2.getY());
            } catch(RuntimeException e){ }
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        _points.add(e.getPoint());
        repaint();
//        SwingUtilities.updateComponentTreeUI(this);
    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    @Override
    public void mouseDragged(MouseEvent e) {
        _x = e.getX();
        _y = e.getY();

        if(_points.contains(e.getPoint())){

        }
    }

    @Override
    public void mouseMoved(MouseEvent e) {

    }
}
