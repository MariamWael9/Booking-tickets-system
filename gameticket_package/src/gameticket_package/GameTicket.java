
package gameticket_package;

import java.util.*;
import javax.swing.*;
import  java.util.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;

public class GameTicket extends Exception {

    /**an array list carries all the exist fans data*/
    static ArrayList<Fan> allFans = new ArrayList<Fan>();
    /**an array list carries all the exist bets data*/
    static ArrayList<bet> allbets = new ArrayList<bet>();
    /**an array list carries all the reserved tickets data*/
    static ArrayList<ticket> reservedTickets = new ArrayList<ticket>();
    /**an array list carries all the reserved seats data*/
    static ArrayList<Seat> reservedSeats = new ArrayList<Seat>();
    /**an array list carries all the data of seats which were canceled or exchanged*/
    static ArrayList<Seat> CanceledSeats = new ArrayList<Seat>();
    /**an array carries all the data of the matches*/
    static Game [] games = new Game [20];
    static Scanner scanner = new Scanner(System.in);
    
    /**Static Method 
     *this method has values for the objects (game, fan,bet,ticket,....)defined as hard code */
    static void Hard_Codes() {
    	
    	/**run-time Polymorphism & Overloading  */
        games[0] = new international("Egypt" ,"saudi arabia" ,"2020/6/10 18:00:00","el-Malek Fahd Stadium", 100,200 ,300,"Egypt","3-1", "Saudi Arabia");
        games[1] = new international("Germany" ,"France" ,"2020/6/15 17:30:00" ,"Allianz arena Stadium", 15,25 , 35, "France","2-5", "Germany" ,5000,3000 ,1500);
        games[2] = new national ("Al-Ahly" ,"Somha" ,"2020/7/12 20:00:00" ,"Cairo Stadium", 100,200 , 300, "Al-Ahly","4-2",1500,1000 ,500);
        games[3] = new national ("El-Ismailiy" ,"Petrojet" ,"2020/6/25 19:30:00" ,"Petrojet Stadium",15 ,25,35, "El-Ismailiy","1-0");
        
        Fan f1 = new Fan("Mariam",1999);      //This fan's id = 2020101
        allFans.add(f1);
        Fan f2 = new Fan("Youssef",2004);     //This fan's id = 2020102
        allFans.add(f2);
        Fan f3 = new Fan("Ali",1997);         //This fan's id = 2020103
        allFans.add(f3);
        Fan f4 = new Fan("Nour",2005);        //This fan's id = 2020104
        allFans.add(f4);
        
        Seat s = new Seat("First",games[0].getSEAT1().getprice(),games[0].getcodeOFgame(),games[0].getSEAT1().seat_num());
        reservedSeats.add(s);
        ticket t = new ticket(games[0],f1.getFANid(),"First",games[0].getSEAT1().getprice(),s);
        allFans.get(0).addTicket(t);
        reservedTickets.add(t);
        
        s = new Seat("Second",games[1].getSEAT2().getprice(),games[1].getcodeOFgame(),games[1].getSEAT2().seat_num());
        reservedSeats.add(s);
        t = new ticket(games[1],f1.getFANid(),"Second",games[1].getSEAT2().getprice(),s);
        allFans.get(0).addTicket(t);
        reservedTickets.add(t);
        
        s = new Seat("First",games[2].getSEAT1().getprice(),games[2].getcodeOFgame(),games[2].getSEAT1().seat_num());
        reservedSeats.add(s);
        t = new ticket(games[2],f2.getFANid(),"First",games[2].getSEAT1().getprice(),s);
        allFans.get(1).addTicket(t);
        reservedTickets.add(t);
        
        s = new Seat("Third",games[3].getSEAT3().getprice(),games[3].getcodeOFgame(),games[3].getSEAT3().seat_num());
        reservedSeats.add(s);
        t = new ticket(games[3],f2.getFANid(),"Third",games[3].getSEAT3().getprice(),s);
        allFans.get(1).addTicket(t);
        reservedTickets.add(t);
        
        s = new Seat("First",games[2].getSEAT1().getprice(),games[2].getcodeOFgame(),games[2].getSEAT1().seat_num());
        reservedSeats.add(s);
        t = new ticket(games[2],f3.getFANid(),"First",games[2].getSEAT1().getprice(),s);
        allFans.get(2).addTicket(t);
        reservedTickets.add(t);
        
        
        s = new Seat("Second",games[1].getSEAT2().getprice(),games[1].getcodeOFgame(),games[1].getSEAT2().seat_num());
        reservedSeats.add(s);
        t = new ticket(games[1],f3.getFANid(),"Second",games[1].getSEAT2().getprice(),s);
        allFans.get(2).addTicket(t);
        reservedTickets.add(t);
        
        bet b = new bet (true , games[0]);    //bet id =1 and it's a right guessed bet
        allbets.add(b);
        b = new bet (true , games[1]);        //bet id =2 and it's a right guessed bet
        allbets.add(b);
        b = new bet (true , games[2]);        //bet id =3 and it's a right guessed bet
        allbets.add(b);
        b = new bet (false , games[0]);       //bet id =4 and it's a wrong guessed bet
        allbets.add(b);
        b = new bet (false , games[1]);       //bet id =5 and it's a wrong guessed bet
        allbets.add(b);
        b = new bet (false , games[2]);       //bet id =6 and it's a wrong guessed bet
        allbets.add(b);
        
    }  
   
