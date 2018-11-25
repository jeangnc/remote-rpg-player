package modelos;

public class Posicao {

    private Personagem ocupante = null;
    private int coordenada_x;
    private int coordenada_y;

    Posicao(int x, int y) {
        coordenada_x = x;
        coordenada_y = y;
    }

    /**
     *
     * @return
     */
    public boolean retornaDisponivel() {
        return ocupante == null;
    }

    /**
     *
     * @return
     */
    public Personagem retornaOcupante() {
        return ocupante;
    }

    /**
     *
     * @param p
     */
    void definirOcupante(Personagem p) {
        ocupante = p;
    }

    void removerOcupante() {
        ocupante = null;
    }
}