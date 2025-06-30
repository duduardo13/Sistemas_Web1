<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<!DOCTYPE html>
<html lang="pt-br">
<head>
    <meta charset="UTF-8">
    <title>Inventário - Lista de Ordens de Venda</title>
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
        <div class="wrapper">
            <h1>Ordens de Venda</h1>
            <a href="${pageContext.request.contextPath}/ordensVenda/new" class="button">Nova Ordem</a>
        </div>
        
        <table>
            <thead>
                <tr>
                    <th>Nº Ordem</th>
                    <th>Valor</th>
                    <th>Data</th>
                    <th>ID Cliente</th>
                    <th>ID Vendedor</th>
                    <th colspan="2">Ações</th>
                </tr>
            </thead>
            <tbody>
                <c:if test="${empty list}">
                    <tr>
                        <td colspan="7" style="text-align: center; padding: 30px;">Sem ordens de venda cadastradas.</td>
                    </tr>
                </c:if>
                
                <c:forEach var="ordem" items="${list}">
                    <tr>
                        <td><c:out value="${ordem.ordNo}" /></td>
                        <td><fmt:formatNumber value="${ordem.purchAmt}" type="currency" currencySymbol="R$ " /></td>
                        <td><fmt:formatDate value="${ordem.ordDate}" pattern="dd/MM/yyyy" /></td>
                        <td><c:out value="${ordem.customerId}" /></td>
                        <td><c:out value="${ordem.salesmanId}" /></td>
                        <td>
                            <a href="${pageContext.request.contextPath}/ordensVenda/edit?id=<c:out value='${ordem.ordNo}' />">Editar</a>
                        </td>
                        <td>
                            <a href="${pageContext.request.contextPath}/ordensVenda/delete?id=<c:out value='${ordem.ordNo}' />" 
                               onclick="return confirm('Tem certeza que deseja remover esta ordem?');">Remover</a>
                        </td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
    </main>
    <footer>
        Desenvolvido por Eduardo Barbosa E Vinicius Pontes
    </footer>
</body>
</html>