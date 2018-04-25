<%-- 
    Document   : listaEmpleado
    Created on : 22-04-2018, 20:15:32
    Author     : chasc
--%>

<%@page import="modelo.EmpleadoVO"%>
<%@page import="java.util.ArrayList"%>
<%@page import="modelo.EmpleadoADO"%>
<%@page import="javax.sql.DataSource"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%
    String usuarioConectado = (String) session.getAttribute("usuarioConectado");
      
    if(usuarioConectado != "Administrador"){
        response.sendRedirect("login.jsp");
    }
    
    DataSource ds = (DataSource) session.getAttribute("ds");
    EmpleadoADO empleadoADO = new EmpleadoADO(ds.getConnection());
    ArrayList<EmpleadoVO> empleadosVO = empleadoADO.buscarTodos();
%>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" type="text/css" href="css/estilo.css">
        <title>Listado de Empleados</title>
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
        <h1>Listado de Empleados</h1>
        <table border="1">
            <thead>
                <tr>
                    <th>Run</th>
                    <th>Dv</th>
                    <th>Nombre</th>
                    <th>Apellido Paterno</th>
                    <th>Apellido Materno</th>
                    <th>Fecha Nacimiento</th>
                    <th>Fecha Contrato</th>
                    <th>Direccion</th>
                    <th>Teléfono</th>
                    <th>Sexo</th>
                    <th>Email</th>
                    <th>Acción</th>
                </tr>
            </thead>
            <tbody>
                <%for (EmpleadoVO empleadoVO : empleadosVO) {%>
                <tr>
                    <td><%=empleadoVO.getRun()%></td>
                    <td><%=empleadoVO.getDv()%></td>
                    <td><%=empleadoVO.getNombre()%></td>
                    <td><%=empleadoVO.getaPaterno()%></td>
                    <td><%=empleadoVO.getaMaterno()%></td>
                    <td><%=empleadoVO.getFechaNacimiento()%></td>
                    <td><%=empleadoVO.getFechaContrato()%></td>
                    <td><%=empleadoVO.getDireccion()%></td>
                    <td><%=empleadoVO.getTelefono()%></td>
                    <td><%=empleadoVO.getSexo()%></td>
                    <td><%=empleadoVO.getEmail()%></td>
                    <td>
                        <a href="empleados.jsp?run=<%=empleadoVO.getRun()%>"><img src="img/Modificar.png" alt="" height="15" width="15"/></a>
                        <a href="EliminarEmpleado.do?run=<%=empleadoVO.getRun()%>"><img src="img/Eliminar2.png" alt="" height="15" width="15"/></a>
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
        <a href="empleados.jsp"><img src="img/agregar2.png" alt="" height="15px" width="15px"/> Ingresar Empleado</a><br><br>
        <a href="index.jsp">Volver al inicio</a>
    </body>
</html>
