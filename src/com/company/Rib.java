package com.company;

import java.util.Arrays;

public class Rib {

    public static Rib[][] ribMatrix;
    int length;
    int start;
    int end;
    double feromonQuantity;
    int ribCounter = 0;

    static {
        generateRibs(250, 40, new double[]{0.1, 0.2, 0.3});
    }

    Rib() {}

    Rib(int length, double feromonQuantity, int start, int end) {
        this.length = length;
        this.feromonQuantity = feromonQuantity;
        this.start = start;
        this.end = end;
    }

    static void generateRibs(int ribsNumber, int maxLength, double[] starterFeromonList) {
        ribMatrix = new Rib[ribsNumber][ribsNumber];

        for (int i = 0; i < ribMatrix.length; i++) {
            for (int j = 0; j < ribMatrix[i].length; j++) {

                if (ribMatrix[j][i] == null && i != j) {
                    int randomLength = (int) (Math.random() * maxLength + 1);
                    double randomQuantity = starterFeromonList[(int) (Math.random() * starterFeromonList.length)];
                    ribMatrix[i][j] = new Rib(randomLength, randomQuantity, i, j);
                } else if (ribMatrix[j][i] != null) ribMatrix[i][j] = ribMatrix[j][i];

            }
        }

    }

    public static void main(String[] args) {

    }

}
