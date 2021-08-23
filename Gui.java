import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

/*
This class is responsible for the graphical user interface of the game.
 */
public class Gui {

    public Gui(){
        LogInOrSignIn();
    }

    /*
    This method create a frame for the user in which he haw two options.
    If he is a new player then has to sign in but if already haw an account has to Log in
     */
    public void LogInOrSignIn(){

        JFrame frame=new JFrame("Log in or Sign in");
        frame.setSize(400, 200);
        frame.setLayout(null);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setResizable(false);

        JButton button1=new JButton("Log in");
        button1.setFocusable(false);

        button1.addActionListener(e -> {
            frame.setVisible(false);
            frame.dispose();
            createOrVerifyAccount(0);
        });

        JButton button2=new JButton("Sign in");
        button2.setFocusable(false);
        button2.addActionListener(e -> {
            frame.setVisible(false);
            frame.dispose();
            createOrVerifyAccount(1);
        });

        JButton button3=new JButton("Exit");
        button3.setFocusable(false);
        button3.addActionListener(e -> exit());

        button1.setBounds(70,40,110,30);
        button2.setBounds(210,40,100,30);
        button3.setBounds(307,133,80,30);

        frame.add(button1);
        frame.add(button2);
        frame.add(button3);

        frame.setVisible(true);
    }

    /*
    This method creates a frame in which the user can terminate the program by clicking in yes or
    change his mind and click no.
     */
    public void exit(){

        JFrame frame=new JFrame("Exit");
        frame.setSize(250, 100);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setResizable(false);

        JLabel jlabel=new JLabel("Are you sure that you want to exit ?");

        JButton button1=new JButton("Yes");
        button1.addActionListener(e -> {
            frame.setVisible(false);
            frame.dispose();
            System.exit(0);
        });

        JButton button2=new JButton("No");
        button2.addActionListener(e -> {
            frame.setVisible(false);
            frame.dispose();
        });

        FlowLayout aLayout = new FlowLayout();
        frame.setLayout(aLayout);

        frame.add(jlabel);
        frame.add(button1);
        frame.add(button2);

        frame.setVisible(true);
    }

    /*
    This method create a frame for the user in order that create a new account(parameter==1)
     or verify an account(parameter==0) that already exists.
     */
    public void createOrVerifyAccount(int k){
        JFrame frame1=new JFrame("Verify Account");
        frame1.setSize(300, 180);
        frame1.setLocationRelativeTo(null);
        frame1.setLayout(null);
        frame1.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame1.setResizable(false);

        JLabel label1=new JLabel("Give your name:");
        JLabel label2=new JLabel();
        JTextField textField=new JTextField(10);

        textField.addActionListener(e -> {
            String name;
            name=textField.getText();
            if(k==0){
                if(FileHandler.foundName(name,"Users.txt")==1){
                    frame1.dispose();
                    menu(name);
                }
                else{
                    label2.setText("There is not this name.");
                    label2.setForeground(Color.RED);
                }
            }
            else{
                if(FileHandler.foundName(name,"Users.txt")==0 && name.length()>=4 && name.length()<=8 && name.charAt(0)!='0'&& name.charAt(0)!='1' && name.charAt(0)!='2' && name.charAt(0)!='3' && name.charAt(0)!='4' && name.charAt(0)!='5' && name.charAt(0)!='6' && name.charAt(0)!='7' && name.charAt(0)!='8' && name.charAt(0)!='9'){
                    FileHandler.writeToFile(name,"Users.txt");
                    frame1.dispose();
                    menu(name);
                }
                else{
                    if(FileHandler.foundName(name,"Users.txt")==1) {
                        label2.setText("Existing name.");
                        label2.setForeground(Color.RED);
                    }
                    else if(name.length()<4 || name.length()>10) {
                        label2.setText("4-8 letters limits");
                        label2.setForeground(Color.RED);
                    }
                    else{
                        label2.setText("The first char must not be number.");
                        label2.setForeground(Color.RED);
                    }
                }
            }
        });

        JButton button1=new JButton("Exit");
        button1.addActionListener(e -> exit());
        JButton button2=new JButton("Back");
        button2.addActionListener(e -> {
            frame1.dispose();
            LogInOrSignIn();
        });

        label1.setBounds(30,20,110,30);
        label2.setBounds(60,60,200,20);
        textField.setBounds(125,25,110,23);
        button1.setBounds(217,115,70,30);
        button2.setBounds(0,115,70,30);

        frame1.add(label1);
        frame1.add(label2);
        frame1.add(textField);
        frame1.add(button1);
        frame1.add(button2);

        frame1.setVisible(true);
    }

