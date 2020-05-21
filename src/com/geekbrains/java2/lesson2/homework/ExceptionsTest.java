package com.geekbrains.java2.lesson2.homework;

public class ExceptionsTest {
    public static void main(String[] args) {
        String[][] array = {{"1", "3", "5", "6"}, {"2", "3", "5", "7"}, {"1", "3", "5", "8"}, {"1", "3", "5", "9"}};
        try {
            int result = stringArraySum(array);
            System.out.printf("Sum of array is %d", result);
        } catch (MyArraySizeException | MyArrayDataException e) {
            e.printStackTrace();
        }
    }

    public static int stringArraySum(String[][] array) throws MyArraySizeException, MyArrayDataException {

        int SIZE = 4;

        if (!(array.length == SIZE)){
            throw MyArraySizeException.forSize(SIZE);
        } else {
            for (String[] item: array) {
                if (!(item.length == SIZE)) {
                    throw MyArraySizeException.forSize(SIZE);
                }
            }
        }

        int sum = 0;

        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array.length; j++) {
                try {
                    int number = Integer.parseInt(array[i][j]);
                    sum += number;
                } catch (NumberFormatException e) {
                    throw MyArrayDataException.forItem(i,j);
                }
            }
        }

        return sum;
    }
}

