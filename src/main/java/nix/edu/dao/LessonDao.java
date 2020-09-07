package nix.edu.dao;

import nix.edu.entity.Lesson;

import java.time.LocalDate;
import java.util.List;

public interface LessonDao {

    List<Lesson> findAll();

    Lesson findById(Long id);

    Lesson findNextLesson(LocalDate date, Long groupId);

}
