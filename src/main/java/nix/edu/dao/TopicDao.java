package nix.edu.dao;

import nix.edu.entity.Topic;

import java.util.List;

public interface TopicDao {
    List<Topic> findAll();

    Topic findById(Long id);

    void create(Topic topic);
}
