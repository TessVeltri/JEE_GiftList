/*
 * Generated by the Jasper component of Apache Tomcat
 * Version: Apache Tomcat/9.0.54
 * Generated at: 2023-01-02 14:37:37 UTC
 * Note: The last modified time of this file was set to
 *       the last modified time of the source file after
 *       generation to assist with modification tracking.
 */
package org.apache.jsp.WEB_002dINF.JSP;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import be.veltri.JAVABEANS.*;
import java.util.*;

public final class InfoList_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent,
                 org.apache.jasper.runtime.JspSourceImports {

  private static final javax.servlet.jsp.JspFactory _jspxFactory =
          javax.servlet.jsp.JspFactory.getDefaultFactory();

  private static java.util.Map<java.lang.String,java.lang.Long> _jspx_dependants;

  private static final java.util.Set<java.lang.String> _jspx_imports_packages;

  private static final java.util.Set<java.lang.String> _jspx_imports_classes;

  static {
    _jspx_imports_packages = new java.util.HashSet<>();
    _jspx_imports_packages.add("javax.servlet");
    _jspx_imports_packages.add("java.util");
    _jspx_imports_packages.add("be.veltri.JAVABEANS");
    _jspx_imports_packages.add("javax.servlet.http");
    _jspx_imports_packages.add("javax.servlet.jsp");
    _jspx_imports_classes = null;
  }

  private volatile javax.el.ExpressionFactory _el_expressionfactory;
  private volatile org.apache.tomcat.InstanceManager _jsp_instancemanager;

  public java.util.Map<java.lang.String,java.lang.Long> getDependants() {
    return _jspx_dependants;
  }

  public java.util.Set<java.lang.String> getPackageImports() {
    return _jspx_imports_packages;
  }

  public java.util.Set<java.lang.String> getClassImports() {
    return _jspx_imports_classes;
  }

  public javax.el.ExpressionFactory _jsp_getExpressionFactory() {
    if (_el_expressionfactory == null) {
      synchronized (this) {
        if (_el_expressionfactory == null) {
          _el_expressionfactory = _jspxFactory.getJspApplicationContext(getServletConfig().getServletContext()).getExpressionFactory();
        }
      }
    }
    return _el_expressionfactory;
  }

  public org.apache.tomcat.InstanceManager _jsp_getInstanceManager() {
    if (_jsp_instancemanager == null) {
      synchronized (this) {
        if (_jsp_instancemanager == null) {
          _jsp_instancemanager = org.apache.jasper.runtime.InstanceManagerFactory.getInstanceManager(getServletConfig());
        }
      }
    }
    return _jsp_instancemanager;
  }

  public void _jspInit() {
  }

  public void _jspDestroy() {
  }

  public void _jspService(final javax.servlet.http.HttpServletRequest request, final javax.servlet.http.HttpServletResponse response)
      throws java.io.IOException, javax.servlet.ServletException {

    if (!javax.servlet.DispatcherType.ERROR.equals(request.getDispatcherType())) {
      final java.lang.String _jspx_method = request.getMethod();
      if ("OPTIONS".equals(_jspx_method)) {
        response.setHeader("Allow","GET, HEAD, POST, OPTIONS");
        return;
      }
      if (!"GET".equals(_jspx_method) && !"POST".equals(_jspx_method) && !"HEAD".equals(_jspx_method)) {
        response.setHeader("Allow","GET, HEAD, POST, OPTIONS");
        response.sendError(HttpServletResponse.SC_METHOD_NOT_ALLOWED, "Les JSPs ne permettent que GET, POST ou HEAD. Jasper permet aussi OPTIONS");
        return;
      }
    }

    final javax.servlet.jsp.PageContext pageContext;
    javax.servlet.http.HttpSession session = null;
    final javax.servlet.ServletContext application;
    final javax.servlet.ServletConfig config;
    javax.servlet.jsp.JspWriter out = null;
    final java.lang.Object page = this;
    javax.servlet.jsp.JspWriter _jspx_out = null;
    javax.servlet.jsp.PageContext _jspx_page_context = null;


    try {
      response.setContentType("text/html; charset=UTF-8");
      pageContext = _jspxFactory.getPageContext(this, request, response,
      			null, true, 8192, true);
      _jspx_page_context = pageContext;
      application = pageContext.getServletContext();
      config = pageContext.getServletConfig();
      session = pageContext.getSession();
      out = pageContext.getOut();
      _jspx_out = out;

      out.write("\r\n");
      out.write("\r\n");
      out.write("<!DOCTYPE html>\r\n");
      out.write("<html>\r\n");
      out.write("<head>\r\n");
      out.write("<meta charset=\"UTF-8\">\r\n");
      out.write("<title>Info about a list</title>\r\n");
      out.write("<link href=\"/Veltri_GiftList/CSS/infoListCSS.css\" rel=\"stylesheet\"\r\n");
      out.write("	type=\"text/css\">\r\n");
      org.apache.jasper.runtime.JspRuntimeLibrary.include(request, response, "Header.jsp", out, false);
      out.write("\r\n");
      out.write("</head>\r\n");

ArrayList<String> errors = (ArrayList<String>)request.getAttribute("errorsDeleteGift");
GiftList gl = (GiftList) request.getAttribute("giftList");

      out.write("\r\n");
      out.write("<body>\r\n");
      out.write("	<h2>\r\n");
      out.write("		List :\r\n");
      out.write("		");
      out.print(gl.getNameList());
      out.write("</h2>\r\n");
      out.write("	");

	if (gl.isActive()) {
		// true -> afficher le status
		String status = gl.getStatusList().toString();
		String check = "";
		if (status.equals("Active"))
			check = "checked";
	
      out.write("\r\n");
      out.write("	<div>\r\n");
      out.write("		Inactive <label class=\"switch\"> <input onclick=\"\"\r\n");
      out.write("			type=\"checkbox\" ");
      out.print(check);
      out.write("> <span class=\"slider round\"></span>\r\n");
      out.write("		</label> Active\r\n");
      out.write("	</div>\r\n");
      out.write("	");

	} else {
	// false -> afficher la date
	
      out.write("\r\n");
      out.write("	<h4> <u>Occasion :</u> ");
      out.print(gl.getOccasion() );
      out.write(" </h4>\r\n");
      out.write("	<h4>\r\n");
      out.write("\r\n");
      out.write("		<div id=\"getDate\" style=\"display: block;\">\r\n");
      out.write("			<div class=\"child\">Date :</div>\r\n");
      out.write("			<div id=\"limitDate\" class=\"child\">");
      out.print(gl.getLimitDate());
      out.write("</div>\r\n");
      out.write("\r\n");
      out.write("			<button class=\"btn child\" onclick=\"btnClick()\">\r\n");
      out.write("				<img width=\"25px\" height=\"25px\" src=\"/Veltri_GiftList/IMG/pen.png\"></img>\r\n");
      out.write("			</button>\r\n");
      out.write("		</div>\r\n");
      out.write("		<div id=\"modifyDate\" style=\"display: none;\">\r\n");
      out.write("			<div class=\"child\">Date :</div>\r\n");
      out.write("			<input type=\"date\" name=\"limitDate\" id=\"limitDate\"\r\n");
      out.write("				value=");
      out.print(gl.getLimitDate());
      out.write(" class=\"child\"></input>\r\n");
      out.write("			<button class=\"btn child\" onclick=\"btnClick()\">\r\n");
      out.write("				<img width=\"25px\" height=\"25px\" src=\"/Veltri_GiftList/IMG/valid.png\"></img>\r\n");
      out.write("			</button>\r\n");
      out.write("		</div>\r\n");
      out.write("	</h4>\r\n");
      out.write("	");

	}
	
      out.write("\r\n");
      out.write("\r\n");
      out.write("	<div>\r\n");
      out.write("		<div align=\"right\">\r\n");
      out.write("			<ul>\r\n");
      out.write("			");

				if (errors != null){
					for (String err : errors){
						
      out.write("\r\n");
      out.write("							<li style=\"color:red;\">");
      out.print(err);
      out.write(" </li>\r\n");
      out.write("						");

					}
				}
				
			
      out.write("</ul>\r\n");
      out.write("		</div>\r\n");
      out.write("		<h3 align=\"center\">\r\n");
      out.write("			<em><u>All gifts</u></em>\r\n");
      out.write("		</h3>\r\n");
      out.write("		<table>\r\n");
      out.write("			<tr>\r\n");
      out.write("				<th>Image</th>\r\n");
      out.write("				<th>Gift Name</th>\r\n");
      out.write("				<th>Gift Description</th>\r\n");
      out.write("				<th>Price</th>\r\n");
      out.write("				<th>Priority</th>\r\n");
      out.write("				<th>Website</th>\r\n");
      out.write("				<th>Status</th>\r\n");
      out.write("				<th>Reserve</th>\r\n");
      out.write("			</tr>\r\n");
      out.write("			");

			for (Gift g : gl.getLstGift()) {
				String b64 = "";

				String image = "";

				if (g.getImage() != null && g.getImage().length != 0) {
					b64 = Base64.getEncoder().encodeToString(g.getImage());
					image = "data:image/" + g.getExtensionImage() + ";base64,";
					image += b64;
				}
			
      out.write("\r\n");
      out.write("			<tr>\r\n");
      out.write("				<td>\r\n");
      out.write("					");

					if (g.getImage() != null && g.getImage().length != 0) {
					
      out.write(" <img src=\"");
      out.print(image);
      out.write("\" width=\"80px\" height=\"50px\"></img> ");

 }
 
      out.write("\r\n");
      out.write("				</td>\r\n");
      out.write("				<td>");
      out.print(g.getName());
      out.write("</td>\r\n");
      out.write("				<td>");
      out.print(g.getDescription());
      out.write("</td>\r\n");
      out.write("				<td>");
      out.print(g.getAveragePrice());
      out.write("</td>\r\n");
      out.write("				<td>");
      out.print(g.getPriority().toString());
      out.write("</td>\r\n");
      out.write("				<td>");
      out.print(g.getWebsiteLink());
      out.write("</td>\r\n");
      out.write("				<td>");
      out.print(g.getStatusGift().toString());
      out.write("</td>\r\n");
      out.write("				<td>\r\n");
      out.write("					");

					
					for (Reserve r : g.getLstReserve()) {
					
      out.write(' ');
      out.print(r.getAmount());
      out.write(' ');
      out.write('€');
      out.write('/');
      out.print(r.getUser().getFirstname());
      out.write(' ');
      out.print(r.getUser().getName());
      out.write("\r\n");
      out.write("					");

					}
					
      out.write("\r\n");
      out.write("				</td>\r\n");
      out.write("				<td><button type=\"button\" class=\"btn child\" onclick=\"location.href='/Veltri_GiftList/modifyGift?idGift=");
      out.print( g.getIdGift() );
      out.write("&where=server'\">\r\n");
      out.write("						<img width=\"25px\" height=\"25px\" src=\"/Veltri_GiftList/IMG/pen.png\"></img>\r\n");
      out.write("					</button></td>\r\n");
      out.write("				<td><button type=\"button\" class=\"btn child\" onclick=\"location.href='/Veltri_GiftList/infoList?idGift=");
      out.print( g.getIdGift() );
      out.write("&idGiftList=");
      out.print( gl.getIdGiftList());
      out.write("'\">\r\n");
      out.write("						<img width=\"25px\" height=\"25px\"\r\n");
      out.write("							src=\"/Veltri_GiftList/IMG/delete.png\"></img>\r\n");
      out.write("					</button></td>\r\n");
      out.write("			</tr>\r\n");
      out.write("			");

			}
			
      out.write("\r\n");
      out.write("		</table>\r\n");
      out.write("		<button type=\"button\" id=\"addGiftBtn\" onclick=\"location.href='/Veltri_GiftList/addGift?idGiftList=");
      out.print(gl.getIdGiftList());
      out.write("'\">\r\n");
      out.write("				<img width=\"30px\" height=\"30px\" src=\"/Veltri_GiftList/IMG/plus.png\"></img>\r\n");
      out.write("		</button>\r\n");
      out.write("	</div>\r\n");
      out.write("	<h3 align=\"center\">\r\n");
      out.write("		<em><u>All participants</u></em>\r\n");
      out.write("	</h3>\r\n");
      out.write("	<div align=\"center\">\r\n");
      out.write("\r\n");
      out.write("		<h4>Names</h4>\r\n");
      out.write("		");

		for (User u : gl.getLstParticipant()) {
		
      out.write("\r\n");
      out.write("		<div style=\"display: block;\">\r\n");
      out.write("			");
      out.print(u.getName());
      out.write("\r\n");
      out.write("			");
      out.print(u.getFirstname());
      out.write("\r\n");
      out.write("			<button class=\"btn child\" onclick=\"location.href='/Veltri_GiftList/infoList?idUser=");
      out.print( u.getIdUser() );
      out.write("&idGiftList=");
      out.print( gl.getIdGiftList());
      out.write("'\">\r\n");
      out.write("				<img width=\"25px\" height=\"25px\"\r\n");
      out.write("					src=\"/Veltri_GiftList/IMG/delete.png\"></img>\r\n");
      out.write("			</button>\r\n");
      out.write("		</div>\r\n");
      out.write("\r\n");
      out.write("		");

		}
		
      out.write("\r\n");
      out.write("		<button type=\"button\" id=\"addParticipantBtn\" onclick=\"location.href='/Veltri_GiftList/addParticipant?idGiftList=");
      out.print(gl.getIdGiftList());
      out.write("'\">\r\n");
      out.write("				<img width=\"30px\" height=\"30px\" src=\"/Veltri_GiftList/IMG/plus.png\"></img>\r\n");
      out.write("		</button>\r\n");
      out.write("	</div>\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("</body>\r\n");
      org.apache.jasper.runtime.JspRuntimeLibrary.include(request, response, "Footer.jsp", out, false);
      out.write("\r\n");
      out.write("<script>\r\n");
      out.write("	const getDate = document.getElementById(\"getDate\");\r\n");
      out.write("	const modifyDate = document.getElementById(\"modifyDate\");\r\n");
      out.write("\r\n");
      out.write("	function btnClick() {\r\n");
      out.write("		if (getDate.style.display == 'block') {\r\n");
      out.write("			getDate.style.display = 'none';\r\n");
      out.write("			modifyDate.style.display = 'block';\r\n");
      out.write("		} else {\r\n");
      out.write("			getDate.style.display = 'block';\r\n");
      out.write("			modifyDate.style.display = 'none';\r\n");
      out.write("		}\r\n");
      out.write("	}\r\n");
      out.write("</script>\r\n");
      out.write("</html>");
    } catch (java.lang.Throwable t) {
      if (!(t instanceof javax.servlet.jsp.SkipPageException)){
        out = _jspx_out;
        if (out != null && out.getBufferSize() != 0)
          try {
            if (response.isCommitted()) {
              out.flush();
            } else {
              out.clearBuffer();
            }
          } catch (java.io.IOException e) {}
        if (_jspx_page_context != null) _jspx_page_context.handlePageException(t);
        else throw new ServletException(t);
      }
    } finally {
      _jspxFactory.releasePageContext(_jspx_page_context);
    }
  }
}
