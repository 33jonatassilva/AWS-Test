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

// Estrutura para passar informações do processo pai para a thread
struct ThreadArgs {
    int valor;
};

// A função da thread agora recebe um argumento e retorna um valor
int threadFunction(void* argument) {
    struct ThreadArgs* args = (struct ThreadArgs*)argument;
    printf("Valor recebido pela thread: %d\n", args->valor);

    // Modifica o valor recebido pela thread
    args->valor *= 2;

    printf("Valor enviado de volta pela thread: %d\n", args->valor);

    // Retorna um valor para o processo pai
    return args->valor;
}

int main() {
    void* stack;
    pid_t pid;
    int status;

    // Allocate the stack
    stack = malloc(FIBER_STACK);
    if (stack == 0) {
        perror("malloc: could not allocate stack");
        exit(1);
    }

    printf("Creating child thread\n");

    // Prepara os argumentos para a thread
    struct ThreadArgs args = { 42 };

    // Call the clone system call to create the child thread
    pid = clone(&threadFunction, (char*)stack + FIBER_STACK,
                SIGCHLD | CLONE_FS | CLONE_FILES | CLONE_SIGHAND | CLONE_VM, &args);
    if (pid == -1) {
        perror("clone");
        exit(2);
    }

    // Wait for the child thread to exit and get its return value
    pid = waitpid(pid, &status, 0);
    if (pid == -1) {
        perror("waitpid");
        exit(3);
    }

    if (WIFEXITED(status)) {
        printf("Child thread returned: %d\n", WEXITSTATUS(status));
    } else {
        printf("Child thread did not exit normally\n");
    }

    // Free the stack
    free(stack);
    printf("Child thread returned and stack freed.\n");

    return 0;
}


