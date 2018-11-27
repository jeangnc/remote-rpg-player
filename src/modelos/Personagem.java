package modelos;

import java.util.UUID;

public class Personagem {

    private String id;
    private String nome;
    private int hpMaximo;
    private int hpAtual;
    private boolean npc;
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

    public String retornaId() {
        return id;
    }

    public String retornaNome() {
        return nome;
    }

    public boolean retornaInimigo() {
        return inimigo;
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
        // TODO - implement Personagem.mover
        throw new UnsupportedOperationException();
    }

    /**
     *
     * @param dono
     */
    boolean confirmarDono(Jogador dono) {
        // TODO - implement Personagem.confirmarDono
        throw new UnsupportedOperationException();
    }

    /**
     *
     * @param hp
     */
    private void modificarHp(int hp) {
        // TODO - implement Personagem.modificarHp
        throw new UnsupportedOperationException();
    }

    private void desacordar() {
        // TODO - implement Personagem.desacordar
        throw new UnsupportedOperationException();
    }

    private void acordar() {
        // TODO - implement Personagem.acordar
        throw new UnsupportedOperationException();
    }

    private void matar() {
        // TODO - implement Personagem.matar
        throw new UnsupportedOperationException();
    }

}