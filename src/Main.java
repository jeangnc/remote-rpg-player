import modelos.eventos.SolicitarIniciativa;

public class Main {
    public static void main(String[] args) {

        int nJogadores = args.length == 0 ? 1 : 2;
        int idJogador = args.length > 0 ? Integer.parseInt(args[0]) : 1;



//        Rede r = new Rede(nJogadores);
//
//        try {
//            r.conectar("localhost", idJogador, "Jean " + idJogador);
//            r.escutarEventos(EventoPartida.class, (e) -> {
//                SolicitarIniciativa i  = (SolicitarIniciativa) e;
//
//                System.out.println("Iniciativa solicitada n: " + i.n);
//            });
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//
//
//        while (true) {
//            try {
//                System.out.println("Tentando enviar jogada");
//                r.transmitirEvento(new SolicitarIniciativa(2));
//                System.out.println("Consegui, aguardando");
//
//            } catch (Exception e) {
//                System.out.println("Não consegui, aguardando");
//            }
//
//            try {
//                Thread.sleep (2000);
//            } catch (InterruptedException ex) {
//                System.out.println("Thread interrompida");
//            }
//        }
    }
}
