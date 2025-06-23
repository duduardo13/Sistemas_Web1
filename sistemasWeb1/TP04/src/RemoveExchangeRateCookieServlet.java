import java.io.IOException;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.Optional;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import daos.CurrencyPreferenceDAO;

/**
 * =======================================================
 * Nomes da Dupla:
  * - Eduardo Barbosa e Vinicius Pontes
 * =======================================================
 */
@WebServlet(name = "removeCookieServlet", urlPatterns = { "/remove-currency-pair" })
public class RemoveExchangeRateCookieServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;
    private CurrencyPreferenceDAO preferenceDAO;

    @Override
    public void init() throws ServletException {
       
        String jdbcURL = getServletContext().getInitParameter("jdbcURL");
        String jdbcUsername = getServletContext().getInitParameter("jdbcUsername");
        String jdbcPassword = getServletContext().getInitParameter("jdbcPassword");

       
        this.preferenceDAO = new CurrencyPreferenceDAO(jdbcURL, jdbcUsername, jdbcPassword);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

       
        try {
            String userId = "usuario_teste_01"; 
            preferenceDAO.deletePreference(userId);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new ServletException("Erro de banco de dados ao remover prefer�ncia.", e);
        }

       
        if (req.getCookies() != null) {
            Optional<Cookie> cookieOptional = Arrays.stream(req.getCookies())
                    .filter(c -> ExchangeRatesServlet.currencyPairKey.equals(c.getName()))
                    .findAny();

            if (cookieOptional.isPresent()) {
                Cookie cookie = cookieOptional.get();
                cookie.setMaxAge(0); // Diz ao navegador para apagar o cookie
                resp.addCookie(cookie);
            }
        }

        
        resp.sendRedirect("currencySelection.jsp");
    }
}