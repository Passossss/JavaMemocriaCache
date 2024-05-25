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
        System.out.println("Tamanho da Cache: " + tamanhoCache);
        System.out.println("Pos Cache | Bloco de Memória (LRU)");
        for (Map.Entry<Integer, Deque<Integer>> entry : cache.entrySet()) {
            int posCache = entry.getKey() * conjuntoSize;
            System.out.printf("         %d| ", posCache);
            int index = 0;
            for (Integer bloco : entry.getValue()) {
                if (index == conjuntoSize - 1) {
                    System.out.printf("%d (LRU) ", bloco);
                } else {
                    System.out.printf("%d ", bloco);
                }
                index++;
            }
            System.out.println();
        }
    }

    public void mapeamentoAssociativoConjunto(List<Integer> posicoesMemoriaAcessar) {
        int hits = 0;
        int misses = 0;

        System.out.println("Estado Inicial da Cache:");
        imprimirCacheAssociativa();

        for (int posicaoMemoria : posicoesMemoriaAcessar) {
            int conjunto = posicaoMemoria % (tamanhoCache / conjuntoSize);
            Deque<Integer> blocos = cache.get(conjunto);

            if (blocos.contains(posicaoMemoria)) {
                hits++;
                System.out.println("Hit! Endereço " + posicaoMemoria + " já está na Cache.");
                blocos.remove(posicaoMemoria);
                blocos.addFirst(posicaoMemoria);
            } else {
                misses++;
                System.out.println("Miss! Endereço " + posicaoMemoria + " não está na Cache. Adicionando...");
                if (blocos.size() == conjuntoSize) {
                    blocos.removeLast();
                }
                blocos.addFirst(posicaoMemoria);
            }

            System.out.println("Cache Atualizada:");
            imprimirCacheAssociativa();
        }

        int totalAcessos = hits + misses;
        double taxaHit = hits / (double) totalAcessos;

        System.out.println("\nResumo:");
        System.out.println("Total de posições de memória acessadas: " + totalAcessos);
        System.out.println("Total de hits: " + hits);
        System.out.println("Total de misses: " + misses);
        System.out.printf("Taxa de cache hit: %.2f%n", taxaHit);
    }
}