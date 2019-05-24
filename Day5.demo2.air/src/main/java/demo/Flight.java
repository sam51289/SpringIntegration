package demo;

public class Flight {
	private String fromcity ;
	private String tocity ;
	private String date;
	public Flight(){}
	public Flight(String from, String to, String date) {
		super();
		this.fromcity = from;
		this.tocity = to;
		this.date = date;
	}
	public String getFromcity() {
		return fromcity;
	}
	public void setFromcity(String fromcity) {
		this.fromcity = fromcity;
	}
	public String getTocity() {
		return tocity;
	}
	public void setTocity(String tocity) {
		this.tocity = tocity;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	@Override
	public String toString() {
		return "Flight [fromcity=" + fromcity + ", tocity=" + tocity + ", date=" + date + "]";
	}
	
}
