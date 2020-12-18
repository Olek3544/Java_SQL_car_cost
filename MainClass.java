package olek.auto.jdbc;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class MainClass {
	LocalDate ld   =  LocalDate.now(); 
	public MainClass() {
		System.out.println("Welecome in car cost evidention program");
		System.out.println("Today is : " + ld);
		System.out.println("1-enter fuel cost "); 
		System.out.println("2-enter maintance cost ");
		System.out.println("3-enter fuel cost ");
		System.out.println("Press enter to start  ");
	}
	public static void main(String[] args) throws Exception {
		LocalDate ld   =  LocalDate.now();
		List<Object> justList =  new ArrayList<Object>();
		UserComunnication uc = new UserComunnication(); 
		ConfigClass cfc = new ConfigClass(); 
		JdbcConnection jdbc = new JdbcConnection();  // return fun of list 
		BufferedReader rd = new BufferedReader(new InputStreamReader(System.in));
		System.out.println("Select car c - to config car param :)   ");
		System.out.println("Select car 1,2 ,4  ");
		System.out.println("Press 0 to see car parameter  ");
	
		char czn =  (char)rd.read();
		if(czn=='0')
		{
			System.out.println("Press 0 to see car parameter  "); 
			
			CalculationLogic cll = new CalculationLogic(); 
			//cll.setSumPart();
			LocalDate ld1 = LocalDate.parse("2020-06-12"); 
			cll.setByEnterValus(ld1);
		//	cll.setTwoDates();
			cll.setSumAvg();
			
			
		}
		else if(czn=='1')
		{
			justList.clear();
			System.out.println("1-selected fuel bill   ");
			uc.setFuelBill(czn);
		justList = uc.getFuelBill();
		justList.stream().forEach(System.out::println);
		
		jdbc.setPrzebieg(uc.getTableName('5', "null")); //  her are print of  fuel cons 
		uc.setAvgFuelCons(jdbc.getPrzebieg());
		
		}	
		else if(czn=='c')
		{
			System.out.println("Congig program selected ");
			cfc.startActions();
			
			
		}
		else if(czn=='2')
		{
			System.out.println("2-selected maintanance cost    ");
		justList.clear();
		uc.setMaintnance();
		justList =  uc.getMaintnance(); 
		}
		else if(czn=='3')
		{
			System.out.println("3-selected insurnace  cost   ");
		justList.clear();
		uc.setInsurance();
		justList =  uc.getInsurance(); 
		
		}
		else if(czn=='4')
		{
			System.out.println("4-select sum avg from  fuel table,    "); 
			System.out.println("4-temporary trenig of sum, avg ,  from  1fuelcosttable  "); 
			
			
			jdbc.setCarAvgSum(uc.getTableName('1', Integer.toString(ld.getYear())),uc.getTwoDates(0), uc.getTwoDates(1),1);
			uc.setSumAvg(jdbc.getCarAvgSum());  
			
			
			
		}
		else if(czn=='5')
		{
			System.out.println("5-select ilosc litrow     "); 
			jdbc.setCarAvgSum(uc.getTableName('5', "null"),uc.getTwoDates(0), uc.getTwoDates(1),10);
			uc.setSumAvg(jdbc.getCarAvgSum(),1);
			
		}
		else if(czn=='6')
		{
			System.out.println("6-Selected sum of maintanance cost   ");
		
			jdbc.setCarAvgSum(23,uc.getTableName('2', Integer.toString(ld.getYear())) , uc.getTwoDates(0), uc.getTwoDates(1), 2);
			uc.setSumAvg(jdbc.getCarAvgSum(23), 2, 23);
			
		}
		else if(czn=='7')
		{
			System.out.println("7-Selected sum of insurance  cost   ");
			jdbc.setCarAvgSum(23,uc.getTableName('3', Integer.toString(ld.getYear())) , uc.getTwoDates(0), uc.getTwoDates(1), 3);
			uc.setSumAvg(jdbc.getCarAvgSum(23), 3, 23);
		}
		else if(czn== '8')
		{
			System.out.println("8-Selected sum of yera cost  for car eks  ");
			jdbc.setCarAvgSum(uc.getTableName('1', Integer.toString(ld.getYear())),uc.getTwoDates(0), uc.getTwoDates(1),1);
			uc.setSumAvg(jdbc.getCarAvgSum());
			jdbc.setCarAvgSum(23,uc.getTableName('2', Integer.toString(ld.getYear())) , uc.getTwoDates(0), uc.getTwoDates(1), 2);
			uc.setSumAvg(jdbc.getCarAvgSum(23), 2, 23);
		}
		else if(czn=='9')
		{
			System.out.println("9-Selected sum of cost   ");
			String tNames[] = {uc.getTableName('1', Integer.toString(ld.getYear())), uc.getTableName('2', Integer.toString(ld.getYear())), uc.getTableName('3', Integer.toString(ld.getYear())),uc.getTableName('3', Integer.toString(ld.getYear()))}; 
			
				jdbc.setSumOf(tNames, 2, uc.getTwoDates(0), uc.getTwoDates(1));
				System.out.println("Now Now ");
				
				uc.setAllWWElem(jdbc.getSumOf());
				jdbc.setListaPaliwowa( uc.getTwoDates(0), uc.getTwoDates(1), uc.getTableName('1', Integer.toString(ld.getYear())));
			
		}
		
	}
}
