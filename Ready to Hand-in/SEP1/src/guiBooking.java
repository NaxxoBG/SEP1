import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;
import javax.swing.JCheckBox;

/**
 * 
 * This class creates a booking form where all the information about the guest will be written
 * It allows the user to create, add and save a new booking for a certain room
 * 
 * @author Atanas
 *
 */
public class guiBooking {
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

   private Guest newGuest;
   private Date date = new Date();
   String day1text;

   public guiBooking() {
      frame.setResizable(false);
      frame.setTitle("Booking");
      frame.setBounds(100, 100, 480, 577);
      frame.setLocationRelativeTo(null);
      frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
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

      tfName = new JTextField();
      tfName.requestFocus();

      JLabel lblNewLabel_9 = new JLabel("  Extra bed ? :");
      lblNewLabel_9.setBounds(50, 440, 90, 16);
      frame.getContentPane().add(lblNewLabel_9);
      tfName.setBounds(170, 40, 170, 20);
      frame.getContentPane().add(tfName);
      tfName.setColumns(10);

      tfDateOfBirth = new JTextField();
      tfDateOfBirth.setBounds(172, 90, 170, 22);
      frame.getContentPane().add(tfDateOfBirth);
      tfDateOfBirth.setColumns(10);

      tfAdress = new JTextField();
      tfAdress.setBounds(172, 140, 170, 22);
      frame.getContentPane().add(tfAdress);
      tfAdress.setColumns(10);

      tfPassport = new JTextField();
      tfPassport.setBounds(172, 190, 170, 22);
      frame.getContentPane().add(tfPassport);
      tfPassport.setColumns(10);

      tfPhoneNumber = new JTextField();
      tfPhoneNumber.setBounds(171, 240, 170, 22);
      frame.getContentPane().add(tfPhoneNumber);
      tfPhoneNumber.setColumns(10);

      tfEmail = new JTextField();
      tfEmail.setBounds(172, 290, 170, 22);
      frame.getContentPane().add(tfEmail);
      tfEmail.setColumns(10);

      tfArrivalDate = new JTextField();
      tfArrivalDate.addKeyListener(new KeyAdapter() {
         @Override
         public void keyTyped(KeyEvent arg0) {
            if (tfArrivalDate.getText().equals("dd/mm/yyyy"))
               tfArrivalDate.setText("");
            else if (tfArrivalDate.getText().equals("")) {
               tfArrivalDate.setText("dd/mm/yyyy");
               tfArrivalDate.setCaretPosition(0);
            }
         }
      });

      tfArrivalDate.setToolTipText("dd/mm/yyyy");
      tfArrivalDate.setBounds(172, 340, 170, 22);
      tfArrivalDate.setText("dd/mm/yyyy");
      tfArrivalDate.addFocusListener(new FocusAdapter() {
         @Override
         public void focusGained(FocusEvent arg0) {
            if (tfDepartureDate.getText().equals("dd/mm/yyyy"))
               tfDepartureDate.setCaretPosition(0);
         }
      });
      frame.getContentPane().add(tfArrivalDate);
      tfArrivalDate.setColumns(10);

      tfDepartureDate = new JTextField();
      tfDepartureDate.addKeyListener(new KeyAdapter() {
         @Override
         public void keyTyped(KeyEvent e) {
            if (tfDepartureDate.getText().equals("dd/mm/yyyy"))
               tfDepartureDate.setText("");
            else if (tfDepartureDate.getText().equals("")) {
               tfDepartureDate.setText("dd/mm/yyyy");
               tfDepartureDate.setCaretPosition(0);
            }
         }
      });
      tfDepartureDate.setText("dd/mm/yyyy");
      tfDepartureDate.setBounds(172, 390, 170, 22);
      tfDepartureDate.setToolTipText("dd/mm/yyyy");
      tfDepartureDate.addFocusListener(new FocusAdapter() {
         @Override
         public void focusGained(FocusEvent arg0) {
            if (tfArrivalDate.getText().equals("dd/mm/yyyy"))
               tfArrivalDate.setCaretPosition(0);
         }
      });
      frame.getContentPane().add(tfDepartureDate);
      tfDepartureDate.setColumns(10);

      chbExtraBed = new JCheckBox("");
      chbExtraBed.setBounds(170, 431, 113, 25);
      frame.getContentPane().add(chbExtraBed);

      btnSave = new JButton();
      btnSave.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent arg0) {
            if (tfName.getText().equals("") || containsInvalidCharacter())lblExceptionName.setText("*Invalid input");else lblExceptionName.setText("");
            if (tfDateOfBirth.getText().equals("")) lblExceptionBirth.setText("*Invalid input"); else lblExceptionBirth.setText("");
            if (tfAdress.getText().equals(""))lblExceptionAdress.setText("*Invalid input"); else lblExceptionAdress.setText("");
            try {
               tfArrivalDate.setText(dateFix(tfArrivalDate.getText()));
               //Checks if the arrival date really exists
               if (!(tfArrivalDate.getText().equals(dateChecker(tfArrivalDate.getText()))))lblExceptionArrival.setText("Invalid date. Did you mean"+ dateChecker(tfArrivalDate.getText())+ "?");
               //You cannot book a room for an old date
               else if (!(isAfterToday(tfArrivalDate.getText(),date.getTodayDate()))) {lblExceptionArrival.setText("*Invalid date");
               } else lblExceptionArrival.setText("");
            } catch (Exception e) {
               lblExceptionArrival.setText("Wrong input");
            }

            try {
               tfDepartureDate.setText(dateFix(tfDepartureDate.getText()));
               //Checks if the arrival date really exists
               if (!(tfDepartureDate.getText().equals(dateChecker(tfDepartureDate.getText()))))lblExceptionDeparture.setText("Invalid date. Did you mean"+ dateChecker(tfDepartureDate.getText())+ "?");
               //You cannot book a room for an old date
               else if (!(isAfterToday(tfDepartureDate.getText(),date.getTodayDate()))) {lblExceptionDeparture.setText("*Invalid date");}
               //Checks if the departure date is after the arrival date
               else if (!(isAfterToday(tfDepartureDate.getText(),tfArrivalDate.getText())))lblExceptionDeparture.setText("*Departure after arrival");
               else lblExceptionDeparture.setText("");
            } catch (Exception e) {
               lblExceptionDeparture.setText("Wrong input");
            }

            if (noException()) {
               checkFirstAvailableDate();
               //Checks if the room is available for the arrival date
               if (tfArrivalDate.getText().equals(day1text) && (!checkFirstAvailableDate()))lblExceptionArrival.setText("");
               else if ((!tfArrivalDate.getText().equals(day1text) && (!checkFirstAvailableDate())))lblExceptionArrival.setText("Reserved until "+ day1text);
               else if (checkFirstAvailableDate()) {lblExceptionArrival.setText("Reserved room for this date");lblExceptionDeparture.setText("Reserved room for this date");
               } else {
                  lblExceptionArrival.setText("");lblExceptionDeparture.setText("");}		
            }

            if (noException()) {
               checkForAvailability();
               if (checkForAvailability() && (!tfDepartureDate.getText().equals(day1text))) {lblExceptionDeparture.setText("Reserved room from "+ day1text);
               }
            }
            //If everything is fine, creates a new guest and adds booked dates to the array list
            if (noException()) {
               setGuest();
               frame.setVisible(false);
               reset();
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
            reset();
            frame.setVisible(false);}
      });
      btnBack.setBounds(254, 486, 100, 40);
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

   }
   /** Create a new guest with the information from the textFields **/
   private void setGuest() {
      newGuest = new Guest(tfName.getText(), tfAdress.getText(),tfPhoneNumber.getText(), tfEmail.getText(),tfPassport.getText(), tfDateOfBirth.getText(),tfArrivalDate.getText(), tfDepartureDate.getText(),chbExtraBed.isSelected(), Main.getSelectedRoomNumber());
      Main.addBooking(newGuest);
   }

   public Guest getGuest() {
      return newGuest;
   }

   public void setVisibility(boolean value) {
      frame.setVisible(value);
      if (value == true)reset();
   }

   /** Clears all textFields & Labels **/
   public void reset() {
      tfName.setText("");
      tfDateOfBirth.setText("");
      tfPassport.setText("");
      tfAdress.setText("");
      tfPhoneNumber.setText("");
      tfEmail.setText("");
      chbExtraBed.setSelected(false);
      tfArrivalDate.setText("dd/mm/yyyy");
      tfDepartureDate.setText("dd/mm/yyyy");
      lblExceptionName.setText("");
      lblExceptionBirth.setText("");
      lblExceptionAdress.setText("");
      lblExceptionArrival.setText("");
      lblExceptionDeparture.setText("");
      tfName.requestFocus();
   }

   /** This method creates a real date (35/1/2015 -> 31/1/2015) and returns it as a String **/
   public String dateChecker(String dt) {
      Date date = new Date();
      date.setArrivalDate(dt);
      return date.getArrivalDate();
   }
   /** This method makes the dates in the desired format from 1/1/2015 -> 01/01/2015 **/
   public static String dateFix(String dt) {
      String date = "";
      String[] list = dt.split("/");
      if (list[0].length() < 2) list[0] = "0" + list[0];
      if (list[1].length() < 2)list[1] = "0" + list[1];
      for (int i = 0; i < list.length; i++) {
         date += list[i];
         if (i != list.length - 1)date += "/";
      }
      return date;
   }
   /** This method checks every Exception label and returns true if everything is fine **/
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
   /** This method checks if a room is available for a specific time period. If the room is reserved, it stores the last free day to the variable day1text.**/
   public boolean checkForAvailability() {
      String[] date1 = tfArrivalDate.getText().split("/");
      String[] date2 = tfDepartureDate.getText().split("/");
      int day1 = Integer.parseInt(date1[0]), month1 = Integer.parseInt(date1[1]), year1 = Integer.parseInt(date1[2]);
      int day2 = Integer.parseInt(date2[0]), month2 = Integer.parseInt(date2[1]), year2 = Integer.parseInt(date2[2]);
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
   /** If the arrival date is already booked, this method finds the first available date and stores it in day1text **/
   public boolean checkFirstAvailableDate() {
      String[] date1 = tfArrivalDate.getText().split("/");
      String[] date2 = tfDepartureDate.getText().split("/");
      int day1 = Integer.parseInt(date1[0]), month1 = Integer.parseInt(date1[1]), year1 = Integer.parseInt(date1[2]);
      int day2 = Integer.parseInt(date2[0]), month2 = Integer.parseInt(date2[1]), year2 = Integer.parseInt(date2[2]);
      String day2text = day2 + "/" + month2 + "/" + year2;
      day2text = dateFix(day2text);
      day1text = day1 + "/" + month1 + "/" + year1;
      day1text = dateFix(day1text);
      if (!(Main.rooms[Main.getSelectedIndex()].bookedDates.contains(day1text)))return false;
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
         if (tfName.getText().contains("/")|| tfName.getText().contains("\\"))
            return true;
      }
      return false;
   }

}