    /*
    This method creates a frame for the basic menu. The user can play a new game, see the statistics
    of the game. Also the user can log out or terminate the program by clicking the correspond button.
     */
    public void menu(String name) {

        int wins= FileHandler.findWins(name,"Users.txt");
        int draws= FileHandler.findDraws(name,"Users.txt");
        int loses= FileHandler.findLoses(name,"Users.txt");

        JFrame frame1=new JFrame("Menu");
        frame1.setSize(300, 200);
        frame1.setLayout(null);
        frame1.setLocationRelativeTo(null);
        frame1.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame1.setResizable(false);

        JLabel label1=new JLabel("Welcome to the game "+name+".");
        JButton button1=new JButton("New Game");
        JButton button2=new JButton("Exit");
        JButton button3=new JButton("Statistics");
        JButton button4=new JButton("Log out");

        button1.addActionListener(e -> {
            frame1.dispose();
            login2ndPlayer(name,wins,draws,loses);
        });

        button2.addActionListener(e -> exit());

        button3.addActionListener(e -> {
            frame1.dispose();
            statistics(name);
        });

        button4.addActionListener(e -> {
            frame1.dispose();
            LogInOrSignIn();
        });

        label1.setBounds(45,20,220,30);
        button1.setBounds(40,60,100,25);
        button2.setBounds(216,133,70,30);
        button3.setBounds(150,60,100,25);
        button4.setBounds(0,133,90,30);

        frame1.add(label1);
        frame1.add(button1);
        frame1.add(button2);
        frame1.add(button3);
        frame1.add(button4);

        frame1.setVisible(true);
    }

    /*
    This method creates a frame for the statistics. The user  can return to menu by clicking the
    button <<Back>>.
     */
    public void statistics(String name){
        ArrayList<String> names = FileHandler.getNamesOfFile();
        ArrayList<Integer> wins = FileHandler.getWinsOfFile();
        ArrayList<Integer> draws = FileHandler.getDrawsOfFile();
        ArrayList<Integer> loses = FileHandler.getLosesOfFile();


        JFrame frame1=new JFrame("Statistics");
        frame1.setSize(500, 700);
        frame1.setLayout(null);
        frame1.setLocationRelativeTo(null);
        frame1.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame1.setResizable(false);


        JButton button1=new JButton("Back");
        button1.setBounds(405,630,80,30);
        frame1.add(button1);
        button1.addActionListener(e -> {
            frame1.dispose();
            menu(name);
        });


        JLabel labelName=new JLabel("Name");
        JLabel labelWins=new JLabel("Wins");
        JLabel labelDraws=new JLabel("Draws");
        JLabel labelLoses=new JLabel("Loses");

        JLabel[] labelname =new JLabel[names.size()];
        JLabel[] labelwins =new JLabel[names.size()];
        JLabel[] labeldraws =new JLabel[names.size()];
        JLabel[] labelloses =new JLabel[names.size()];

        for(int i=0;i<names.size();i++){
            labelname[i]=new JLabel(names.get(i));
            labelwins[i]=new JLabel(String.valueOf(wins.get(i)));
            labeldraws[i]=new JLabel(String.valueOf(draws.get(i)));
            labelloses[i]=new JLabel(String.valueOf(loses.get(i)));
        }


        labelName.setBounds(10,10,80,15);
        labelWins.setBounds(130,10,80,15);
        labelDraws.setBounds(250,10,80,15);
        labelLoses.setBounds(370,10,80,15);


        for(int i=0;i<names.size();i++){
            labelname[i].setBounds(10,10+(i+1)*20,80,15);
            labelwins[i].setBounds(130,10+(i+1)*20,80,15);
            labeldraws[i].setBounds(250,10+(i+1)*20,80,15);
            labelloses[i].setBounds(370,10+(i+1)*20,80,15);
        }


        frame1.add(labelName);
        frame1.add(labelWins);
        frame1.add(labelDraws);
        frame1.add(labelLoses);

        for(int i=0;i<names.size();i++){
            frame1.add(labelname[i]);
            frame1.add(labelwins[i]);
            frame1.add(labeldraws[i]);
            frame1.add(labelloses[i]);
        }

        frame1.setVisible(true);
    }


