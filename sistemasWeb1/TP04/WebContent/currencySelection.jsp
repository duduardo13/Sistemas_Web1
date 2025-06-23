<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="pt-br">
<head>
    <meta charset="UTF-8">
    <title>Seleção de Moeda</title>
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