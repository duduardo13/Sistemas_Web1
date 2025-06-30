<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html lang="pt-br">
<head>
    <meta charset="UTF-8">
    <title>Inventário - Cadastro de Vendedores</title>
</head>
<body>
    <header>
        <div class="menu-left"><a href="${pageContext.request.contextPath}/home">Prova 2</a></div>
        <div class="menu-right">
            <ul>
                <li><a href="${pageContext.request.contextPath}/clientes">Clientes</a></li>
                <li><a href="${pageContext.request.contextPath}/vendedores">Vendedores</a></li>
                <li><a href="${pageContext.request.contextPath}/ordensVenda">Ordens de Venda</a></li>
            </ul>
        </div>
    </header>
    
    <main class="container">
        <h1>
            <c:if test="${vendedor != null}">Editar Vendedor</c:if>
            <c:if test="${vendedor == null}">Novo Vendedor</c:if>
        </h1>
        
        <div class="form-container">
            <form action="${vendedor != null ? 'update' : 'insert'}" method="post">
                <input type="hidden" name="id" value="<c:out value='${vendedor.salesmanId}' />" />
                
                <div class="form-group">
                    <label for="name">Nome do Vendedor:</label>
                    <input type="text" id="name" name="name" value="<c:out value='${vendedor.name}' />" required />
                </div>
                
                <div class="form-group">
                    <label for="city">Cidade:</label>
                    <input type="text" id="city" name="city" value="<c:out value='${vendedor.city}' />" required />
                </div>
                
                <div class="form-group">
                    <label for="commission">Comissão:</label>
                    <input type="number" step="0.01" id="commission" name="commission" value="<c:out value='${vendedor.commission}' />" required />
                </div>
                
                <div class="form-group-buttons">
                    <a href="${pageContext.request.contextPath}/vendedores" class="button btn-cinza">Cancelar</a>
                    <input type="submit" value="Salvar" class="button"/>
                </div>
            </form>
        </div>
    </main>
    <footer>
        Desenvolvido por Eduardo Barbosa E Vinicius Pontes
    </footer>
</body>
</html>