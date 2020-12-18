package olek.auto.jdbc;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.util.Random;

//import io.javabrains.Greeting;


public class InsertRandomVal {
	int przebieg = 15900; 
	double srednieSpalanie = 6.5;
	int iloscDniRok =365; 
	double iloscLitrRandom; 
	double zasiegRandom; 
	int iloscTankowanRandom;
	int zasiegZDb;
	String dataZDb; //  data pierwszego tankowania  
	String dataRandom; //  daty kolejnych tankowañ 
	String nazwaStacji; 
	
	// get i set po koleji !!!!!
	
	
	LocalDate ld = LocalDate.now(); 
	
	
	public String getDataZDb() {
		return dataZDb;
	}

	public void setDataZDb(/*String dataZDb*/int iloscTankowan) {
		LocalDate dataPoczatkowa  = ld.of(ld.getYear(), 1, iloscTankowan); // "startujemy od stycznia bierzacego roku "
		String dataZDb =  dataPoczatkowa.toString(); 
		this.dataZDb = dataZDb;
	}

	public String getNazwaStacji() {
		return nazwaStacji;
	}

	public void setNazwyStacji() {
		String[] nazwyStacji = {"Orlen", "BP", "Lotos", "Shell" , "Circle K", "Amic Energy", "Moya", "Tesco"};  //  8 elementów  od 0 do 7 
		//  rndomowe warosci dadz
		Random rn  = new Random();
		int nrStacji =  rn.ints(0, 7)
			    .findFirst()
			    .getAsInt();
		String nazwaStacji = nazwyStacji[nrStacji] ;  
		this.nazwaStacji = nazwaStacji;
	}
	/*public String getDataRandom() {
		return dataRandom;
	}
	public void setDataRandom(String dataRandom) {
		this.dataRandom = dataRandom;
	}*/ 

	public InsertRandomVal() {
		System.out.println("Today is :  "+ld);
		System.out.println("Pseudo random generator :  ");
		System.out.println("For test insert to db   ");
		System.out.println("1. to insert fuelCost  table   ");
		System.out.println("2. to insert to  maintnanceCost  table   ");
		System.out.println("3. to insert insuranceCost table   ");
		System.out.println("4. to insert carParam table and eneter car param   ");
	}
	
	public int getIloscTankowanRandom() {
		return iloscTankowanRandom;
	}

	public void setIloscTankowanRandom() throws NumberFormatException, IOException {
		  setZasiegRandom();
		  double zasiegRandom = getZasiegRandom(); 
		  setPrzebieg();   
		  int  przebieg =  getPrzebieg(); 
		int iloscTankowanRandom =  przebieg/(int)zasiegRandom; 
		
		this.iloscTankowanRandom = iloscTankowanRandom;
	}

	public int getPrzebieg() {
		return przebieg;
	}

	public void setPrzebieg() throws NumberFormatException, IOException {
		
	}

	public String getDataRandom() {
		return dataRandom;
	}
	public void setDataRandom(/*String dataRandom*/ int iloscTankowan, String poczatek) {
		/*podaj to na interface */
		
		InsertRandomValHelper1 innerClassHelping  = new InsertRandomValHelper1() {
	    	 // to sobie instantionujesz obiekr  wg siebie 
	    	 
	    	 public void callDef()
	    	 {
	    		 System.out.print("Call def method ");
	    	 }
	    	 
	     };
	     innerClassHelping.callDef(); 
	     int czasMTank =  innerClassHelping.czasMiedzyTankowaniami(iloscTankowan); 
		LocalDate poczatek1= LocalDate.parse(poczatek);  // ogarnij te strumienie tu 
		LocalDate kolejnaData = poczatek1.plusDays(czasMTank);  // tu mozna by zastosowac strumienie :) 
		String dataRandom =kolejnaData.toString();
		this.dataRandom = dataRandom;
	}
	public double getIloscLitrRandom() {
		return iloscLitrRandom;
	}

