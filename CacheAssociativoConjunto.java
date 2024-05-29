import java.util.*;

class CacheAssociativoConjunto {
    private Map<Integer, Deque<Integer>> cache;
    private int tamanhoCache;
    private int conjuntoSize;
    private int qntBlocos;
    private int blocoSize;

    public CacheAssociativoConjunto(int tamanhoCache, int conjuntoSize) {
        this.tamanhoCache = tamanhoCache;
        this.conjuntoSize = conjuntoSize;
        this.qntBlocos = tamanhoCache / conjuntoSize;
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
        cache.forEach((conjunto, blocos) -> {
            System.out.printf("%d |\n", conjunto);
            int indiceBloco = 0;
            for (Integer bloco : blocos) {
                System.out.printf("  | %d | %d\n", indiceBloco, bloco);
                indiceBloco++;
            }
            System.out.println("--------------");
        });
    }

    public void mapeamentoAssociativoConjunto(List<Integer> posicoesMemoriaAcessar, int blocoSize) {
        int hits = 0;
        int misses = 0;
        this.blocoSize = blocoSize;

        System.out.println("Estado Inicial da Cache:");
        imprimirCacheAssociativa();

        for (int posicao : posicoesMemoriaAcessar) {
            int conjunto = (posicao / blocoSize) % qntBlocos;
            Deque<Integer> blocos = cache.get(conjunto);

            if (blocos.contains(posicao)) {
                hits++;
                blocos.remove(posicao);
                blocos.addLast(posicao);
            } else {
                misses++;
                if (blocos.size() == conjuntoSize) {
                    blocos.removeFirst();
                }
                blocos.addLast(posicao);
            }

            cache.put(conjunto, blocos);
        }

        int totalAcessos = hits + misses;
        double taxaHit = hits / (double) totalAcessos;

        System.out.println("\nEstado Final da Cache:");
        imprimirCacheAssociativa();

        System.out.println("\nResumo:");
        System.out.println("Total de posições de memória acessadas: " + totalAcessos);
        System.out.println("Total de hits: " + hits);
        System.out.println("Total de misses: " + misses);
        System.out.printf("Taxa de cache hit: %.2f%%%n", taxaHit * 100);
    }
}
