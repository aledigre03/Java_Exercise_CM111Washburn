import java.util.Scanner;
//import has been added to make the user choose the value of the variables
 public class Assignment {
  
    public static void main( String[] args)
    {
        Scanner in = new Scanner(System.in);
        double left;
        double right;
        double y;
     //all the variables are set
        System.out.println("Enter a value for the mean: ");
        double mean = in.nextDouble();
        System.out.println("Enter a value for the standard deviation: ");
        double StdDe = in.nextDouble();
        System.out.println( "Enter a value for the x: ");
        double x= in.nextDouble();
     //the prompts have been written with all the variables that can be choosen by the user
        
        left=1/(StdDe*Math.pow(2*Math.PI, 0.5));
     // equation for the left side of my gaussian
        right=Math.pow(Math.E, (-0.5)*Math.pow(((x-mean)/StdDe), 2.0));
     //equation for the power of e
        y=right*left;
     //combination of my variables, I didn't do a singles string for the equation. Having my code split in different parts allow me to clearly view what I did wrong in the process
        System.out.println("The result is:");
        System.out.printf( "%.3f%n", y);
      
  }
}
