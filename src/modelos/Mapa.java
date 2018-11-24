package modelos;

public class Mapa {

    private Posicao[][] posicoes;
    private int largura;
    private int altura;

    /**
     *
     * @param l
     * @param a
     */
    Mapa(int l, int a) {
        largura = l;
        altura = a;

        construirPosicoes();
    }

    private void construirPosicoes() {
        posicoes = new Posicao[largura][altura];

        for(int i = 0; i < largura; i++) {
            for(int j = 0; j < altura; j++) {
                posicoes[i][j] = new Posicao(i, j);
            }
        }
    }

    public Posicao retornarPosicao(int x, int y) {
        return posicoes[x][y];
    }

    public int retornarLargura() {
        return largura;
    }

    public int retornarAltura() {
        return altura;
    }
}