    /**Static Method 
    *Print all the games which still have available seats to be booked and If there is cancellation for any seat, this prints these seats details*/
    static void Print_AvailableSeats () {
    	for(int i=0 ; i < Game.numberOFgames() ; i ++)
        {
            games[i].Display_availableSeats();
        }
        if (!CanceledSeats.isEmpty()) {
        	System.out.println("There are seats in the front which were canceled by there old owners here:- ");
        	for(int i=0 ; i < CanceledSeats.size() ; i ++)
            {
        		System.out.println( (i+1) + ")the game code is:- " + CanceledSeats.get(i).Game_code + "  the seat is in the " + CanceledSeats.get(i).SeatCategory + " category" ); 
            }
        }
    }
    
    /**Static Method 
     *This method takes inputs (name ,year of birth)from the user to create a new fan and prints the fan's id 
     * @throws InputMismatchException when the user writes the name in the birth year place and writes the birth year in the name place*/
    static void New_Fan() {
    	System.out.println("Please enter your name");
        try {
        String NAME = scanner.next();
        System.out.println("Please enter the year you were born in");
        int birthyear = scanner.nextInt();
        Fan fan = new Fan (NAME ,birthyear);
        System.out.println("Your fan ID is :-  " + Fan.getFanID());
        allFans.add(fan);
        }
        
      /**Java defined exception*/
        catch(InputMismatchException e) {
        	JOptionPane.showMessageDialog(JOptionPane.getRootFrame(),"You entered the inputs in the wrong place, please try again..", "Wrong input type",JOptionPane.ERROR_MESSAGE);
        }
    }
    
    /**Static Method  
     *Print the data of all the games available to the user to make a bet and creates a new object of bet with the data the user chooses and save it in the 'allbets' array list 
     *then prints the bet id to the user*/
    static void Make_aBet() {
	System.out.println("choose the number of the game you want to make a bet for ");
         for(int i=0 ; i < Game.numberOFgames(); i ++)
        {
            System.out.println("game number " + (i+1) + " between " + games[i].getTeam1() +" & "+ games[i].getTeam2());
        }
        int chooseGame = scanner.nextInt();
        System.out.println("Note:- if you get 3 right gusses you can use the 3 bet IDs to get a match for free");
        System.out.println("choose number of the team you think it will win 1)" + games[(chooseGame-1)].getTeam1() +" 2) "+ games[(chooseGame-1)].getTeam2());
        int betWinner = scanner.nextInt();
        if(betWinner ==1 )
        {
            if (games[(chooseGame-1)].getTeam1().equals(games[(chooseGame-1)].getWinner())){
            	bet BET = new bet (true ,games[(chooseGame-1)] );
                allbets.add(BET);
            }
            else {
            	bet BET = new bet (false ,games[(chooseGame-1)] );
              allbets.add(BET);
            }
        }
        else if (betWinner ==2 ){
            if (games[(chooseGame-1)].getTeam2().equals(games[(chooseGame-1)].getWinner())){
            	bet BET = new bet (true ,games[(chooseGame-1)] );
                allbets.add(BET);
            }
            else {
            	bet BET = new bet (false ,games[(chooseGame-1)] );
              allbets.add(BET);
            }
        }
        System.out.println("Your bet ID is :- " + bet.getBetID());
	}
    
    /**Static Method 
     *checks the result of a bet by the bet id in the 'allbets' array list, If id exists it prints the result to the user
     *If bet id not exists it print a message*/
    static void Check_aBet_resault() {
    	boolean found = false;
        System.out.println("Please enter your bet ID :- ");
        int betId = scanner.nextInt();
        for (int i =0 ; i< allbets.size(); i++)
        {
            if(allbets.get(i).getBETid() == betId)
            {
                allbets.get(i).check_betResault();
                found = true ;
                break;
            }
        }
        if (found == false)
         System.out.println("The bet ID you entered wasn't found, please check the ID then try again.");
    }
    
    /**Static Method 
     *Gets 3 bet id from user to check if the 3 were guessed right to get a free ticket or not*/
    static void Check_3Right_Bets(int ticketPrice) {
	System.out.println("Enter your first bet id :- ");
       	int Bet1 = scanner.nextInt();
       	System.out.println("Enter your second bet id :- ");
       	int Bet2 = scanner.nextInt();
       	System.out.println("Enter your third bet id :- ");
       	int Bet3 = scanner.nextInt();
       	boolean isFree = false;
       	boolean bet1 = false , bet2 = false ;
       	for (int B1 = 0 ; B1< allbets.size() ;B1 ++ ) {
       		if(allbets.get(B1).BETid == Bet1 && allbets.get(B1).isBetResult()== true) {
       			allbets.remove(B1);
       			for (int B2 = 0 ; B2< allbets.size() ;B2 ++ ) {
               		if(allbets.get(B2).BETid == Bet2 && allbets.get(B2).isBetResult()== true) {
               			allbets.remove(B2);
               			for (int B3 = 0 ; B3< allbets.size() ;B3 ++ ) {
                       		if(allbets.get(B3).BETid == Bet3 && allbets.get(B3).isBetResult()== true) {
                       			allbets.remove(B3);
                       			System.out.println("Congratulations, your ticket is free now ");
                                   System.out.println("your ticket is booked...... " );
                                   bet1 = true ;
                                   bet2 = true ;
                                   isFree = true;
                                   break;
                       		}
                       	}
               			if(bet2 == true)
               				break;
               		}
               	}
       		}
       		if(bet1 == true)
       			break;
       	}
       if (!isFree) {
           System.out.println("Unfortunately, your 3 bets weren't right. Your ticket price is:- " + ticketPrice);
           System.out.println("your ticket is booked...... " );
           }
	}
    
