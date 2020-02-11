/**
 * 
 * Holds all information about room, guest and price.
 * 
 * @author Stefan
 *
 */


import java.io.Serializable;
import java.util.ArrayList;


public class Room implements Serializable{

   private static final long serialVersionUID = 1L;
   ArrayList<Guest> guests = new ArrayList<>();
   ArrayList<String> bookedDates = new ArrayList<>();
   int numberOfBeds,roomNumber;
   int price,defaultPrice,additionalFees;
   String typeOfBed,type;
   boolean extraBed=false;


   /** Constructor **/
   public Room(int numberOfBeds, int roomNumber,int defaultPrice,
         String typeOfBed, String type) {
      this.numberOfBeds = numberOfBeds;
      this.roomNumber = roomNumber;
      this.typeOfBed = typeOfBed;
      this.defaultPrice=defaultPrice;
      this.type=type;
      this.price=defaultPrice;


   }



   public void addAdditionalFees(int value){
      additionalFees+=value;
   }

   public boolean isExtraBed() {
      return extraBed;
   }



   public void setExtraBed(boolean extraBed) {
      this.extraBed = extraBed;
   }



   public int getNumberOfBeds() {
      return numberOfBeds;
   }



   public int getAdditionalFees() {
      return additionalFees;
   }



   public String getTypeOfBed() {
      return typeOfBed;
   }



   public String getType() {
      return type;
   }



   public int getRoomNumber() {
      return roomNumber;
   }



   public void setNewPrice(int price){
      this.price=price;
   }

   public Integer getNumberOfGuests(){
      return guests.size();
   }


   public void addGuest(Guest guest){
      guests.add(guest);
   }

   public void changePrice(int newPrice){
      price=newPrice;
   }

   /** Returns true or false according to the availability, if there are no guests in the room, it is available **/
   public boolean isAvilable(){
      if ((guests==null || guests.size()==0)) return true;
      else return false;

   }


   /** Same as the previous but a bit more advanced. Used in "Show available rooms" for today (Button available) **/
   public boolean isAvilableForAvilableRooms(){
      boolean booked=false;
      for (int i = 0; i < Main.bookings.size(); i++) {
         if(Main.bookings.get(i).getArrivalDate().equals(Date.getTodayDate()) && roomNumber==Main.bookings.get(i).getRoomNumber()) booked=true;	
      }
      if ((guests==null || guests.size()==0) && !booked) return true;
      else return false;

   }


   public int getPrice() {
      return price;
   }


   public int getDefaultPrice() {
      return defaultPrice;
   }


   public int getPriceToPay(){
      return guests.get(0).date.getDaysOfStay()*price*guests.size()+additionalFees;
   }

   public String getArrivalDate(){
      if (guests.size()==0 || guests==null) return "None";
      else return guests.get(0).getArrivalDate();
   }

   public String getDepartureDate(){
      if (guests.size()==0 || guests==null) return "None";
      else return guests.get(0).getDepartureDate();
   }

   /** This method removes all guests from a room, removes any additional fees and also deletes all booked dates of the guests **/
   public void reset(){
      guests.get(0).date.deleteFromBookedDates();
      price=defaultPrice;
      additionalFees=0;
      guests.clear();
   }

   public void addToBookedDates(String arrival, String departure){
      String[] date1 = arrival.split("/");
      String[] date2 = departure.split("/");

      int day1 = Integer.parseInt(date1[0]), month1 = Integer.parseInt(date1[1]), year1= Integer.parseInt(date1[2]);
      int day2 = Integer.parseInt(date2[0]), month2 = Integer.parseInt(date2[1]), year2= Integer.parseInt(date2[2]);
      String day2text=day2+"/"+month2+"/"+year2;
      String day1text=day1+"/"+month1+"/"+year1;
      day2text=guiBooking.dateFix(day2text);
      day1text=guiBooking.dateFix(day1text);

      while(!day1text.equals(day2text)){ 
         bookedDates.add(day1text);
         day1++;
         if (day1>Date.getDaysOfMonth(month1)){day1=1; month1++;}
         if (month1==13){ month1=1; year1++;}
         day1text=day1+"/"+month1+"/"+year1;
         day1text=guiBooking.dateFix(day1text);

      }
   }
}