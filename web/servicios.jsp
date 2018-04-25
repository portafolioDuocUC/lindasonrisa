<%-- 
    Document   : servicios
    Created on : 21-abr-2018, 19:14:46
    Author     : Diana
--%>

<%@page import="modelo.ServicioVO"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" type="text/css" href="css/estilo.css">
        <script src="js/script.js"></script>
        <title>Servicios</title>
    </head>
    <body>
        <a id="cerrarSesion" href="login.jsp">Cerrar Sesión</a>
        <%
            String usuarioConectado = (String) session.getAttribute("usuarioConectado");

            if (usuarioConectado != "Administrador") {
                response.sendRedirect("login.jsp");
            }

            List<ServicioVO> listaServicios = (List<ServicioVO>) session.getAttribute("listaServicios");
            String mensaje;
            mensaje = (request.getAttribute("msg") == null) ? "" : (String) request.getAttribute("msg");
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

            <h1>Servicios</h1>

            <table id="tablaServicio">
                <tr>
                    <th>Código</th>
                    <th>Nombre</th>
                    <th colspan="2">Acción</th>
                </tr> 
                <%
                    for (ServicioVO s : listaServicios) {
                        out.print("<tr>");
                        out.print("<td>" + s.getId() + "</td>");
                        out.print("<td>" + s.getNombre() + "</td>");
                        out.print("<td><a href=# id=eliminaServicio onclick=eliminaServicio('" + s.getId() + "')>Eliminar</a></td>");
                        out.print("<td><a href=# onclick=cargaDatosServicio('" + s.getNombre() + "','" + s.getId() + "')>Modificar</a></td>");
                        out.print("</tr>");
                    }
                %>
            </table>




            <form name="formServicios" id="formServicios" method="post" action="AgregarServicio.do">
                <label>Nombre</label>
                <input id="nombre" type="text" maxlength="30" name="nombre" placeholder="Ingrese nombre del servicio..." />
                <input id="btnServicio" type="submit" value="Ingresar">
            </form>      
            <p><%=mensaje%></p>
        </div>



        <footer>
            <p>© 2018 Linda Sonrisa. Todos los derechos reservados.</p>
        </footer>
    </body>
</html>

