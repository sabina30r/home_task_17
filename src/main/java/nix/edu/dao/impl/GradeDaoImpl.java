package nix.edu.dao.impl;

import nix.edu.dao.GradeDao;
import nix.edu.entity.Grade;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.util.List;

public class GradeDaoImpl implements GradeDao {
    private final Session session;

    public GradeDaoImpl(Session session) {
        this.session = session;
    }

    @Override
    public List<Grade> findAll() {
        return session.createQuery("from Grade").list();
    }

    @Override
    public Grade findById(Long id) {
        return session.get(Grade.class, id);
    }

    @Override
    public void create(Grade grade) {
        session.persist(grade);
    }

    @Override
    public List<Grade> findGradesByLessonId(Long lessonId) {
        Query query = session.createQuery("from Grade where lesson.id = :id ");
        query.setParameter("id", lessonId);
        return query.list();

    }
}
