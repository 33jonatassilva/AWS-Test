#include <stdio.h>
#include <stdlib.h>
#include <time.h>


int main() {
    int tam, line, small;

    srand(time(NULL));

    printf("Digite uma número para sua matriz quadrada: ");
    scanf("%d", &tam);

    int matriz[tam][tam];

    printf("\n");

    for (int i = 0; i < tam; i++) {
        for (int j = 0; j < tam; j++) {
            matriz[i][j] = rand() % 100;
        }
    }

    small = matriz[0][0];
    line = 0;

    for (int i = 0; i < tam; i++) {
        printf("[");
        for (int j = 0; j < tam; j++) {
            printf("%d ", matriz[i][j]) ;
            if(matriz[i][j] < small){
                small = matriz[i][j];
                line = i;
            }
        }
        printf("]\n");
    }

    printf("O menor elemento é %d.\nE a linha em que ele está é a %d.\n\n", small, line);       
    return 0;
}

