package example.camunda.web;

import example.camunda.domain.Group;
import example.camunda.service.GroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by ashlah on 31/07/17.
 */
@RestController
@RequestMapping("/groups")
public class GroupController {

    private final GroupService groupService;

    @Autowired
    public GroupController(GroupService groupService) {
        this.groupService = groupService;
    }

    @RequestMapping(method = RequestMethod.POST)
    Group postGroup(@RequestBody Group group) {
        return groupService.save(group);
    }
}
