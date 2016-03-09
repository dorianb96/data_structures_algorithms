package data_structures.Graph;

import java.util.*;
import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * Created by Dorian on 03-Mar-16.
 * DS&A
 */
public class TopologicalSort {
    public static void topologicalSort(Graph g){
        if (!g.isDirected()) throw new IllegalArgumentException("Can't use undirected graphs for topological sort");
        Set<Graph.Vertex> visited = new LinkedHashSet<>(g.numVertices());
        Stack<Graph.Vertex> order = new Stack<>();
        for (Object vertex : g.vertices()){
            if(!visited.contains((Graph.Vertex) vertex))
                topSort((Graph.Vertex) vertex, visited, order);
        }
        System.out.println(order);
    }
    public static void topSort(Graph.Vertex v, Set visited, Stack order){
        visited.add(v);
        for (Object vertex : v.outgoing.keySet()){
            Graph.Vertex vertex1 = (Graph.Vertex) vertex;
            if(!visited.contains(vertex1)){
                topSort(vertex1,visited,order);
            }
        }
        order.push(v);
    }

    // this is another version where we use a queue

    public static void topologicalSort2(Graph g){
        System.out.println("hehehehe");
        if (!g.isDirected()) throw new IllegalArgumentException("Can't use undirected graphs for topological sort");
        Queue<Graph.Vertex> order = new ConcurrentLinkedQueue<>();
        Queue<Graph.Vertex> order2 = new ConcurrentLinkedQueue<>();


        Set<Graph.Vertex> visited = new LinkedHashSet<>();
        for (Object vertex : g.vertices){
            Graph.Vertex v = (Graph.Vertex) vertex;
            if (v.incoming.entrySet().size() == 0){
                order.add(v);
                visited.add(v);
            }
        }


        while (!order.isEmpty()){
            Graph.Vertex v = order.poll();
            order2.offer(v);
            for (Object iter : v.outgoing.keySet()){
                Graph.Vertex vertex = (Graph.Vertex) iter;
                if (!visited.contains(vertex)) {
                    order.add(vertex);
                    visited.add(vertex);
                }
            }
        }
        System.out.println(order2);
    }
}
