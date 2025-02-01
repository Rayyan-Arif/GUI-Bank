import javax.swing.*;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;


public class Menu extends JFrame implements ActionListener{
    JButton first,second,third,fourth,fifth,sixth , seventh;
    Bank User = new Bank();
    Menu()
    {
        this.setTitle("THE BANK APPLICATION");
        JLabel mainLabel = new JLabel("                                    THE BANK");
        first = new JButton("1. Open Account");
        second = new JButton("2. Show Account Details");
        third = new JButton("3. Deposit Money In Account");
        fourth = new JButton("4. Withdraw Cash From Account");
        fifth = new JButton("5. Transfer Money To Another Account");
        sixth = new JButton("6. Close Your Account");
        seventh = new JButton("7. Exit The Application");
        BankBackground background = new BankBackground();
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(1900,1000);
        this.setLocationRelativeTo(null);
        mainLabel.setBounds(0,0,1900,130);

        first.setBounds(10,130,800,85);
        first.setFont(new Font("Arial" , Font.PLAIN , 40));
        first.setFocusable(false);
        first.setBackground(Color.BLACK);
        first.setForeground(Color.WHITE);
        first.addActionListener(this);
        this.add(first);

        second.setBounds(10,220,800,85);
        second.setFont(new Font("Arial" , Font.PLAIN , 40));
        second.setFocusable(false);
        second.setBackground(Color.BLACK);
        second.setForeground(Color.WHITE);
        second.addActionListener(this);
        this.add(second);

        third.setBounds(10,310,800,85);
        third.setFont(new Font("Arial" , Font.PLAIN , 40));
        third.setFocusable(false);
        third.setBackground(Color.BLACK);
        third.setForeground(Color.WHITE);
        third.addActionListener(this);
        this.add(third);

        fourth.setBounds(10,400,800,85);
        fourth.setFont(new Font("Arial" , Font.PLAIN , 40));
        fourth.setFocusable(false);
        fourth.setBackground(Color.BLACK);
        fourth.setForeground(Color.WHITE);
        fourth.addActionListener(this);
        this.add(fourth);

        fifth.setBounds(10,490,800,85);
        fifth.setFont(new Font("Arial" , Font.PLAIN , 40));
        fifth.setFocusable(false);
        fifth.setBackground(Color.BLACK);
        fifth.setForeground(Color.WHITE);
        fifth.addActionListener(this);
        this.add(fifth);

        sixth.setBounds(10,580,800,85);
        sixth.setFont(new Font("Arial" , Font.PLAIN , 40));
        sixth.setFocusable(false);
        sixth.setBackground(Color.BLACK);
        sixth.setForeground(Color.WHITE);
        sixth.addActionListener(this);
        this.add(sixth);

        seventh.setBounds(10,670,800,85);
        seventh.setFont(new Font("Arial" , Font.PLAIN , 40));
        seventh.setFocusable(false);
        seventh.setBackground(Color.BLACK);
        seventh.setForeground(Color.WHITE);
        seventh.addActionListener(this);
        this.add(seventh);

        mainLabel.setFont(new Font("Arial",Font.BOLD , 60));
        mainLabel.setForeground(Color.ORANGE);
        this.add(mainLabel);
        
        this.add(background);
        this.setVisible(true);
    }

    public void actionPerformed(ActionEvent e)
    {
        if(e.getSource() == first)
        {
            User.OpenAccount();
        }
        else if(e.getSource() == second)
        {
            try {
                User.ShowAccountDetails();
            } catch (IOException e1) {
                // TODO Auto-generated catch block
                e1.printStackTrace();
            } catch (InterruptedException e1) {
                // TODO Auto-generated catch block
                e1.printStackTrace();
            }
        }
        else if(e.getSource() == third)
        {
            User.DepositMoney();
        }
        else if(e.getSource() == fourth)
        {
            User.WithdrawMoney();
        }
        else if(e.getSource() == fifth)
        {
            User.TransferMoney();  
        }
        else if(e.getSource() == sixth)
        {
            User.CloseAccount();
        }
        else if(e.getSource() == seventh)
        {
            JOptionPane.showMessageDialog(null, "Thank you for using the Application", "Press OK to Exit", JOptionPane.INFORMATION_MESSAGE);
            System.exit(0);
        }
    }
}
