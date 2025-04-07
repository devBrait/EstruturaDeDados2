public class ArvoreBinaria implements ArvoreGenerica {

    public No raiz;

    @Override
    public void inserir(int valor) {
        raiz = inserirRec(raiz, valor);
    }

    private No inserirRec(No no, int valor) {
        if (no == null) return new No(valor);

        if (no.esquerdo == null) no.esquerdo = inserirRec(no.esquerdo, valor);
        else if (no.direito == null) no.direito = inserirRec(no.direito, valor);
        else {
            int alturaEsq = altura(no.esquerdo);
            int alturaDir = altura(no.direito);
            if (alturaEsq <= alturaDir)
                no.esquerdo = inserirRec(no.esquerdo, valor);
            else
                no.direito = inserirRec(no.direito, valor);
        }
        return no;
    }

    @Override
    public No buscar(int valor) {
        return buscarRec(raiz, valor);
    }

    private No buscarRec(No no, int valor) {
        if (no == null || no.valor == valor) return no;
        No encontrado = buscarRec(no.esquerdo, valor);
        return (encontrado != null) ? encontrado : buscarRec(no.direito, valor);
    }

    @Override
    public void excluir(int valor) {
        raiz = remover(raiz, valor);
    }

    private No remover(No no, int valor) {
        if (no == null) return null;

        if (no.valor == valor) {
            // Remover nó sem filhos ou com um filho
            if (no.esquerdo == null) return no.direito;
            if (no.direito == null) return no.esquerdo;

            // Nó com dois filhos: substitui pelo menor da subárvore direita
            No sucessor = minimo(no.direito);
            no.valor = sucessor.valor;
            no.direito = remover(no.direito, sucessor.valor);
        } else {
            no.esquerdo = remover(no.esquerdo, valor);
            no.direito = remover(no.direito, valor);
        }
        return no;
    }

    private No minimo(No no) {
        while (no != null && no.esquerdo != null)
            no = no.esquerdo;
        return no;
    }

    private int altura(No no) {
        if (no == null) return 0;
        return 1 + Math.max(altura(no.esquerdo), altura(no.direito));
    }

    @Override
    public void emOrdem() {
        emOrdemRec(raiz);
        System.out.println();
    }

    private void emOrdemRec(No no) {
        if (no != null) {
            emOrdemRec(no.esquerdo);
            System.out.print(no.valor + " ");
            emOrdemRec(no.direito);
        }
    }

    @Override
    public void preOrdem() {
        preOrdemRec(raiz);
        System.out.println();
    }

    private void preOrdemRec(No no) {
        if (no != null) {
            System.out.print(no.valor + " ");
            preOrdemRec(no.esquerdo);
            preOrdemRec(no.direito);
        }
    }

    @Override
    public void posOrdem() {
        posOrdemRec(raiz);
        System.out.println();
    }

    private void posOrdemRec(No no) {
        if (no != null) {
            posOrdemRec(no.esquerdo);
            posOrdemRec(no.direito);
            System.out.print(no.valor + " ");
        }
    }

    @Override
    public void emLargura() {
        if (raiz == null) return;
        java.util.Queue<No> fila = new java.util.LinkedList<>();
        fila.add(raiz);

        while (!fila.isEmpty()) {
            No atual = fila.poll();
            System.out.print(atual.valor + " ");
            if (atual.esquerdo != null) fila.add(atual.esquerdo);
            if (atual.direito != null) fila.add(atual.direito);
        }
        System.out.println();
    }

    @Override
    public No sucessor(int valor) {
        // Em árvore binária comum, não existe ordem -> não é possível garantir sucessor
        return null;
    }

    @Override
    public No antecessor(int valor) {
        // Em árvore binária comum, também não é possível garantir antecessor
        return null;
    }

    @Override
    public void ancestrais(int valor) {
        mostrarAncestrais(raiz, valor);
        System.out.println();
    }

    private boolean mostrarAncestrais(No no, int valor) {
        if (no == null) return false;
        if (no.valor == valor) return true;

        if (mostrarAncestrais(no.esquerdo, valor) || mostrarAncestrais(no.direito, valor)) {
            System.out.print(no.valor + " ");
            return true;
        }

        return false;
    }

    @Override
    public int quantidadedenos() {
        return contarNos(raiz);
    }

    private int contarNos(No no) {
        if (no == null) return 0;
        return 1 + contarNos(no.esquerdo) + contarNos(no.direito);
    }

    @Override
    public void exibirArvore() {
        imprimirArvore(raiz, "", true);
    }

    private void imprimirArvore(No no, String prefixo, boolean ehDireita) {
        if (no != null) {
            System.out.println(prefixo + (ehDireita ? "└── " : "├── ") + no.valor);
            imprimirArvore(no.direito, prefixo + (ehDireita ? "    " : "│   "), false);
            imprimirArvore(no.esquerdo, prefixo + (ehDireita ? "    " : "│   "), true);
        }
    }

    @Override
    public void excluirArvore() {
        raiz = null;
    }
}
