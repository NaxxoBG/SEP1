/**
 * This class creates an Edit form with all the information from the booking
 *  The user can edit certain fields or delete whole bookings
 * @author Atanas
 *
 */

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;
import javax.swing.JCheckBox;

public class guiEdit {
   private JFrame frame = new JFrame();
   private JTextField tfName;
   private JTextField tfAdress;
   private JTextField tfPhoneNumber;
   private JTextField tfEmail;
   private JTextField tfPassport;
   private JTextField tfDateOfBirth;
   private JTextField tfArrivalDate;
   private JTextField tfDepartureDate;
   private JCheckBox chbExtraBed;
   private JButton btnSave;
   private JButton btnBack;
   private JLabel lblExceptionName;
   private JLabel lblExceptionBirth;
   private JLabel lblExceptionAdress;
   private JLabel lblExceptionArrival;
   private JLabel lblExceptionDeparture;

   private Guest guest;
   private Guest newGuest;
   private Date date = new Date();
   String day1text;
   private int bookingIndex = 0;

   public guiEdit() {
      String[] help = Main.getSelectedValue().split(" ");
      String departureDate = "";
      if (Main.getSelectedValue().contains("/"))departureDate = help[help.length - 1];
      for (int i = 0; i < Main.bookings.size(); i++) {
         if (Main.getSelectedRoomNumber() == Main.bookings.get(i).getRoomNumber()&& departureDate.equals(Main.bookings.get(i).getDepartureDate())) {
            bookingIndex = i;
            break;
         }
      }
      guest = Main.bookings.get(bookingIndex);
      Main.bookings.get(bookingIndex).date.deleteFromBookedDates();

      frame.setResizable(false);
      frame.setTitle("Edit");
      frame.setBounds(100, 100, 480, 577);
      frame.setLocationRelativeTo(null);
      frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
      frame.addWindowListener(new WindowListener() {
         @Override
         public void windowClosing(WindowEvent e) {
            frame.setVisible(false);
            Main.bookings.get(bookingIndex).date.addToBookedDates();
         }
         @Override
         public void windowOpened(WindowEvent e) {
            // TODO Auto-generated method stub
         }
         @Override
         public void windowClosed(WindowEvent e) {
            // TODO Auto-generated method stub
         }
         @Override
         public void windowIconified(WindowEvent e) {
            // TODO Auto-generated method stub
         }
         @Override
         public void windowDeiconified(WindowEvent e) {
            // TODO Auto-generated method stub
         }
         @Override
         public void windowActivated(WindowEvent e) {
            // TODO Auto-generated method stub
         }
         @Override
         public void windowDeactivated(WindowEvent e) {
            // TODO Auto-generated method stub
         }
      });

      frame.getContentPane().setLayout(null);

      JLabel lblNewLabel_1 = new JLabel("* Name :");
      lblNewLabel_1.setBounds(50, 40, 90, 20);
      frame.getContentPane().add(lblNewLabel_1);

      JLabel lblNewLabel_2 = new JLabel("* Date of birth :");
      lblNewLabel_2.setBounds(50, 90, 90, 20);
      frame.getContentPane().add(lblNewLabel_2);

      JLabel lblNewLabel_3 = new JLabel("* Address :");
      lblNewLabel_3.setBounds(50, 140, 90, 20);
      frame.getContentPane().add(lblNewLabel_3);

      JLabel lblNewLabel_4 = new JLabel("  Passport nr. :");
      lblNewLabel_4.setBounds(50, 190, 90, 20);
      frame.getContentPane().add(lblNewLabel_4);

      JLabel lblNewLabel_5 = new JLabel("  Phone number :");
      lblNewLabel_5.setBounds(50, 240, 100, 20);
      frame.getContentPane().add(lblNewLabel_5);

      JLabel lblNewLabel_6 = new JLabel("  E-mail :");
      lblNewLabel_6.setBounds(50, 290, 90, 20);
      frame.getContentPane().add(lblNewLabel_6);

      JLabel lblNewLabel_7 = new JLabel("* Arrival date :");
      lblNewLabel_7.setBounds(50, 340, 90, 20);
      frame.getContentPane().add(lblNewLabel_7);

      JLabel lblNewLabel_8 = new JLabel("* Departure date :");
      lblNewLabel_8.setBounds(50, 390, 100, 20);
      frame.getContentPane().add(lblNewLabel_8);

      JLabel lblNewLabel_9 = new JLabel("Extra bed ? :");
      lblNewLabel_9.setBounds(50, 440, 90, 16);

      tfName = new JTextField();
      tfName.requestFocus();
      tfName.setBounds(170, 40, 170, 20);
      frame.getContentPane().add(tfName);
      tfName.setText(guest.getName());
      tfName.setColumns(10);

      tfDateOfBirth = new JTextField();
      tfDateOfBirth.setBounds(172, 90, 170, 22);
      frame.getContentPane().add(tfDateOfBirth);
      tfDateOfBirth.setColumns(10);
      tfDateOfBirth.setText(guest.getDateOfBirth());

      tfAdress = new JTextField();
      tfAdress.setBounds(172, 140, 170, 22);
      frame.getContentPane().add(tfAdress);
      tfAdress.setColumns(10);
      tfAdress.setText(guest.getAddress());

      tfPassport = new JTextField();
      tfPassport.setBounds(172, 190, 170, 22);
      frame.getContentPane().add(tfPassport);
      tfPassport.setColumns(10);
      tfPassport.setText(guest.getPassport());

      tfPhoneNumber = new JTextField();
      tfPhoneNumber.setBounds(171, 240, 170, 22);
      frame.getContentPane().add(tfPhoneNumber);
      tfPhoneNumber.setColumns(10);
      tfPhoneNumber.setText(guest.getPhone());

      tfEmail = new JTextField();
      tfEmail.setBounds(172, 290, 170, 22);
      frame.getContentPane().add(tfEmail);
      tfEmail.setColumns(10);
      tfEmail.setText(guest.getEmail());

      tfArrivalDate = new JTextField();

      tfArrivalDate.setBounds(172, 340, 170, 22);
      tfArrivalDate.setText(guest.getArrivalDate());
      frame.getContentPane().add(tfArrivalDate);
      tfArrivalDate.setColumns(10);

      tfDepartureDate = new JTextField();
      tfDepartureDate.setText(guest.getDepartureDate());
      tfDepartureDate.setBounds(172, 390, 170, 22);

      frame.getContentPane().add(tfDepartureDate);
      tfDepartureDate.setColumns(10);

      chbExtraBed = new JCheckBox("");
      chbExtraBed.setBounds(170, 431, 113, 25);
      frame.getContentPane().add(chbExtraBed);

      btnSave = new JButton();
      btnSave.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent arg0) {
            if (tfName.getText().equals("") || containsInvalidCharacter())lblExceptionName.setText("*Invalid input"); else lblExceptionName.setText("");
            if (tfDateOfBirth.getText().equals(""))lblExceptionBirth.setText("*Invalid input");else lblExceptionBirth.setText("");
            if (tfAdress.getText().equals(""))lblExceptionAdress.setText("*Invalid input");else lblExceptionAdress.setText("");
            try {
               tfArrivalDate.setText(dateFix(tfArrivalDate.getText()));
               if (!(tfArrivalDate.getText().equals(dateChecker(tfArrivalDate.getText()))))lblExceptionArrival.setText("Invalid date. Did you mean"+ dateChecker(tfArrivalDate.getText())+ "?");
               else if (!(isAfterToday(tfArrivalDate.getText(),date.getTodayDate()))) {lblExceptionArrival.setText("*Invalid date");} else lblExceptionArrival.setText("");
            } catch (Exception e) {
               lblExceptionArrival.setText("Wrong input");
            }

            try {
               tfDepartureDate.setText(dateFix(tfDepartureDate.getText()));
               if (!(tfDepartureDate.getText().equals(dateChecker(tfDepartureDate.getText()))))lblExceptionDeparture.setText("Invalid date. Did you mean"+ dateChecker(tfDepartureDate.getText())+ "?");
               else if (!(isAfterToday(tfDepartureDate.getText(),date.getTodayDate()))) {lblExceptionDeparture.setText("*Invalid date");} 
               else if (!(isAfterToday(tfDepartureDate.getText(),tfArrivalDate.getText())))lblExceptionDeparture.setText("*Departure after arrival");
               else lblExceptionDeparture.setText("");
            } catch (Exception e) {
               lblExceptionDeparture.setText("Wrong input");
            }
            if (noException()) {
               checkFirstAvailableDate();
               if (tfArrivalDate.getText().equals(day1text)&& (!checkFirstAvailableDate()))lblExceptionArrival.setText("");
               else if ((!tfArrivalDate.getText().equals(day1text) && (!checkFirstAvailableDate())))lblExceptionArrival.setText("Reserved until "+ day1text);
               else if (checkFirstAvailableDate()) {lblExceptionArrival.setText("Reserved room for this date");lblExceptionDeparture.setText("Reserved room for this date");}
               else {lblExceptionArrival.setText("");lblExceptionDeparture.setText("");
               }
            }
            if (noException()) {
               checkForAvailability();
               if (checkForAvailability()) {lblExceptionDeparture.setText("Reserved room from "+ day1text);
               }
            }
            if (noException()) {
               editGuest();
               frame.setVisible(false);
               Main.bookings.get(Main.bookings.size() - 1).date.addToBookedDates();
               Main.save();
            }
         }
      });
      btnSave.setBounds(50, 486, 100, 40);
      btnSave.setText("Save");
      frame.getContentPane().add(btnSave);

      btnBack = new JButton("Back");
      btnBack.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent arg0) {
            frame.setVisible(false);
            Main.bookings.get(bookingIndex).date.addToBookedDates();
         }
      });
      frame.getContentPane().add(btnBack);

      lblExceptionName = new JLabel("");
      lblExceptionName.setForeground(Color.RED);
      lblExceptionName.setBounds(352, 42, 90, 16);
      frame.getContentPane().add(lblExceptionName);

      lblExceptionBirth = new JLabel("");
      lblExceptionBirth.setForeground(Color.RED);
      lblExceptionBirth.setBounds(354, 92, 88, 16);
      frame.getContentPane().add(lblExceptionBirth);

      lblExceptionAdress = new JLabel("");
      lblExceptionAdress.setForeground(Color.RED);
      lblExceptionAdress.setBounds(354, 142, 88, 16);
      frame.getContentPane().add(lblExceptionAdress);

      lblExceptionArrival = new JLabel("");
      lblExceptionArrival.setForeground(Color.RED);
      lblExceptionArrival.setBounds(170, 361, 304, 16);
      frame.getContentPane().add(lblExceptionArrival);

      lblExceptionDeparture = new JLabel("");
      lblExceptionDeparture.setForeground(Color.RED);
      lblExceptionDeparture.setBounds(170, 412, 292, 16);
      frame.getContentPane().add(lblExceptionDeparture);
      frame.setVisible(true);

      JButton btnDelete = new JButton("Delete");
      btnDelete.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent e) {
            Main.bookings.remove(bookingIndex);
            frame.setVisible(false);
            Main.save();
         }
      });
      btnDelete.setBounds(187, 486, 100, 40);
      frame.getContentPane().add(btnDelete);
      frame.setVisible(true);

   }

   /** Creates a new guest with information from the textFields **/
   private void editGuest() {
      newGuest = new Guest(tfName.getText(), tfAdress.getText(),tfPhoneNumber.getText(), tfEmail.getText(),tfPassport.getText(), tfDateOfBirth.getText(),tfArrivalDate.getText(), tfDepartureDate.getText(),chbExtraBed.isSelected(), Main.getSelectedRoomNumber());
      Main.bookings.remove(bookingIndex);
      Main.bookings.add(newGuest);
   }

   /**This method creates a real date (35/1/2015 -> 31/1/2015) and return it as a String**/
   public String dateChecker(String dt) {
      Date date = new Date();
      date.setArrivalDate(dt);
      return date.getArrivalDate();
   }

   /** This method makes the date in the desired format from 1/1/2015 -> 01/01/2015 **/
   public static String dateFix(String dt) {
      String date = "";
      String[] list = dt.split("/");
      if (list[0].length() < 2)list[0] = "0" + list[0];
      if (list[1].length() < 2)list[1] = "0" + list[1];
      for (int i = 0; i < list.length; i++) {
         date += list[i];
         if (i != list.length - 1)date += "/";
      }
      return date;
   }

   /** This method checks every Exception label and returns true if everything is fine**/
   public boolean noException() {
      if (lblExceptionName.getText().equals("")&& lblExceptionBirth.getText().equals("")&& lblExceptionAdress.getText().equals("")&& lblExceptionArrival.getText().equals("")&& lblExceptionDeparture.getText().equals(""))
         return true;
      else
         return false;
   }

   /** This method takes 2 arguments(Strings) and compares if the first date is before the second one **/
   public boolean isAfterToday(String date, String today1) {
      String[] dates = date.split("/");
      int day = Integer.parseInt(dates[0]);
      int month = Integer.parseInt(dates[1]);
      int year = Integer.parseInt(dates[2]);
      String[] today = today1.split("/");
      int todayDay = Integer.parseInt(today[0]);
      int todayMonth = Integer.parseInt(today[1]);
      int todayYear = Integer.parseInt(today[2]);
      if ((year == todayYear && month == todayMonth && day >= todayDay)|| (todayYear == year && month > todayMonth)|| (year > todayYear))
         return true;
      else
         return false;
   }

   /**This method checks if the room is available for a certain time period. If the room is reserved, it stores the last free day to the variable day1text.**/
   public boolean checkForAvailability() {
      String[] date1 = tfArrivalDate.getText().split("/");
      String[] date2 = tfDepartureDate.getText().split("/");
      int day1 = Integer.parseInt(date1[0]), month1 = Integer
            .parseInt(date1[1]), year1 = Integer.parseInt(date1[2]);
      int day2 = Integer.parseInt(date2[0]), month2 = Integer
            .parseInt(date2[1]), year2 = Integer.parseInt(date2[2]);
      String day2text = day2 + "/" + month2 + "/" + year2;
      day2text = dateFix(day2text);
      day1text = day1 + "/" + month1 + "/" + year1;
      day1text = dateFix(day1text);
      if (Main.rooms[Main.getSelectedIndex()].bookedDates.contains(day1text))
         return true;
      while (!day1text.equals(day2text)) {
         day1++;
         if (day1 > Date.getDaysOfMonth(month1)) {day1 = 1;month1++;}
         if (month1 == 13) {month1 = 1;year1++;}
         day1text = day1 + "/" + month1 + "/" + year1;
         day1text = dateFix(day1text);
         if (Main.rooms[Main.getSelectedIndex()].bookedDates.contains(day1text))
            return true;
      }
      return false;
   }

   /**If the arrival date is already booked, this method finds the first available date and stores it in day1text **/
   public boolean checkFirstAvailableDate() {
      String[] date1 = tfArrivalDate.getText().split("/");
      String[] date2 = tfDepartureDate.getText().split("/");
      int day1 = Integer.parseInt(date1[0]), month1 = Integer
            .parseInt(date1[1]), year1 = Integer.parseInt(date1[2]);
      int day2 = Integer.parseInt(date2[0]), month2 = Integer
            .parseInt(date2[1]), year2 = Integer.parseInt(date2[2]);
      String day2text = day2 + "/" + month2 + "/" + year2;
      day2text = dateFix(day2text);
      day1text = day1 + "/" + month1 + "/" + year1;
      day1text = dateFix(day1text);
      if (!(Main.rooms[Main.getSelectedIndex()].bookedDates.contains(day1text)))
         return false;
      if (Main.rooms[Main.getSelectedIndex()].bookedDates.contains(day1text)) {
         while (!day1text.equals(day2text)) {
            day1++;
            if (day1 > Date.getDaysOfMonth(month1)) {day1 = 1;month1++;}
            if (month1 == 13) {month1 = 1;year1++;}
            day1text = day1 + "/" + month1 + "/" + year1;
            day1text = dateFix(day1text);
            if (!(Main.rooms[Main.getSelectedIndex()].bookedDates.contains(day1text)))
               return false;
         }
      }
      return true;
   }
   /** Checks if the name textFiled contains / or \ **/
   public boolean containsInvalidCharacter() {
      for (int i = 0; i < tfName.getText().length(); i++) {
         if (tfName.getText().contains("/")
               || tfName.getText().contains("\\"))
            return true;
      }
      return false;
   }
}
