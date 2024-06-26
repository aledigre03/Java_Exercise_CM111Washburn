
public class Student {
    private String firstName;
    private String lastName;
    private int exam1Score;
    private int exam2Score;
    private int exam3Score;
    private int PracticalFinalScore;
    private int WrittenFinalScore;
    public static final int TOTAL_ASSIGNMENTS = 11;
    private static int[] assScores= new int[TOTAL_ASSIGNMENTS];
    public int[] targetArray= new int[assScores.length];
    public String getFirstName() { 
        return firstName;
    }
    
    public void setFirstName(String newFirstName) {
        firstName = newFirstName;
    }
    
    public String getLastName() {
        return lastName;
    }
    
    public void setLastName(String newLastName) {
        lastName = newLastName;
    }
    
    public void setExam1Score(int newExam1Score) {
      exam1Score = newExam1Score;  
    }
    
    public void setExam2Score(int newExam2Score) {
       exam2Score = newExam2Score; 
    }
    
    public void setExam3Score(int newExam3Score) {
       exam3Score = newExam3Score; 
    }
    
    public void setPracticalFinalScore(int newPracticalFinalScore) {
       PracticalFinalScore = newPracticalFinalScore; 
    }
    
    public void setWrittenFinalScore(int newPracticalFinalScore) {
        WrittenFinalScore= newPracticalFinalScore;
    }
    
    public int getExam1Score() {
        return exam1Score;
    }
      
    public int getExam2Score() {
        return exam2Score;
    }
    
    public int getExam3Score() {
        return exam3Score;
    }
    
    public int getPracticalFinalScore() {
        return PracticalFinalScore;
    }
    
    public int getWrittenFinalScore() {
        return WrittenFinalScore;
    }
    
    public int getExamAverage() {
        int ExamAverage=0;
        if(exam1Score<exam2Score && exam1Score<exam3Score && exam1Score<PracticalFinalScore && exam1Score<WrittenFinalScore) {
            ExamAverage= (exam2Score+exam3Score+PracticalFinalScore+WrittenFinalScore)/4;
        }else if(exam2Score<exam1Score && exam2Score<exam3Score && exam2Score<PracticalFinalScore && exam2Score<WrittenFinalScore) {
            ExamAverage= (exam1Score+exam3Score+PracticalFinalScore+WrittenFinalScore)/4;
        }else if(exam3Score<exam1Score && exam3Score<exam2Score && exam3Score<PracticalFinalScore && exam3Score<WrittenFinalScore) {
            ExamAverage= (exam1Score+exam2Score+PracticalFinalScore+WrittenFinalScore)/4;
        }else if(PracticalFinalScore<exam1Score && PracticalFinalScore<exam2Score && PracticalFinalScore<exam3Score && PracticalFinalScore<WrittenFinalScore) {
            ExamAverage= (exam1Score+exam2Score+exam3Score+WrittenFinalScore)/4;
        }else if(WrittenFinalScore<exam1Score && WrittenFinalScore<exam2Score && WrittenFinalScore<PracticalFinalScore && WrittenFinalScore<exam3Score) {
            ExamAverage= (exam1Score+exam2Score+exam3Score+PracticalFinalScore)/4;
        }
        
        return ExamAverage;
    }
    
    
    public void setAssignmentScore(int assignmentnumber, int score) {
        
        assScores[assignmentnumber-1]= score;
    
    }
    
    
    
    public double getAssignmentScore(int assignmentnumber) {
       return assScores[assignmentnumber-1]; 
    }
    
    public double getAssignmentAverage() {
       double sum=0;
       double lowest=101;
       double finalsum=0;
       double avg;
       for(int i=0; i<targetArray.length; i++) {
          sum += targetArray[i];
          if(targetArray[i]< lowest) {
              lowest=targetArray[i];
          }else {
              lowest= lowest;
          }
          
       }
       finalsum= sum-lowest;
       avg= finalsum/(TOTAL_ASSIGNMENTS-1);
       
       return avg;
    
    }
    
    public double getOverallGrade(){
        double overall= (getExamAverage()+getAssignmentAverage())/2;
        return overall;
        
    }
    
    public void importStudentData(String rawData) {
        
        String[] studentdata = rawData.split(",");
        firstName = studentdata[0];
        lastName = studentdata[1];
        exam1Score= Integer.parseInt(studentdata[2]);
        exam2Score= Integer.parseInt(studentdata[3]);
        exam3Score= Integer.parseInt(studentdata[4]);
        WrittenFinalScore= Integer.parseInt(studentdata[5]);
        PracticalFinalScore= Integer.parseInt(studentdata[6]);
    
        
        
        for(int i=0; i<assScores.length; i++) {
            assScores[i] = Integer.parseInt(studentdata[7+i]);
        }
        for(int i=0; i<targetArray.length; i++) {
            targetArray[i] = Integer.parseInt(studentdata[7+i]);
        }
        
    }
    
    public String exportStudentData() {
        String exportedData = firstName + "," + lastName + "," + exam1Score + "," + exam2Score + "," + exam3Score + "," + WrittenFinalScore + "," + PracticalFinalScore;
        for(int i=0; i<assScores.length; i++) {
            exportedData = exportedData + "," + targetArray[i];
        }
        return exportedData;
    }
    
    public int[] getAssignmentScorearray() {
        return targetArray;
    }
}