	public void setIloscLitrRandom(/*double iloscLitrRandom*/) {
		Random rn  = new Random(); 
		
		int pelneLitry = rn.ints(46, 53)
			    .findFirst()
			    .getAsInt();
		
		BigDecimal bd = new BigDecimal(Double.toString(rn.nextDouble()));
	    bd = bd.setScale(2, RoundingMode.HALF_UP);
	    double poPrzecinku =  bd.doubleValue();
		double iloscLitrRandom = pelneLitry+poPrzecinku;
		this.iloscLitrRandom = iloscLitrRandom;
		
	}
	public double getZasiegRandom() {
		return zasiegRandom;
	}
	public void setZasiegRandom(/*double zasiegRandom*/) {
		setIloscLitrRandom(); 
		double rndLitry =  getIloscLitrRandom(); 
		double zasiegRandom1=(rndLitry/srednieSpalanie)*100; 
		BigDecimal bd = new BigDecimal(Double.toString(zasiegRandom1));
	    bd = bd.setScale(2, RoundingMode.HALF_UP);
	   
		double zasiegRandom = bd.doubleValue();
		
		this.zasiegRandom = zasiegRandom;
	}
	public static void main(String[] args) throws NumberFormatException, IOException {
		
		InsertRandomVal irv  = new InsertRandomVal(); 
		
		System.out.println(" :) Utworzenie pierwszego rachunku za paliwo z losowych wartosci  ");
		int przebieg1 =  irv.getPrzebieg(); 
		System.out.println("Przebieg podany prze uzytkownika   "+przebieg1 +" km");
		double srSpalanie = irv.srednieSpalanie;
		System.out.println("Srednie spalanie uzytkownika    "+srSpalanie+" l/100km");
		irv.setIloscLitrRandom();
		double x1 =  irv.getIloscLitrRandom();
		System.out.println("Generowan wartosc to  litrow "+x1 +" dm3");
		irv.setZasiegRandom(); 
		double zasiegP = irv.getZasiegRandom();
		System.out.println("Wygenerowany zasieg  "+zasiegP +" km");
		irv.setIloscTankowanRandom();
		int  c2= 	irv.getIloscTankowanRandom(); 
		System.out.println("Generowan ilosc tankowan "+c2 +" tank/rok");
		irv.setDataZDb(c2);
		String dataTankowaniaP =  irv.getDataZDb(); 
		System.out.println("Data pierwszego tankowania   "+ dataTankowaniaP);
		irv.setNazwyStacji();
		String nazwaStacjiP  = irv.getNazwaStacji(); 
		System.out.println("Generowana nazwa stacji  "+nazwaStacjiP);
		
		double[] zasiegSuma = new double[25]; 
		int i= 0; 
		double xSuma = 0; 
		String dataTankowania = dataTankowaniaP; 
		while(xSuma<(double)przebieg1)
		{
			irv.setNazwyStacji();
			String nazwaStacji  = irv.getNazwaStacji(); 
			System.out.println("Generowana nazwa stacji  "+nazwaStacji);
		irv.setIloscLitrRandom();
		double x2 =  irv.getIloscLitrRandom();
		System.out.println("Generowan wartosc to  litrow "+x2);
		irv.setZasiegRandom(); 
		double zasieg = irv.getZasiegRandom();  
		InsertRandomValHelper irvH = (shMe)->System.out.println("Wygenerowany zasieg to " +shMe);
		irvH.testDesctription(zasieg);
		irv.setIloscTankowanRandom();
	int  c1 = 	irv.getIloscTankowanRandom(); 
		System.out.println("Generowan ilosc tankowan "+c1);
		// typical while elem
		// irv.setDataRandom(c1, dataTankowaniaP);
		irv.setDataRandom(c1, dataTankowania);
		 dataTankowania =  irv.getDataRandom();  
		System.out.println("Data tankowania  "+ dataTankowania);
		i++;
		zasiegSuma[i] =zasieg;
		xSuma = xSuma+zasiegSuma[i]; 
		System.out.println("Ilosc realnych tankowan "+ i);
		System.out.println("Kolejne zasiegi: "+ zasiegSuma[i]);
	
	BigDecimal bd = new BigDecimal(Double.toString(xSuma));
    bd = bd.setScale(2, RoundingMode.HALF_UP);
   
	double zasiegGenerowany = bd.doubleValue();
	System.out.println("Suma zasiegow "+zasiegGenerowany);
		
		
		}
		

	}

}
interface InsertRandomValHelper {
	void testDesctription(double shMe); 
	
	
	
}
interface InsertRandomValHelper1 {
	//void testDesctription(double shMe); 
	default int czasMiedzyTankowaniami(int iloscTankowan) {
		int iloscDni = 365/iloscTankowan;
		return iloscDni;
	}

	void callDef(); 
	
	
}