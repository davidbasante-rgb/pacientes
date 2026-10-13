package servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import modelo.CentralPacientes;
import modelo.Paciente;
import persistencia.ArchivoPacientes;

@WebServlet("/pacientes")
public class PacienteServlet extends HttpServlet {
    
    private CentralPacientes central;
    private ArchivoPacientes archivo;

    @Override
    public void init() throws ServletException {
        // Este método se ejecuta una sola vez cuando arranca el Servlet
        archivo = new ArchivoPacientes();
        try {
            // Intentamos cargar la lista desde el archivo txt
            central = archivo.cargar();
        } catch (IOException e) {
            // Si hay error o no existe, arrancamos con una lista vacía
            central = new CentralPacientes();
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Enviamos el primer nodo de la lista a la vista (JSP) para que pueda recorrerla
        request.setAttribute("primero", central.getPrimero());
        request.getRequestDispatcher("/pacientes.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        // 1. Recuperar parámetros enviados desde el formulario web
        int codigo = Integer.parseInt(request.getParameter("codigo"));
        String nombre = request.getParameter("nombre");
        String clinica = request.getParameter("clinica");
        String informacion = request.getParameter("informacionMedica");
        int sexo = Integer.parseInt(request.getParameter("sexo"));
        
        // 2. Validar código único: buscamos si ya existe alguien con ese código
        if (central.localizar(codigo) == null) {
            
            // 3. Si no existe, creamos el nodo Paciente y lo agregamos a la lista
            Paciente nuevo = new Paciente(codigo, nombre, clinica, informacion, sexo);
            central.agregarPacienteAlFinal(nuevo);
            
            // 4. Inmediatamente sincronizamos la lista en memoria con el archivo de texto
            archivo.guardar(central);
        }
        
        // 5. Redireccionamos de nuevo a la URL del Servlet para recargar la tabla (GET)
        response.sendRedirect("pacientes");
    }
}