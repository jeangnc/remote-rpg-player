package interface_grafica.eventos;

public class PersonagemAdicionado extends EventoInterface {
    private String nome;
    private int hpMaximo;
    private boolean inimigo;

    public PersonagemAdicionado(String n, int h, boolean i) {
        nome = n;
        hpMaximo = h;
        inimigo = i;
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
}