package com.company;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

public class Main {

    public static void main(String[] args) {

        final int NUMBER_OF_CORES = 8;
        ThreadPoolExecutor fixedThreadPool = (ThreadPoolExecutor) Executors.newFixedThreadPool(NUMBER_OF_CORES);
        List<Future<Integer>> futureList;
        List<Task> taskList = new ArrayList<>();
        int matrice[][] = new int[5][5];
        int numberSearch = ThreadLocalRandom.current().nextInt(11) + 1;

        for (int i = 0; i < matrice.length; i++) {
            for (int j = 0; j < matrice[i].length; j++) {
                matrice[i][j] = ThreadLocalRandom.current().nextInt(10) + 1;
            }
            taskList.add(new Task(matrice[i], numberSearch, i));
        }

        System.out.println("La matriz es la siguiente:");
        for (int i = 0; i < matrice.length; i++) {
            for (int j = 0; j < matrice[i].length; j++) {
                if (matrice[i][j] >= 10){
                    System.out.printf("%d ", matrice[i][j]);
                } else {
                    System.out.printf("%d  ", matrice[i][j]);
                }
            }
            System.out.println();
        }

        System.out.println(numberSearch);

        try {
            RowColumn rowColumn = fixedThreadPool.invokeAny(taskList);
            System.out.printf("Se ha encontrado el número %s en la fila %s y en la columna %s",
                                numberSearch, rowColumn.getRow() + 1, rowColumn.getColumn() + 1);

        } catch (InterruptedException ignored) {
        } catch (ExecutionException e) {
            System.out.printf("El número %s no aparece en la matriz.", numberSearch);
        } finally {
            fixedThreadPool.shutdown();
        }

    }
}
