#include <stdio.h>

// Função para exibir a matriz
void displayMatrix(int matrix[][100], int rows, int cols) {
    printf("Matriz:\n");
    for (int i = 0; i < rows; i++) {
        for (int j = 0; j < cols; j++) {
            printf("%d\t", matrix[i][j]);
        }
        printf("\n");
    }
}

// Função para multiplicar uma linha por um número
void multiplyRow(int matrix[][100], int row, int cols, int multiplier) {
    for (int j = 0; j < cols; j++) {
        matrix[row][j] *= multiplier;
    }
}

// Função para multiplicar uma coluna por um número
void multiplyColumn(int matrix[][100], int col, int rows, int multiplier) {
    for (int i = 0; i < rows; i++) {
        matrix[i][col] *= multiplier;
    }
}

int main() {
    int matrix[100][100], rows, cols;
    
    // Solicitar o tamanho da matriz
    printf("Digite o número de linhas da matriz: ");
    scanf("%d", &rows);
    printf("Digite o número de colunas da matriz: ");
    scanf("%d", &cols);
    
    // Ler a matriz
    printf("Digite os elementos da matriz:\n");
    for (int i = 0; i < rows; i++) {
        for (int j = 0; j < cols; j++) {
            scanf("%d", &matrix[i][j]);
        }
    }
    
    // Exibir a matriz original
    displayMatrix(matrix, rows, cols);
    
    int choice, index, multiplier;
    printf("\nDigite 1 para multiplicar uma linha ou 2 para multiplicar uma coluna: ");
    scanf("%d", &choice);
    
    // Verificar a escolha do usuário
    switch (choice) {
        case 1:
            printf("Digite o número da linha que deseja multiplicar: ");
            scanf("%d", &index);
            printf("Digite o multiplicador: ");
            scanf("%d", &multiplier);
            // Multiplicar a linha
            multiplyRow(matrix, index - 1, cols, multiplier);
            break;
        case 2:
            printf("Digite o número da coluna que deseja multiplicar: ");
            scanf("%d", &index);
            printf("Digite o multiplicador: ");
            scanf("%d", &multiplier);
            // Multiplicar a coluna
            multiplyColumn(matrix, index - 1, rows, multiplier);
            break;
        default:
            printf("Escolha inválida.\n");
            return 1;
    }
    
    // Exibir a matriz modificada
    displayMatrix(matrix, rows, cols);
    
    return 0;
}

