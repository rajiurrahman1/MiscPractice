/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BitManipulation;

import java.util.Scanner;

/**
 *
 * @author Rajiur
 */
public class MaximizingXor {
    /*
 * Complete the function below.
 */
    
    public static int findClosestMultipleOf2(int xor){
        int i=0;
        for(i=0; i<100000; i++){
            if( Math.pow(2,i) > xor ){
                break;
            }
        }
        int res = (int) Math.pow(2, i);
        return res;
    }
    
    public static int maxXor(int l, int r) {
        int xor = l^r;
        int multipleOf2 = findClosestMultipleOf2(xor);
        return multipleOf2-1;
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int res;
        int _l;
        _l = Integer.parseInt(in.nextLine());
        
        int _r;
        _r = Integer.parseInt(in.nextLine());
        
        res = maxXor(_l, _r);
        System.out.println(res);
        
    }
}
