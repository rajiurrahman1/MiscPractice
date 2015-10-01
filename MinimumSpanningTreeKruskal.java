
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Scanner;

/**
 * Description:
 * Implementation of Kruskal's algorithm for Minimum Spanning Tree (MST)
 * Check wiki for general description of the algorithm ---(http://bit.ly/KruskalsAlgorithm)---
 * 
 * To be honest it was a pain in the ass, although it deems itself an easy one in algorithm books & lectures 
 * How to detect whether an edge is safe to add to MST or if the addition will create a cycle - the most difficult part
 * The rest of the thing is fairly simple
 * 
 * Input: (Check the end of file for sample input)
 * Num_Nodes Num_Edges
 * Followed by all the edges in the following format
 * srcNode dstNode edgeWeight
 * 
 *
 * @author Rajiur Rahman
 * reach me at rajiurrahman.bd@gmail.com
 */

class Edge{ //class name and constructor function is self-explanator i assume
    public int src;
    public int dst;
    public Edge(int s, int d){
        this.src = s;
        this.dst = d;
    }
}

public class MinimumSpanningTreeKruskal {
    
    public static void main(String[] args){
        Scanner in = new Scanner(System.in);
        int numNodes = in.nextInt();
        int numEdges = in.nextInt();
        
        //the graph will be saved as a HashMap where the key will be edge weight
        //and the value of HashMap will be a list of type <Edge>. The list will contain all the edges with similar weights
        //putting weight as Key for the convenience of implementing Kruskal algorithm. In each iteration, we need the smallest weight
        HashMap<Integer, List<Edge>> graph = new HashMap<Integer, List<Edge>>();
        //at each pop, PriorityQueue gives the lowest value, that's EXACTLY what we need for this algorithm
        PriorityQueue<Integer> pq = new PriorityQueue<Integer>(); 
        
        for(int i=0; i<numEdges; i++){
            int source = in.nextInt();
            int destination = in.nextInt();
            int weight = in.nextInt();
            addEdge(graph, source, destination, weight);
            pq.add(weight);
        }
//        printGraph(graph);
        List<Edge> mstEdges = new ArrayList<Edge>();
        int mstSum = mstKruskal(numNodes, graph, pq, mstEdges);
//        System.out.println(pq.toString());
        System.out.println(mstSum);

    }
    
    private static int mstKruskal(int numNodes, HashMap<Integer, List<Edge>> graph, PriorityQueue<Integer> pq, List<Edge> mstEdges ) {
        //first we build a set to maintain if it is safe to add an edge to MST
        //and write a method using this set // if adding an edge forms cycle, the method returns false
        HashMap<Integer, List<Integer>> set = buildSet(numNodes); // build the set to check cycle before adding edge to MST
        int mstWeight = 0;
//        printSet(set);

        //now we traverse the whole graph
        while( !pq.isEmpty() ){
            int minWeight = pq.poll();
            Edge e = getEdge(graph, minWeight);
            boolean res = isSafe(set, e.src, e.dst);
            if(res){ //if res is true, add them in mstWeight and edges
                mstWeight += minWeight;
                mstEdges.add(e);
            }
        }
        return mstWeight;
    }
    
    //the following method has two parts
    //part 1 - to find out if src and dst are in a same set or not
            //if yes, its not safe to add this edge into MST, therefore return false
    //part 2- if not same set, then adjust the set so that src and dst both are in same set, the return true
    private static boolean isSafe(HashMap<Integer, List<Integer>> set, int src, int dst) {
        //find wehre 'src' is taking rest
        int srcIndex = -1;
        for(Integer key:set.keySet()){
            List<Integer> list1 = set.get(key);
            for(Integer i:list1){
                if(i==src){
                    srcIndex = key;
                    break;
                }
            }
        }
        //now its the turn of dst
        int dstIndex = -1;
        for(Integer key:set.keySet()){
            List<Integer> list1 = set.get(key);
            for(Integer i:list1){
                if(i==dst){
                    dstIndex = key;
                    break;
                }
            }
        }
        if(srcIndex == dstIndex){ //both the nodes are in same set, not safe to add the node.
            return false;
        }
        else{
            //the source and destination nodes are in different set
            //therefore, its safe to add this edge into MST
            //adjust the set before returning
            List<Integer> srcList = set.get(srcIndex);
            List<Integer> dstList = set.get(dstIndex);
            for(Integer element:dstList){
                srcList.add(element);
            }
            set.remove(dstIndex);  
            set.put(srcIndex, srcList);
            return true;
        }
    }
    
    private static int finDstIndexInTheList(List<Integer> dstList, int dst) {
        int x = 0;
        for(int i=0; i<dstList.size(); i++){
            if(dstList.get(i) == dst){
                x = i;
                break;
            }
        }
        return x;
    }
    
    private static Edge getEdge(HashMap<Integer, List<Edge>> graph, int minWeight) {
        List<Edge> edgeList = graph.get(minWeight);
        if(edgeList.size() == 1){ 
            // there is only one edge associated with this weight
            // get List from Hash map which contains only one edge, delete the HashMap entry for that weight and return edge
            Edge e = edgeList.get(0);
            graph.remove(minWeight);
            return e;
        }
        else{ 
            //there are multiple edges for this weight
            //get the list, from the list get the first edge
            //remove the first edge from the list, return the edge
            Edge e = edgeList.get(0);
            edgeList.remove(0);
            graph.put(minWeight, edgeList);
            return e;
        }
    }
    
    private static HashMap<Integer, List<Integer>> buildSet(int numNodes) {
        HashMap<Integer, List<Integer>> set = new HashMap<Integer, List<Integer>>();
        for(int i=1; i<=numNodes; i++){
            List<Integer> list1 = new ArrayList<Integer>();
            list1.add(i);
            set.put(i, list1);
        }
        return set;
    }
    
    private static void addEdge(HashMap<Integer, List<Edge>> graph, int source, int destination, int weight) {
        if(graph.get(weight) == null ){ //there is no edge in the graph with the particular weight // create key
            Edge e = new Edge(source, destination);
            List<Edge> l = new ArrayList<Edge>();
            l.add(e);
            graph.put(weight, l);
        }
        else{ //already one edge with given weight exists, so append the list
            Edge e = new Edge(source, destination);
            List<Edge> l = graph.get(weight);
            l.add(e);
            graph.remove(weight);
            graph.put(weight, l);
        }
    }

    private static void printGraph(HashMap<Integer, List<Edge>> graph) {
        System.out.println("");
        for(Integer key:graph.keySet()){
            List<Edge> l = graph.get(key);
            System.out.print("Weight:"+key+"  ");
            for(Edge e:l){
                System.out.print(e.src+"-"+e.dst+"\t");
            }System.out.println("");
        }
    }

    private static void printSet(HashMap<Integer, List<Integer>> set) {
        for(Integer key:set.keySet()){
            List<Integer> list1 = set.get(key);
            System.out.print("Key:"+key+"  ");
            for(Integer i:list1){
                System.out.print(i+" ");
            }System.out.println("");
        }
    }

}

/**
 * Input 1:
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
Output: 19
* 
* 
* 
* Input 2:
4 6
1 2 5
1 3 3
4 1 6
2 4 7
3 2 4
3 4 5
Output: 12 
 */