package example.camunda.bpm.engine.idservice;

import example.camunda.service.GroupService;
import example.camunda.service.UserService;
import org.camunda.bpm.engine.BadUserRequestException;
import org.camunda.bpm.engine.identity.*;
import org.camunda.bpm.engine.impl.context.Context;
import org.camunda.bpm.engine.impl.identity.ReadOnlyIdentityProvider;
import org.camunda.bpm.engine.impl.interceptor.CommandContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by ashlah on 28/07/17.
 */
@Service
public class CustomIdentityProvider implements ReadOnlyIdentityProvider {

    private UserService userService;

    private GroupService groupService;

    @Autowired
    public CustomIdentityProvider(UserService userService, GroupService groupService) {
        this.userService = userService;
        this.groupService = groupService;
    }

    // User ////////////////////////////////////////////

    @Override
    public User findUserById(String userId) {
        return userService.findById(userId);
    }

    @Override
    public UserQuery createUserQuery() {
        return new CustomUserQuery(Context.getProcessEngineConfiguration().getCommandExecutorTxRequired());
    }

    @Override
    public UserQuery createUserQuery(CommandContext commandContext) {
        return new CustomUserQuery();
    }

    @Override
    public NativeUserQuery createNativeUserQuery() {
        throw new BadUserRequestException("not supported");
    }

    public long findUserCountByQueryCriteria(CustomUserQuery query) {
        return findUserByQueryCriteria(query).size();
    }

    public List<User> findUserByQueryCriteria(CustomUserQuery query) {

        return userService.findAll().stream()
                .filter(user -> user.getId().equals(query.getId()))
                .filter(user -> user.getFirstName().equals(query.getFirstName()))
                .filter(user -> user.getLastName().equals(query.getLastName()))
                .filter(user -> user.getEmail().equals(query.getEmail()))
                .filter(user -> user.getGroup().getId().equals(query.getGroupId()))
                .collect(Collectors.toList());

//        return Collections.emptyList();
    }

    @Override
    public boolean checkPassword(String userId, String password) {

        if(userId == null || password == null || userId.isEmpty() || password.isEmpty())
            return false;

        User user = findUserById(userId);

        if(user == null)
            return false;

        return user.getPassword().equals(password);
    }

    // Group //////////////////////////////////////////

    @Override
    public Group findGroupById(String groupId) {
        return groupService.findById(groupId);
    }

    @Override
    public GroupQuery createGroupQuery() {
        return new CustomGroupQuery(Context.getProcessEngineConfiguration().getCommandExecutorTxRequired());
    }

    @Override
    public GroupQuery createGroupQuery(CommandContext commandContext) {
        return new CustomGroupQuery();
    }

    public long findGroupCountByQueryCriteria(CustomGroupQuery query) {
        return findGroupByQueryCriteria(query).size();
    }

    public List<Group> findGroupByQueryCriteria(CustomGroupQuery query) {

        return groupService.findAll().stream()
                .filter(group -> group.getId().equals(query.getId()))
                .filter(group -> group.getName().equals(query.getName()))
                .filter(group -> group.getType().equals(query.getType()))
                .collect(Collectors.toList());

//        return Collections.emptyList();
    }

    // Tenant ////////////////////////////////////////

    @Override
    public Tenant findTenantById(String tenantId) {
        return null;
    }

    @Override
    public TenantQuery createTenantQuery() {
        return new CustomTenantQuery(Context.getProcessEngineConfiguration().getCommandExecutorTxRequired());
    }

    @Override
    public TenantQuery createTenantQuery(CommandContext commandContext) {
        return new CustomTenantQuery();
    }

    @Override
    public void flush() {

    }

    @Override
    public void close() {

    }
}
