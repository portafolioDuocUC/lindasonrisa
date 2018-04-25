<%-- 
    Document   : listaCliente
    Created on : 22-04-2018, 1:07:17
    Author     : chasc
--%>

<%@page import="modelo.ClienteVO"%>
<%@page import="java.util.ArrayList"%>
<%@page import="modelo.ClienteADO"%>
<%@page import="javax.sql.DataSource"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%
    String usuarioConectado = (String) session.getAttribute("usuarioConectado");
      
    if(usuarioConectado != "Administrador"){
        response.sendRedirect("login.jsp");
    }
    
    DataSource ds = (DataSource) session.getAttribute("ds");
    ClienteADO clienteADO = new ClienteADO(ds.getConnection());
    ArrayList<ClienteVO> clientesVO = clienteADO.buscarTodos();
%>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" type="text/css" href="css/estilo.css">
        <title>Listado de clientes</title>
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
        <h1>Listado de clientes</h1>
        <table border="1">
            <thead>
                <tr>
                    <th>Run</th>
                    <th>Dv</th>
                    <th>Nombre</th>
                    <th>Apellido Paterno</th>
                    <th>Apellido Materno</th>
                    <th>Fecha Nacimiento</th>
                    <th>Direccion</th>
                    <th>Teléfono</th>
                    <th>Sexo</th>
                    <th>Email</th>
                    <th>Usuario</th>
                    <th>Contraseña</th>
                    <th>Acción</th>
                </tr>
            </thead>
            <tbody>
                <%for (ClienteVO clienteVO : clientesVO) {%>
                <tr>
                    <td><%=clienteVO.getRun()%></td>
                    <td><%=clienteVO.getDv()%></td>
                    <td><%=clienteVO.getNombre()%></td>
                    <td><%=clienteVO.getApellidoPaterno()%></td>
                    <td><%=clienteVO.getApellidoMaterno()%></td>
                    <td><%=clienteVO.getFechaNacimiento()%></td>
                    <td><%=clienteVO.getDireccion()%></td>
                    <td><%=clienteVO.getTelefono()%></td>
                    <td><%=clienteVO.getSexo()%></td>
                    <td><%=clienteVO.getEmail()%></td>
                    <td><%=clienteVO.getUsuario()%></td>
                    <td><%=clienteVO.getPassword()%></td>
                    <td>
                        <a href="clientes.jsp?run=<%=clienteVO.getRun()%>&dv=<%=clienteVO.getDv()%>"><img src="img/Modificar.png" alt="" height="15" width="15"/></a>
                        <a href="EliminarCliente.do?run=<%=clienteVO.getRun()%>"><img src="img/Eliminar2.png" alt="" height="15" width="15"/></a>
                    </td>
                </tr>
                <%}%>
            </tbody>
        </table>
        <br>
        <%if (request.getParameter("mensaje") != null) {
                String mensaje = request.getParameter("mensaje");%>
        <%=mensaje%>        
        <%}%>
        <br>
        <a href="clientes.jsp"><img src="img/agregar2.png" alt="" height="15" width="15"/> Ingresar Cliente</a><br><br>
        <a href="index.jsp">Volver al inicio</a>
         </div>
    </body>
</html>