package it.polito.tdp.poweroutages;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import it.polito.tdp.poweroutages.model.Model;
import it.polito.tdp.poweroutages.model.Nerc;
import it.polito.tdp.poweroutages.model.Blackout;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class FXMLController {
	
	private Model model;

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private ComboBox<Nerc> nercBox;

    @FXML
    private TextField txtY;

    @FXML
    private TextField txtH;

    @FXML
    private Button btnAnalysis;

    @FXML
    private TextArea txtResult;

    @FXML
    void doAnalysis(ActionEvent event) {
    	
    	int maxY = 0;
    	int maxH = 0;
    	
    	if(txtY.getText().equals("")) {
    		txtResult.setText("Inserire un numero massimo di anni!");
    		return;
    	}
    	
    	if(txtH.getText().equals("")) {
    		txtResult.setText("Inserire un numero massimo di ore!");
    		return;
    	}
    	
    	if(nercBox.getValue()==null) {
    		txtResult.setText("Selezionare un NERC!");
    		return;
    	}
    	
    	try {
    		maxY = Integer.parseInt(txtY.getText());
    	}catch(NumberFormatException ne){
    		txtResult.setText("Puoi inserire solamente caratteri numerici!");
    	}
    	
    	try {
    		maxH = Integer.parseInt(txtH.getText());
    	}catch(NumberFormatException ne){
    		txtResult.setText("Puoi inserire solamente caratteri numerici!");
    	}
    	
    	List<Blackout> soluzione = new ArrayList(model.getAnalysis(nercBox.getValue(), maxY, maxH));
    	
    	
    	if(soluzione.size()==0) {
    		txtResult.setText("Non sono presenti blackout per i valori selezionati!");
    		return;
    	}
    	
    	txtResult.clear();
    	txtResult.appendText("Tot people affected: "+model.getBestSomma()+"\n");
    	txtResult.appendText("Tot hours of outage: "+model.getSommaOre(soluzione)+"\n");
    	for(Blackout b: soluzione) {
    		txtResult.appendText(b.toString()+"\n");
    	}

    }

    @FXML
    void initialize() {
        assert nercBox != null : "fx:id=\"nercBox\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtY != null : "fx:id=\"txtY\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtH != null : "fx:id=\"txtH\" was not injected: check your FXML file 'Scene.fxml'.";
        assert btnAnalysis != null : "fx:id=\"btnAnalysis\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtResult != null : "fx:id=\"txtResult\" was not injected: check your FXML file 'Scene.fxml'.";

    }
    
    public void setModel(Model model) {
    	this.model = model;
    	nercBox.getItems().add(null);
    	nercBox.getItems().addAll(model.getNercList());
    	
    }
}
