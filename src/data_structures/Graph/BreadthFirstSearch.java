package data_structures.Graph;

import data_structures.Queue.Queue;
import data_structures.Queue.ResizableQueue;

import java.util.LinkedHashMap;

/**
 * Created by Dorian on 02-Mar-16.
 * DS&A
 */
public class BreadthFirstSearch {
    public static LinkedHashMap BreadthFirstSearch(Graph g, Graph.Vertex v){
        Queue<Graph.Vertex> queue = new Queue<Graph.Vertex>();
        LinkedHashMap<Graph.Vertex,Graph.Vertex> visited = new LinkedHashMap<>(g.numVertices());

        visited.put(v,null);
        queue.enqueue(v);

        while (! queue.isEmpty()){
            Graph.Vertex current = queue.dequeue();
            for (Object temp : current.outgoing.keySet()){
                Graph.Vertex vertex = (Graph.Vertex) temp;
                if (!visited.containsKey(vertex)){
                    visited.put(vertex,current);
                    queue.enqueue(vertex);
                }
            }
        }
        return visited;
    }
}
