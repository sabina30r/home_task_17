package nix.edu.dao.impl;

import nix.edu.dao.LessonDao;
import nix.edu.dao.StudentDao;
import nix.edu.dao.TeacherDao;
import nix.edu.entity.Grade;
import nix.edu.entity.Group;
import nix.edu.entity.Lesson;
import nix.edu.entity.Teacher;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class TeacherDaoImpl implements TeacherDao {

    private final SessionFactory sessionFactory;
    private final LessonDao lessonDao;
    private final StudentDao studentDao;

    public TeacherDaoImpl(SessionFactory sessionFactory, LessonDao lessonDao, StudentDao studentDao) {
        this.sessionFactory = sessionFactory;
        this.lessonDao = lessonDao;
        this.studentDao = studentDao;
    }

    @Override
    public List<Teacher> findAll() {
        Session session = sessionFactory.openSession();
        return session.createQuery("from Teacher ").list();
    }

    @Override
    public Teacher findById(Long id) {
        Session session = sessionFactory.openSession();
        return session.get(Teacher.class, id);
    }

    @Override
    public void create(Teacher teacher) {
        Session session = sessionFactory.openSession();
        session.persist(teacher);
    }

    @Override
    public Optional<Group> getTheMostSuccessfulGroupByTeacherId(Long teacherId) {
        Session session = sessionFactory.openSession();
        Query query = session.createSQLQuery("select id from lessons " +
                "where topic_id = (select id from topics where name = 'Final') and " +
                "      id_teacher =:id");
        query.setParameter("id", teacherId);
        List<Lesson> finalLesson = query.list();
        Optional<Lesson> maxMedian = finalLesson
                .stream().max(
                        Comparator.comparingDouble(o -> estimateGrades(o.getGradeList()))
                );
        return maxMedian.map(Lesson::getGroup);
    }

    private double estimateGrades(List<Grade> grades) {
        List<Integer> sortedGrades = grades.stream()
                .map(Grade::getValue)
                .sorted()
                .collect(Collectors.toList());
        int size = sortedGrades.size();
        if (size % 2 != 0) {
            return (double) sortedGrades.get(size / 2);
        } else {
            return (double) (sortedGrades.get((size - 1) / 2) + sortedGrades.get(size / 2)) / 2.0;
        }
    }
}

