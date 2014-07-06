package Peers;

public class RFCIndex {
	int RFCno;
	String title;
	String peername; // which is Inetaddress
	int TTL;
	public int getRFCno() {
		return RFCno;
	}
	public void setRFCno(int rFCno) {
		RFCno = rFCno;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getPeername() {
		return peername;
	}
	public void setPeername(String peername) {
		this.peername = peername;
	}
	public int getTTL() {
		return TTL;
	}
	//public void setTTL(int tTL) {
	//	TTL = tTL;
	//}
 
}
