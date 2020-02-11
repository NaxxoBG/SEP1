/**
 * 
 * The Main class.
 * This class is executed when the program is running,
 * also it creates the Main gui where the user can select rooms and different operations to be performed.
 * 
 * @author Martin
 *
 */

import javax.swing.DefaultListModel;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Calendar;

import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.ListSelectionEvent;

import java.awt.Font;
import java.awt.Color;

public class Main implements Runnable {


   @SuppressWarnings("unused")
   private int selectedIndexOfGuests = 0;
   @SuppressWarnings("unused")
   private static guiCheckOut guiCheckout;
   @SuppressWarnings("unused")
   private static guiCheckIn guiCheckin;
   @SuppressWarnings("unused")
   private static guiEdit guiedit;
   private static guiBooking guiBooking = new guiBooking();
   private static JFrame frame = new JFrame("Hotel");

   static JButton btnTodayList = new JButton("Today");
   static JButton btnFreeRooms = new JButton("Available");
   static JButton btnBooked = new JButton("Booked");
   static JButton btnBooking = new JButton("Booking");
   static JButton btnCheckOut = new JButton("Check Out");
   static JButton btnCheckIn = new JButton("Check In");
   static JButton btnOcuppied = new JButton("Occupied");
   static JLabel lblRoomNumber = new JLabel("Room:");
   static JLabel lblNumberOfGuests = new JLabel("Number of guests:");
   static JLabel lblTypeOfBed = new JLabel("Type of bed:");
   static JLabel lblExtraBed = new JLabel("Extra Bed: ");
   static JLabel lblGuests = new JLabel("Guests:");
   static JLabel lblType = new JLabel("Type:");
   static JLabel lblArrivalDate = new JLabel("Arrival date:");
   static JLabel lblDepartureDate = new JLabel("Departure date");
   static JLabel lblGuest1 = new JLabel("");
   static JLabel lblGuest2 = new JLabel("");
   static JLabel lblGuest3 = new JLabel("");
   static JLabel lblGuest4 = new JLabel("");
   private static JLabel lblName = new JLabel("name");
   private static JLabel lblAddress = new JLabel("Address:");
   private static JLabel lblTelephone = new JLabel("Telephone:");
   private static JLabel lblDateOfBirth = new JLabel("Date of birth:");
   private static JLabel lblEmail = new JLabel("Email:");
   private static JLabel lblPassport = new JLabel("Passport:");
   private static int index;
   private static JTextField tfSearch;
   private static int indexOfPerson = 0;
   private static final JScrollPane scrollPane = new JScrollPane();
   private static final JButton btnIOPminus = new JButton("<");
   private static final JButton btnIOPplus = new JButton(">");
   private static final JLabel lblId = new JLabel((indexOfPerson + 1) + "");
   private static JTextField tfAdditionFees;
   private static final JLabel lblExceptionAdditionalFees = new JLabel("");
   private static final JLabel lblAdditionalfeesrespond = new JLabel("additionalFeesRespond");
   private static JButton btnOk = new JButton("OK");

   static DefaultListModel<String> listModel = new DefaultListModel<String>();
   private static boolean checkOrCancel = false;
   static JList<String> list_1 = new JList<String>();
   private static String[] help;
   static Room[] rooms = new Room[25];
   static ArrayList<Guest> bookings = new ArrayList<>();

   /** Main Method **/
   public static void main(String[] args) throws ClassNotFoundException {
      initialize();
      setArrays();
      automaticBooksDeleter();

   }

