/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package br.nom.abdon.genetica;

import java.util.Random;

/**
 *
 * @author Bruno
 */
public class Deus {

    private static Random r = new Random();
    
    private static final int PRECISAO = 1000;
    
    static boolean randomBoolean(double percentaualDeChance) {
        return r.nextInt(100*PRECISAO) <= percentaualDeChance * PRECISAO;
    }
    
    public static void main(String[] args) {
        for (int i = 0; i < 120; i++) {
            System.out.println(i + " -> " + Deus.randomBoolean(i));
        }
    }
}
