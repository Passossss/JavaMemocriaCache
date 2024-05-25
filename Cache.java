import java.util.*;
class Cache {
    private Map<Integer, Integer> cache;

    public Cache(int tamanhoCache) {
        this.cache = new HashMap<>();
        for (int i = 0; i < tamanhoCache; i++) {
            cache.put(i, -1);
        }
    }
    public void adicionarNaCache(int posicaoCache, int posicaoMemoria) {
        cache.put(posicaoCache, posicaoMemoria);
    }
    public boolean verificarCache(int posicaoCache, int posicaoMemoria) {
        return cache.get(posicaoCache) == posicaoMemoria;
    }

    public void imprimirCache() {
        System.out.println("Tamanho da Cache: " + cache.size());
        System.out.println("Pos Cache | Posição Memória");
        for (Map.Entry<Integer, Integer> entry : cache.entrySet()) {
            System.out.printf("     %d    |          %d%n", entry.getKey(), entry.getValue());
        }
    }
    public int getTamanho() {
        return cache.size();
    }
}
