package controllers;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import daos.ClientesDAO;
import daos.VendedorDAO;
import models.Cliente;
import models.Vendedor;

/**
 * @author Eduardo Barbosa E Vinicius Pontes
 */


@WebServlet(name = "VendedorControllerServlet",
	urlPatterns = {"/vendedores/*"},
	loadOnStartup = 1)
public class VendedorControllerServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private VendedorDAO _vendedorDAO;
    
    public void init() {
        String jdbcURL = getServletContext().getInitParameter("jdbcURL");
        String jdbcUsername = getServletContext().getInitParameter("jdbcUsername");
        String jdbcPassword = getServletContext().getInitParameter("jdbcPassword");
 
        this._vendedorDAO = new VendedorDAO(jdbcURL, jdbcUsername, jdbcPassword);
    }
 
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }
 
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    	String action = request.getPathInfo();

    	if(action == null) {
    		action = "";
    	}

    	try {
            switch (action) {
	            case "/new":
	                showNewForm(request, response);
	                break;
	            case "/insert":
	                insert(request, response);
	                break;
	            case "/delete":
	                delete(request, response);
	                break;
	            case "/edit":
	                showEditForm(request, response);
	                break;
	            case "/update":
	                update(request, response);
	                break;
	            default:
	                list(request, response);
	                break;
            }
        } catch (SQLException ex) {
            throw new ServletException(ex);
        }
    }
 
    private void list(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ServletException {
        List<Vendedor> list = _vendedorDAO.listAll();
        request.setAttribute("list", list);
        
    	RequestDispatcher dispatcher = request.getRequestDispatcher("/view/vendedor/list.jsp");
        dispatcher.forward(request, response);
    }
 
    private void showNewForm(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("/view/vendedor/form.jsp");
        dispatcher.forward(request, response);
    }
 
    private void showEditForm(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        Vendedor vendedor = _vendedorDAO.getById(id);
        
        RequestDispatcher dispatcher = request.getRequestDispatcher("/view/vendedor/form.jsp");
        request.setAttribute("vendedor", vendedor);
        dispatcher.forward(request, response);
    }
 
    private void insert(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException { 
     	 
    			String name = request.getParameter("name");
    			String city = request.getParameter("city");
    			double commission = Double.parseDouble(request.getParameter("commission"));
   
          
          Vendedor entity = new Vendedor(name, city, commission);
        _vendedorDAO.insert(entity);
        response.sendRedirect("list");
    }
 
    private void update(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {
         int id = Integer.parseInt(request.getParameter("id")); 
         String name = request.getParameter("name");
			String city = request.getParameter("city");
			double commission = Double.parseDouble(request.getParameter("commission"));
  
         
         Vendedor entity = new Vendedor(id, name, city, commission);
 
         _vendedorDAO.update(entity);
         response.sendRedirect("list");
    }
 
    private void delete(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
        
        int id = Integer.parseInt(request.getParameter("id"));
        Vendedor entity = new Vendedor(id);

        try {
            _vendedorDAO.delete(entity);
            response.sendRedirect(request.getContextPath() + "/vendedores?msg=deleteSuccess");

        } catch (SQLException ex) {
            if (ex.getErrorCode() == 1451) {
                response.sendRedirect(request.getContextPath() + "/vendedores?msg=deleteError");
            } else {
                throw new ServletException(ex);
            }
        }
    }
}
