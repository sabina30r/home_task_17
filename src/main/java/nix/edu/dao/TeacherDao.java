package nix.edu.dao;

import nix.edu.entity.Group;
import nix.edu.entity.Teacher;

import java.util.List;
import java.util.Optional;

public interface TeacherDao {
    List<Teacher> findAll();

    Teacher findById(Long id);

    void create(Teacher teacher);

    Optional<Group> getTheMostSuccessfulGroupByTeacherId(Long teacherId);
}
