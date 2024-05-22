package AssociativoConjunto;

import java.util.*;

public class CacheAssociativoConjunto {
    private List<Map<Integer, Integer>> conjuntoCache;
    private int conjuntoSize;
    private int cacheSize;

    public CacheAssociativoConjunto(int cacheSize, int conjuntoSize) {
        this.cacheSize = cacheSize;
        this.conjuntoSize = conjuntoSize;
        this.conjuntoCache = new ArrayList<>();

        for (int i = 0; i < cacheSize / conjuntoSize; i++) {
            conjuntoCache.add(new HashMap<>());
        }
    }

    public boolean verificarCache(int posicaoMemoria) {
        int conjuntoIndex = posicaoMemoria % (cacheSize / conjuntoSize);
        Map<Integer, Integer> conjunto = conjuntoCache.get(conjuntoIndex);
        return conjunto.containsKey(posicaoMemoria);
    }

    public void adicionarNaCache(int posicaoMemoria) {
        int conjuntoIndex = posicaoMemoria % (cacheSize / conjuntoSize);
        Map<Integer, Integer> conjunto = conjuntoCache.get(conjuntoIndex);

        if (conjunto.size() >= conjuntoSize) {
            int menorContador = Integer.MAX_VALUE;
            int menorContadorPosicao = -1;
            for (Map.Entry<Integer, Integer> entry : conjunto.entrySet()) {
                if (entry.getValue() < menorContador) {
                    menorContador = entry.getValue();
                    menorContadorPosicao = entry.getKey();
                }
            }
            conjunto.remove(menorContadorPosicao);
        }

        conjunto.put(posicaoMemoria, conjunto.size());
    }

    public void imprimirCache() {
        for (int i = 0; i < conjuntoCache.size(); i++) {
            Map<Integer, Integer> conjunto = conjuntoCache.get(i);
            System.out.print("Conjunto " + i + ": ");
            for (int endereco : conjunto.keySet()) {
                System.out.print(endereco + " ");
            }
            System.out.println();
        }
    }
}