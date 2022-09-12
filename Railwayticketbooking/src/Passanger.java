
public class Passanger {
	static int id;
	String name;
	int age;
	String berthpreference;//(L,M,U)
	int PassangerId;
	String allocated;//(L,M,U,RAC,WL)
	int number; //seatNumber
	
	Passanger(String name,int age,String berthpreference){
		this.name=name;
		this.age=age;
		this.berthpreference=berthpreference;
		this.PassangerId=id++; //Passenger id is auto increment by static
		this.allocated="";
		this.number=-1;
		
		
		
	}
	

}
