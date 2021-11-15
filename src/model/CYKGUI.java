package model;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.SplitPane;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class CYKGUI {

	@FXML
	private BorderPane borderPain;

	@FXML
	private Label falseLAB;

	@FXML
	private TextArea grammaticalTXT;

	@FXML
	private Pane pane;

	@FXML
	private SplitPane splitPane;

	@FXML
	private Label trueLAB;

	@FXML
	private VBox vBOX;

	@FXML
	private TextField wTXT;


	private AlgorithmCYK cyk;

	public CYKGUI() {
	}

	public void init() {

		trueLAB.setVisible(false);
		falseLAB.setVisible(false);
	}

	public void createAlgorithmCYK() {

	}

	@FXML
	void enterBTN(ActionEvent event) {
		String w = null;
		String gram = null;
		try {
			w = wTXT.getText();
			gram = grammaticalTXT.getText();
		}catch(Exception e) {
			Alert alert = new Alert(Alert.AlertType.ERROR);
		    alert.setHeaderText(null);
		    alert.setTitle("Error");
		    alert.setContentText("Please enter a valid chain or grammar");
		    alert.showAndWait();
		}


		String[][] matrixGram = null;

		if(!w.equals("") && !gram.equals("")) {

			String[] rows = gram.split(",");

			for(int i=0;i<rows.length;i++) {
				String[] p1 = rows[i].split("-->");
				matrixGram[i][0]=p1[0];
				String[] p2 = p1[1].split("|");

				for(int j=1;j<p2.length;j++) {			
					matrixGram[i][j] = p2[j];
				}

			}
			cyk = new AlgorithmCYK(matrixGram,w);
			boolean result = cyk.generateCYKMatrix();
			if(result) {
				trueLAB.setVisible(true);
			}else {
				falseLAB.setVisible(false);
			}
		}
	}
}
