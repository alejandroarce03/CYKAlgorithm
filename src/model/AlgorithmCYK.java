package model;

import java.util.HashMap;

public class AlgorithmCYK {

	private String[][] gram;
	private String w;
	private HashMap<String,String> mapGram;


	public AlgorithmCYK(String[][] g,String chain) {
		gram = g;
		w = chain;
		mapGram = new HashMap<String, String>();
	}

	public boolean generateCYKMatrix() throws ArrayIndexOutOfBoundsException{
		boolean generate=false;
		
		String[][] resultMatrix = new String[w.length()+1][w.length()+1];
		
		System.out.println("w: "+w);

		for(int wL=0;wL<w.length();wL++) {
			String result="";
			String letter = ""+w.charAt(wL);
			for(int i=0;i<gram.length;i++) {
				for(int j=1;j<gram[i].length;j++) {
					mapGram.put(gram[i][j],gram[i][j]);
					if(letter.equals(gram[i][j])) {
						result+=gram[i][0];
					}
				}
			}
			resultMatrix[wL][wL]=result;
		}
		
		for(int i=1;i<w.length();i++) {
			for(int j=i;j<=w.length();j++) {
				String pw="";
				for(int k=j-i;k<j;k++) {
					System.out.println("i "+i);
					System.out.println("j "+j);
					System.out.println("k "+k);
					pw += combineStrings(resultMatrix[j-i][k], resultMatrix[k+1][j]);
					System.out.println("veo 1 "+resultMatrix[j-i][k]);
					System.out.println("veo 2 "+resultMatrix[k+1][j]);
				}
				resultMatrix[j-i][j] = pw;
			}
		}

		if(resultMatrix[0][w.length() - 1].indexOf("S") >= 0) {
			generate=true;
		}
		
		for (int i = 0; i < resultMatrix.length; i++) {
			for (int j = 0; j < resultMatrix[i].length; j++) {
				System.out.print(resultMatrix[i][j] + " ");
			}
			System.out.println();
		}
		
		System.out.println("pasa: "+ generate);
		return generate;
	}

	public String verifyIfExist(String search) {
		String result="";
		int j=0;
		 for(int i = 0; i < gram.length; i++) {
	        	
	            for( j = 0; j < gram[i].length ; j++){
	            	
	                if(gram[i][j].equals(search)){
	                	
	                    result += gram[i][0];
	                    
	                }
	            }
	            
	        }
		 return result;
	}

	public String combineStrings(String chain1,String chain2) {
		
		String result= "";
		String str = "";
		if(chain1!=null && chain2!=null) {
			for(int i = 0; i < chain1.length(); i++){

				for(int j = 0; j < chain2.length(); j++){
					str="";
					str +=  chain1.charAt(i)+""+chain2.charAt(j);
					result+=verifyIfExist(str);
						
					
				}
			}
		}

		return result;	
	}

}
