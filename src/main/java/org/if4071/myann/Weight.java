/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.if4071.myann;

import java.util.Random;

/**
 *
 * @author Fahmi
 */
public class Weight {
    /*ATTRIBUTE*/
    private Node n1;
    private Node n2;
    private double weight;
    private double prevDeltaWeight;
    /*METHOD*/
    public Weight(){
        n1 = new Node();
        n2 = new Node();
        weight = randomWeight();
        prevDeltaWeight = 0;
    }
    public Weight(Node newN1, Node newN2){
        n1 = newN1;
        n2 = newN2;
        weight = randomWeight();
        prevDeltaWeight = 0;
    }
    public Weight(Node newN1, Node newN2, double newWeight){
        n1 = newN1;
        n2 = newN2;
        weight = newWeight;
        prevDeltaWeight = 0;
    }
    public void setNode1(Node newNode){
        n1 = newNode;
    }
    public Node getNode1(){
        return n1;
    }
    public void setNode2(Node newNode){
        n2 = newNode;
    }
    public Node getNode2(){
        return n2;
    }
    public void setWeight(double newWeight){
        weight = newWeight;
    }
    public double getWeight(){
        return weight;
    }
    public void setPrevDeltaWeight(double deltaWeight){
        prevDeltaWeight = deltaWeight;
    }
    public double getPrevDeltaWeight(){
        return prevDeltaWeight;
    }
    private double randomWeight()
    {
        double rangeMin = -0.05;
        double rangeMax = 0.05;
        Random r = new Random();

        double newWeight = rangeMin + (rangeMax - rangeMin) * r.nextDouble();
        return newWeight;
    }
}
