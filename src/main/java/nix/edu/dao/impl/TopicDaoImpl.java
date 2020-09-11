package nix.edu.dao.impl;

import nix.edu.dao.TopicDao;
import nix.edu.entity.Topic;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.util.List;

public class TopicDaoImpl implements TopicDao {

    private final Session session;

    public TopicDaoImpl(Session session) {
        this.session = session;
    }

    @Override
    public List<Topic> findAll() {
        return session.createQuery("from Topic ").list();
    }

    @Override
    public Topic findById(Long id) {
        return session.get(Topic.class, id);
    }

    @Override
    public void create(Topic topic) {
        session.persist(topic);
    }
}
