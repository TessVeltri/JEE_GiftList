package be.veltri.FILTERS;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import be.veltri.JAVABEANS.User;

/**
 * Servlet Filter implementation class AuthenticationFilters
 */
public class AuthenticationFilter implements Filter {

	private FilterConfig filterConfig;
	/**
     * @see HttpFilter#HttpFilter()
     */
    public AuthenticationFilter() {
        super();
    }
    

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		Filter.super.init(fConfig);
		this.filterConfig=fConfig;
	}

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		Filter.super.destroy();
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest req= (HttpServletRequest)request;
		HttpSession session = req.getSession(false);
	    HttpServletResponse res = (HttpServletResponse)response;
		if(session!=null) {
			User user =(User)session.getAttribute("user");
			if(user !=null && !user.getEmail().equals("")) {
				try {
					chain.doFilter(request, response);
				}
				catch(Exception ex) {
					System.out.println(ex.getMessage());
				}
			}
			else {
				res.sendRedirect("connection");	
			}
		}
		else {
			res.sendRedirect("connection");	
		}
	}


}
