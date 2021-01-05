package olek.szaruch.nowy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class PaliwoService {
	private List<Paliwo> paliwo	=new ArrayList<Paliwo>();
	public PaliwoService() {
		
	}
	public void setPaliwo() throws NumberFormatException, IOException
	{
		//int id=0;
		BufferedReader rd = new BufferedReader(new InputStreamReader(System.in));
		rd.readLine(); 
		
		System.out.println("How many fuel bills You want to enter");
		int numOfb =  Integer.parseInt(rd.readLine());
		 int[] id = new int[numOfb];;
		 int[] carId = new int[numOfb];
		  String[] data = new String[numOfb];
		 double[] koszt = new double[numOfb]; 
		  double[] iloscLitrow = new double[numOfb]; 
		  int[] przebieg = new int[numOfb]; 
		  String[] nazwaStacji = new String[numOfb]; 
		  for(int i =0; i<data.length;i++)
			  
		  {
			  id[i]=i+1;
		System.out.println("Enter carID: ");
		carId[i]  = Integer.parseInt(rd.readLine());
		System.out.println("Enter fuelling date in format ::-> yyyy-MM-dd : ");
		 data[i] =rd.readLine(); 
		System.out.println("Enter fuelling cost : ");
	     koszt[i] =Double.parseDouble(rd.readLine()); 
	    System.out.println("Enter litter amount  : ");
		 iloscLitrow[i] = Double.parseDouble(rd.readLine()); 
		System.out.println("Enter mileage : ");
	 przebieg[i] = Integer.parseInt(rd.readLine()); 
		System.out.println("Enter station name  : ");
		nazwaStacji[i] = rd.readLine();
		// d
		 paliwo.add( new Paliwo(id[i],  carId[i],  data[i],  koszt[i],  iloscLitrow[i], przebieg[i], nazwaStacji[i]));
		  }
		  dispayPaliwo(paliwo);
	}
	public void loadToDataBase() throws Exception
	{
		JdbcConnection jdbc = new JdbcConnection(); 
		setPaliwo();
		System.out.println("Do you want to remove something from entered data or enter data one more time ? ");
		System.out.println("Press 1-to remove, 2-clear list and enter data one more time , 3- do not change load to db  ");
		BufferedReader rd = new BufferedReader(new InputStreamReader(System.in));
		//rd.readLine(); 
		char czn =  (char)rd.read();
		if(czn=='1')
		{
			System.out.println("Select number o records to remove :  ");
			rd.readLine();
			int nrR = Integer.parseInt(rd.readLine());
			paliwo.remove(nrR);
			System.out.println("Enetered data after removing row nr  :  "+nrR );
			dispayPaliwo(paliwo);
			
		}
		
		else if(czn=='2')
		{
			System.out.println("List cleared  :  ");
			paliwo.clear();
			setPaliwo();
		}
		else if(czn=='3')
		{
			jdbc.getConnection();
			  jdbc.insertIntoFuelCostTable(jdbc.getTableName('1', "2020"), paliwo);
			  jdbc.closeConnection();	
		}
		
			jdbc.getConnection();
			  jdbc.insertIntoFuelCostTable(jdbc.getTableName('1', "2020"), paliwo);
			  jdbc.closeConnection();
	
		
	}
	public void dispayPaliwo(List<Paliwo> list)
	{
		for(int i = 0; i<list.size(); i++) {
			Paliwo pal = list.get(i); 
		System.out.println(pal.getId() +" "+pal.getCarId()+" "+pal.getData()+" "+pal.getKoszt()+" "+pal.getIloscLitrow()+" "+ pal.getPrzebieg()+" "+ pal.getNazwaStacji());
		}
		}
	
    public  List<Paliwo>getPaliwo()
	{  
		return paliwo;
	}
	public  List<Paliwo>getAllPaliwo()
	{  
	return paliwo; 	 // lista 

	}
	
}