   /**
    * This method builds the GUI and contains all listeners for Jlist and Buttons
    **/
   private static void initialize() {

      frame.setBounds(100, 100, 751, 577);
      frame.setLocationRelativeTo(null);
      frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      frame.getContentPane().setLayout(null);
      btnTodayList.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent e) { // Button Today action
            listModel = new DefaultListModel<String>();
            for (int i = 0; i < bookings.size(); i++) { // write all check-ins for today
               if (bookings.get(i).getArrivalDate()
                     .equals(Date.getTodayDate())) {
                  listModel.addElement(bookings.get(i).getRoomNumber()
                        + " - " + bookings.get(i).getName()
                        + " - Check In" + " "
                        + bookings.get(i).getArrivalDate() + " - "
                        + bookings.get(i).getDepartureDate());
               }

            }

            for (int j = 0; j < rooms.length; j++) { // write all checkouts for today										
               if (!rooms[j].isAvilable()
                     && rooms[j].getDepartureDate().equals(
                           Date.getTodayDate()))
                  listModel.addElement(rooms[j].getRoomNumber() + " - "
                        + " Check Out");
            }

            list_1.setModel(listModel);
            if (listModel.isEmpty())
               JOptionPane.showMessageDialog(null, "No results", "Information", JOptionPane.INFORMATION_MESSAGE);
         }
      });

      btnTodayList.setBounds(0, 112, 97, 25);
      frame.getContentPane().add(btnTodayList);
      btnFreeRooms.setBounds(0, 150, 97, 25);

      btnFreeRooms.addActionListener(new ActionListener() { // Shows a list of all available rooms for today
         public void actionPerformed(ActionEvent e) {
            listModel = new DefaultListModel<String>();
            for (int i = 0; i < rooms.length; i++) {

               if (rooms[i].isAvilableForAvilableRooms()) {
                  listModel.addElement(rooms[i].getRoomNumber()
                        + " " + rooms[i].getType() + " "
                        + rooms[i].getTypeOfBed() + " "
                        + rooms[i].getDefaultPrice() + "€");
               }

            }
            list_1.setModel(listModel);
            if (listModel.isEmpty())
               JOptionPane.showMessageDialog(null, "No results","Information",JOptionPane.INFORMATION_MESSAGE);
         }

      });

      frame.getContentPane().add(btnFreeRooms);

      btnBooked.addActionListener(new ActionListener() { // Shows all bookings
         public void actionPerformed(ActionEvent e) {
            listModel = new DefaultListModel<String>();
            for (int i = 0; i < bookings.size(); i++) {
               if (!bookings.get(i).isChecked())
                  listModel.addElement(bookings.get(i).getRoomNumber()
                        + " " + bookings.get(i).getName() + " "
                        + bookings.get(i).getArrivalDate() + " - "
                        + bookings.get(i).getDepartureDate());
            }
            list_1.setModel(listModel);
            if (listModel.isEmpty())
               JOptionPane.showMessageDialog(null, "No results", "Information", JOptionPane.INFORMATION_MESSAGE);
         }
      });

      btnBooked.setBounds(0, 190, 97, 25);
      frame.getContentPane().add(btnBooked);
      btnBooking.setBounds(361, 469, 97, 25);

      btnBooking.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent e) { // Opens the Booking Gui
            guiBooking.setVisibility(true);

         }
      });
      frame.getContentPane().add(btnBooking);
      btnCheckIn.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent e) {
            guiCheckin = new guiCheckIn();
         }
      });
      btnCheckIn.setBounds(487, 469, 97, 25);
      frame.getContentPane().add(btnCheckIn);
      btnCheckOut.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent e) {

            if (checkOrCancel == true)
               guiedit = new guiEdit();
            else
               guiCheckout = new guiCheckOut();

         }
      });
      btnCheckOut.setBounds(611, 469, 97, 25);
      frame.getContentPane().add(btnCheckOut);
      btnOcuppied.setBounds(0, 228, 97, 25);
      btnOcuppied.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent e) {
            listModel = new DefaultListModel<String>();
            for (int i = 0; i < rooms.length; i++) { // button occupied: shows all occupied rooms
               if (!rooms[i].isAvilable())
                  listModel.addElement(rooms[i].getRoomNumber() + " "
                        + rooms[i].getType() + " "
                        + rooms[i].guests.get(0).getName());
            }

            list_1.setModel(listModel);
            if (listModel.isEmpty())
               JOptionPane.showMessageDialog(null, "No results", "Information", JOptionPane.INFORMATION_MESSAGE);

         }
      });
      frame.getContentPane().add(btnOcuppied);

      JButton btnGuests = new JButton("Guests");
      btnGuests.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent e) {
            listModel = new DefaultListModel<String>();
            for (int i = 0; i < rooms.length; i++) { // button guests: shows all guests in hotel
               if (!rooms[i].isAvilable())
                  for (int j = 0; j < rooms[i].guests.size(); j++) {
                     listModel.addElement(rooms[i].getRoomNumber() + " "
                           + rooms[i].guests.get(j).getName());
                  }

            }

            list_1.setModel(listModel);
            if (listModel.isEmpty())
               JOptionPane.showMessageDialog(null, "No results", "Information", JOptionPane.INFORMATION_MESSAGE);
         }
      });
      btnGuests.setBounds(0, 266, 97, 25);
      frame.getContentPane().add(btnGuests);

      tfSearch = new JTextField();
      tfSearch.setBounds(109, 51, 229, 36);
      frame.getContentPane().add(tfSearch);
      tfSearch.setColumns(10);

      JButton btnSearch = new JButton("Search"); // Button Search
      btnSearch.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent e) {

            listModel = new DefaultListModel<String>();
            if (tfSearch.getText().equals(""))
               return;
            else if (tfSearch.getText().contains("-")) {
               String[] a = tfSearch.getText().split("-");
               searchRoomByDate(a[0], a[1]);
            } else
               try {
                  index = Integer.parseInt(tfSearch.getText()) - 1;
                  if (index < 25 && index >= 0)
                     listModel.addElement(rooms[index].getRoomNumber()
                           + " " + rooms[index].getType() + " "
                           + rooms[index].getTypeOfBed() + " "
                           + rooms[index].getDefaultPrice() + "€");

               } catch (NumberFormatException ex) {
                  for (int i = 0; i < rooms.length; i++) {
                     for (int j = 0; j < rooms[i].getNumberOfGuests(); j++) {
                        if (tfSearch.getText().equalsIgnoreCase(
                              rooms[i].guests.get(j).getName()))
                           listModel.addElement(rooms[i]
                                 .getRoomNumber()
                                 + " "
                                 + rooms[i].guests.get(j).getName());
                     }

                  }

                  for (int i = 0; i < bookings.size(); i++) {
                     if (tfSearch.getText().equalsIgnoreCase(
                           bookings.get(i).getName()))
                        listModel.addElement(bookings.get(i)
                              .getRoomNumber()
                              + " "
                              + bookings.get(i).getName()
                              + " - Booking"
                              + " "
                              + bookings.get(i).getArrivalDate()
                              + " - "
                              + bookings.get(i).getDepartureDate());
                  }

               }

            list_1.setModel(listModel);
            if (listModel.isEmpty())
               JOptionPane.showMessageDialog(null, "No results", "Information", JOptionPane.INFORMATION_MESSAGE);

         }
      });
      btnSearch.setBounds(0, 57, 97, 25);
      frame.getContentPane().add(btnSearch);
      scrollPane.setBounds(109, 100, 229, 354);

      frame.getContentPane().add(scrollPane);

      list_1.addListSelectionListener(new ListSelectionListener() { //A Listener for JList. This happens every time the user clicks on a different room from the List
         public void valueChanged(ListSelectionEvent e) {
            if (list_1.getSelectedIndex() == -1) {
               btnCheckOut.setEnabled(false);
               btnBooking.setEnabled(false);
               btnCheckIn.setEnabled(false);
            } else {
               automaticBooksDeleter();
               checkOrCancel = false;
               btnCheckOut.setText("Check Out");
               btnOk.setEnabled(false);
               lblAdditionalfeesrespond.setText("");
               btnIOPplus.setEnabled(false);
               btnIOPminus.setEnabled(false);
               lblId.setEnabled(false);
               help = list_1.getSelectedValue().split(" ");
               index = Integer.parseInt(help[0]) - 1;
               lblRoomNumber.setText("Room: "
                     + rooms[index].getRoomNumber());
               lblType.setText("Type: " + rooms[index].getType());
               lblTypeOfBed.setText("Type of bed: "
                     + rooms[index].getTypeOfBed());

               if (!rooms[index].isAvilable()
                     && !list_1.getSelectedValue().contains("/"))
                  btnOk.setEnabled(true);

               if (list_1.getSelectedValue().contains("Booking")
                     || list_1.getSelectedValue().contains("/"))
                  setGuestInformationFromBooking();
               else
                  setGuestInformationFromRoom();
               if ((rooms[index].guests.size() > 1)) {
                  btnIOPplus.setEnabled(true);
                  btnIOPminus.setEnabled(true);
                  lblId.setEnabled(true);

               }

               if (rooms[index].isAvilable()
                     || list_1.getSelectedValue().contains("Check In")) {
                  btnCheckOut.setEnabled(false);
                  btnBooking.setEnabled(true);
                  btnCheckIn.setEnabled(true);
               } else {
                  btnCheckOut.setEnabled(true);
                  btnBooking.setEnabled(true);
                  btnCheckIn.setEnabled(false);
               }

               if (help[help.length - 1].contains("/")
                     || help[help.length - 1].equals("Booking")) {
                  btnCheckOut.setText("Edit");
                  btnCheckOut.setEnabled(true);
                  checkOrCancel = true;
               }

            }
         }

      }); //End of list Listener


      scrollPane.setViewportView(list_1);

      lblRoomNumber.setBounds(361, 51, 145, 16);
      frame.getContentPane().add(lblRoomNumber);

      lblNumberOfGuests.setBounds(361, 71, 131, 16);
      frame.getContentPane().add(lblNumberOfGuests);

      lblTypeOfBed.setBounds(532, 71, 189, 16);
      frame.getContentPane().add(lblTypeOfBed);

      lblExtraBed.setBounds(532, 90, 189, 16);
      frame.getContentPane().add(lblExtraBed);

      lblGuests.setBounds(361, 135, 43, 16);
      frame.getContentPane().add(lblGuests);

      lblArrivalDate.setBounds(361, 90, 189, 16);
      frame.getContentPane().add(lblArrivalDate);

      lblDepartureDate.setBounds(361, 112, 189, 16);
      frame.getContentPane().add(lblDepartureDate);

      lblType.setBounds(532, 51, 189, 16);
      frame.getContentPane().add(lblType);

      lblGuest1.setBounds(412, 135, 246, 16);
      frame.getContentPane().add(lblGuest1);

      lblGuest2.setBounds(412, 150, 246, 16);
      frame.getContentPane().add(lblGuest2);

      lblGuest3.setBounds(412, 164, 246, 16);
      frame.getContentPane().add(lblGuest3);

      lblGuest4.setBounds(412, 178, 246, 16);
      frame.getContentPane().add(lblGuest4);

      btnCheckOut.setEnabled(false);
      btnBooking.setEnabled(false);
      btnCheckIn.setEnabled(false);

      btnIOPminus.addActionListener(new ActionListener() { // A small button with an arrow that is used to switch to another guest in the room
         public void actionPerformed(ActionEvent e) {
            if (indexOfPerson - 1 < 0)
               indexOfPerson = Main.rooms[index].guests.size() - 1;
            else
               indexOfPerson--;
            lblId.setText((indexOfPerson + 1) + "");
            setGuestInformationFromRoom();
         }
      });

      btnIOPminus.setBounds(450, 421, 43, 25);

      frame.getContentPane().add(btnIOPminus);

      btnIOPplus.addActionListener(new ActionListener() { //A small button with an arrow that is used to switch to another guest in the room
         public void actionPerformed(ActionEvent e) {
            if (indexOfPerson + 1 > Main.rooms[index].guests.size() - 1)
               indexOfPerson = 0;
            else
               indexOfPerson++;
            lblId.setText((indexOfPerson + 1) + "");
            setGuestInformationFromRoom();
         }
      });

      btnIOPplus.setBounds(528, 421, 43, 25);

      frame.getContentPane().add(btnIOPplus);

      lblName.setFont(lblName.getFont().deriveFont(
            lblName.getFont().getStyle() | Font.BOLD));
      lblName.setBounds(450, 278, 191, 16);
      frame.getContentPane().add(lblName);

      lblAddress.setBounds(361, 303, 168, 16);
      frame.getContentPane().add(lblAddress);

      lblTelephone.setBounds(528, 303, 193, 16);
      frame.getContentPane().add(lblTelephone);

      lblDateOfBirth.setBounds(361, 341, 168, 16);
      frame.getContentPane().add(lblDateOfBirth);

      lblEmail.setBounds(528, 341, 193, 16);
      frame.getContentPane().add(lblEmail);

      lblPassport.setBounds(361, 382, 136, 16);
      frame.getContentPane().add(lblPassport);
      lblId.setBounds(505, 425, 19, 16);

      frame.getContentPane().add(lblId);
      lblId.setEnabled(false);
      btnIOPplus.setEnabled(false);
      btnIOPminus.setEnabled(false);

      tfAdditionFees = new JTextField();
      tfAdditionFees.setBounds(608, 207, 50, 22);
      frame.getContentPane().add(tfAdditionFees);
      tfAdditionFees.setColumns(10);

      btnOk.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent e) { // Add the value of the Textfield into the additional price for the room
            try {
               rooms[index].addAdditionalFees(Integer
                     .parseInt(tfAdditionFees.getText()));
               lblExceptionAdditionalFees.setText("");
               lblAdditionalfeesrespond.setText(tfAdditionFees.getText()
                     + "€ added!");
               tfAdditionFees.setText("");
               save();
            } catch (NumberFormatException er) {
               lblExceptionAdditionalFees.setText("Invalid Input");
               tfAdditionFees.setText("");
            }
         }
      });

      btnOk.setBounds(658, 206, 63, 25);
      frame.getContentPane().add(btnOk);
      lblExceptionAdditionalFees.setForeground(Color.RED);
      lblExceptionAdditionalFees.setBounds(534, 210, 77, 16);

      frame.getContentPane().add(lblExceptionAdditionalFees);

      JLabel lblAdditionalFees = new JLabel("Additional fees:");
      lblAdditionalFees.setBounds(611, 190, 106, 16);
      frame.getContentPane().add(lblAdditionalFees);
      lblAdditionalfeesrespond.setForeground(Color.BLUE);
      lblAdditionalfeesrespond.setBounds(611, 228, 110, 16);
      lblAdditionalfeesrespond.setText("");
      frame.getContentPane().add(lblAdditionalfeesrespond);
      btnOk.setEnabled(false);
      frame.setVisible(true);
      frame.setResizable(false);

   }

   /** SetArrays method loads or creates new files of Room and booking arrays (Saves) **/
   private static void setArrays() throws ClassNotFoundException {
      boolean endOfFile = false;
      File file = new File("saveRooms.bin");
      File file2 = new File("saveBookings.bin");
      if (file.exists()) {

         try {
            FileInputStream fis = new FileInputStream(file);
            ObjectInputStream in = new ObjectInputStream(fis);
            for (int i = 0; i < rooms.length; i++) {
               rooms[i] = (Room) in.readObject();
            }
            in.close();
         } catch (IOException e) {
         }

      } else {
         for (int i = 0; i < 3; i++) {
            rooms[i] = new Room(1, i + 1, 110, "Single", "Room");
         }

         for (int i = 3; i < 15; i++) {
            rooms[i] = new Room(2, i + 1, 170, "Double King", "Room");
         }

         for (int i = 15; i < 21; i++) {
            rooms[i] = new Room(2, i + 1, 170, "Double Twin", "Room");
         }

         for (int i = 21; i < 23; i++) {
            rooms[i] = new Room(1, i + 1, 220, "1-Bedroom", "Suite");
         }

         rooms[23] = new Room(2, 24, 340, "2-Bedrooms", "Suite");
         rooms[24] = new Room(3, 25, 450, "3-Bedrooms", "Suite");

      }
      if (file2.exists()) {

         try {
            FileInputStream fis = new FileInputStream(file2);
            ObjectInputStream in = new ObjectInputStream(fis);

            while (!endOfFile) {
               try {
                  bookings.add((Guest) in.readObject());
               } catch (EOFException e) {
                  endOfFile = true;
               }
            }
            in.close();
         } catch (IOException e) {
         }

      }

      save();
      bookings.add(new Guest("", "", "", "", "", "", Date.getTodayDate(),
            "01/01/01", false, 5));
      bookings.remove(bookings.size() - 1);
   }

   public static void addBooking(Guest guest) {
      bookings.add(guest);
   }

   @Override /** this method is needed to make runnable jar files **/
   public void run() {
      new Main();

   }

   /** save new files **/
   public static void save() {

      try {
         File file = new File("saveRooms.bin");
         File file2 = new File("saveBookings.bin");
         FileOutputStream fos = new FileOutputStream(file);
         ObjectOutputStream out = new ObjectOutputStream(fos);
         for (int i = 0; i < rooms.length; i++) {
            out.writeObject(rooms[i]);
         }
         out.close();

         fos = new FileOutputStream(file2);
         out = new ObjectOutputStream(fos);
         for (int i = 0; i < bookings.size(); i++) {
            out.writeObject(bookings.get(i));
         }
         out.close();

      } catch (IOException e) {
      }

   }

   public static int getSelectedRoomNumber() {
      return index + 1;
   }

   public static int getSelectedIndex() {
      return index;
   }

   public static String getSelectedValue() {
      return list_1.getSelectedValue();
   }

   public static void setNewRoom(int index, Room room) {
      rooms[index] = room;

   }

   /** This method is using the search Function. The arguments are two dates and this model returns all rooms that are available between these days **/
   public static void searchRoomByDate(String date1, String date2) {
      String[] d1 = date1.split("/");
      String[] d2 = date2.split("/");
      boolean check;
      int day1 = Integer.parseInt(d1[0]), month1 = Integer.parseInt(d1[1]), year1 = Integer
            .parseInt(d1[2]);
      int day2 = Integer.parseInt(d2[0]), month2 = Integer.parseInt(d2[1]), year2 = Integer
            .parseInt(d2[2]);
      String day2text = day2 + "/" + month2 + "/" + year2;
      String day1text = day1 + "/" + month1 + "/" + year1;
      for (int i = 0; i < rooms.length; i++) {
         check = false;
         while (!day1text.equals(day2text)) {
            if (rooms[i].bookedDates.contains(day1text)) {
               check = true;
            }
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
         }
         day1 = Integer.parseInt(d1[0]);
         month1 = Integer.parseInt(d1[1]);
         year1 = Integer.parseInt(d1[2]);
         day1text = day1 + "/" + month1 + "/" + year1;
         if (check == false)
            listModel.addElement(rooms[i].getRoomNumber() + " "
                  + rooms[i].getType() + " " + rooms[i].getTypeOfBed()
                  + " " + rooms[i].getDefaultPrice() + "€");
         check = false;
      }
      for (int j = 0; j < rooms[getSelectedIndex()].bookedDates.size(); j++) {
      }
   }

   /** This method is used in List listener, it sets all labels according to the room information and the guests' information **/
   private static void setGuestInformationFromRoom() {
      lblNumberOfGuests.setText("Number of guests: "
            + rooms[index].getNumberOfGuests());
      lblArrivalDate
      .setText("Arrival Date: " + rooms[index].getArrivalDate());
      lblDepartureDate.setText("Departure Date: "
            + rooms[index].getDepartureDate());

      if (rooms[index].isExtraBed())
         lblExtraBed.setText("Extra bed: Yes");
      else
         lblExtraBed.setText("Extra bed: No");

      if (rooms[index].guests.size() > 0)
         lblGuest1.setText(rooms[index].guests.get(0).getName());
      else
         lblGuest1.setText("");
      if (rooms[index].guests.size() > 1)
         lblGuest2.setText(rooms[index].guests.get(1).getName());
      else
         lblGuest2.setText("");
      if (rooms[index].guests.size() > 2)
         lblGuest3.setText(rooms[index].guests.get(2).getName());
      else
         lblGuest3.setText("");
      if (rooms[index].guests.size() > 3)
         lblGuest4.setText(rooms[index].guests.get(3).getName());
      else
         lblGuest4.setText("");

      if ((rooms[index].guests.size() > 0)) {
         lblName.setText((rooms[index].guests.get(indexOfPerson).getName()));
         lblAddress.setText("Address: "
               + (rooms[index].guests.get(indexOfPerson).getAddress()));
         lblTelephone.setText("Telephone: "
               + (rooms[index].guests.get(indexOfPerson).getPhone()));
         lblPassport.setText("Passport: "
               + (rooms[index].guests.get(indexOfPerson).getPassport()));
         lblDateOfBirth
         .setText("Date of birth: "
               + (rooms[index].guests.get(indexOfPerson)
                     .getDateOfBirth()));
         lblEmail.setText("Email: "
               + (rooms[index].guests.get(indexOfPerson).getEmail()));
      } else {
         lblName.setText("Name");
         lblAddress.setText("Address:");
         lblTelephone.setText("Telephone:");
         lblPassport.setText("Passport:");
         lblDateOfBirth.setText("Date of Birth:");
         lblEmail.setText("Email:");
      }
   }

   /** if the user clicks on a booked room but not checked room this method will be used for the labels**/
   private static void setGuestInformationFromBooking() {
      int x = 0;
      String departureDate = "";
      if (list_1.getSelectedValue().contains("/"))
         departureDate = help[help.length - 1];
      for (int i = 0; i < bookings.size(); i++) {
         if (index + 1 == bookings.get(i).getRoomNumber()
               && departureDate.equals(bookings.get(i).getDepartureDate())) {
            x = i;
            break;
         }
      }
      int i = 0;
      if (bookings.get(x).getExtraBed())
         i = 1;
      else
         i = 0;

      lblNumberOfGuests.setText("Number of guests: "
            + rooms[index].getNumberOfGuests() + i);
      lblArrivalDate.setText("Arrival Date: "
            + bookings.get(x).getArrivalDate());
      lblDepartureDate.setText("Departure Date: "
            + bookings.get(x).getDepartureDate());
      if (bookings.get(0).getExtraBed())
         lblExtraBed.setText("Extra bed: Yes");
      else
         lblExtraBed.setText("Extra bed: No");

      lblGuest1.setText("");
      lblGuest2.setText("");
      lblGuest3.setText("");
      lblGuest4.setText("");

      lblName.setText("Name: " + bookings.get(x).getName());
      lblAddress.setText("Address: " + bookings.get(x).getAddress());
      lblTelephone.setText("Telephone: " + bookings.get(x).getPhone());
      lblPassport.setText("Passport: " + bookings.get(x).getPassport());
      lblDateOfBirth.setText("Date of birth: "
            + bookings.get(x).getDateOfBirth());
      lblEmail.setText("Email: " + bookings.get(x).getEmail());

   }

   /** This method is executed very often, on startup, on clicks, etc.. It checks the time and if it is after 18:00, it deletes all bookings for today (Only if guests did not come)**/
   public static void automaticBooksDeleter() {
      Calendar cal = Calendar.getInstance();

      String[] dates;
      int hours = cal.get(Calendar.HOUR_OF_DAY);
      int departureDay, departureMonth, departureYear;
      int todayDay = Calendar.getInstance().get(Calendar.DAY_OF_MONTH);
      int todayMonth = Calendar.getInstance().get(Calendar.MONTH) + 1;
      int todayYear = Calendar.getInstance().get(Calendar.YEAR);

      for (int i = 0; i < bookings.size(); i++) {
         dates = bookings.get(i).getArrivalDate().split("/");
         departureDay = Integer.parseInt(dates[0]);
         departureMonth = Integer.parseInt(dates[1]);
         departureYear = Integer.parseInt(dates[2]);
         if (todayDay == departureDay && todayMonth == departureMonth
               && todayYear == departureYear && hours >= 18) {
            bookings.get(i).date.deleteFromBookedDates();
            bookings.remove(i);
            save();
         } else if (todayDay > departureDay && todayMonth == departureMonth
               && todayYear == departureYear) {
            bookings.get(i).date.deleteFromBookedDates();
            bookings.remove(i);
            save();
         }

      }
   }

}
