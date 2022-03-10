package jm_social_project.profile_service.config_annotation;

import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class AuthPrincipalFilter implements Filter {

    private String authHeader;

    public String getAuthHeader() {
        return authHeader;
    }

    @Override
    public void doFilter(ServletRequest servletRequest,
                         ServletResponse servletResponse,
                         FilterChain filterChain) throws IOException, ServletException {

        HttpServletRequest req = (HttpServletRequest) servletRequest;
        HttpServletResponse resp = (HttpServletResponse) servletResponse;
        String authHeader1 = req.getHeader("user_id");

        if (authHeader1 == null) {
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            resp.getOutputStream().close();
            return;
        }
        authHeader = authHeader1;
        filterChain.doFilter(servletRequest, servletResponse);
    }
}

