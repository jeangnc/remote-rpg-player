package modelos.eventos;

public class NotificarErro extends EventoPartida {

    String msg;

    /**
     *
     * @param m
     */
    public NotificarErro(String m) {
        msg = m;
    }

    public String getMsg() {
        return msg;
    }
}