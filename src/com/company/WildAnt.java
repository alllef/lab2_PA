package com.company;

import java.util.ArrayList;

public class WildAnt extends Ant {

    @Override
    void chooseRib() {

        ArrayList<Integer> reachableTopsList = new <Integer>ArrayList();

        for (int i = 0; i < Rib.ribMatrix.length; i++) {
            if (!path.contains(i)) reachableTopsList.add(i);
        }

        Integer nextTop = reachableTopsList.get((int) (Math.random()*reachableTopsList.size()));
        pathLength += Rib.ribMatrix[(int) path.getLast()][nextTop].ribLength;
        path.add(nextTop);
    }

}
