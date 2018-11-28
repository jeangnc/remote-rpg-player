package modelos;

import java.util.ArrayList;
import java.util.UUID;

public class Jogador {
    private String id;
    private String nome;
    private ArrayList<Personagem> personagens = new ArrayList<>();

    public Jogador(String n) {
        this(UUID.randomUUID().toString(), n);
    }

    public Jogador(String i, String n) {
        id = i;
        nome = n;
    }

    void adicionarPersonagem(Personagem p) {
        personagens.add(p);
    }

    public String retornaId() {
        return id;
    }

    public String retornarNome() {
        return nome;
    }

    public ArrayList<Personagem> retornarPersonagens() {
        return personagens;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Jogador) {
            return ((Jogador) obj).retornaId().equals(id);
        }

        return super.equals(obj);
    }
}