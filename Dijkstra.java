
import static GraphTheoryPractice.Dijkstra.dijkstra;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Scanner;

/**
 *
 * @author Rajiur
 */
public class Dijkstra {
    public static void main(String[] args){
        Scanner in = new Scanner(System.in);
        int testCases = in.nextInt();
        for(int x=0; x<testCases; x++){
            int numNodes = in.nextInt();
            int numEdges = in.nextInt();
            int[][] adjacencyMatrix = new int[numNodes][numNodes];
            for(int i=0; i<numEdges; i++){
                int source = in.nextInt();
                int destination = in.nextInt();
                int weight = in.nextInt();
                adjacencyMatrix[source-1][destination-1] = weight;
                adjacencyMatrix[destination-1][source-1] = weight;
            }
            int startNode = in.nextInt();
//            dumpMatrix(adjacencyMatrix);
            int[] distances = dijkstra(adjacencyMatrix, startNode);
            printArray(distances);
        }
    }
    public static int[] dijkstra(int[][] adjacencyMatrix, int startNode){
        boolean[] visited = new boolean[adjacencyMatrix.length];
        int[] distances = new int[adjacencyMatrix.length];
        Arrays.fill(distances, -1);
        PriorityQueue<Integer> pQueue = new PriorityQueue<Integer>();
        
        distances[startNode-1] = 0;
        pQueue.add(0); //inserting distance from source to source in the Priority Queue
        while( !pQueue.isEmpty() ){
            int minWeight = pQueue.poll(); // have to take out the node with minimum weight .. that's why Priority Queue
            int currentNode = findNodeFromWeight(distances, minWeight, visited); //find which node has the minimum weight
//            System.out.println("\nmin weight:"+minWeight+" current node"+currentNode);
            visited[currentNode] = true;
            for(int i=0; i<adjacencyMatrix.length; i++){
                if(adjacencyMatrix[currentNode][i] > 0 && visited[i]==false ){
                    int currentDistanceFromNode = distances[i];
                    if( currentDistanceFromNode == -1){ //node not visited at all // current distance is still -1
                        distances[i] = minWeight+adjacencyMatrix[currentNode][i]; 
//                        System.out.println("Adding "+currentNode+"-"+i+" dist:"+distances[i]);
                        pQueue.add(distances[i]);
                    }
                    else{ //node visited before // check the updated route distance less than current distance or not
                        if( (minWeight+adjacencyMatrix[currentNode][i]) < distances[i] ){
                            pQueue.remove(distances[i]);
                            distances[i] = minWeight+adjacencyMatrix[currentNode][i];
//                            System.out.println("Adding "+currentNode+"-"+i+" dist:"+distances[i]);
                            pQueue.add(distances[i]);
                        }
                    }
                }
            }
        }
        return distances;
    }
    private static int findNodeFromWeight(int[] distances, int minWeight, boolean[] visited) {
        int i;
        for(i=0; i<distances.length; i++){
            if(distances[i] == minWeight && visited[i]==false){
                break;
            }
        }
//        if(i==distances.length){System.out.println("\n\nRAN OUT\nmin weight:"+minWeight+"\n");}
        return i;
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
        for(int i=0; i<arr.length; i++){
            /*if(arr[i] !=0)*/System.out.print(arr[i] + " ");
        }System.out.println("");
    }
}

/*

1
7 12
1 2 4
1 3 3
1 5 7
2 3 6
2 4 5
3 4 11
3 5 8
4 5 2
4 6 10
4 7 2
5 6 5
6 7 3
1

*/
