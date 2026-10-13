package persistencia;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import modelo.CentralPacientes;
import modelo.Paciente;

public class ArchivoPacientes {
    
    // Ruta donde se guardará el archivo de texto (carpeta data)
    private final Path ruta = Paths.get("C:\\proyecto\\CentralPacientes\\data", "pacientes.txt");

    // Método para guardar toda la lista enlazada en el archivo
    public void guardar(CentralPacientes central) throws IOException {
        // Asegurarnos de que la carpeta 'data' exista antes de escribir
        Files.createDirectories(ruta.getParent());
        // Usamos try-with-resources para que el archivo se cierre automáticamente
        try (BufferedWriter bw = Files.newBufferedWriter(ruta, StandardCharsets.UTF_8)) {
            
            // Empezamos a recorrer desde el primer paciente
            Paciente actual = central.getPrimero();
            
            // Mientras haya pacientes en la lista
            while (actual != null) {
                // Escribimos los datos del paciente separados por el carácter '|'
                bw.write(actual.getCodigo() + "|" + actual.getNombre() + "|" + 
                         actual.getClinica() + "|" + 
                         actual.getInformacionMedica() + "|" + 
                         actual.getSexo());
                bw.newLine(); // Salto de línea para el próximo paciente
                
                // Avanzamos al siguiente nodo
                actual = actual.getSiguiente();
            }
        }
    }
    public CentralPacientes cargar() throws IOException {
        CentralPacientes central = new CentralPacientes();
        
        if (!Files.exists(ruta)) {
            return central;
        }
        
        for (String linea : Files.readAllLines(ruta, StandardCharsets.UTF_8)) {
            // NUEVO: Si la línea está vacía, saltarla y continuar con la siguiente
            if (linea.trim().isEmpty()) {
                continue;
            }
            
            String[] d = linea.split("\\|", -1);
            Paciente p = new Paciente(
                Integer.parseInt(d[0]), // código
                d[1],                   // nombre
                d[2],                   // clínica
                d[3],                   // información médica
                Integer.parseInt(d[4])  // sexo
            );
            
            central.agregarPacienteAlFinal(p);
        }
        return central;
    }
}
