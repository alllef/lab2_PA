package com.company;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

public class Ant {

    LinkedList path = new <Integer>LinkedList();
    static int alpha;
    static int beta;
    static int bestPathLength = Integer.MAX_VALUE;
    static int greedyPathLength;
    int pathLength = 0;

    void go() {

        int startTop = (int) (Math.random() * Rib.ribMatrix.length);
        path.add(startTop);

        while (path.size() < Rib.ribMatrix.length) {
            chooseRib();
        }

        pathLength += Rib.ribMatrix[(int) path.getFirst()][(int) path.getLast()].ribLength;
        int tmp = (int) path.getFirst();
        path.add(tmp);

    }

    void chooseRib() {

        int[] reachableTopsList = getReachableTopsList();
        Map<Integer, Double> transitionProbabilities = new HashMap<>(Rib.ribMatrix.length);
        double wholeFeromonLength = calculateWholeFeromonLength(reachableTopsList);
        double transitionProbabilityNumber = 0;

        for (Integer i : reachableTopsList) {

            double transitionProbability = getTransitionProbability(wholeFeromonLength, Rib.ribMatrix[(int) path.getLast()][i]);
            transitionProbabilityNumber += transitionProbability;
            transitionProbabilities.put(i, transitionProbability);

        }

        Integer nextTop = chooseTopRandomly(transitionProbabilities, reachableTopsList, transitionProbabilityNumber);
        pathLength += Rib.ribMatrix[(int) path.getLast()][nextTop].ribLength;
        path.add(nextTop);

    }

    int chooseTopRandomly(Map<Integer, Double> probabilities, int[] reachableTopsList, double transitionProbabilityNumber) {

        double randomNumber = (Math.random() * transitionProbabilityNumber);
        double tmpNumber = 0;


        for (Integer i : reachableTopsList) {

            tmpNumber += probabilities.get(i);
            if (tmpNumber > randomNumber) return i;
        }

        return -1;
    }

    double calculateWholeFeromonLength(int[] reachableTopsList) {

        double wholeFeromonLength = 0;

        for (Integer i : reachableTopsList) {
            wholeFeromonLength += calculateFeromonLength(Rib.ribMatrix[(int) path.getLast()][i]);
        }
        return wholeFeromonLength;
    }

    double getTransitionProbability(double sum, Rib rib) {
        return calculateFeromonLength(rib) / sum;
    }

    double calculateFeromonLength(Rib rib) {

        double eyeSide = (double) 1 / rib.ribLength;
        double feromon = rib.feromonQuantity;
        return Math.pow(feromon, alpha) * Math.pow(eyeSide, beta);
    }

    int[] getReachableTopsList() {
        LinkedList<Integer> reachableTopsList = new <Integer>LinkedList();

        for (int i = 0; i < Rib.ribMatrix.length; i++) {
            if (!path.contains(i)) reachableTopsList.add(i);
        }

        int[] tmpArr = new int[reachableTopsList.size()];


        for (int i = 0; i < tmpArr.length; i++) {
            tmpArr[i] = reachableTopsList.getFirst();
            reachableTopsList.removeFirst();
        }

        return tmpArr;
    }

    void putFeromones() {

        for (int i = 0; i < path.size() - 1; i++) {
            Rib.ribMatrix[(int) path.get(i)][(int) path.get(i + 1)].puttedFeromon += (double) greedyPathLength / pathLength;
        }

    }


    public static void main(String[] args) {
        for (int i = 0; i < 1000; i++) {
            System.out.println(Math.random() * 1.57);
        }

    }
}
