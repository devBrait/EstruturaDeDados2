public interface ArvoreGenerica {

    void inserir(int valor);
    No buscar(int valor);
    void emOrdem();
    void preOrdem();
    void posOrdem();
    void emLargura();
    No sucessor(int valor);
    No antecessor(int valor);
    void ancestrais(int valor);
    int quantidadedenos();
    void exibirArvore();
    void excluirArvore();
    void excluir(int valor);
}
