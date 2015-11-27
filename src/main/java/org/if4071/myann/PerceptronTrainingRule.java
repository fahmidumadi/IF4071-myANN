/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.if4071.myann;

import weka.classifiers.Classifier;
import weka.classifiers.Evaluation;
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
        do{
            epochError = 0;
            int target;
            for(int i=0; i < dataSet.numInstances();i++){
                Instance instance = dataSet.instance(i);
                if (instance.classValue() == 1){
                    target = 1;
                }else{
                    target = -1;
                }
                for(int j=0;j< topology.getWeights().size();j++){
                    Weight weight = topology.getWeights().get(j);
                    weight.getNode2().setInput(weight.getNode2().getInput()+ (weight.getNode1().getOutput() * weight.getWeight()));
                }
                outputNode.setInput(outputNode.getInput()+(outputNode.getBias()*outputNode.getBiasWeight()));
                int output = Node.sign(outputNode.getInput());
                for (int j = 0; j< topology.getWeights().size();j++){
                    Weight weight = topology.getWeights().get(j);
                    double delta = (topology.getLearningRate()*(target-output)*outputNode.getBias()) + 
                            topology.getMomentumRate()*outputNode.getPrevDeltaWeight();
                    weight.setPrevDeltaWeight(delta);
                    weight.setWeight(weight.getWeight()+delta);
                }
                double biasWeight = outputNode.getBiasWeight();
                double delta = (topology.getLearningRate() * (target - output) * outputNode.getBias()) +
                                topology.getMomentumRate()* outputNode.getPrevDeltaWeight();
                outputNode.setPrevDeltaWeight(delta);
                outputNode.setBiasWeight(biasWeight + delta);
                topology.resetNodesInput();
                for (int j = 0; j < topology.getWeights().size(); j++) {
                    Weight weight = topology.getWeights().get(j);
                    weight.getNode2().setInput(weight.getNode2().getInput() + (weight.getNode1().getOutput() * weight.getWeight()));
                }
                outputNode.setInput(outputNode.getInput() + (outputNode.getBias() * outputNode.getBiasWeight()));
                output = Node.sign(outputNode.getInput());
                int squaredError = (output-target)*(output-target);
                epochError += squaredError;
            }
            epochError = epochError / 2;
            loop++;
        }while((epochError > threshold) && 
                (!topology.isUseIterationTerminate() || (loop < topology.getIterationNumber())
                ));
    }
    
    @Override
    public double classifyInstance(Instance data) throws Exception{
        Instance instance = new Instance(data);
        nomToBinFilter.input(instance);
        instance = nomToBinFilter.output();
        normalizeFilter.input(instance);
        instance = normalizeFilter.output();

        topology.insertDataToInputNodes(instance);
        Node outputNode = topology.getOutputNode(0);
        topology.resetNodesInput();

        for (int j = 0; j < topology.getWeights().size(); j++) {
            Weight weight = topology.getWeights().get(j);
            weight.getNode2().setInput(weight.getNode2().getInput() + (weight.getNode1().getOutput() * weight.getWeight()));
        }
        outputNode.setInput(outputNode.getInput() + (outputNode.getBias() * outputNode.getBiasWeight()));
        int output = Node.sign(outputNode.getInput());
        if (output==1){
            return 1;
        }else{
            return 0;
        }
        /*public static void main(String [] args) throws Exception {
            Instances dataset = Util.readARFF("weather.numeric.arff");
            TopologyModel topology = new TopologyModel();
            topology.setLearningRate(0.1);
            topology.setInitWeightValues(0.0);
            topology.setMomentumRate(0.0);
            topology.setIterationNumber(500);
            PerceptronTrainingRule ptr = new PerceptronTrainingRule(topology);
            ptr.buildClassifier(dataset);
            Evaluation eval = Util.evaluateModel(ptr, dataset);
            System.out.println(eval.toSummaryString());
            System.out.println(eval.toMatrixString());
        }*/
    }
}
