/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.if4071.myann;

import java.util.ArrayList;

/**
 *
 * @author Fahmi
 */
public class TopologyModel {
    private ArrayList<Node> nodes;
    private double initWeightValues;
    private int iterationNumber;
    private double errorThreshold;
    private double learningRate;
    private boolean givenWeight;
    private boolean useIterationTerminate;
    private boolean useErrorThresholdTerminate;
    
    
    public TopologyModel(){
        nodes = new ArrayList<>();
        initWeightValues = 0.0;
        iterationNumber = 0;
        errorThreshold = 0.0;
        learningRate = 0.1;
        givenWeight = false;
        useIterationTerminate = false;
        useErrorThresholdTerminate = false;
    }
    public void setNodes(ArrayList<Node> newNodes){
        nodes = newNodes;
    }
    public ArrayList<Node> getNodes(){
        return nodes;
    }
    public void setInitWeightValues(double newInitWeightValues){
        initWeightValues = newInitWeightValues;
    }
    public double getInitWeightValues(){
        return initWeightValues;
    }
    public void setIterationNumber(int newIterationNumber){
        iterationNumber = newIterationNumber;
    }
    public int getIterationNumber(){
        return iterationNumber;
    }
    public void setErrorThreshold(double newErrorThreshold){
        errorThreshold = newErrorThreshold;
    }
    public double getErrorThreshold(){
        return errorThreshold;
    }
    public void setLearningRate(double newLearningRate){
        learningRate = newLearningRate;
    }
    public double getLearningRate(){
        return learningRate;
    }
    public void setGivenWeight(boolean givenWeight){
        this.givenWeight = givenWeight;
    }
    public boolean isGivenWeight(){
        return givenWeight;
    }
    public void setUseIterationTerminate(boolean useIterationTerminate){
        this.useIterationTerminate = useIterationTerminate;
    }
    public boolean isUseIterationTerminate(){
        return useIterationTerminate;
    }
    public void setUseErrorThresholdTerminate(boolean useErrorThresholdTerminate){
        this.useErrorThresholdTerminate = useErrorThresholdTerminate;
    }
    public boolean isUseErrorThresholdTerminate(){
        return useErrorThresholdTerminate;
    }
}
