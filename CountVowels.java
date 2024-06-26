import java.util.Scanner;
public class Assignment {
    public static void main (String[] args) {
        Scanner in = new Scanner(System.in);
        System.out.println("Enter a word: ");
        String WORD = in.nextLine();
      //this is my original string, which can contain capital letters
        int l=0;
      //l is a general variable for my loop to go through every character in my string while my if statements
      //will make a check if there's a vowel or not
        String word;
        word= WORD.toLowerCase();
      //I am using lowercase letters in my program
        int numberOfVowels = 0;
      do  {
            if(word.charAt(l) == 'a') {
                numberOfVowels++;
            }
            if(word.charAt(l) == 'e') {
                numberOfVowels++;
            }
            if(word.charAt(l) == 'i') {
                numberOfVowels++;
            }
            if(word.charAt(l) == 'o') {
                numberOfVowels++;
            }
            if(word.charAt(l) == 'u') {
                numberOfVowels++;
            }
            if(word.charAt(l) == 'y') {
                numberOfVowels++;
            }
            l++;
          //every time l has a + 1 to go to the next character
 }while(l<word.length());
      //until l will be less than the length of my string my loop will keep going,
      //if I used <= I would have had the "string index out of range" error for instance
        System.out.printf("%d%n", numberOfVowels);
    }
}
