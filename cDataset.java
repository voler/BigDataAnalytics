import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class cDataset {
  
	double[][] DataSet;
	int rows , cols;
	String[] ColumnNames;
	File fDataFile;
	
	public cDataset(){
		System.out.println("Please send me a filename.");
	}
	public cDataset(String fileName) throws FileNotFoundException{
		// read data into a two-dimensional array (table)
		fDataFile = new File(fileName);
		if (fDataFile.exists()) {
			Scanner inFile = new Scanner(fDataFile);
			String line;
			String [] tokens;
			int c, r=0;
			
			line = inFile.nextLine(); // read all headers
			tokens = line.split(","); // split up the headers by delim
			cols = tokens.length; // number of column headers
			ColumnNames = new String[tokens.length]; // instantiate an array
			for (c=0; c<cols; c++){ // store column headers into the array
				ColumnNames[c] = tokens[c];
			}
			
			// capture 500 rows
			DataSet = new double[500][cols];
			while (inFile.hasNext()) {
				line = inFile.nextLine();
				tokens = line.split(",");
				for (c=0; c<tokens.length; c++){
					DataSet[r][c] = Double.parseDouble(tokens[c]);
				}
				r++;
				rows = r;
			}
			inFile.close();
		}		
	}
	public void display() {
		// print table
		int r, c;
		for (c=0; c<cols; c++){
			System.out.print(ColumnNames[c] + " ");
		}
		System.out.println();
		for (r=0;r<rows;r++){
			for (c=0; c<cols;c++){
				System.out.print(DataSet[r][c] + " ");
			}
			System.out.println();
		}
		System.out.println();
		System.out.println();
	}
	public void display(int r) {
		// print table
		if (r<1){System.out.print("Your input is too small!");
		}
		else if (r>=rows){
			System.out.print("Your input is too large!");
		}
		else{
		int  c;
		for (c=0; c<cols; c++){
			System.out.print(ColumnNames[c] + " ");
		}
		System.out.println();	
		for (c=0; c<cols;c++){
				System.out.print(DataSet[r-1][c] + " ");
		}
		}
		System.out.println();
		System.out.println();
		}
	public void display(int startr, int endr) {
		if (startr>endr){System.out.print("Your start row number is larger than end row number!");
		}
		else if (startr<1){System.out.print("Your start row number is too small!");
		}
		else if (endr>=rows){System.out.print("Your end row number is too large!");
		}
		else{
		int r, c;
		for (c=0; c<cols; c++){
			System.out.print(ColumnNames[c] + " ");
		}
		System.out.println();
		for (r=startr-1;r<endr;r++){
			for (c=0; c<cols;c++){
				System.out.print(DataSet[r][c] + " ");
			}
			System.out.println();
		}
		}
		System.out.println();
		System.out.println();
	}

	public void display(String colName) {
		// print table
		int r, c, cmark = -1;
		for (c=0; c<cols; c++){
			if (ColumnNames[c].equals(colName))
			{System.out.print(ColumnNames[c] + " ");
			cmark=c;
			}
		}
		if (cmark == -1)
		{System.out.print("This Column Name does not Exist!");}
		else
		{
		System.out.println();
		for (r=0;r<rows;r++){
			System.out.print(DataSet[r][cmark] + " ");
			System.out.println();
		}
		}
		System.out.println();
		System.out.println();
	}
}
