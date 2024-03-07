#include <stdio.h>

int main(int nArgs, char ** Args){



if(nArgs < 2){
        printf("%s: numero de paramentros insuficiente.\n", Args[0]);
        return 1;
}

FILE *fp = fopen(Args[1], "r");//SYSCALL

if(fp == NULL){
        printf("%s:erro na abertura do arquivo %s.\n", Args[0], Args[1]);
        return 2;
}


while(1){
        char c = fgetc(fp);
        if(c == EOF)
           break;
        printf("%c", c);
}


fclose(fp);
return 0;

}

