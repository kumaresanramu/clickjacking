
import java.io.IOException;
 
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletResponse;
 
/* types of X-Frame-Options
   X-Frame-Options: deny
   X-Frame-Options: sameorigin
   X-Frame-Options: allow-from https://example.com/ 
   furter ref:https://developer.mozilla.org/en-US/docs/Web/HTTP/Headers/X-Frame-Options*/
 
public class ClickjackingPreventionFilter implements Filter 
{
  private String mode = "DENY";
   
// Add X-FRAME-OPTIONS response header to tell any other browsers who   not to display this //content in a frame.
     public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
         HttpServletResponse res = (HttpServletResponse)response;
         res.addHeader("X-FRAME-OPTIONS", mode );   
         chain.doFilter(request, response);
     }
     public void destroy() {
     }
      
     public void init(FilterConfig filterConfig) {
         String configMode = filterConfig.getInitParameter("mode");
         if ( configMode != null ) {
             mode = configMode;
         }
     }
}
