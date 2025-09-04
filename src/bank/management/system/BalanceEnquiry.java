
package bank.management.system;
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.sql.*;
public class BalanceEnquiry extends JFrame implements ActionListener {
    JButton back;
    String pinnumber;
    
    BalanceEnquiry(String pinnumber){
        this.pinnumber = pinnumber;
        setLayout(null);
        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icons/atm.jpg"));
        Image i2 = i1.getImage().getScaledInstance(900, 900, Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel image = new JLabel(i3);
        image.setBounds(0, 0, 800, 720);
        add(image);
        
        back = new JButton("Back");
        back.setBounds(315,430,150,30);
        back.addActionListener(this);
        image.add(back);
        
        
        int balance = 0;
        Conn c = new Conn();
        try{
            ResultSet rs = c.s.executeQuery("select * from bank where pin = '"+pinnumber+"'");
            while(rs.next()) {
                if(rs.getString("type").equals("Deposit")) {
                    balance += Integer.parseInt(rs.getString("amount"));
                } else{
                    balance -= Integer.parseInt(rs.getString("amount"));
                }
            }
        } catch(Exception e){
            System.out.println(e);
        }
            
        JLabel text = new JLabel();
        text.setText("Your Current Account Balance is Rs:  " + balance);
        text.setBounds(125,235,400,35);
        text.setForeground(Color.WHITE);
        text.setFont(new Font("System",Font.BOLD,16));
        image.add(text);
        
        setSize(800,720);
        setLocation(300,0);
        setUndecorated(true);
        setVisible(true);
    }
    
    @Override
    public void actionPerformed(ActionEvent ae){
        setVisible(false);
        new Transactions(pinnumber).setVisible(true);
    }
    public static void main(String[] args){
        new BalanceEnquiry("").setVisible(true);
    }
}

