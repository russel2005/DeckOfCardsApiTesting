package configs;

public class Endpoint {
	
	//GET
	public static final String GET_NEW_DECK = "/api/deck/new/";
	
	public static  String GET_DRAW_CARDS_FROM_DECK(String deck_id) {
		return "/api/deck/"+deck_id +"/draw/";
	}
	

}
