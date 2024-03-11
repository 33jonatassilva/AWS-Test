#include <stdio.h>

int main()
{
    
    float a,b,c,d; 
    
    printf("Primeira nota: ");
    scanf("%f", &a);
    
    printf("Segunda nota: ");
    scanf("%f", &b);
    
    printf("Terceira nota: ");
    scanf("%f", &c);
    
    printf("Média das atividades: ");
    scanf("%f", &d);
    
    float ma = (a+ 2*b + 3*c +d)/7;
    
    printf("\nMédia de aproveitamento: %f", ma);
    
    if(ma>=9){
        printf("\nConceito: A");
    }
    else if(ma >= 7.5){
        printf("\nConceito: B");
    }
    else if(ma >= 6){
        printf("\nConceito: C");
    }
    else if(ma >= 4){
        printf("\nConceito: D");
    }
    else{
        printf("\nConceito: E");
    }

    return 0;
}

