package nix.edu;

import nix.edu.dao.GroupDao;
import nix.edu.dao.LessonDao;
import nix.edu.dao.StudentDao;
import nix.edu.dao.TeacherDao;
import nix.edu.dao.impl.GroupDaoImpl;
import nix.edu.dao.impl.LessonDaoImpl;
import nix.edu.dao.impl.StudentDaoImpl;
import nix.edu.dao.impl.TeacherDaoImpl;
import nix.edu.entity.Group;
import nix.edu.entity.Lesson;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.Optional;

public class Main {
    public static void main(String[] args) {
        Long teacherId = 1L;
        Long studentId = 1L;
        Configuration configuration = new Configuration().configure();
        SessionFactory sessionFactory = configuration.buildSessionFactory();
        Session session = sessionFactory.openSession();
        final GroupDao groupDao = new GroupDaoImpl(session);
        final LessonDao lessonDao = new LessonDaoImpl(session);
        final StudentDao studentDao = new StudentDaoImpl(session, lessonDao, groupDao);
        final TeacherDao teacherDao = new TeacherDaoImpl(session, lessonDao, studentDao);
        Lesson nextLesson = studentDao.showNextLessonInfoByStudentId(studentId);
        System.out.println("Next lesson: Teacher full name: " + nextLesson.getGroup().getTeacher().getFullName() +
                "Next lesson: Topic full name: " + nextLesson.getTopic().getName() +
                "Next lesson: Date: " + nextLesson.getDate());
        Optional<Group> theMostSuccessfulGroupByTeacherId = teacherDao.getTheMostSuccessfulGroupByTeacherId(teacherId);
        if (theMostSuccessfulGroupByTeacherId.isPresent()) {
            Group group = theMostSuccessfulGroupByTeacherId.get();
            System.out.println("The most successful group of the teacher" + group.getTeacher().getFullName() + " is " + group.getName() +
                    "\nCourse: " + group.getCourse().getName());
        }
    }
}

