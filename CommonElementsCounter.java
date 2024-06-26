import java.util.Scanner;

public class Assignment {
  public static void main(String[] args) {
      Scanner in = new Scanner(System.in);
      
      System.out.print("Enter a sequence of integers, separated by commas: ");
      String line = in.nextLine();
      String[] tokens = line.split(",");
      int[] sequence1 = new int[tokens.length];
      for(int i=0; i<tokens.length; i++) {
          sequence1[i] = Integer.parseInt(tokens[i].trim());
      }
      System.out.print("Enter another sequence of integers, separated by commas: ");
      line = in.nextLine();
      tokens = line.split(",");
      int[] sequence2 = new int[tokens.length];
      for(int i=0; i<tokens.length; i++) {
          sequence2[i] = Integer.parseInt(tokens[i].trim());
      }
      System.out.printf("The two sequences have %d numbers in common.%n", 
          commonElements(sequence1, sequence2));
  }
  public static int commonElements(int[] sequence1, int[] sequence2){
      int count= 0;
      for(int i=0; i < sequence1.length; i++) {
          int difference = Math.abs(sequence1[i] - sequence2[i]);
          if (difference == 0) {
              count++;
          }
    
      } 
      //the loop looks for equal numbers going from left to right through the arrays
      int i=0;
      int u=2;
      while(i < sequence1.length && count<3 && u < sequence2.length) {
          int difference = Math.abs(sequence1[i] - sequence2[u]);
          if (difference == 0) {
              count++;
          }
          if (difference == 0 && i == u) {
             count--; 
          }
          i++;
          u--;
      }
      /** the loop looks for equal numbers when the first array goes from left to right
       *  and the second goes from right to left.
       *  Note u< sequence2.length to avoid going out of bounds  
       */
      i= 0;
      int z= 0;
      while(i <sequence1.length && count<3) {
         int difference = Math.abs(sequence1[i] - sequence2[z]);
         if (difference == 0 && i != z) {
              count++;
          }
         i++;
         if (i>=2){
             z++;
         }
      }
      /** the loop looks for same numbers in teh arrays but while the first one goes from
       *  0 to i, the second one goes from i-1 to 2.
       *  Therefore, it takes in account the options not considered by the other loops.
       *  It is limited for arrays where max i=3.
       */
      return count;
  }
}
