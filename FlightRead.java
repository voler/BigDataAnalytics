import java.io.*;
import java.util.Arrays;

public class FlightRead {
  public static void main(String[] args) {
		//defined the direction that stores data so that we can scan all the csv files under it
		File dirin = new File("C:/Users/voler_000/Dropbox/Northwestern/Study Quarter 3/MSIA431 Big Data Analytics/FlightData"); ;
		//defined the name of output document
		String fileout = "Output.csv";
		//This String store all the variable wanted
		//String [] keep = {"Year","Quarter","Month","DayofMonth","DayOfWeek","UniqueCarrier","Origin","OriginCityName","OriginState","Dest","DestCityName","DestState","CRSDepTime","DepTime","DepDelay","DepDelayMinutes","DepDel15","DepartureDelayGroups","TaxiOut","TaxiIn","ArrDelay","ArrDelayMinutes","ArrDel15","Cancelled","ActualElapsedTime","AirTime","Distance"};
		String [] keep = {"\"Year\"","\"Quarter\"","\"Month\"","\"DayofMonth\"","\"DayOfWeek\"","\"UniqueCarrier\"","\"Origin\"","\"OriginCityName\"","\"OriginState\"","\"Dest\"","\"DestCityName\"","\"DestState\"","\"CRSDepTime\"","\"DepTime\"","\"DepDelay\"","\"DepDelayMinutes\"","\"DepDel15\"","\"DepartureDelayGroups\"","\"TaxiOut\"","\"TaxiIn\"","\"ArrDelay\"","\"ArrDelayMinutes\"","\"ArrDel15\"","\"Cancelled\"","\"ActualElapsedTime\"","\"AirTime\"","\"Distance\""};
		int numkeep = keep.length;
		//System.out.print(keep[1]);
		
		try{
			//define the output writer that write to the putput file
			BufferedWriter outputwirter = new  BufferedWriter(new FileWriter(fileout));
			//define a temp string to store information from each line
			String line = new String();
			//for all the file under the direction
			for (File fileName: dirin.listFiles())
			{
				try{
					//define the input reader
					BufferedReader inputreader = new BufferedReader(new FileReader(fileName));
					//get the first line to determine which column to keep
					line = inputreader.readLine();
					//split this line to pieces
					String[] header = line.split(",");
					//get the number of columns in the header
					int numheader = header.length;
					//create a array to store index of columns want to keep
					int[] colkeepindx = new int[numkeep];
					for (int i=0;i<numkeep;i++)
					{
						for (int j=0;j<numheader;j++)
						{
							if (header[j].equals(keep[i]))
								{
									colkeepindx[i]=j;
								}
						}
					}
					//System.out.print(colkeepindx[0]);
					//System.out.print(header[2].equals(keep[2]));
					//System.out.print(numheader);
					
					while((line = inputreader.readLine()) != null)
					{
						String[] data = line.split(",(?=([^\"]*\"[^\"]*\")*[^\"]*$)");
						String[] selecteddata = new String[numkeep];
						for (int k=0;k<numkeep;k++)
						{
							selecteddata[k]=data[colkeepindx[k]];
						}
						String selecteddataline = Arrays.toString(selecteddata);
						selecteddataline=selecteddataline.replaceAll("\\[","").replaceAll("\\]","");
						//System.out.print(selecteddataline+"\n");
						outputwirter.write(selecteddataline);
						outputwirter.newLine();
					}
					
					inputreader.close();
					
				}
				catch(FileNotFoundException ex)
					{System.out.println("Could not find file '" + fileName + "'");}
				catch(IOException ex)
					{System.out.println("Could not open file '" + fileName + "'");}
			}
			outputwirter.close();
			
		}
		catch(IOException ex)
			{System.out.println("Could not writing to file '" + fileout + "'");}
	}

}
