package olek.szaruch.nowy;

public class CarParam {
	/*id           | int         | NO   | PRI | NULL    | auto_increment |
	| marka        | varchar(20) | YES  |     | NULL    |                |
	| model        | varchar(20) | YES  |     | NULL    |                |
	| pojemnosc    | varchar(20) | YES  |     | NULL    |                |
	| rodzajPaliwa | varchar(20) | YES  |     | NULL    |                |
	| moc*/ 
	private int id; 
	private String marka; 
	private String model;
	private String pojemnosc;
	private String rodzajPaliwa;
	private int moc; 
	
	public CarParam(int id, String marka, String model, String pojemnosc, String rodzajPaliwa, int moc) {
		super();
		this.id = id;
		this.marka = marka;
		this.model = model;
		this.pojemnosc = pojemnosc;
		this.rodzajPaliwa = rodzajPaliwa;
		this.moc = moc;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getMarka() {
		return marka;
	}

	public void setMarka(String marka) {
		this.marka = marka;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public String getPojemnosc() {
		return pojemnosc;
	}

	public void setPojemnosc(String pojemnosc) {
		this.pojemnosc = pojemnosc;
	}

	public String getRodzajPaliwa() {
		return rodzajPaliwa;
	}

	public void setRodzajPaliwa(String rodzajPaliwa) {
		this.rodzajPaliwa = rodzajPaliwa;
	}

	public int getMoc() {
		return moc;
	}

	public void setMoc(int moc) {
		this.moc = moc;
	}

	public CarParam() {
		// TODO Auto-generated constructor stub
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
