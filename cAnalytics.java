import java.io.FileNotFoundException;

public class cAnalytics extends cDataset{
  double[] subset;
	double mean;
	int N;
	public cAnalytics(String fileName) throws FileNotFoundException {
		super(fileName);
	}
	public cAnalytics() throws FileNotFoundException {
		super();
	}
	
	public double[] subset(String colName){
		int r, c;
		subset = new double[500];
		for (r=0;r<rows;r++){
			for (c=0; c<cols;c++){
				if (ColumnNames[c].equals(colName)) {
					subset[r] = DataSet[r][c];
				}
			}
		}	
		return subset;
	}

	public double minimum(){
		int r, c;
		double minimum = DataSet[0][4];
		for (r=0; r<rows; r++){
			for (c=4; c<cols; c++){
				if (DataSet[r][c]< minimum){
				minimum = DataSet[r][c];
				}
			}
		}
		return minimum;
	}
	public double minimum(int week){
		int c;
		double minimum = DataSet[week-1][4];
		for (c=4; c<cols; c++){
				if (DataSet[week-1][c]< minimum){
				minimum = DataSet[week-1][c];
				}
			}
		return minimum;
	}
	
	public double minimum(int weekStart, int weekEnd){
		int r, c;
		double minimum = DataSet[weekStart-1][4];
		for (r=weekStart-1; r<weekEnd; r++){
			for (c=4; c<cols; c++){
				if (DataSet[r][c]< minimum){
				minimum = DataSet[r][c];
				}
			}
		}
		return minimum;
	}

	public double minimum(String colName){
		int r;
		subset(colName);
		double minimum = subset[0];
		for (r=0; r<rows; r++){
			if (subset[r] < minimum){
				minimum = subset[r];
			}
		}
		return minimum;
	}
	public double maximum(){
		int r, c;
		double maximum = DataSet[0][4];
		for (r=0; r<rows; r++){
			for (c=4; c<cols; c++){
				if (DataSet[r][c]> maximum){
				maximum = DataSet[r][c];
				}
			}
		}
		return maximum;
	}
	public double maximum(int week){
		int c;
		double maximum = DataSet[week][4];
		for (c=4; c<cols; c++){
				if (DataSet[week][c]> maximum){
				maximum = DataSet[week][c];
				}
			}
		return maximum;
	}
	public double maximum(int weekStart, int weekEnd){
		int r, c;
		double maximum = DataSet[weekStart][4];
		for (r=weekStart; r<=weekEnd; r++){
			for (c=4; c<cols; c++){
				if (DataSet[r][c]> maximum){
					maximum = DataSet[r][c];
				}
			}
		}
		return maximum;
	}
	public double maximum(String colName){
		int r;
		subset(colName);
		double maximum = subset[0];
		for (r=0; r<rows; r++){
			if (subset[r] > maximum){
				maximum = subset[r];
			}
		}
		return maximum;
	}
	
	public double calcMean() {
		int r, c;
		mean = 0.0;
		for (r=0; r<rows; r++){
			for (c=4; c<cols; c++){
				mean = mean + DataSet[r][c];
			}
		}
		mean = mean / (rows * (cols-4));
		N = (rows * (cols-4));
		return mean;
	}
	public double calcMean(int week) {
		int c;
		mean = 0.0;
		for (c=4; c<cols; c++){
				mean = mean + DataSet[week][c];
			}
		
		mean = mean / (1 * (cols-4));
		N = (1 * (cols-4));
		return mean;
	}
	
	public double calcMean(int weekStart, int weekEnd) {
		int r, c;
		mean = 0.0;
		for (r=weekStart; r<=weekEnd; r++){
			for (c=4; c<cols; c++){
				mean = mean + DataSet[r][c];
			}
		}
		mean = mean / ((weekEnd - weekStart + 1) * (cols-4));
		N = ((weekEnd - weekStart + 1) * (cols-4));
		return mean;
	}
	public double calcMean(String colName) {
		int r;
		subset(colName);
		mean = 0;
		for (r=0; r<rows; r++){
				mean = mean + subset[r];
		}
		mean = mean / (rows * (1));
		N = (rows * (1));
		return mean;
	}
	
	public double stdev() {
		int r, c;
		calcMean();
		double SSE=0;
		for (r=0; r<rows; r++){
			for(c=4;c<cols;c++){
				SSE = SSE + Math.pow((DataSet[r][c] - mean),2);
			}
		}
		double variance = SSE / N;
		double stdev = Math.pow(variance,0.5);
		return stdev;
	}
	public double stdev(int week) {
		int c;
		calcMean(week);
		double SSE=0;
		for(c=4;c<cols;c++){
				SSE = SSE + Math.pow((DataSet[week][c] - mean),2);
		}
		double variance = SSE / N;
		double stdev = Math.pow(variance,0.5);
		return stdev;
	}
	
	public double stdev(int weekStart, int weekEnd) {
		int r, c;
		calcMean(weekStart, weekEnd);
		double SSE=0;
		for (r=weekStart; r<=weekEnd; r++){
			for(c=4;c<cols;c++){
				SSE = SSE + Math.pow((DataSet[r][c] - mean),2);
			}
		}
		double variance = SSE / N;
		double stdev = Math.pow(variance,0.5);
		return stdev;
	}
	public double stdev(String colName) {
		int r;
		calcMean(colName);
		double SSE=0;
		for (r=0; r<rows; r++){
				SSE = SSE + Math.pow((subset[r] - mean),2);
			
		}
		double variance = SSE / N;
		double stdev = Math.pow(variance,0.5);
		return stdev;
	}
}
