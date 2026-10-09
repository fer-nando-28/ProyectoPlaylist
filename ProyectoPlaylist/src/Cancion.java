public class Cancion {
    private static int contadorGlobal = 1;
    
    private int id;
    private String titulo;
    private String artista;
    private int duracion; // en segundos

    public Cancion(String titulo, String artista, int duracion) {
        this.id = contadorGlobal++;
        this.titulo = titulo;
        this.artista = artista;
        this.duracion = duracion > 0 ? duracion : 1; 
    }

    public int getId() { return id; }
    public String getTitulo() { return titulo; }
    public String getArtista() { return artista; }
    public int getDuracion() { return duracion; }

    @Override
    public String toString() {
        return String.format("[ID: %d] %s - %s (%d s)", id, titulo, artista, duracion);
    }
}