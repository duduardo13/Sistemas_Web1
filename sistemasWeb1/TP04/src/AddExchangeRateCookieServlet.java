import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import daos.CurrencyPreferenceDAO;
import models.CurrencyPreference;

@WebServlet(name = "saveExchangeRatesServlet",
            urlPatterns = {"/save-exchange-rates-pair"}) // N�o precisa mais do loadOnStartup=1
public class AddExchangeRateCookieServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;
    // O DAO agora � um atributo da classe, para ser reutilizado.
    private CurrencyPreferenceDAO preferenceDAO;

    /**
     * O m�todo init() � chamado pelo servidor APENAS UMA VEZ,
     * quando o servlet � criado. � o lugar perfeito para ler configura��es
     * e inicializar objetos pesados como o DAO.
     */
    public void init() {
        // Pega os par�metros de conex�o definidos no arquivo web.xml
        String jdbcURL = getServletContext().getInitParameter("jdbcURL");
        String jdbcUsername = getServletContext().getInitParameter("jdbcUsername");
        String jdbcPassword = getServletContext().getInitParameter("jdbcPassword");

        // Cria a inst�ncia do DAO que ser� usada por todas as requisi��es.
        this.preferenceDAO = new CurrencyPreferenceDAO(jdbcURL, jdbcUsername, jdbcPassword);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        
        String thePair = req.getParameter("currencyPair");
        
        if (thePair != null && !thePair.isEmpty()) {
            
            String userId = "usuario_teste_01";
            CurrencyPreference preference = new CurrencyPreference(userId, thePair);
            
            try {
                // Usa o DAO que j� foi inicializado no m�todo init()
                // � mais eficiente do que criar um novo a cada requisi��o.
                preferenceDAO.savePreference(preference);
                
            } catch (SQLException e) {
                e.printStackTrace();
                throw new ServletException("Erro de banco de dados ao salvar prefer�ncia.", e);
            }
            
            Cookie cookie = new Cookie("currencyPair", thePair);
            cookie.setMaxAge(30 * 24 * 60 * 60);
            resp.addCookie(cookie);
            
            resp.sendRedirect("exchange-rate");
        }
    }
}