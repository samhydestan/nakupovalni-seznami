package si.fri.prpo.nakupovanje.api;

import si.fri.prpo.nakupovanje.storitve.UporabnikBean;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.Logger;

@WebServlet("/servlet")
public class JPAServlet extends HttpServlet{

  @Inject
  private UporabnikBean uporabnikBean;

  private Logger log=Logger.getLogger(JPAServlet.class.getName());

  @Override
  protected void doGet(HttpServletRequest req,HttpServletResponse resp) throws ServletException, IOException{

    resp.setContentType("text/html; charset=UTF-8");
    resp.setCharacterEncoding("UTF-8");
    PrintWriter pw=resp.getWriter();

    pw.append("<br/><br/>Uporabniki:<br/>");
    uporabnikBean.getUporabniki().stream().forEach(u->pw.append(u.toString()+"<br/><br/>"));

  }
}