import java.util.*;

public class Main {
    public static void main(String[] args) {
        List<Integer> posicoesMemoriaAcessar = Arrays.asList(33, 3, 11, 5);
        int tamanhoCache = 5;
        CacheSimulation cacheSim = new CacheSimulation(tamanhoCache);
        cacheSim.mapeamentoDireto(posicoesMemoriaAcessar);
    }
}