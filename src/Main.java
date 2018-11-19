public class Main {
    public static void main(String[] args) {
        int nJogadores = 2;
        int idJogador = Integer.parseInt(args[0]);

        NetGamesProxy proxy = new NetGamesProxy(nJogadores, idJogador, "Jogador " + idJogador);

        Rede r = new Rede(proxy);
        r.escutarObjetos(Tabuleiro.class, System.out::println);

        while (true) {
            try {
                System.out.println("Tentando enviar jogada");
                r.enviarObjeto(new Tabuleiro());
                System.out.println("Consegui, aguardando");

            } catch (Exception e) {
                System.out.println("Não consegui, aguardando");
            }

            try {
                Thread.sleep (2000);
            } catch (InterruptedException ex) {
                System.out.println("Thread interrompida");
            }
        }
    }
}
