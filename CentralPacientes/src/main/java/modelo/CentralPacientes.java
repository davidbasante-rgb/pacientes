package modelo;

public class CentralPacientes {
    
    // Referencias principales de la lista enlazada
    private Paciente primero;
    private Paciente ultimo;
    private int numPacientes;

    // Constructor: al crear la central, la lista empieza vacía
    public CentralPacientes() {
        this.primero = null;
        this.ultimo = null;
        this.numPacientes = 0;
    }

    // Método para agregar un paciente al final de la cola
    public void agregarPacienteAlFinal(Paciente nuevo) {
        if (primero == null) {
            // Si la lista está vacía, el nuevo es el primero y también el último
            primero = nuevo;
            ultimo = nuevo;
        } else {
            // Si ya hay pacientes, el que era el último ahora apunta al nuevo
            ultimo.setSiguiente(nuevo);
            // Y el nuevo pasa a ser el último de la lista
            ultimo = nuevo;
        }
        numPacientes++; // Aumentamos el contador
    }
    public Paciente localizar(int codigo) {
        // Usamos una referencia auxiliar llamada 'actual' que empieza en el 'primero'
        Paciente actual = primero;
        
        // Mientras no lleguemos al final de la lista (null)
        while (actual != null) {
            // Si el código del paciente actual coincide con el que buscamos
            if (actual.getCodigo() == codigo) {
                return actual; // Lo encontramos, devolvemos el objeto Paciente
            }
            // Si no es, avanzamos al siguiente nodo de la lista
            actual = actual.getSiguiente();
        }
        
        // Si el ciclo termina y nunca entró al 'if', significa que no existe
        return null;
    }
    public void agregarPacienteAlComienzo(Paciente nuevo) {
        // El nuevo paciente apunta al que actualmente es el primero
        nuevo.setSiguiente(primero);
        // Ahora el nuevo pasa a ser oficialmente el primero
        primero = nuevo;

        // Si la lista estaba vacía (ultimo es null), este nuevo también es el último
        if (ultimo == null) {
            ultimo = nuevo;
        }
        numPacientes++; // Aumentamos el contador
    }
    public void agregarPacienteDespuesDe(int codigo, Paciente nuevo) {
        // Primero, buscamos al paciente con el código dado usando el método que ya hicimos
        Paciente actual = localizar(codigo);
        
        // Si lo encontramos
        if (actual != null) {
            // El nuevo nodo debe apuntar al que le seguía al 'actual'
            nuevo.setSiguiente(actual.getSiguiente());
            // Y ahora el 'actual' debe apuntar al nuevo
            actual.setSiguiente(nuevo);
            
            // Si el paciente 'actual' era el último de la lista, el nuevo pasa a ser el último
            if (actual == ultimo) {
                ultimo = nuevo;
            }
            numPacientes++; // Aumentamos el contador
        }
    }
    public void eliminarPaciente(int codigo) {
        if (primero == null) return; // Si la lista está vacía, no hay nada que borrar

        // CASO 1: Si el paciente a eliminar es el primero de la lista
        if (primero.getCodigo() == codigo) {
            primero = primero.getSiguiente(); // El segundo pasa a ser el primero
            if (primero == null) {
                ultimo = null; // Si al borrar el primero la lista quedó vacía
            }
            numPacientes--;
            return;
        }

        // CASO 2: Si el paciente está en el medio o al final
        // Usamos una referencia para buscar al paciente ANTERIOR al que queremos borrar
        Paciente actual = primero;
        while (actual.getSiguiente() != null) {
            if (actual.getSiguiente().getCodigo() == codigo) {
                // Desconectamos el nodo, haciendo que el actual apunte al siguiente del siguiente
                actual.setSiguiente(actual.getSiguiente().getSiguiente());
                
                // Si el nodo que acabamos de borrar era el último, actualizamos la referencia 'ultimo'
                if (actual.getSiguiente() == null) {
                    ultimo = actual;
                }
                numPacientes--;
                return;
            }
            actual = actual.getSiguiente(); // Avanzamos en la lista
        }
    }
    

    // --- Getters ---
    public Paciente getPrimero() { 
        return primero; 
    }
    
    public int getNumPacientes() {
        return numPacientes;
    }
}
