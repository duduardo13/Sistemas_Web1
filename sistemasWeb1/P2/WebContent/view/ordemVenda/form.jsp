<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<!DOCTYPE html>
<html lang="pt-br">
<head>
    <meta charset="UTF-8">
    <title>Inventário - Ordem de Venda</title>
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
            <c:if test="${ordemVenda != null}">Editar Ordem de Venda</c:if>
            <c:if test="${ordemVenda == null}">Nova Ordem de Venda</c:if>
        </h1>
        
        <div class="form-container">
            <form action="${ordemVenda != null ? 'update' : 'insert'}" method="post">
                <input type="hidden" name="ord_no" value="<c:out value='${ordemVenda.ordNo}' />" />
                
                <div class="form-group">
                    <label for="purch_amt">Valor da Compra:</label>
                    <input type="number" step="0.01" id="purch_amt" name="purch_amt" value="<c:out value='${ordemVenda.purchAmt}' />" required />
                </div>
                
                <div class="form-group">
                    <label for="ord_date">Data da Ordem:</label>
                    <fmt:formatDate value="${ordemVenda.ordDate}" pattern="yyyy-MM-dd" var="formattedDate" />
                    <input type="date" id="ord_date" name="ord_date" value="${formattedDate}" required />
                </div>
                
                <div class="form-group">
                    <label for="customer_id">Cliente:</label>
                    <select name="customer_id" id="customer_id" required>
                        <option value="">-- Selecione um cliente --</option>
                        <c:forEach var="cliente" items="${listaClientes}">
                            <option value="${cliente.customerId}" 
                                <c:if test="${ordemVenda.customerId == cliente.customerId}">selected</c:if>>
                                ${cliente.custName}
                            </option>
                        </c:forEach>
                    </select>
                </div>
                
                <div class="form-group">
                    <label for="salesman_id">Vendedor:</label>
                    <select name="salesman_id" id="salesman_id" required>
                        <option value="">-- Selecione um vendedor --</option>
                        <c:forEach var="vendedor" items="${listaVendedores}">
                            <option value="${vendedor.salesmanId}" 
                                <c:if test="${ordemVenda.salesmanId == vendedor.salesmanId}">selected</c:if>>
                                ${vendedor.name}
                            </option>
                        </c:forEach>
                    </select>
                </div>
                
                <div class="form-group-buttons">
                    <a href="${pageContext.request.contextPath}/ordensVenda" class="button btn-cinza">Cancelar</a>
                    <input type="submit" value="Salvar" class="button" />
                </div>
            </form>
        </div>
    </main>
    <footer>
        Desenvolvido por Eduardo Barbosa E Vinicius Pontes
    </footer>
</body>
</html>