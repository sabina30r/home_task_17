package nix.edu.dao.impl;

import nix.edu.dao.CourseDao;
import nix.edu.entity.Course;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.util.List;

public class CourseDaoImpl implements CourseDao {

    private final SessionFactory sessionFactory;

    public CourseDaoImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public void create(Course course) {
        Session session = sessionFactory.openSession();
        session.persist(course);
    }

    @Override
    public List<Course> findAll() {
        Session session = sessionFactory.openSession();
        return session.createQuery("from Course ").list();
    }

    @Override
    public Course findById(Long id) {
        Session session = sessionFactory.openSession();
        return session.get(Course.class, id);
    }


}
