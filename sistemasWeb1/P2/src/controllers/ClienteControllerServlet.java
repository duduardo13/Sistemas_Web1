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
import daos.VendedorDAO; // ADICIONADO: Importar o VendedorDAO
import models.Cliente;
import models.Vendedor; // ADICIONADO: Importar o modelo Vendedor

/*
 * @author Eduardo Barbosa e Vinicius Pontes
*/

@WebServlet(name = "ClienteControllerServlet", urlPatterns = { "/clientes/*" }, loadOnStartup = 1)
public class ClienteControllerServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private ClientesDAO _clienteDAO;
    private VendedorDAO _vendedorDAO; // ADICIONADO: Um DAO para Vendedores

    public void init() {
        String jdbcURL = getServletContext().getInitParameter("jdbcURL");
        String jdbcUsername = getServletContext().getInitParameter("jdbcUsername");
        String jdbcPassword = getServletContext().getInitParameter("jdbcPassword");

        this._clienteDAO = new ClientesDAO(jdbcURL, jdbcUsername, jdbcPassword);
        this._vendedorDAO = new VendedorDAO(jdbcURL, jdbcUsername, jdbcPassword); // ADICIONADO: Inicializar o VendedorDAO
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getPathInfo();

        if (action == null) {
            action = "/list"; 
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
        List<Cliente> list = _clienteDAO.listAll();
        request.setAttribute("list", list);

        RequestDispatcher dispatcher = request.getRequestDispatcher("/view/clientes/list.jsp");
        dispatcher.forward(request, response);
    }

    private void showNewForm(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {
        List<Vendedor> listaVendedores = _vendedorDAO.listAll();
        request.setAttribute("listaVendedores", listaVendedores);
        
        RequestDispatcher dispatcher = request.getRequestDispatcher("/view/clientes/form.jsp");
        dispatcher.forward(request, response);
    }

    private void showEditForm(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        Cliente cliente = _clienteDAO.getById(id);
        
        List<Vendedor> listaVendedores = _vendedorDAO.listAll();
        
        request.setAttribute("cliente", cliente);
        request.setAttribute("listaVendedores", listaVendedores);
        
        RequestDispatcher dispatcher = request.getRequestDispatcher("/view/clientes/form.jsp");
        dispatcher.forward(request, response);
    }

    private void insert(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {
        String custName = request.getParameter("cust_name"); 
        String city = request.getParameter("city");
        int grade = Integer.parseInt(request.getParameter("grade"));
        int salesmanId = Integer.parseInt(request.getParameter("salesman_id"));

        Cliente entity = new Cliente(custName, city, grade, salesmanId);
        _clienteDAO.insert(entity);
        response.sendRedirect("list");
    }

    private void update(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        String custName = request.getParameter("cust_name");
        String city = request.getParameter("city");
        int grade = Integer.parseInt(request.getParameter("grade"));
        int salesmanId = Integer.parseInt(request.getParameter("salesman_id")); 
        Cliente entity = new Cliente(id, custName, city, grade, salesmanId);

        _clienteDAO.update(entity);
        response.sendRedirect("list");
    }

    
    private void delete(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
        
        int id = Integer.parseInt(request.getParameter("id"));
        Cliente entity = new Cliente(id);

        try {
            _clienteDAO.delete(entity);
            response.sendRedirect(request.getContextPath() + "/clientes?msg=deleteSuccess");

        } catch (SQLException ex) {
            if (ex.getErrorCode() == 1451) {
                response.sendRedirect(request.getContextPath() + "/clientes?msg=deleteError");
            } else {
                throw new ServletException(ex);
            }
        }
    }
}