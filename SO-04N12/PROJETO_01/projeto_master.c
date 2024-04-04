#define _GNU_SOURCE
#include <stdlib.h>
#include <malloc.h>
#include <sys/types.h>
#include <sys/wait.h>
#include <signal.h>
#include <sched.h>
#include <stdio.h>
// 64kB stack
#define FIBER_STACK 1024*64

// Uma struct e uma variavel especial que contem diversas outras variaveis.
struct c {
    int saldo;
};

// muda o nome da struct "c" para "conta"
typedef struct c conta;

// cria 2 variaveis para a struct: "from" e "to"
conta from, to;
// variavel valor
int valor;

// A thread filha vai executar essa funcao
int transferencia(void *arg) {
    if (from.saldo >= valor) { // Verifica se há saldo suficiente na conta 'from'
        from.saldo -= valor; // Deduz o valor transferido do saldo da conta 'from'
        to.saldo += valor; // Adiciona o valor transferido ao saldo da conta 'to'
        printf("Transferencia concluida com sucesso!\n"); // Imprime uma mensagem indicando que a transferência foi bem-sucedida
    }
    printf("Saldo de c1: %d\n", from.saldo); // Imprime o saldo atual da conta 'from'
    printf("Saldo de c2: %d\n", to.saldo); // Imprime o saldo atual da conta 'to'
    return 0;
}
int transferencia2(void *arg) {
    if (to.saldo >= valor){ // Verifica se há saldo suficiente na conta 'to'
            to.saldo -= valor; // Deduz o valor transferido do saldo da conta 'to'
            from.saldo += valor; // Adiciona o valor transferido ao saldo da conta 'from'
            printf("Transferencia concluida com sucesso!\n"); // Imprime uma mensagem indicando que a transferência foi bem-sucedida
    }
    printf("Saldo de c1: %d\n", from.saldo); // Imprime o saldo atual da conta 'from'
    printf("Saldo de c2: %d\n", to.saldo); // Imprime o saldo atual da conta 'to'
    return 0;
}

int main()
{
    void* stack;
    pid_t pid;
    int i;
    // Alocar memoria
    stack = malloc( FIBER_STACK ); // Aloca memória para a pilha da thread
    if (stack == 0) { // Verifica se a alocação foi bem-sucedida
        perror("malloc: could not allocate stack"); // Imprime uma mensagem de erro se a alocação falhar
        exit(1); // Sai do programa com código de erro 1
    }
    // Todas as contas começam com saldo 100
    printf("Digite o saldo na conta 'from': "); // Solicita ao usuário o saldo da conta 'from'
    scanf("%d", &from.saldo); // Lê o saldo da conta 'from' fornecido pelo usuário
    printf("Digite o saldo na conta 'to': "); // Solicita ao usuário o saldo da conta 'to'
    scanf("%d", &to.saldo); // Lê o saldo da conta 'to' fornecido pelo usuário

    printf( "Transferindo 10 para a conta c2\n" ); // Imprime uma mensagem indicando a transferência de 10 para a conta 'to'
    valor = 10; // Define o valor da transferência como 10
      
    for (i = 0; i < 50; i++) {
        //Chama a syscall para criar a thread criança
        pid = clone(&transferencia, (char*) stack + FIBER_STACK, SIGCHLD | CLONE_FS | CLONE_FILES | CLONE_SIGHAND | CLONE_VM | CLONE_VFORK, 0); // Cria uma nova thread para transferir dinheiro da conta 'from' para 'to'
        if (pid == -1) { // Verifica se houve erro na criação da thread
            perror("clone"); // Imprime uma mensagem de erro se a criação da thread falhar
            exit(2); // Sai do programa com código de erro 2
        }
        
        if (from.saldo < valor) { // Verifica se a conta 'from' não tem saldo suficiente
            printf("Conta c1 sem saldo!\n"); // Imprime uma mensagem indicando que a conta 'from' não tem saldo suficiente
        }
        if(from.saldo == 0 || to.saldo == 0){ // Verifica se uma das contas tem saldo zero
            printf("\n\n");
            break; }// Interrompe o laço se uma das contas tiver saldo zero
    }

	for (i = 0; i < 50; i++) {
        //Call the clone system call to create the child thread
        pid = clone(&transferencia2, (char*) stack + FIBER_STACK, SIGCHLD | CLONE_FS | CLONE_FILES | CLONE_SIGHAND | CLONE_VM | CLONE_VFORK, 0); // Cria uma nova thread para transferir dinheiro da conta 'to' para 'from'
        if (pid == -1){ // Verifica se houve erro na criação da thread
            perror("clone"); // Imprime uma mensagem de erro se a criação da thread falhar
            exit(2); // Sai do programa com código de erro 2
        }

        if (from.saldo < valor) { // Verifica se a conta 'from' não tem saldo suficiente
            printf("Conta c1 sem saldo!\n"); // Imprime uma mensagem indicando que a conta 'from' não tem saldo suficiente
        }

		if (to.saldo < valor) { // Verifica se a conta 'to' não tem saldo suficiente
			printf("Conta c2 sem saldo!\n"); // Imprime uma mensagem indicando que a conta 'to' não tem saldo suficiente
		}
		if(from.saldo == 0 || to.saldo == 0){ // Verifica se uma das contas tem saldo zero
            printf("\n\n");
            break;} // Interrompe o laço se uma das contas tiver saldo zero
	}

    // Liberar a memoria
    free(stack); // Libera a memória alocada para a pilha da thread
    printf("Transferencias concluidas e memoria liberada.\n"); // Imprime uma mensagem indicando que as transferências foram concluídas e a memória foi liberada
    printf("Conta 1 com: %d\n", from.saldo); // Imprime o saldo final da conta 'from'
    printf("Conta 2 com: %d\n", to.saldo); // Imprime o saldo final da conta 'to'
    return 0; // Retorna 0 para indicar que o programa foi executado com sucesso
}

