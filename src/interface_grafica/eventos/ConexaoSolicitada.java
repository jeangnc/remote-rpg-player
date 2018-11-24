package interface_grafica.eventos;

public class ConexaoSolicitada extends EventoInterface {
    private int id;
    private String nome;

    public ConexaoSolicitada(int i, String n) {
        id = i;
        nome = n;
    }

    public int getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }
}