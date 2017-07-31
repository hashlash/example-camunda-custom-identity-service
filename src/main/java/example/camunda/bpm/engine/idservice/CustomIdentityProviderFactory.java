package example.camunda.bpm.engine.idservice;

import example.camunda.service.GroupService;
import example.camunda.service.UserService;
import org.camunda.bpm.engine.impl.identity.ReadOnlyIdentityProvider;
import org.camunda.bpm.engine.impl.interceptor.Session;
import org.camunda.bpm.engine.impl.interceptor.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by ashlah on 28/07/17.
 */
@Service
public class CustomIdentityProviderFactory implements SessionFactory {

    private final UserService userService;

    private final GroupService groupService;

    @Autowired
    public CustomIdentityProviderFactory(UserService userService, GroupService groupService) {
        this.userService = userService;
        this.groupService = groupService;
    }

//    @Autowired
//    private CustomIdentityProvider customIdentityProvider;

    @Override
    public Class<?> getSessionType() {
        return ReadOnlyIdentityProvider.class;
    }

    @Override
    public Session openSession() {
        return new CustomIdentityProvider(userService, groupService);
    }
}
