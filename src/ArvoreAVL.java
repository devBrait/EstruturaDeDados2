public class ArvoreAVL implements ArvoreGenerica {

    public No raiz;

    @Override
    public void inserir(int valor) {
        raiz = inserirRec(raiz, valor);
    }

    private No inserirRec(No no, int valor) {
        if (no == null) return new No(valor);

        if (valor < no.valor) no.esquerdo = inserirRec(no.esquerdo, valor);
        else if (valor > no.valor) no.direito = inserirRec(no.direito, valor);
        else return no;

        atualizarAltura(no);
        return balancear(no);
    }

    public void excluir(int valor) {
        raiz = remover(raiz, valor);
    }

    private No remover(No no, int valor) {
        if (no == null) return null;

        if (valor < no.valor) no.esquerdo = remover(no.esquerdo, valor);
        else if (valor > no.valor) no.direito = remover(no.direito, valor);
        else {
            if (no.esquerdo == null || no.direito == null) {
                no = (no.esquerdo != null) ? no.esquerdo : no.direito;
            } else {
                No temp = minimo(no.direito);
                no.valor = temp.valor;
                no.direito = remover(no.direito, temp.valor);
            }
        }

        if (no == null) return null;

        atualizarAltura(no);
        return balancear(no);
    }

    private No minimo(No no) {
        while (no.esquerdo != null) no = no.esquerdo;
        return no;
    }

    private void atualizarAltura(No no) {
        int altEsq = (no.esquerdo != null) ? no.esquerdo.altura : 0;
        int altDir = (no.direito != null) ? no.direito.altura : 0;
        no.altura = 1 + Math.max(altEsq, altDir);
    }

    private int fatorBalanceamento(No no) {
        int altEsq = (no.esquerdo != null) ? no.esquerdo.altura : 0;
        int altDir = (no.direito != null) ? no.direito.altura : 0;
        return altEsq - altDir;
    }

    private No balancear(No no) {
        int fb = fatorBalanceamento(no);

        if (fb > 1) {
            if (fatorBalanceamento(no.esquerdo) < 0)
                no.esquerdo = rotacaoEsquerda(no.esquerdo);
            return rotacaoDireita(no);
        }

        if (fb < -1) {
            if (fatorBalanceamento(no.direito) > 0)
                no.direito = rotacaoDireita(no.direito);
            return rotacaoEsquerda(no);
        }

        return no;
    }

    private No rotacaoDireita(No y) {
        No x = y.esquerdo;
        No T2 = x.direito;

        x.direito = y;
        y.esquerdo = T2;

        atualizarAltura(y);
        atualizarAltura(x);

        return x;
    }

    private No rotacaoEsquerda(No x) {
        No y = x.direito;
        No T2 = y.esquerdo;

        y.esquerdo = x;
        x.direito = T2;

        atualizarAltura(x);
        atualizarAltura(y);

        return y;
    }

    @Override
    public No buscar(int valor) {
        return buscarRec(raiz, valor);
    }

    private No buscarRec(No no, int valor) {
        if (no == null || no.valor == valor) return no;
        return (valor < no.valor) ? buscarRec(no.esquerdo, valor) : buscarRec(no.direito, valor);
    }

    // Percursos
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
        No atual = buscarRec(raiz, valor);
        if (atual == null) return null;

        if (atual.direito != null) {
            No temp = atual.direito;
            while (temp.esquerdo != null) temp = temp.esquerdo;
            return temp;
        }

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
        No atual = buscarRec(raiz, valor);
        if (atual == null) return null;

        if (atual.esquerdo != null) {
            No temp = atual.esquerdo;
            while (temp.direito != null) temp = temp.direito;
            return temp;
        }

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
