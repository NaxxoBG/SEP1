
/**
 * 
 * This class is used as Guest in room but also as a reservation 
 * and holds all information about the guest
 * 
 * @author Stefan
 *
 */


import java.io.Serializable;

public class Guest implements Serializable {

   private static final long serialVersionUID = -3095613184967343173L;
   public Date date = new Date();
   private String name, address, phone, email, passport, dateOfBirth;
   private int roomNumber;
   private boolean extraBed = false;
   private boolean checked = false;

   /** Constructor **/
   public Guest(String name, String address, String phone, String email,
         String passport, String dateOfBirth, String arrivalDate,
         String departureDate, boolean extraBed) {
      super();
      this.name = name;
      this.address = address;
      this.phone = phone;
      this.email = email;
      this.passport = passport;
      this.dateOfBirth = dateOfBirth;
      this.extraBed = extraBed;
      date.setArrivalDate(arrivalDate);
      date.setDepartureDate(departureDate);
   }

   /** Constructor WITH the roomNumber field **/
   public Guest(String name, String address, String phone, String email,
         String passport, String dateOfBirth, String arrivalDate,
         String departureDate, boolean extraBed, int roomNumber) {
      super();
      this.name = name;
      this.address = address;
      this.phone = phone;
      this.email = email;
      this.passport = passport;
      this.dateOfBirth = dateOfBirth;
      this.extraBed = extraBed;
      this.roomNumber = roomNumber;
      date.setArrivalDate(arrivalDate);
      date.setDepartureDate(departureDate);
   }


   /** Classic getters and setters **/
   public String getName() {
      return name;
   }

   public String getAddress() {
      return address;
   }

   public String getPhone() {
      return phone;
   }

   public String getEmail() {
      return email;
   }

   public String getPassport() {
      return passport;
   }

   public String getDateOfBirth() {
      return dateOfBirth;
   }

   public String getArrivalDate() {
      return date.getArrivalDate();
   }

   public String getDepartureDate() {
      return date.getDepartureDate();
   }

   public int getRoomNumber() {
      return roomNumber;
   }

   public void setName(String name) {
      this.name = name;
   }

   public void setAddress(String address) {
      this.address = address;
   }

   public void setPhone(String phone) {
      this.phone = phone;
   }

   public void setEmail(String email) {
      this.email = email;
   }

   public void setPassport(String passport) {
      this.passport = passport;
   }

   public void setDateOfBirth(String dateOfBirth) {
      this.dateOfBirth = dateOfBirth;
   }

   public void setArrivalDate(String arrivalDate) {
      date.setArrivalDate(arrivalDate);
   }

   public void setDepartureDate(String departureDate) {
      date.setDepartureDate(departureDate);
   }

   public void setRoomNumber(int roomNumber) {
      this.roomNumber = roomNumber;
   }

   public String toString() {
      return name;
   }

   public Boolean getExtraBed() {
      return extraBed;
   }

   public boolean isChecked() {
      return checked;
   }

   public void setChecked(boolean checked) {
      this.checked = checked;
   }

}
