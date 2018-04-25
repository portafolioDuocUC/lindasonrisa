<%-- 
    Document   : index
    Created on : 24-04-2018, 1:13:44
    Author     : chasc
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" type="text/css" href="css/estilo.css">
        <title>Iniciar sesion</title>
    </head>
    <body>
        <%
            String mensaje;
            mensaje = (request.getAttribute("msg") == null) ? "" : (String) request.getAttribute("msg");
        %>
        <header>
            <img id="logo" src="img/logo.png">
            <h1>Sistema de Gestión Odontológica<br><span>Linda Sonrisa</span></h1>
        </header>
    <center><div id="containerLogin">
            <h1>Iniciar sesión</h1>

            <form id="formLogin" action="Autenticar.do" method="post">
                <table id="login">
                    <tr>
                        <td>Usuario:</td><td><input type="text" name="usuario"></td>
                    </tr>
                    <tr>
                        <td>Contraseña:</td><td><input type="password" name="password"></td>
                    </tr>
                    <tr>
                        <td></td><td><input id="btnLogin" type="submit" value="Aceptar"></td>
                    </tr>
                </table>


            </form>

            
            <a id="registrarse" href="clientes.jsp">Registrarse</a><br><br>
            
            <div id="msgLogin"><%=mensaje%></div>
        </div></center>
</body>
</html>