    /**Static Method 
     *@return true if the seat was reserved or not
     *@param the order of the game in the array and its code
     *reserve a seat from one of the seats in the 'cancelledSeats' array list
     *add the seat to the 'reservedSeats' array list and but it in a new object of ticket, add the ticket to the fan's list of reserved tickets then remove it from the 'cancelledSeats' array list
     *then it prints the seat number to the user 
     *it calls method "Check_3Right_Bets" to check if the ticket will be free or not to print it price*/
    static boolean Reserve_aCancelled_Seat(int i , int GAMEcode) {
	boolean TicketBooked = false;
	System.out.println("Please enter your id ");
        int ID = scanner.nextInt();
	for (int m =0 ; m< Game.numberOFgames(); m++)
        {
            if(games[m].getcodeOFgame() == GAMEcode)
            {
            	for (int j =0 ; j < allFans.size() ; j ++)
                {
                    if  (allFans.get(j).getFANid()== ID){
                ticket buyticket = new ticket (games[m],ID,CanceledSeats.get(i).SeatCategory,CanceledSeats.get(i).seat_Price,CanceledSeats.get(i));
                reservedSeats.add(CanceledSeats.get(i));
                reservedTickets.add(buyticket);
                allFans.get(j).allTickets.add(buyticket);
                CanceledSeats.remove(i);
                System.out.println("your seat number is:- " + buyticket.seat.seat_number);
                System.out.println("If you have 3 right guessed bets press (1) for yes to make your ticket free, Press (2) if you don't have" );
                int HaveBet = scanner.nextInt();
                if(HaveBet == 1) {
             	   Check_3Right_Bets( buyticket.ticketPrice);
                }
                else {
                System.out.println("your ticket price is:- " + buyticket.ticketPrice);
                System.out.println("your ticket is booked...... " );
                }
                TicketBooked = true;
                break;
                    }
                }
            }
            if (TicketBooked == true)
            	break;
        }
		return TicketBooked;
	}
    
    /**Static Method 
     *@param the order of the game in the "games"array
     *prints the available category in the match */
    static void Print_available_Category(int m) {
	games[m].booking_msg();
        System.out.println("press (1) for the 1st category if it's available or (2) for the 2nd  category if it's available or (3) for the 3rd category if it's available");
        System.out.println("There are available seats in ");
        if (games[m].getSEAT1().SeatsAvailablity() != 0){
            System.out.println("1st category");
        }
        if(games[m].getSEAT2().SeatsAvailablity() != 0){
            System.out.println("2nd category");
        }
        if(games[m].getSEAT3().SeatsAvailablity() != 0){
            System.out.println("3rd category");
        }
	}
    
    /**Static Method 
     *@param the order of the game in the "games" array , the category of the seat , it's price and number
     *@return true if the seat was reserved and game code found
     *this method reserves by creating a new object of seat and put it in a new object of ticket and put it in the fan's reserved ticket list
     *then prints the seat number 
     *calls method Check_3Right_Bets to check if the ticket is free or not to print it price*/
    static boolean reserve_newSeat(int m ,String category,int Seat_price ,int Seat_number) {
	boolean GameIDfound = false ;
        System.out.println("Please enter your Fan id ");
        int ID = scanner.nextInt();
        boolean fanFound = false;
        for (int j =0 ; j < allFans.size() ; j ++)
        {
            if  (allFans.get(j).getFANid()== ID){
            	if(category == "First") {
                    games[m].getSEAT1().SeatsAvailablity();
            	}
            	else if (category == "Second") {
            		games[m].getSEAT2().SeatsAvailablity();
            	}
            	else if(category == "Third") {
            		games[m].getSEAT3().SeatsAvailablity();
            	}
               Seat seat = new Seat(category,Seat_price,games[m].getcodeOFgame(),Seat_number);
               ticket buyticket = new ticket (games[m],ID,category,Seat_price,seat);
               reservedSeats.add(seat);
               reservedTickets.add(buyticket);
               allFans.get(j).allTickets.add(buyticket);
               System.out.println("your seat number is:- " + Seat_number);
               fanFound = true;
               System.out.println("If you have 3 right guessed bets press (1) for yes to make your ticket free, Press (2) if you don't have" );
               int HaveBet = scanner.nextInt();
               if(HaveBet == 1) {
            	   Check_3Right_Bets( buyticket.ticketPrice);
               }
               else {
                   System.out.println("your ticket price is:- " + buyticket.ticketPrice);
                   System.out.println("your ticket is booked...... " );
                   }
                   GameIDfound = true;
                   break;
            }
        }
    if (fanFound == false )
    	System.out.println("You enterd a wrong fan id, Please try again " );
    
		return GameIDfound;
	}
    
