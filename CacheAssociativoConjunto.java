import java.util.*;

class CacheAssociativoConjunto {
    private Map<Integer, Deque<Integer>> cache;
    private int tamanhoCache;
    private int conjuntoSize;

    public CacheAssociativoConjunto(int tamanhoCache, int conjuntoSize) {
        this.tamanhoCache = tamanhoCache;
        this.conjuntoSize = conjuntoSize;
        this.cache = inicializarCacheAssociativa(tamanhoCache, conjuntoSize);
    }

    private Map<Integer, Deque<Integer>> inicializarCacheAssociativa(int tamanhoCache, int conjuntoSize) {
        Map<Integer, Deque<Integer>> cache = new HashMap<>();
        for (int i = 0; i < tamanhoCache / conjuntoSize; i++) {
            cache.put(i, new ArrayDeque<>(conjuntoSize));
        }
        return cache;
    }

    private void imprimirCacheAssociativa() {


    }

    public void mapeamentoAssociativoConjunto(List<Integer> posicoesMemoriaAcessar) {
        int hits = 0;
        int misses = 0;

        System.out.println("Estado Inicial da Cache:");





        int totalAcessos = hits + misses;
        double taxaHit = hits / (double) totalAcessos;

        System.out.println("\nResumo:");
        System.out.println("Total de posições de memória acessadas: " + totalAcessos);
        System.out.println("Total de hits: " + hits);
        System.out.println("Total de misses: " + misses);
        System.out.printf("Taxa de cache hit: %.2f%n", taxaHit);
    }
}