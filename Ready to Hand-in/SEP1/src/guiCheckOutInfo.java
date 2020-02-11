
/**
 * 
 * This class is for the check out function.
 * After the Check Out confirmation, this creates a new gui where the user can see information about the price and also it
 * removes all guests and booked dates from this Occupied room.
 * 
 * @author Atanas
 *
 */

import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;


public class guiCheckOutInfo {

   private JFrame frame;
   private int numberOfGuests, generalFees,additionalFees,total,daysOfStay;

   public guiCheckOutInfo() {
      initialize();
   }

   private void initialize() {
      frame = new JFrame();
      numberOfGuests = Main.rooms[Main.getSelectedIndex()].guests.size();
      generalFees = Main.rooms[Main.getSelectedIndex()].getPrice();
      additionalFees = Main.rooms[Main.getSelectedIndex()].getAdditionalFees();
      daysOfStay=Main.rooms[Main.getSelectedIndex()].guests.get(0).date.getDaysOfStayForCheckOut();
      total = generalFees*daysOfStay+additionalFees;
      frame.setTitle("Check-out Information");
      frame.setBounds(100, 100, 450, 300);
      frame.getContentPane().setLayout(null);
      frame.setLocationRelativeTo(null);

      JLabel lblGeneralFees = new JLabel("Price per day: "+generalFees+"€");
      lblGeneralFees.setBounds(34, 30, 229, 16);
      frame.getContentPane().add(lblGeneralFees);

      JLabel lblNewLabel = new JLabel("Number of guests: "+numberOfGuests);
      lblNewLabel.setBounds(34, 59, 185, 16);
      frame.getContentPane().add(lblNewLabel);

      JLabel lblStayLenght = new JLabel("Days of stay: "+daysOfStay+" days");
      lblStayLenght.setBounds(228, 30, 204, 16);
      frame.getContentPane().add(lblStayLenght);

      JLabel lblNewLabel_1= new JLabel();
      if (additionalFees==0)
         lblNewLabel_1.setText("Additional fees: No");
      else lblNewLabel_1.setText("Additional fees: Yes");
      lblNewLabel_1.setBounds(231, 59, 189, 16);
      frame.getContentPane().add(lblNewLabel_1);

      JLabel lblNewLabel_2 = new JLabel("additional fees: "+additionalFees+"€");
      lblNewLabel_2.setBounds(34, 109, 161, 16);
      frame.getContentPane().add(lblNewLabel_2);

      JLabel lblNewLabel_3 = new JLabel("Total: "+total+"€");
      lblNewLabel_3.setFont(new Font("Tahoma", Font.PLAIN, 30));
      lblNewLabel_3.setBounds(123, 138, 185, 57);
      frame.getContentPane().add(lblNewLabel_3);

      JButton btnOk = new JButton("OK");
      btnOk.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent e) {
            Main.rooms[Main.getSelectedIndex()].reset();
            Main.save();
            frame.setVisible(false);
         }
      });
      btnOk.setBounds(155, 208, 97, 25);
      frame.getContentPane().add(btnOk);
      frame.setVisible(true);
      frame.setResizable(false);
   }
}
