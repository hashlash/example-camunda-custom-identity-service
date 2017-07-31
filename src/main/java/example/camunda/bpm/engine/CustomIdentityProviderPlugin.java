package example.camunda.bpm.engine;

import example.camunda.bpm.engine.idservice.CustomIdentityProviderFactory;
import example.camunda.service.GroupService;
import example.camunda.service.UserService;
import org.camunda.bpm.engine.ProcessEngine;
import org.camunda.bpm.engine.impl.cfg.ProcessEngineConfigurationImpl;
import org.camunda.bpm.engine.impl.cfg.ProcessEnginePlugin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by ashlah on 28/07/17.
 */
@Component
public class CustomIdentityProviderPlugin implements ProcessEnginePlugin {

    private final UserService userService;

    private final GroupService groupService;

    @Autowired
    public CustomIdentityProviderPlugin(UserService userService, GroupService groupService) {
        this.userService = userService;
        this.groupService = groupService;
    }

    @Override
    public void preInit(ProcessEngineConfigurationImpl processEngineConfiguration) {

        CustomIdentityProviderFactory identityProviderFactory = new CustomIdentityProviderFactory(userService, groupService);
        processEngineConfiguration.setIdentityProviderSessionFactory(identityProviderFactory);
    }

    @Override
    public void postInit(ProcessEngineConfigurationImpl processEngineConfiguration) {

    }

    @Override
    public void postProcessEngineBuild(ProcessEngine processEngine) {

    }
}
