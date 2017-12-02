package com.company;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.*;

// This class represents a directed graph using adjacency
// list representation
class Graph
{
    private int V;   // No. of vertices
    private LinkedList<Integer> adj[]; // Adjacency List

    //Constructor
    Graph(int v)
    {
        V = v;
        adj = new LinkedList[v];
        for (int i=0; i<v; ++i)
            adj[i] = new LinkedList();
    }

    // Function to add an edge into the graph
    void addEdge(int v,int w) { adj[v].add(w); }

    // A recursive function used by topologicalSort
    void topologicalSortUtil(int v, boolean visited[],
                             Stack stack)
    {
        // Mark the current node as visited.
        visited[v] = true;
        Integer i;

        // Recur for all the vertices adjacent to this
        // vertex
        Iterator<Integer> it = adj[v].iterator();
        while (it.hasNext())
        {
            i = it.next();
            if (!visited[i])
                topologicalSortUtil(i, visited, stack);
        }

        // Push current vertex to stack which stores result
        stack.push(new Integer(v));
    }

    void topologicalSort(int order)
    {
        Stack stack = new Stack();

        // Mark all the vertices as not visited
        boolean visited[] = new boolean[V];
        for (int i = 0; i < V; i++)
            visited[i] = false;


        if(order == 1)
            for (int i = 0; i < V; i++)
                if (visited[i] == false)
                    topologicalSortUtil(i, visited, stack);
        if(order == 2)
            for (int i = V-1; i >= 0 ; i--)
                if (visited[i] == false)
                    topologicalSortUtil(i, visited, stack);

        // Print contents of stack
        while (stack.empty()==false)
            System.out.print(stack.pop() + " ");
    }


    public static void main(String args[]) throws Exception
    {
        // Create a graph given in the above diagram


        String file = new File("src/infile.dat").getAbsolutePath();

        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            int count = 0;

            line = br.readLine();
            String edgeNode[] = line.split(" ");
            Graph g = new Graph(Integer.parseInt(edgeNode[0]));

            for (int i = 0;i<Integer.parseInt(edgeNode[1]);i++){

                line = br.readLine();
                String edgeNode1[] = line.split(" ");
                g.addEdge(Integer.parseInt(edgeNode1[0]), Integer.parseInt(edgeNode1[1]));

            }


            System.out.println("First Ordering");
            g.topologicalSort(1);
            System.out.println("\n");
            System.out.println("Second Ordering");

            g.topologicalSort(2);




            }
        }

}