    /**Static Method 
     *this method takes the game code,if exists then asks the user if he want a new seat or a seat from the canceled seats if exists
     *then This method calls one of the two methods Reserve_aCancelled_Seat and reserve_newSeat to reserve a ticket, depending on the user choice to reserve a ticket
     *@throws user defined exception which is thrown if the user choice for category was out of the choices*/
    static void Reserve_aNew_ticket() {
    	Print_AvailableSeats (); 
        System.out.println("Please enter the code of the game you want to buy its ticket");
        boolean GameIDfound = false;
        boolean TicketBooked = false;
        int GAMEcode = scanner.nextInt();
        for (int i = 0 ; i < CanceledSeats.size() ; i++) {
        	if(CanceledSeats.get(i).Game_code == GAMEcode) {
        		System.out.println("there is a seat in the front in the " +CanceledSeats.get(i).SeatCategory +"category If you want to book it press (1) if you don't want it press (2)");	
        		int CancelledORnewSeat = scanner.nextInt();
        		if(CancelledORnewSeat == 1) {
        			TicketBooked = Reserve_aCancelled_Seat( i , GAMEcode);
        			if (TicketBooked == true)
        	        	break;
        		}
        		else  
        			break; 
        	}
        	if (TicketBooked == true)
            	break;
        }
        
        for (int m =0 ; m< Game.numberOFgames(); m++)
        {
        	if (TicketBooked == true)
            	break;
            if(games[m].getcodeOFgame() == GAMEcode)
            {
            	Print_available_Category(m);
                try {
                int categoryChoice = scanner.nextInt();
                if (categoryChoice ==1 && games[m].getSEAT1().SeatsAvailablity() != 0){
                	GameIDfound=reserve_newSeat( m ,"First",games[m].getSEAT1().getprice(), games[m].getSEAT1().seat_num());
                }
                else if (categoryChoice ==2 && games[m].getSEAT2().SeatsAvailablity() != 0){
                	GameIDfound=reserve_newSeat( m ,"Second",games[m].getSEAT2().getprice(), games[m].getSEAT2().seat_num());
            
                }
                else if (categoryChoice ==3 && games[m].getSEAT3().SeatsAvailablity() != 0){
                	GameIDfound=reserve_newSeat( m ,"Third",games[m].getSEAT3().getprice(), games[m].getSEAT3().seat_num());
                    
                }
                else 
                {
                	GameIDfound = true ;
                	OutOfTheChoicesRange_Exception ex = new OutOfTheChoicesRange_Exception("You entered a number not in the choices");
                       throw ex; 
                }
            }
            /**Java user defined exception*/
            catch(OutOfTheChoicesRange_Exception M) {
            	JOptionPane.showMessageDialog(JOptionPane.getRootFrame(),M.getMessage(),"Out of choices range input" , JOptionPane.ERROR_MESSAGE);
            	break;
            }
            }
        }
        if(GameIDfound == false && TicketBooked == false)
        System.out.println("The game code you entered wasn't found, please check the code then try again.");  	    			 
    
	}
   
    /**Static Method 
     *this method is used when the user wants to upgrade his ticket it exchange the old seat with the new one
     *then prints the number of the new seat*/
    static void Xchange_Seats(int i) {
	for (int k=0 ; k < reservedTickets.size(); k++)
        {
	    if(reservedTickets.get(k).seat == reservedSeats.get(i)) {
		CanceledSeats.add(reservedSeats.get(i));
		reservedSeats.remove(i);
		reservedTickets.get(k).seat =  reservedSeats.get(reservedSeats.size()-1);
		System.out.println("your new seat number is:-  " + reservedSeats.get(reservedSeats.size()-1).seat_number);
		break;
			  }
		}
	}
    
    /**Static Method 
     *this method prints if the categories are available to upgrade depending on the old seats category
     *then it exchange the old seat with the new one using method Xchange_Seats */
    static void Upgrade_ticket() {
	System.out.println("please enter the code of the game you want to upgrade its ticket");
    	int Game_Code = scanner.nextInt();
    	System.out.println("please enter your seat number ");
    	int seat_number = scanner.nextInt();
    	for (int i=0 ; i < reservedSeats.size() ; i++){
    		if ((Game_Code == reservedSeats.get(i).Game_code) && (seat_number == reservedSeats.get(i).seat_number)) {
    			if(reservedSeats.get(i).SeatCategory == "First") {
    				System.out.println("Sorry but you can't upgrade the first category ");
    			}
    			else if (reservedSeats.get(i).SeatCategory == "Second") {
    				boolean found = false;
    				for (int j =0 ; j< Game.numberOFgames(); j++)
    	            {
    	                if(games[j].getcodeOFgame() == Game_Code)
    	                {
    	                	if (games[j].getSEAT1().SeatsAvailablity() != 0){
    	                	   System.out.println("press (1) to upgrade your ticket to the 1st category ");
    	                	   found = true ;
    	                	   break;
    	                	}
    	                }
    			    }
    				
    				if (found == true) {
    					int upgradeChoice = scanner.nextInt();
    					if (upgradeChoice == 1) {
    						for (int j =0 ; j< Game.numberOFgames(); j++){
    							if(games[j].getcodeOFgame() == Game_Code)
            	                {
    							 Seat seat = new Seat("First",games[j].getSEAT1().getprice(),games[j].getcodeOFgame(),games[j].getSEAT1().seat_num());
    							 reservedSeats.add(seat);
    							 break;
            	                }
    						}
    						Xchange_Seats(i);		
    					}
    					else
        					System.out.println("You choosed wrong number"); 
    				}
    				
    		    }
                else if (reservedSeats.get(i).SeatCategory == "Third") {
                	boolean found = false;
    				for (int j =0 ; j< Game.numberOFgames(); j++)
    	            {
    	                if(games[j].getcodeOFgame() == Game_Code)
    	                {
    	                	if (games[j].getSEAT1().SeatsAvailablity() != 0 ){
    	                	   System.out.println("press (1) to upgrade your ticket to the 1st category  ");
    	                	if (games[j].getSEAT2().SeatsAvailablity() != 0) 
    	                		System.out.println("press (2) to upgrade your ticket to the 2nd category ");
    	                	   found = true ;
    	                	   break;
    	                	}
    	                }
    			    }
    				if (found == true) {
    					int upgradeChoice = scanner.nextInt();
    					if (upgradeChoice == 1) {
    						for (int j =0 ; j< Game.numberOFgames(); j++){
    							if(games[j].getcodeOFgame() == Game_Code)
            	                {
    							 Seat seat = new Seat("First",games[j].getSEAT1().getprice(),games[j].getcodeOFgame(),games[j].getSEAT1().seat_num());
    							 reservedSeats.add(seat);
    							 break;
            	                }
    						}
    						Xchange_Seats(i);		
    					}
    					else if (upgradeChoice == 2) {
    						for (int j =0 ; j< Game.numberOFgames(); j++){
    							if(games[j].getcodeOFgame() == Game_Code)
            	                {
    							 Seat seat = new Seat("Second",games[j].getSEAT2().getprice(),games[j].getcodeOFgame(),games[j].getSEAT2().seat_num());
    							 reservedSeats.add(seat);
    							 break;
            	                }
    						}
    						Xchange_Seats(i);		
    					}
    					else if(upgradeChoice != 1 && upgradeChoice!=2)
        					System.out.println("You choosed wrong number"); 
    				}
    				else
    					System.out.println("There is no available seat in the first category in this match");
    			}   			
    		}
    	}
	}
    
