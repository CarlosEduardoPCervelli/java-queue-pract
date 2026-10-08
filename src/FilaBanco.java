import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

/**
 * Fila de atendimento de banco com senhas normais (N001, N002...) e
 * prioritárias (P001, P002...).
 *
 * Regra de chamada: a cada duas senhas normais chamadas, a próxima
 * é uma prioritária (se houver alguma esperando).
 */
public class FilaBanco {

    private static final Queue<String> filaNormal = new LinkedList<>();
    private static final Queue<String> filaPrioritaria = new LinkedList<>();

    private static int contadorNormal = 0;
    private static int contadorPrioritaria = 0;

    // quantas normais foram chamadas desde a última prioritária
    private static int normaisSeguidas = 0;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean rodando = true;

        while (rodando) {
            mostrarMenu();

            // Entrada encerrada (Ctrl+D / Ctrl+Z): sai sem estourar exceção.
            if (!scanner.hasNextLine()) {
                System.out.println("\nEntrada encerrada. Encerrando atendimento.");
                break;
            }
            String opcao = scanner.nextLine().trim();

            switch (opcao) {
                case "1":
                    gerarSenhaNormal();
                    break;
                case "2":
                    gerarSenhaPrioritaria();
                    break;
                case "3":
                    chamarProxima();
                    break;
                case "4":
                    mostrarSituacaoDasFilas();
                    break;
                case "5":
                    rodando = false;
                    System.out.println("Encerrando atendimento.");
                    break;
                default:
                    System.out.println("Opção inválida. Escolha de 1 a 5.");
            }
        }

        scanner.close();
    }

    private static void mostrarMenu() {
        System.out.println("\n===== ATENDIMENTO =====");
        System.out.println("1. Gerar senha normal");
        System.out.println("2. Gerar senha prioritária");
        System.out.println("3. Chamar próxima senha");
        System.out.println("4. Ver filas");
        System.out.println("5. Sair");
        System.out.print("Escolha: ");
    }

    private static void gerarSenhaNormal() {
        contadorNormal++;
        String senha = String.format("N%03d", contadorNormal);
        filaNormal.add(senha);
        System.out.println("Senha gerada: " + senha);
    }

    private static void gerarSenhaPrioritaria() {
        contadorPrioritaria++;
        String senha = String.format("P%03d", contadorPrioritaria);
        filaPrioritaria.add(senha);
        System.out.println("Senha gerada: " + senha);
    }

    private static void chamarProxima() {
        if (filaNormal.isEmpty() && filaPrioritaria.isEmpty()) {
            System.out.println("Nenhuma senha na fila.");
            return;
        }

        boolean deveChamarPrioritaria = normaisSeguidas >= 2 && !filaPrioritaria.isEmpty();
        boolean semNormais = filaNormal.isEmpty();

        if (deveChamarPrioritaria || semNormais) {
            System.out.println("Chamando senha: " + filaPrioritaria.poll());
            normaisSeguidas = 0;
        } else {
            System.out.println("Chamando senha: " + filaNormal.poll());
            normaisSeguidas++;
        }
    }

    private static void mostrarSituacaoDasFilas() {
        System.out.println("Normais esperando: " + filaNormal);
        System.out.println("Prioritárias esperando: " + filaPrioritaria);
    }
}
