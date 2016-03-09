package dsa_psets.Set5;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

/**
 numVertices( ): Returns the number of vertices of the graph.
 vertices( ): Returns an iteration of all the vertices of the graph.
 numEdges( ): Returns the number of edges of the graph.
 edges( ): Returns an iteration of all the edges of the graph.
 getEdge(u, v): Returns the edge from vertex u to vertex v, if one exists;
     otherwise return null. For an undirected graph, there is no
     difference between getEdge(u, v) and getEdge(v, u).
     endVertices(e): Returns an array containing the two endpoint vertices of
     edge e. If the graph is directed, the first vertex is the origin
     and the second is the destination.
 opposite(v, e): For edge e incident to vertex v, returns the other vertex of
     the edge; an error occurs if e is not incident to v.
 outDegree(v): Returns the number of outgoing edges from vertex v.
 inDegree(v): Returns the number of incoming edges to vertex v. For
     an undirected graph, this returns the same value as does
     outDegree(v).
 outgoingEdges(v): Returns an iteration of all outgoing edges from vertex v.
 incomingEdges(v): Returns an iteration of all incoming edges to vertex v. For
     an undirected graph, this returns the same collection as
     does outgoingEdges(v).
 insertVertex(x): Creates and returns a new Vertex storing element x.
 insertEdge(u, v, x): Creates and returns a new Edge from vertex u to vertex v,
     storing element x; an error occurs if there already exists an
     edge from u to v.
 removeVertex(v): Removes vertex v and all its incident edges from the graph.
 removeEdge(e): Removes edge e from the graph.
 */
public class Graph<E,V>  {
    LinkedList<Vertex<V>> vertices;
    LinkedList<Edge<E>> edges;
    private boolean isDirected;

    public Graph(boolean isDirected){
        vertices = new LinkedList<>();
        edges = new LinkedList<>();
        this.isDirected = isDirected;
    }

    @SuppressWarnings("unchecked")
    protected class Edge<E> {
        E value;
        Vertex<V>[] endPoints;
        public Edge(Vertex<V> v1, Vertex<V> v2, E value){
            endPoints = (Vertex<V>[]) new Vertex[]{v1,v2};
            this.value = value;
        }

        @Override
        public String toString() {
            return "{" + value +
                    '}';
        }
    }
    protected class Vertex<V> {
        V value;
        Map<Vertex<V>,Edge<E>> incoming;
        Map<Vertex<V>,Edge<E>> outgoing;
        public Vertex (boolean isDirected, V value){
            this.value = value;
            outgoing = new HashMap<Vertex<V>, Edge<E>>();
            if (isDirected){
                incoming = new HashMap<Vertex<V>, Edge<E>>();
            }
            else{
                incoming = outgoing;
            }
        }

        @Override
        public String toString() {
            return "{" + value +
                    '}';
        }
    }

    public int numVertices(){
        return vertices.size();
    }
    public int numEdges(){
        return edges.size();
    }
    public LinkedList<Edge<E>> edges(){
        return edges;
    }
    public LinkedList<Vertex<V>> vertices(){
        return vertices;
    }
    public Edge<E> getEdge(Vertex<V> v1, Vertex<V> v2){
        // first find the element in linked list, then access the Map with outgoing vertices
        // then find the value ie edge associated with that vertex key v2
        return (vertices.get(vertices.indexOf(v1))).outgoing.get(v2);
    }
    // returns the endpoints of a edge, they are stored as a field inside it
    public Vertex<V>[] endPoints(Edge<E> edge){
        return edge.endPoints;
    }
    //

    public boolean isDirected(){
        return isDirected;
    }

    public Vertex<V> opposite(Edge<E> edge, Vertex<V> v1){
        Vertex<V> v2;
        // we shall access the field endpoints of an edge
        if (v1 == edge.endPoints[0]){
            v2 = edge.endPoints[1];
        }
        else{
            v2 = edge.endPoints[0];
        }
        return v2;
    }
    // inDegree is the number of edges/connected vertices
    // in this case outgoing edges
    public int outDegree(Vertex<V> vertex){
        return vertex.outgoing.size();
    }
    // inDegree is the number of edges/connected vertices
    // in this case incoming edges
    public int inDegree(Vertex<V> vertex){
        return vertex.incoming.size();
    }
    // this is just a choice to return a linked list, could have returned almost anything else also
    public LinkedList<Edge<E>> outgoingEdges( Vertex<V> vertex){
        LinkedList<Edge<E>> list = new LinkedList<>();
        for (Edge<E> edge : vertex.outgoing.values()){
            list.add(edge);
        }
        return list;
    }
    // this is just a choice to return a linked list, could have returned almost anything else also
    public LinkedList<Edge<E>> incomingEdges( Vertex<V> vertex){
        LinkedList<Edge<E>> list = new LinkedList<>();
        for (Edge<E> edge : vertex.incoming.values()){
            list.add(edge);
        }
        return list;
    }
    // this just creates an vertex and stores him in the vertices collection
    // but also it returns a useful reference to the vertex
    public Vertex<V> insertVertex(V element){
        Vertex<V> vertex = new Vertex<>(isDirected, element);
        vertices.add(vertex);
        return vertex;
    }
    //insertEdge(u, v, x): Creates and returns a new Edge from vertex u to vertex v,
    //storing element x; an error occurs if there already exists an
    //edge from u to v.
    public Edge<E> insertEdge(Vertex<V> origin, Vertex<V> destin, E element){
        if (getEdge(origin,destin) == null) {
            Edge<E> edge = new Edge<>(origin, destin, element);
            edges.add(edge);
            origin.outgoing.put(destin,edge);
            destin.incoming.put(origin,edge);
            return edge;
        }
        else {
            throw new IllegalArgumentException("The edge already exists");
        }
    }

    public void removeVertex(Vertex<V> vertex){
        for (Edge<E> connectedEdge : vertex.incoming.values()){
            removeEdge(connectedEdge);

        }
        for (Edge<E> connectedEdge : vertex.outgoing.values()){
            removeEdge(connectedEdge);
        }
        vertices.remove(vertices.indexOf(vertex));
    }

    public void removeEdge(Edge<E> edge){
        edges.remove(edges.indexOf(edge));
        Vertex<V> v1  = edge.endPoints[0];
        Vertex<V> v2  = edge.endPoints[1];
        // thus v1 has v2 in incoming, and v2 has v1 in outgoing
        if (v1.incoming.containsKey(v2)){
            v1.incoming.remove(v2);
            v2.outgoing.remove(v1);
        }
        // otherwise v2[incoming] --> v1
        // v1[outgoing] -- v2
        else{
            v1.outgoing.remove(v2);
            v2.incoming.remove(v1);
        }
    }
}
