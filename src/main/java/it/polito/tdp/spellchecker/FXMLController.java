package it.polito.tdp.spellchecker;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;

import javafx.scene.control.TextArea;
import model.Dictionary;
import model.RichWord;

public class FXMLController {

	private Dictionary model;
	
    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;
    
    @FXML
    private ComboBox<String> cmbBox;

    @FXML
    private TextArea txt1;

    @FXML
    private TextArea txt2;

    @FXML
    void doClear(ActionEvent event) {
    	txt1.clear();
    	txt2.clear();
    }
   
    	@FXML
    	void doSpellCheck(ActionEvent event) {
    		model.loadDictionary(cmbBox.getValue() + ".txt");
    		String s = txt1.getText();
    		s.replaceAll("[.,\\/#!$%\\^&\\*;:{}=\\-_`~()\\[\\]\"?]", "");
    		String celle[] = s.split(" ");
    		List<String> l = new ArrayList<String>();
    		for (int i = 0; i < celle.length; i++) {
    			l.add(celle[i]);
    		}
    		List<RichWord> k = model.spellCheckText(l);
    		for (RichWord r : k) {
    			if (r.isCorretto() == false) {
    				txt2.appendText(r.toString() + "\n");
    			}
    		}
    	}

    	public void setModel(Dictionary model) {
    		this.model = model;
    	}

    @FXML
    void initialize() {
        assert cmbBox != null : "fx:id=\"cmbBox\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txt1 != null : "fx:id=\"txt1\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txt2 != null : "fx:id=\"txt2\" was not injected: check your FXML file 'Scene.fxml'.";
    	cmbBox.getItems().clear();
        cmbBox.getItems().addAll("Italian","English");
    }
        
    }

