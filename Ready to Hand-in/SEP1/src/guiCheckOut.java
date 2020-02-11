
/**
 * 
 * This class is for the check out function.
 * It creates a new gui where the user has to confirm the checkout of a room.
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


public class guiCheckOut {

   private JFrame frame;
   JLabel lblNewLabel_1 = new JLabel("");


   public guiCheckOut() {
      initialize();
      lblNewLabel_1.setText(Main.rooms[Main.getSelectedIndex()].getRoomNumber()+" - "+Main.rooms[Main.getSelectedIndex()].guests.get(0).getName());
   }

   private void initialize() {
      frame = new JFrame();
      frame.setBounds(100, 100, 326, 161);
      frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      frame.setTitle("Check-out");
      frame.setLocationRelativeTo(null);
      frame.getContentPane().setLayout(null);

      JLabel lblNewLabel = new JLabel("Continue?");
      lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 20));
      lblNewLabel.setBounds(101, 0, 112, 42);
      frame.getContentPane().add(lblNewLabel);

      JButton btnYes = new JButton("Yes");
      btnYes.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent e) {
            frame.setVisible(false);
            guiCheckOutInfo checkOutInfo = new guiCheckOutInfo(); 
         }
      });
      btnYes.setBounds(33, 76, 97, 25);
      frame.getContentPane().add(btnYes);

      JButton btnNo = new JButton("No");
      btnNo.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent e) {
            frame.setVisible(false);
         }
      });
      btnNo.setBounds(163, 76, 97, 25);
      frame.getContentPane().add(btnNo);


      lblNewLabel_1.setBounds(44, 47, 216, 16);
      frame.getContentPane().add(lblNewLabel_1);
      frame.setVisible(true);
      frame.setResizable(false);
   }
}
