import java.util.Scanner;
import java.util.Random;

public class Main {
    public static void main(String []args){
        Scanner sc = new Scanner(System.in);
        Random r = new Random();
        final int AMPLITUDE_SORTEIOS = 900;
        boolean continua = true;
        int opcao, valor, qtd_sorteio;

        ArvoreGenerica ar = null;
        System.out.println("Escolha o tipo de árvore:");
        System.out.println("1 - Árvore Binária Simples");
        System.out.println("2 - Árvore Binária de Busca (BST)");
        System.out.println("3 - Árvore AVL (balanceada)");
        System.out.print("Digite sua opção: ");
        int tipo = sc.nextInt();

        switch(tipo) {
            case 1:
                ar = new ArvoreBinaria();
                break;
            case 2:
                ar = new ArvoreBST();
                break;
            case 3:
                ar = new ArvoreAVL();
                break;
            default:
                System.out.println("Tipo inválido. Encerrando programa.");
                return;
        }
        do {
            System.out.println("\n1 - Inserir elemento");
            System.out.println("2 - Inserir sequência aleatória");
            System.out.println("3 - Remover elemento");
            System.out.println("4 - Remover sequência aleatória");
            System.out.println("5 - Buscar valor");
            System.out.println("6 - Em ordem");
            System.out.println("7 - Pré-ordem");
            System.out.println("8 - Pós-ordem");
            System.out.println("9 - Em largura");
            System.out.println("10 - Sucessor");
            System.out.println("11 - Antecessor");
            System.out.println("12 - Ancestrais");
            System.out.println("13 - Quantidade de nós");
            System.out.println("14 - Exibir árvore");
            System.out.println("15 - Excluir árvore");
            System.out.println("16 - Sair\n");

            System.out.print("Digite sua opção: ");
            opcao = sc.nextInt();

            switch(opcao) {
                case 1:
                    System.out.print("Digite o valor a ser inserido: ");
                    valor = sc.nextInt();
                    ar.inserir(valor);
                    break;
                case 2:
                    System.out.print("Quantidade de elementos aleatórios: ");
                    qtd_sorteio = sc.nextInt();
                    for(int i = 0; i < qtd_sorteio; i++) {
                        ar.inserir(r.nextInt(AMPLITUDE_SORTEIOS));
                    }
                    break;
                case 3:
                    System.out.print("Valor a remover: ");
                    valor = sc.nextInt();
                    ar.excluir(valor); // esse método precisa ser ajustado para existir na interface
                    break;
                case 4:
                    System.out.print("Quantidade a remover aleatoriamente: ");
                    qtd_sorteio = sc.nextInt();
                    for(int i = 0; i < qtd_sorteio; i++) {
                        valor = r.nextInt(AMPLITUDE_SORTEIOS);
                        System.out.print(valor + " ");
                        ar.excluir(valor);
                    }
                    break;
                case 5:
                    System.out.print("Valor a buscar: ");
                    valor = sc.nextInt();
                    No buscado = ar.buscar(valor);
                    System.out.println(buscado != null ? "Encontrado: " + buscado.valor : "Não encontrado.");
                    break;
                case 6:
                    ar.emOrdem();
                    break;
                case 7:
                    ar.preOrdem();
                    break;
                case 8:
                    ar.posOrdem();
                    break;
                case 9:
                    ar.emLargura();
                    break;
                case 10:
                    System.out.print("Nó para achar sucessor: ");
                    valor = sc.nextInt();
                    No suc = ar.sucessor(valor);
                    System.out.println(suc != null ? "Sucessor: " + suc.valor : "Sem sucessor.");
                    break;
                case 11:
                    System.out.print("Nó para achar antecessor: ");
                    valor = sc.nextInt();
                    No ant = ar.antecessor(valor);
                    System.out.println(ant != null ? "Antecessor: " + ant.valor : "Sem antecessor.");
                    break;
                case 12:
                    System.out.print("Elemento para ver ancestrais: ");
                    valor = sc.nextInt();
                    ar.ancestrais(valor);
                    break;
                case 13:
                    System.out.println("Quantidade de nós: " + ar.quantidadedenos());
                    break;
                case 14:
                    ar.exibirArvore();
                    break;
                case 15:
                    ar.excluirArvore();
                    break;
                case 16:
                    continua = false;
                    break;
                default:
                    System.out.println("Opção inválida!");
            }
        } while(continua);
    }
}
