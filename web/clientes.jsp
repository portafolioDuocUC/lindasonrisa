<%-- 
    Document   : clientes
    Created on : 24-abr-2018, 6:14:00
    Author     : Diana
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%

    String action, run, dv, btnValue, title = "";
    if (request.getParameter("run") == null) {
        action = "IngresarCliente.do";
        run = "";
        dv = "";
        btnValue = "Ingresar Cliente";
        title = "Ingresar Cliente";
    } else {
        run = request.getParameter("run");
        dv = request.getParameter("dv");
        action = "ModificarCliente.do?run=" + run;
        btnValue = "Modificar Cliente";
        title = "Modificar Cliente";
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

   

        <%
            String mensaje;
            mensaje = (request.getAttribute("msg") == null) ? "" : (String) request.getAttribute("msg");
        %>
        <div id="container">
            <form id="formUsuario" name="form1" method="POST" action="<%=action%>">
                <center>
                    <table>
                        <tr>
                            <td colspan="2" ><center><div id="titulo">&nbsp;<%=title%> </div></center></td>	

                        </tr>	
                        <tr>
                            <td>RUN</td><td><input type="text" id="run" name="run" value="<%=run%>" required> </td>	
                        </tr>
                        <tr>
                            <td>Dv:</td><td><input type="text" id="dv" name="dv" value="<%=dv%>" required></td>	
                        </tr>
                        <tr>
                            <td>Nombre:</td><td><input type="text" id="nombre" name="nombre" required> </td>	
                        </tr>	
                        <tr>
                            <td>Apellido Paterno:</td><td><input type="text" id="paterno" name="paterno" required></td>	
                        </tr>
                        <tr>
                            <td>Apellido Materno:</td><td><input type="text" id="materno" name="materno"  required></td>	
                        </tr>
                        <tr>
                            <td>Fecha Nacimiento:</td><td><input type="date" id="fecha" name="fecha" min="1920-01-01" max="2010-01-01" required> </td>	
                        </tr>	
                        <tr>
                            <td>Sexo:</td><td><select name="sexo" id="sexo" required>
                                    <option >Masculino </option>
                                    <option >Femenino</option>
                                </select></td>	
                        </tr>
                        <tr>
                            <td>Dirección:</td><td><input type="text" id="direccion" name="direccion" required></td>	
                        </tr>
                        <tr>
                            <td>Teléfono:</td><td><input type="text" id="telefono" name="telefono" required></td>	
                        </tr>
                        <tr>
                            <td>Email:</td><td><input type="text" id="email" name="email" required></td>	
                        </tr>
                        <tr>
                            <td>Contraseña:</td><td><input type="text" id="password" name="password" required></td>	
                        </tr>
                        <center>	
                            <tr>
                                <td colspan="2"><center><label id="enviar"><input type="submit"  value="<%=btnValue%>"></label></center></td>
                            </tr>	
                        </center>
                    </table>
                    <br><a href="login.jsp">Iniciar Sesión</a>

                </center>
            </form>
       
        <p><%=mensaje%></p>
        </div>
    </body>
</html>
