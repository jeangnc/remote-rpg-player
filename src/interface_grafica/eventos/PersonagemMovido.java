package interface_grafica.eventos;

public class PersonagemMovido extends EventoInterface {

    String idPersonagem;
    int coordX;
    int coordY;

    /**
     *
     * @param i
     * @param x
     * @param y
     */
    public PersonagemMovido(String i, int x, int y) {
        idPersonagem = i;
        coordX = x;
        coordY = y;
    }

    public String retornarIdPersonagem() {
        return idPersonagem;
    }

    public int retornarCoordenadaX() {
        return coordX;
    }

    public int retornarCoordenadaY() {
        return coordY;
    }
}