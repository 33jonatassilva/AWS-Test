#include <stdio.h>
#include <stdlib.h>
#include <pthread.h>

long long n = 1000000; // Número de termos
int thread_count = 4; // Número de threads
double sum = 0.0;
pthread_mutex_t mutex;

void* Thread_sum(void* rank) {
    long my_rank = (long) rank;
    double factor;
    long long i;
    long long my_n = n / thread_count;
    long long my_first_i = my_n * my_rank;
    long long my_last_i = my_first_i + my_n;

    if (my_first_i % 2 == 0) /* my_first_i is even */
        factor = 1.0;
    else /* my_first_i is odd */
        factor = -1.0;

    for (i = my_first_i; i < my_last_i; i++, factor = -factor) {
        sum += factor / (2.0 * i + 1);
    }
    return NULL;
} /* Thread_sum */

int main() {
    long thread;
    pthread_t* thread_handles;

    thread_handles = malloc(thread_count * sizeof(pthread_t));

    for (thread = 0; thread < thread_count; thread++)
        pthread_create(&thread_handles[thread], NULL,
                       Thread_sum, (void*) thread);

    for (thread = 0; thread < thread_count; thread++)
        pthread_join(thread_handles[thread], NULL);

    free(thread_handles);

    sum = 4 * sum; // Somamos os resultados de todas as threads
    printf("Valor de pi calculado: %.15f\n", sum);

    return 0;
}

