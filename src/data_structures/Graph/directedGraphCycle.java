package data_structures.Graph;

import java.util.HashSet;

/**
 * Created by Dorian on 09-Mar-16.
 * DS&A
 */
public class directedGraphCycle {
    private HashSet<Graph.Vertex> visited;
    private HashSet<Graph.Vertex> stack;
    protected boolean hasCycle;

    public directedGraphCycle(Graph graph){
        // we initialize some useful collections
        visited = new HashSet<>(graph.numVertices());
        stack = new HashSet<>(graph.numVertices());

        // we want to traverse all vertices of graph
        for (Object temp : graph.vertices()){
            // we just need a small cast
            Graph.Vertex vertex = (Graph.Vertex) temp;
            // if the vertex isn't visited
            if (!visited.contains(vertex)){
                // and if we haven't found a cycle so far
                if(!hasCycle){
                    // explore that vertex in a depth first manner
                    depthFirstSearch(vertex);
                }
            }
        }
    }

    // we send a vertex into a depth first search
    // the depth first search makes sure to explore all vertices
    // connected and reachable from current vertex
    public void depthFirstSearch(Graph.Vertex current){
        // we add the current vertex to list of visited
        visited.add(current);
        // we will use a "stack" to remember which vertices we were able to visit in the
        // current iteration of DFS and then if we visit them again in another DFS we have a cycle
        stack.add(current);

        // now we check all the neighbours of current vertex
        for (Object temp : current.outgoing.keySet()){
            Graph.Vertex neighbour = (Graph.Vertex) temp;

            // this just checks whether we already found a cycle
            if(hasCycle) return;
            // if the neighbour hasn't already been visited, visit it via DFS
            // this part makes sure to explore all reachable elements
            // also this will add it to the current stack
            else if(! visited.contains(neighbour)){
                depthFirstSearch(neighbour);
            }
            /** otherwise if the stack of already visited elements during this search cycle contains
             the neighbour element, we know that during the depth first search we already visited such
             an element and thus this is a cycle  */
            else if (stack.contains(neighbour)){
                hasCycle = true;
            }
            // if none of conditions are met, just explore new neighbours
        }

        // so if we explored all the neighbours, but didn't find a cycle
        // we must remove the current node from the stack
        // stack is only used to keep track of all vertices visited in current search (DFS)
        // so this basically disqualifies the part of graph traversed with depth first search
        // since we already set it as visited and removed from the stack we won't ever access it again
        stack.remove(current);
    }
}
