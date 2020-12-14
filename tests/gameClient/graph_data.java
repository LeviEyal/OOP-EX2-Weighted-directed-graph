package gameClient;

import api.*;

import java.util.List;

public class graph_data {
        private double _path;
        private List<node_data> _list;
        private int _length;
        private dw_graph_algorithms _algo = new DWGraph_Algo();
        private directed_weighted_graph g = new DWGraph_DS();

        public graph_data(directed_weighted_graph g, int src, int dest){
            _algo.init(g);
            _path = _algo.shortestPathDist(src,dest);
            _list = _algo.shortestPath(src,dest);
            _length = _algo.shortestPath(src,dest).size();
        }
        public double get_path() {
            return _path;
        }
        public List<node_data> get_list() {
            return _list;
        }
        @Override
        public String toString() {
            return "graph_data{" +
                    "_path=" + _path +
                    ", _list=" + _list +
                    '}';
        }
}
