import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.awt.Color;
import java.util.ArrayList;

import javax.swing.JCheckBox;
/**
 * This class creates a new check-in form. 
 * The size and number of fields depends on the capacity of a certain room.
 * The user can check-in an already booked room or check-in a new room.
 * Information about all guests has to be written.
 * 
 * @author Daniel
 *
 */
public class guiCheckIn {
   private JFrame frame = new JFrame();
   private JButton btnSave;
   private JButton btnBack;
   private JCheckBox chbExtraBed;

   private ArrayList<Guest> guestList = new ArrayList<Guest>();
   private int numberOfGuests = Main.rooms[Main.getSelectedIndex()].getNumberOfBeds();// set number of guests from room capacity

   private ArrayList<JTextField> listOfTextFields1 = new ArrayList<JTextField>();
   private ArrayList<JTextField> listOfTextFields2 = new ArrayList<JTextField>();
   private ArrayList<JTextField> listOfTextFields3 = new ArrayList<JTextField>();;
   private ArrayList<JTextField> listOfTextFields4 = new ArrayList<JTextField>();;

   private ArrayList<JLabel> listOfLabels1 = new ArrayList<JLabel>();
   private ArrayList<JLabel> listOfLabels2 = new ArrayList<JLabel>();
   private ArrayList<JLabel> listOfLabels3 = new ArrayList<JLabel>();
   private ArrayList<JLabel> listOfLabels4 = new ArrayList<JLabel>();

   private JLabel guest2, guest3, guest4;
   private int bookingIndex;
   Date date = new Date();
   String date1text;
   private String day1text;

