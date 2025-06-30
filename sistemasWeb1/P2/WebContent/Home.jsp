<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="pt-br">
<head>
    <meta charset="UTF-8">
    <title>Inventário - Início</title>
</head>
<body>
    <header>
        <div class="menu-left">
            <a href="${pageContext.request.contextPath}/home">Prova 2</a>
        </div>
        
        <nav class="menu-right">
            <ul>
                <li><a href="${pageContext.request.contextPath}/clientes">Clientes</a></li>
                <li><a href="${pageContext.request.contextPath}/vendedores">Vendedores</a></li>
                <li><a href="${pageContext.request.contextPath}/ordensVenda">Ordens de Venda</a></li>
            </ul>
        </nav>
    </header>

    <main>
        <div class="card">
            <h2>Bem-vindo ao Sistema de Inventário</h2>
            <p>
                Trabalho Prático final de SWE1
            </p>
        </div>
    </main>
    
    <footer>
        Desenvolvido por Eduardo Barbosa E Vinicius Pontes
    </footer>
</body>
</html>