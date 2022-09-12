import java.util.Scanner;

public class Main {
	//ticket booking
	public static void ticketbooking(Passanger p) {
		
		TicketBooker t=new TicketBooker();
		
		if(TicketBooker.available_waiting_list==0) {
			System.out.println("Ticket not available");
			return;
		}
		//check available berth
		if(p.berthpreference.equals("L")&&TicketBooker.available_lower_berth >0 ||
			p.berthpreference.equals("M") && TicketBooker.available_midle_berth >0 ||
			p.berthpreference.equals("U") && TicketBooker.available_upper_berth >0) {
				System.out.println("Prefered Berth Available..");
				
				if(p.berthpreference.equals("L")) {
					System.out.println("Lower Berth Available..");
					//call method
					t.ticketbooking(p,(TicketBooker.lower_berth_positions.get(0)),"L");
					TicketBooker.lower_berth_positions.remove(0);
					TicketBooker.available_lower_berth--;
				}
				else if(p.berthpreference.equals("M")) {
					System.out.println("Middle Berth Available..");
					//call method
					t.ticketbooking(p,(TicketBooker.middle_berth_Positions.get(0)),"M");
					TicketBooker.middle_berth_Positions.remove(0);
					TicketBooker.available_midle_berth--;
				}
				else if(p.berthpreference.equals("U")) {
					System.out.println("Upper Berth Available..");
					//call method
					t.ticketbooking(p,(TicketBooker.upper_berth_positions.get(0)),"U");
					TicketBooker.upper_berth_positions.remove(0);
					TicketBooker.available_upper_berth--;
				}
			
		}
		//preference not available -> book the available berth
		else if(TicketBooker.available_lower_berth>0)
		{
			System.out.println("Lower Berth Available..");
			//call method
			t.ticketbooking(p,(TicketBooker.lower_berth_positions.get(0)),"L");
			TicketBooker.lower_berth_positions.remove(0);
			TicketBooker.available_lower_berth--;
		}
		else if(TicketBooker.available_midle_berth>0)
		{
			System.out.println("Middle Berth Available..");
			//call method
			t.ticketbooking(p,(TicketBooker.middle_berth_Positions.get(0)),"M");
			TicketBooker.middle_berth_Positions.remove(0);
			TicketBooker.available_midle_berth--;
		}
		else if(TicketBooker.available_upper_berth>0)
		{
			System.out.println("Upper Berth Available..");
			//call method
			t.ticketbooking(p,(TicketBooker.upper_berth_positions.get(0)),"U");
			TicketBooker.upper_berth_positions.remove(0);
			TicketBooker.available_upper_berth--;
			
		}
		//not available Berth go Rac
		else if(TicketBooker.available_Rac_Tickets>0)
		{
			System.out.println("Rac Available..");
			t.addToRAC(p,(TicketBooker.rac_Positions.get(0)),"RAC");
			
			
		}
		//not available ticket go WL
		else if(TicketBooker.available_waiting_list<0)
		{
			System.out.println("Wl Available...");
			t.addToWaitingList(p,(TicketBooker.waiting_List_Positions.get(0)),"WL");
			
		}
	}

	public static void main(String[] args) {
		
		try (Scanner input = new Scanner(System.in)) {
			boolean loop=true;
			
			while(loop) {
				System.out.println(" 1.Book Ticket \n 2.Cancel Ticket \n 3.Available Ticket \n 4.Booked Tickets \n 5.Exit");
				try {
					int choise=input.nextInt();
				
					switch(choise){
					case 1:
						//Book ticket user input
					{
						System.out.println("****Book Ticket*****");
						
						System.out.println("Enter Passanger Name:");
						String name=input.next();
						
						System.out.println("Enter Passanger Age:");
						int age=input.nextInt();
						
						System.out.println("Enter Passanger Berth(Lower L,Middle M,Upper U):");
						String berthpreference=input.next();
						if(!(berthpreference=="L" ||berthpreference=="M"||berthpreference=="U")) {
							System.out.println("Enter Correct details!");
							break;
						}
						
						//create object in Passenger class
						Passanger p=new Passanger(name,age,berthpreference);
						//call booking function
						ticketbooking(p);
						
					}
						break;
					case 2:
					{
						//Cancel Tickets
						System.out.println("Enter PassengerId to Cancel Ticket");
						TicketBooker t=new TicketBooker();
						int id=input.nextInt();
						t.cancelTicket(id);
					}
						break;
					case 3:
					{
						//View Ticket Available Details
						TicketBooker t=new TicketBooker();
						t.printAvailable();
					}
						break;
					case 4:
					{
						//View Booking Passanger Details
						System.out.println("****Booked Tickets****");
						TicketBooker t=new TicketBooker();
						t.printPassanger();
					}
						break;
					case 5:
					{	
						//Exit Project
						System.out.println("Exit");
						loop=false;
					}
						break;
					default:
						break;
						
					}
					
				}
				catch(Exception e){
					System.out.println("Enter Corect Option");
				}
			}
		}

	}

	

}
	