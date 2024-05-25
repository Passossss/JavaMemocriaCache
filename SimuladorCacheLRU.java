import java.util.*;

public class SimuladorCacheLRU {

    private static final int TAMANHO_CACHE = 4; // Tamanho da cache
    private static final int TAMANHO_CONJUNTO = 2; // Tamanho do conjunto associativo
    private static final int[] POSICOES_ACESSO = {1,7,0,5,10}; // Posições de memória a serem acessadas

    private static Map<Integer, LinkedList<Integer>> cache; // Mapa para armazenar conjuntos na cache
    private static Map<Integer, LinkedList<Integer>> lru; // Mapa para gerenciar LRU por conjunto

    private static int totalAcessos = 0;
    private static int hits = 0;
    private static int misses = 0;

    public static void main(String[] args) {
        cache = new HashMap<>();
        lru = new HashMap<>();

        // Inicializa os conjuntos da cache e LRU
        for (int i = 0; i < TAMANHO_CACHE / TAMANHO_CONJUNTO; i++) {
            cache.put(i, new LinkedList<>());
            lru.put(i, new LinkedList<>());
        }

        simularCache();

        // Exibe o resumo
        System.out.println("\nResumo:");
        System.out.println("Total de posições de memória acessadas: " + totalAcessos);
        System.out.println("Total de hits: " + hits);
        System.out.println("Total de misses: " + misses);
        double taxaHit = (double) hits / totalAcessos * 100;
        System.out.printf("Taxa de cache hit: %.2f%%%n", taxaHit);
    }

    private static void simularCache() {
        for (int posicaoMemoria : POSICOES_ACESSO) {
            acessarMemoria(posicaoMemoria);
            imprimirCache();
        }
    }

    private static void acessarMemoria(int posicaoMemoria) {
        totalAcessos++;
        int conjunto = (posicaoMemoria / TAMANHO_CONJUNTO) % (TAMANHO_CACHE / TAMANHO_CONJUNTO); // Calcula conjunto associativo
        LinkedList<Integer> conjuntoCache = cache.get(conjunto);
        LinkedList<Integer> conjuntoLRU = lru.get(conjunto);

        if (conjuntoCache.contains(posicaoMemoria)) { // Já está na cache?
            hits++;
            conjuntoLRU.removeFirstOccurrence(posicaoMemoria); // Remove da lista LRU
            conjuntoLRU.addLast(posicaoMemoria); // Adiciona no final
        } else {
            misses++;
            if (conjuntoCache.size() < TAMANHO_CONJUNTO) { // Há espaço no conjunto?
                conjuntoCache.add(posicaoMemoria);
                conjuntoLRU.addLast(posicaoMemoria);
            } else { // Conjunto cheio: remover LRU
                int lruPosicao = conjuntoLRU.removeFirst(); // Remove primeiro da LRU
                conjuntoCache.removeFirstOccurrence(lruPosicao); // Remove da cache
                conjuntoCache.add(posicaoMemoria);
                conjuntoLRU.addLast(posicaoMemoria);
            }
        }
    }

    private static void imprimirCache() {
        System.out.println("Cache:");

        for (int conjunto = 0; conjunto < TAMANHO_CACHE / TAMANHO_CONJUNTO; conjunto++) {
            System.out.print("| Conjunto " + conjunto + " |");

            LinkedList<Integer> conjuntoCache = cache.get(conjunto);
            LinkedList<Integer> conjuntoLRU = lru.get(conjunto);

            for (int i = 0; i < TAMANHO_CONJUNTO; i++) {
                if (i < conjuntoCache.size()) {
                    int posicao = conjuntoCache.get(i);
                    String indicadorLRU = conjuntoLRU.getLast().equals(posicao) ? "*" : "";
                    System.out.print(" " + posicao + indicadorLRU + " ");
                } else {
                    System.out.print(" - ");
                }
            }

            System.out.println("|");
        }

        System.out.println();
    }
}
