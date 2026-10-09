public class ListaDobleCircular {
    private Nodo inicio;
    private Nodo fin;
    private Nodo actual;
    private int tamano;

    public ListaDobleCircular() {
        this.inicio = null;
        this.fin = null;
        this.actual = null;
        this.tamano = 0;
    }

    public void agregarInicio(Cancion cancion) {
        Nodo nuevo = new Nodo(cancion);
        if (estaVacia()) {
            inicializarListaUnNodo(nuevo);
        } else {
            nuevo.siguiente = inicio;
            nuevo.anterior = fin;
            inicio.anterior = nuevo;
            fin.siguiente = nuevo;
            inicio = nuevo;
        }
        tamano++;
    }

    public void agregarFinal(Cancion cancion) {
        Nodo nuevo = new Nodo(cancion);
        if (estaVacia()) {
            inicializarListaUnNodo(nuevo);
        } else {
            nuevo.anterior = fin;
            nuevo.siguiente = inicio;
            fin.siguiente = nuevo;
            inicio.anterior = nuevo;
            fin = nuevo;
        }
        tamano++;
    }

    private void inicializarListaUnNodo(Nodo nuevo) {
        inicio = nuevo;
        fin = nuevo;
        actual = nuevo;
        nuevo.siguiente = nuevo;
        nuevo.anterior = nuevo;
    }

    public void mostrarActual() {
        if (estaVacia()) {
            System.out.println("La lista está vacía.");
        } else {
            System.out.println("🎵 Reproduciendo actual: " + actual.cancion);
        }
    }

    public void avanzar() {
        if (!estaVacia()) {
            actual = actual.siguiente;
            mostrarActual();
        } else {
            System.out.println("Lista vacía. No se puede avanzar.");
        }
    }

    public void retroceder() {
        if (!estaVacia()) {
            actual = actual.anterior;
            mostrarActual();
        } else {
            System.out.println("Lista vacía. No se puede retroceder.");
        }
    }

    public void seleccionarPorId(int id) {
        Nodo encontrado = buscarNodo(id);
        if (encontrado != null) {
            actual = encontrado;
            System.out.println("Canción seleccionada con éxito.");
            mostrarActual();
        } else {
            System.out.println("Error: Identificador no encontrado.");
        }
    }

    public void buscarPorId(int id) {
        Nodo encontrado = buscarNodo(id);
        if (encontrado != null) {
            System.out.println("Canción encontrada: " + encontrado.cancion);
        } else {
            System.out.println("Error: Identificador no encontrado.");
        }
    }

    public void eliminarActual() {
        if (estaVacia()) {
            System.out.println("Error: La lista está vacía.");
            return;
        }
        System.out.println("Eliminando: " + actual.cancion.getTitulo());
        eliminarNodo(actual);
    }

    public void eliminarPorId(int id) {
        Nodo aEliminar = buscarNodo(id);
        if (aEliminar != null) {
            System.out.println("Eliminando: " + aEliminar.cancion.getTitulo());
            eliminarNodo(aEliminar);
        } else {
            System.out.println("Error: Identificador inexistente. No se modificó la lista.");
        }
    }

    private void eliminarNodo(Nodo nodo) {
        if (tamano == 1) {
            inicio = null;
            fin = null;
            actual = null;
        } else {
            nodo.anterior.siguiente = nodo.siguiente;
            nodo.siguiente.anterior = nodo.anterior;

            if (nodo == inicio) inicio = nodo.siguiente;
            if (nodo == fin) fin = nodo.anterior;
            
            // Regla: si se elimina el actual, el nuevo actual es el que le seguía
            if (nodo == actual) actual = nodo.siguiente;
        }
        tamano--;
        // En Java, el nodo aislado será limpiado por el Garbage Collector
    }

    private Nodo buscarNodo(int id) {
        if (estaVacia()) return null;
        Nodo temp = inicio;
        do {
            if (temp.cancion.getId() == id) return temp;
            temp = temp.siguiente;
        } while (temp != inicio); // Condición de término para listas circulares
        return null;
    }

    public void mostrarAdelante() {
        if (estaVacia()) {
            System.out.println("Lista vacía.");
            return;
        }
        Nodo temp = inicio;
        System.out.println("--- Lista de Reproducción (Adelante) ---");
        do {
            System.out.println((temp == actual ? ">> " : "   ") + temp.cancion);
            temp = temp.siguiente;
        } while (temp != inicio);
    }

    public void mostrarAtras() {
        if (estaVacia()) {
            System.out.println("Lista vacía.");
            return;
        }
        Nodo temp = fin;
        System.out.println("--- Lista de Reproducción (Atrás) ---");
        do {
            System.out.println((temp == actual ? ">> " : "   ") + temp.cancion);
            temp = temp.anterior;
        } while (temp != fin);
    }

    public void simularReproduccion(int k) {
        if (estaVacia()) {
            System.out.println("Lista vacía. Nada que reproducir.");
            return;
        }
        System.out.println("▶️ Iniciando simulación de " + k + " canciones...");
        for (int i = 0; i < k; i++) {
            System.out.println("Reproduciendo: " + actual.cancion);
            actual = actual.siguiente;
        }
        System.out.println("Simulación terminada. Próxima en la fila:");
        mostrarActual();
    }

    public int getTamano() { return tamano; }
    public boolean estaVacia() { return tamano == 0; }
}