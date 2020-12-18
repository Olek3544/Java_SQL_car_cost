package olek.auto.jdbc;

//import java.awt.List;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;

import javax.swing.JFrame;
import java.util.List;




public class JdbcConnection extends  CreateTableName{
	//UserComunnication uc = new UserComunnication(); 

	public JdbcConnection() {
		
	}
	private String driver; 
	private String url; 
	private String username; 
	private String password; 
	protected Connection conn; 
	List<Object> listaPaliwowa  =  new ArrayList<Object>(); 
	
	List<Object> carAvgSum = new ArrayList<Object>();  
	List<Object> avgAndSum =new ArrayList<Object>(); //  get to this and load to fun 
	List<Object> avgAndSumRow = new ArrayList<Object>();
	List<String> marka   = new ArrayList<String>();
	List<String> model  =  new ArrayList<String>(); 
	List<Double> sumOfEverything  =  new ArrayList<Double>(); 
	ArrayList<Double> sumOf  = new ArrayList<Double>();
	
	
	int rowCount; 
	int przebieg; 
	
	
	// String[][] carBParam; 
	List<Object> carBParam = new ArrayList<Object>(); 
	/*String[] model; 
	String[] marka; */
	
	
	
	public  Connection getConnection() throws Exception{ 
		String driver ="com.mysql.cj.jdbc.Driver";
		String url  = "";
		String username = "";
		String password  =  "";
		this.driver = driver;
		this.url = url; 
		this.username = username; 
		this.password = password;
		Connection conn= DriverManager.getConnection(url, username, password);;
		this.conn= conn;
		
		Class.forName(driver);
		System.out.println("Connected");
		return conn; 
	}
	// 
	public int getRowCount() {
		return rowCount;
	}
	public void setRowCount(String tName ) {
		int rowCount = 0; 
		String query = "select id from "
				+ tName;
		try {
			getConnection(); 
					
					Statement stmt = conn.createStatement(); 
					
					ResultSet rs = stmt.executeQuery(query);
				
				      while(rs.next()) {
				         
				    
				        
				    
				   
				    
				    	  rowCount =   rs.getRow(); 
				    
				    
				    	
				    
				 	   
				      }
					
				    
				      closeConnection();
					
				} catch (Exception e) {
					
					e.printStackTrace();
					
				}
		this.rowCount = rowCount;
	}
	public int getPrzebieg() {
		return przebieg;
	}
	public List<Object> getListaPaliwowa() {
		return listaPaliwowa;
	}
	public void setListaPaliwowa(/*List<Object> listaPaliwowa*/ String dataPoczatkowa, String dataKoncowa, String nazwaTabeli) {
		List<Object> listaPaliwowa = new ArrayList<Object>();
		String[] fuelColumn = {"id ",             
				 "carID  ",          
				 "dataTankowania ",   
				 "nazwaStacji ",     
				 "iloscLitrow ",    
				 "wartoscRachunku" ,
				 "przebieg"};
		String query  =  "select przebieg from  fuelcost_2020"; 
		int liczbaRzedow = 0; 
		
		System.out.println(query); 
		try {
			getConnection(); 
					
					Statement stmt = conn.createStatement(); 
					
					ResultSet rs = stmt.executeQuery(query);
				
				      while(rs.next()) {
				   liczbaRzedow=  rs.getRow();
				      }
		closeConnection();
					
				} catch (Exception e) {
					
					e.printStackTrace();
					
				}
		String query1 = "select * from "
				+ nazwaTabeli
				+ " where id = "
				+Integer.toString(liczbaRzedow);
		System.out.println(query1);
		try {
			getConnection(); 
					
					Statement stmt = conn.createStatement(); 
					
					ResultSet rs = stmt.executeQuery(query1);
				
				      while(rs.next()) {
				    	  listaPaliwowa.add(rs.getInt(1)); 
				    	  listaPaliwowa.add(rs.getInt(2));
				    	  listaPaliwowa.add(rs.getString(3));
				    	  listaPaliwowa.add(rs.getString(4));
				    	  listaPaliwowa.add(rs.getDouble(5));
				    	  listaPaliwowa.add(rs.getDouble(6));
				    	  listaPaliwowa.add(rs.getInt(7));
				      }
		closeConnection();
					
				} catch (Exception e) {
					
					e.printStackTrace();
					
				}
		System.out.println("From jdbc ");
		for(int i = 0; i <listaPaliwowa.size(); i++ )
		{
			System.out.println(fuelColumn[i]+" "+listaPaliwowa.get(i));
		}
		this.listaPaliwowa = listaPaliwowa;
	}
	public void setPrzebieg( String tName) {
int przebieg =0; 
setRowCount(getTableName('5', "null") );
int rCount = getRowCount(); 

String query  =  "select przebieg from "
		+ tName+
		" where id = "
		+Integer.toString(rCount);
System.out.println(query); 
try {
	getConnection(); 
			
			Statement stmt = conn.createStatement(); 
			
			ResultSet rs = stmt.executeQuery(query);
		
		      while(rs.next()) {
		    przebieg =  rs.getInt(1); 
		    
		    	
		   
		 	   
		      }
			
		    
		      closeConnection();
			
		} catch (Exception e) {
			
			e.printStackTrace();
			
		}
		this.przebieg = przebieg;
		
	}
	public List<Double> getSumOfEverything() {
		return sumOfEverything;
	}
	public void setSumOfEverything(int xyz, String nazwaTabeli, String dataPoczatkowa, String dataKoncowa) {
		List<Double> sumOfEverything = new ArrayList<Double>(); 
		String query = ""; 
		// 1 -get sum paliwo, 2 get sum maint , 3 get sum insu
		if(xyz==1)
		{
			query  ="select Sum(wartoscRachunku)  "
					+ "from "
					+ nazwaTabeli
					+ " where "
					+ "dataTankowania "
					+ " between "
					+ "'"+dataPoczatkowa+"'"
					+ " and " 
					+"'"+dataKoncowa+"'"; 
		}
		else if(xyz==2)
		{
			query  ="select Sum(wartoscRachunku) "
					+ "from "
					+ nazwaTabeli
					+ " where "
					+ "dataNaprawy "
					+ " between "
					+ "'"+dataPoczatkowa+"'"
					+ " and " 
					+"'"+dataKoncowa+"'";
			
			}
		else if(xyz==3)
		{
			query  ="select Sum(wartoscOC), Sum( wartoscAC) "
					+ "from "
					+ nazwaTabeli
					+ " where "
					+ "dataUbezpieczenia "
					+ " between "
					+ "'"+dataPoczatkowa+"'"
					+ " and " 
					+"'"+dataKoncowa+"'"; 
		}
		
		this.sumOfEverything = sumOfEverything;
	}
	public List<Object> getCarAvgSum() {
		return carAvgSum;
	}
	public void setCarAvgSum(/*List<Object> carAvgSum*/String nazwaTabeli, String dataPoczatkowa, String dataKoncowa, int x1) {
		List<Object> carAvgSum =  new ArrayList<Object>(); 
		dataPoczatkowa = "2020-12-01"; 
		dataKoncowa =  "2020-12-22"; 
		String query = ""; 
		if(x1==1)
		{
		 query  ="select Sum(wartoscRachunku), Avg(wartoscRachunku) "
				+ "from "
				+ nazwaTabeli
				+ " where "
				+ "dataTankowania "
				+ " between "
				+ "'"+dataPoczatkowa+"'"
				+ " and " 
				+"'"+dataKoncowa+"'";  //  begin  2020-02-17 end 2020-10-02 
		}
		else if(x1==10)
		{
			query  ="select Sum(iloscLitrow), Avg(iloscLitrow) "
					+ "from "
					+ "1fuelcosttable "
					+ "where "
					+ "dataTankowania "
					+ " between "
					+ "'"+dataPoczatkowa+"'"
					+ " and " 
					+"'"+dataKoncowa+"'";   	
		}
		System.out.println(query);
		try {
			getConnection(); 
					
					Statement stmt = conn.createStatement(); 
					
					ResultSet rs = stmt.executeQuery(query);
				
				      while(rs.next()) {
				         
				    
				        
				    
				   
				    
				    carAvgSum.add(rs.getRow()); 
				    carAvgSum.add(rs.getDouble(1));
				    carAvgSum.add(rs.getDouble(2)); 
				    
				    	
				    //System.out.println();
				 	   
				      }
					
				    
				      closeConnection();
					
				} catch (Exception e) {
					
					e.printStackTrace();
					
				}
		this.carAvgSum = carAvgSum;
	}
	
	
	public List<Object> getCarAvgSum(int z) { //  2 i 3 tak jak  z==23 
		return carAvgSum;
	}
	public void setCarAvgSum(int z, String nazwaTabeli, String dataPoczatkowa, String dataKoncowa, int x1) { //  z ==23  mian ins x1 == 2 mian, x1==3 ins 
		carAvgSum.clear();
		List<Object> carAvgSum =  new ArrayList<Object>(); 
		dataPoczatkowa = "2020-12-01"; 
		dataKoncowa =  "2020-12-22"; 
		String query = ""; 
		if(x1==3)
		{ //  dla ubezpieczeñ 
		 query  ="select Sum(wartoscOC), Sum( wartoscAC) "
				+ "from "
				+ nazwaTabeli
				+ " where "
				+ "dataUbezpieczenia "
				+ " between "
				+ "'"+dataPoczatkowa+"'"
				+ " and " 
				+"'"+dataKoncowa+"'";  //  begin  2020-02-17 end 2020-10-02 
		}
		else if(x1==2)
		{ //  za koszty eksploatacyjne 
			query  ="select Sum(wartoscRachunku) "
					+ "from "
					+ nazwaTabeli
					+ " where "
					+ "dataNaprawy "
					+ " between "
					+ "'"+dataPoczatkowa+"'"
					+ " and " 
					+"'"+dataKoncowa+"'";  //  begin  2020-02-17 end 2020-10-02 	
		}
		System.out.println(query); 
		try {
			getConnection(); 
					
					Statement stmt = conn.createStatement(); 
					
					ResultSet rs = stmt.executeQuery(query);
				
				      while(rs.next()) {
				         
				    
				        
				    
				   
				    if(x1==3) {
				   
				    	carAvgSum.add(rs.getDouble(1));
					    carAvgSum.add(rs.getDouble(2));
				    
				    }
				    else if(x1==2) {
				    	carAvgSum.add(rs.getDouble(1));
				    }	
				    //System.out.println();
				    
				 	   
				      }
					
				    
				      closeConnection();
					
				} catch (Exception e) {
					
					e.printStackTrace();
					
				}
		this.carAvgSum = carAvgSum;
	}
	public ArrayList<Double> getSumOf()
	{
		return sumOf;
	}
	public void setSumOf (String[] nazwaTabeli, int carId, String dataPoczatkowa, String dataKoncowa)
	{
	ArrayList<Double> sumOf = new ArrayList<Double>();
	ArrayList<String> queres =  new ArrayList<String>(); //  
	// paliwo 
	queres.add("select Sum(wartoscRachunku) "
			+ "from "
			+ nazwaTabeli[0]
			+ " where "
			+ "dataTankowania "
			+ " between "
			+ "'"+dataPoczatkowa+"'"
			+ " and " 
			+"'"+dataKoncowa+"'"); 
	// eksploatacja 
	queres.add("select Sum(wartoscRachunku) "
			+ "from "
			+ nazwaTabeli[1]
			+ " where "
			+ "dataNaprawy "
			+ " between "
			+ "'"+dataPoczatkowa+"'"
			+ " and " 
			+"'"+dataKoncowa+"'"); 
	// OC
	queres.add("select Sum(wartoscOC) "
			+ "from "
			+ nazwaTabeli[2]
			+ " where "
			+ "dataUbezpieczenia "
			+ " between "
			+ "'"+dataPoczatkowa+"'"
			+ " and " 
			+"'"+dataKoncowa+"'");
	// AC 
	queres.add("select  Sum( wartoscAC) "
			+ "from "
			+ nazwaTabeli[2]
			+ " where "
			+ "dataUbezpieczenia "
			+ " between "
			+ "'"+dataPoczatkowa+"'"
			+ " and " 
			+"'"+dataKoncowa+"'");
	for(int i = 0; i<queres.size();i++)
	{
		try {
			getConnection(); 
					
					Statement stmt = conn.createStatement(); 
					
					ResultSet rs = stmt.executeQuery(queres.get(i));
					System.out.println(queres.get(i));
					
				      while(rs.next()) {
				     System.out.println(rs.getDouble(1)); 
				    	  sumOf.add(rs.getDouble(1)); 
				       
				      }
					
				    
				      closeConnection();
					
				} catch (Exception e) {
					
					e.printStackTrace();
					
				}
		
	}
	
	
	this.sumOf = sumOf; 
	}
	
