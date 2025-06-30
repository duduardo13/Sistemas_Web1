<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html lang="pt-br">
<head>
    <meta charset="UTF-8">
    <title>Inventário - Cadastro de Clientes</title>
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
            <c:if test="${cliente != null}">Editar Cliente</c:if>
            <c:if test="${cliente == null}">Novo Cliente</c:if>
        </h1>
        
        <div class="form-container">
            <form action="${cliente != null ? 'update' : 'insert'}" method="post">
                <input type="hidden" name="id" value="<c:out value='${cliente.customerId}' />" />
                
                <div class="form-group">
                    <label for="cust_name">Nome do Cliente:</label>
                    <input type="text" id="cust_name" name="cust_name" value="<c:out value='${cliente.custName}' />" required />
                </div>
                
                <div class="form-group">
                    <label for="city">Cidade:</label>
                    <input type="text" id="city" name="city" value="<c:out value='${cliente.city}' />" required />
                </div>
                
                <div class="form-group">
                    <label for="grade">Classificação:</label>
                    <input type="text" id="grade" name="grade" value="<c:out value='${cliente.grade}' />" required />
                </div>
                
                <div class="form-group">
                    <label for="salesman_id">Vendedor:</label>
                    <select name="salesman_id" id="salesman_id" required>
                        <option value="">-- Selecione um vendedor --</option>
                        <c:forEach var="vendedor" items="${listaVendedores}">
                            <option value="${vendedor.salesmanId}" 
                                <c:if test="${cliente.salesmanId == vendedor.salesmanId}">selected</c:if>>
                                ${vendedor.name}
                            </option>
                        </c:forEach>
                    </select>
                </div>
                
                <div class="form-group-buttons">
                    <a href="${pageContext.request.contextPath}/clientes" class="button btn-cinza">Cancelar</a>
                    <input type="submit" value="Salvar" class="button"/>
                </div>
            </form>
        </div>
    </main>

    <footer>
        Desenvolvido por Eduardo Barbosa E Vinicius Pontes
</body>
</html>