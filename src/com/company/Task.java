package com.company;

import java.util.concurrent.Callable;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;

public class Task implements Callable <RowColumn>{

    int row[];
    int number;
    int position;

    public Task(int[] row, int number, int position) {
        this.row = row;
        this.number = number;
        this.position = position;
    }

    @Override
    public RowColumn call() throws InterruptedException {
        return searchNumber(row, number, position);
    }

    private RowColumn searchNumber(int row[], int number, int position) throws InterruptedException{
        for (int i = 0; i < row.length; i++) {
            TimeUnit.MILLISECONDS.sleep(ThreadLocalRandom.current().nextInt(500)+501);
            if(row[i] == number) {
                return new RowColumn(position, i);
            }
        }
        throw new RuntimeException("The number is not in the row.");
    }
}
