/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.if4071.myann;

import weka.classifiers.Classifier;
import weka.core.Capabilities;
import weka.core.Instance;
import weka.core.Instances;
import weka.filters.Filter;
import weka.filters.unsupervised.attribute.NominalToBinary;
import weka.filters.unsupervised.attribute.Normalize;

/**
 *
 * @author Fahmi
 */
public class PerceptronTrainingRule extends Classifier{
    /*ATTRIBUTE*/
    private Instances dataSet;
    private TopologyModel topology;
    private NominalToBinary nomToBinFilter;
    private Normalize normalizeFilter;
    /*METHOD*/
    public PerceptronTrainingRule(){
        topology = new TopologyModel();
        nomToBinFilter = new NominalToBinary();
        normalizeFilter = new Normalize();
    }
    public PerceptronTrainingRule(TopologyModel newTopology){
        topology = newTopology;
        nomToBinFilter = new NominalToBinary();
        normalizeFilter = new Normalize();
    }
    public void setTopology(TopologyModel newTopology){
        topology = newTopology;
    }
    public TopologyModel getTopology(){
        return topology;
    }
    public void setDataSet(Instances newDataSet){
        dataSet = newDataSet;
    }
    public Instances getDataSet(){
        return dataSet;
    }
    @Override
    public Capabilities getCapabilities(){
        Capabilities result = super.getCapabilities();
        result.disableAll();

        result.enable(Capabilities.Capability.NOMINAL_ATTRIBUTES);
        result.enable(Capabilities.Capability.NUMERIC_ATTRIBUTES);
        result.enable(Capabilities.Capability.BINARY_CLASS);
        
        return result;
    }
    @Override
    public void buildClassifier(Instances data) throws Exception{
        getCapabilities().testWithFail(data);
        dataSet = new Instances(data);
        for(int i=0;i<dataSet.numAttributes();i++){
            dataSet.deleteWithMissing(i);
        }
        //filterNominal
        nomToBinFilter.setInputFormat(dataSet);
        dataSet = Filter.useFilter(data, nomToBinFilter);
        normalizeFilter.setInputFormat(dataSet);
        dataSet = Filter.useFilter(data, normalizeFilter);
        //setTopology
        topology.addInputLayer(dataSet.numAttributes()-1);
        topology.addOutputLayer(1);
        topology.connectNodes();
        Node outputNode = topology.getOutputNode(0);
        outputNode.setBiasWeight(0.0);
        double threshold = 0.0;
        if(topology.isUseErrorThresholdTerminate()){
            threshold = topology.getErrorThreshold();
        }
        int epochError;
        int loop = 0;
    }
    
    @Override
    public double classifyInstance(Instance data){
        //ganti output nanti
        int output = 0;
        return output;
    }
}
