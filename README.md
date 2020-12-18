![enter image description here](https://github.com/LeviEyal/OOP-EX2-Weighted-directed-graph/blob/master/screenshots/pokemon-logo.png)

**wolcome to EX0!**

This is the first assignment in the course OOP at the Ariel University to year 2020.

> Made by Eyal Levi
>
> GitHub page: [github.com/LeviEyal](github.com/LeviEyal)


## Subject: Unweighted - undirected graph

The project contains classes represents an  unweighted - undirected graph and its vertices. it also contains the class Graph_Algo that holds methods that perform some operations on a given garph.

For full documentation visit:
https://levieyal.github.io/Unweighted-Undirected-Graph/

## The operations:

 - isConnected: check if the given graph is connected, means that for any vertex their is a path to every other vertex.
 - shortestPathDist: for some two given vertices, compute the size of the shortest path from one to another.
- shortestPath: for some two given vertices, return a list holds the vertices of the shortest path from one to another.

## How to use?

 Create some Test class in the package ex0 and run the code below (**for example**):
 

    public static void main(String[] args) {  
  
	    graph graph = new Graph_DS();  
	    for (int i = 0; i <= 4; i++) {  
	        node_data node = new NodeData();  
	        graph.addNode(node);  
	    }  
	    graph.connect(4, 1);  
	    graph.connect(1, 3);  
	    graph.connect(2, 3);  
	    graph.connect(4, 3);  
	    graph.connect(0, 4);  
	    graph.connect(0, 1);  
	    graph.connect(1, 2);  
	    Graph_Algo ga = new Graph_Algo();  
	    ga.init(graph);  
  
	    System.out.println(ga.isConnected());  
	    System.out.println(ga.shortestPathDist(0, 2));  
	    System.out.println(ga.shortestPath(0, 2));  
    }
The code creates the graph:

![enter image description here](https://github.com/LeviEyal/OOP-EX2-Weighted-directed-graph/blob/master/screenshots/scr1.jpg)

The output of this example program shoud be:

    true
    2
    [#0 , #1 , #2 ]

## Algorithms used:
For those three mentioned operations i used some variations of the BFS algorithm.

## External info
What is graph: [https://en.wikipedia.org/wiki/Graph_(discrete_mathematics)](https://en.wikipedia.org/wiki/Graph_%28discrete_mathematics%29)

Connectivity of a graph: [https://en.wikipedia.org/wiki/Connectivity_(graph_theory)](https://en.wikipedia.org/wiki/Connectivity_%28graph_theory%29)

The shortest path problem: [https://en.wikipedia.org/wiki/Shortest_path_problem](https://en.wikipedia.org/wiki/Shortest_path_problem)

The BFS algorithm: [https://en.wikipedia.org/wiki/Breadth-first_search](https://en.wikipedia.org/wiki/Breadth-first_search)