    /**Static Method 
     *@throws ParseException if there was a problem in the date form 
     *This method cancels a ticket of a fan but the date of the game must be after more than three days
     * *we pretend that today date is  "2020/6/10 20:30:00" so the game should be after 3 days or more from this date
     *if there are 3 days of more to the match it add the seat to the canceled seats then remove the ticket from the reserved tickets and from the list of tickets of the fan*/
    static void Cancel_ticket() {
	SimpleDateFormat format = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        /**the date of today, we pretend that it's the date of the day the user is using the system on*/
	String today = "2020/6/10 20:30:00";
        Date Today = null ;
        Date MatchDay = null ;
        try {
        Today = format.parse(today);
        }
        catch(ParseException k) { }
        System.out.println("please enter the code of the game you want to cancel its ticket");
    	int Game_Code = scanner.nextInt();
    	System.out.println("please enter your seat number ");
    	int seat_number = scanner.nextInt();
    	boolean StopLoop = false;
    	for (int i=0 ; i < reservedSeats.size() ; i++){
    	    if ((Game_Code == reservedSeats.get(i).Game_code) && (seat_number == reservedSeats.get(i).seat_number)) {
    		for (int k =0 ; k< reservedTickets.size(); k++){
    			if(reservedTickets.get(k).game.getcodeOFgame() == Game_Code && reservedSeats.get(i)==reservedTickets.get(k).seat ) {
    			    String DayofMatch = reservedTickets.get(k).game.getDate();
    			    try {
    			    MatchDay = format.parse(DayofMatch);
    				}
    			    catch(ParseException h) { }
    			    long diff = MatchDay.getTime() - Today.getTime();
    			    long diffIndays = diff / (24 * 60 * 60 * 1000);
    			    if( diffIndays < 3) {
    			        System.out.println("unfourtantly, you can only cancel the ticket before at least 3 days from the match date");
    			        StopLoop = true;
    			        break;
    			        }
    			        else if(diffIndays >= 3)
        				{
    					   for (int m = 0 ; m < allFans.size() ; m++) {
    							if (reservedTickets.get(k).FanID == allFans.get(m).FANid) {
    								for(int l = 0 ;l <allFans.get(m).allTickets.size() ; l++ ) {	
    									if(reservedTickets.get(k).game.getcodeOFgame() == allFans.get(m).allTickets.get(l).game.getcodeOFgame()) {
    									    CanceledSeats.add(reservedSeats.get(i));
    		        						    reservedSeats.remove(i);
    									    allFans.get(m).allTickets.remove(l);
    									    System.out.println("your ticket is canceled");
    									    StopLoop = true;
    									    break;
    									}
    								}
    							}
    							if(StopLoop == true)
    			        			break;
    						}
    						reservedTickets.remove(k);
        				}
    				}
    			}
    		}
    		if(StopLoop == true)
    			break;
    	}
	}
    
    /**note: - the message box is shown on the desktop if it's the first message box shown in the run
    *This project has different Access modifiers 
    *this project Applies single responsibility because every class has only one job
    *Applies Open-Closed Principle because the classes were written and don't need to change because of using inheritance and overriding functions
    *Applies Liskov Substitution by using interface
    *Applies Interface Segregation Principle because all the implemented methods in the interface 
    *are used in the classes that implement the interface "Category" */
    
