/**
 * 
 * This class contains dates for each room and calculates the days between the dates
 * 
 * @author Martin
 *
 */


import java.io.Serializable;
import java.util.Calendar;

public class Date implements Serializable {

   private static final long serialVersionUID = -5319048196675057999L;
   private int departureDay = 0, departureMonth = 0, departureYear = 0;
   private int arrivalDay = 0, arrivalMonth = 0;
   private static int arrivalYear = 0;
   private static int todayDay = Calendar.getInstance().get(Calendar.DAY_OF_MONTH);
   private static int todayMonth = Calendar.getInstance().get(Calendar.MONTH) + 1;
   private static int todayYear = Calendar.getInstance().get(Calendar.YEAR);

   public void setArrivalDate(String arrivalDate) {
      String[] dates = arrivalDate.split("/");
      if (Integer.parseInt(dates[0]) < 0)
         dates[0] = (-(Integer.parseInt(dates[0]))) + "";
      if (Integer.parseInt(dates[1]) < 0)
         dates[1] = (-(Integer.parseInt(dates[1]))) + "";
      if (Integer.parseInt(dates[2]) < 0)
         dates[2] = (-(Integer.parseInt(dates[2]))) + "";
      if (Integer.parseInt(dates[1]) > 12)
         arrivalMonth = 12;
      else
         arrivalMonth = Integer.parseInt(dates[1]);

      if (Integer.parseInt(dates[0]) > getDaysOfMonth(arrivalMonth))
         arrivalDay = getDaysOfMonth(arrivalMonth);
      else
         arrivalDay = Integer.parseInt(dates[0]);
      setArrivalYear(Integer.parseInt(dates[2]));
   }

   public String getArrivalDate() {
      String day, month;

      if (arrivalDay < 10)
         day = "0" + arrivalDay;
      else
         day = arrivalDay + "";

      if (arrivalMonth < 10)
         month = "0" + arrivalMonth;
      else
         month = arrivalMonth + "";

      return day + "/" + month + "/" + getArrivalYear();

   }

   public static String getTodayDate() {

      String day, month;

      if (todayDay < 10)
         day = "0" + todayDay;
      else
         day = todayDay + "";

      if (todayMonth < 10)
         month = "0" + todayMonth;
      else
         month = todayMonth + "";

      return day + "/" + month + "/" + todayYear;
   }

   public void setDepartureDate(String departureDate) {
      String[] dates = departureDate.split("/");
      if (Integer.parseInt(dates[0]) < 0)
         dates[0] = (-(Integer.parseInt(dates[0]))) + "";
      if (Integer.parseInt(dates[1]) < 0)
         dates[1] = (-(Integer.parseInt(dates[1]))) + "";
      if (Integer.parseInt(dates[2]) < 0)
         dates[2] = (-(Integer.parseInt(dates[2]))) + "";
      if (Integer.parseInt(dates[1]) > 12)
         departureMonth = 12;
      else
         departureMonth = Integer.parseInt(dates[1]);
      if (Integer.parseInt(dates[0]) > getDaysOfMonth(departureMonth))
         departureDay = getDaysOfMonth(departureMonth);
      else
         departureDay = Integer.parseInt(dates[0]);
      departureYear = Integer.parseInt(dates[2]);
   }

   public String getDepartureDate() {
      String day, month;

      if (departureDay < 10)
         day = "0" + departureDay;
      else
         day = departureDay + "";

      if (departureMonth < 10)
         month = "0" + departureMonth;
      else
         month = departureMonth + "";

      return day + "/" + month + "/" + departureYear;

   }

   /** getDaysOfStay() returns the number of days between the arrival date and the departure date **/
   public Integer getDaysOfStay() {
      int arrivalDay = this.arrivalDay, arrivalMonth = this.arrivalMonth, arrivalYear = Date
            .getArrivalYear();
      int x = 0;
      while (true) {
         if (arrivalDay == departureDay && arrivalMonth == departureMonth && arrivalYear == departureYear)
            break;
         x++;
         arrivalDay++;
         if (arrivalDay > getDaysOfMonth(arrivalMonth)) {
            arrivalDay = 1;
            arrivalMonth++;
         }
         if (arrivalMonth == 13) {
            arrivalMonth = 1;
            arrivalYear++;
         }

      }

      return x;
   }

   /** getDaysOfStayForCheckout() returns the number of days between the current date and the departure date **/
   public Integer getDaysOfStayForCheckOut() {
      int arrivalDay = this.arrivalDay, arrivalMonth = this.arrivalMonth, arrivalYear = Date
            .getArrivalYear();
      int x = 0;
      while (true) {
         if (arrivalDay == todayDay && arrivalMonth == todayMonth
               && arrivalYear == todayYear)
            break;
         x++;
         arrivalDay++;
         if (arrivalDay > getDaysOfMonth(arrivalMonth)) {
            arrivalDay = 1;
            arrivalMonth++;
         }
         if (arrivalMonth == 13) {
            arrivalMonth = 1;
            arrivalYear++;
         }

      }

      return x;
   }

