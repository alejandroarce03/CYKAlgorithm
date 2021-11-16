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

	@SuppressWarnings("null")
	@FXML
	void enterBTN(ActionEvent event) {
		init();
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


		String[][] matrixGram;
		int numColumns =0;
		if(!w.equals("") && !gram.equals("")) {


			String[] rows = gram.split(",");

			for(int i=0;i<rows.length;i++) {
				if(numColumns<rows[i].length()) {
					numColumns=rows[i].length();
				}
			}
			matrixGram = new String[rows.length][numColumns-6];

			for(int i=0;i<rows.length;i++) {

				String[] p1 = rows[i].split("-->");

				matrixGram[i][0]=p1[0].trim();

				boolean proof=false;
				String[] p2 = p1[1].split("\\|");
				int x=0;
				for(int j=1;j<p2.length;j++) {		

					proof=true;

					matrixGram[i][j] = p2[j-1].trim();

					x=j;
				}

				if(p2.length>=2) {
					matrixGram[i][x+1] = p2[x].trim();

				}

				if(!proof) {

					matrixGram[i][1]=p1[1].trim();

				}

				
			}
			for (int i = 0; i < matrixGram.length; i++) {
				for (int j = 0; j < matrixGram[i].length; j++) {
					if(matrixGram[i][j]==null) {
						matrixGram[i][j]="";
					}
					System.out.print(matrixGram[i][j] + " ");
				}
				System.out.println();
			}	
			
			
			cyk = new AlgorithmCYK(matrixGram,w);
			boolean result = cyk.generateCYKMatrix();
			if(result) {
				trueLAB.setVisible(true);
			}else {
				falseLAB.setVisible(true);
			}
			

			
			
		}
	}
}