    /**the menu of the functions the user can do
    *@throws InputMismatchException if the user entered a letter instead of number
    *@throws Java user defined exception if the user's choice was a number not in the choices*/
     public static void main(String[] args) throws Exception {
    	 Hard_Codes();  
          while(true)
        {
        System.out.println("What do you want to do ? enter your answer from 1 to 9");
        System.out.println("1- Get your own fan ID ");
        System.out.println("2- Check seats availability ");
        System.out.println("3- Make a bet for a game");
        System.out.println("4- Check the result of your bet");
        System.out.println("5- Reserve a ticket");
        System.out.println("6- Upgrade a ticket");
        System.out.println("7- Cancel a ticket");
        System.out.println("8- Display the details of all sport fans");
        System.out.println("9- Exit");
        
        try {
        int choice = scanner.nextInt();
        
        if (choice == 1)
        {
        	New_Fan();
        }
        else if (choice == 2)
        {
        	Print_AvailableSeats ();
        }
        else if (choice ==3)
        {
        	Make_aBet();   
        }
        
        else if (choice == 4)
        {
        	Check_aBet_resault();
        }
        
        else if (choice ==5)
        {
        	Reserve_aNew_ticket();
        }
        
        else if (choice == 6)
        {
        	Upgrade_ticket();
        }
        else if (choice == 7)
        {
        	Cancel_ticket();
        }
        	
        
        else if (choice == 8)
        {
            for (int i=0 ; i < allFans.size(); i ++)
            {
                allFans.get(i).displayFan();
            }
            
        }
        else if (choice == 9)
        {
            break;
        }
        else 
        {
        	OutOfTheChoicesRange_Exception ex = new OutOfTheChoicesRange_Exception("You entered a number out of the choices range, Choose a number from 1 to 9");
               throw ex; 
        }
        }
        /**Java defined exception*/
        catch(InputMismatchException WrongInput) 
        {
        	//System.out.println("you must enter an number from 1 to 9....");
        	JOptionPane.showMessageDialog(JOptionPane.getRootFrame(),"You must enter a number from 1 to 9....", "Wrong input type",JOptionPane.ERROR_MESSAGE);
        	break;
        }
        /**Java user defined exception*/
        catch(OutOfTheChoicesRange_Exception M) 
        {
        	JOptionPane.showMessageDialog(JOptionPane.getRootFrame(),M.getMessage(),"Out of choices range input" , JOptionPane.ERROR_MESSAGE);
        }
       }
    }
}

/**abstract class*/
abstract class Game{
    /**the winner of the game*/
    private String winner ;
    /**the name of the stadium*/
    private String location;
    /**score of the game*/
    private String gamescore;
    /**the date of the match*/
    private String match_date ;
    /**the first team plays in the match*/
    private String team1 ;
    /**the second team plays in the match*/
    private String team2 ;
    /**carries the information of the seats of first category in the game*/
    private Category1 SEAT1 ;
    /**carries the information of the seats of second category in the game*/ 
    private Category2 SEAT2 ;
    /**carries the information of the seats of third category in the game*/
    private Category3 SEAT3 ;
    /**Static data member GameCode by which the codes of the games are generated */
    private static int GameCode = 709010;
    /**the code of the game*/
    private int codeOFgame ;
    /**Static data member is numberOFgames that counts number of matches in the system*/
    private static int numberOFgames =0 ;
    /**constructor of the game when the game doesn't have special prices for the seats */
    public Game(String team1 ,String team2 ,String date ,String location, int numseatsOf_1stCategory,int numseatsOf_2ndCategory , int numseatsOf_3rdCategory, String winner,String gamescore)
    {
        codeOFgame = GameCode ;
        GameCode ++;
        numberOFgames++;
        this.team1 = team1;
        this.team2 = team2;
        this.match_date = date ;
        this.location = location;
        this.winner = winner;
        this.gamescore = gamescore;
        SEAT1 = new Category1(numseatsOf_1stCategory);
        SEAT2 = new Category2(numseatsOf_2ndCategory);
        SEAT3 = new Category3(numseatsOf_3rdCategory);
    }
    /**constructor of the game when the game has special prices for the seats
     *@overloading when special prices entered to the seats of the game*/
     public Game(String team1 ,String team2 ,String date ,String location, int numseatsOf_1stCategory,int numseatsOf_2ndCategory , int numseatsOf_3rdCategory, String winner,String gamescore,int priceOF1st,int priceOF2nd ,int priceOF3rd)
     {
    	 codeOFgame = GameCode ;
         GameCode ++;
        numberOFgames++;
        this.team1 = team1;
        this.team2 = team2;
        this.match_date = date ;
        this.location = location;
        this.winner = winner;
        this.gamescore = gamescore;
        SEAT1 = new Category1(numseatsOf_1stCategory,priceOF1st);
        SEAT2 = new Category2(numseatsOf_2ndCategory,priceOF2nd);
        SEAT3 = new Category3(numseatsOf_3rdCategory,priceOF3rd);
    }

     /**Static Method is numberOFgames that counts number of matches*/
    public static int numberOFgames() {
        return numberOFgames;
    }
    public String getTeam1() {
        return team1;
    }
    public int getcodeOFgame() {
    	return codeOFgame;
    }
    public  String getTeam2() {
        return team2;
    }

    public String getWinner() {
        return winner;
    }

    public String getGamescore() {
        return gamescore;
    }

    public String getDate() {
        return match_date;
    }

    public Category getSEAT1() {
        return SEAT1;
    }

    public Category getSEAT2() {
        return SEAT2;
    }

    public Category getSEAT3() {
        return SEAT3;
    }

    public String getLocation() {
        return location;
    }
    
    /**display the available categories if it have seats */
    public void Display_availableSeats(){
         
        if (SEAT1.numOFSeatsInCategory != 0 || SEAT2.numOFSeatsInCategory !=0 || SEAT3.numOFSeatsInCategory !=0 )
        {
            System.out.println("There are available seats in the Game with code " + codeOFgame + "  Between " + team1 + " & " + team2 + "   in " + location );
        }
     }
    
    public abstract void booking_msg();
}

 /**Inheritance*/
