package nix.edu.dao;

import nix.edu.entity.Group;

import java.util.List;

public interface GroupDao {
    List<Group> findAll();

    Group findById(Long id);

    void create(Group group);

    Group findGroupIdByStudentId(Long studentId);

    List<Group> findGroupsByTeacherId(Long teacherId);
}
