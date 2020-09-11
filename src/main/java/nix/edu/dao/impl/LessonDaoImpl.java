package nix.edu.dao.impl;

import nix.edu.dao.LessonDao;
import nix.edu.entity.Lesson;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;


import java.time.LocalDate;
import java.util.List;

public class LessonDaoImpl implements LessonDao {
    private final Session session;

    public LessonDaoImpl(Session session) {
        this.session = session;
    }

    @Override
    public List<Lesson> findAll() {
        return session.createQuery("from Lesson ").list();
    }

    @Override
    public Lesson findById(Long id) {
        return session.get(Lesson.class, id);
    }

    @Override
    public Lesson findNextLesson(LocalDate date, Long groupId) {
        Query query = session.createQuery("from Lesson L " +
                "where date = (select min(date) from Lesson ls" +
                " where ls.group.id = :qGroupId and date > :date) AND L.group.id = :id");
        query.setParameter("date", date);
        query.setParameter("id", groupId);

        return (Lesson) query.list().get(0);
    }

}
