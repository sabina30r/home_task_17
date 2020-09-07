package nix.edu.dao.impl;

import nix.edu.dao.TopicDao;
import nix.edu.entity.Topic;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.util.List;

public class TopicDaoImpl implements TopicDao {

    private final SessionFactory sessionFactory;

    public TopicDaoImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public List<Topic> findAll() {
        Session session = sessionFactory.openSession();
        return session.createQuery("from Topic ").list();
    }

    @Override
    public Topic findById(Long id) {
        Session session = sessionFactory.openSession();
        return session.get(Topic.class, id);
    }

    @Override
    public void create(Topic topic) {
        Session session = sessionFactory.openSession();
        session.persist(topic);
    }
}
