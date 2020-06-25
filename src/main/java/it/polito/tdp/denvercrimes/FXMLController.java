package it.polito.tdp.denvercrimes;

import java.net.URL;
import java.util.ResourceBundle;

import it.polito.tdp.denvercrimes.model.Model;
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
    private ComboBox<Integer> boxAnno;

    @FXML
    private ComboBox<Integer> boxMese;

    @FXML
    private ComboBox<Integer> boxGiorno;

    @FXML
    private Button btnCreaReteCittadina;

    @FXML
    private Button btnSimula;

    @FXML
    private TextField txtN;

    @FXML
    private TextArea txtResult;

    @FXML
    void doCreaReteCittadina(ActionEvent event) {
    	txtResult.clear();
    	if(boxAnno.getValue()==null) 
    		txtResult.appendText("seleziona un anno");
    	int anno =0;
    	anno= boxAnno.getValue();
    	model.creaGrafo(anno);
    	txtResult.appendText("Numero Vertici "+ model.getVertex().size() +"\nNumero Archi "+model.getEdge()+"\n");
    	for(Integer i : model.getVertex()) {
    		for(int z=0;z<model.getVicini(i).size();z++) {
    			txtResult.appendText(model.getVicini(i).get(z).getVicino()+"    " +model.getVicini(i).get(z).getDistanza()+"\n");
    		}
    		txtResult.appendText("\n\n\n");
    	}
    	boxMese.getItems().addAll(model.getMonths(anno));
    	boxGiorno.getItems().addAll(model.getDay(anno, 1));
    	

    }

    @FXML
    void doSimula(ActionEvent event) {
    	int n=0;
    	try {
			n= Integer.parseInt(txtN.getText());
		} catch (NumberFormatException nfe) {
			txtResult.appendText("inserire un numero intero tra 1 e 10");
			return;
		}
    	
    	if(n<1 && n>10)
    		txtResult.appendText("Inserisci un numero compreso tra 1 e 10");

    }

    @FXML
    void initialize() {
        assert boxAnno != null : "fx:id=\"boxAnno\" was not injected: check your FXML file 'Crimes.fxml'.";
        assert boxMese != null : "fx:id=\"boxMese\" was not injected: check your FXML file 'Crimes.fxml'.";
        assert boxGiorno != null : "fx:id=\"boxGiorno\" was not injected: check your FXML file 'Crimes.fxml'.";
        assert btnCreaReteCittadina != null : "fx:id=\"btnCreaReteCittadina\" was not injected: check your FXML file 'Crimes.fxml'.";
        assert btnSimula != null : "fx:id=\"btnSimula\" was not injected: check your FXML file 'Crimes.fxml'.";
        assert txtN != null : "fx:id=\"txtN\" was not injected: check your FXML file 'Crimes.fxml'.";
        assert txtResult != null : "fx:id=\"txtResult\" was not injected: check your FXML file 'Crimes.fxml'.";

    }

	public void setModel(Model model) {
		this.model = model;
		boxAnno.getItems().addAll(model.getAnni());
		txtResult.setEditable(false);
	}
}
