package olek.szaruch.nowy;

public class Paliwo {
private int id;
private int carId;
private String data;
private double koszt; 
private double iloscLitrow; 
private int przebieg; 
private String nazwaStacji; 

	public Paliwo(int id, int carId, String data, double koszt, double iloscLitrow, int przebieg, String nazwaStacji) {
	super();
	this.id = id;
	this.carId = carId;
	this.data = data;
	this.koszt = koszt;
	this.iloscLitrow = iloscLitrow;
	this.przebieg = przebieg;
	this.nazwaStacji = nazwaStacji;
}

	public Paliwo() {
		// TODO Auto-generated constructor stub
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getCarId() {
		return carId;
	}

	public void setCarId(int carId) {
		this.carId = carId;
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

	public double getKoszt() {
		return koszt;
	}

	public void setKoszt(double koszt) {
		this.koszt = koszt;
	}

	public double getIloscLitrow() {
		return iloscLitrow;
	}

	public void setIloscLitrow(double iloscLitrow) {
		this.iloscLitrow = iloscLitrow;
	}

	public int getPrzebieg() {
		return przebieg;
	}

	public void setPrzebieg(int przebieg) {
		this.przebieg = przebieg;
	}

	public String getNazwaStacji() {
		return nazwaStacji;
	}

	public void setNazwaStacji(String nazwaStacji) {
		this.nazwaStacji = nazwaStacji;
	}

}
