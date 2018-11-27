package interface_grafica.eventos;

import modelos.Posicao;

public class PersonagemAdicionado extends EventoInterface {
    private String nome;
    private int hpMaximo;
    private boolean inimigo;
    private int coordX;
    private int coordY;

    public PersonagemAdicionado(String n, int h, boolean i) {
        nome = n;
        hpMaximo = h;
        inimigo = i;
    }

    /**
     *
     * @param pos
     */
    public void definirPosicao(Posicao pos) {
        coordX = pos.retornarCoordenadaX();
        coordY = pos.retornarCoordenadaY();
    }

    public String retornaNome() {
        return nome;
    }

    public int retornaHpMaximo() {
        return hpMaximo;
    }

    public boolean retornaInimigo() {
        return inimigo;
    }

    public int retornarCoordenadaX() {
        return coordX;
    }

    public int retornarCoordenadaY() {
        return coordY;
    }
}