package modelo; // Asegúrate de que coincida con el nombre de tu paquete

public class Paciente {
    
    // Atributos del paciente
    private int codigo;
    private String nombre;
    private String clinica;
    private String informacionMedica;
    private int sexo;
    
    // Este es el atributo clave para la lista enlazada (el "enlace" al siguiente nodo)
    private Paciente siguiente;

    // Constructor
    public Paciente(int codigo, String nombre, String clinica, String informacionMedica, int sexo) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.clinica = clinica;
        this.informacionMedica = informacionMedica;
        this.sexo = sexo;
        this.siguiente = null; // Todo paciente nuevo arranca sin tener a nadie después
    }

    // --- Getters y Setters ---

    // Getter y Setter para enlazar los nodos
    public Paciente getSiguiente() { return siguiente; }
    public void setSiguiente(Paciente siguiente) { this.siguiente = siguiente; }

    // Getters y Setters de los datos médicos y personales
    public int getCodigo() { return codigo; }
    public void setCodigo(int codigo) { this.codigo = codigo; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public String getClinica() { return clinica; }
    public void setClinica(String clinica) { this.clinica = clinica; }

    public String getInformacionMedica() { return informacionMedica; }
    public void setInformacionMedica(String informacionMedica) { this.informacionMedica = informacionMedica; }

    public int getSexo() { return sexo; }
    public void setSexo(int sexo) { this.sexo = sexo; }
}