   /** The argument is a month, for example "2" and the method returns the number of days in this month, so for "2" it is 2/ or 29(Leap year)**/
   public static Integer getDaysOfMonth(int month) {
      switch (month) {
         case 1:
            return 31;
         case 2:
            if (isLeapYear(getArrivalYear()))
               return 29;
            else
               return 28;
         case 3:
            return 31;
         case 4:
            return 30;
         case 5:
            return 31;
         case 6:
            return 30;
         case 7:
            return 31;
         case 8:
            return 31;
         case 9:
            return 30;
         case 10:
            return 31;
         case 11:
            return 30;
         case 12:
            return 31;
         default:
            return 0;
      }
   }

   /** true or false....**/
   public static boolean isLeapYear(int year) {
      if (((year % 4 == 0) && (year % 100 != 0)) || (year % 400 == 0))
         return true;
      else
         return false;

   }

   /** It takes the arrival date and the departure date and add all dates between these days to Room's array list bookedDates **/
   public void addToBookedDates() {
      int day1 = arrivalDay, month1 = arrivalMonth, year1 = getArrivalYear();
      int day2 = departureDay, month2 = departureMonth, year2 = departureYear;
      String day2text = day2 + "/" + month2 + "/" + year2;
      String day1text = day1 + "/" + month1 + "/" + year1;
      day2text = guiBooking.dateFix(day2text);
      day1text = guiBooking.dateFix(day1text);
      while (!day1text.equals(day2text)) {
         Main.rooms[Main.getSelectedIndex()].bookedDates.add(day1text);
         day1++;
         if (day1 > Date.getDaysOfMonth(month1)) {
            day1 = 1;
            month1++;
         }
         if (month1 == 13) {
            month1 = 1;
            year1++;
         }
         day1text = day1 + "/" + month1 + "/" + year1;
         day1text = guiBooking.dateFix(day1text);

      }
   }

   /** Same as the previous one but with the arrival date and the departure date as arguments (For editing, etc...) **/
   public void addToBookedDates(String arrival, String departure) {
      String[] date1 = arrival.split("/");
      String[] date2 = departure.split("/");

      int day1 = Integer.parseInt(date1[0]), month1 = Integer
            .parseInt(date1[1]), year1 = Integer.parseInt(date1[2]);
      int day2 = Integer.parseInt(date2[0]), month2 = Integer
            .parseInt(date2[1]), year2 = Integer.parseInt(date2[2]);
      String day2text = day2 + "/" + month2 + "/" + year2;
      String day1text = day1 + "/" + month1 + "/" + year1;
      day2text = guiBooking.dateFix(day2text);
      day1text = guiBooking.dateFix(day1text);

      while (!day1text.equals(day2text)) {
         Main.rooms[Main.getSelectedIndex()].bookedDates.add(day1text);
         day1++;
         if (day1 > Date.getDaysOfMonth(month1)) {
            day1 = 1;
            month1++;
         }
         if (month1 == 13) {
            month1 = 1;
            year1++;
         }
         day1text = day1 + "/" + month1 + "/" + year1;
         day1text = guiBooking.dateFix(day1text);

      }

   }

   /** Removes all dates between arrivalDate and DepartureDate from booked dates.... so dates are again free **/
   public void deleteFromBookedDates() {
      int day1 = arrivalDay, month1 = arrivalMonth, year1 = getArrivalYear();
      int day2 = departureDay, month2 = departureMonth, year2 = departureYear;
      String day2text = day2 + "/" + month2 + "/" + year2;
      day2text = guiBooking.dateFix(day2text);
      String day1text = day1 + "/" + month1 + "/" + year1;
      day1text = guiBooking.dateFix(day1text);
      while (!day1text.equals(day2text)) {
         Main.rooms[Main.getSelectedIndex()].bookedDates.remove(day1text);
         day1++;
         if (day1 > Date.getDaysOfMonth(month1)) {
            day1 = 1;
            month1++;
         }
         if (month1 == 13) {
            month1 = 1;
            year1++;
         }
         day1text = day1 + "/" + month1 + "/" + year1;
         day1text = guiBooking.dateFix(day1text);

      }

   }

   public static int getArrivalYear() {
      return arrivalYear;
   }

   public static void setArrivalYear(int arrivalYear) {
      Date.arrivalYear = arrivalYear;
   }

}