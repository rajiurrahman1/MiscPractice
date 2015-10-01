
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author Rajiur
 */
public class JourneyToTheMoon {
    
    public static void main(String[] args){
        Scanner in = new Scanner(System.in);
        int numNodes = in.nextInt();
        int numEdges = in.nextInt();
        HashMap<Integer, List<Integer>> set = buildSet(numNodes);
        
//        printSet(set);
        for(int i=0; i<numEdges; i++){
            int a1 = in.nextInt();
            int a2 = in.nextInt();
            adjustSet(set, a1, a2);
//            printSet(set);
        }
        if(numNodes <= 100000 && numEdges<= 10000){
            long numCombinations = getNumberCombinations(set, numNodes);
            System.out.println(numCombinations);
        }
    }
    
    private static long getNumberCombinations(HashMap<Integer, List<Integer>> set, Integer numNodes) {
        long numNodesLong = numNodes.longValue();
        long nC2 = calculateNcK(numNodesLong, 2);
        for(Integer key:set.keySet()){
            List<Integer> list1 = set.get(key);
            Integer listSize = list1.size();
            long listSizeLong = listSize.longValue();
            nC2 = nC2 - calculateNcK(listSizeLong, 2);
        }
        
        return nC2;
    }

    private static long calculateNcK(long n, long k) {
        if(k == 0){
            return 1;
        }
        else{
            return calculateNcK(n, k-1)*(n-(k-1))/k;
        }
    }

    private static void adjustSet(HashMap<Integer, List<Integer>> set, int src, int dst) {
        src = src+1;
        dst = dst+1;
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
        if(srcIndex != dstIndex){
//            System.out.println("src:"+src+"\tdst:"+dst);
            List<Integer> srcList = set.get(srcIndex);
            List<Integer> dstList = set.get(dstIndex);
            for(Integer element:dstList){
                srcList.add(element);
            }
            set.remove(dstIndex);  
            set.put(srcIndex, srcList);
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
    
    private static void printSet(HashMap<Integer, List<Integer>> set) {
        for(Integer key:set.keySet()){
            List<Integer> list1 = set.get(key);
            System.out.print("Key:"+key+"  ");
            for(Integer i:list1){
                System.out.print(i+" ");
            }System.out.println("");
        }System.out.println("");
    }
    
}


/*
*
Input:
11 6
0 2
0 3
0 4
5 6
5 7 
8 9

Output: 
*/