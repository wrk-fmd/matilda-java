package at.wrk.fmd.matilda.pojo;

import java.util.ArrayList;

public class VeranstaltungBuchungWrapper
{
	private ArrayList<VeranstaltungBuchung> buchungList;
	
	public ArrayList<VeranstaltungBuchung> getBuchungList(){
		return buchungList;
	}
	public void setBuchungList(ArrayList<VeranstaltungBuchung> veranstaltungBuchungs) {
		this.buchungList = veranstaltungBuchungs;
	}
}
