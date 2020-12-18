package olek.auto.jdbc;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

//import szaruch.start.program.TableTime;
abstract class CreateTableName {
	String tableName;
	LocalDate ld  = LocalDate.now();
	String dataPoczatkowa; 
	String dataKoncowa; 
	int dayDiff; 
	String[] twoDates; 
	public void setByEnterValus(LocalDate ldt)
	{
		String d1 =  "2020-12-05";
		String d2 =  "2020-12-06";
		LocalDate ld1 =  LocalDate.parse(d1); 
		LocalDate ld2 =  LocalDate.parse(d2);
		System.out.println("First " +ld1+ " Secound " + ld2);
		}
	public String[] getTwoDates1() {
		return twoDates; 
	}
	
	public void setTwoDates1() // param two types of dates (int znak, String data1, String data2, String nTab) // call from entering fun 
	{
		System.out.println("Fun setTwoDates :: ");
		String dataPoczatkowa = "2020-12-05"; 
		String dataKoncowa= "2020-12-06";
		String[] beTwoDates = {"pocz", "kon "}; 
		
		LocalDate[] twoDates0 = {LocalDate.parse(dataPoczatkowa), LocalDate.parse(dataKoncowa)}; 
		
		System.out.println("Pocztek "+ twoDates0[0] +" Koniec "+ twoDates0[1]);
		
	}
	public void setSumAvg()
	{
		LocalDate ld = LocalDate.now(); 
		System.out.println("Ten rok ma "+ ld.getDayOfYear()+"a to  "+ld.getMonth());
		System.out.println("Ten miesiac ma " + ld.getDayOfMonth());
		
	}
	public void setMainFun ()
	{
		
		
	}
	


	public String getTableName(char num, String textYear) {
		setTableName(  num, textYear); 
		return tableName; 
	}

	public void setTableName( char num, String textYear) { // text = Integer.toString(LocalDate.getYear()) to jest tak 
		if(num=='1')
		{
			tableName = "fuelCost_"+textYear;
		}	
		else if(num=='2')
		{
			tableName = "maintananceCost_"+textYear;	
		}
		else if(num=='3')
		{
			tableName = "insuranceCost_"+textYear;
		}
		else if(num=='4')
		{
			tableName = "carParam"+textYear; // do poprawy w sql 
		}
		else if(num=='5')
		{
			tableName = "1fuelcosttable"; // dla testow mam tu swoja 1fuelcosttable  chociaz po co 
		}
		
		this.tableName = tableName;
	} 

		
}
 
public class UserComunnication extends  CreateTableName implements WorkSelector {
	BufferedReader readData = new BufferedReader(new InputStreamReader(System.in));
	LocalDate ld = LocalDate.now(); 
	//klasa do po³¹czenia 
	JdbcConnection jdbc  = new JdbcConnection(); 
	//jdbc.getConnection();
	List<Object> fuelBill = new ArrayList<Object>();
	List<Object> insurance = new ArrayList<Object>(); 
	List<Object> maintnance = new ArrayList<Object>();
	List<Double>allWWElem =  new ArrayList<Double>(); 
	
	int  yearNum; 
	int nextYearNum; 
	double avgFuelCons;
	
	String twoDates; 
	List<Object>sumAvg = new ArrayList<Object>();
	List<Object>sumLit = new ArrayList<Object>();
 	
