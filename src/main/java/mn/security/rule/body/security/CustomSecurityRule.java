package mn.security.rule.body.security;

import java.util.Map;
import java.util.Optional;
import javax.inject.Singleton;

import io.micronaut.http.HttpRequest;
import io.micronaut.security.rules.ConfigurationInterceptUrlMapRule;
import io.micronaut.security.rules.SecuredAnnotationRule;
import io.micronaut.security.rules.SecurityRule;
import io.micronaut.security.rules.SecurityRuleResult;
import io.micronaut.web.router.RouteMatch;


@Singleton
class CustomSecurityRule implements SecurityRule {

    /**
     * Executed before the built-ins
     * {@link io.micronaut.security.rules.SecuredAnnotationRule} and {@link io.micronaut.security.rules.ConfigurationInterceptUrlMapRule},
     */
    public static final int ORDER = Math.min(SecuredAnnotationRule.ORDER, ConfigurationInterceptUrlMapRule.ORDER) - 1;


    @Override
    public int getOrder() {
        return ORDER;
    }

    @Override
    public SecurityRuleResult check(HttpRequest<?> request, RouteMatch<?> routeMatch, Map<String, Object> claims) {
        Optional body = request.getBody();
        if (!body.isPresent()) {
            throw new IllegalStateException("Body not found");
        }

        return SecurityRuleResult.ALLOWED;
    }

}
