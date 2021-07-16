<%@ page isErrorPage="true" %>

<html>
<head>
    <title>!!! Página de Erro !!!</title>
</head>

<body>
<h1>Opps...</h1>
<p>Desculpe, um erro ocorreu...</p>
<pre>Informação Técnica: <%=exception.getMessage() %></pre>
<p>Por Favor, entre em contato com o suporte: gerente@varejonline.com </p>
</body>
</html>