class international extends Game {
     /**a variable that carries the country that the game will be played in*/
    String county ;
    public international(String team1 ,String team2 ,String date ,String location, int numseatsOf_1stCategory,int numseatsOf_2ndCategory , int numseatsOf_3rdCategory, String winner,String gamescore , String countryWillplayIn){
        super( team1 , team2 ,date ,location,  numseatsOf_1stCategory, numseatsOf_2ndCategory ,  numseatsOf_3rdCategory, winner, gamescore);
        county = countryWillplayIn;
    }
    /**overloading in the constructor of the international game if the game has special entered prices for seats*/
     public international(String team1 ,String team2 ,String date ,String location, int numseatsOf_1stCategory,int numseatsOf_2ndCategory , int numseatsOf_3rdCategory, String winner,String gamescore, String countryWillplayIn ,int priceOF1st,int priceOF2nd ,int priceOF3rd){
        super( team1 , team2 ,date ,location,  numseatsOf_1stCategory, numseatsOf_2ndCategory ,  numseatsOf_3rdCategory, winner, gamescore,priceOF1st,priceOF2nd,priceOF3rd);
        county = countryWillplayIn;
    }
     /**Overriding message
     *@Override*/
     public void booking_msg(){
         System.out.println("You choosed an international match so make sure that your passport ready to travel ");
     }
    
}

/**Inheritance*/
class national extends  Game {
    
     public national(String team1 ,String team2 ,String date ,String location, int numseatsOf_1stCategory,int numseatsOf_2ndCategory , int numseatsOf_3rdCategory, String winner,String gamescore){
        super( team1 , team2 ,date ,location,  numseatsOf_1stCategory, numseatsOf_2ndCategory ,  numseatsOf_3rdCategory, winner, gamescore);
    }
     /**overloading in the constructor of the national game if the game has special entered prices for seats*/
     public national(String team1 ,String team2 ,String date ,String location, int numseatsOf_1stCategory,int numseatsOf_2ndCategory , int numseatsOf_3rdCategory, String winner,String gamescore,int priceOF1st,int priceOF2nd ,int priceOF3rd){
        super( team1 , team2 ,date ,location,  numseatsOf_1stCategory, numseatsOf_2ndCategory ,  numseatsOf_3rdCategory, winner, gamescore,priceOF1st,priceOF2nd,priceOF3rd);
    }
     /**Overriding message
     *@Override*/
     public void booking_msg(){
         System.out.println("You choosed a national game so don't forget to take your national identity card to be able to enter the stadium");
     }
}
/**interface */
interface Category {
    int seat_num();
    int SeatsAvailablity ();  
    int getprice();
    public int getseat_num();
}

class Category1 implements Category{
    /**the number of the seats in the 1st category for a match*/
   protected int numOFSeatsInCategory ;
   /**counter to generate the numbers of the seats*/
   protected int SeatNUM = 102050;
   /**the seat number*/
   protected int SeatID ;
   /**the price of the seat in the 1st category*/
   protected int price = 1000 ;
   /**@param  the number of the seats in the first category of the match*/
    public Category1 (int numOFseats){
    	numOFSeatsInCategory = numOFseats;
    }
    /**@param  the number of the seats in the first category of the match
     *overloading happens if the seat of the first category have a special entered price*/
    public Category1 (int numOFseats,int seatPrice){
    	numOFSeatsInCategory = numOFseats;
    	price = seatPrice ;
    }
    public int getprice() {
    	return price;
    }
    public int seat_num(){
        SeatID = SeatNUM;
        SeatNUM ++;
        return SeatID;
    }
    public int getseat_num(){
        return SeatID;
    }
    /**decrease the number of the available seats */
    public int SeatsAvailablity()
    {
    	numOFSeatsInCategory -- ;
        return numOFSeatsInCategory;
    }
}

class Category2 implements Category{
    /**the number of the seats in the 2nd category for a match*/
    protected int numOFSeatsInCategory ;
    /**counter to generate the numbers of the seats*/
    protected int SeatNUM = 202050;
    /**the seat number*/
    protected int SeatID ;
    /**the price of the seat in the 2nd category*/
    protected int price = 500;
    /**@param  the number of the seats in the second category of the match*/
    public Category2 (int numOFseats){
    	numOFSeatsInCategory = numOFseats;
    }
    
     /**@param  the number of the seats in the second category of the match
     *overloading happens if the seat of the second category have a special entered price*/
    public Category2 (int numOFseats,int seatPrice){
    	numOFSeatsInCategory = numOFseats;
    	price = seatPrice ;
    }
    public int getprice() {
    	return price;
    }
    public int seat_num(){
        SeatID = SeatNUM;
        SeatNUM ++;
        return SeatID;
    }
    public int getseat_num(){
        return SeatID;
    }
    /**decrease the number of the available seats */
    public int SeatsAvailablity()
    {
    	numOFSeatsInCategory -- ;
        return numOFSeatsInCategory;
    }
}

class Category3 implements Category{
    /**the number of the seats in the 3rd category for a match*/
    protected int numOFSeatsInCategory ;
    /**counter to generate the numbers of the seats*/
    protected int SeatNUM = 302050;
    /**the seat number*/
    protected int SeatID ;
    /**the price of the seat in the 3rd category*/
    protected int price = 200;
    
