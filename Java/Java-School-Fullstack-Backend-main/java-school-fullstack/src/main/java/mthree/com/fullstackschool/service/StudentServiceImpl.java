package mthree.com.fullstackschool.service;

import mthree.com.fullstackschool.dao.StudentDao;
import mthree.com.fullstackschool.model.Course;
import mthree.com.fullstackschool.model.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class StudentServiceImpl implements StudentServiceInterface {

    //YOUR CODE STARTS HERE

    private StudentDao studentDao;

    public StudentServiceImpl(StudentDao studentDao) {
        this.studentDao = studentDao;
    }


    //YOUR CODE ENDS HERE

    public List<Student> getAllStudents() {
        //YOUR CODE STARTS HERE

        return studentDao.getAllStudents();

        //YOUR CODE ENDS HERE
    }

    public Student getStudentById(int id) {
        //YOUR CODE STARTS HERE

        Student student = studentDao.findStudentById(id);

        if (student == null) {
            student = new Student();
            student.setStudentId(id);
            student.setStudentFirstName("Student Not Found");
            student.setStudentLastName("Student Not Found");
        }

        return student;

        //YOUR CODE ENDS HERE
    }

    public Student addNewStudent(Student student) {
        //YOUR CODE STARTS HERE
        boolean add = true;

        if (student.getStudentFirstName().isBlank()) {
            student.setStudentFirstName("First Name blank, student NOT added");
            add = false;
        }

        if (student.getStudentLastName().isBlank()) {
            student.setStudentLastName("Last Name blank, student NOT added");
            add = false;
        }

        if (!add) return student;

        return studentDao.createNewStudent(student);

        //YOUR CODE ENDS HERE
    }

    public Student updateStudentData(int id, Student student) {
        //YOUR CODE STARTS HERE

        if (id != student.getStudentId()) {
            student.setStudentFirstName("IDs do not match, student not updated");
            student.setStudentLastName("IDs do not match, student not updated");
            return student;
        }

        studentDao.updateStudent(student);

        return student;

        //YOUR CODE ENDS HERE
    }

    public void deleteStudentById(int id) {
        //YOUR CODE STARTS HERE

        studentDao.deleteStudent(id);

        //YOUR CODE ENDS HERE
    }

    public void deleteStudentFromCourse(int studentId, int courseId) {
        //YOUR CODE STARTS HERE

        if (getStudentById(studentId).getStudentFirstName().equals("Student Not Found")) {
            System.out.println("Student not found");
            return;
        }

        //YOUR CODE ENDS HERE
    }

    public void addStudentToCourse(int studentId, int courseId) {

        //YOUR CODE STARTS HERE

        if (getStudentById(studentId).getStudentFirstName().equals("Student Not Found")) {
            System.out.println("Student not found");
            return;
        }

        studentDao.addStudentToCourse(studentId, courseId);
        //YOUR CODE ENDS HERE
    }
}
