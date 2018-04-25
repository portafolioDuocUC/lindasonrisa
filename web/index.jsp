<%-- 
    Document   : index
    Created on : 21-abr-2018, 16:26:53
    Author     : Diana
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
         <link rel="stylesheet" type="text/css" href="css/estilo.css">
         <script src="js/script.js"></script>
        <title>Inicio</title>
    </head>
    <body>
        <a id="cerrarSesion" href="login.jsp">Cerrar Sesión</a>
    <%
    String usuarioConectado = (String) session.getAttribute("usuarioConectado");
      
    if(usuarioConectado != "Administrador"){
        response.sendRedirect("login.jsp");
    }
    %>
        <header>
            <img id="logo" src="img/logo.png">
            <h1>Sistema de Gestión Odontológica<br><span>Linda Sonrisa</span></h1>
        </header>
        <nav>
            <a href="index.jsp">Inicio</a> |
            <a href="ListarProductos.do">Productos</a> |
            <a href="ListarServicios.do">Servicios</a> |
            <a href="clientes.jsp">Clientes</a> |
            <a href="ListarCliente.do">Listar Clientes </a> |
            <a href="empleados.jsp">Empleados</a> |
            <a href="ListarEmpleado.do">Listar Empleados </a> |
            <a href="proveedores.jsp">Proveedores</a>
            <a href="ListarProveedor.do">Listar Proveedores </a> |
            
        </nav>
        
        <div id="container">
            <h1>Bienvenido<span>, Administrador.</span></h1>
        </div>
                    
       
        <footer>
            <p>© 2018 Linda Sonrisa. Todos los derechos reservados.</p>
        </footer>
    </body>
</html>
