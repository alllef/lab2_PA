package com.company;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

public class Ant {

    LinkedList path = new <Integer>LinkedList();
    static int alpha;
    static int beta;
    static int bestPathLength;
    int pathLength = 0;

    static {
        alpha = 0;
        beta = 0;
        //bestPathLength = calculateGreedyPath();
    }

    void go() {
        int startTop = (int) (Math.random() * Rib.ribMatrix.length);
        path.add(startTop);

        while (path.size() < Rib.ribMatrix.length) {
            chooseRib();
        }

        pathLength+= Rib.ribMatrix[(int) path.getFirst()][(int) path.getLast()].length;
        path.add(path.getFirst());
        Rib.ribMatrix[(int) path.getFirst()][(int) path.getLast()].ribCounter++;
    }

    void chooseRib() {
        LinkedList<Integer> reachableTopsList = new <Integer>LinkedList();

        for (int i = 0; i < Rib.ribMatrix.length; i++) {
            if (!path.contains(i)) reachableTopsList.add(i);
        }

        Map<Integer, Double> revertedTransitionProbabilities = new HashMap<Integer, Double>();


        for (Integer i : reachableTopsList) {

            double revertedTransitionProbability = 1 / getTransitionProbability(reachableTopsList, Rib.ribMatrix[(int) path.getLast()][i]);
            revertedTransitionProbabilities.put(i, revertedTransitionProbability);

        }

        Integer nextTop = chooseTopRandomly(revertedTransitionProbabilities);
        pathLength += Rib.ribMatrix[(int) path.getLast()][nextTop].length;
        Rib.ribMatrix[(int) path.getLast()][nextTop].ribCounter++;
        path.add(nextTop);

    }

    int chooseTopRandomly(Map<Integer, Double> probabilities) {
        double transtionProbabilityNumber = 0;

        for (Map.Entry<Integer, Double> i : probabilities.entrySet()) {
            transtionProbabilityNumber += i.getKey();
        }

        double randomNumber = Math.random() * transtionProbabilityNumber;
        int tmpNumber = 0;

        for (Map.Entry<Integer, Double> i : probabilities.entrySet()) {
            tmpNumber += i.getValue();
            if (randomNumber < tmpNumber) return i.getKey();
        }

        return -1;
    }

    double calculateWholeFeromonLength(LinkedList<Integer> reachableTopsList) {
        double wholeFeromonLength = 0;

        for (Integer i : reachableTopsList) {
            wholeFeromonLength += calculateFeromonLength(Rib.ribMatrix[(int) path.getLast()][i]);
        }
        return wholeFeromonLength;
    }

    double getTransitionProbability(LinkedList<Integer> reachableTopsList, Rib rib) {
        return calculateFeromonLength(rib) / calculateWholeFeromonLength(reachableTopsList);
    }

    double calculateFeromonLength(Rib rib) {

        double eyeSide = 1 / rib.length;
        double feromon = rib.feromonQuantity;
        return Math.pow(feromon, alpha) * Math.pow(eyeSide, beta);

    }

}
