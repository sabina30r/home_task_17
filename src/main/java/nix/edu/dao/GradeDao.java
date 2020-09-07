package nix.edu.dao;

import nix.edu.entity.Grade;

import java.util.List;

public interface GradeDao {
    List<Grade> findAll();

    Grade findById(Long id);

    void create(Grade grade);

    List<Grade> findGradesByLessonId(Long lessonId);
}
