import java.util.Random;

public class ComparacaoOrdenacao {

    public static int[] gerarListaAleatoria(int tamanho) {
        Random random = new Random();
        int[] lista = new int[tamanho];
        for (int i = 0; i < tamanho; i++) {
            lista[i] = random.nextInt(1_000_000); // números entre 0 e 999.999
        }
        return lista;
    }

    public static void bubbleSort(int[] arr) {
        int n = arr.length;
        boolean trocou;
        for (int i = 0; i < n - 1; i++) {
            trocou = false;
            for (int j = 0; j < n - i - 1; j++) {
                if (arr[j] > arr[j + 1]) {
                    int temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                    trocou = true;
                }
            }
            if (!trocou) break;
        }
    }

    public static void quickSort(int[] arr, int inicio, int fim) {
        if (inicio < fim) {
            int pivoIndex = particionar(arr, inicio, fim);
            quickSort(arr, inicio, pivoIndex - 1);
            quickSort(arr, pivoIndex + 1, fim);
        }
    }

    private static int particionar(int[] arr, int inicio, int fim) {
        int pivo = arr[fim];
        int i = inicio - 1;
        for (int j = inicio; j < fim; j++) {
            if (arr[j] < pivo) {
                i++;
                int temp = arr[i];
                arr[i] = arr[j];
                arr[j] = temp;
            }
        }
        int temp = arr[i + 1];
        arr[i + 1] = arr[fim];
        arr[fim] = temp;
        return i + 1;
    }

    public static int[] copiarArray(int[] original) {
        int[] copia = new int[original.length];
        System.arraycopy(original, 0, copia, 0, original.length);
        return copia;
    }

    public static void main(String[] args) {
        int tamanho = 100_000;
        System.out.println("Gerando lista aleatória com " + tamanho + " números...");
        int[] lista = gerarListaAleatoria(tamanho);

        int[] listaBubble = copiarArray(lista);
        int[] listaQuick = copiarArray(lista);

        System.out.println("\nIniciando BubbleSort...");
        long inicioBubble = System.nanoTime();
        bubbleSort(listaBubble);
        long fimBubble = System.nanoTime();
        double tempoBubble = (fimBubble - inicioBubble) / 1_000_000.0;

        System.out.println("Iniciando QuickSort...");
        long inicioQuick = System.nanoTime();
        quickSort(listaQuick, 0, listaQuick.length - 1);
        long fimQuick = System.nanoTime();
        double tempoQuick = (fimQuick - inicioQuick) / 1_000_000.0;

        System.out.println("\n===== RELATÓRIO DE DESEMPENHO =====");
        System.out.printf("Tempo BubbleSort: %.3f ms%n", tempoBubble);
        System.out.printf("Tempo QuickSort : %.3f ms%n", tempoQuick);

        if (tempoBubble < tempoQuick) {
            System.out.println("➡️  O BubbleSort foi mais rápido (improvável para grandes listas).");
        } else {
            System.out.println("➡️  O QuickSort foi mais rápido, pois é um algoritmo mais eficiente (O(n log n) vs O(n²)).");
        }
    }
}
