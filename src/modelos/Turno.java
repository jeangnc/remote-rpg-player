package modelos;

import java.util.ArrayList;
import java.util.Comparator;

public class Turno {

    private ArrayList<Iniciativa> iniciativasExecutadas = new ArrayList<>();
    private ArrayList<Iniciativa> iniciativasPendentes;
    private boolean finalizado;

    /**
     *
     * @param iniciativas
     */
    public Turno(ArrayList<Iniciativa> iniciativas) {
        iniciativasPendentes = iniciativas;
        sequenciarPersonagens();
    }

    public void proximaJogada() {
        // TODO - implement Turno.proximaJogada
        throw new UnsupportedOperationException();
    }

    public boolean verificarFinalizado() {
        // TODO - implement Turno.verificarFinalizado
        throw new UnsupportedOperationException();
    }

    private void sequenciarPersonagens() {
        iniciativasPendentes.sort(Comparator.comparingInt(Iniciativa::retornarIniciativa));
    }
}