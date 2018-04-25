<%-- 
    Document   : productos
    Created on : 21-abr-2018, 16:25:15
    Author     : Diana
--%>

<%@page import="modelo.ProductoVO"%>
<%@page import="modelo.TipoProductoVO"%>
<%@page import="modelo.FamiliaProductoVO"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" type="text/css" href="css/estilo.css">
        <script src="js/script.js"></script>
        <title>Productos</title>
    </head>
    <body>
        <a id="cerrarSesion" href="login.jsp">Cerrar Sesión</a>
        <%
        String usuarioConectado = (String) session.getAttribute("usuarioConectado");
      
        if(usuarioConectado != "Administrador"){
            response.sendRedirect("login.jsp");
        }
            List<FamiliaProductoVO> listaFamilia = (List<FamiliaProductoVO>) session.getAttribute("listaFamilia");
            List<TipoProductoVO> listaTipoProd = (List<TipoProductoVO>) session.getAttribute("listaTipoProd");
            List<ProductoVO> listaProductos = (List<ProductoVO>) session.getAttribute("listaProductos");

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

            <h1>Productos</h1>

            <nav id="secciones">
                <button onclick="mostrar('Producto');">Productos
                    <button onclick="mostrar('Familia');">Familia de Productos
                        <button onclick="mostrar('Tipo');">Tipo de Productos    
                            </nav>

                            <table id="tablaProducto" hidden="true">
                                <tr>
                                    <th>Código</th>
                                    <th>Familia</th>
                                    <th>Tipo</th>
                                    <th>Stock</th>
                                    <th>Stock Crítico</th>
                                    <th>Descripción</th>
                                    <th colspan="2">Acción</th>
                                </tr>

                                <% for (ProductoVO p : listaProductos) {%>
                                <tr>
                                    <td><%=p.getId()%></td>
                                    <td><%=p.getIdFamilia()%></td>
                                    <td><%=p.getIdTipo()%></td>
                                    <td><%=p.getStock()%></td>
                                    <td><%=p.getStockCritico()%></td>
                                    <td><%=p.getDescripcion()%></td>
                                    <td><a href="#" onclick="eliminaProducto(<%=p.getId()%>)">Eliminar</a></td>
                                    <td><a href="#" onclick="cargaDatosProducto('<%=p.getId()%>','<%=p.getStock()%>','<%=p.getStockCritico()%>','<%=p.getDescripcion()%>')">Modificar</a></td>

                                </tr>
                                <% } %>
                                   

                            </table>

                            <table id="tablaFamilia" hidden="true">
                                <tr>
                                    <th>Código</th>
                                    <th>Nombre</th>
                                    <th colspan="2">Acción</th>
                                </tr> 
                                <%
                                    for (FamiliaProductoVO f : listaFamilia) {
                                        out.print("<tr>");
                                        out.print("<td>" + f.getId() + "</td>");
                                        out.print("<td>" + f.getNombre() + "</td>");
                                        out.print("<td><a href=# onclick=eliminaFamilia(" + f.getId() + ")>Eliminar</a></td>");
                                        out.print("<td><a href=# onclick=cargaDatosFamilia('" + f.getNombre() + "','" + f.getId() + "')>Modificar</a></td>");
                                        out.print("</tr>");
                                    }
                                %>
                            </table>

                            <table id="tablaTipo" hidden="true">
                                <tr>
                                    <th>Código</th>
                                    <th>Nombre</th>
                                    <th colspan="2">Acción</th>
                                </tr> 
                                <%
                                    for (TipoProductoVO t : listaTipoProd) {
                                        out.print("<tr>");
                                        out.print("<td>" + t.getId() + "</td>");
                                        out.print("<td>" + t.getNombre() + "</td>");
                                        out.print("<td><a href=# onclick=eliminaTipo(" + t.getId() + ")>Eliminar</a></td>");
                                        out.print("<td><a href=# onclick=cargaDatosTipoProd('" + t.getNombre() + "','" + t.getId() + "')>Modificar</a></td>");
                                        out.print("</tr>");
                                    }
                                %>
                            </table>

                            <form id="formProducto" method="post" action="AgregarProducto.do?opcion=producto" hidden="true">
                                <table id="tablaFormProducto">
                                    <tr>
                                        <th><label>Código</label></th>
                                        <th><label>Familia</label></th>
                                        <th><label>Tipo</label></th>
                                        <th><label>Stock</label></th>
                                        <th><label>Stock Crítico</label></th>
                                        <th><label>Descripción</label></th>
                                    </tr>
                                    <tr>
                                        <td id="codigoProducto"></td>
                                        <td>
                                            <select id="selectFamilia " name="selectFamilia" >
                                                <option value="">Seleccione familia...</option>
                                                <%
                                                    for (FamiliaProductoVO f : listaFamilia) {
                                                        out.print("<option value=" + f.getId() + ">" + f.getNombre() + "</option>");
                                                    }
                                                %>
                                            </select>
                                        </td>
                                        <td>
                                            <select id="selectTipo" name="selectTipo" >
                                                <option value="">Ingrese tipo...</option>
                                                <%
                                                    for (TipoProductoVO t : listaTipoProd) {
                                                        out.print("<option value=" + t.getId() + ">" + t.getNombre() + "</option>");
                                                    }
                                                %>
                                            </select>
                                        </td>
                                        <td><input type="number" id="stock" name="stock" placeholder="Ingrese stock..." /></td>
                                        <td><input type="number" id="stockCritico" name="stockCritico" placeholder="Ingrese stock crítico..." /></td>
                                        <td><input type="text" id="descripcionProducto" name="descripcionProducto" maxlength="30" placeholder="Ingrese descripción del producto..." /></td>

                                    </tr>
                                    <tr>
                                        <td colspan="5"><input id="btnProducto" type="submit" value="Ingresar"></td>
                                    </tr>
                                </table>                        
                            </form>

                            <form id="formFamilia" method="post" action="AgregarProducto.do?opcion=familia" hidden="true">
                                <label>Nombre</label>
                                <input type="text" id="nombreFamilia" maxlength="30" name="nombre" placeholder="Ingrese familia del producto..." />
                                <input id="btnFamilia" type="submit" value="Ingresar">

                            </form>

                            <form id="formTipo" method="post" action="AgregarProducto.do?opcion=tipo" hidden="true">
                                <label>Nombre</label>
                                <input type="text" id="nombreTipo" maxlength="30" name="nombre" placeholder="Ingrese tipo del producto..." />
                                <input id="btnTipo" type="submit" value="Ingresar">

                            </form>
                            <p><%=mensaje%></p>
                            </div>

                            <footer>
                                <p>© 2018 Linda Sonrisa. Todos los derechos reservados.</p>
                            </footer>
                            </body>
                            </html>
