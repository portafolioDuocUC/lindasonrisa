<%-- 
    Document   : Proveedor
    Created on : 18-04-2018, 14:16:34
    Author     : chasc
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%
    String usuarioConectado = (String) session.getAttribute("usuarioConectado");

    if (usuarioConectado != "Administrador") {
        response.sendRedirect("login.jsp");
    }
    
    String action, rut, dv, btnValue, title = "";
    if (request.getParameter("rut") == null) {
        action = "IngresarProveedor.do";
        rut = "";
        dv = "";
        btnValue = "Ingresar Proveedor";
        title = "Ingresar Proveedor";
    } else {
        rut = request.getParameter("rut");
        dv = request.getParameter("dv");
        action = "ModificarProveedor.do?rut=" + rut;
        btnValue = "Modificar Proveedor";
        title = "Modificar Proveedor";
    }
%>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">        
        <link rel="stylesheet" type="text/css" href="css/estilo.css">

        <title><%=title%></title>
    </head>
    <body>
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
        <a id="cerrarSesion" href="login.jsp">Cerrar Sesión</a>

        <div id="container">
        <%
            String mensaje;
            mensaje = (request.getAttribute("msg") == null) ? "" : (String) request.getAttribute("msg");
        %>
    
            <form id="formUsuario" name="form3" method="POST" action="<%=action%>">
                <center>
                    <table>
                        <tr>
                            <td colspan="2" ><center><div id="titulo">&nbsp;<%=title%> </div></center></td>	

                        </tr>	
                        <tr>
                            <td>RUT:</td><td><input type="text" id="rut" name="rut" value="<%=rut%>"  required></td>	

                        </tr>
                        <tr>
                            <td>DV:</td><td><input type="text" id="dv" name="dv" value="<%=dv%>"  required></td>
                        </tr>
                        <tr>
                            <td>Razón Social:</td><td><input type="text" id="razon" name="razon" required> </td>	

                        </tr>
                        <tr>
                            <td>Dirección:</td><td><input type="text" id="direccion" name="direccion" required> </td>	

                        </tr>	
                        <tr>
                            <td>Teléfono:</td><td><input type="text" id="telefono" name="telefono" required></td>	

                        </tr>
                        <tr>
                            <td>Email:</td><td><input type="text" id="email" name="email"  required></td>	

                        </tr>
                        <tr>
                            <td>Rubro:</td><td><input type="text" id="rubro" name="rubro"  required></td>	

                        </tr>      
                        <tr>
                            <td>Contraseña:</td><td><input type="text" id="password" name="password"  required></td>	

                        </tr>
                        <tr>
                            <td>Usuario</td><td><input type="text" id="usuario" name="usuario"  required></td>	

                        </tr>
                        <center>	
                            <tr>
                                <td colspan="2"><center><label id="enviar"><input type="submit" value="<%=btnValue%>"></label></center></td>

                            </tr>	
                        </center>

                    </table>
                    <a href="ListarProveedor.do?">Listar Proveedores </a><br><a href="clientes.jsp?">Cliente</a>
                </center>
            </form>
        
        <p><%=mensaje%></p>
        </div>
    </body>
</html>
