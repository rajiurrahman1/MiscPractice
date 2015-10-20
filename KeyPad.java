import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author Rajiur
 * 
 * Write a program that converts number to characters (simulating mobile keypad)
 * The character '#' represents a blank space
 */


public class KeyPad {
    public static void main(String[] args){
        HashMap<Character, List<Character>> numPad = BuildHashMap();
        Scanner in = new Scanner(System.in);
        String input = in.nextLine();
        
        String[] tokens = input.split("#");
        for(String tok: tokens){
            if(tok.length() > 1){ //user has either pressed different keypands or pressed same keypad more than once
                int count = 0;
//                int numCharsInKey = numPad.get(tok.charAt(0)).size(); //lets assume the input is correct
                Character cCurrent, cNext; 
                for(int i=0; i<tok.length()-1; i++){
                    cCurrent = tok.charAt(i);
                    cNext = tok.charAt(i+1);
                    if(cCurrent == cNext){ //same key pressed twice or more
                        count ++;
                        if(count == numPad.get(cCurrent).size()-1 ){
                            System.out.println(numPad.get(cCurrent).get(count+1));
                            count = 0;
                        }
                    }
                    else{ 
                        if(count == 0){//keys are different, print the Character
                            System.out.print(numPad.get(cCurrent).get(0));
                        }
                        else{
                            System.out.print(numPad.get(cCurrent).get(count));
                            count = 0;
                        }
                    }
                }
                if(count == 0){
                    System.out.print(numPad.get(tok.charAt(tok.length()-1)).get(0));
                }
                else{
                    System.out.print(numPad.get(tok.charAt(tok.length()-1)).get(count));
                }
            }
            else{ //one number then #
                System.out.print(numPad.get(tok.charAt(0)).get(0));
            }
            System.out.print(" ");
        }
        
    }

    private static HashMap<Character, List<Character>> BuildHashMap() {
        HashMap<Character, List<Character>> numPad = new HashMap<Character, List<Character>>();
        numPad.put('1', new ArrayList<>());
        numPad.put('2', Arrays.asList('A', 'B', 'C'));
        numPad.put('3', Arrays.asList( 'D', 'E', 'F' ) );
        numPad.put('4', Arrays.asList( 'G', 'H', 'I' ) );
        numPad.put('5', Arrays.asList( 'J', 'K', 'L' ) );
        numPad.put('6', Arrays.asList( 'M', 'N', 'O' ) );
        numPad.put('7', Arrays.asList( 'P', 'Q', 'R', 'S' ) );
        numPad.put('8', Arrays.asList( 'T', 'U', 'V' ) );
        numPad.put('9', Arrays.asList( 'W','X', 'Y', 'Z' ) );
        numPad.put('0', new ArrayList<Character>() );
        
        return numPad;
    }
}
