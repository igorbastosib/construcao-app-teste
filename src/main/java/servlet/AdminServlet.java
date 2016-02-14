package servlet;

import static servlet.ServletUtil.forward;
import static servlet.ServletUtil.validaLogin;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.JpaUtil;
import dao.PageViewersDAO;
import dao.UsuarioDAO;
import admin.PageViewers;
import admin.RelatorioAcesso;
import admin.Usuario;

/**
 * Servlet que gerencia movimentacao dentro das funcoes de AMD
 *
 */
@WebServlet(value = "/AdminServlet.saas")
public class AdminServlet extends HttpServlet {

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {
			String comando = ServletUtil.stringNuloParaVazio(req.getParameter("comando"));

			// Valida login
			if (!validaLogin(req, resp)) {
				resp.sendRedirect("/saasAnalytics/login-admin.saas");
			} else {
				if (!comando.isEmpty()) {
					if (comando.equals("painel")) {
						req.setAttribute("comando", "painel");
						forward(req, resp, "/UserServlet.saas");
					} else if (comando.equals("usuarios")) {
						req.setAttribute("comando", "usuarios");
						forward(req, resp, "/UserServlet.saas");
					} else if (comando.equals("relatorio")) {
						listarRelatorio(req);
						forward(req, resp, "./saas-admin/relatorio.jsp");
					} else if (comando.equals("logout")) {
						req.setAttribute("comando", "");
						forward(req, resp, "/login-admin.saas");
					}
				}
			}
			if (comando.equals("acessoURL")) {
				acessouURL(req);
			}
		} catch (Throwable e) {
			// Retorna o 404
			resp.sendError(HttpServletResponse.SC_NOT_FOUND);
			e.printStackTrace();
		}
	}

	/**
	 * Pega o relatorio de acesso e armazena em uma lista
	 * 
	 * @param req
	 */
	private void listarRelatorio(HttpServletRequest req) {
		List<RelatorioAcesso> relatorioAcesso = PageViewersDAO.getInstance().geraRelatorioAcessos();
		req.setAttribute("relatorioAcesso", relatorioAcesso);
	}

	private void acessouURL(HttpServletRequest req) {
		String url = ServletUtil.stringNuloParaVazio(req.getParameter("url"));
		String ip = ServletUtil.stringNuloParaVazio(req.getParameter("ip"));;
		String date = ServletUtil.stringNuloParaVazio(req.getParameter("dateTime"));
		try {
			Date dateTime = ServletUtil.stringToDateTime(date);//ServletUtil.stringToDateTime(new SimpleDateFormat("yyyy-mm-dd hh:mm:ss").format(new Date()));
			PageViewers pageView = new PageViewers(url, ip, dateTime);
			PageViewersDAO.getInstance().create(pageView);
		}catch (Exception e){
			e.printStackTrace();
		}
	}
}
