package data_structures.Graph;

import java.util.LinkedHashMap;

/**
 * Created by Dorian on 02-Mar-16.
 * DS&A
 */
public class DepthFirstSearch <E,V> {
    public static LinkedHashMap DepthFirstSearch(Graph g, Graph.Vertex v){
        LinkedHashMap visited = new LinkedHashMap(g.numVertices());
        visited.put(v,null);
        DepthFirstSearch(g,v,visited);
        return visited;
    }

    public static void DepthFirstSearch(Graph g, Graph.Vertex v, LinkedHashMap visited){
        for (Object temp : v.outgoing.keySet()){
            Graph.Vertex vertex = (Graph.Vertex) temp;
            if (! visited.containsKey(vertex)){
                visited.put(vertex,v);
                DepthFirstSearch(g,vertex,visited);
            }
        }
    }
}
