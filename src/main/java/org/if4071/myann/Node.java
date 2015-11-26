/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.if4071.myann;

/**
 *
 * @author Fahmi
 */
public class Node {
    /**
     * id Node
     */
    private int id;
    /**
     * input untuk Node
     */
    private double input;
    /**
     * output dari Node
     */
    private double output;
    /**
     * target output dari node
     */
    private double target;
    /**
     * nilai error pada output
     */
    private double error;
    /**
     * nilai bias pada node, default bias = 1;
     */
    private double bias;
    /**
     * bobot nilai bias
     */
    private double biasWeight;
    
    public Node(){
        id = 0;
        input = 0;
        output = 0;
        target = 0;
        error = 0;
        bias = 1;
        biasWeight = 0;
    }
    public Node(int id){
        this.id = id;
        input = 0;
        output = 0;
        target = 0;
        error = 0;
        bias = 1;
        biasWeight = 0;
    }
    public void setID(int newID){
        id = newID;
    }
    public int getID(){
        return id;
    }
    public void setInput(double newInput){
        input = newInput;
    }
    public double getInput(){
        return input;
    }
    public void setOutput(double newOutput){
        output = newOutput;
    }
    public double getOutput(){
        return output;
    }
    public void setTarget(double newTarget){
        target = newTarget;
    }
    public double getTarget(){
        return target;
    }
    public void setError(double newError){
        error = newError;
    }
    public double getError(){
        return error;
    }
    public void setBias(double newBias){
        bias = newBias;
    }
    public double getBias(){
        return bias;
    }
    public void setBiasWeight(double newBiasWeight){
        biasWeight = newBiasWeight;
    }
    public double getBiasWeight(){
        return biasWeight;
    }
    public int sign(double x){
        if(x>=0){
            return 1;
        }
        else{
            return -1;
        }
    }
    public double sigmoid(double x){
        return (1 / (1+(Math.exp(-x))));
    }
    @Override
    public String toString() {
        StringBuffer sb = new StringBuffer();
        sb.append("Node ").append(id).append("\n");
        sb.append("input: ").append(input).append("\n");
        sb.append("output: ").append(output).append("\n");
        sb.append("target: ").append(target).append("\n");
        sb.append("error: ").append(error).append("\n");
        return sb.toString();
    }
}
