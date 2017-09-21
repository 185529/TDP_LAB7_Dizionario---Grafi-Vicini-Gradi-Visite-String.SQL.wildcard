package it.polito.tdp.dizionario.controller;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import it.polito.tdp.dizionario.model.Degree;
import it.polito.tdp.dizionario.model.Model;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class DizionarioController {

	@FXML
	private ResourceBundle resources;
	@FXML
	private URL location;
	@FXML
	private TextArea txtResult;
	@FXML
	private TextField inputNumeroLettere;
	@FXML
	private TextField inputParola;
	@FXML
	private Button btnGeneraGrafo;
	@FXML
	private Button btnTrovaVicini;
	@FXML
	private Button btnTrovaGradoMax;
	
	private Model model;
	
	@FXML // fx:id="btnAllNeighbours"
    private Button btnAllNeighbours; // Value injected by FXMLLoader

    @FXML
    void doAllNeighbours(ActionEvent event) {
    	
    	txtResult.clear();
		
		int length = Integer.parseInt(inputNumeroLettere.getText());
		
		String word = inputParola.getText();
		
		if(word == null || word.isEmpty() || word.length() != length){
			txtResult.setText("ERROR: Insert a word with the specified length.");
			return;
		}
		
		List<String> reachable = new ArrayList<String>();
		
		reachable = model.getAllNeighbours(word);
		
		if(reachable.isEmpty()){
			txtResult.appendText("No neighbours found.");
			return;
		}
		
		for(String s : reachable){
			txtResult.appendText(s+"\n");
		}

    }

	@FXML
	void doReset(ActionEvent event) {

		inputParola.clear();
		inputNumeroLettere.clear();
		txtResult.clear();
		
		inputNumeroLettere.setDisable(false);
		inputParola.setDisable(true);
		btnTrovaVicini.setDisable(true);
		btnTrovaGradoMax.setDisable(true);
		btnAllNeighbours.setDisable(true);
		btnGeneraGrafo.setDisable(false);
	
	}

	@FXML
	void doGeneraGrafo(ActionEvent event) {

		Long start = System.nanoTime();
		
		txtResult.clear();
		
		int length;
		
		try {
			
			length = Integer.parseInt(inputNumeroLettere.getText());
			
		} catch (NumberFormatException e) {
			
			txtResult.setText("ERROR: Insert a number for word length.");
			return;
			
		}
		
		model.createGraph(length);
		
		inputNumeroLettere.setDisable(true);
		btnGeneraGrafo.setDisable(true);

		inputParola.setDisable(false);
		btnTrovaVicini.setDisable(false);
		btnTrovaGradoMax.setDisable(false);
		btnAllNeighbours.setDisable(false);
		
		Long stop = System.nanoTime();
		
		txtResult.setText("Graph succesfully created in "+(stop-start)/1e9+" seconds.");
		
	}

	@FXML
	void doTrovaGradoMax(ActionEvent event) {
		
		Degree degreeMax = new Degree();
		
		degreeMax = model.findMaxDegree();
		
		txtResult.setText("Max degree vertex is "+degreeMax.getVertex()+" with "+degreeMax.getDegree()+" neighbours:\n\n");
		
		List<String> neighbours = model.displayNeighbours(degreeMax.getVertex());
			
		for(String s : neighbours){
			txtResult.appendText(s+"\n");
		}
		
	}

	@FXML
	void doTrovaVicini(ActionEvent event) {
		
		txtResult.clear();
		
		int length = Integer.parseInt(inputNumeroLettere.getText());
		
		String word = inputParola.getText();
		
		if(word == null || word.isEmpty() || word.length() != length){
			txtResult.setText("ERROR: Insert a word with the specified length.");
			return;
		}
		
		List<String> neighbours;
		
		try {
			
			neighbours = model.displayNeighbours(word);
			
			for(String s : neighbours){
				txtResult.appendText(s+"\n");
			}
			
		} catch (IllegalArgumentException e) {
			txtResult.setText("Word not found.");
		}
				
	}

	@FXML
	void initialize() {
		assert txtResult != null : "fx:id=\"txtResult\" was not injected: check your FXML file 'Dizionario.fxml'.";
		assert inputNumeroLettere != null : "fx:id=\"inputNumeroLettere\" was not injected: check your FXML file 'Dizionario.fxml'.";
		assert inputParola != null : "fx:id=\"inputParola\" was not injected: check your FXML file 'Dizionario.fxml'.";
		assert btnGeneraGrafo != null : "fx:id=\"btnGeneraGrafo\" was not injected: check your FXML file 'Dizionario.fxml'.";
		assert btnTrovaVicini != null : "fx:id=\"btnTrovaVicini\" was not injected: check your FXML file 'Dizionario.fxml'.";
		assert btnTrovaGradoMax != null : "fx:id=\"btnTrovaTutti\" was not injected: check your FXML file 'Dizionario.fxml'.";
        assert btnAllNeighbours != null : "fx:id=\"btnAllNeighbours\" was not injected: check your FXML file 'Dizionario.fxml'.";

	}

	public void setModel(Model model) {
		
		this.model = model;
		
		inputParola.setDisable(true);
		
		btnTrovaVicini.setDisable(true);
		btnTrovaGradoMax.setDisable(true);
		btnAllNeighbours.setDisable(true);
		
	}
}