import java.util.Scanner;
import java.util.Random;

public class Main {
    public static void main(String []args){

        Arvore ar = new Arvore();
        Scanner sc = new Scanner(System.in);
        Random r = new Random();
        final int AMPLITUDE_SORTEIOS = 900;
        boolean continua = true;
        int opcao, valor, qtd_sorteio;
        do {
            System.out.println("\n1 - insira um elemento");
            System.out.println("2 - insira um sequencia aleatória de n elementos");
            System.out.println("3 - remova um elemento");
            System.out.println("4 - remova um sequencia aleatória de n elementos");
            System.out.println("5 - busque um valor");
            System.out.println("6 - percorra a árvore EmOrdem");
            System.out.println("7 - percorra a árvore PréOrdem");
            System.out.println("8 - percorra a árvore PósOrdem");
            System.out.println("9 - percorra a árvore em largura");
            System.out.println("10 - sucessor de um elemento");
            System.out.println("11 - antecessor de um elemento");
            System.out.println("12 - ancestrais de um elemento");
            System.out.println("13 - exibir árvore");
            System.out.println("14 - excluir árvore");
            System.out.println("15 - sair\n");
            System.out.print("\nDigite sua opção: ");
            opcao = sc.nextInt();
            switch(opcao) {
                case 1:
                    System.out.print("Digite o valor a ser inserido na árvore: ");
                    valor = sc.nextInt();
                    ar.inserir(valor);
                    System.out.print("Árvore resultante: ");
                    ar.emOrdem();
                    break;
                case 2:
                    System.out.print("Digite a quantidade de números a serem sorteados para a inserção: ");
                    qtd_sorteio = sc.nextInt();
                    for(int i = 0; i < qtd_sorteio; i++)
                        ar.inserir(r.nextInt(AMPLITUDE_SORTEIOS));
                    System.out.print("Árvore resultante: ");
                    ar.emOrdem();
                    break;
                case 3:

                    break;
                case 4:

                    break;
                case 5:
                    No noBusca;
                    System.out.print("Digite o valor a ser encontrado na árvore: ");
                    valor = sc.nextInt();
                    noBusca = ar.buscar(valor);
                    if(noBusca != null)
                        System.out.println("Achou o valor " + noBusca.valor);
                    else
                        System.out.println("NÃO Achou o valor " + valor);
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
                    ar.bfs();
                    break;
                case 10:

                    break;
                case 11:
                    break;
                case 12:

                    break;
                case 13:
                    ar.exibirArvore();
                    break;
                case 14:
                    ar.excluirArvore();
                    break;
                case 15:
                    continua = false;
                    break;
                default:
                    System.out.println("\nDigite uma opção válida!\n");
            }
        } while(continua);
    }
}
