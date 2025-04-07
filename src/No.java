public class No {
    int valor;
    int altura; // Utilização na árvore AVL
    No esquerdo, direito;

    public No(int valor) {
        this.valor = valor;
        this.altura = 1;
        this.esquerdo = null;
        this.direito = null;
    }
}