    /*
    This method creates a frame for log in the second player who ia going to play the game.This player
    has to already have an account and log in here to play the game.
     */
    public void login2ndPlayer(String name1,int wins1, int draws1,int loses1){
        JFrame frame1=new JFrame("Login 2nd player");
        frame1.setSize(300, 180);
        frame1.setLocationRelativeTo(null);
        frame1.setLayout(null);
        frame1.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame1.setResizable(false);

        JLabel label1=new JLabel("Give your name:");
        JLabel label2=new JLabel();
        JTextField textField=new JTextField(10);

        textField.addActionListener(e -> {
            String name;
            name=textField.getText();

            if(FileHandler.foundName(name,"Users.txt")==1 && !name.equals(name1)){
                frame1.dispose();
                tickTackToe(name1,wins1,draws1,loses1,name, FileHandler.findWins(name,"Users.txt"), FileHandler.findDraws(name,"Users.txt"), FileHandler.findLoses(name,"Users.txt"));
            }
            else if(FileHandler.foundName(name,"Users.txt")==0){
                label2.setText(" There is not this name");
                label2.setForeground(Color.RED);
            }
            else{
                label2.setText("You can not play against yourself");
                label2.setForeground(Color.RED);
            }
        });

        JButton button1=new JButton("Exit");
        button1.addActionListener(e -> exit());

        JButton button2=new JButton("Back");
        button2.addActionListener(e -> {
            frame1.dispose();
            menu(name1);
        });

        label1.setBounds(30,20,110,30);
        label2.setBounds(60,60,200,20);
        textField.setBounds(125,25,110,23);
        button1.setBounds(217,115,70,30);
        button2.setBounds(0,115,70,30);

        frame1.add(label1);
        frame1.add(label2);
        frame1.add(textField);
        frame1.add(button1);
        frame1.add(button2);

        frame1.setVisible(true);
    }

    /*
    This method creates a frame for the two players in order to play Tic-Tac-Toe.
     */
    public void tickTackToe(String name1,int wins1,int draws1,int loses1,String name2,int wins2,int draws2,int loses2){
            Logic logic=new Logic();

        JFrame frame=new JFrame("Tic-Tac-Toe");
        frame.setSize(500, 500);
        frame.setLayout(null);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setResizable(false);

        JLabel announcewinner=new JLabel();
        announcewinner.setBounds(200,320,140,20);
        announcewinner.setVisible(false);

        JLabel user1=new JLabel(name1+" has: X");
        JLabel user2=new JLabel(name2+" has: O");

        user1.setBounds(10,30,130,15);
        user2.setBounds(350,30,130,15);

        JButton button1=new JButton("Exit");
        button1.addActionListener(e -> exit());
        button1.setBounds(417,425,70,40);


        JButton button2=new JButton("Back");
        button2.addActionListener(e -> {
            frame.dispose();
            menu(name1);
        });
        button2.setBounds(1,422,70,40);


        JButton[] ticktacktoee=new JButton[9];
        for(int i=0;i<9;i++){
            ticktacktoee[i]=new JButton(" ");
            ticktacktoee[i].setFocusable(false);
        }
        for(int i=0;i<3;i++){
            ticktacktoee[i].setBounds(110+i*80,100,80,60);
        }

        for(int i=3;i<6;i++){
            ticktacktoee[i].setBounds(110+(i-3)*80,160,80,60);
        }
        for(int i=6;i<9;i++){
            ticktacktoee[i].setBounds(110+(i-6)*80,220,80,60);
        }


        for(int i=0;i<9;i++){
            int I=i;
            ticktacktoee[i].addActionListener(e -> {
              if(logic.isFree(I/3,I%3)){
                  String value=logic.fillTable();
                  ticktacktoee[I].setText(value);
                  logic.filltable(I/3,I%3,value);
                  int winner=logic.checkHit(value);
                  if(winner==1){
                      announcewinner.setText(name1+" wins");
                      for(int j=0;j<9;j++){
                          ticktacktoee[j].setEnabled(false);
                      }
                      FileHandler.add1Win("Users.txt",name1,wins1,draws1,loses1);
                      FileHandler.add1Lose("Users.txt",name2,wins2,draws2,loses2);
                  }
                  else if(winner==2){
                      announcewinner.setText(name2+" wins");
                      for(int j=0;j<9;j++){
                          ticktacktoee[j].setEnabled(false);
                      }
                      FileHandler.add1Win("Users.txt",name2,wins2,draws2,loses2);
                      FileHandler.add1Lose("Users.txt",name1,wins1,draws1,loses1);
                  }
                  else if(winner==-1){
                      announcewinner.setText("It is a draw");
                      for(int j=0;j<9;j++){
                          ticktacktoee[j].setEnabled(false);
                      }
                      FileHandler.add1Draw("Users.txt",name2,wins2,draws2,loses2);
                      FileHandler.add1Draw("Users.txt",name1,wins1,draws1,loses1);
                  }
                  announcewinner.setVisible(true);

              }
            });
        }


        frame.add(announcewinner);
        for(int i=0;i<9;i++){
            frame.add(ticktacktoee[i]);
        }
        frame.add(user1);
        frame.add(user2);
        frame.add(button1);
        frame.add(button2);
        frame.setVisible(true);
    }

}
