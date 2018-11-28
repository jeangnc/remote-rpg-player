package modelos;

import java.util.UUID;

public class Personagem {

    private String id;
    private String nome;
    private int hpMaximo;
    private int hpAtual;
    private Posicao posicao;
    private Jogador dono;
    private boolean inimigo;
    private boolean morto;
    private boolean acordado;

    public Personagem(String n, int h, boolean ig) {
        this(UUID.randomUUID().toString(), n, h, ig);
    }

    /**
     *
     * @param i
     * @param n
     * @param h
     * @param ig
     */
    public Personagem(String i, String n, int h, boolean ig) {
        id = i;
        nome = n;
        hpMaximo = h;
        inimigo = ig;

        modificarHp(h);
    }

    /**
     *
     * @param j
     */
    void definirDono(Jogador j) {
        dono = j;
    }

    /**
     *
     * @param hp
     */
    void aumentarHp(int hp) {
        // TODO - implement Personagem.aumentarHp
        throw new UnsupportedOperationException();
    }

    /**
     *
     * @param hp
     */
    void reduzirHp(int hp) {
        // TODO - implement Personagem.reduzirHp
        throw new UnsupportedOperationException();
    }

    /**
     *
     * @param destino
     */
    void mover(Posicao destino) {
        if (!destino.retornarDisponivel()) {
            // TODO lançar excecao
        }

        destino.definirOcupante(this);

        if (posicao != null) {
            posicao.removerOcupante();
        }
    }

    /**
     *
     * @return
     */
    public String retornaId() {
        return id;
    }

    /**
     *
     * @return
     */
    public String retornaNome() {
        return nome;
    }

    /**
     *
     * @return
     */
    public boolean retornaInimigo() {
        return inimigo;
    }

    /**
     *
     * @param hp
     */
    private void modificarHp(int hp) {
        hpAtual = hp;

        // TODO desacordar
        // TODO acordar
        // TODO matar
    }

    private void desacordar() {
        acordado = false;
    }

    private void acordar() {
        acordado = true;
    }

    private void matar() {
        morto = true;
    }
}