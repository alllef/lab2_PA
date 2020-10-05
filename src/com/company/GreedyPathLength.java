package com.company;

import java.util.LinkedList;

public class GreedyPathLength {

    static  int getGreedyPathLength() {

       int greedyPathLength = 0;
        LinkedList path = new <Integer>LinkedList();
        LinkedList lengthes = new <Integer>LinkedList();

        int startTop = (int) (Math.random() * Rib.ribMatrix.length);
        path.add(startTop);

        LinkedList<Integer> reachableTopsList = new <Integer>LinkedList();

        for (int i = 0; i < Rib.ribMatrix.length; i++) {
            if (!path.contains(i)) reachableTopsList.add(i);
        }

        while (path.size() < Rib.ribMatrix.length) {

            for (int i = 0; i < Rib.ribMatrix.length; i++) {
                if (!path.contains(i)) reachableTopsList.add(i);
            }

            int number = Integer.MAX_VALUE;
            int next = -1;

            for (int i = 0; i < reachableTopsList.size(); i++) {
                //System.out.println(path.getLast()+ " "+reachableTopsList.get(i));
                if (Rib.ribMatrix[(int) path.getLast()][reachableTopsList.get(i)].ribLength < number) {
                    number = Rib.ribMatrix[(int) path.getLast()][reachableTopsList.get(i)].ribLength;
                    next = reachableTopsList.get(i);
                }
            }
            lengthes.add(number);
            greedyPathLength+=number;
            path.add(next);
            reachableTopsList.clear();
        }

        greedyPathLength += Rib.ribMatrix[(int) path.getFirst()][(int) path.getLast()].ribLength;
        path.add(path.getFirst());
        return greedyPathLength;
    }
}
