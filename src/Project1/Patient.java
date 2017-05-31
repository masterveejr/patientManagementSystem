package Project1;

import java.time.LocalDate;
import java.util.Date;

public class Patient {
	private String name;
	private String ID;
	private String address;
	 private int height;
	private double weight;
	private Date dob;
	private Date IV;
	private Date LV;
	
	public Patient(String name, String ID, String address, int height, double weight, Date dob, Date IV, Date LV){
		this.name=name;
		this.ID=ID;
		this.address=address;
		this.height=height;
		this.weight=weight;
		this.dob=dob;
		this.IV=IV;
		this.LV=LV;
	}
	public String getID() {
		return ID;
	}
	public void setID(String iD) {
		ID = iD;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public int getHeight() {
		return height;
	}
	public void setHeight(int height) {
		this.height = height;
	}
	public double getWeight() {
		return weight;
	}
	public void setWeight(double weight) {
		this.weight = weight;
	}
	public Date getDob() {
		return dob;
	}
	public void setDob(Date dob) {
		this.dob = dob;
	}
	public Date getIV() {
		return IV;
	}
	public void setIV(Date iV) {
		IV = iV;
	}
	public Date getLV() {
		return LV;
	}
	public void setLV(Date lV) {
		LV = lV;
	}
	
	@SuppressWarnings("deprecation")
	public int get_Age(){
		Date DOB = this.getDob();
		Date currentDate = new Date(System.currentTimeMillis());
		int age = 0;
		age = (currentDate.getYear()-DOB.getYear());
		return age;
	}
	public int get_time_as_patient(){
		Date IV = this.getIV();
		Date currentDate = new Date(System.currentTimeMillis());
		int tap = 0;
		tap = (currentDate.getYear()-IV.getYear());
		return tap;
	}
	public int get_time_since_last_visit(){
		Date LV = this.getLV();
		Date currentDate = new Date(System.currentTimeMillis());
		int tslv = 0;
		tslv = (currentDate.getYear()-LV.getYear());
		return tslv;
	}
	@Override
	public String toString(){
		return "PatientInfo name=" + name + ", id=" + ID + ", address=" + address + ", height=" + height + ", weight="
	               + weight + ", dateOfBirth=" + dob + ", intialVisit=" + IV + ", lastVisit=" + LV;
	}
}
