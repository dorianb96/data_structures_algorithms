package dsa_psets.Set5;

import org.junit.Test;

/**
 * Created by Dorian on 09-Mar-16.
 * DS&A
 */
public class tester {
    // first make the non-directed graph
    @Test
    public void tester(){
        Graph<Object,Integer> graph = new Graph<>(false);
        Graph.Vertex v1 = graph.insertVertex(1);
        Graph.Vertex v2 = graph.insertVertex(2);
        Graph.Vertex v3 = graph.insertVertex(3);
        Graph.Vertex v4 = graph.insertVertex(4);
        Graph.Vertex v5 = graph.insertVertex(5);
        Graph.Vertex v6 = graph.insertVertex(6);
        Graph.Vertex v7 = graph.insertVertex(7);

        graph.insertEdge(v1,v5,null);
        graph.insertEdge(v1,v7,null);
        graph.insertEdge(v5,v7,null);
        graph.insertEdge(v4,v5,null);
        graph.insertEdge(v4,v6,null);
        graph.insertEdge(v4,v3,null);
        graph.insertEdge(v3,v6,null);
        graph.insertEdge(v3,v2,null);
        graph.insertEdge(v6,v2,null);
        graph.insertEdge(v7,v2,null);
        graph.insertEdge(v6,v7,null);

        System.out.println(new GraphCycle(graph).hasCycle);
    }
    // now just inter-connect 1,5 and 7 which will also make a cycle
    @Test
    public void tester2(){
        Graph<Object,Integer> graph = new Graph<>(false);
        Graph.Vertex v1 = graph.insertVertex(1);
        Graph.Vertex v2 = graph.insertVertex(2);
        Graph.Vertex v3 = graph.insertVertex(3);
        Graph.Vertex v4 = graph.insertVertex(4);
        Graph.Vertex v5 = graph.insertVertex(5);
        Graph.Vertex v6 = graph.insertVertex(6);
        Graph.Vertex v7 = graph.insertVertex(7);


        graph.insertEdge(v1,v5,null);
        graph.insertEdge(v1,v7,null);
        graph.insertEdge(v5,v7,null);
        System.out.println(new GraphCycle(graph).hasCycle);
    }
    // now just connect 1 and 7 which will not make a cycle
    @Test
    public void tester3(){
        Graph<Object,Integer> graph = new Graph<>(false);
        Graph.Vertex v1 = graph.insertVertex(1);
        Graph.Vertex v2 = graph.insertVertex(2);
        Graph.Vertex v3 = graph.insertVertex(3);
        Graph.Vertex v4 = graph.insertVertex(4);
        Graph.Vertex v5 = graph.insertVertex(5);
        Graph.Vertex v6 = graph.insertVertex(6);
        Graph.Vertex v7 = graph.insertVertex(7);


        graph.insertEdge(v1,v5,null);
        graph.insertEdge(v1,v7,null);

        graph.insertEdge(v4,v6,null);
        graph.insertEdge(v4,v3,null);

        System.out.println(new GraphCycle(graph).hasCycle);
    }


    // now the directed graphs

    // this is the given example, it has no cycles
    @Test
    public void tester4(){
        Graph<Object,Integer> graph = new Graph<>(true);
        Graph.Vertex v1 = graph.insertVertex(1);
        Graph.Vertex v2 = graph.insertVertex(2);
        Graph.Vertex v3 = graph.insertVertex(3);
        Graph.Vertex v4 = graph.insertVertex(4);
        Graph.Vertex v5 = graph.insertVertex(5);
        Graph.Vertex v6 = graph.insertVertex(6);


        // first we specify origin and then the destination
        graph.insertEdge(v1,v4,null);
        graph.insertEdge(v2,v4,null);
        graph.insertEdge(v3,v4,null);
        graph.insertEdge(v4,v5,null);
        graph.insertEdge(v5,v6,null);


        System.out.println(new DigraphCycle(graph).hasCycle);
    }

    // this is when we add a edge from 6 to 4, which produces a cycle
    @Test
    public void tester5(){
        Graph<Object,Integer> graph = new Graph<>(true);
        Graph.Vertex v1 = graph.insertVertex(1);
        Graph.Vertex v2 = graph.insertVertex(2);
        Graph.Vertex v3 = graph.insertVertex(3);
        Graph.Vertex v4 = graph.insertVertex(4);
        Graph.Vertex v5 = graph.insertVertex(5);
        Graph.Vertex v6 = graph.insertVertex(6);

        graph.insertEdge(v1,v4,null);
        graph.insertEdge(v2,v4,null);
        graph.insertEdge(v3,v4,null);
        graph.insertEdge(v4,v5,null);
        graph.insertEdge(v5,v6,null);
        // the added part
        graph.insertEdge(v6,v4,null);


        System.out.println(new DigraphCycle(graph).hasCycle);
    }



}
