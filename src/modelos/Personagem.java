package modelos;

public class Personagem {

    private String nome;
    private int hpMaximo;
    private int hpAtual;
    private boolean npc;
    private Posicao posicao;
    private Jogador dono;
    private boolean inimigo;
    private boolean morto;
    private boolean acordado;

    /**
     *
     * @param nome
     * @param hpMaximo
     * @param inimigo
     */
    public Personagem(String nome, int hpMaximo, boolean inimigo) {
        // TODO - implement Personagem.Personagem
        throw new UnsupportedOperationException();
    }

    /**
     *
     * @param hp
     */
    public void aumentarHp(int hp) {
        // TODO - implement Personagem.aumentarHp
        throw new UnsupportedOperationException();
    }

    /**
     *
     * @param hp
     */
    public void reduzirHp(int hp) {
        // TODO - implement Personagem.reduzirHp
        throw new UnsupportedOperationException();
    }

    /**
     *
     * @param destino
     */
    public void mover(Posicao destino) {
        // TODO - implement Personagem.mover
        throw new UnsupportedOperationException();
    }

    /**
     *
     * @param dono
     */
    public boolean confirmarDono(Jogador dono) {
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