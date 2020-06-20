/**
 * Sample Skeleton for 'Food.fxml' Controller Class
 */

package it.polito.tdp.food;

import java.net.URL;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import it.polito.tdp.food.model.Model;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class FoodController {
	
	private Model model;

    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML // fx:id="txtCalorie"
    private TextField txtCalorie; // Value injected by FXMLLoader

    @FXML // fx:id="txtPassi"
    private TextField txtPassi; // Value injected by FXMLLoader

    @FXML // fx:id="btnAnalisi"
    private Button btnAnalisi; // Value injected by FXMLLoader

    @FXML // fx:id="btnCorrelate"
    private Button btnCorrelate; // Value injected by FXMLLoader

    @FXML // fx:id="btnCammino"
    private Button btnCammino; // Value injected by FXMLLoader

    @FXML // fx:id="boxPorzioni"
    private ComboBox<String> boxPorzioni; // Value injected by FXMLLoader

    @FXML // fx:id="txtResult"
    private TextArea txtResult; // Value injected by FXMLLoader

    @FXML
    void doCammino(ActionEvent event) {
    	txtResult.clear();
    	String s= this.boxPorzioni.getValue();
    	String input= this.txtPassi.getText();
    	if(!input.matches("[0-9]+")) {
    		txtResult.appendText("Devi inserire un valore numerico intero");
    		return;
    	}
    	if(s==null) {
    		txtResult.appendText("Devi prima creare un grafo!");
    		return;
    	}
    	int n= Integer.parseInt(input);
    	List<String> cammino= this.model.camminoMinimo(n, s);
    	if(model.pesoMax()==-1) {
    		txtResult.appendText("Non ho trovato un cammino di lunghezza N"+"\n");
    		return;
    	}
    	txtResult.appendText("cammino di peso massimo con "+cammino.size()+" vertici percorsi e peso "+this.model.pesoMax()+"\n");
    	for(String a: cammino) {
    		txtResult.appendText(a+"\n");
    	}
    	
    }

    @FXML
    void doCorrelate(ActionEvent event) {
    	txtResult.clear();
    	String s= this.boxPorzioni.getValue();
    	if(s==null) {
    		txtResult.appendText("Devi prima creare un grafo!");
    		return;
    	}
    	Map<String, Double> connessi= this.model.connessi(s);
    	for(String a: connessi.keySet()) {
    		txtResult.appendText("Porzione: "+ a+ " peso "+connessi.get(a)+"\n");
    	}
    	
    	
    }

    @FXML
    void doCreaGrafo(ActionEvent event) {
    	txtResult.clear();
    	this.boxPorzioni.getItems().remove(0, this.boxPorzioni.getItems().size()-1);
    	String input= this.txtCalorie.getText();
    	if(!input.matches("^[1-9]\\d*(\\.\\d+)?$")) {
    		txtResult.appendText("Devi inserire un valore numerico");
    		return;
    	}
    	int c= Integer.parseInt(input);
    	this.model.creaGrafo(c);
    	txtResult.appendText("Grafo creato con #vertici: "+model.vertici()+" #archi: "+model.archi());
    	this.boxPorzioni.getItems().addAll(this.model.listVertici(c));
    	
    }

    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
        assert txtCalorie != null : "fx:id=\"txtCalorie\" was not injected: check your FXML file 'Food.fxml'.";
        assert txtPassi != null : "fx:id=\"txtPassi\" was not injected: check your FXML file 'Food.fxml'.";
        assert btnAnalisi != null : "fx:id=\"btnAnalisi\" was not injected: check your FXML file 'Food.fxml'.";
        assert btnCorrelate != null : "fx:id=\"btnCorrelate\" was not injected: check your FXML file 'Food.fxml'.";
        assert btnCammino != null : "fx:id=\"btnCammino\" was not injected: check your FXML file 'Food.fxml'.";
        assert boxPorzioni != null : "fx:id=\"boxPorzioni\" was not injected: check your FXML file 'Food.fxml'.";
        assert txtResult != null : "fx:id=\"txtResult\" was not injected: check your FXML file 'Food.fxml'.";

    }
    
    public void setModel(Model model) {
    	this.model = model;
    }
}
