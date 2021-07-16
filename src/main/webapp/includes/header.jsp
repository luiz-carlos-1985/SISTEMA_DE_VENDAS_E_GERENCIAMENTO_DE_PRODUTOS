<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<button onclick="topFunction()" id="myBtn" title="Vá para o topo"><i class="fa fa-arrow-up"></i></button>

<nav class="navbar navbar-expand-lg navbar-dark bg-dark sticky-top shadow p-3 mb-5">
    <div class="container">
    	<sec:authorize access="!isAuthenticated()">
        	<a class="navbar-brand" href="/">SGVPI</a>
        </sec:authorize>
        <sec:authorize access="hasRole('ADMIN')">
        	<a class="navbar-brand" href="/admin/product/list">SGVPI</a>
        </sec:authorize>
        <sec:authorize access="hasRole('CUSTOMER')">
        	<a class="navbar-brand" href="/customer/order_place">SGVPI</a>
        </sec:authorize>
        <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent"
            aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Alternar Navegação">
            <span class="navbar-toggler-icon"></span>
        </button>

        <div class="collapse navbar-collapse" id="navbarSupportedContent">
        	<ul class="navbar-nav mr-auto">
	            <sec:authorize access="hasRole('ADMIN')">
                	<li class="nav-item"><a class="nav-link" href="/admin/product/list">Produto</a></li>
					<li class="nav-item"><a class="nav-link" href="/admin/order/list">Pedido</a></li>
					<li class="nav-item"><a class="nav-link" href="/admin/product_category/list">Categoria</a></li>
                	<li class="nav-item"><a class="nav-link" href="/admin/product/report">Relatório por Produtos</a></li>
                	<li class="nav-item"><a class="nav-link" href="/admin/category/list">Relatório por Categorias</a></li>
                </sec:authorize>
	            <sec:authorize access="hasRole('CUSTOMER')">
                	<li class="nav-item"><a class="nav-link" href="/customer">Realizar Encomenda</a></li>
                	<li class="nav-item"><a class="nav-link" href="/customer/order/list">Meus Pedidos</a></li>
                </sec:authorize>
            </ul>
            
            <ul class="navbar-nav my-2 my-lg-0">
            	<sec:authorize access="!isAuthenticated()">
            		<li class="nav-item"><a class="nav-link" href="/login">LOGAR</a></li>
           		</sec:authorize>
           		<sec:authorize access="isAuthenticated()">
	                <li class="nav-item dropdown">
	                    <a class="nav-link dropdown-toggle" href="#" id="navbarDropdown" role="button"
	                    data-toggle="dropdown" aria-haspopup="true" aria-expanded="false"> <sec:authentication property="name"/> </a>
	                    <div class="dropdown-menu" aria-labelledby="navbarDropdown">
	                        <a class="dropdown-item" href="/logout">SAIR</a>
	                    </div>
	                </li>
                </sec:authorize>
            </ul>
        </div>
    </div>
</nav>
