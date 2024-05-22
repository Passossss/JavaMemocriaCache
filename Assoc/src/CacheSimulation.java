package AssociativoConjunto;

import java.util.*;

public class CacheSimulation {
    private CacheAssociativoConjunto cache;

    public CacheSimulation(int cacheSize, int conjuntoSize) {
        this.cache = new CacheAssociativoConjunto(cacheSize, conjuntoSize);
    }

    public void mapeamentoAssociativoConjunto(List<Integer> posicoesMemoriaAcessar) {
        int hits = 0;
        int misses = 0;

        System.out.println("Estado Inicial da Cache:");
        cache.imprimirCache();

        for (int posicaoMemoria : posicoesMemoriaAcessar) {
            if (cache.verificarCache(posicaoMemoria)) {
                hits++;
                System.out.println("Status: Hit! Endereço " + posicaoMemoria + " já está na Cache.");
            } else {
                misses++;
                System.out.println("Status: Miss! Endereço " + posicaoMemoria + " não está na Cache. (Adicionando)");
                cache.adicionarNaCache(posicaoMemoria);
            }

            System.out.println("Cache Atualizada:");
            cache.imprimirCache();
        }

        int totalAcessos = hits + misses;
        double taxaAcerto = totalAcessos > 0 ? (double) hits / totalAcessos : 0;
        double taxaAcertoPercentual = taxaAcerto * 100;

        System.out.println("\nResumo:");
        System.out.println("Total de posições de memória acessadas: " + totalAcessos);
        System.out.println("Total de hits: " + hits);
        System.out.println("Total de misses: " + misses);
        System.out.printf("Taxa de acerto: %.2f%%\n", taxaAcertoPercentual);
    }

    public static void main(String[] args) {
        CacheSimulation cacheSim = new CacheSimulation(16, 4);
        List<Integer> posicoesMemoria = Arrays.asList(10, 20, 15, 30, 10);

        cacheSim.mapeamentoAssociativoConjunto(posicoesMemoria);
    }
}