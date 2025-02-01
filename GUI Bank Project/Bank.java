import javax.swing.*;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class Bank implements ActionListener{
    private String Username;
    private String UserCNIC;
    private String UserPin;
    private double Balance;
    String FindName , FindCNIC , FindPin;
    double FindBalance;
    boolean CheckIfOpening = false , AccountFound = false , DepositMoneyConfirmed = false , WithdrawMoneyConfirmed = false , ClosingAccount = false , TransferringMoney = false;
    JButton OpenAccountButton , ShowAccount;
    JFrame frame;
    JPanel GetDetails , ShowDetails;
    JLabel Name , CNIC , PIN , SubmitDetails ,ShowName , ShowCNIC , ShowPIN , ShowBalance;
    JTextField getName , getCNIC , getPIN;
    public void GetAccountDetails()
    {
        frame = new JFrame("Account Details");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(null);
        frame.setSize(1920,1080);
        frame.setLocationRelativeTo(null);
        Name = new JLabel("                 Name: ");
        CNIC = new JLabel("                 CNIC: ");
        PIN = new JLabel("                  PIN: ");
        SubmitDetails = new JLabel("        Click here to submit details....");
        OpenAccountButton = new JButton("Submit");
        getName = new JTextField();
        getCNIC = new JTextField();
        getPIN = new JTextField();

        getName.setCaretColor(Color.green);
        getCNIC.setCaretColor(Color.green);
        getPIN.setCaretColor(Color.green);

        Name.setFont(new Font("Arial" , Font.PLAIN , 50));
        CNIC.setFont(new Font("Arial" , Font.PLAIN , 50));
        PIN.setFont(new Font("Arial" , Font.PLAIN , 50));
        SubmitDetails.setFont(new Font("Arial" , Font.PLAIN , 40));
        OpenAccountButton.setFont(new Font("Arial" , Font.PLAIN , 40));
        getName.setFont(new Font("Arial" , Font.PLAIN , 40));
        getCNIC.setFont(new Font("Arial" , Font.PLAIN , 40));
        getPIN.setFont(new Font("Arial" , Font.PLAIN , 40));
        frame.setBackground(Color.BLUE);

        Name.setBounds(0,0,700,200);
        CNIC.setBounds(0,200,700,200);
        PIN.setBounds(0,400,700,200);
        SubmitDetails.setBounds(0,600,700,200);
        getName.setBounds(700,0,900,200);
        getCNIC.setBounds(700,200,900,200);
        getPIN.setBounds(700,400,900,200);
        OpenAccountButton.setBounds(700,600,900,200);

        Name.setBackground(Color.BLACK);
        Name.setForeground(Color.green);
        Name.setOpaque(true);

        CNIC.setBackground(Color.BLACK);
        CNIC.setForeground(Color.green);
        CNIC.setOpaque(true);

        getName.setBackground(Color.BLACK);
        getName.setForeground(Color.green);
        getName.setOpaque(true);

        getCNIC.setBackground(Color.BLACK);
        getCNIC.setForeground(Color.green);
        getCNIC.setOpaque(true);

        PIN.setBackground(Color.BLACK);
        PIN.setForeground(Color.green);
        PIN.setOpaque(true);

        getPIN.setBackground(Color.BLACK);
        getPIN.setForeground(Color.green);
        getPIN.setOpaque(true);

        SubmitDetails.setBackground(Color.BLACK);
        SubmitDetails.setForeground(Color.green);
        SubmitDetails.setOpaque(true);

        OpenAccountButton.setBackground(Color.darkGray);
        OpenAccountButton.setForeground(Color.green);
        OpenAccountButton.setFocusable(false);
        OpenAccountButton.setOpaque(true);

        frame.add(Name);
        frame.add(getName);
        frame.add(CNIC);
        frame.add(getCNIC);
        frame.add(PIN);
        frame.add(getPIN);
        frame.add(SubmitDetails);
        frame.add(OpenAccountButton);
        frame.setVisible(true);
    }

    public void OpenAccount()
    {
        CheckIfOpening = true;
        GetAccountDetails();
        OpenAccountButton.addActionListener(this);
    }

    public void ShowAccountDetails() throws IOException, InterruptedException
    {
        GetAccountDetails();
        OpenAccountButton.addActionListener(this);
    }

    public void DepositMoney()
    {
        DepositMoneyConfirmed = true;
        GetAccountDetails();
        OpenAccountButton.addActionListener(this);
    }

    public void WithdrawMoney()
    {
        GetAccountDetails();
        WithdrawMoneyConfirmed = true;
        OpenAccountButton.addActionListener(this);
    }

    public void CloseAccount()
    {
        GetAccountDetails();
        ClosingAccount = true;
        OpenAccountButton.addActionListener(this);
    }

    public void TransferMoney()
    {
        GetAccountDetails();
        TransferringMoney = true;
        OpenAccountButton.addActionListener(this);
    }

    public void actionPerformed(ActionEvent e)
    {
        if(e.getSource() == OpenAccountButton && CheckIfOpening)
        {
            CheckIfOpening = false;
            FileWriter openFile;
            try {
                openFile = new FileWriter("Accounts.txt" , true);
                BufferedWriter writeFile = new BufferedWriter(openFile);
                Username = getName.getText();
                UserCNIC = getCNIC.getText();
                UserPin = getPIN.getText();
                Balance = 0;
                writeFile.write(Username + "\n");
                writeFile.write(UserCNIC + "\n");
                writeFile.write(UserPin + "\n");
                writeFile.write(Balance + "\n\n");
                writeFile.close();
            } catch (IOException e1) {
                // TODO Auto-generated catch block
                e1.printStackTrace();
            }
            frame.dispose();
        }
        else if (e.getSource() == OpenAccountButton && CheckIfOpening == false && DepositMoneyConfirmed == false && WithdrawMoneyConfirmed == false && ClosingAccount == false && TransferringMoney == false)
        {
            Username = getName.getText();
            UserCNIC = getCNIC.getText();
            UserPin = getPIN.getText();
            FileReader openFile;
            try {
                openFile = new FileReader("Accounts.txt");
                BufferedReader readFile = new BufferedReader(openFile);
                while((FindName = readFile.readLine()) != null)
                {
                    AccountFound = false;
                    if(FindName.length() == 0)
                    {
                        continue;
                    }
                    FindCNIC = readFile.readLine();
                    FindPin = readFile.readLine();
                    FindBalance = Double.parseDouble(readFile.readLine());
                    if(FindCNIC.equals(UserCNIC) && FindPin.equals(UserPin))
                    {
                        AccountFound = true;
                        Balance = FindBalance;
                        break;
                    }
                }
                readFile.close();
                frame.dispose();
            } catch (NumberFormatException e1) {
                // TODO Auto-generated catch block
                e1.printStackTrace();
            } catch (IOException e1) {
                // TODO Auto-generated catch block
                e1.printStackTrace();
            }
            if(AccountFound)
            {
                AccountDetails();
            }
            else
            {
                JOptionPane.showMessageDialog(null, "Account Does Not Exist", "Error", JOptionPane.ERROR_MESSAGE);
                frame.dispose();
            }
        }

        else if(e.getSource() == OpenAccountButton && DepositMoneyConfirmed)
        {
            DepositMoneyConfirmed = false;
            Username = getName.getText();
            UserCNIC = getCNIC.getText();
            UserPin = getPIN.getText();
            FileReader openFile;
            FileWriter opentempFile;
            try {
                openFile = new FileReader("Accounts.txt");
                BufferedReader readFile = new BufferedReader(openFile);
                opentempFile = new FileWriter("tempfile.txt" , true);
                BufferedWriter writeFile = new BufferedWriter(opentempFile);
                AccountFound = false;
                while((FindName = readFile.readLine()) != null)
                {
                    if(FindName.length() == 0)
                    {
                        continue;
                    }
                    FindCNIC = readFile.readLine();
                    FindPin = readFile.readLine();
                    FindBalance = Double.parseDouble(readFile.readLine());
                    if(FindCNIC.equals(UserCNIC) && FindPin.equals(UserPin))
                    {
                        AccountFound = true;
                        Balance = FindBalance;
                        Double Money = Double.parseDouble(JOptionPane.showInputDialog("Enter the money to be deposited: "));
                        Balance += Money;
                        writeFile.write(FindName + "\n");
                        writeFile.write(FindCNIC + "\n");
                        writeFile.write(FindPin + "\n");
                        writeFile.write(Balance + "\n\n");
                    }
                    else
                    {
                        writeFile.write(FindName + "\n");
                        writeFile.write(FindCNIC + "\n");
                        writeFile.write(FindPin + "\n");
                        writeFile.write(FindBalance + "\n\n");
                    }
                }
                writeFile.close();
                readFile.close();
                frame.dispose();
                File f1 = new File("Accounts.txt");
                File f2 = new File("tempfile.txt");
                f1.delete();
                f2.renameTo(f1);
            } catch (NumberFormatException e1) {
                // TODO Auto-generated catch block
                e1.printStackTrace();
            } catch (IOException e1) {
                // TODO Auto-generated catch block
                e1.printStackTrace();
            }
            if(AccountFound)
            {
                JOptionPane.showMessageDialog(null, "Amount Deposited Successfully!" , "Confirmation" , JOptionPane.INFORMATION_MESSAGE);
            }
            else if(AccountFound == false)
            {
                JOptionPane.showMessageDialog(null, "Account does not Exist", "Error", JOptionPane.ERROR_MESSAGE);
                frame.dispose();
            }
        }

        else if(e.getSource() == OpenAccountButton && WithdrawMoneyConfirmed)
        {
            WithdrawMoneyConfirmed = false;
            Username = getName.getText();
            UserCNIC = getCNIC.getText();
            UserPin = getPIN.getText();
            FileReader openFile;
            FileWriter opentempFile;
            try {
                openFile = new FileReader("Accounts.txt");
                BufferedReader readFile = new BufferedReader(openFile);
                opentempFile = new FileWriter("tempfile.txt" , true);
                BufferedWriter writeFile = new BufferedWriter(opentempFile);
                AccountFound = false;
                while((FindName = readFile.readLine()) != null)
                {
                    if(FindName.length() == 0)
                    {
                        continue;
                    }
                    FindCNIC = readFile.readLine();
                    FindPin = readFile.readLine();
                    FindBalance = Double.parseDouble(readFile.readLine());
                    if(FindCNIC.equals(UserCNIC) && FindPin.equals(UserPin))
                    {
                        AccountFound = true;
                        Balance = FindBalance;
                        Double Money = Double.parseDouble(JOptionPane.showInputDialog("Enter the money to be Withdrawn: "));
                        if(Money>Balance)
                        {
                            JOptionPane.showMessageDialog(null, "Not Enough Money To Withdraw", "Error", JOptionPane.ERROR_MESSAGE);
                            return;
                        }
                        Balance -= Money;
                        writeFile.write(FindName + "\n");
                        writeFile.write(FindCNIC + "\n");
                        writeFile.write(FindPin + "\n");
                        writeFile.write(Balance + "\n\n");
                    }
                    else
                    {
                        writeFile.write(FindName + "\n");
                        writeFile.write(FindCNIC + "\n");
                        writeFile.write(FindPin + "\n");
                        writeFile.write(FindBalance + "\n\n");
                    }
                }
                writeFile.close();
                readFile.close();
                frame.dispose();
                File f1 = new File("Accounts.txt");
                File f2 = new File("tempfile.txt");
                f1.delete();
                f2.renameTo(f1);
            } catch (NumberFormatException e1) {
                // TODO Auto-generated catch block
                e1.printStackTrace();
            } catch (IOException e1) {
                // TODO Auto-generated catch block
                e1.printStackTrace();
            }

            if(AccountFound)
            {
                JOptionPane.showMessageDialog(null, "Amount Withdrawn Successfully!" , "Confirmation" , JOptionPane.INFORMATION_MESSAGE);
            }
            else if(AccountFound == false)
            {
                JOptionPane.showMessageDialog(null, "Account does not Exist", "Error", JOptionPane.ERROR_MESSAGE);
                frame.dispose();
            }
        }

        else if(e.getSource() == OpenAccountButton && ClosingAccount)
        {
            ClosingAccount = false;
            Username = getName.getText();
            UserCNIC = getCNIC.getText();
            UserPin = getPIN.getText();
            FileReader openFile;
            FileWriter opentempFile;
            try {
                openFile = new FileReader("Accounts.txt");
                BufferedReader readFile = new BufferedReader(openFile);
                opentempFile = new FileWriter("tempfile.txt" , true);
                BufferedWriter writeFile = new BufferedWriter(opentempFile);
                AccountFound = false;
                while((FindName = readFile.readLine()) != null)
                {
                    if(FindName.length() == 0)
                    {
                        continue;
                    }
                    FindCNIC = readFile.readLine();
                    FindPin = readFile.readLine();
                    FindBalance = Double.parseDouble(readFile.readLine());
                    if(FindCNIC.equals(UserCNIC) && FindPin.equals(UserPin))
                    {
                        AccountFound = true;
                        Balance = FindBalance;
                        continue;
                    }
                    else
                    {
                        writeFile.write(FindName + "\n");
                        writeFile.write(FindCNIC + "\n");
                        writeFile.write(FindPin + "\n");
                        writeFile.write(FindBalance + "\n\n");
                    }
                }
                writeFile.close();
                readFile.close();
                frame.dispose();
                File f1 = new File("Accounts.txt");
                File f2 = new File("tempfile.txt");
                f1.delete();
                f2.renameTo(f1);
            } catch (NumberFormatException e1) {
                // TODO Auto-generated catch block
                e1.printStackTrace();
            } catch (IOException e1) {
                // TODO Auto-generated catch block
                e1.printStackTrace();
            }

            if(AccountFound)
            {
                JOptionPane.showMessageDialog(null, "Account Closed Successfully!" , "Confirmation" , JOptionPane.INFORMATION_MESSAGE);
            }
            else if(AccountFound == false)
            {
                JOptionPane.showMessageDialog(null, "Account does not Exist", "Error", JOptionPane.ERROR_MESSAGE);
                frame.dispose();
            }
        }

        else if(e.getSource() == OpenAccountButton && TransferringMoney)
        {
            TransferringMoney = false;
            Double UseMoney = 0.0;
            boolean RecieverAccountFound = false;
            String RecieverCNIC = " ";
            Username = getName.getText();
            UserCNIC = getCNIC.getText();
            UserPin = getPIN.getText();
            FileReader openFile;
            FileWriter opentempFile;
            try {
                openFile = new FileReader("Accounts.txt");
                BufferedReader readFile = new BufferedReader(openFile);
                opentempFile = new FileWriter("tempfile.txt" , true);
                BufferedWriter writeFile = new BufferedWriter(opentempFile);
                AccountFound = false;
                while((FindName = readFile.readLine()) != null)
                {
                    if(FindName.length() == 0)
                    {
                        continue;
                    }
                    FindCNIC = readFile.readLine();
                    FindPin = readFile.readLine();
                    FindBalance = Double.parseDouble(readFile.readLine());
                    if(FindCNIC.equals(UserCNIC) && FindPin.equals(UserPin))
                    {
                        AccountFound = true;
                        Balance = FindBalance;
                        RecieverCNIC = JOptionPane.showInputDialog("Enter Reciever's CNIC");
                        if(UserCNIC.equals(RecieverCNIC))
                        {
                            JOptionPane.showMessageDialog(null, "You Cannot Transfer Cash to Yourself" , "Error" , JOptionPane.ERROR_MESSAGE);
                            return;
                        }
                        UseMoney = Double.parseDouble(JOptionPane.showInputDialog("Enter the money to be Transferred: "));
                        if(UseMoney>Balance)
                        {
                            JOptionPane.showMessageDialog(null, "Not Enough Money To Withdraw", "Error", JOptionPane.ERROR_MESSAGE);
                            return;
                        }
                        Balance -= UseMoney;
                        writeFile.write(FindName + "\n");
                        writeFile.write(FindCNIC + "\n");
                        writeFile.write(FindPin + "\n");
                        writeFile.write(Balance + "\n\n");
                    }
                    else
                    {
                        writeFile.write(FindName + "\n");
                        writeFile.write(FindCNIC + "\n");
                        writeFile.write(FindPin + "\n");
                        writeFile.write(FindBalance + "\n\n");
                    }
                }
                writeFile.close();
                readFile.close();
                File f1 = new File("Accounts.txt");
                File f2 = new File("tempfile.txt");
                f1.delete();
                f2.renameTo(f1);
                openFile = new FileReader("Accounts.txt");
                readFile = new BufferedReader(openFile);
                opentempFile = new FileWriter("tempfile.txt" , true);
                writeFile = new BufferedWriter(opentempFile);
                RecieverAccountFound = false;
                while((FindName = readFile.readLine()) != null)
                {
                    if(FindName.length() == 0)
                    {
                        continue;
                    }
                    FindCNIC = readFile.readLine();
                    FindPin = readFile.readLine();
                    FindBalance = Double.parseDouble(readFile.readLine());
                    if(FindCNIC.equals(RecieverCNIC))
                    {
                        RecieverAccountFound = true;
                        Balance = FindBalance;
                        Balance += UseMoney;
                        writeFile.write(FindName + "\n");
                        writeFile.write(FindCNIC + "\n");
                        writeFile.write(FindPin + "\n");
                        writeFile.write(Balance + "\n\n");
                    }
                    else
                    {
                        writeFile.write(FindName + "\n");
                        writeFile.write(FindCNIC + "\n");
                        writeFile.write(FindPin + "\n");
                        writeFile.write(FindBalance + "\n\n");
                    }
                }
                writeFile.close();
                readFile.close();
                frame.dispose();
                File f3 = new File("Accounts.txt");
                File f4 = new File("tempfile.txt");
                f3.delete();
                f4.renameTo(f1);
            } catch (NumberFormatException e1) {
                // TODO Auto-generated catch block
                e1.printStackTrace();
            } catch (IOException e1) {
                // TODO Auto-generated catch block
                e1.printStackTrace();
            }

            if(AccountFound)
            {
                if(RecieverAccountFound)
                {
                    JOptionPane.showMessageDialog(null, "Amount Transferred Successfully" , "Confirmation " , JOptionPane.INFORMATION_MESSAGE);
                }
                else
                {
                    JOptionPane.showMessageDialog(null, "Reciever Account does not Exist", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
            else{
                JOptionPane.showMessageDialog(null, "Your Account does not Exist", "Error", JOptionPane.ERROR_MESSAGE);
                frame.dispose();
            }
        }
    }


    public void AccountDetails()
    {
        JFrame ShowFrame = new JFrame("Account Details");
        JLabel TheBank = new JLabel("The Bank");
        JLabel AccountLabel = new JLabel("Account Details");
        ShowFrame.setSize(1920,1080);
        ImageIcon icon = new ImageIcon("ImageIcon.png");
        ShowFrame.setLayout(null);
        JLabel logoLabel = new JLabel(icon);
        JPanel logoPanel = new JPanel();
        JPanel AccountPanel = new JPanel();
        Name = new JLabel("Account Name: ");
        JLabel ShowName = new JLabel(Username);
        CNIC = new JLabel("CNIC: ");
        JLabel ShowCNIC = new JLabel(UserCNIC);
        PIN = new JLabel("Account PIN:");
        JLabel ShowPIN = new JLabel(UserPin);
        JLabel BALANCE = new JLabel("Account Balance: ");
        JLabel ShowBalance = new JLabel(String.valueOf(Balance));
        JLabel Window = new JLabel("Window Closing After A Few Seconds.....");

        Window.setFont(new Font("Arial" , Font.PLAIN , 40));
        Window.setForeground(Color.WHITE);
        Window.setBounds(80,700,1000,50);

        Name.setFont(new Font("Arial" , Font.PLAIN , 40));
        Name.setForeground(Color.WHITE);
        Name.setBounds(80,100,400,50);

        ShowName.setFont(new Font("Arial" , Font.PLAIN , 40));
        ShowName.setForeground(Color.WHITE);
        ShowName.setBounds(80,150,400,50);

        CNIC.setFont(new Font("Arial" , Font.PLAIN , 40));
        CNIC.setForeground(Color.WHITE);
        CNIC.setBounds(80,250,400,50);

        ShowCNIC.setFont(new Font("Arial" , Font.PLAIN , 40));
        ShowCNIC.setForeground(Color.WHITE);
        ShowCNIC.setBounds(80,300,400,50);

        PIN.setFont(new Font("Arial" , Font.PLAIN , 40));
        PIN.setForeground(Color.WHITE);
        PIN.setBounds(80,400,400,50);

        ShowPIN.setFont(new Font("Arial" , Font.PLAIN , 40));
        ShowPIN.setForeground(Color.WHITE);
        ShowPIN.setBounds(80,450,400,50);

        BALANCE.setFont(new Font("Arial" , Font.PLAIN , 40));
        BALANCE.setForeground(Color.WHITE);
        BALANCE.setBounds(80,550,400,50);

        ShowBalance.setFont(new Font("Arial" , Font.PLAIN , 40));
        ShowBalance.setForeground(Color.WHITE);
        ShowBalance.setBounds(80,600,400,50);

        AccountPanel.setLayout(null);
        AccountPanel.setBounds(400,0,1500,1080);
        logoPanel.setBackground(new Color(0,128,128));
        logoPanel.setPreferredSize(new Dimension(400,1080));
        logoPanel.setBounds(0,0,400,1080);
        TheBank.setFont(new Font("Arial" , Font.PLAIN , 40));
        TheBank.setForeground(Color.WHITE);
        AccountLabel.setFont(new Font("Arial" , Font.PLAIN , 40));
        AccountLabel.setForeground(Color.WHITE);
        AccountLabel.setBounds(450,20,300,50);
        AccountPanel.setBackground(Color.BLACK);

        logoPanel.add(logoLabel);
        logoPanel.add(TheBank);
        AccountPanel.add(AccountLabel);
        AccountPanel.add(Name);
        AccountPanel.add(ShowName);
        AccountPanel.add(CNIC);
        AccountPanel.add(ShowCNIC);
        AccountPanel.add(PIN);
        AccountPanel.add(ShowPIN);
        AccountPanel.add(BALANCE);
        AccountPanel.add(ShowBalance);
        AccountPanel.add(Window);
        ShowFrame.add(AccountPanel);
        ShowFrame.add(logoPanel);
        ShowFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        ShowFrame.setVisible(true);

        javax.swing.Timer timer = new javax.swing.Timer(7000, new ActionListener() {
        public void actionPerformed(ActionEvent e) {
            ShowFrame.dispose();
        }
        });
        timer.setRepeats(false); 
        timer.start();
    }
}