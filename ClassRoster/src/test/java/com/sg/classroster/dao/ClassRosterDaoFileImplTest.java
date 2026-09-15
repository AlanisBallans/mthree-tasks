package com.sg.classroster.dao;

import com.sg.classroster.dto.Student;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.FileWriter;
import java.sql.PseudoColumnUsage;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ClassRosterDaoFileImplTest {

    ClassRosterDao testDao;

    public ClassRosterDaoFileImplTest() {
    }

    @BeforeEach
    public void setUp() throws Exception {
        String testFile = "testroster.txt";
        new FileWriter(testFile);
        testDao = new ClassRosterDaoFileImpl(testFile);
    }

    @Test
    void testAddGetStudent() throws Exception {
        String studentId  = "0001";
        Student student = new Student(studentId);
        student.setFirstName("Ada");
        student.setLastName("Lovelace");
        student.setCohort("Java-May-1845");

        testDao.addStudent(studentId, student);
        Student retrievedStudent = testDao.getStudent(studentId);

        assertEquals(student.getStudentId(), retrievedStudent.getStudentId(), "Checking student id.");
        assertEquals(student.getFirstName(), retrievedStudent.getFirstName(), "Checking student first name");
        assertEquals(student.getLastName(), retrievedStudent.getLastName(), "Checking student last name");
        assertEquals(student.getCohort(), retrievedStudent.getCohort(), "Checking student cohort");
    }

    @Test
    void testGetAllStudents() throws Exception {
        Student firstStudent = new Student("0001");
        firstStudent.setFirstName("Ada");
        firstStudent.setLastName("Lovelace");
        firstStudent.setCohort("Java-May-1845");

        Student secondStudent = new Student("0002");
        secondStudent.setFirstName("Charles");
        secondStudent.setLastName("Babbage");
        secondStudent.setCohort(".NET-May-1845");

        testDao.addStudent(secondStudent.getStudentId(), secondStudent);
        testDao.addStudent(firstStudent.getStudentId(), firstStudent);

        List<Student> allStudents = testDao.getAllStudents();

        assertNotNull(allStudents, "The list of students must not be null");
        assertEquals(2, allStudents.size(), "List should have 2 students");

        assertTrue(testDao.getAllStudents().contains(firstStudent));
        assertTrue(testDao.getAllStudents().contains(secondStudent));
    }

    @Test
    void testRemoveStudent() throws Exception {
        Student firstStudent = new Student("0001");
        firstStudent.setFirstName("Ada");
        firstStudent.setLastName("Lovelace");
        firstStudent.setCohort("Java-May-1845");

        Student secondStudent = new Student("0002");
        secondStudent.setFirstName("Charles");
        secondStudent.setLastName("Babbage");
        secondStudent.setCohort(".NET-May-1845");

        testDao.addStudent(secondStudent.getStudentId(), secondStudent);
        testDao.addStudent(firstStudent.getStudentId(), firstStudent);

        Student removedStudent = testDao.removeStudent(firstStudent.getStudentId());

        assertEquals(removedStudent, firstStudent, "The removed student should be Ada");

        List<Student> allStudents = testDao.getAllStudents();

        assertNotNull(allStudents, "The list of students must not be null");
        assertEquals(1, allStudents.size(), "List should have 1 student");

        assertFalse(testDao.getAllStudents().contains(firstStudent), "List should not contain Ada");
        assertTrue(testDao.getAllStudents().contains(secondStudent), "List should contain Charles");

        removedStudent = testDao.removeStudent(secondStudent.getStudentId());
        assertEquals(removedStudent, secondStudent, "The removed student should be Charles");

        allStudents = testDao.getAllStudents();
        assertTrue(allStudents.isEmpty(), "List should be empty");

        Student retrievedStudent = testDao.getStudent(firstStudent.getStudentId());
        assertNull(retrievedStudent, "Ada was removed, should be null");

        retrievedStudent = testDao.getStudent(secondStudent.getStudentId());
        assertNull(retrievedStudent, "Charles was removed, should be null");
    }
}