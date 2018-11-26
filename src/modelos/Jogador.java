package modelos;

public class Jogador {
    private int id;
    private String nome;
    private Personagem[] personagens;

    public Jogador(int i, String n) {
        this(i, n, null);
    }

    public Jogador(int i, String n, Personagem[] p) {
        id = i;
        nome = n;
        personagens = p;
    }

    public String retornarNome() {
        return nome;
    }
}