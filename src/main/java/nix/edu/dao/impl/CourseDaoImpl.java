package nix.edu.dao.impl;

import nix.edu.dao.CourseDao;
import nix.edu.entity.Course;
import org.hibernate.Session;

import java.util.List;

public class CourseDaoImpl implements CourseDao {

    private final Session session;

    public CourseDaoImpl(Session session) {
        this.session = session;
    }

    @Override
    public void create(Course course) {
        session.persist(course);
    }

    @Override
    public List<Course> findAll() {
        return session.createQuery("from Course ").list();
    }

    @Override
    public Course findById(Long id) {
        return session.get(Course.class, id);
    }


}
