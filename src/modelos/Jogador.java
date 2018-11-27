package modelos;

public class Jogador {
    private String id;
    private String nome;
    private Personagem[] personagens;

    public Jogador(String i, String n) {
        id = i;
        nome = n;
    }

    public String retornaId() {
        return id;
    }

    public String retornarNome() {
        return nome;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Jogador) {
            return ((Jogador) obj).retornaId().equals(id);
        }

        return super.equals(obj);
    }
}