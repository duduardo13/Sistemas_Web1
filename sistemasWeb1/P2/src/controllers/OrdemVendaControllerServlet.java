package controllers;

import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

// ADICIONADO: Imports necess�rios para os novos DAOs e Modelos
import daos.ClientesDAO;
import daos.OrdemVendaDAO;
import daos.VendedorDAO;
import models.Cliente;
import models.OrdemVenda;
import models.Vendedor;

/**
 * @author Eduardo Barbosa E Vinicius Pontes
 */

@WebServlet(name = "OrdemVendaControllerServlet", urlPatterns = { "/ordensVenda/*" }, loadOnStartup = 1)
public class OrdemVendaControllerServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private OrdemVendaDAO _ordemVendaDAO;
    private ClientesDAO _clienteDAO;
    private VendedorDAO _vendedorDAO;

    public void init() {
        String jdbcURL = getServletContext().getInitParameter("jdbcURL");
        String jdbcUsername = getServletContext().getInitParameter("jdbcUsername");
        String jdbcPassword = getServletContext().getInitParameter("jdbcPassword");

        this._ordemVendaDAO = new OrdemVendaDAO(jdbcURL, jdbcUsername, jdbcPassword);
        this._clienteDAO = new ClientesDAO(jdbcURL, jdbcUsername, jdbcPassword);
        this._vendedorDAO = new VendedorDAO(jdbcURL, jdbcUsername, jdbcPassword);
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
        } catch (SQLException | ParseException ex) { 
            throw new ServletException(ex);
        }
    }

    private void list(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ServletException {
        List<OrdemVenda> list = _ordemVendaDAO.listAll();
        request.setAttribute("list", list);

        RequestDispatcher dispatcher = request.getRequestDispatcher("/view/ordemVenda/list.jsp");
        dispatcher.forward(request, response);
    }

    private void showNewForm(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {
        List<Cliente> listaClientes = _clienteDAO.listAll();
        List<Vendedor> listaVendedores = _vendedorDAO.listAll();

        request.setAttribute("listaClientes", listaClientes);
        request.setAttribute("listaVendedores", listaVendedores);

        RequestDispatcher dispatcher = request.getRequestDispatcher("/view/ordemVenda/form.jsp");
        dispatcher.forward(request, response);
    }

    private void showEditForm(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        OrdemVenda ordemVenda = _ordemVendaDAO.getById(id);
        
        List<Cliente> listaClientes = _clienteDAO.listAll();
        List<Vendedor> listaVendedores = _vendedorDAO.listAll();

        request.setAttribute("ordemVenda", ordemVenda);
        request.setAttribute("listaClientes", listaClientes);
        request.setAttribute("listaVendedores", listaVendedores);

        RequestDispatcher dispatcher = request.getRequestDispatcher("/view/ordemVenda/form.jsp");
        dispatcher.forward(request, response);
    }

    private void insert(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ParseException {

        double purchAmt = Double.parseDouble(request.getParameter("purch_amt"));
        String ordDateStr = request.getParameter("ord_date");
        int customerId = Integer.parseInt(request.getParameter("customer_id"));
        int salesmanId = Integer.parseInt(request.getParameter("salesman_id"));
        
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date ordDate = dateFormat.parse(ordDateStr);

        OrdemVenda entity = new OrdemVenda(purchAmt, ordDate, customerId, salesmanId);
        _ordemVendaDAO.insert(entity);
        response.sendRedirect("list");
    }

    private void update(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ParseException {
        int id = Integer.parseInt(request.getParameter("ord_no"));
        double purchAmt = Double.parseDouble(request.getParameter("purch_amt"));
        String ordDateStr = request.getParameter("ord_date");
        int customerId = Integer.parseInt(request.getParameter("customer_id"));
        int salesmanId = Integer.parseInt(request.getParameter("salesman_id"));

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date ordDate = dateFormat.parse(ordDateStr);

        OrdemVenda entity = new OrdemVenda(id, purchAmt, ordDate, customerId, salesmanId);
        _ordemVendaDAO.update(entity);
        response.sendRedirect("list");
    }

    private void delete(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));

        OrdemVenda entity = new OrdemVenda(id);
        _ordemVendaDAO.delete(entity);

        response.sendRedirect("list");
    }
}