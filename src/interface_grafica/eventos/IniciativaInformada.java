package interface_grafica.eventos;


public class IniciativaInformada extends EventoInterface {

    private String idPersonagem;
    private int iniciativa;

    /**
     *
     * @param i
     * @param iv
     */
    public IniciativaInformada(String i, int iv) {
        idPersonagem = i;
        iniciativa = iv;
    }

    public String retornarIdPersonagem() {
        return idPersonagem;
    }

    public int retornarIniciativa() {
        return iniciativa;
    }
}