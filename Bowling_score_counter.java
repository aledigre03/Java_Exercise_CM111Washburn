import java.util.Scanner;
public class Assignment
{
    public static void main ( String[] args)
   {
       Scanner in = new Scanner(System.in);
       int points = 0;
       int count = 0;
       //these are the only two variables needed outside booleans and loops
       while(count<9) {
           //I made a loop for the first 9 throws, it's not <= because 9 is not related to the number of throws but to the positions where I put the count variable.
           
           int pins = in.nextInt();
        
         if(pins<10) {
           
             //possibility that we hit less than 10 pins
           int turn2pins = in.nextInt();
           points = pins + points + turn2pins;
       
           count = count+1;
           if(pins + turn2pins == 10) {
               //code for spare
               int turnafterspare = in.nextInt();
               points = points + turnafterspare*2;
               int turnafterspare2 = in.nextInt();
               points = turnafterspare2 + points;
               count = count + 1;
           }

       }
     
      if(pins==10) {
           count= count + 1;
           //boolean for a strike
        
           int pinsafterstrike = in.nextInt();
           //what happens after a strike needs to be taken in account, I can't just come back at the beggining of the loop.
           if(pinsafterstrike==10) {
               count= count +1;
               while(pinsafterstrike==10 && count<9){
                   //doing multiple strikes cause a loop, but to create a loop like that and being able to add an "exit" taking in account that 
                   //after multiple strikes your score is dictated by future variables is very hard.
                   //I tried my best
                   int pinsformorestrike = in.nextInt();
                   if(pinsformorestrike==10 && count<9){
                       count+=1;
                       if(count<9){
                           //this "if" sentence is required when my strikes are so many that will reach the next loop for the last shots,
                           //basically it allows me to have 12 total shots instead of 13.
                       int pinsloop= in.nextInt();
                       if(pinsloop==10&&count<9){
                           points= points + 80; 
                           pinsafterstrike=pinsafterstrike;
                           count+=1;
                           //this is what happens after you get all the strikes that allows you to reach this point, however even if I get 300 points
                           //as total if I have 12 strikes, I don't know if the code works with less than 12 and more than 4 strikes.
                           //Bowling is harder than quantum mechanics. I can't figure out a better formula than this one.
                       }
                       else if(pinsloop<10&&count<9){
                           //this is my way out of the strikes loop which should take in account all the strikes done before and the throws after.
                           int pinsloop2= in.nextInt();
                           points = points + 2*(pinsloop+pinsloop2) + 60;
                           count+=1;
                           pinsafterstrike=pinsafterstrike+1;
                       }
                    }
                   }
                   else if(pinsformorestrike<10 && count<9){
                       pinsafterstrike=pinsafterstrike + 1;
                       //this is a second way out for my loop.
                       //It exists because for every variable I need to consider the possibility for a strike.
                       int pinsoutloop= in.nextInt();
                       points = points + 2*(pinsformorestrike+pinsoutloop) + 30;
                       count+=1;
                   }
               }
           }
           else if(pinsafterstrike<10) {
            //this is my code for a single strike
            //it's a simple equation. Evidently, more elegant than whatever happened in the loop-tripleboolean from above.
           int turn2pins = in.nextInt();
           points = points + 2*(turn2pins+pinsafterstrike) + 10;
           count = count+1;
        }
       }
    
    
    }
    
    while(count<11 && count>=9) {
        //this is the loop for the last throws that takes in account all the rules.
        int pins = in.nextInt();
        if(pins==10){
          points += 10;
          count = count + 1;
         //strike  
          int turn2pins = in.nextInt();
        
         if(turn2pins==10){
           points += 20;
           //strike 2 
           count += 1;
           int turn3pins = in.nextInt();
           if(turn3pins<10){
               points += turn3pins;
               //failed third strike
           }
           if(turn3pins==10) {
               points +=30;
               count += 1;
               //strike 3
           }
           
        
          
          }
        
        if((turn2pins)<10){
            points += pins + turn2pins;
            count = count + 2;
            //no third throw, indeed count is +2
            
        }
        }
        if(pins<10){
            //no strike
            count= count + 1;
              
          int turn2pins = in.nextInt();
          if((pins+turn2pins)<10){
            points += pins + turn2pins;
            count = count + 2;
            //no spare and no third throw
            
              if((pins+turn2pins)==10){
            //spare
           int turn3pins = in.nextInt();
           if(turn3pins<10) {
               points += pins + turn2pins + turn3pins;
           count = count + 1;
           //failed third strike
           }
           
           if(turn3pins==10) {
               points = points + 20;
               count+=1;
               //strike after the spare
           }
        }
        }
        
        }
        
       
        
        
        
    }
    
    System.out.printf("The final score is: %d%n", points);
    }
    
    
    
    
}
