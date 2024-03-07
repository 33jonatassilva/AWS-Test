#include <stdio.h>

int main(){
        FILE *fp = fopen("Entrada.txt", "r");

        if(fp == NULL){
                printf("Erro ao abrir o arquivo.\n");
                return 0;
        } 
        printf("Arquivo lido com sucesso!\n");
        return 1;

}

