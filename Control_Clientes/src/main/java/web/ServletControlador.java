package web;

import datos.ClienteDaoJDBC;
import dominio.Cliente;
import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/ServletControlador")
public class ServletControlador extends HttpServlet {
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
        String accion = request.getParameter("accion");
        
        if (accion != null) {
            switch (accion) {
                case "insertar":
                    insertarCliente(request, response);
                    break;
                case "editar":
                    editarCliente(request, response);
                    break;
                case "eliminar":
                    eliminarCliente(request, response);
                    break;
                default:
                    this.accionDefault(request, response);
            }
        } else {
            this.accionDefault(request, response);
        }
        
    }
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String accion = request.getParameter("accion");
        
        if (accion != null) {
            switch (accion) {
                case "insertar":
                    insertarCliente(request, response);
                    break;
                case "modificar":
                    modificarCliente(request, response);
                    break;
                default:
                    this.accionDefault(request, response);
            }
        } else {
            this.accionDefault(request, response);
        }
    }
    
    private double calcularSaldoTotal(List<Cliente> clientes) {
        return clientes.stream().mapToDouble(x -> x.getSaldo()).sum();
    }
    
    private void insertarCliente(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Recuperamos los valores del formulario agregar cliente
        String nombre = request.getParameter("nombre");
        String apellido = request.getParameter("apellido");
        String email = request.getParameter("email");
        String telefono = request.getParameter("telefono");
        String saldoString = request.getParameter("saldo");
        double saldo = (!saldoString.isEmpty() && !"".equals(saldoString)) ? Double.parseDouble(saldoString) : 0;

        // Creamos el objeto cliente 
        Cliente cliente = new Cliente(nombre, apellido, email, telefono, saldo);

        // Insertamos el objeto en la base de datos
        int registrosModificados = new ClienteDaoJDBC().insertar(cliente);
        System.out.println("Registros modificados: " + registrosModificados);

        //redirigimos hacia accion por default
        this.accionDefault(request, response);
        
    }
    
    private void editarCliente(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Recuperamos el id cliente
        int idCliente = Integer.parseInt(request.getParameter("idCliente"));
        
        Cliente cliente = new ClienteDaoJDBC().encontrar(new Cliente(idCliente));
        request.setAttribute("cliente", cliente);
        
        String jspEditar = "/WEB-INF/paginas/cliente/editarCliente.jsp";
        request.getRequestDispatcher(jspEditar).forward(request, response);
        
        
    }
    
    private void modificarCliente(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Recuperamos los valores del formulario editarCliente
        int idCliente = Integer.parseInt(request.getParameter("idCliente"));
        String nombre = request.getParameter("nombre");
        String apellido = request.getParameter("apellido");
        String email = request.getParameter("email");
        String telefono = request.getParameter("telefono");
        String saldoString = request.getParameter("saldo");
        double saldo = (!saldoString.isEmpty() && !"".equals(saldoString)) ? Double.parseDouble(saldoString) : 0;

        // Creamos el objeto cliente 
        Cliente cliente = new Cliente(idCliente, nombre, apellido, email, telefono, saldo);

        // Modificar el objeto en la base de datos
        int registrosModificados = new ClienteDaoJDBC().actualizar(cliente);
        System.out.println("Registros modificados: " + registrosModificados);

        //redirigimos hacia accion por default
        this.accionDefault(request, response);
        
    }
    
        private void eliminarCliente(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Recuperamos los valores del formulario editarCliente
        int idCliente = Integer.parseInt(request.getParameter("idCliente"));

        // Creamos el objeto cliente 
        Cliente cliente = new Cliente(idCliente);

        // eliminar el objeto en la base de datos
        int registrosModificados = new ClienteDaoJDBC().eliminar(cliente);
        System.out.println("Registros modificados: " + registrosModificados);

        //redirigimos hacia accion por default
        this.accionDefault(request, response);
        
    }
    
    private void accionDefault(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        List<Cliente> clientes = new ClienteDaoJDBC().listar();
        HttpSession session = request.getSession();
        session.setAttribute("clientes", clientes);
        session.setAttribute("totalClientes", clientes.size());
        session.setAttribute("saldoTotal", calcularSaldoTotal(clientes));
//        request.getRequestDispatcher("clientes.jsp").forward(request, response);
        response.sendRedirect("clientes.jsp");
    }
}
