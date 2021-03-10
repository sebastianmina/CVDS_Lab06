package edu.eci.cvds.calculator;


import java.util.ArrayList;
import java.util.Random;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ManagedBean;

@ManagedBean(name = "calculadoraBean")
@ApplicationScoped

/**
 * @author Luis Amaya
 * @author Sebastian Mina
 * @version 1.0 03/03/2021
 */
public class BackingBean {

    private int numeroRandom;
    private ArrayList<Integer> valores;
    private int media;
    private int desviacionEstandar;
    private int varianza;
    private int moda;
    private ArrayList<ArrayList<Integer>> valoresIngresados;

    public BackingBean(){
        restart();
    }
    public int getNumeroRandom() {
        return numeroRandom;
    }
    public ArrayList<ArrayList<Integer>> getValoresIngresados() {
        return valoresIngresados;
    }
    
    public int getMedia() {
        return media;
    }

    public int getDesviacionEstandar() {
        return desviacionEstandar;
    }
    public int getVarianza() {
        return varianza;
    }
    public int getModa() {
        return moda;
    }
    public void setMedia(int media) {
        this.media = media;
    }
    public void setModa(int moda) {
        this.moda = moda;
    }
    public void setDesviacionEstandar(int desviacionEstandar) {
        this.desviacionEstandar = desviacionEstandar;
    }
    public void setVarianza(int varianza) {
        this.varianza = varianza;
    }
    public void setNumeroRandom(int numeroRandom) {
        this.numeroRandom = numeroRandom;
    }
    public void setValoresIngresados(ArrayList<ArrayList<Integer>> valoresIngresados) {
        this.valoresIngresados = valoresIngresados;
    }

    private int createNumeroRandom(){
        Random random = new Random();
        int nuevoNumRand = random.nextInt(100);
        return nuevoNumRand;
    }

    public void calculate(String lista){
        valores = new ArrayList<Integer>();
        lista+=";";
        String valor = "";
        for(int i = 0; i < lista.length(); i++){
            if(lista.charAt(i) != ';'){
                valor += lista.charAt(i)+"";
            }else{
                valores.add(Integer.parseInt(valor));
                valor="";
            }
        }
        setMedia(calculateMean(valores));
        setDesviacionEstandar(calculateStandardDeviation(valores));
        setVarianza(calculateVariance(valores));
        setModa(calculateMode(valores));
        valoresIngresados.add(valores);
    }

    public int calculateMean(ArrayList<Integer> valores){
        int result = 0;
        for(int i = 0; i < valores.size(); i++){
            result += valores.get(i);
        }
        return result/valores.size();
    }

    public int calculateStandardDeviation(ArrayList<Integer> valores){
        int varianza = calculateVariance(valores);
        return (int) Math.pow(varianza, 0.5);
    }

    public int calculateVariance(ArrayList<Integer> valores){
        int promedio = calculateMean(valores);
        int sumatoria = 0;
        for(int i = 0; i < valores.size(); i++){
            sumatoria += Math.pow(valores.get(i)-promedio, 2);
        }
        return sumatoria/(valores.size());
    }

    public int calculateMode(ArrayList<Integer> valores){
        int moda = 0;
        int maxNumRep = 0;
        for(int i = 0; i< valores.size(); i++){
            int numRep=0;
            for (int j = 0; j < valores.size(); j++){
                if(valores.get(i) == valores.get(j)){
                    numRep++;
                }else if(numRep > maxNumRep){
                    moda = valores.get(i);
                    maxNumRep = numRep;
                }
            }
        }
        return moda;
    }
    
    public void restart(){
        valoresIngresados = new ArrayList<ArrayList<Integer>>();
        numeroRandom = createNumeroRandom();
        valores = new ArrayList<>();
        setMedia(0);
        setDesviacionEstandar(0);
        setModa(0);
        setVarianza(0);
    }
}
