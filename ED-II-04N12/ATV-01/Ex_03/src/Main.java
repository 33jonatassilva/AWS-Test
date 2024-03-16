//Proposta de enunciado: Construa um código em Java que gera um número inteiro aleatório e utiliza uma Pilha para converte-lo em binário. Depois imprima o número e sua versão binária.

//Resolução:

import java.util.Stack;

public class Main {
    public static void main(String[] args) {
        //inicializa esquerda, direita e centro
        Stack<Integer> numBinario = new Stack<Integer>();

        int num = (int) (Math.random() *1000);
        int aux = num;

        while (aux > 0){
            numBinario.push(aux % 2);
            aux = aux / 2;}
        System.out.println("O número " + num + " em binário: ");
        while(!numBinario.isEmpty()) System.out.print(numBinario.pop());

    }}