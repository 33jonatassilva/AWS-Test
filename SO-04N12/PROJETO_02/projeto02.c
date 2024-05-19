#include <pthread.h>
#include <stdio.h>
#include <stdlib.h>

// Variáveis globais
int timer = 0, direction = -1;
int number_passenger, number_passenger_zero = 0, number_passenger_one = 0;
int passengers_zero[10000], passengers_one[10000];
int point0 = 0, point1 = 0;
int rides = 0;

pthread_mutex_t mutex = PTHREAD_MUTEX_INITIALIZER;
pthread_cond_t cond = PTHREAD_COND_INITIALIZER;

// Função para adicionar passageiro em ordem crescente
void adicionar_passageiro(int *passageiros, int *number_passenger, int T) {
    passageiros[*number_passenger] = T;
    int j = *number_passenger;
    while (j > 0 && passageiros[j - 1] > passageiros[j]) {
        int aux = passageiros[j - 1];
        passageiros[j - 1] = passageiros[j];
        passageiros[j] = aux;
        j--;
    }
    (*number_passenger)++;
}

// Função que representa o movimento do lado 0 da escada
void* escalator_zero(void* arg) {
    while (1) {
        pthread_mutex_lock(&mutex);
        if (point0 >= number_passenger_zero) {
            pthread_mutex_unlock(&mutex);
            break;
        }
        while (direction != 0) {
            pthread_cond_wait(&cond, &mutex);
        }
        if (timer < passengers_zero[point0]) {
            direction = -1;
        } else {
            timer += 10 - (timer - passengers_zero[point0]);
            point0++;
            rides++;
            if (point0 == number_passenger_zero) {
                direction = -1;
            }
        }
        pthread_cond_broadcast(&cond);
        pthread_mutex_unlock(&mutex);
    }
    return NULL;
}

// Função que representa o movimento do lado 1 da escada
void* escalator_one(void* arg) {
    while (1) {
        pthread_mutex_lock(&mutex);
        if (point1 >= number_passenger_one) {
            pthread_mutex_unlock(&mutex);
            break;
        }
        while (direction != 1) {
            pthread_cond_wait(&cond, &mutex);
        }
        if (timer < passengers_one[point1]) {
            direction = -1;
        } else {
            timer += 10 - (timer - passengers_one[point1]);
            point1++;
            rides++;
            if (point1 == number_passenger_one) {
                direction = -1;
            }
        }
        pthread_cond_broadcast(&cond);
        pthread_mutex_unlock(&mutex);
    }
    return NULL;
}

// Função para determinar a próxima direção e ajustar o timer
void decide_direction() {
    pthread_mutex_lock(&mutex);
    if (direction == -1) {
        int ladoAux;
        if (point0 == number_passenger_zero) {
            ladoAux = 1;
        } else if (point1 == number_passenger_one) {
            ladoAux = 0;
        } else if (passengers_zero[point0] <= passengers_one[point1]) {
            ladoAux = 0;
        } else {
            ladoAux = 1;
        }

        if (ladoAux == 0) {
            if (timer <= passengers_zero[point0]) timer = passengers_zero[point0];
            else {
                for (int k = point0; k < number_passenger_zero && timer > passengers_zero[k]; k++) {
                    passengers_zero[k] = timer;
                }
            }
        } else {
            if (timer <= passengers_one[point1]) timer = passengers_one[point1];
            else {
                for (int k = point1; k < number_passenger_one && timer > passengers_one[k]; k++) {
                    passengers_one[k] = timer;
                }
            }
        }
        direction = ladoAux;
    }
    pthread_cond_broadcast(&cond);
    pthread_mutex_unlock(&mutex);
}

int main(void) {
    // Recebe o número de passageiros e o timer de cada um
    printf("Insira a quantidade de passageiros: ");
    scanf("%d", &number_passenger);
    while (number_passenger > 10000 || number_passenger < 1) {
        printf("Insira uma quantidade valida de passageiros: ");
        scanf("%d", &number_passenger);
    }

    for (int i = 0; i < number_passenger; i++) {
        int T, L;
        printf("\nInsira o momento de chegada do passageiro %d e seu sentido (0 ou 1): ", i + 1);
        scanf("%d %d", &T, &L);
        while ((T < 0 || T > 100000) || (L != 0 && L != 1)) {
            printf("Insira valores validos: ");
            scanf("%d %d", &T, &L);
        }

        if (L == 0) {
            adicionar_passageiro(passengers_zero, &number_passenger_zero, T);
        } else if (L == 1) {
            adicionar_passageiro(passengers_one, &number_passenger_one, T);
        }
    }

    // Cria as threads
    pthread_t thread0, thread1;
    pthread_create(&thread0, NULL, escalator_zero, NULL);
    pthread_create(&thread1, NULL, escalator_one, NULL);

    // Enquanto houver rides para fazer, decide para qual lado a escada volta a andar
    while (rides < number_passenger) {
        decide_direction();
    }

    pthread_join(thread0, NULL);
    pthread_join(thread1, NULL);

    // Mostra o timer total gasto
    printf("\n\nTempo total até a ultima pessoa sair: %d\n", timer);

    return 0;
}

