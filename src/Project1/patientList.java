package Project1;

import java.io.*;
import java.text.*;
import java.util.*;

public class patientList {

	private List<Patient> patientList= new LinkedList<Patient>();
	private PatientNode head;
	private PatientNode tail;
	 private PatientNode location;
	private patientList theList;
	private int size;
	private boolean found;
	public patientList(){
		tail = null;
		head = null;
		size = 0;
	}
	public void insert(Patient element) { // inserts the new patient to the end of the list.
		PatientNode a = new PatientNode(element);
		PatientNode b = head;
		if (head == null){
			head = a;
		}
		else{
			while(b.getLink() != null){
				b = b.getLink();

			}
			b.setLink(a);
			tail = a;

		}
		size++;

	}
	public int size() { //gets the numer of objects stored on the list
		int count = 0;
		PatientNode node;
		node = head;
		while (node != null){
			count = count +1;
			node = node.getLink();
		}
		return count;
	}
	public List<Patient> getList(){//returns the list 
		return patientList;
	}


	public void clear(){ //completely clears the list
		head = null;
	}
	public boolean contains(Patient element) { // returns true or false depending on if patient is on list
		PatientNode node;
		node = head;
		boolean found = false;
		boolean moreToSearch;
		moreToSearch = (node!=null);
		while(moreToSearch && !found){
			if(element==(node.getPatient()))
				found = true;
			else{
				node = node.getLink();
				moreToSearch = (node!=null);
			}
		}
		return found;
	}
	public void find1(String ID){//just lets you know if there or not
		location = head;
		found = false;
		while(location != null){
			if(location.getPatient().getID().equalsIgnoreCase(ID)){
				found = true;
				return;
			}
			else
				location = location.getLink();
		}

	}
	public PatientNode find(String ID) {//finds specified patient based off of ID inputed

		PatientNode search = head;
		while(search!= null){
			if(search.getPatient().getID().equalsIgnoreCase(ID))
				return search;
			else
				search = search.getLink();
		}
		return null;
	}
	
	public void remove(String element)// removes the specified patient by ID
	{	
		this.find1(element);
		if(this.found==false)
			return;
		PatientNode delete = head;  
		PatientNode after = delete.getLink();
		PatientNode rr = null;
		while (delete!=null) 
		{
			if(delete.getPatient().getID().equals(element))
			{
				if(delete == head)
				{
					this.head=delete.getLink();
					return;
				}
				while(delete.getLink()!=null && !delete.getLink().getPatient().getID().equalsIgnoreCase(element))
				{
					delete = delete.getLink();
				}
				if (delete.getLink()==null){
					if(delete.getPatient().getID().equalsIgnoreCase(element)){
						return;
					}
					else {
						if (delete.getLink().getLink()==null){
							delete.setLink(null);
							this.tail = delete;
							this.size--;
						}
						else {
							delete.setLink(delete.getLink().getLink());
							this.size--;
						}



					}
				}
			}
		}
	}



		public static void main(String[] args) throws IOException, ParseException {
			String fileName = "C:/Users/maste/Documents/patientfiles.txt";
			System.out.println("C:/Users/maste/Documents/patientfiles.txt");
			new patientList(fileName);

		}
		public patientList(String IFILE) throws IOException, ParseException{
			this.theList=loadFromFile(IFILE);
			System.out.println(theList);
			start();

		}

		private void start()throws IOException { // displays the list and waits for input
			Scanner kb = new Scanner(System.in);
			while (true){
				displayMenu();
			int option = kb.nextInt();
			switch(option){
			case 1 :
				displayList();
				break;

			case 2:
				addPatient(kb);
				break;
			case 3:
				getPatient(kb);
				break;
			case 4:
				deletePatient(kb);
				break;
			case 5:
				averageAge();
				break;
			case 6:
				ypInfo();
				break;
			case 7:
				notificationList();
				break;
			case 8:
				quit(kb);
				kb.close();
				return;

			}
		}
	}