	public String getTwoDates(int w) {
		setTwoDates(w); 
		return twoDates;
	}
	public void setTwoDates( int with) {
		/*docelowo wpisuje wartosci tu user */
		String d1 =  "2020-01-1"; 
		String d2 =  "2020-12-31";
		String twoDates = ""; 
		
		if(with==0)
		{
			twoDates =  d1;
			System.out.println("The beginning date is "+d1);
		}
		else if(with==1)
		{
			twoDates = d2;
			System.out.println("The beginning date is "+d2);
		}
		this.twoDates = twoDates;
	}
	public List<Double> getAllWWElem() {
		return allWWElem;
	}
	public void setAllWWElem(List<Double> allWWElem) {
		LocalDate rok = LocalDate.of(ld.getYear(), 12, 31);
		String pln = " zl"; 
		String proc = " %";
		String[] nazwyElem =  {"Fuel ", "Maintanance ", "OC ", "AC "}; 
		List<Double> udzialy  =  new ArrayList<Double>(); 
		double kosztRoczny = 0; 
		
		System.out.println("To z funkcji setAllWWELEM ");
		
		for(int i = 0; i < allWWElem.size(); i++) {
			kosztRoczny  =  kosztRoczny+allWWElem.get(i); 
		    System.out.println(nazwyElem[i]+":"+(allWWElem.get(i))+pln);
		}
		System.out.println("Part of cost (year):  " );
		for(int i = 0; i < allWWElem.size(); i++) {
			  udzialy.add(allWWElem.get(i)/kosztRoczny); 
		    System.out.println(nazwyElem[i]+":"+(udzialy.get(i))*100+proc);
		}
		double kosztDzienny =  kosztRoczny/rok.getDayOfYear(); 
		double kosztMiesieczny  =  kosztRoczny/12; 
		System.out.println("Daily car cost "+ kosztDzienny+pln);
		System.out.println("Monthly cost "+ kosztMiesieczny+pln); 
		System.out.println("Year cost "+ kosztRoczny+pln); 
		
		this.allWWElem = allWWElem;
	}
	public List<Object> getInsurance() {
		return insurance;
	}
	public void setInsurance() throws Exception {
		List<Object> insurance =  new ArrayList<Object>();
		readData.readLine();
		System.out.println("Enter insurance cost  "); 
		
		System.out.println("Enter date of insurancing    ");
		String dataUbezpieczenia= readData.readLine();
		System.out.println("What was do ?   ");
		String   nazwaUbezp  = readData.readLine();
		System.out.println("Enter cost of  OC   ");
		double  wartoscOC   = Double.parseDouble(readData.readLine());
		System.out.println("Enter cost of  AC   ");
		double  wartoscAC   = Double.parseDouble(readData.readLine());
		insurance.add(dataUbezpieczenia);
		insurance.add( nazwaUbezp ); 
		insurance.add(wartoscOC); 
		insurance.add(wartoscAC); 
		
		
		this.insurance = insurance;
		jdbc.getConnection();
		jdbc.insertIntoInsuranceCostTable(getTableName('3', Integer.toString(ld.getYear())), 2, dataUbezpieczenia, nazwaUbezp, wartoscOC, wartoscAC);
		jdbc.closeConnection();
	}
	public List<Object> getMaintnance() {
		return maintnance;
	}
	public void setMaintnance() throws Exception {
		
		List<Object> maintnance  = new ArrayList<Object>();
		readData.readLine();
		System.out.println("1. Insert  fuel cost  ");
		
		System.out.println("Enter date of repearing    ");
		String dataNaprawy= readData.readLine();
		System.out.println("What was do ?   ");
		String  nazwaUslugi = readData.readLine();
		System.out.println("Enter cost of repearing   ");
		double  wartoscRachunku  = Double.parseDouble(readData.readLine());
		
		
		maintnance.add(dataNaprawy);
		maintnance.add(nazwaUslugi ); 
		maintnance.add(wartoscRachunku); 
		jdbc.getConnection(); 
		jdbc.insertIntoMaintenanceCostTable(getTableName('2', Integer.toString(ld.getYear())), 2, dataNaprawy, nazwaUslugi, wartoscRachunku);
		jdbc.closeConnection();
		this.maintnance = maintnance;
	}
	public List<Object> getFuelBill() {
		return fuelBill;
	}
	public void setFuelBill( char znak) throws Exception {
		int  yearNum =ld.getYear(); 
		int nextYearNum = ld.getYear()+1; 
		this.yearNum = yearNum; 
		this.nextYearNum = nextYearNum; 
		List<Object> fuelBill = new ArrayList<Object>();
		
		readData.readLine();
		System.out.println("1. Get fuel cost   ");
		
		System.out.println("Enter fuel date   ");
		String  dataTankowania= readData.readLine();
		System.out.println("Enter Petrol Station name  ");
		String  nazwaStacji = readData.readLine();
		System.out.println("Enter liter amount   ");
		double  iloscLitrow  = Double.parseDouble(readData.readLine());
		System.out.println("Enter tank cost  ");
		double  wartoscRachunku  =  Double.parseDouble(readData.readLine());
		System.out.println("Enter car odomiter   ");
		int przebieg = Integer.parseInt(readData.readLine());
		
		fuelBill.add(dataTankowania);
		fuelBill.add(nazwaStacji); 
		fuelBill.add(iloscLitrow); 
		fuelBill.add(wartoscRachunku);
		fuelBill.add(przebieg);
		
		this.fuelBill = fuelBill;
		//setTableName( '5', "");
		jdbc.getConnection();
		jdbc.insertIntoFuelCostTable(getTableName('1', Integer.toString(ld.getYear())), 2, dataTankowania, nazwaStacji, iloscLitrow, wartoscRachunku, przebieg);
		jdbc.closeConnection();
	}
	
