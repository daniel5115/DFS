import java.io.*;
import java.util.*;

public class AdjListGraph {
  // Adjacency List  
  ArrayList<ArrayList<Integer>> adj;
  // Componentes conexas
  int[] numcc;
  // Pre y Post
  int[] pre;
  int[] post;
  // DFS tree 
  ArrayList<Integer> dfsTree;
  int numNodes;
  int numEdges;
  int alreadyVisited = 0;
  int cc = 0;
  int clock = 1;

  // Read a graph from the standard input
  public void readData(Scanner s) {
    String line = s.nextLine(); // Ignore comment
    line = s.nextLine();
    String[] data = line.split(" ");
    numNodes = Integer.parseInt(data[0]);
    numEdges = Integer.parseInt(data[2]);
    adj = new ArrayList<ArrayList<Integer>>(numNodes);
    dfsTree = new ArrayList<Integer>();
    numcc = new int[numNodes];
    pre = new int[numNodes];
    post = new int[numNodes];
    System.out.println("numcc size: " + numcc.length);
    for (int i = 0; i < numNodes; ++i)
      adj.add(new ArrayList<Integer>()); 
    while (s.hasNextLine()) {
      line = s.nextLine();
      data = line.split(" ");
      int u = Integer.parseInt(data[0]);
      int v = Integer.parseInt(data[1]);
      u--;
      v--;
      adj.get(u).add(v); // arista u, v
      adj.get(v).add(u); // arista v, u
    }
  }
  // Print adjacency list to standard output
  public void printAdjacencyList() {
    for(int i = 0; i < numNodes; i++){
      System.out.print("Node " + i + ": ");
      for (int j = 0; j < adj.get(i).size(); j++)
        System.out.print(adj.get(i).get(j) + " ");
      System.out.println();
    }
    System.out.println();
  }
  // Previsita
  public void preVisita(int v) {
    numcc[v] = cc;
    pre[v] = clock;
    clock = clock + 1;
  }
  // Postvisita
  public void postVisita(int v) {
    post[v] = clock;
    clock = clock + 1;
  }
    // Print DFS tree
  public void printTree() {
    System.out.println("DFS tree");
    System.out.println("Node: (numcc, pre, post)");
    for (int i = 0; i < numNodes; i++) {
      int v = dfsTree.get(i);
      System.out.println(v + ": (" + numcc[v] + ", " + pre[v] + ", " + post[v] + ")" );
    }
  }
  //  
  public void exploreDFS(int v, boolean visited[]) {
    // Mark nodo as visited
    visited[v] = true;
    // llamar a previsita
    preVisita(v);
    // Process the node v
    System.out.print(v + " ");
    dfsTree.add(v);
    alreadyVisited++;
    // Add all the adjacent nodes of this node
    for (int j = 0; j < adj.get(v).size(); j++) {
      int u = adj.get(v).get(j);
      // if adjacent nodo is not visited, explore it
      if (!visited[u]) 
        exploreDFS(u, visited);
    }
    // post visita
    postVisita(v);
  }
  // DFS Search
  public void DFS(int v) {
    int i;
    // Mark all the nodes as not visited
    boolean visited[] = new boolean[numNodes];
    cc++;
    exploreDFS(v, visited);
    while (alreadyVisited < numNodes) {
      for (i = 0; i < numNodes; i++) {
        if (!visited[i]) 
          break;
      }
      System.out.println("\nLlamada a exploreDFS extra: ");
      cc++;
      exploreDFS(i, visited);
    }
    System.out.println();
  }
}
