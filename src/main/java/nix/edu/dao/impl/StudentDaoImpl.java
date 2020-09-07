package nix.edu.dao.impl;

import nix.edu.dao.GroupDao;
import nix.edu.dao.LessonDao;
import nix.edu.dao.StudentDao;
import nix.edu.entity.Lesson;
import nix.edu.entity.Student;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.time.LocalDate;
import java.util.List;

public class StudentDaoImpl implements StudentDao {

    private final SessionFactory sessionFactory;
    private final LessonDao lessonDao;
    private final GroupDao groupDao;

    public StudentDaoImpl(SessionFactory sessionFactory, LessonDao lessonDao, GroupDao groupDao) {
        this.sessionFactory = sessionFactory;
        this.lessonDao = lessonDao;
        this.groupDao = groupDao;
    }


    @Override
    public List<Student> findAll() {
        Session session = sessionFactory.openSession();
        return session.createQuery("from Student ").list();
    }

    @Override
    public Student findById(Long id) {
        Session session = sessionFactory.openSession();
        return session.get(Student.class, id);
    }

    @Override
    public void create(Student student) {
        Session session = sessionFactory.openSession();
        session.persist(student);
    }

    @Override
    public Lesson showNextLessonInfoByStudentId(Long studentId) {
        LocalDate currentDate = LocalDate.now();
        Long groupId = groupDao.findGroupIdByStudentId(studentId).getId();
        return lessonDao.findNextLesson(currentDate, groupId);
    }
}
