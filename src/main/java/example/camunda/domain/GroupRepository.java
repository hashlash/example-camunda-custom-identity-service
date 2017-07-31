package example.camunda.domain;

import org.springframework.data.repository.CrudRepository;

/**
 * Created by ashlah on 28/07/17.
 */
public interface GroupRepository extends CrudRepository<Group, String> {
    Group findById(String id);
}
