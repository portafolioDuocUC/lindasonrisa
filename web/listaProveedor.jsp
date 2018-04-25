<%-- 
    Document   : listaProveedor
    Created on : 22-04-2018, 20:05:34
    Author     : chasc
--%>

<%@page import="modelo.ProveedorVO"%>
<%@page import="java.util.ArrayList"%>
<%@page import="modelo.ProveedorADO"%>
<%@page import="javax.sql.DataSource"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%
    String usuarioConectado = (String) session.getAttribute("usuarioConectado");
      
    if(usuarioConectado != "Administrador"){
        response.sendRedirect("login.jsp");
    }
    
    DataSource ds = (DataSource) session.getAttribute("ds");
    ProveedorADO proveedorADO = new ProveedorADO(ds.getConnection());
    ArrayList<ProveedorVO> proveedoresVO = proveedorADO.buscarTodos();
%>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" type="text/css" href="css/estilo.css">
        <title>Listado de Proveedores</title>
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
        <h1>Listado de Proveedores</h1>
        <table border="1">
            <thead>
                <tr>
                    <th>Rut</th>
                    <th>Dv</th>
                    <th>Razón Social</th>
                    <th>Direccion</th>
                    <th>Teléfono</th>
                    <th>Email</th>
                    <th>Rubro</th>
                    <th>Usuario</th>
                    <th>Contraseña</th>
                    <th>Acción</th>
                </tr>
            </thead>
            <tbody>
                <%for (ProveedorVO proveedorVO : proveedoresVO) {%>
                <tr>
                    <td><%=proveedorVO.getRut()%></td>
                    <td><%=proveedorVO.getDv()%></td>
                    <td><%=proveedorVO.getRazonSocial()%></td>
                    <td><%=proveedorVO.getDireccion()%></td>
                    <td><%=proveedorVO.getTelefono()%></td>
                    <td><%=proveedorVO.getEmail()%></td>
                    <td><%=proveedorVO.getRubro()%></td>
                    <td><%=proveedorVO.getUsuario()%></td>
                    <td><%=proveedorVO.getPassword()%></td>
                    <td>
                        <a href="proveedores.jsp?rut=<%=proveedorVO.getRut()%>&dv=<%=proveedorVO.getDv()%>"><img src="img/Modificar.png" alt="" height="15" width="15"/></a>
                        <a href="EliminarProveedor.do?rut=<%=proveedorVO.getRut()%>"><img src="img/Eliminar2.png" alt="" height="15" width="15"/></a>
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
        <a href="proveedores.jsp"><img src="img/agregar2.png" alt="" height="15" width="15"/> Ingresar Proveedor</a><br><br>
        <a href="index.jsp">Volver al inicio</a>
         </div>
    </body>
</html>