   public guiCheckIn() {
      frame.setTitle("Check In");
      frame.setBounds(100, 100, 480, 577);
      frame.setLocationRelativeTo(null);
      frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
      frame.addWindowListener(new WindowListener() {
         @Override
         public void windowClosing(WindowEvent e) {
            if(isBooked())Main.bookings.get(bookingIndex).date.addToBookedDates();
            frame.setVisible(false);
         }
         public void windowOpened(WindowEvent e) {
         }
         public void windowClosed(WindowEvent e) {
         }
         public void windowIconified(WindowEvent e) {
         }
         public void windowDeiconified(WindowEvent e) {
         }
         public void windowActivated(WindowEvent e) {
         }
         public void windowDeactivated(WindowEvent e) {
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
      frame.add(lblNewLabel_9);

      chbExtraBed = new JCheckBox("");
      chbExtraBed.setBounds(170, 431, 113, 25);
      chbExtraBed.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent arg0) {
            if (chbExtraBed.isSelected()) {
               numberOfGuests++;
               buildTextForm();
               buildErrorLabels();
            } else {
               removeFieldsAndLabels(numberOfGuests);
               clearArrayList(numberOfGuests);
               numberOfGuests--;
               buildErrorLabels();
               buildTextForm();
            }
         }
      });
      frame.add(chbExtraBed);

      btnSave = new JButton();
      btnSave.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent arg0) {
            switch (numberOfGuests) {
               case 1: {
                  emtyFieldChecker(listOfTextFields1, listOfLabels1);
                  wrongDate(listOfTextFields1, listOfLabels1);
                  break;}
               case 2: {
                  emtyFieldChecker(listOfTextFields1, listOfLabels1);
                  wrongDate(listOfTextFields1, listOfLabels1);
                  emtyFieldChecker(listOfTextFields2, listOfLabels2);
                  break;}
               case 3: {
                  emtyFieldChecker(listOfTextFields1, listOfLabels1);
                  wrongDate(listOfTextFields1, listOfLabels1);
                  emtyFieldChecker(listOfTextFields2, listOfLabels2);
                  emtyFieldChecker(listOfTextFields3, listOfLabels3);
                  break;}
               case 4: {
                  emtyFieldChecker(listOfTextFields1, listOfLabels1);
                  wrongDate(listOfTextFields1, listOfLabels1);
                  emtyFieldChecker(listOfTextFields2, listOfLabels2);
                  emtyFieldChecker(listOfTextFields3, listOfLabels3);
                  emtyFieldChecker(listOfTextFields4, listOfLabels4);
                  break;}
            }
            if (noException()) {
               if (!isArrivalToday()) {listOfLabels1.get(3).setText("Check in only for today");
               } else listOfLabels1.get(3).setText("");
            }
            if (noException()) {
               checkFirstAvailableDate();
               //Checks if the room is available for the arrival date
               if (listOfTextFields1.get(6).getText().equals(day1text) && (!checkFirstAvailableDate()))listOfLabels1.get(3).setText("");
               else if ((!listOfTextFields1.get(6).getText().equals(day1text) && (!checkFirstAvailableDate())))listOfLabels1.get(3).setText("Reserved until "+ day1text);
               else if (checkFirstAvailableDate()) {listOfLabels1.get(3).setText("Reserved room for this date");listOfLabels1.get(4).setText("Reserved room for this date");
               } else {
                  listOfLabels1.get(3).setText("");listOfLabels1.get(4).setText("");}		
            }
            if (noException()) {
               checkForAvailability();
               if (checkForAvailability() && (!(listOfLabels1.get(3).getText().equals(day1text)))) {listOfLabels1.get(4).setText("Reserved room from " + day1text);}
            }
            if (noException()) {
               addAllGuests(numberOfGuests);
               Main.rooms[Main.getSelectedIndex()].guests = guestList;
               isBooked();
               Main.rooms[Main.getSelectedIndex()].addToBookedDates(listOfTextFields1.get(6).getText(),listOfTextFields1.get(7).getText());
               if (isBooked())Main.bookings.remove(bookingIndex);
               Main.save();
               frame.setVisible(false);
            }
         }
      });
      btnSave.setBounds(50, 486, 100, 40);
      btnSave.setText("Save");
      frame.getContentPane().add(btnSave);

      btnBack = new JButton("Back");
      btnBack.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent arg0) {
            if(isBooked())Main.bookings.get(bookingIndex).date.addToBookedDates();
            frame.setVisible(false);
         }
      });
      btnBack.setBounds(254, 486, 100, 40);
      frame.getContentPane().add(btnBack);
      //Creates all textFields and labels
      buildTextForm();
      buildErrorLabels();

      if (isBooked() && Main.bookings.get(bookingIndex).getExtraBed()) {
         numberOfGuests++;
         chbExtraBed.setEnabled(false);
         buildTextForm();
         buildErrorLabels();
         setFirstGuest();
      }
      if (isBooked()) Main.bookings.get(bookingIndex).date.deleteFromBookedDates();
      else listOfTextFields1.get(6).setText(date.getTodayDate());

      listOfTextFields1.get(6).setToolTipText("dd/mm/yyyy");
      listOfTextFields1.get(7).setToolTipText("dd/mm/yyyy");
      frame.setVisible(true);
      frame.setResizable(false);
   }

   // Adds all guests to GuestList
   private void addAllGuests(int numberOfGuests) {
      for (int i = 1; i < (numberOfGuests + 1); i++) {
         setGuest(i);
      }
   }
   private void setGuest(int index) {
      switch (index) {
         case 1: {
            guestList.add(new Guest(listOfTextFields1.get(0).getText(),listOfTextFields1.get(2).getText(), listOfTextFields1.get(4).getText(), listOfTextFields1.get(5).getText(), listOfTextFields1.get(3).getText(),listOfTextFields1.get(1).getText(), listOfTextFields1.get(6).getText(), listOfTextFields1.get(7).getText(), false));
            break;
         }
         case 2: {
            guestList.add(new Guest(listOfTextFields2.get(0).getText(),listOfTextFields2.get(2).getText(), listOfTextFields2.get(4).getText(), listOfTextFields2.get(5).getText(), listOfTextFields2.get(3).getText(),listOfTextFields2.get(1).getText(), listOfTextFields1.get(6).getText(), listOfTextFields1.get(7).getText(), false));
            break;
         }
         case 3: {
            guestList.add(new Guest(listOfTextFields3.get(0).getText(),listOfTextFields3.get(2).getText(), listOfTextFields3.get(4).getText(), listOfTextFields3.get(5).getText(), listOfTextFields3.get(3).getText(),listOfTextFields3.get(1).getText(), listOfTextFields1.get(6).getText(), listOfTextFields1.get(7).getText(), false));
            break;
         }
         case 4: {
            guestList.add(new Guest(listOfTextFields4.get(0).getText(),listOfTextFields4.get(2).getText(), listOfTextFields4.get(4).getText(), listOfTextFields4.get(5).getText(), listOfTextFields4.get(3).getText(),listOfTextFields4.get(1).getText(), listOfTextFields1.get(6).getText(), listOfTextFields1.get(7).getText(), false));
            break;
         }
      }
   }
   /**This method creates a real date (35/1/2015 -> 31/1/2015) and returns it as a String**/
   private String dateChecker(String dt) {
      Date date = new Date();
      date.setArrivalDate(dt);
      return date.getArrivalDate();
   }
   /** This method makes the dates in the desired format, from 1/1/2015 -> 01/01/2015 **/
   private String dateFix(String dt) {
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
   /** This method checks if every exception label is empty and returns true/false **/
   private boolean noException() {
      switch (numberOfGuests) {
         case 1:
            return (clearErrorChecker(listOfLabels1, 5));
         case 2:
            return (clearErrorChecker(listOfLabels1, 5) && clearErrorChecker(listOfLabels2, 3));
         case 3:
            return (clearErrorChecker(listOfLabels1, 5)&& clearErrorChecker(listOfLabels2, 3) && clearErrorChecker(listOfLabels3, 3));
         case 4:
            return (clearErrorChecker(listOfLabels1, 5)&& clearErrorChecker(listOfLabels2, 3)&& clearErrorChecker(listOfLabels3, 3) && clearErrorChecker(listOfLabels4, 3));
         default:
            return false;
      }
   }
   /** If the textField is empty or the name contains an invalid character (/,\), the label shoes the message "Invalid input" **/
   private void emtyFieldChecker(ArrayList<JTextField> listFields,ArrayList<JLabel> listLabels) {
      for (int i = 0; i < 3; i++) {
         if (listFields.get(i).getText().equals("")) listLabels.get(i).setText("*Invalid input");
         else listLabels.get(i).setText("");
      }
      if (containsInvalidCharacter(listFields.get(0)))listLabels.get(0).setText("*Invalid input");
   }
   /** This method examines if the date really exists, if the departure date is after the arrival date & if the check-in is today **/
   private void wrongDate(ArrayList<JTextField> listTextField,ArrayList<JLabel> listLabel) {
      int help = 6; //Number of textFields
      for (int i = 3; i < 5; i++) {
         try {
            listTextField.get(help).setText(dateFix(listTextField.get(help).getText()));
            if (!(listTextField.get(help).getText().equals(dateChecker(listTextField.get(help).getText())))) {listLabel.get(i).setText("*Did you mean "+ dateChecker(listTextField.get(help).getText()) + "?");}
            else if (!(isAfterToday(listTextField.get(help).getText(),date.getTodayDate())))listLabel.get(i).setText("*Invalid date");
            else if (help == 7 && (!(isAfterToday(listTextField.get(help).getText(),listTextField.get(help - 1).getText()))))listLabel.get(i).setText("*Departure after arrival");
            else listLabel.get(i).setText("");
         }
         catch (Exception e) {
            listLabel.get(i).setText("*Wrong input");
         }
         help++;
      }
   }
   /**This method examines if all exception labels are empty**/
   private boolean clearErrorChecker(ArrayList<JLabel> listLabels, int j) {
      for (int i = 0; i < j; i++) {
         if (!(listLabels.get(i).getText().equals("")))
            return false;
      }
      return true;
   }
   /**Deletes textFields from the frame**/
   private void removeTextField(ArrayList<JTextField> list) {
      for (int i = 0; i < 6; i++) {
         frame.remove(list.get(i));
      }
   }
   /**Deletes labels from the frame**/
   private void removeLabel(ArrayList<JLabel> list) {
      for (int i = 0; i < 3; i++) {
         frame.remove(list.get(i));
      }
   }

   public void setVisible(boolean status) {
      frame.setVisible(status);
   }

   /** Removes all textFields & labels. Number == number of guests **/
   private void removeFieldsAndLabels(int number) {
      switch (number) {
         case 2: {
            removeTextField(listOfTextFields2);
            removeLabel(listOfLabels2);
            frame.remove(guest2);
            break;
         }
         case 3: {
            removeTextField(listOfTextFields3);
            removeLabel(listOfLabels3);
            frame.remove(guest3);
            break;
         }
         case 4: {
            removeTextField(listOfTextFields4);
            removeLabel(listOfLabels4);
            frame.remove(guest4);
            break;
         }
      }
   }

   /** This method clears arryLists**/
   private void clearArrayList(int number) {
      switch (number) {
         case 2: {
            listOfTextFields2.clear();
            listOfLabels2.clear();
            break;
         }
         case 3: {
            listOfTextFields3.clear();
            listOfLabels3.clear();
            break;
         }
         case 4: {
            listOfTextFields4.clear();
            listOfLabels4.clear();
            break;
         }
      }
   }

   /** This method puts all textFields in the frame and sets them in position **/
   private void generateTextFields(int x, int y, int guestNr) {
      switch (guestNr) {
         case 1: {
            for (int i = 0; i < 8; i++) {
               listOfTextFields1.add(new JTextField());
               listOfTextFields1.get(i).setBounds(x, y, 170, 22);
               frame.getContentPane().add(listOfTextFields1.get(i));
               y += 50;
            }
            break;
         }
         case 2: {
            for (int i = 0; i < 6; i++) {
               listOfTextFields2.add(new JTextField());
               listOfTextFields2.get(i).setBounds(x, y, 170, 22);
               frame.getContentPane().add(listOfTextFields2.get(i));
               y += 50;
            }
            break;
         }

         case 3: {
            for (int i = 0; i < 6; i++) {
               listOfTextFields3.add(new JTextField());
               listOfTextFields3.get(i).setBounds(x, y, 170, 22);
               frame.getContentPane().add(listOfTextFields3.get(i));
               y += 50;
            }
            break;
         }
         case 4: {
            for (int i = 0; i < 6; i++) {
               listOfTextFields4.add(new JTextField());
               listOfTextFields4.get(i).setBounds(x, y, 170, 22);
               frame.getContentPane().add(listOfTextFields4.get(i));
               y += 50;
            }
            break;
         }
      }
   }

   private void generateGuest1() {
      JLabel guest1 = new JLabel("Guest1");
      guest1.setBounds(240, 0, 52, 22);
      frame.getContentPane().add(guest1);
      frame.setBounds(100, 100, 480, 577);
      frame.setLocationRelativeTo(null);
      if (listOfTextFields1.size() < 6)generateTextFields(180, 40, 1);
   }

   private void generateGuest2() {
      generateGuest1();
      guest2 = new JLabel("Guest2");
      guest2.setBounds(440, 0, 52, 22);
      frame.getContentPane().add(guest2);
      frame.setBounds(100, 100, 600, 577);
      frame.setLocationRelativeTo(null);
      if (listOfTextFields2.size() < 6)generateTextFields(380, 40, 2);
   }

   private void generateGuest3() {
      generateGuest2();
      guest3 = new JLabel("Guest3");
      guest3.setBounds(640, 0, 52, 22);
      frame.getContentPane().add(guest3);
      frame.setBounds(100, 100, 800, 577);
      frame.setLocationRelativeTo(null);
      if (listOfTextFields3.size() < 6)generateTextFields(580, 40, 3);
   }

   private void generateGuest4() {
      guest4 = new JLabel("Guest4");
      guest4.setBounds(840, 0, 52, 22);
      frame.getContentPane().add(guest4);
      frame.setBounds(100, 100, 1000, 577);
      frame.setLocationRelativeTo(null);
      if (listOfTextFields4.size() < 6)generateTextFields(780, 40, 4);
   }

   private void buildTextForm() {
      switch (numberOfGuests) {
         case 1: {
            generateGuest1();
            break;
         }
         case 2: {
            generateGuest2();
            break;
         }
         case 3: {
            generateGuest3();
            break;
         }
         case 4: {
            generateGuest4();
            break;
         }
      }
   }
   /** This method puts all exception labels in the frame and set them in position **/
   public void generateErrorLabels(int x, int y, int guestNr) {
      switch (guestNr) {
         case 1: {
            for (int i = 0; i < 5; i++) {
               listOfLabels1.add(new JLabel());
               listOfLabels1.get(i).setBounds(x, y, 250, 16);
               listOfLabels1.get(i).setForeground(Color.RED);
               frame.getContentPane().add(listOfLabels1.get(i));
               if (i == 2)y += 200;
               else y += 50;
            }
            break;
         }
         case 2: {
            for (int i = 0; i < 3; i++) {
               listOfLabels2.add(new JLabel());
               listOfLabels2.get(i).setBounds(x, y, 250, 16);
               listOfLabels2.get(i).setForeground(Color.RED);
               frame.getContentPane().add(listOfLabels2.get(i));
               y += 50;
            }
            break;
         }

         case 3: {
            for (int i = 0; i < 3; i++) {
               listOfLabels3.add(new JLabel());
               listOfLabels3.get(i).setBounds(x, y, 250, 16);
               listOfLabels3.get(i).setForeground(Color.RED);
               frame.getContentPane().add(listOfLabels3.get(i));
               y += 50;
            }
            break;
         }
         case 4: {
            for (int i = 0; i < 3; i++) {
               listOfLabels4.add(new JLabel());
               listOfLabels4.get(i).setBounds(x, y, 250, 16);
               listOfLabels4.get(i).setForeground(Color.RED);
               frame.getContentPane().add(listOfLabels4.get(i));
               y += 50;
            }
            break;
         }
      }
   }

   private void buildErrorLabels() {
      switch (numberOfGuests) {
         case 1: {
            generateErrorLabels(180, 60, 1);
            break;
         }
         case 2: {
            generateErrorLabels(180, 60, 1);
            generateErrorLabels(380, 60, 2);
            break;
         }
         case 3: {
            generateErrorLabels(180, 60, 1);
            generateErrorLabels(380, 60, 2);
            generateErrorLabels(580, 60, 3);
            break;
         }
         case 4: {
            generateErrorLabels(180, 60, 1);
            generateErrorLabels(380, 60, 2);
            generateErrorLabels(580, 60, 3);
            generateErrorLabels(780, 60, 4);
            break;
         }
      }
   }
   /** This method takes all information about the guests from booking and fills out all textFields. Executes only if the room was booked. **/
   private void setFirstGuest() {
      listOfTextFields1.get(0).setText(Main.bookings.get(bookingIndex).getName());
      listOfTextFields1.get(1).setText(Main.bookings.get(bookingIndex).getDateOfBirth());
      listOfTextFields1.get(2).setText(Main.bookings.get(bookingIndex).getAddress());
      listOfTextFields1.get(3).setText(Main.bookings.get(bookingIndex).getPassport());
      listOfTextFields1.get(4).setText(Main.bookings.get(bookingIndex).getPhone());
      listOfTextFields1.get(5).setText(Main.bookings.get(bookingIndex).getEmail());
      listOfTextFields1.get(6).setText(Main.bookings.get(bookingIndex).getArrivalDate());
      listOfTextFields1.get(7).setText(Main.bookings.get(bookingIndex).getDepartureDate());
   }
   /** This method examines if the room was booked. Returns true/false. **/
   private boolean isBooked() {
      String[] list = Main.getSelectedValue().split(" ");
      for (int i = 0; i < Main.bookings.size(); i++) {
         if (list[0].equals(Main.bookings.get(i).getRoomNumber() + "")&& list[list.length - 3].equals(Main.bookings.get(i).getArrivalDate())) {
            bookingIndex = i;
            setFirstGuest();
            return true;
         }
      }
      return false;
   }

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
   /** This method checks if a room is available for a certain time period. If the room is reserved, it stores the last free day to the variable day1text.**/
   public boolean checkForAvailability() {
      String[] date1 = listOfTextFields1.get(6).getText().split("/");
      String[] date2 = listOfTextFields1.get(7).getText().split("/");
      int day1 = Integer.parseInt(date1[0]), month1 = Integer.parseInt(date1[1]), year1 = Integer.parseInt(date1[2]);
      int day2 = Integer.parseInt(date2[0]), month2 = Integer.parseInt(date2[1]), year2 = Integer.parseInt(date2[2]);
      String day2text = day2 + "/" + month2 + "/" + year2;
      day2text = dateFix(day2text);
      day1text = day1 + "/" + month1 + "/" + year1;
      day1text = dateFix(day1text);
      if (Main.rooms[Main.getSelectedIndex()].bookedDates.contains(day1text))return true;
      while (!day1text.equals(day2text)) {
         day1++;
         if (day1 > Date.getDaysOfMonth(month1)) {day1 = 1;month1++;}
         if (month1 == 13) {month1 = 1;year1++;}
         day1text = day1 + "/" + month1 + "/" + year1;
         day1text = dateFix(day1text);
         if (Main.rooms[Main.getSelectedIndex()].bookedDates.contains(day1text)) {return true;
         }
      }
      return false;
   }
   /** If the arrival date is already booked, this method finds the first available date and stores it in day1text **/
   public boolean checkFirstAvailableDate() {
      String[] date1 = listOfTextFields1.get(6).getText().split("/");
      String[] date2 = listOfTextFields1.get(7).getText().split("/");
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
   /** You can check-in a room only for today **/
   public boolean isArrivalToday() {
      if (listOfTextFields1.get(6).getText().equals(date.getTodayDate()))
         return true;
      else
         return false;
   }
   /** Checks if the name textFiled contains / or \ **/
   public boolean containsInvalidCharacter(JTextField tf) {
      for (int i = 0; i < tf.getText().length(); i++) {
         if (tf.getText().contains("/") || tf.getText().contains("\\"))
            return true;
      }
      return false;
   }
}