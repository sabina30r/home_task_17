package nix.edu.dao.impl;

import nix.edu.dao.GradeDao;
import nix.edu.entity.Grade;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

import java.util.List;

public class GradeDaoImpl implements GradeDao {
    private final SessionFactory sessionFactory;

    public GradeDaoImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public List<Grade> findAll() {
        Session session = sessionFactory.openSession();
        return session.createQuery("from Grade").list();
    }

    @Override
    public Grade findById(Long id) {
        Session session = sessionFactory.openSession();
        return session.get(Grade.class, id);
    }

    @Override
    public void create(Grade grade) {
        Session session = sessionFactory.openSession();
        session.persist(grade);
    }

    @Override
    public List<Grade> findGradesByLessonId(Long lessonId) {
        Session session = sessionFactory.openSession();
        Query query = session.createQuery("from Grade where lesson.id = :id ");
        query.setParameter("id", lessonId);
        return query.list();

    }
}
