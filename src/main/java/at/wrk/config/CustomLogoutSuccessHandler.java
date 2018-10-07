package at.wrk.config;

import java.io.IOException;
import java.util.Enumeration;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.stereotype.Component;

@Component
public class CustomLogoutSuccessHandler implements LogoutSuccessHandler{

	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Override
	public void onLogoutSuccess(HttpServletRequest request,
			HttpServletResponse response, Authentication authentication)
			throws IOException, ServletException {
		logger.info("Logout successfully with principal: " + authentication.getName());
		
        HttpSession hs = request.getSession();
        String username = request.getUserPrincipal().getName();
        String id = hs.getId();
        Enumeration e = hs.getAttributeNames();
        while (e.hasMoreElements()) {
            String attr = (String) e.nextElement();
            hs.setAttribute(attr, null);
        }
        hs.invalidate();
	}
}