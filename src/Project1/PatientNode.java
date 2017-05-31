package Project1;

public class PatientNode {
	private Patient np;
	private PatientNode link;
	
	 public PatientNode(Patient np){
		this.np = np;
		link = null;
	}
	public void setPatient(Patient np){
		this.np = np;
	}
	public Patient getPatient(){
		return np;
	}
	public void setLink(PatientNode link){
		this.link = link;
	}
	public PatientNode getLink(){
		return link;
	}
	
}


