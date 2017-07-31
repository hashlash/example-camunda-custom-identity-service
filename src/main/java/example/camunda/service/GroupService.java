package example.camunda.service;

import example.camunda.domain.Group;
import example.camunda.domain.GroupRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;

/**
 * Created by ashlah on 28/07/17.
 */
@Service
@Transactional
public class GroupService {

    @Autowired
    private GroupRepository repository;

    public Group findById(String id) {
        return repository.findById(id);
    }

    public Collection<Group> findAll() {
        return (Collection<Group>) repository.findAll();
    }

    public Group save(Group group) {
        return repository.save(group);
    }
}
