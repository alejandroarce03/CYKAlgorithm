package model;

import java.util.HashMap;

public class AlgorithmCYK {

	private String[][] gram;
	private String w;
	private HashMap<String,String> mapGram;


	public AlgorithmCYK(String[][] g,String chain) {
		gram = g;
		w = chain;
	}

	public boolean generateCYKMatrix(){
		boolean generate=false;
		String result;
		String[][] resultMatrix = new String[w.length()][w.length()];

		//En los siguientes ciclos se llena la primera columna de la matriz de respuesta 
		for(int wL=0;wL<w.length();wL++) {
			String letter = String.valueOf(w);
			for(int i=0;i<gram.length;i++) {
				for(int j=0;j<gram[i].length;j++) {
					mapGram.put(gram[i][j],gram[i][j]);
					if(letter.equals(gram[i][j])) {
						result=gram[i][0];
						resultMatrix[wL][0]=result;
					}
				}
			}
		}

		String pw=null;
		for(int i=1;i<w.length();i++) {
			for(int j=i;j<w.length();j++) {
				for(int k=j-i;k<j;k++) {
					pw += combineStrings(resultMatrix[j - i][k], resultMatrix[k + 1][j]);
				}
			}
		}

		if(resultMatrix[0][w.length()].equals(gram[0][0])) {
			generate=true;
		}
		return generate;
	}

	public boolean verifyIfExist(String search) {
		boolean r=false;
		if(mapGram.get(search)!=null) {
			r=true;
		}
		return r;
	}

	public String combineStrings(String chain1,String chain2) {
		String result= null;
		String str = null;
		for(int i = 0; i < chain1.length(); i++){

			for(int j = 0; j < chain2.length(); j++){
				char c1 = chain1.charAt(i);
				char c2 = chain2.charAt(j);
				str +=  c1+c2;
				if(verifyIfExist(str)) {
					result=str;
				}
			}
		}

		return result;	
	}

}
