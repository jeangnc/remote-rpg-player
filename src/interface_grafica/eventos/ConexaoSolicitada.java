package interface_grafica.eventos;

public class ConexaoSolicitada extends EventoInterface {
    private String nome;

    public ConexaoSolicitada(String n) {
        nome = n;
    }

    public String retornaNome() {
        return nome;
    }
}