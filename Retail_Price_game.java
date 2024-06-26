import java.util.Scanner;
public class Assignment
{
    public static void main ( String[] args)
    {
        Scanner in = new Scanner(System.in);
        
        
        System.out.println("Contestant 1, state your bid: ");
        double bid1 = in.nextDouble();
        System.out.println("Contestant 2, state your bid: ");
        double bid2 = in.nextDouble();
        System.out.println("actual retail price: ");
        double actualRetailPrice = in.nextDouble();
       //prompts and variables have been set 
        if(bid1>bid2){
            if(actualRetailPrice>=bid1)
            System.out.println("contestant 1 wins");
           //this "if statement" allows contestant 1 to win based on the main rule of the game 
            else if(actualRetailPrice>=bid2)
            System.out.println("contestant 2 wins");
            //this if statement allows constestant 2 to win when constestant 1 had a bid higher than 2 but more than the actual retail price
            else
            System.out.println("no winner");
            //else allows me to print a "no winner statement" when both the constestants are over the bid or something different happened
        }
        
            
        
        if(bid1<bid2){
            if(actualRetailPrice>=bid2)
                System.out.println("contestant 2 wins");
                else if(actualRetailPrice>=bid1)
                System.out.println("contestant 1 wins");
            else
            System.out.println("no winner");
        }
        //this whole boolean is the exact opposite of the other. It allows contestant 2 to win and the other things that the over boolean covered
        if(bid1==bid2){
            System.out.println("no winner");
        }
    }
          //this last boolean is useless if we think that such a thing can't happen during the game but I wanted my program to print something for the occasion anyways
        
    
    
        
        
        
    
    
}
