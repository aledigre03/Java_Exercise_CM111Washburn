import java.util.Scanner;
import java.io.*;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/** Tests for CM111 Assignment #11
 * @author  Joseph Kendall-Morwick <Joseph.Kendall-Morwick@washburn.edu>
 */
public class A8Test {

    @Test
    public void setNameTest()
    {
        Student s = new Student();
        s.setFirstName("Ada");
        s.setLastName("Lovelace");
        assertEquals(s.getFirstName() + " " + s.getLastName(), "Ada Lovelace");
    }

    // @Test
    // public void getSetExam1Test()
    // {
        // Student s = new Student();
        // s.setExam1Score(84);
        // assertEquals(s.getExam1Score(), 84);
        // s.setExam1Score(72);
        // assertEquals(s.getExam1Score(), 72);
    // }

    // @Test
    // public void getSetExam2Test()
    // {
        // Student s = new Student();
        // s.setExam2Score(39);
        // assertEquals(s.getExam2Score(), 39);
        // s.setExam2Score(99);
        // assertEquals(s.getExam2Score(), 99);
    // }

    // @Test
    // public void exam1Exam2UniqueTest()
    // {
        // Student s = new Student();
        // s.setExam1Score(39);
        // s.setExam2Score(99);
        // assertEquals(s.getExam1Score(), 39);
        // assertEquals(s.getExam2Score(), 99);
    // }

    // @Test
    // public void simpleExamAverageTest()
    // {
        // Student s = new Student();
        // s.setExam1Score(0);
        // s.setExam2Score(1);
        // s.setExam3Score(2);
        // s.setPracticalFinalScore(3);
        // s.setWrittenFinalScore(4);
        // assertEquals((int)s.getExamAverage(), 2);
    // }

    // @Test
    // public void typicalExamAverageTest()
    // {
        // Student s = new Student();
        // s.setExam1Score(64);
        // s.setExam2Score(87);
        // s.setExam3Score(76);
        // s.setPracticalFinalScore(82);
        // s.setWrittenFinalScore(92);
        // assertEquals((int)s.getExamAverage(), 84);
    // }

}