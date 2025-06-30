<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html lang="pt-br">
<head>
    <meta charset="UTF-8">
    <title>Inventário - Lista de Clientes</title>
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
    
        <c:if test="${param.msg == 'deleteSuccess'}">
            <div class="alert alert-success">Cliente removido com sucesso!</div>
        </c:if>
        <c:if test="${param.msg == 'deleteError'}">
            <div class="alert alert-danger"><strong>Erro!</strong> Este cliente não pode ser removido, pois está associado a uma ou mais ordens de venda.</div>
        </c:if>
        <div class="wrapper">
            <h1>Clientes</h1>
            <a href="${pageContext.request.contextPath}/clientes/new" class="button">Novo Cliente</a>
        </div>
        
        <table>
            <thead>
                <tr>
                    <th>ID</th>
                    <th>Nome do Cliente</th>
                    <th>Cidade</th>
                    <th>Classificação</th>
                    <th>ID Vendedor</th>
                    <th colspan="2">Ações</th>
                </tr>
            </thead>
            <tbody>
                <c:if test="${empty list}">
                    <tr>
                        <td colspan="7" style="text-align: center; padding: 30px;">Sem clientes cadastrados.</td>
                    </tr>
                </c:if>
                
                <c:forEach var="item" items="${list}">
                    <tr>
                        <td><c:out value="${item.customerId}" /></td>
                        <td><c:out value="${item.custName}" /></td>
                        <td><c:out value="${item.city}" /></td>
                        <td><c:out value="${item.grade}" /></td>
                        <td><c:out value="${item.salesmanId}" /></td>
                        <td>
                            <a href="${pageContext.request.contextPath}/clientes/edit?id=<c:out value='${item.customerId}' />">Editar</a>
                        </td>
                        <td>
                            <a href="${pageContext.request.contextPath}/clientes/delete?id=<c:out value='${item.customerId}' />" 
                               onclick="return confirm('Tem certeza que deseja remover este cliente?');">Remover</a>
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