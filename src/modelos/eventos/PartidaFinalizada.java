package modelos.eventos;

public class PartidaFinalizada extends EventoPartida {

    String vencedor;

    /**
     *
     * @param v
     */
    public PartidaFinalizada(String v) {
        vencedor = v;
    }

    public String retornaVencedor() {
        return vencedor;
    }
}