	private void quit(Scanner kb) throws IOException{//exits the program and saves file
		  kb.nextLine();
          System.out.println("Do you want to save a new file? y/n ");
         
          while (true) {
              String input = kb.nextLine();
             
              if (input.equalsIgnoreCase("Y")) {
                  System.out.print("Enter output file : ");
                  writeBackToFile(kb.nextLine(), theList);
                  break;
              }
              else if (input.equalsIgnoreCase("N")) {
                  return;
              }
              else {
                  System.out.println("either Y or N");
                  
              }
          }
      }
	private void writeBackToFile(String name, patientList theList2) throws IOException {//program to write to file
		if(name == null || name.isEmpty())
			return;
		FileOutputStream bw = new FileOutputStream(name);
		ObjectOutputStream aw = new ObjectOutputStream(bw);
		for(Patient newPatient:theList2.getList()){
			String Name = newPatient.getName(), ID = newPatient.getID(), Address = newPatient.getAddress();
			int Height = newPatient.getHeight();
			double weight = newPatient.getWeight();
			Date dob = newPatient.getDob(), IV = newPatient.getIV(), LV = newPatient.getLV();
			aw.writeChars("Name: "+ Name + " ID: " + ID +" Address: " + Address + " Height: "+ Height + " Weight" + weight + " Date of Birth: " + 
			dob+ " Initial visit: " + IV + " Last Visit: " + LV);
			bw.close();
		}
	}
	private void notificationList() {
return;
	}
	private void ypInfo() {
return;
	}
	private void averageAge() {//calculates the average age of the patients
		int age=0;
		PatientNode a = head;
		while (a != null){
			age = age + a.getPatient().get_Age();
			a = a.getLink();
		}
		
		age = age/theList.size();
		System.out.println(age);
	}
	private void deletePatient(Scanner kb) {//deletes patient by id inputed
		kb.nextLine();
		System.out.println("enter id:");
		String ID = kb.nextLine();
		theList.remove(ID);
	}
	private void getPatient(Scanner kb) {//finds patient by id inputed
		kb.nextLine();
		System.out.println("enter id:");
		String ID = kb.nextLine();
		theList.find1(ID);
		System.out.println( "found = " + theList.found);
		PatientNode curP = theList.find(ID);
		if(curP != null){
			System.out.println(curP.getPatient().toString());
		}
		else
			System.out.println("Patient not found");
		System.out.println(System.lineSeparator());
	}
	private void displayList() {//displays each patient
		if(theList == null)
			return;
		PatientNode runner = theList.head;
		int c = 1;
		while(runner != null){

			System.out.println(c+". Name: " +runner.getPatient().getName() + " ID: "+runner.getPatient().getID());
			runner = runner.getLink();
			c++;
		}

	}
	private void addPatient(Scanner kb) {//adds new patient
		DateFormat format = new SimpleDateFormat("MM/dd/yyyy");
		String Name ="", ID="", Address="";
		int height;
		double weight;
		Date dob = null, iv = null, lv= null;
		kb.nextLine();
		System.out.println("enter patients name: ");
		Name=kb.nextLine();
		System.out.println("Enter a Patient ID: ");
		ID = kb.nextLine();
		theList.find1(ID);
		if(theList.found==true){
			System.out.println("Enter a Patient ID: ");
			ID = kb.nextLine();


		}
		System.out.println("enter patients address: ");
		Address=kb.nextLine();
		System.out.println("enter patients height: ");
		height=kb.nextInt();
		System.out.println("enter patients weight: ");
		weight=kb.nextDouble();
		System.out.println("enter patients date of birth:(MM/DD/YYYY) ");
		while (dob == null){
			kb.nextLine();
			String line = kb.nextLine();
			try{
				dob = format.parse(line);
			}
			catch (ParseException e){
				System.out.println("sorry not valid");
			}
		}
		System.out.println("enter patients initial visit(MM/DD/YYYY): ");
		while (iv == null){
			String line = kb.nextLine();
			try{
				iv = format.parse(line);
			}
			catch (ParseException e){
				System.out.println("sorry not valid");
			}
		}
		System.out.println("enter patients last visit(MM/DD/YYYY): ");
		while (lv == null){
			String line = kb.nextLine();
			try{
				lv = format.parse(line);
			}
			catch (ParseException e){
				System.out.println("sorry not valid");
			}
		}
		Patient nPatient = new Patient(Name, ID, Address, height, weight, dob, iv, lv);
		theList.insert(nPatient);
	}
	public patientList loadFromFile(String fileLoc)throws IOException, ParseException{//loads patients from external file

		DateFormat format = new SimpleDateFormat("MM/DD/YYYY");

		patientList theList = new patientList();
		BufferedReader bInput = new BufferedReader(new FileReader(fileLoc));

		String IL;
		while((IL = bInput.readLine())!= null){
			String[] Patient = IL.split(",");

			String Name = Patient[0];
			String ID = Patient[1];
			String address = Patient[2];
			int Height = Integer.valueOf(Patient[3]);
			double Weight = Double.valueOf(Patient[4]);
			Date DOB = format.parse(Patient[5]);
			Date IV = format.parse(Patient[6]);
			Date LV = format.parse(Patient[7]);

			Patient newPatient = new Patient(Name, ID, address, Height, Weight, DOB, IV, LV);
			if(head == null){
				head = new PatientNode(newPatient);
				theList.insert(newPatient);

			}
			else{
				tail = new PatientNode(newPatient);
				theList.insert(newPatient);
			}

		}
		bInput.close();
		return theList;

	}
	public void displayMenu() {  
		System.out.println(System.lineSeparator());
		System.out.println(System.lineSeparator());
		System.out.println("Option Menu:");
		System.out.println("1.Display List");
		System.out.println("2.Add a new patient");
		System.out.println("3.Show info for a patient");          
		System.out.println("4.Delete a patient");          
		System.out.println("5.Show average age of patient");          
		System.out.println("6.Show info of youngest patient");
		System.out.println("7.Show notification list");
		System.out.println("8.Quit");
		System.out.print("Enter : " );
	}


}



