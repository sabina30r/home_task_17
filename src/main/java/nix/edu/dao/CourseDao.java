package nix.edu.dao;

import nix.edu.entity.Course;

import java.util.List;

public interface CourseDao {

    List<Course> findAll();

    Course findById(Long id);

    void create(Course course);
}