	public UserComunnication() {
		System.out.println("Today is :  "+ld);
		System.out.println("Enetering data   ");
		System.out.println("Chose      ");
		System.out.println("1. insert fuel cost:    ");
		System.out.println("2. insert maintanance cost   ");
		System.out.println("3. insert insurance cost     ");
		System.out.println("4.  for "+ LocalDate.of(2020, 12, 7)+"   here are the test :)  ");
		//calculateR(); 
	}
	/*CZESC NA POBÓR DANYCH */
	public List<Object> getSumAvg() {
		return sumAvg;
	}
	public void setSumAvg(List<Object> sumAvg) { 
		
		  DecimalFormat df = new DecimalFormat("0.00"); 
		  System.out.println("Sum of  fuel bill "+df.format(sumAvg.get(1))+" PLN"); 
		  System.out.println("Avg of  fuel bill "+df.format(sumAvg.get(2))+" PLN");
		
		this.sumAvg = sumAvg;
	}
	public List<Object> getSumAvg(int y1) {
		return sumAvg;
	}
	public void setSumAvg(List<Object> sumAvg, int y1) {
		
		  DecimalFormat df = new DecimalFormat("0.00"); 
		  System.out.println("Sum of  fuel litter is "+df.format(sumAvg.get(1))+" Litter"); 
		  System.out.println("Avg of  fuel litter is "+df.format(sumAvg.get(2))+" Litter");
		
		this.sumAvg = sumAvg;
	}
	// calculation part 
	public double getAvgFuelCons() {
		return avgFuelCons;
	}
	public void setAvgFuelCons(int przebieg) {
		//double avgFuelCons =0+przebieg; 
		int przebiegPocz = (int)fuelBill.get(4); 
		int zasieg  = przebiegPocz-przebieg;
		double avgFuelCons = (double)fuelBill.get(2)/(zasieg/100);
		System.out.println("Srednie spalanie to  "+avgFuelCons);
		this.avgFuelCons = avgFuelCons;
	}
	 
	public List<Object> getSumAvg(int x, int y) {
		return sumAvg;
	}
	public void setSumAvg(List<Object> sumAvg, int x, int y) { //   !!! dane  x==2 maintannance x ==3 insurance !!  !! ! y = 23 min and insurence 
		DecimalFormat df = new DecimalFormat("0.00"); 
		if(x==2)
		{
		  
		  System.out.println("Sum of  maintanance bill "+df.format(sumAvg.get(0))+" PLN"); 
		  sumAvg.clear();
		}
		else if(x==3) 
		{
		  //System.out.println("Avg of  fuel bill "+df.format(sumAvg.get(2))+" PLN");
		  System.out.println("Sum of  insurance OC "+df.format(sumAvg.get(0))+" PLN"); 
		  System.out.println("Sum of  insurance AC "+df.format(sumAvg.get(1))+" PLN");
		  sumAvg.clear();
		}
		
		this.sumAvg = sumAvg;
	}
	public static void main(String[] args) {
		
		UserComunnication unm  = new UserComunnication(); 
		
	

	}

}
interface InsuranceCostHelper {
	public void showSomething(); 
	
	
	
}
interface WorkKeaper {
	default void showProblem()
	{
		System.out.println("Do not what car offer from internet ");
		System.out.println("Fight our futher is in our hands  ");
	}
	
}
interface WorkSelector {
	//  do obliczania sumy rocznej oraz udza³ów poszczególnych 
	// fufnkjce do wprowadzania danych 
	//public List<Double> getAllWWElem();
	//public void setAllWWElem(); 
	public List<Object> getInsurance(); 
	public void setInsurance() throws Exception; 
	public List<Object> getMaintnance()  ; 
	public void setMaintnance() throws Exception; 
	public List<Object> getFuelBill(); 
	public void setFuelBill(char znak) throws Exception; 
	// funkcje do poboru danych paliwo
	public List<Object> getSumAvg();  //  suma z³ 	
	public void setSumAvg(List<Object> sumAvg);  // suma z³ 
	public List<Object> getSumAvg(int y1);  // suma litry 
	public void setSumAvg(List<Object> sumAvg, int y1);  // suma litry 
	// obliczenia sredniego spalania jest fun ok tylko tabela sie nie zgadza 
	public double getAvgFuelCons();  // liczymy œredni dla ostatniego zu¿ytego tankowania  tu liczy zle bo bierze " z podanej œwierzo wartoœci 
	public void setAvgFuelCons(int przebieg); 
//  suam kosztów dla eksploatacji i ubezpieczenia  
	public List<Object> getSumAvg(int x, int y); //  x==2  y==23  2 main 3 ins
	public void setSumAvg(List<Object> sumAvg, int x, int y);
	
	
	
	
	
}