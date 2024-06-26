import java.util.Scanner;
import java.io.File;
import java.io.PrintWriter;
import java.io.FileNotFoundException;

public class GradebookApp {

    private static Student[] students;
    private static int totalstudents= 0;
    private static String totalstudentss;
    
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        /**Every method is explained case by case in the switch loop
         */
         
        
        
        System.out.println("Welcome to the CM111 Gradebook!");
        System.out.println("");
        boolean running= true;
        while(running) {
            System.out.println("Please select an option");
            System.out.println(" 1 - Initialize the gradebook from a file");
            System.out.println(" 2 - List the class roster");
            System.out.println(" 3 - Read a student's exam score");
            System.out.println(" 4 - Read a student's assignment score");
            System.out.println(" 5 - Enter a student's exam score");
            System.out.println(" 6 - Enter a student's assignment score");          
            System.out.println(" 7 - View Overall Grades for the class");
            System.out.println(" 8 - Save the grade book to a file");
            System.out.println(" 9 - Quit");
            System.out.println("");
            System.out.print("Please make a selection: ");
            
            try{  
            /** This first try method checks if the input from the user is
             * correct or not
             */
            String choice = in.nextLine();
            try {
                /** Using nested try and catch methods is not a good
                 * programming choice. However, this method makes sure that
                 * after the file is loaded no issues happen if the file is a
                 * fake one. The array for the students will be created
                 * but they will have a series of issues. For instance, a null
                 * object could be called or an array out of bound could be created
                 */
            switch(Integer.parseInt(choice)) {
                case 1:
                    /** Case 1 reads a file, since the try and catch method
                     * are built rounf teh switch method, every file can be read
                     * but not all of them will work.
                     * If the first line is not a number though, the user gets
                     * instantly notified. 
                     * In order, the case prompts the user for a file, check the file,
                     * reads the file in a for loop that creats all the objects and stops.
                     * 
                     */
                    try{
                    System.out.println("Type the name of the file with the students' data, specify the file format too (.txt, .CTXT, ...)");
                    String filename = in.nextLine();
                    Scanner inFile = new Scanner( new File( filename ) );
                    totalstudentss= inFile.nextLine();
                    try {
                    totalstudents= Integer.parseInt(totalstudentss);
                } catch (NumberFormatException e) {
                    System.out.println("Review your file.");
                    break;
                }
                    students = new Student[totalstudents];
                    for (int i=0; i<totalstudents; i++) {
                        String rawStudentData = inFile.nextLine();
                        students[i] = new Student();
                        students[i].importStudentData(rawStudentData);
                        
                    }
                    
                } catch (FileNotFoundException e) {
                    System.out.println("Could not open save file");
                }
                   break;
                case 2:
                    /** case 2 reads the roster by calling methods from
                     * the student class. +1 and -1 are around because
                     * arrays start at 0 but IDs start at 1.
                     */
                    for(int i=0; i<=(totalstudents-1); i++) {
                     System.out.printf("ID #%d", i+1);
                     System.out.printf(" %s", students[i].getFirstName());
                     System.out.printf(" %s %n", students[i].getLastName());   
                    }
                    break;
                case 3:
                    /**
                     * Case 3 reads all the exams, you need to selct number
                     * and ID and the program looks for errors in your
                     * input. The boolean foundexamn is an easy way to check
                     * the nputs and stop the program.
                     */
                try {  
                    System.out.println("Declare student ID and exam number");
                    System.out.println("ID selected: ");
                    int ID= in.nextInt();
                    System.out.println("Exam selected: ");
                    String examn= in.next();
                    examn = examn.toLowerCase();
                    boolean foundexamn= false;
                    if(ID>totalstudents || ID<=0) {
                        System.out.println("ID not found");
                        break;
                    }
                    if(examn.equals("1")) {
                           System.out.printf("The grade is: %d %n", students[ID-1].getExam1Score());
                           foundexamn= true;
                        } else if(examn.equals("2")) {
                           System.out.printf("The grade is: %d %n", students[ID-1].getExam2Score());
                           foundexamn= true;
                        } else if(examn.equals("written final score")) {
                           System.out.printf("The grade is: %d %n", students[ID-1].getPracticalFinalScore());
                           foundexamn= true;
                        }else if (examn.equals("practical final score")) {
                           System.out.printf("The grade is: %d %n", students[ID-1].getWrittenFinalScore());
                           foundexamn= true;
                        }else if (examn.equals("3")){
                        System.out.printf("The grade is: %d %n", students[ID-1].getExam3Score());
                        foundexamn= true;
                        }
                    if(!foundexamn) {
                       System.out.println("Exam not found"); 
                    }
                }catch (NumberFormatException e) {
                 System.out.println("Error in response -- enter a valid number");
                }
                    break; 
                case 4:
                   /**
                    * case 4 does the same thing case 3 does, but with assignments
                    */ 
                try{
                   System.out.println("Declare student ID and Assignment number");
                   System.out.println("ID selected: ");
                   int Id= in.nextInt();
                   if(Id>totalstudents || Id<=0) {
                        System.out.println("ID not found");
                        break;
                    }
                   System.out.println("Assignment selected: ");
                   int assignmentn= in.nextInt();
                   if(assignmentn<=0 || assignmentn>Student.TOTAL_ASSIGNMENTS){
                       System.out.println("Assignment not found");
                       break;
                   }
                   System.out.printf("The grade is: %d%n",students[Id-1].getAssignmentScore(assignmentn));
                }catch (NumberFormatException e) {
                 System.out.println("Error in response -- enter a valid number");
                }   
                   break;
                case 5:
                    /**
                     * case 5 allows the user to change a code, it is very
                     * similar to previous cases. However, the code checks
                     * if teh grade assignmed is in the range between 0 and 100.
                     */
                try{
                    System.out.println("Declare student ID and exam number");
                    int IDg= in.nextInt();
                    String examna= in.next();
                    examna = examna.toLowerCase();
                    boolean foundexamng= false;
                   
                    if(IDg>totalstudents || IDg<=0) {
                        System.out.println("ID not found");
                        break;
                    }
                       if(examna.equals("1")) {
                        System.out.println("Declare grade: ");
                        int exam1= in.nextInt();
                        if (exam1>100 || exam1<0) {
                           System.out.println("Grade cannot be assigned");
                           break;
                        }
                        students[IDg-1].setExam1Score(exam1);
                        foundexamng= true;
                    }else if (examna.equals("2")) {
                        System.out.println("Declare grade: ");
                        int exam2= in.nextInt();
                        if (exam2>100 || exam2<0) {
                           System.out.println("Grade cannot be assigned");
                           break;
                        }
                        students[IDg-1].setExam2Score(exam2);
                        foundexamng= true;
                    }else if (examna.equals("3")) {
                        System.out.println("Declare grade: ");
                        int exam3= in.nextInt();
                        if (exam3>100 || exam3<0) {
                           System.out.println("Grade cannot be assigned");
                           break;
                        }
                        students[IDg-1].setExam3Score(exam3);
                        foundexamng= true;
                    }else if (examna.equals("written final score")) {
                        System.out.println("Declare grade: ");
                        int examwritten= in.nextInt();
                        if (examwritten>100 || examwritten<0) {
                           System.out.println("Grade cannot be assigned");
                           break;
                        }
                        students[IDg-1].setWrittenFinalScore(examwritten);
                        foundexamng= true;
                    }else if (examna.equals("practical final score")) {
                        System.out.println("Declare grade: ");
                        int exampractical= in.nextInt();
                        if (exampractical>100 || exampractical<0) {
                           System.out.println("Grade cannot be assigned"); 
                           break;
                        }
                        students[IDg-1].setPracticalFinalScore(exampractical);
                        foundexamng= true;
                    
                    }
                    if(!foundexamng) {
                       System.out.println("Exam not found"); 
                    }
                }catch (NumberFormatException e) {
                 System.out.println("Error in response -- enter a valid number");
                }
                    break;
                case 6:
                    /**
                     * Case 6 does the same thing case does but with assignments.
                     */
                try{
                    System.out.println("Declare student ID and Assignment number");
                    System.out.println("ID selected: ");
                    int Ids= in.nextInt();
                    if(Ids>totalstudents || Ids<=0) {
                        System.out.println("ID not found");
                        break;
                    }
                    System.out.println("Assignment selected: ");
                    int assignmentns= in.nextInt();
                    if(assignmentns<=0 || assignmentns>Student.TOTAL_ASSIGNMENTS){
                       System.out.println("Assignment not found");
                       break;
                    }
                    System.out.println("Declare grade: ");
                    int assignmentscore= in.nextInt();
                    if (assignmentscore>100 || assignmentscore<0) {
                           System.out.println("Grade cannot be assigned");
                           break;
                        }
                    students[Ids-1].setAssignmentScore(assignmentns, assignmentscore);
                }catch (NumberFormatException e) {
                 System.out.println("Error in response -- enter a valid number");
                }   
                    break;
                case 7:
                    /**
                     * case 7 prints all the information, different methods are called
                     * from the student class.
                     */
                    for(int i=0; i<=(totalstudents-1); i++) {
                    System.out.printf("%s %s %.2f%n", students[i].getFirstName(), students[i].getLastName(), students[i].getOverallGrade());
                }
                    break;
                case 8: 
                    /**
                     * Case use the printwriter command to w=create a new file based
                     * on teh inromation provided over the course of the program.
                     * The user prompts a name for teh file and the program checks 
                     * if everything runs well and the it closes.
                     */
                    try {
                        System.out.println("Type the name of the file with the students' data to create, specify the file format too (.txt, .CTXT, ...)");
                        String GradebookAppFile = in.nextLine();
                        
                        PrintWriter output = new PrintWriter(GradebookAppFile);
                        
                        output.println(totalstudentss);
                        
                        for(Student student : students) {
                            output.println(student.exportStudentData());
                        }
                        output.close();
                        running = false;
                        System.out.println("Data saved");
                    } catch(FileNotFoundException e) {
                        System.out.println("Could not open save file for writing");
                        System.out.println("Not closing program, try again");
                    }
                    
                    break;
                case 9:
                    /**
                     * case 9 stops the program if 0 errors have happened.
                     */
                    System.exit(0);
                    break;
                    
                default:
                    System.out.println("Unrecognized response: " + choice);
            }
        } catch (NullPointerException e) {
            System.out.println(" ");
            System.out.println("The file you entered was invalid, restart the program");
            running= false;
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println(" ");
            System.out.println("The file you entered was invalid, restart the program");
            running=false;
        }
            } catch (NumberFormatException e){
            System.out.println("Enter a valid number");
        }


        }
        
        }

    
    
    public static int[][] getAssignmentGrades() {
        /**
         * The method creates a multidimensional array with all the grades
         * of the assignments for every student.
         * A different array is created to keep the main array in Student
         * private. Last commitment.
         */
        int[][] grades = new int[totalstudents][Student.TOTAL_ASSIGNMENTS];
        for( int i= 0; i<=(totalstudents-1); i++) {
        grades[i] = students[i].getAssignmentScorearray();
        }
        return grades;
    }
    
}
