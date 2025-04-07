public class ArvoreBST implements ArvoreGenerica {

    public No raiz;

    @Override
    public void inserir(int valor) {
        raiz = inserirRec(raiz, valor);
    }

    private No inserirRec(No no, int valor) {
        if (no == null)
            return new No(valor);
        if (valor < no.valor)
            no.esquerdo = inserirRec(no.esquerdo, valor);
        else if (valor > no.valor)
            no.direito = inserirRec(no.direito, valor);
        // valores iguais são ignorados (sem duplicatas)
        return no;
    }

    @Override
    public No buscar(int valor) {
        return buscarRec(raiz, valor);
    }

    private No buscarRec(No no, int valor) {
        if (no == null || no.valor == valor)
            return no;
        if (valor < no.valor)
            return buscarRec(no.esquerdo, valor);
        else
            return buscarRec(no.direito, valor);
    }

    public void excluir(int valor) {
        raiz = remover(raiz, valor);
    }

    public No remover(No no, int valor) {
        if (no == null)
            return null;

        if (valor < no.valor) {
            no.esquerdo = remover(no.esquerdo, valor);
        } else if (valor > no.valor) {
            no.direito = remover(no.direito, valor);
        } else {
            // Nó com um ou nenhum filho
            if (no.esquerdo == null)
                return no.direito;
            else if (no.direito == null)
                return no.esquerdo;

            // Nó com dois filhos: obter o menor da subárvore direita
            no.valor = valorMinimo(no.direito);
            no.direito = remover(no.direito, no.valor);
        }

        return no;
    }

    private int valorMinimo(No no) {
        int min = no.valor;
        while (no.esquerdo != null) {
            min = no.esquerdo.valor;
            no = no.esquerdo;
        }
        return min;
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
        No atual = buscar(valor);
        if (atual == null) return null;

        // Caso 1: tem subárvore direita
        if (atual.direito != null) {
            No temp = atual.direito;
            while (temp.esquerdo != null) temp = temp.esquerdo;
            return temp;
        }

        // Caso 2: não tem subárvore direita
        No sucessor = null, ancestor = raiz;
        while (ancestor != atual) {
            if (valor < ancestor.valor) {
                sucessor = ancestor;
                ancestor = ancestor.esquerdo;
            } else {
                ancestor = ancestor.direito;
            }
        }
        return sucessor;
    }

    @Override
    public No antecessor(int valor) {
        No atual = buscar(valor);
        if (atual == null) return null;

        // Caso 1: tem subárvore esquerda
        if (atual.esquerdo != null) {
            No temp = atual.esquerdo;
            while (temp.direito != null) temp = temp.direito;
            return temp;
        }

        // Caso 2: não tem subárvore esquerda
        No antecessor = null, ancestor = raiz;
        while (ancestor != atual) {
            if (valor > ancestor.valor) {
                antecessor = ancestor;
                ancestor = ancestor.direito;
            } else {
                ancestor = ancestor.esquerdo;
            }
        }
        return antecessor;
    }

    @Override
    public void ancestrais(int valor) {
        mostrarAncestrais(raiz, valor);
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
