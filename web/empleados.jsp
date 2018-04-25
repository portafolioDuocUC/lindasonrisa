<%-- 
    Document   : empleados
    Created on : 18-04-2018, 14:15:58
    Author     : chasc
--%>

<%
    String usuarioConectado = (String) session.getAttribute("usuarioConectado");
   
    if(usuarioConectado != "Administrador"){
        response.sendRedirect("login.jsp");
    }
    
    String action, run, btnValue, title = "";
    if(request.getParameter("run")==null){
        action = "IngresarEmpleado.do";
        run="";
        btnValue = "Ingresar Empleado";
        title = "Ingresar Empleado";
    }else{
        run = request.getParameter("run");
        action = "ModificarEmpleado.do?run="+run;        
        btnValue = "Modificar Empleado";
        title = "Modificar Empleado";
    }
%>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
                <link rel="stylesheet" type="text/css" href="css/estilo.css">

        <title><%=title%></title>
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
		<td>Dv:</td><td><input type="text" id="dv" name="dv" required> </td>	

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
		<td>Fecha Nacimiento:</td><td><input type="date" id="fecha" name="fecha" required> </td>	

	</tr>
        <tr>
		<td>Fecha Contrato:</td><td><input type="date" id="contrato" name="contrato" required> </td>	

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
            <a href="ListarEmpleado.do">Listar </a>&nbsp;<a href="clientes.jsp?">Registrar Cliente</a>&nbsp
            <a href="Proveedor.jsp?">Registrar Proveedor</a>
    </center>
  </form>
 
        <p><%=mensaje%></p>
        </div>
</body>
</html>
