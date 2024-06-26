import java.util.Scanner;

public class Assignment {
  public static void main(String[] args) {
      Scanner in = new Scanner(System.in);
      
      System.out.print("Enter the cost of the item in cents: ");
      int cost = in.nextInt();
      System.out.print("Enter the number of quarters: ");
      int quarters = in.nextInt();
      System.out.print("Enter the number of dimes: ");
      int dimes = in.nextInt();
      System.out.print("Enter the number of nickels: ");
      int nickels = in.nextInt();
      System.out.print("Enter the number of pennies: ");
      int pennies = in.nextInt();
      
      if(exactChange(cost, quarters, dimes, nickels, pennies)) {
          System.out.println("Exact change can be made");
      } else {
          System.out.println("Exact change can not be made");
      }
      
  }
  public static boolean exactChange( int cost, int quarters, int dimes, int nickels, int pennies) {
      if(quarters >= 1) { //for every kind of unit I need to remove a precise amount and only under spefici conditions I can keep removing after
          cost = cost-25*(cost/25);
          if((cost)/10 <= dimes && cost/10 >=1 && cost-10*(cost/10)>=0) { //cost/10 >=1 makes sure that I'm not working on something that doesn't exist
              cost= cost-10*(cost/10); //the last boolean makes sure that I'm not going negative while removing units
              if((cost)/5 <= nickels && cost/5 >=1 && cost-5*(nickels/5) >=0) {
                  cost = cost-5*(nickels/5);
                  if(cost <= pennies || cost==5) {//only when cost=5 my equation doesn't work and since nickels are the last unit I can afford to treat the case as a special one
                      return true;
                  } else {
                      return false;
                  }
              } else {
                  cost=cost;
              }
          } else {
              cost = cost;
          }
      }else {
          cost = cost;
      }
      if(dimes >= 1 && cost/10 >= 1 && cost-10*(cost/10)>=0) {
         cost= cost-10*(cost/10);
         if((cost)/5 <= nickels && cost/5 >= 1 && cost-5*(nickels/5) >=0) {
           cost = cost-5*(nickels/5); 
           if(cost <= pennies || cost==5) {
               return true;
               } else {
               return false;
           }
         }else {
             cost=cost;
         }
      } else {
          cost=cost;
      }
      if(nickels >= 1 && cost/5 >= 1 && cost-5*(nickels/5) >=0) {
          cost = cost-5*(nickels/5); 
          if(cost <= pennies || cost==5) {
             return true; 
          } else {
              cost=cost;
          }
      }else {
          cost=cost;
      }
      if(pennies>=1) {
         cost = cost - pennies;
         if (cost<=0) {
                    return true;
                    
                }else {
                    return false;
               } 
      } else {
          return false;
      }
  }
}
