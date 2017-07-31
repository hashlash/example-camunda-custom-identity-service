package example.camunda.bpm.engine.idservice;

import org.camunda.bpm.engine.identity.User;
import org.camunda.bpm.engine.impl.Page;
import org.camunda.bpm.engine.impl.UserQueryImpl;
import org.camunda.bpm.engine.impl.interceptor.CommandContext;
import org.camunda.bpm.engine.impl.interceptor.CommandExecutor;

import java.util.List;

/**
 * Created by ashlah on 28/07/17.
 */
public class CustomUserQuery extends UserQueryImpl {

    public CustomUserQuery() {
        super();
    }

    public CustomUserQuery(CommandExecutor commandExecutor) {
        super(commandExecutor);
    }

    @Override
    public long executeCount(CommandContext commandContext) {
        final CustomIdentityProvider provider = getCustomIdentityProvider(commandContext);
        return provider.findUserCountByQueryCriteria(this);
    }

    @Override
    public List<User> executeList(CommandContext commandContext, Page page) {
        final CustomIdentityProvider provider = getCustomIdentityProvider(commandContext);
        return provider.findUserByQueryCriteria(this);
    }

    protected CustomIdentityProvider getCustomIdentityProvider(CommandContext commandContext) {
        return (CustomIdentityProvider) commandContext.getReadOnlyIdentityProvider();
    }
}
