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
        <div class="card-header text-white shadow bg-dark">
            <h2 class="float-left">Relat?rio de Produtos</h2>
        </div>
        <div class="card-body">
            <table class="table table-hover">
                <thead>
                    <tr>
                        <th scope="col">ID</th>
                        <th scope="col">Nome</th>
                        <th scope="col">Pre?o</th>
                        <th scope="col">Quantidade Vendida</th>
                        <th scope="col">Total</th>
                    </tr>
                </thead>
                <tbody>
                    <%
                    	float sumAmu = 0;
                    	List<Product> pl = (List<Product>) request.getAttribute("pList");
                    	for (Product p : pl)
                        {
                        %>
                        <tr>
                            <th scope="row"><%=p.getProductId()%></th>
                            <td><%=p.getProductName()%></td>
                            <td>R$ <%= p.getProductPrice() %></td>
                            <%
                            int sumQty = 0;
                            for(OrderProduct op: p.getOrderProduct()) {
                            	if(!op.getOrder().getStatus().equals("CANCEL") && !op.getOrder().getStatus().equals("REJECT")) {
                            		sumQty = sumQty + op.getBuyqty();
                            	}
                            }
                            %>
                            <td><%= sumQty %></td>
                            <td>R$ <%= sumQty * p.getProductPrice() %></td>
                        </tr>
                        <%   
                        	sumAmu = sumAmu + sumQty * p.getProductPrice();
                        }
                    %>
                    <tr><td  colspan="4" class="text-center"><b>Total</b></td><td>R$ <%= sumAmu %></td></tr>
                </tbody>
            </table>
        </div>
    </div>
    <br><br><br>
    </main>
    <%@include file="/includes/footer.jsp"%>
</body>
</html>