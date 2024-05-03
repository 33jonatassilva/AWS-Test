#include <stdio.h>
#include <stdlib.h>
#include <pthread.h>
#include <time.h>

#define MAX_SIZE 100

int **A;
int *x;
int *y;
int m, n, thread_count;

void *Pth_mat_vect(void *rank) {
    long my_rank = (long) rank;
    int i, j;
    int local_m = m / thread_count;
    int my_first_row = my_rank * local_m;
    int my_last_row = (my_rank + 1) * local_m - 1;

    for (i = my_first_row; i <= my_last_row; i++) {
        y[i] = 0.0;
        for (j = 0; j < n; j++) {
            y[i] += A[i][j] * x[j];
        }
    }

    return NULL;
}

void Mat_vect_mult() {
    pthread_t *thread_handles;
    long thread;

    thread_handles = malloc(thread_count * sizeof(pthread_t));

    for (thread = 0; thread < thread_count; thread++) {
        pthread_create(&thread_handles[thread], NULL, Pth_mat_vect, (void *) thread);
    }

    for (thread = 0; thread < thread_count; thread++) {
        pthread_join(thread_handles[thread], NULL);
    }

    free(thread_handles);
}

int main() {
    printf("Digite o número de linhas da matriz A: ");
    scanf("%d", &m);
    printf("Digite o número de colunas da matriz A e o tamanho do vetor x: ");
    scanf("%d", &n);
    thread_count = n;

    // Aloca matriz A e vetores x e y
    A = malloc(m * sizeof(int *));
    for (int i = 0; i < m; i++) {
        A[i] = malloc(n * sizeof(int));
    }
    x = malloc(n * sizeof(int));
    y = malloc(m * sizeof(int));

    // Preencher a matriz A com números aleatórios até 20
    srand(time(NULL));
    printf("Matriz A:\n");
    for (int i = 0; i < m; i++) {
        for (int j = 0; j < n; j++) {
            A[i][j] = rand() % 21; // Números aleatórios de 0 a 20
            printf("%d ", A[i][j]);
        }
        printf("\n");
    }

    // Preencher o vetor x com números aleatórios até 10
    printf("\nVetor B:\n");
    for (int i = 0; i < n; i++) {
        x[i] = rand() % 11; // Números aleatórios de 0 a 10
        printf("%d\n", x[i]);
    }

    // Realizar a multiplicação de matriz-vetor
    Mat_vect_mult();

    // Imprimir o vetor resultante y
    printf("\nVetor Resultado de AxB:\n");
    for (int i = 0; i < m; i++) {
        printf("%d\n", y[i]);
    }

    // Liberar memória alocada
    for (int i = 0; i < m; i++) {
        free(A[i]);
    }
    free(A);
    free(x);
    free(y);

    return 0;
}

