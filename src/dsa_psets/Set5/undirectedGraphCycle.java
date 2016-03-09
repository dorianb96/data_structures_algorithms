package dsa_psets.Set5;


import java.util.LinkedHashSet;

/**
 * Created by Dorian on 09-Mar-16.
 * DS&A
 */
public class undirectedGraphCycle
{
    private LinkedHashSet<Graph.Vertex> visited;
    protected boolean hasCycle;
    // we will use a utility class with a constructor which receives the graph
    public undirectedGraphCycle(Graph graph)
    {
        // we initialize the set which stores visited vertices
        visited = new LinkedHashSet<>(graph.numVertices());
        // we traverse all possible sets
        for (Object temp : graph.vertices()) {
            if (hasCycle) return;
            // we just need to make a small cast
            Graph.Vertex vertex = (Graph.Vertex) temp;
            // if it wasn't already visited
            if (!visited.contains(vertex)) {
                if (!hasCycle) {
                    // then you can explore it
                    depthFirstSearch(graph, vertex, vertex);
                }
            }
        }
    }

    // this performs the depth first search
    private void depthFirstSearch(Graph G, Graph.Vertex current, Graph.Vertex parent)
    {
        // we add the node as visited
        visited.add(current);
        // and traverse all neighbours
        for (Object temp : current.outgoing.keySet()){
            // again some casting
            Graph.Vertex neighbour = (Graph.Vertex) temp;
            // and now the tricky part
            // if it wasn't visited then it is safe and we can explore it
            if (!visited.contains(neighbour))
                depthFirstSearch(G, neighbour, current);
            // a problem with undirected graphs is that they include their parent as
            // a vertex inside outgoing hash map (in this case)
            // thus we had to introduce the parent check and also make sure that if
            // we visited a already visited set that it is not the parent from which we originated
            else if (neighbour != parent)
                hasCycle = true;
        }
    }
}