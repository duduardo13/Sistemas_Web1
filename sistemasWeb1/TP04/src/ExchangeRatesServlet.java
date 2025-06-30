import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.Optional;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import daos.CurrencyPreferenceDAO;
import models.CurrencyPreference;

/**
 * =======================================================
 * Nomes da Dupla:
* - Eduardo Barbosa e Vinicius Pontes
 * =======================================================
 */
@WebServlet(name = "exchangeRatesServlet", urlPatterns = { "/exchange-rate" })
public class ExchangeRatesServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;
    static final String currencyPairKey = "currencyPair";
    
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
        
        String currencyPair = null;

        if (req.getCookies() != null) {
            Optional<Cookie> cookie = Arrays.stream(req.getCookies())
                                           .filter(c -> currencyPairKey.equals(c.getName()))
                                           .findAny();
            if (cookie.isPresent()) {
                currencyPair = cookie.get().getValue();
            }
        }
        
        if (currencyPair == null) {
            try {
                String userId = "usuario_teste_01";
                CurrencyPreference preference = preferenceDAO.getPreference(userId);
                if (preference != null) {
                    currencyPair = preference.getCurrencyPair();
                }
            } catch (SQLException e) {
                e.printStackTrace();
                throw new ServletException("Erro de banco de dados ao buscar prefer�ncia.", e);
            }
        }
        
        if (currencyPair != null) {
            showCurrencyRate(resp, currencyPair);
        } else {
            RequestDispatcher rd = req.getRequestDispatcher("/currencySelection.jsp");
            rd.forward(req, resp);
        }
    }

    private void showCurrencyRate(HttpServletResponse resp, String currencyPair) throws IOException {
        resp.setContentType("text/html; charset=UTF-8");
        PrintWriter writer = resp.getWriter();

        long rate = Math.round((Math.random() + 1) * 100);

        writer.write("<!DOCTYPE html>");
        writer.write("<html lang='pt-br'>");
        writer.write("<head>");
        writer.write("<meta charset='UTF-8'>");
        writer.write("<title>Cota��o Atual</title>");
        writer.write("</head>");
        writer.write("<body>");
        writer.write("<header>");
        writer.write("<a href='currencySelection.html' style='text-decoration:none;'><h1>Cota��o de Moedas</h1></a>");
        writer.write("<nav class='menu'><a href='currencySelection.jsp'>Sele��o de Moeda</a><a href='credito.jsp'>Cr�ditos</a></nav>");
        writer.write("</header>");
        writer.write("<main>");
        writer.write("<div class='card'>");
        writer.write("<h2>Cota��o de Exemplo</h2>");
        writer.write(String.format("<p>A taxa de c�mbio para <strong>%s</strong> �</p>", currencyPair));
        writer.write(String.format("<p style='font-size: 2em; color: #2e7d32; margin: 10px 0;'>%d</p>", rate));
        writer.write("<a href='remove-currency-pair' class='botao-voltar'>Remover Prefer�ncia</a>");
        writer.write("</div>");
        writer.write("</main>");
        writer.write("<footer>Desenvolvido por Eduardo Barbosa e Vinicius Pontes </footer>");
        writer.write("</body>");
        writer.write("</html>");

        writer.close();
    }
}