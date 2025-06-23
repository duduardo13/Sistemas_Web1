<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="pt-br">
<head>
    <meta charset="UTF-8">
    <title>Seleção de Moeda</title>
    <style>
        /* Todo o seu código CSS que já tínhamos vai aqui... */
        * { box-sizing: border-box; }
        body, html { margin: 0; padding: 0; height: 100%; font-family: 'Segoe UI', sans-serif; background-color: #f0f4f8; display: flex; flex-direction: column; }
        header { background-color: white; box-shadow: 0 2px 8px rgba(0,0,0,0.1); padding: 15px 30px; height: 70px; display: flex; align-items: center; justify-content: space-between; z-index: 100; }
        header h1 { margin: 0; font-size: 24px; color: #2e7d32; }
        nav.menu a { text-decoration: none; color: #4CAF50; font-weight: 600; margin-left: 20px; }
        main { flex: 1; margin-top: 20px; display: flex; justify-content: center; align-items: center; }
        .card { background-color: white; padding: 40px 30px; border-radius: 16px; box-shadow: 0 10px 25px rgba(0,0,0,0.1); text-align: center; max-width: 450px; width: 100%; }
        .card h2 { margin-bottom: 25px; font-size: 28px; color: #2e7d32; }
        form { text-align: left; font-size: 18px; line-height: 2; }
        .botao-salvar { display: inline-block; margin-top: 20px; background-color: #4CAF50; color: white; padding: 12px 24px; border: none; border-radius: 8px; font-size: 16px; cursor: pointer; }
        .botao-salvar:hover { background-color: #388e3c; }
        footer { background-color: white; box-shadow: 0 -2px 8px rgba(0,0,0,0.1); height: 50px; display: flex; justify-content: center; align-items: center; color: #4CAF50; font-weight: 600; }
    </style>
</head>
<body>
    <header>
        <a href="currencySelection.jsp" style="text-decoration:none;">
            <h1>Cotação de Moedas</h1>
        </a>
        <nav class="menu">
            <a href="currencySelection.jsp">Seleção de Moeda</a>
            <a href="credito.jsp">Créditos</a>
        </nav>
    </header>
    <main>
        <div class="card">
            <h2>Selecione sua Moeda de Preferência</h2>
            <form method="post" action="save-exchange-rates-pair">
                <input type="radio" name="currencyPair" value="USD/EUR" id="usd-eur"> <label for="usd-eur">USD/EUR</label><br/>
                <input type="radio" name="currencyPair" value="USD/CAD" id="usd-cad"> <label for="usd-cad">USD/CAD</label><br/>
                <input type="radio" name="currencyPair" value="USD/AUD" id="usd-aud"> <label for="usd-aud">USD/AUD</label><br/><br/>
                <input type="submit" value="Salvar Preferência" class="botao-salvar">
            </form>
        </div>
    </main>
    <footer>
        Desenvolvido por Eduardo Barbosa E Vinicius Pontes
    </footer>
</body>
</html>