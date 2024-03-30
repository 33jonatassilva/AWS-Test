#include <stdio.h>
#include <sys/types.h>
#include <unistd.h>
#include <sys/wait.h>

int value = 5;

int main() {
    int pipefd[2];
    pid_t pid;

    if (pipe(pipefd) == -1) {
        perror("pipe");
        return 1;
    }

    pid = fork();

    if (pid == 0) { // child process /
        close(pipefd[0]); // Fechar a extremidade de leitura do pipe no processo filho

        printf("Entrei no filho!\n");
        value += 15;
        printf("CHILD: value = %d\n", value); // LINE A /

        // Escrever o valor modificado no pipe
        write(pipefd[1], &value, sizeof(value));

        close(pipefd[1]); // Fechar a extremidade de escrita do pipe no processo filho
        return 0;
    }
    else if (pid > 0) { // parent process /
        close(pipefd[1]); // Fechar a extremidade de escrita do pipe no processo pai

        wait(NULL);

        // Ler o valor modificado do pipe
        read(pipefd[0], &value, sizeof(value));

        printf("PARENT: value = %d\n", value); // LINE A */

        close(pipefd[0]); // Fechar a extremidade de leitura do pipe no processo pai
        return 0;
    }
}
