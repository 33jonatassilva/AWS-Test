#include <stdio.h>

void imprimir_padrao(int max_num) {
    if (max_num % 2 == 0) {
        printf("O número máximo deve ser ímpar.\n");
        return;
    }

    int i, j, k;
    for (i = 1; i <= max_num; i++) {
        for (j = 1; j < i; j++) {
            printf("  ");
        }
        for (k = i; k <= max_num - (i - 1); k++) {
            printf("%d ", k);
        }
        if(i <= (max_num/2))
            printf("\n");
    }
}

int main() {
    int max_num;
    printf("Digite o número máximo (deve ser ímpar): ");
    scanf("%d", &max_num);
    imprimir_padrao(max_num);
    return 0;
}

