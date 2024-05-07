#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>

// Definição da estrutura da célula
struct reg {
    int conteudo;
    struct reg *prox;
};
typedef struct reg celula;


// Função para imprimir todos os valores da lista encadeada
    void imprimirLista(celula *inicio) {
        if(inicio == NULL) printf("Lista vazia");
        
        celula *atual = inicio;
        
        // Percorre a lista enquanto não chegar ao final (quando o ponteiro for NULL)
        int cont = 1;
        while (atual != NULL) {
            printf("Conteudo da celula %d = %d\n", cont, atual->conteudo); // Imprime o conteúdo da célula atual
            cont++;
            atual = atual->prox; // Avança para a próxima célula
        }
        printf("\n"); // Imprime uma quebra de linha no final
    }
    
    
    // Função para remover todos os elementos da lista encadeada
    void removerLista(celula *inicio) {
        celula *atual = inicio;
        celula *proxima;
    
        // Percorre a lista enquanto não chegar ao final
        while (atual != NULL) {
            proxima = atual->prox; // Salva o ponteiro para a próxima célula
            free(atual); // Libera a memória alocada para a célula atual
            atual = proxima; // Avança para a próxima célula
        }
    }
    
    
    // Função para remover a primeira ocorrência de um elemento na lista encadeada
    void removerElemento(celula **inicio, int valor) {
        celula *atual = *inicio;
        celula *anterior = NULL;
    
        // Percorre a lista enquanto não chegar ao final
        while (atual != NULL) {
            if (atual->conteudo == valor) { // Verifica se encontrou o valor a ser removido
                if (anterior == NULL) { // Se o elemento a ser removido é o primeiro da lista
                    *inicio = atual->prox; // Atualiza o início da lista
                } else {
                    anterior->prox = atual->prox; // Remove o elemento atual da lista
                }
                free(atual); // Libera a memória alocada para o elemento removido
                return; // Termina a função após remover o elemento
            }
            anterior = atual; // Atualiza o ponteiro para o elemento anterior
            atual = atual->prox; // Avança para o próximo elemento
        }
    }
    
    
    // Função para calcular o número máximo de elementos na fila com base na memória RAM disponível
    void calcularMaxElementosFila() {
        long paginas = sysconf(_SC_PHYS_PAGES);
        long tamanho_pagina = sysconf(_SC_PAGE_SIZE);
        size_t memoria_disponivel = paginas * tamanho_pagina;
        size_t tamanho_celula = sizeof(celula);
        size_t max_elementos = memoria_disponivel / tamanho_celula;
        printf("Número máximo de elementos na fila: %zu\n", max_elementos);
        //return max_elementos;
    }
    
    // Função para criar três instâncias do objeto célula
    celula *criarLista() {
        celula *p1 = (celula*)malloc(sizeof(celula));
        celula *p2 = (celula*)malloc(sizeof(celula));
        celula *p3 = (celula*)malloc(sizeof(celula));
    
        // Preenchendo o conteúdo das células
        p1->conteudo = 10;
        p2->conteudo = 20;
        p3->conteudo = 30;
    
        // Encadeando as células
        p1->prox = p2;
        p2->prox = p3;
        p3->prox = NULL; // A última célula aponta para NULL
    
        return p1; // Retornando o ponteiro para o início da lista
    }
    
    
    
    
    
   
    
    // Função do menu
void menu() {
    celula *lista = NULL;
    char opcao[3];
    do {
        printf("\n-=-=-=-=-=-=-=-=-=-=-=-=-=-= MENU -=-=-=-=-=-=-=-=-=-=-=-=-=-=\n\n");
        printf("1. Criar lista com três instâncias do objeto célula\n");
        printf("2. Imprimir todos os valores da lista\n");
        printf("3. Remover todos os elementos da lista\n");
        printf("4. Calcular máximo de elementos possíveis na fila\n");
        printf("5. Sair\n");
        printf("Escolha uma opção: ");
        scanf("%s", opcao);

        switch (opcao[0]) {
            case '1':
                lista = criarLista();
                printf("Lista criada com sucesso!\n");
                break;
            case '2':
                if (lista != NULL) {
                    printf("Valores da lista:\n");
                    imprimirLista(lista);
                } else {
                    printf("Lista não foi criada ainda.\n");
                }
                break;
            case '3':
                if (lista != NULL) {
                    removerLista(lista);
                    lista = NULL; // Define lista como NULL após a remoção
                    printf("Elementos da lista removidos e memória liberada.\n");
                } else {
                    printf("Lista não foi criada ainda.\n");
                }
                break;
            case '4':
                //printf("Número máximo de elementos na fila: ");
                calcularMaxElementosFila();
                break;
            case '5':
                printf("Saindo...\n");
                break;
            default:
                printf("Opção inválida! Tente novamente.\n");
                break;
        }
    } while (opcao[0] != '5');
}

int main() {
    
     // Criando três instâncias da célula
    celula *p1 = (celula*)malloc(sizeof(celula));
    celula *p2 = (celula*)malloc(sizeof(celula));
    celula *p3 = (celula*)malloc(sizeof(celula));

    // Preenchendo o conteúdo das células
    p1->conteudo = 10;
    p2->conteudo = 20;
    p3->conteudo = 30;

    // Encadeando as células
    p1->prox = p2;
    p2->prox = p3;
    p3->prox = NULL; // A última célula aponta para NULL

    // Exibindo o conteúdo das células
    //printf("Conteúdo da célula 1: %d\n", p1->conteudo);
    //printf("Conteúdo da célula 2: %d\n", p2->conteudo);
    //printf("Conteúdo da célula 3: %d\n", p3->conteudo);
    
    //imprimirLista(p1);
    
    
    //int tamanho_celula = sizeof(celula);
    //printf("Tamanho de cada instância da célula: %d bytes\n", tamanho_celula);
    
    //calcularMaxElementosFila();

    // Liberando a memória alocada
    //free(p1);
    //free(p2);
    //free(p3);
    
    menu();
    
    return 0;
    
}


