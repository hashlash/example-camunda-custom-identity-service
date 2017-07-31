package example.camunda.bpm.engine.idservice;

import org.camunda.bpm.engine.identity.Group;
import org.camunda.bpm.engine.impl.GroupQueryImpl;
import org.camunda.bpm.engine.impl.Page;
import org.camunda.bpm.engine.impl.interceptor.CommandContext;
import org.camunda.bpm.engine.impl.interceptor.CommandExecutor;

import java.util.List;

/**
 * Created by ashlah on 28/07/17.
 */
public class CustomGroupQuery extends GroupQueryImpl {

    public CustomGroupQuery() {
        super();
    }

    public CustomGroupQuery(CommandExecutor commandExecutor) {
        super(commandExecutor);
    }

    @Override
    public long executeCount(CommandContext commandContext) {
        final CustomIdentityProvider provider = getCustomIdentityProvider(commandContext);
        return provider.findGroupCountByQueryCriteria(this);
    }

    @Override
    public List<Group> executeList(CommandContext commandContext, Page page) {
        final CustomIdentityProvider provider = getCustomIdentityProvider(commandContext);
        return provider.findGroupByQueryCriteria(this);
    }

    protected CustomIdentityProvider getCustomIdentityProvider(CommandContext commandContext) {
        return (CustomIdentityProvider) commandContext.getReadOnlyIdentityProvider();
    }
}
