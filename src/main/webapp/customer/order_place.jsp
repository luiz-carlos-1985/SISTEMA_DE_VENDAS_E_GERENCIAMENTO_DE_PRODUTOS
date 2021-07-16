<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"
 import="java.util.*,me.anant.PMS.model.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%@include file="/includes/head.jsp"%>
</head>
<body style="background-color: #f8f9fa !important;">
    <%@include file="/includes/header.jsp"%>
    <main role="main" class="container"> <%@include file="/includes/msg.jsp"%>
    <div class="card">
        <div class="card-header text-white shadow bg-success text-center">
            <h2 class="float-left">Pedido Realizado</h2>
        </div>
        <div class="card-body">
	            <table class="table table-hover">
	                <thead>
	                    <tr>
	                        <th scope="col">Nome</th>
	                        <th scope="col">Pre�o</th>
	                        <th scope="col">Quantidade</th>
	                        <th scope="col">Total</th>
	                    </tr>
	                </thead>
	                <tbody>
	                    <%
	                    	float sum = 0;
	                    	Set<OrderProduct> opList = (Set<OrderProduct>) request.getAttribute("opList");
	                    	for (OrderProduct op : opList)
	                        {
	                    		sum = sum + op.getProduct().getProductPrice() * op.getBuyqty();
	                        %>
	                        <tr>
	                            <td><%=op.getProduct().getProductName()%></td>
	                            <td>R$ <%=op.getProduct().getProductPrice()%></td>
	                            <td><%=op.getBuyqty()%></td>
	                            <td>R$ <%=op.getProduct().getProductPrice() * op.getBuyqty()%></td>
	                        </tr>
	                        <%   
	                        }
	                    %>
	                    <tr><td  colspan="3" class="text-center"><b>Total</b></td><td>R$ <%= sum %></td></tr>
	                </tbody>
	            </table>
        </div>
    </div>
    <br><br><br>
    </main>
    <%@include file="/includes/footer.jsp"%>
</body>
</html>