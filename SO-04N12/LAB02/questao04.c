#include<stdio.h>
int main(void)
{
  char nome1[50], nome2[50];
 
  printf("\nDigite o primeiro nome: ");
  scanf("%s",nome1);
  printf("Digite o segundo nome: ");
  scanf("%s",nome2);
  
  printf("****NOMES EM ORDEM ALFABÉTICA****\n");
  if(nome1[0] > nome2[0]){
      printf(" - %s\n - %s\n", nome2,nome1);
  }
  else{
      printf(" - %s\n - %s\n", nome1,nome2);
  }
  
  return 0;
}