    /**@param  the number of the seats in the third category of the match*/
    public Category3 (int numOFseats){
    	numOFSeatsInCategory = numOFseats;
    }
    /**@param  the number of the seats in the third category of the match
     *overloading happens if the seat of the third category have a special entered price*/
    public Category3 (int numOFseats,int seatPrice){
    	numOFSeatsInCategory = numOFseats;
    	price = seatPrice ;
    } 
    public int getprice() {
    	return price;
    }
    public int seat_num(){
        SeatID = SeatNUM;
        SeatNUM ++;
        return SeatID;
    }
    public int getseat_num(){
        return SeatID;
    }
    /**decrease the number of the available seats */
    public int SeatsAvailablity(){
    	numOFSeatsInCategory -- ;
        return numOFSeatsInCategory;
    }
}
class Seat {
    /**the number of the seat*/
     public int seat_number;
     /**the category of the seat*/
     public String SeatCategory;
     /**the price of the seat*/
     public int seat_Price;
     /**the code of the game the seat is reserved for*/
     public int Game_code ;
	
	public Seat(String SeatCategory,int seat_Price,int Game_code,int seat_number) {
	this.SeatCategory=SeatCategory;
	this.Game_code =Game_code ;
	this.seat_Price =seat_Price ;
	this.seat_number =seat_number ;
	}
	
}

class ticket {
    /**the game the ticket is booked for*/
    public Game game ;
    /**the seat was booked on this ticket*/
    public Seat seat ;
    /**the fan id*/
    public int FanID;
    /**thee price of the ticket*/
    public int ticketPrice;
    /**the category of the seat*/
    public String SeatCategory;
    
    public ticket (Game game ,int FanID,String SeatCategory,int ticketPrice ,Seat seat) {
    	this.game =game;
    	this.seat = seat;
    	this.FanID = FanID;
    	this.SeatCategory = SeatCategory;
    	this.ticketPrice = ticketPrice ;
    	if (SeatCategory == "First"){
    		this.game.getSEAT1().SeatsAvailablity();
    	
    		
    	}
    	else if (SeatCategory == "Second") {
    		this.game.getSEAT2().SeatsAvailablity();
    	
    	}
    	else if (SeatCategory == "Third") {
    		this.game.getSEAT3().SeatsAvailablity();
    	
    	}
    }
    public Seat getSeat()
    {
    	return seat;
    }
    
}
class Fan {
    /**Name is Final data member , carries the name of the fan*/
    public final String name;
    /**the year of the birth of the fan*/
    public int birthyear;
    /**age is Calculated data member*/
    public int age ;
    /**Static data member is fanID which is used to generate the id of new fans*/
    public static int fanID = 2020100;
    /**the id of the fan*/
    public int FANid ;
    /**a list that carries all the tickets of a specific fan*/
    public ArrayList<ticket> allTickets = new ArrayList<ticket>();

    
    public Fan (String name, int birthyear)
    {
        fanID ++ ;
        FANid = fanID;
        this.name = name ;
        this.birthyear = birthyear;
        setAge(birthyear);
    }
    /**Final method is setAge that counts the age of the fan*/
    public final void setAge(int birthyear) {
    	age = 2020 - birthyear ;
    }
    /**adds a ticket to the tickets list of the fan*/
    public void addTicket (ticket Ticket){
        allTickets.add(Ticket);
    }

    /**Static method is getFanID*/
    public static int getFanID() {
        return fanID;
    }
    
    public int getFANid() {
	return FANid;
    }
    
    /**display the data of the fan*/
    public void displayFan(){
    System.out.println("ID : " + FANid);
    System.out.println("Name : " + name);
    System.out.println("Age : " + age);
        if(!allTickets.isEmpty())
        {
        for (int i =0 ; i < allTickets.size() ; i++)
        {
            System.out.println("The code of the game of Ticket number" + (i+1) + ": " + allTickets.get(i).game.getcodeOFgame() + "  the seat number is "+allTickets.get(i).getSeat().seat_number + "  The game was between " + allTickets.get(i).game.getTeam1()+ " & " + allTickets.get(i).game.getTeam2() + "    on :- "+ allTickets.get(i).game.getDate());
        }
        System.out.println("__________________________________________________");
        }
        else {
            System.out.println("This fan didn't get any tickets till now ");
            System.out.println("__________________________________________________");
        }
    }   
}

 class bet{
     /**Static data member is betID  which is used to generate the id of new bets*/
     public static int betID ;
     /** the id of the bet*/
     public int BETid ;
     /**boolean carries true if the bet was guessed right and false if wrong*/
     private boolean betResult ;
     /**the game the bet was made for*/
     private Game game;
     
     public bet (boolean betResult , Game game)
     {
         betID ++ ;
         BETid = betID;
         this.betResult = betResult ;
    	 this.game = game ;
     }

    /**Static method is getBetID that generates the id of new bets*/
    public static int getBetID() {
        return betID;
    }
    
    public int getBETid() {
	return BETid;
	}

    public boolean isBetResult() {
        return betResult;
    }

    public Game getGame() {
        return game;
    }
     /**checks the result of a bet*/
    public void check_betResault(){
         if (betResult == true)
         {
             System.out.println("You guessed the right winner and the game end with score " + game.getGamescore() + " now this bet id is valid tobe used as dicound voutcher.");
         }
         else 
             System.out.println("You guessed the winner worng your team lost and the game end with score " + game.getGamescore() + " good luck in another try...");  
     } 
 }
 
    /**Java user defined exception is OutOfTheChoicesRange_Exception*/
class OutOfTheChoicesRange_Exception extends Exception
{
    
     public OutOfTheChoicesRange_Exception (String Message)
    {
    	 super(Message);
    }
}

