import java.util.*;
class CacheSimulation {
    private Cache cache;

    public CacheSimulation(int tamanhoCache) {
        this.cache = new Cache(tamanhoCache);
    }
    public void mapeamentoDireto(List<Integer> posicoesMemoriaAcessar) {
        int hits = 0;
        int misses = 0;

        System.out.println("Estado Inicial da Cache:");
        cache.imprimirCache();

        int tamanhoCache = cache.getTamanho();

        for (int posicaoMemoria : posicoesMemoriaAcessar) {
            int posicaoCache = posicaoMemoria % tamanhoCache;
            if (cache.verificarCache(posicaoCache, posicaoMemoria)) {
                hits++;
                System.out.println("Hit! Endereço " + posicaoMemoria + " já está na Cache.");
            } else {
                misses++;
                System.out.println("Miss! Endereço " + posicaoMemoria + " não está na Cache. (Adicionando)");
                cache.adicionarNaCache(posicaoCache, posicaoMemoria);
            }

            System.out.println("Cache Atualizada:");
            cache.imprimirCache();
        }

        int totalAcessos = misses;
        double taxaAcerto = totalAcessos > 0 ? (double) hits / totalAcessos : 0;
        double taxaAcertoPercentual = taxaAcerto * 100;

        System.out.println("\nResumo:");
        System.out.println("Total de posições de memória acessadas: " + totalAcessos);
        System.out.println("Total de hits: " + hits);
        System.out.println("Total de misses: " + misses);
        System.out.printf("Taxa de acerto: %.2f%%\n", taxaAcertoPercentual);
    }
}