	public List<Object> getCarBParam() {
		return carBParam;
	}
	public void setCarBParam() throws Exception {
		getConnection(); 
		List<Object> carBParam =  new ArrayList<Object>();
String sqlTableName ="carparam2020"; 
String[] model = new String[3]; 
String[] marka = new String[3];
		String query =  "select  "
				+ "*"
				+ " from "
				+ sqlTableName;
				
				
		System.out.println(query);
		try {
			getConnection(); 
					
					Statement stmt = conn.createStatement(); 
					
					ResultSet rs = stmt.executeQuery(query);
					int i = -1; 
				      while(rs.next()) {
				         
				    i++; 
				        
				    rs.getRow(); 
				    marka[i] = rs.getString(1); 
				    model[i] = rs.getString(2); 
				    
				    carBParam.add(rs.getInt(1)); 
				    carBParam.add(rs.getString(2));
				    carBParam.add(rs.getString(3)); 
				    carBParam.add(rs.getString(4));
				    carBParam.add(rs.getString(5)); 
				    carBParam.add(rs.getInt(6)); 
				   
				    System.out.println("Ilosc krokow" +i);	   
				      }
					
				    
				      closeConnection();
					
				} catch (Exception e) {
					
					e.printStackTrace();
					
				}
		// this.carBParam = carBParam;
		carBParam.forEach(System.out::println);
		System.out.println("A drugi zaczyna sie {}"+carBParam.get(7));
		this.carBParam   = carBParam; 
		for(int j =0; j <marka.length; j++)
		{
			System.out.println(" | "+marka[j]+" ::" +model[j]);	
		}
		
	}
	public void closeConnection() throws Exception {
		System.out.println("Disconnected form DB");
		conn.close();
	}
	
	
	public List<Object> getAvgAndSum() {
		return avgAndSum;
	}
	public void setAvgAndSum() {
		//setTableName(0);
		String tblName  = ""; // getTableName(); //(int num)
		
		this.avgAndSum = avgAndSum;
	}
	//public void 
	public void createCarParameterTable(String tableName) throws Exception {
		String carParameterTable = "CREATE TABLE IF NOT EXISTS "
				+  tableName//"carsInfo"
				+ "(id int AUTO_INCREMENT, "
				+ "marka varchar(20), "
				+ "model varchar(20),"
				+ "pojemnosc varchar(20), "
				+ "rodzajPaliwa varchar(20), "
				+ "moc int,  "
				+ "PRIMARY KEY (id))";
		try {
			PreparedStatement create =  conn.prepareStatement(carParameterTable);
			create.executeUpdate();
			
		
			
		}
		catch(Exception e) {System.out.println(e);}
		finally {
			System.out.println("Function completed, table created :) " );
		};
	}
	public void createFuelCostTable(String tableName) throws Exception {
		String carFuelCostTable = "	CREATE TABLE IF NOT EXISTS "
				+ tableName
				+ "(id int AUTO_INCREMENT, "
				+ "carID int, "
				+ "dataTankowania date, "
				+ "nazwaStacji varchar(20),"
				+ "iloscLitrow double(7,3), "
				+ "wartoscRachunku double(7,3),  "
				+ "przebieg int, "
				+ "PRIMARY KEY (id))";
		System.out.println(carFuelCostTable);
		try {
			PreparedStatement create =  conn.prepareStatement(carFuelCostTable);
			create.executeUpdate();
			
		
			
		}
		catch(Exception e) {System.out.println(e);}
		finally {
			System.out.println("Function completed, table created :) " );
		};
	}
	public void createTableMaintenanceCost(String tableName) throws Exception {
		String carMaintenanceCostTable = "	CREATE TABLE IF NOT EXISTS "
				+tableName
				+ "(id int AUTO_INCREMENT, "
				+ "carID int, "
				+ "dataNaprawy varchar(20), "
				+ "nazwaUslugi varchar(20),"
				+ "wartoscRachunku double(7,3),  "
				+ "PRIMARY KEY (id))";
		
		try {
			PreparedStatement create =  conn.prepareStatement(carMaintenanceCostTable);
			create.executeUpdate();
			
		
			
		}
		catch(Exception e) {System.out.println(e);}
		finally {
			System.out.println("Function completed, table created :) " );
		};
	}
	public void createTableInsuranceCost(String tableName) throws Exception {
		String carInsuranceCostTable = "	CREATE TABLE IF NOT EXISTS "
				+ tableName
				+ "(id int AUTO_INCREMENT, "
				+"carID int, "
				+ "dataUbezpieczenia varchar(20), "
				+ "nazwaUbezp varchar(20),"
				+ "wartoscOC double(7,3),  "
				+ "wartoscAC double(7,3),"
				+ "PRIMARY KEY (id))";
		
		try {
			PreparedStatement create =  conn.prepareStatement(carInsuranceCostTable);
			create.executeUpdate();
			
		
			
		}
		catch(Exception e) {System.out.println(e);}
		finally {
			System.out.println("Function completed, table created :) " );
		};
	}
	public void insertIntoCarParameterTable (String tableName, String marka, String model, String pojemnosc, int moc, String rodzajPaliwa) throws Exception{
				
		String CarParameterTable =  "INSERT INTO "
				+ tableName
				+ "(id, marka, model, pojemnosc, moc, rodzajPaliwa) "
				+ "VALUES(?, ?, ?, ?, ?,?)";
		System.out.println(CarParameterTable);
		System.out.println(marka +" "+model+" "+pojemnosc+" "+moc);
		PreparedStatement preparedStatement  = conn.prepareStatement(CarParameterTable); 
		preparedStatement.setInt(1, 0);
		preparedStatement.setString(2, marka);
		preparedStatement.setString(3, model);
		preparedStatement.setString(4, pojemnosc);
		preparedStatement.setInt(5, moc);
		preparedStatement.setString(6, rodzajPaliwa);
		preparedStatement.executeUpdate();
		
	}
	public void insertIntoFuelCostTable (String nazwaTableli, int carID, String dataTankowania, String nazwaStacji, double iloscLitrow, double wartoscRachunku, int przebieg) throws Exception{
		/*DODANIE REKRODU DATATANKOWANIE, NAZWASTACJI, 
		 * ILOSC LITROW WARTOSC TANKOWANIA PRZEBIEG */
		String FuelCostTable =	"INSERT INTO "
				+ nazwaTableli
								+ "(id, carID,  dataTankowania ,nazwaStacji ,iloscLitrow , wartoscRachunku, przebieg)"
								+ " VALUES(?, ?, ?, ?, ?,?,?)"; 
System.out.println(FuelCostTable);
PreparedStatement preparedStatement  = conn.prepareStatement(FuelCostTable); 
preparedStatement.setInt(1, 0);
preparedStatement.setInt(2, carID);
preparedStatement.setString(3, dataTankowania);
preparedStatement.setString(4, nazwaStacji);
preparedStatement.setDouble(5, iloscLitrow);
preparedStatement.setDouble(6, wartoscRachunku);
preparedStatement.setInt(7, przebieg);
preparedStatement.executeUpdate();

    }
	public void insertIntoMaintenanceCostTable (String nazwaTabeli, int carID, String dataNaprawy, String nazwaUslugi, double wartoscRachunku ) throws Exception{
		
		String MaintenanceCostTable =	"INSERT INTO "
				+  nazwaTabeli
				+ "(id, carID, dataNaprawy ,nazwaUslugi ,wartoscRachunku)"
				+ " VALUES(?, ?, ?, ?, ?)"; 

PreparedStatement preparedStatement  = conn.prepareStatement(MaintenanceCostTable); 
preparedStatement.setInt(1, 0);
preparedStatement.setInt(2, carID);
preparedStatement.setString(3, dataNaprawy);
preparedStatement.setString(4, nazwaUslugi);
preparedStatement.setDouble(5, wartoscRachunku);

preparedStatement.executeUpdate();

}
public void insertIntoInsuranceCostTable (String nazwaTabeli, int carID, String dataUbezpieczenia, String nazwaUbezp, double wartoscOC, double wartoscAC) throws Exception{
		
		String InsuranceCostTable =	"INSERT INTO "
				+ nazwaTabeli
				+ "(id, carID, dataUbezpieczenia, nazwaUbezp, wartoscOC, wartoscAC)"
				+ " VALUES(?, ?, ?, ?, ?,?)"; 

PreparedStatement preparedStatement  = conn.prepareStatement(InsuranceCostTable); 
preparedStatement.setInt(1, 0);
preparedStatement.setInt(2, carID);
preparedStatement.setString(3, dataUbezpieczenia);
preparedStatement.setString(4, nazwaUbezp);
preparedStatement.setDouble(5, wartoscOC);
preparedStatement.setDouble(6, wartoscAC);


preparedStatement.executeUpdate();

}
	
	
	
	
	

}
interface JdbcHelper {
	// funkcje do "wsuwania "danych do db 
	public void createCarParameterTable(String tableName); 
	public void createFuelCostTable(String tableName); 
	public void createTableMaintenanceCost(String tableName); 
	public void createTableInsuranceCost(String tableName); 
	public void insertIntoCarParameterTable (String tableName, String marka, String model, String pojemnosc, int moc, String rodzajPaliwa); 
	public void insertIntoFuelCostTable (String nazwaTableli, int carID, String dataTankowania, String nazwaStacji, double iloscLitrow, double wartoscRachunku, int przebieg); 
	public void insertIntoInsuranceCostTable (String nazwaTabeli, int carID, String dataUbezpieczenia, String nazwaUbezp, double wartoscOC, double wartoscAC); 
	public void insertIntoMaintenanceCostTable (String nazwaTabeli, int carID, String dataNaprawy, String nazwaUslugi, double wartoscRachunku ); 
	//  do info o smamochodach 
	public List<Object> getCarBParam() ; 
	public void setCarBParam() ;
	// pobur sumy  eksploatacji i ubezpieczenia 
	public List<Object> getCarAvgSum(int z);   //  2 eks i 3 ins  tak jak  z==23  znaczy ¿e koszty eksploatacji i ubezpieczeñ 
	public void setCarAvgSum(int z, String nazwaTabeli, String dataPoczatkowa, String dataKoncowa, int x1); 
	// tu masz paliwo za równo koszty jak i sumy litrów 
	public List<Object> getCarAvgSum() ;  // bez parametru zanczy ¿e paliwo 
	public void setCarAvgSum(String nazwaTabeli, String dataPoczatkowa, String dataKoncowa, int x1); // x1==1 dla kosztow ; x1==10  dla paliw sumy itp; 
	
}
