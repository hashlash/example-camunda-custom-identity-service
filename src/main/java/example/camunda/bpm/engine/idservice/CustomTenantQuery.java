package example.camunda.bpm.engine.idservice;

import org.camunda.bpm.engine.identity.Tenant;
import org.camunda.bpm.engine.impl.Page;
import org.camunda.bpm.engine.impl.TenantQueryImpl;
import org.camunda.bpm.engine.impl.interceptor.CommandContext;
import org.camunda.bpm.engine.impl.interceptor.CommandExecutor;

import java.util.Collections;
import java.util.List;

/**
 * Created by ashlah on 28/07/17.
 */
public class CustomTenantQuery extends TenantQueryImpl {

    public CustomTenantQuery() {
        super();
    }

    public CustomTenantQuery(CommandExecutor commandExecutor) {
        super(commandExecutor);
    }

    @Override
    public long executeCount(CommandContext commandContext) {
        return 0;
    }

    @Override
    public List<Tenant> executeList(CommandContext commandContext, Page page) {
        return Collections.emptyList();
    }
}
