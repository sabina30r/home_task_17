package nix.edu.dao;

import nix.edu.entity.Lesson;
import nix.edu.entity.Student;

import java.util.List;

public interface StudentDao {
    List<Student> findAll();

    Student findById(Long id);

    void create(Student student);

    Lesson showNextLessonInfoByStudentId(Long studentId);
}
