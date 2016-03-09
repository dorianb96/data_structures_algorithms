package data_structures.Graph;

import org.junit.Test;

import java.util.Arrays;

/**
 * Created by Dorian on 02-Mar-16.
 * DS&A
 */
public class tester {
    @Test
    public void test() {
        Graph<String, String> graph = new Graph<>(true);
        Graph.Vertex dora = graph.insertVertex("Dora");
        Graph.Vertex dorian = graph.insertVertex("Dorian");
        Graph.Vertex ally = graph.insertVertex("Ally");
        Graph.Vertex baka = graph.insertVertex("baka");
        Graph.Vertex tata = graph.insertVertex("tata");
        Graph.Vertex mama = graph.insertVertex("mama");
        Graph.Vertex seka = graph.insertVertex("seka");



        graph.insertEdge(baka,tata, "punica od");
        graph.insertEdge(baka,mama, "mama od");
        graph.insertEdge(tata,dorian, "tata od");
        graph.insertEdge(dorian,dora, "decko od");
        graph.insertEdge(dora,ally, "vlasnica od");
        graph.insertEdge(mama,seka, "mama od");
     //   System.out.println(BreadthFirstSearch.BreadthFirstSearch(graph,baka).entrySet());
        TopologicalSort.topologicalSort2(graph);
        //System.out.println(DepthFirstSearch.DepthFirstSearch(graph,baka).entrySet());
     //   System.out.println(BreadthFirstSearch.BreadthFirstSearch(graph,baka).entrySet());

    }
}
