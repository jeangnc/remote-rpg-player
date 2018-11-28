package modelos;

public class Iniciativa {

    private Personagem personagem;
    private int iniciativa;

    public Iniciativa(Personagem p, int i) {
        personagem = p;
        iniciativa = i;
    }

    public Personagem retornarPersonagem() {
        return personagem;
    }

    public int retornarIniciativa() {
        return iniciativa;
    }
}