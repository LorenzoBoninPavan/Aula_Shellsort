/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */
package Shellsort;

/**
 *
 * @author Costa e Silva
 */
import java.util.Random;
import java.util.Scanner;

public class Shellsort {

    public static String gerarPlacaAleatoria() {
        Random rand = new Random();
        String letras = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String numeros = "0123456789";

        String parte1 = "" + letras.charAt(rand.nextInt(26))
                + letras.charAt(rand.nextInt(26))
                + letras.charAt(rand.nextInt(26));
        String digito1 = "" + numeros.charAt(rand.nextInt(10));
        String letra2 = "" + letras.charAt(rand.nextInt(26));
        String digito2 = "" + numeros.charAt(rand.nextInt(10)) + numeros.charAt(rand.nextInt(10));

        return parte1 + "-" + digito1 + letra2 + digito2;
    }

    public static void shellSort(String[] array) {
        int n = array.length;
        for (int gap = n / 2; gap > 0; gap /= 2) {
            for (int i = gap; i < n; i++) {
                String temp = array[i];
                int j;
                for (j = i; j >= gap && array[j - gap].compareTo(temp) > 0; j -= gap) {
                    array[j] = array[j - gap];
                }
                array[j] = temp;
            }
        }
    }

    public static void imprimirArray(String[] array) {
        for (String s : array) {
            System.out.println(s);
        }
    }

    public static String[] gerarLista(int tamanho) {
        String[] placas = new String[tamanho];
        for (int i = 0; i < tamanho; i++) {
            placas[i] = gerarPlacaAleatoria();
        }
        return placas;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\nEscolha o tamanho da lista:");
            System.out.println("1 - 50 placas");
            System.out.println("2 - 5.000 placas");
            System.out.println("3 - 50.000 placas");
            System.out.println("0 - Sair");
            int escolhaTamanho = scanner.nextInt();

            if (escolhaTamanho == 0) {
                System.out.println("Encerrando o programa.");
                break;
            }

            int tamanho;
            switch (escolhaTamanho) {
                case 1:
                    tamanho = 50;
                    break;
                case 2:
                    tamanho = 5000;
                    break;
                case 3:
                    tamanho = 50000;
                    break;
                default:
                    System.out.println("Opção inválida.");
                    continue;
            }

            String[] placas = gerarLista(tamanho);

            System.out.println("\nDeseja ver a lista desordenada? (s/n)");
            String verDesordenada = scanner.next();

            if (verDesordenada.equalsIgnoreCase("s")) {
                System.out.println("\nLista desordenada:");
                imprimirArray(placas);
            }

            System.out.println("\nDeseja ordenar a lista? (s/n)");
            String ordenar = scanner.next();

            if (ordenar.equalsIgnoreCase("s")) {
                long inicio = System.nanoTime();
                shellSort(placas);
                long fim = System.nanoTime();
                double tempoSegundos = (fim - inicio) / 1_000_000_000.0;
                System.out.printf("Tempo de ordenação: %.4f segundos%n", tempoSegundos);

                System.out.println("\nDeseja ver a lista ordenada? (s/n)");
                String verOrdenada = scanner.next();

                if (verOrdenada.equalsIgnoreCase("s")) {
                    System.out.println("\nLista ordenada:");
                    imprimirArray(placas);
                }
            }
        }

        scanner.close();
    }
}
