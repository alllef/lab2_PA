package com.company;

import java.io.*;

public class Rib implements Serializable {

    public static Rib[][] ribMatrix;
    int ribLength;
    int start;
    int end;
    double feromonQuantity;
    double puttedFeromon = 0;
    static double evaporationRate;



    Rib() {}

    Rib(int length, double feromonQuantity, int start, int end) {
        this.ribLength = length;
        this.feromonQuantity = feromonQuantity;
        this.start = start;
        this.end = end;
    }

    static void generateRibs(int ribsNumber, int maxLength, double starterFeromonList) {
        ribMatrix = new Rib[ribsNumber][ribsNumber];

        for (int i = 0; i < ribMatrix.length; i++) {
            for (int j = 0; j < ribMatrix[i].length; j++) {

                if (ribMatrix[j][i] == null && i != j) {
                    int randomLength = (int) (Math.random() * maxLength + 1);
                    ribMatrix[i][j] = new Rib(randomLength, starterFeromonList, i, j);
                } else if (ribMatrix[j][i] != null) ribMatrix[i][j] = ribMatrix[j][i];

            }
        }
    }

    static void initializeRibMatrix() {
        File file = new File("Rib.ser");
        if (file.exists()) deserializeRibMatrix();

        else {
            generateRibs(250, 40, 0.5);
            serializeRibMatrix();
        }
    }

    static void deserializeRibMatrix() {
        try {
            ObjectInputStream stream = new ObjectInputStream(new FileInputStream("Rib.ser"));
            try {
                ribMatrix = (Rib[][]) stream.readObject();
                stream.close();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    static void serializeRibMatrix() {
        try {
            ObjectOutputStream stream = new ObjectOutputStream(new FileOutputStream("Rib.ser"));
            stream.writeObject(ribMatrix);
            stream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    void calculateFeromon() {
        feromonQuantity = (1 - evaporationRate) * feromonQuantity + puttedFeromon;
        puttedFeromon = 0;
    }

    public static void main(String[] args) {


    }

}
