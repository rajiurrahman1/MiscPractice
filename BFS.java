package GraphTheoryPractice;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

/**
 * Breadth first traversal of a graph
 * Prints the distance of all the nodes from a given startNode
 * 
 * @author Rajiur
 */
public class BFS {
    public static void main(String[] args){
        Scanner in = new Scanner(System.in);
        int testCases = in.nextInt();
        for(int x=0; x<testCases; x++){
            int numNodes = in.nextInt();
            int numEdges = in.nextInt();
            int[][] edges = new int[numEdges][2];
            int[][] adjacencyMatrix = new int[numNodes][numNodes];
            
            for(int i=0; i<numEdges; i++){
                int src = in.nextInt();
                int dst = in.nextInt();
                edges[i][0] = src;
                edges[i][1] = dst;
                adjacencyMatrix[src-1][dst-1] = 1;
                adjacencyMatrix[dst-1][src-1] = 1;
            }
            int startNode = in.nextInt();
            //input completed
//            dumpMatrix(adjacencyMatrix);
            int[] minDistances = bfs(adjacencyMatrix, startNode);
            printArray(minDistances);
            
        }
    }
    
    private static int[] bfs(int[][] adjacencyMatrix, int startNode) {
        //all nodes are addresse by (-1)
        //for example, startNode = 1, but it is accessed like 0th node
        boolean[] visited = new boolean[adjacencyMatrix.length];
        int[] distance = new int[adjacencyMatrix.length];
        
        //there will be only three values in the distance matrix
        // 0 for startNode, -1 for unreachable nodes, 6n for the rest (distance is multiple of 6 in each hop)
        Arrays.fill(distance, -1); //manually fill all the values to -1
        distance[startNode-1] = 0; //manually set the distance of the startNode to 0
        
        Queue<Integer> queue = new LinkedList<Integer>();
        
        queue.add(startNode-1);
        visited[startNode-1] = true;
        //start traversing all the edges in breadth first manner
        while(!queue.isEmpty()){
            int currentNode = queue.poll(); //take the first element out of Queue
            for(int i=0; i<adjacencyMatrix.length; i++){//add all the neighbours of currentNode to the Queue
                if(adjacencyMatrix[currentNode][i] == 1){
                    visited[currentNode] = true;
                    if(!visited[i]){
                        queue.add(i);
                        distance[i] = distance[currentNode]+6;
                    }
                }
            }
        }
        return distance;
    }
    public static void dumpMatrix(int[][] matrix){
        System.out.println("");
        for(int i=0; i<matrix.length; i++){
            for(int j=0; j<matrix[0].length; j++){
                System.out.print(matrix[i][j]+ " ");
            }System.out.println("");
        }
    }
    public static void printArray(int[] arr){
        System.out.println("");
        for(int i=0; i<arr.length; i++){
            if(arr[i] !=0)System.out.print(arr[i] + " ");
        }System.out.println("");
    }
}
