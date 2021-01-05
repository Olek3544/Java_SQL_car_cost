package olek.szaruch.nowy;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CarParamService {
	JdbcConnection jdbc  = new JdbcConnection();
	
	public CarParamService() {
		// TODO Auto-generated constructor stub
	}
	private List<CarParam> carParam	=new ArrayList<CarParam>();  
	
	public static void main(String[] args) {
		CarParamService cps  =  new CarParamService();
		JdbcConnection jdbc  = new JdbcConnection(); 
		jdbc.setCarParamTable(); // to jest to dobre 
		
		cps.addValues(jdbc.getId(), jdbc.getMarka(), jdbc.getModel(), jdbc.getPojemnosc(), jdbc.getRodzajPaliwa(), jdbc.getMoc());
		

	}
	public void addValues(int id[], String marka[], String model[],  String pojemnosc[], String rodzajPaliwa[], int moc[])
	{
		for(int i = 0; i<id.length;i++)
		{
			System.out.println("Add wlasciwa  "+"&&&"+id[i]+marka[i]+" &&& "+model[i]+"&&&"+pojemnosc[i]+rodzajPaliwa[i]+" &&& "+moc[i]);
			carParam.add(new CarParam( id[i], marka[i],  model[i],   pojemnosc[i],  rodzajPaliwa[i],  moc[i]) ); 
			
			
		}
		displayCarParam(carParam); 
	}
	
	
	public  List<CarParam>getAllTopics()
	{  
		
	return carParam; 	 

	}
	/*tu masz to i jest ok */
	public void openList()
	{
		jdbc.setCarParamTable();
		addValues(jdbc.getId(), jdbc.getMarka(), jdbc.getModel(), jdbc.getPojemnosc(), jdbc.getRodzajPaliwa(), jdbc.getMoc());
		
	}
	
	public void displayCarParam(List<CarParam> cpList)
	{
		System.out.println("Z obiektu czy to gra display Car Param  !! "); 
		for(int i = 0; i<carParam.size(); i++)
		{
			CarParam cm = cpList.get(i); 
			System.out.println(cm.getId() +" "+cm.getMarka()+" "+cm.getModel()+" "+cm.getPojemnosc()+" "+cm.getRodzajPaliwa());
		}
		cpList.remove(1); 
		System.out.println("Po USUNIECIU elementu nr 1 ");
		
		for(int i = 0; i<carParam.size(); i++)
		{
			CarParam cm = cpList.get(i); 
			System.out.println(cm.getId() +" "+cm.getMarka()+" "+cm.getModel()+" "+cm.getPojemnosc()+" "+cm.getRodzajPaliwa());
		}
		
	}
	public void displayCarParam(List<CarParam> cpList, int c)
	{
		for(int i = 0; i<cpList.size(); i++)
		{
			CarParam cm = cpList.get(i); 
			System.out.println(cpList.get(i)); 
			System.out.println(cm.getId() +" "+cm.getMarka()+" "+cm.getModel()+" "+cm.getPojemnosc()+" "+cm.getRodzajPaliwa());
		}
	}
	
}
