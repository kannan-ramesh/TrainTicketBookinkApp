import java.util.*;

public class TicketBooker {
	//63 berths(upper ,lower , middle)  + ( 18 RAC passengers) 
    //10 waiting list tickets ->21 L, 21 M, 21U , 18RAC, 10WL
	
	//berth(seat) allacation in temporary 1
	static int available_lower_berth=1;
	static int available_midle_berth=1;
	static int available_upper_berth=1;
	static int available_Rac_Tickets=1;
	static int available_waiting_list=1;
	
	//store data in collection
	static Queue<Integer> waiting_list=new LinkedList<>();
	static Queue<Integer> Rac_list=new LinkedList<>();
	static List<Integer> Book_list=new ArrayList<>();
	
	static List<Integer> lower_berth_positions=new ArrayList<>(Arrays.asList(1));
	static List<Integer> middle_berth_Positions=new ArrayList<>(Arrays.asList(1));
	static List<Integer> upper_berth_positions=new ArrayList<>(Arrays.asList(1));
	static List<Integer> rac_Positions=new ArrayList<>(Arrays.asList(1));
	static List<Integer> waiting_List_Positions=new ArrayList<>(Arrays.asList(1));
	
	static Map<Integer,Passanger> passangers=new HashMap<>();
	
	//ticket booking function
	public void ticketbooking(Passanger p,int berthInfo,String allotedBerth) {
		p.number=berthInfo;
		p.berthpreference=allotedBerth;
		passangers.put(p.PassangerId, p);
		Book_list.add(p.PassangerId);
		System.out.println("Booked Sucessfully");	
	}
	//Book RAC Function
	public void addToRAC(Passanger p, Integer racInfo, String allotedRAC) {
		
		p.number=racInfo;
		p.allocated=allotedRAC;
		//insert data in map
		passangers.put(p.PassangerId, p);
		Rac_list.add(p.PassangerId);
		available_Rac_Tickets--;
		rac_Positions.remove(0);
		System.out.println("----add to RAC Sucessfully------");
	}
	
	//WaitingList Book Function
	public void addToWaitingList(Passanger p, Integer waitingListInfo, String allotedWL) {
		p.number=waitingListInfo;
		p.allocated=allotedWL;
		passangers.put(p.PassangerId, p);
		waiting_list.add(p.PassangerId);
		available_waiting_list--;
		waiting_List_Positions.remove(0);
		System.out.println("----add to WL Sucessfully----");
	}

	//cancel Ticket Function
	public void cancelTicket(int id) {
		Passanger p=passangers.get(id);
		passangers.remove(Integer.valueOf(id));
		Book_list.remove(Integer.valueOf(id));
		//one ticket remove free location 
		int positionBooked=p.number;
		System.out.println("Canceled Sucessfully");
		
		//add the free position (L,M,U) 
		if(p.allocated.equals("L"))
		{
			available_lower_berth++;
			lower_berth_positions.add(positionBooked);
		}else if(p.allocated.equals("M"))
		{
			available_midle_berth++;
			upper_berth_positions.add(positionBooked);
		}
		else if(p.allocated.equals("U"))
		{
			available_upper_berth++;
			upper_berth_positions.add(positionBooked);
		}
		
		//check RAC there
		if(Rac_list.size()>0)
		{
			Passanger passangerFromRAC=passangers.get(Rac_list.poll());
			int positionRAC=passangerFromRAC.number;
			rac_Positions.add(positionRAC);
			Rac_list.remove(Integer.valueOf(passangerFromRAC.PassangerId));
			available_Rac_Tickets++;
		
			
			if(waiting_list.size()>0)
			{
				Passanger passangerWL=passangers.get(waiting_list.poll());
				int positionWL=p.number;
				waiting_List_Positions.add(positionWL);
				waiting_list.remove(Integer.valueOf(passangerWL.PassangerId));
				
				passangerWL.number=rac_Positions.get(0);
				passangerWL.allocated="RAC";
				rac_Positions.remove(0);
				Rac_list.add(passangerWL.PassangerId);
				available_waiting_list++;
				available_Rac_Tickets--;
			}
		Main.ticketbooking(passangerFromRAC);
		
		}
	}

	public void printAvailable() {
		System.out.println("AvailableLowerBerths"+available_lower_berth);
		System.out.println("AvailableMiddleBerths"+available_midle_berth);
		System.out.println("AvailableUpperBerths"+available_upper_berth);
		System.out.println("AvailableRAC"+available_Rac_Tickets);
		System.out.println("AvailableWaitingList"+available_waiting_list);
		
		
	}

	public void printPassanger() {
		if(passangers.size()==0) {
			System.out.println("No Passenger Available");
			return;
		}
		for(Passanger p:passangers.values()) {
			System.out.println("PassengerId:"+p.PassangerId);
			System.out.println("Passenger Name:"+p.name);
			System.out.println("Passenger age:"+p.age);
			System.out.println("Status:"+p.number+""+p.allocated+""+p.berthpreference);
			System.out.println("-----------------------------------");
			
		}
		
	}
	

}
