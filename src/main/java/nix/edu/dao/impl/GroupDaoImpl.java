package nix.edu.dao.impl;

import nix.edu.dao.GroupDao;
import nix.edu.entity.Group;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

import java.util.List;

public class GroupDaoImpl implements GroupDao {

    private final Session session;

    public GroupDaoImpl(Session session) {
        this.session = session;
    }

    @Override
    public List<Group> findAll() {
        return session.createQuery("from Group ").list();
    }

    @Override
    public Group findById(Long id) {
        return session.get(Group.class, id);
    }

    @Override
    public void create(Group group) {
        session.persist(group);
    }

    @Override
    public Group findGroupIdByStudentId(Long studentId) {
        session.beginTransaction();
        Query query = session.createQuery("select g from Group g inner join Student s " +
                "on s.group.id = g.id" + " where s.id = :id");
        query.setParameter("id", studentId);
        session.getTransaction().commit();
        return (Group) query.list().get(0);
    }

    @Override
    public List<Group> findGroupsByTeacherId(Long teacherId) {
        Query query = session.createQuery("select group from Group group inner join Teacher t " +
                "on group.teacher.id = t.id " + "where t.id = :id");
        query.setParameter("id", teacherId);
        return query.list();
    }
}
