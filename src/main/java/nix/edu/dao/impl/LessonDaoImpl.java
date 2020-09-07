package nix.edu.dao.impl;

import nix.edu.dao.LessonDao;
import nix.edu.entity.Lesson;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;


import java.time.LocalDate;
import java.util.List;

public class LessonDaoImpl implements LessonDao {
    private final SessionFactory sessionFactory;

    public LessonDaoImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public List<Lesson> findAll() {
        Session session = sessionFactory.openSession();
        return session.createQuery("from Lesson ").list();
    }

    @Override
    public Lesson findById(Long id) {
        Session session = sessionFactory.openSession();
        return session.get(Lesson.class, id);
    }

    @Override
    public Lesson findNextLesson(LocalDate date, Long groupId) {
        Query query = sessionFactory.openSession().createQuery("from Lesson L " +
                "where date = (select min(date) from Lesson ls" +
                " where ls.group.id = :qGroupId and date > :date) AND L.group.id = :id");
        query.setParameter("date", date);
        query.setParameter("id", groupId);

        return (Lesson) query.list().get(0);
    }

}
