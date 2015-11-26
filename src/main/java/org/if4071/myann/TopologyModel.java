/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.if4071.myann;

import java.util.ArrayList;
import weka.core.Instance;

/**
 *
 * @author Fahmi
 */
public class TopologyModel {
    private ArrayList<Node> nodes;
    private ArrayList<Integer> layers;
    private double initWeightValues;
    private int iterationNumber;
    private double errorThreshold;
    private double learningRate;
    private boolean givenWeight;
    private boolean useIterationTerminate;
    private boolean useErrorThresholdTerminate;
    
    
    public TopologyModel(){
        nodes = new ArrayList<>();
        layers = new ArrayList<>();
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
    public void setLayers(ArrayList<Integer> newLayers){
        layers = newLayers;
    }
    public ArrayList<Integer> getLayers(){
        return layers;
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
    public void addInputLayer(int n){
        layers.add(0,n);
    }
    public void addOutputLayer(int n){
        layers.add(layers.size(), n);
    }
    public void removeLayer(int indexLayer){
        layers.remove(indexLayer);
    }
    public void connectNodes(){
        createNodes();
    }
    
    private void setBiasValue(double bias){
        for (Node node : nodes) {
            node.setBias(bias);
        }
    }
    private void createNodes(){
        int id=0;
        for (Integer layer : layers) {
            for (int j = 0; j < layer; j++) {
                nodes.add(new Node(id));
                id++;
            }
        }
    }
    public void insertDataToInputNodes (Instance inputData){
        for(int i=0;i<inputData.numAttributes()-1;i++){
            nodes.get(i).setOutput(inputData.value(i));
        }
    }
    /*tanyain*/
    public void insertDataToOutputNodes (Instance inputData){
        int classValue = (int) inputData.classValue();
        for(int i=0;i<inputData.numClasses();i++){
            Node n = nodes.get(nodes.size()-layers.get(layers.size()-1)+i);
            if(i==classValue){
                n.setTarget(1);
            }
            else{
                n.setTarget(0);
            }
        }
    }
    public void resetNodesInput(){
        for(Node n : nodes){
            n.setInput(0);
        }
    }
    public Node getOutputNode(int n)
    {
        return nodes.get(nodes.size()-layers.get(layers.size()-1)+n);
    }
}
