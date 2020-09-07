//Sonali Merchia
//Period 6
//5-18-2018
//Game.java
import java.awt.*; import javax.swing.*;  import java.awt.event.*;
import javax.swing.Timer; import java.util.Scanner; import java.io.*;
import javax.swing.event.*;
public class Game
{
/* This class holds all other classes and methods. */
    Image[] santa, krampus, elf;
    JFrame frame;
    LevelSelect map;
    SaveProgress saver;
    TopPlayers leaders;
    JPanel masterPanel, game;
    Home home;
    JButton[] goHome= new JButton[4];
    JButton[] play = new JButton[4];
    JButton save;
    Color lightBlue = new Color(220, 255, 255);
    Color dark = new Color(23, 172, 83);
    Color light = new Color(253, 255, 178);
    CardLayout whichScreen;
    boolean stand = false;
    public static void main(String[]args)
    {
/*Runs method that starts game*/
        Game object = new Game();
        object.run();
    }
    public void run()
    {
/*This method creates the frame, creates a master panel that has a card layout to hold all the levels and other screens. It also initializes the pictures of the main characters that I will use throughout the code. It then runs the method that sets the frame to the opening screen. */
        frame = new JFrame("Game Trial");
        frame.setSize(800, 600);
        frame.setLocation(0,0);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        String[]which = {"Crouching", "Standing", "Left", "Right", "Squat"};
        santa = new Image[5];
        krampus = new Image[5];
        elf = new Image[5];

        for(int i=0; i<5; i++)
        {
          santa[i] = new ImageIcon(which[i]+"assets/Santa.png").getImage();
          krampus[i] = new ImageIcon(which[i]+"assets/Krampus.png").getImage();
          elf[i] = new ImageIcon(which[i]+"assets/Elf.png").getImage();
        }

        whichScreen = new CardLayout();
        masterPanel = new JPanel(whichScreen);

        opening();
        frame.setVisible(true);
    }
    public void opening()
    {
/* This creates all the back and play buttons on the preliminary screens. It creates the Instruction, Credits, and Leaderboard panels and adds it to the master panel that has the card layout. It then sets the content of the frame to the master panel. */
        masterPanel.setLayout(whichScreen);

        ButtonHandler hand = new ButtonHandler();
        for(int x=0; x<4; x++)
        {
            if(x<3)
            {goHome[x] = new JButton("BACK");
            goHome[x].setPreferredSize(new Dimension(300, 100));
            goHome[x].setBorderPainted(false);
            goHome[x].addActionListener(hand);
            goHome[x].setForeground(dark);
            goHome[x].setBackground(light);
            goHome[x].setFont(new Font("Arial", Font.BOLD, 40));}

            play[x] = new JButton("PLAY");
            play[x].setFont(new Font("Arial", Font.BOLD ,40));
            play[x].setBackground(light);
            play[x].setForeground(Color.RED);
            play[x].setPreferredSize(new Dimension(200, 100));
            play[x].setBorderPainted(false);
            play[x].addActionListener(hand);

        }

        home = new Home();

        home.setLayout(new FlowLayout(1, 250, 10));

        Instructions instruct = new Instructions();
        leaders = new TopPlayers();
        saver = new SaveProgress();

        masterPanel.add(home, "Home Screen");
        masterPanel.add(instruct, "Instructions");
        masterPanel.add(leaders, "Leaderboard");
        masterPanel.add(saver, "Save Score");

        whichScreen.show(masterPanel, "Home Screen");
        frame.setContentPane(masterPanel);

    }
    public void gameMap()
    {
/* This method runs the slides that display the premise of the game. */
        home.jump.stop();
        Introduction introToGame = new Introduction("Intro");
        masterPanel.add(introToGame, "Introduction");
        whichScreen.show(masterPanel, "Introduction");
    }
class Home extends JPanel implements ActionListener
{
/* This class makes the opening panel that is first visible upon opening the game. It contains buttons to travel to the other preliminary screens and the game map. */
    Timer jump = new Timer(500, this);
    JButton instruct, scores;
    Image back = new ImageIcon("assets/HomeBack.png").getImage();
    boolean stand;

    public Home()
    {
/* This starts the timer that makes the characters on the home screen squat and stand up. It also runs the method that creates the buttons. */
        jump.start();
        buttons();
    }
    public void buttons()
    {
/* This method creates the buttons on the home screen and sets their aesthetics. It also creates the title on the top of the screen. */
        JLabel title = new JLabel("Attack on Santa's Workshop");
        title.setFont(new Font("Arial", Font.BOLD, 50));
        title.setForeground(dark);

        instruct = new JButton("INSTRUCTIONS");
        scores = new JButton("LEADERBOARD");
        goHome[3] = new JButton("BACK");
        save = new JButton("SAVE");

        JButton alias = new JButton();
        ButtonHandler hand = new ButtonHandler();
        for(int x =0; x<4 ;x++)
        {
            if(x==0) alias = instruct;
            else if(x==1) alias = goHome[3];
            else if(x==2) alias = save;
            else alias = scores;
            alias.setPreferredSize(new Dimension(150, 25));
            alias.setForeground(dark);
            alias.setBackground(light);
            alias.setBorderPainted(false);
            alias.addActionListener(hand);
        }

        JPanel fill = new JPanel(); fill.setOpaque(false);
        fill.setPreferredSize(new Dimension(200, 100));
        JPanel fill2 = new JPanel(); fill2.setOpaque(false);
        fill2.setPreferredSize(new Dimension(200, 20));

        add(fill2);
        add(title);
        add(fill);
        add(play[0]);
        add(instruct);
        add(scores);
    }
    public void paintComponent(Graphics g)
    {
/* This method paints the cage, santa, krampus, and elf on the home screen of the game. */
        super.paintComponent(g);
        g.drawImage(back, 0, 0, null);

        if(stand)g.drawImage(santa[1], 5, 200, 300, 300, null);
        else g.drawImage(santa[0], 55, 200, 200, 300, null);

        g.setColor(Color.GRAY);
        for(int i=350; i>=0; i-=40)
        g.fillRect(i, 175, 10, 350);
        g.setColor(Color.BLACK);
        g.fillRect(0, 170, 370, 20);

        if(stand)
        {
            g.drawImage(elf[1], 200, 400, 150, 150, null);
            g.drawImage(krampus[0], 525, 200, 250, 300, null);
        }
        else
        {
            g.drawImage(elf[0], 200, 400, 150, 150, null);
            g.drawImage(krampus[1], 500, 200, 300, 300, null);
        }
    }
    public void actionPerformed(ActionEvent evt)
    {
/* This method inverts a boolean every time the timer is called. The boolean changes whether the characters are sitting or squatting. It then repaints the home panel. */
         stand = !stand;
         repaint();
    }
}
class Instructions extends JPanel implements ActionListener
{
/* This class will paint instructions for the next level as soon as I make instruction panels */
    Image back = new ImageIcon("assets/HomeBack.png").getImage();
    JButton[]pick = new JButton[6];
    Image[]pictures = new Image[6];
    JPanel buttonHolder = new JPanel(new GridLayout(1, 6));
    int picked=0;

    public Instructions()
    {
/* This constructor adds and sets the aesthetics for the buttons and initializes the images that hold instructions */
         add(goHome[0]);
         add(play[1]);
         buttonHolder.setPreferredSize(new Dimension(750, 25));

         for(int index=0; index<6; index++)
         {
             pick[index] = new JButton("Level "+(index));
             pick[index].addActionListener(this);
             pick[index].setForeground(dark);
             pick[index].setBackground(light);
             pick[index].setBorderPainted(false);

             pictures[index]=new ImageIcon("assets/Instruct"+(index)+".png").getImage();
             buttonHolder.add(pick[index]);
         }

         pictures[5]=new ImageIcon("assets/Level56.png").getImage();
         pick[0].setText("Overall");
         add(buttonHolder);
    }
    public void paintComponent(Graphics g)
    {
/* This method paints the picture that the user picked*/
         super.paintComponent(g);
         g.drawImage(back, 0, 0, 800, 600, null);
         g.drawImage(pictures[picked], 15, 160, 750, 380, null);
    }
    public void actionPerformed(ActionEvent e)
    {
/* This method figures out what level the user wants to se*/
         if(e.getActionCommand().equals("Overall"))
         picked =0;
         else
         {
            char interm = e.getActionCommand().charAt(e.getActionCommand().length()-1);
            picked = (int)(interm)-48;
         }

         repaint();
     }
}
class TopPlayers extends JPanel
{
/* This class makes and displays a scoreboard based on a text file. */
    File scoreboard;
    Scanner read;
    Image back = new ImageIcon("assets/HomeBack.png").getImage();
    String[]names= new String[5];
    int[]scores=new int[5];
    JLabel[] people = new JLabel[10];
    JPanel holder = new JPanel(new GridLayout(5, 1));
    public TopPlayers()
   {
/* This constructor sets the layout of the leaderboard and adds buttons to go back to the home screen or the game map. It also runs the method grid. */
        setLayout(new FlowLayout(FlowLayout.CENTER, 10, 30));
        add(goHome[2]);
        add(play[3]);

        grid();
   }
    public void grid()
    {
/* This method uses io to read a text file called scores.txt that holds the names of the top scorers of the game. It then makes a panel with a grid layout of JLabels to display the names and adds it to the main panel. */
        scoreboard = new File("assets/scores.txt");
        tryCatch();
        holder.setPreferredSize(new Dimension(600, 300));
        Font write = new Font("Arial", Font.PLAIN, 20);;
        Color lightGreen = new Color(195, 253, 191);
        ImageIcon present = new ImageIcon("assets/SmallGift.png");

        for(int numRead =0; numRead<10 ; numRead++)
        {
              if(numRead%2==0)
              {
                  names[numRead/2]= read.nextLine();
                  people[numRead]= new JLabel("     "+(numRead/2+1)+". "+names[numRead/2]);
              }
              else
              {
                  scores[numRead/2]=Integer.parseInt(read.nextLine());
                  people[numRead]=new JLabel(""+scores[numRead/2]+"    ", present, JLabel.CENTER);
              }

              people[numRead].setFont(write);

              if(numRead/2%2==0)
              {
                  people[numRead].setForeground(dark);
                  people[numRead].setBackground(light);
              }
              else
              {
                  people[numRead].setForeground(Color.RED);
                  people[numRead].setBackground(lightGreen);
              }

              people[numRead].setOpaque(true);
              holder.add(people[numRead]);
        }

        add(holder);
    }
    public void paintComponent(Graphics g)
    {
/* This method draws a premade background in the background of the panel. */
        super.paintComponent(g);
        g.drawImage(back, 0, 0, 800, 600, null);
    }
    public void tryCatch()
    {
/* This method resets the scanner that reads the io file. */
        read=null;
        try
        {
            read = new Scanner(scoreboard);
        }
        catch(FileNotFoundException e)
        {
            System.out.print("\n\n\nscores.txt not found\n\n\n");
            System.exit(1);
        }
    }
    public void save()
    {
/* This method decides whether the user made the scoreboard and what position they'd get*/
        tryCatch();
        int index = -1;
        int score = map.numGifts;
        boolean run=true;

        while(index<5 && run)
        {
            index++;
            if(index<5)
            if(scores[index]<=score) run=false;
        }
        if(!run)
        {
            for(int indexBack=4; indexBack>index; indexBack--)
            {
                names[indexBack]=names[indexBack-1];
                scores[indexBack]=scores[indexBack-1];
            }
            names[index]= saver.name;
            scores[index]= score;
        }

        PrintWriter write=null;

        try
        {
            write = new PrintWriter(scoreboard);
        }
        catch(FileNotFoundException e)
        {
            System.out.print("\n\n\nscores.txt not found\n\n\n");
            System.exit(1);
        }
        for(index=0; index<4; index++)
        {
            write.println(names[index]);
            write.println(scores[index]);
        }

        write.println(names[index]);
        write.print(scores[index]);
        write.close();

        holder.removeAll();
        remove(holder);

        grid();

        whichScreen.show(masterPanel, "Leaderboard");
    }
}
class Introduction extends JPanel implements MouseListener
{
/* This class shows the slides to set the premise of the game. */
    int pic;
    Image[]intro = new Image[6];
    String which;

    public Introduction(String get)
    {
/*This constructor adds a mouse listener to the panel and initializes the images that are shown when the user clicks the "play" button. */
     for(int i=0; i<6; i++)
     intro[i]=new ImageIcon("assets/"+get+(i+1)+".png").getImage();
     which=get;
     addMouseListener(this);
    }
    public void paintComponent(Graphics g)
    {
/* This method paints the correct introductory slide and afterwards sets the frame to the game map. */
        super.paintComponent(g);
        if(pic<6)
            g.drawImage(intro[pic], 0, 0, 800, 570, null);
        else if(which.equals("Intro"))
        {
            map = new LevelSelect();
            masterPanel.add(map, "Game Map");
            whichScreen.show(masterPanel, "Game Map");
        }
        else if(which.equals("Level5"))
        {
            Level5 level5 = new Level5();
            masterPanel.add(level5, "Level5");
            whichScreen.show(masterPanel, "Level5");
        }
    }
    public void mouseClicked(MouseEvent evt)
    {
/* When the user click, this method changes the shown panel and repaints */
        pic++;
        repaint();
    }
    public void mouseEntered(MouseEvent evt){}
    public void mouseExited(MouseEvent evt){}
    public void mousePressed(MouseEvent evt){}
    public void mouseReleased(MouseEvent evt){}
}
class LevelSelect extends JPanel implements KeyListener, ActionListener, MouseListener, FocusListener
{
/* This class makes the Game Map */
    boolean left, right, focus;
    int xAdd, time, shift, speed, yAdd, jump;
    boolean[] completed = new boolean[6];
    int[] trees = new int[8]; int[]height = new int[8];
    boolean rightShift, leftShift;
    Color brown;
    int numGifts;
    Image present = new ImageIcon("assets/Gift.png").getImage();
    Font font = new Font("Arial", Font.BOLD, 50);
    Timer walking = new Timer(50, this);
    public LevelSelect()
    {
/* This constructor sets the background, adds a button if the user wishes to return to the home screen. It also initializes the variables to move the elf and let the user make it jump, walk, and shift the visible screen. It also randomly generates the location of trees so it doesn't change on every repaint. It also adds the Key Listener and Mouse Listener. */
        setBackground(lightBlue);

        completed[0]=true;
        for(int index=1; index<5; index++)
        completed[index]=false;

        setLayout(new FlowLayout(FlowLayout.LEFT, 10, 10));
        add(goHome[3]);
        add(save);

        left = right = rightShift = leftShift = focus = false; //movement
        time=300;
        speed=10;

        addKeyListener(this); //Listeners
        addMouseListener(this);
        addFocusListener(this);
        walking.start();    //Timer

        brown = new Color(202, 104, 0);
        int x=0;
        for(int which = 0; which<8; which++) //Set dimensions of trees
        {
            x+= (int)(Math.random()*200+100);
            int heights = (int)(Math.random()*100+100);
            trees[which]=x;
            height[which] = heights;
        }
     }
     public void paintComponent(Graphics g)
     {
/* This method paints the ground and calls methods to draw the trees, the level doors, and the elf. */
        super.paintComponent(g);
        g.setColor(Color.WHITE); //ground
        g.fillRect(shift-100, 400, 2200, 200);

        treeDraw(g); //trees
        levelDraw(g);
        elf(g); //elf

        g.setFont(font);
        g.drawString(""+numGifts, 730-(30*(""+numGifts).length()), 65);
        g.drawImage(present, 730, 20, 50, 50, null);
        if(!focus)
        {
            g.setColor(Color.RED);
            g.drawString("Click Anywhere to Play", 100, 275);
        }
     }

    public void levelDraw(Graphics g2)
    {
/* This method creates the doors which lead to the levels and labels them. */
        g2.setFont(new Font("Arial", Font.BOLD, 20));
        int index=0;
        while(completed[index] && index<5)
        {
            g2.setColor(Color.YELLOW);
            g2.fillRect(shift-5+350*(index+1), 250, 110, 175);
            g2.fillArc(shift-5+350*(index+1), 195, 110, 110, 0, 180);

            g2.setColor(Color.BLACK);
            g2.fillRect(shift+350*(index+1), 250, 100, 175);
            g2.fillArc(shift+350*(index+1), 200, 100, 100, 0, 180);
            g2.drawString("Level "+(index+1), shift+20+350*(index+1), 175);
            index++;
        }
    }
    public void treeDraw(Graphics g2)
    {
/* This method paints the trees based on the coordinates determined in the constructor. */
        for(int x=0; x<8; x++)
        {
            g2.setColor(brown);
            g2.fillRect(shift+trees[x], 400-height[x], 2, height[x]);
            g2.setColor(Color.GREEN);

            for(int y=0; y<height[x]; y+=3)
            {
                g2.drawLine(shift+trees[x]+1, 400-height[x]+y, shift+trees[x]-(int)(0.3*y), 400-height[x]+(int)(1.1*y));
                g2.drawLine(shift+trees[x]-1, 400-height[x]+y, shift+trees[x]+1+(int)(0.3*y), 400-height[x]+(int)(1.1*y));
            }
        }
     }
    public void elf(Graphics g2)
    {
/* This method paints the elf according to the location and action determined by the user. */
        if(jump==time && yAdd==0)
            g2.drawImage(elf[0], shift+xAdd, 280+yAdd, 150, 150, null);
        else if(!(right||left))
            g2.drawImage(elf[1], shift+xAdd, 280+yAdd, 150, 150, null);
        else
        {
            if(time%10>=0 && time%10<5)
                g2.drawImage(elf[2], shift+xAdd, 278+yAdd, 150, 152, null);
            else
                g2.drawImage(elf[3], shift+xAdd, 280+yAdd, 150, 150, null);
        }
    }
    public void levelPicked(int level)
    {
/* This method changes the game map to the user selected level. */
        walking.stop();
        if(level==1)
        {
             Level1 level1 = new Level1();
             masterPanel.add(level1, "Level1");
             whichScreen.show(masterPanel, "Level1");
        }
        else if(level==2)
        {
             Level2 level2 = new Level2();
             masterPanel.add(level2, "Level2");
             whichScreen.show(masterPanel, "Level2");
        }
        else if(level==3)
        {
             Level3 level3 = new Level3();
             masterPanel.add(level3, "Level3");
             whichScreen.show(masterPanel, "Level3");
        }
        else if(level==4)
        {
             Level4 level4 = new Level4();
             masterPanel.add(level4, "Level4");
             whichScreen.show(masterPanel, "Level4");
        }
        else if(level==5)
        {
             Introduction introToGame = new Introduction("Level5");
             masterPanel.add(introToGame, "Introduction");
             whichScreen.show(masterPanel, "Introduction");
        }
    }
    public void keyPressed(KeyEvent evt)
    {
/* This method determines if the user has tried to move the elf, moves the screen to show what is needed, and starts the process of jumping. */
        int code = evt.getKeyCode();
        if(code==KeyEvent.VK_RIGHT)
            right = true;
        if(code==KeyEvent.VK_LEFT)
            left= true;
        if(code==KeyEvent.VK_UP || code==KeyEvent.VK_SPACE)
        {
            jump = time;
            for(int index=1; index<=5; index++)
            if(xAdd<130+350*index && xAdd>350*index-50)
                levelPicked(index);
        }
        repaint();
     }
     public void keyReleased(KeyEvent evt)
     {
/*This method stops the screen from moving and repaints so the elf stands when not moving instead of having a foot hover. */
        left = right = false;
        repaint();
     }
     public void actionPerformed(ActionEvent evt)
     {
/* This method shifts the screen, changes the elf's elevation so it has a smooth jump, and repaints the screen. */
          time++;
          if(xAdd+shift>=shift+200 && shift>=-1200+speed && right)
          {
              rightShift=true;
              shift-=speed;
          }
          else rightShift=false;

          if(right)  xAdd+=speed;
          else if(left)  xAdd-=speed;

          if(jump+40>time)
          {
              int elapse = time-jump;
              yAdd= (int)(-30*(elapse)+(elapse)*(elapse));
              if(yAdd==0)jump=0;
          }
          if(left && shift+xAdd<200 && shift<-1*speed)
          {
              leftShift=true;
              shift+=speed;
          }
          else leftShift=false;

          repaint();
     }
     public void keyTyped(KeyEvent evt)
     {
/* If the user types F or S, the speed of the elf increases or decreases by a factor of two. */
          if(evt.getKeyChar()=='F') speed*=2;
          else if(evt.getKeyChar()=='S')speed/=2;
          else if(evt.getKeyChar()=='H')

          for(int index=0; index<5; index++)
          completed[index]=true;

          else if(evt.getKeyChar()=='+') numGifts++;
          else if(evt.getKeyChar()=='-') numGifts--;
     }
     public void mouseClicked(MouseEvent evt)
     {
/* This method makes it so that the KeyListener works. */
         grabFocus();
     }
     public void focusGained(FocusEvent e)
     {
/* This method gets rid of the text when the window gains focus */
        focus=true;
        repaint();
     }
     public void focusLost(FocusEvent e)
     {
/* This method adds the text when the window gains focus */
         focus=false;
         repaint();
     }
     public void mouseEntered(MouseEvent evt){}
     public void mouseExited(MouseEvent evt){}
     public void mousePressed(MouseEvent evt){}
     public void mouseReleased(MouseEvent evt){}
}
class SaveProgress extends JPanel implements ActionListener, FocusListener
{
//This class allows the user to save their progress
     JPanel keyboard = new JPanel(new GridLayout(4, 8));
     JButton[]keys = new JButton[32];
     JTextField typeHere = new JTextField(20);
     String name="";
     boolean upper = false;
     Font font = new Font("Arial", Font.BOLD, 20);

     public SaveProgress()
    {
//This constructor makes the keyboard and text field
        setLayout(new BorderLayout());

        for(int index=0; index<32; index++)
        {
                keys[index] = new JButton(""+(char)(index+97));
                keys[index].addActionListener(this);
                keys[index].setForeground(light);
                keys[index].setBackground(dark);
                keys[index].setFont(font);
                keyboard.add(keys[index]);
        }
        keys[26].setText("CAPS");
        keys[27].setText("SPA");
        keys[28].setText("DEL");
        keys[29].setText("ENT");
        keys[30].setText("");
        keys[31].setText("");

        typeHere.setBackground(light);
        typeHere.setFont(new Font("Arial", Font.BOLD, 50));
        typeHere.addActionListener(this);
        typeHere.addFocusListener(this);

        keyboard.setPreferredSize(new Dimension(800, 400));
        keyboard.setOpaque(false);
        add(keyboard, BorderLayout.SOUTH);
        add(typeHere, BorderLayout.CENTER);
    }
    public void actionPerformed(ActionEvent e)
    {
//This method deals with cicking the keys and typing in the text field
        if(e.getSource()==typeHere)
             name = typeHere.getText();
        else
        {
            String cmd = e.getActionCommand();
            if(cmd.equals("SPA")) cmd=" ";
            else if(cmd.equals("DEL") && name.length()>0)
            {
                cmd="";
                name = name.substring(0, name.length()-1);
            }
            if(name.length()<=20)
            {
                if(cmd.length()<=1)
                {
                    name = name+cmd;
                    typeHere.setText(name);
                }
                else if(cmd.equals("CAPS"))
                {
                    int add = 65;
                    if(upper) add= 97;
                    for(int index=0; index<26; index++)
                        keys[index].setText(""+(char)(index+add));

                    upper=!upper;
                }
            }
            if(cmd.equals("ENT"))
            {
                name=typeHere.getText();
                typeHere.setText("");
                leaders.save();
            }
        }
    }
    public void focusGained(FocusEvent e){}
    public void focusLost(FocusEvent e)
    {
//This method changes the name to what ever is in the text field after the user clicks away
        name = typeHere.getText();
    }
}
class ButtonHandler implements ActionListener
{
/* This class is responsible for the clicking of any button on any of the preliminary screens. It sets the master panel to the Game map, instructions, credits, leaderboard, or back to the home screen when needed. It also restarts the timer on the home screen which is stopped to decrease computing power when another screen is shown. */
    public void actionPerformed(ActionEvent evt)
    {
/* This method changes the visible screen based on user commands*/
        String command = evt.getActionCommand();
        if(command.equals("PLAY"))
            gameMap();
        else if(command.equals("INSTRUCTIONS"))
            whichScreen.show(masterPanel, "Instructions");
        else if(command.equals("BACK"))
            {
                whichScreen.show(masterPanel, "Home Screen");
                home.jump.start();
             }
        else if(command.equals("LEADERBOARD"))
            whichScreen.show(masterPanel, "Leaderboard");
        else if(command.equals("SAVE"))
            whichScreen.show(masterPanel, "Save Score");
    }
}
class Levels extends JPanel implements MouseListener
{
/* This class is meant to be the parent of all other levels. It creates a panel, sets the backbar, and the card layout that holds the different
   difficulties. It also has the methods to end the level by death or sucess. */
    CardLayout pickDifficulty = new CardLayout();
    JPanel play = new JPanel( pickDifficulty );
    ShowInstruct instruct;;
    Backbar options = new Backbar();

    String current="";
    boolean dead= false;
    boolean won = false;
    String elfName;
    int which;
    int score;
    int picked=1;
    Image present = new ImageIcon("assets/Gift.png").getImage();

    Color light = new Color(253, 255, 178);
    Color dark = new Color(23, 172, 83);
    Color lightBlue = new Color(220, 255, 255);

    public Levels(String get, int getwhich)
    {
/* This constructor sets the layout, starts a mouselistener for when the level is complete, and recieves what color elf will be freed by the level. */
        setLayout(new BorderLayout());
        addMouseListener(this);
        elfName = get;
        which=getwhich;
        instruct = new ShowInstruct();
        play.add(instruct, "INSTRUCTIONS");
        add(options, BorderLayout.EAST);
        add(play, BorderLayout.CENTER);
    }
    public void restart(){}
    public void killIt()
    {
/* This method removes the level and the backbar so the sucess or death screens would be visible when it repaints. . */
        removeAll();
        repaint();
    }
    public void paintComponent(Graphics g)
    {
/* This method paints either the sucess or death screen depending on the elf passed into the constructor and booleans. */
        super.paintComponent(g);
        setBackground(light);
        Image display = null;
        if(won)
        {
            display = new ImageIcon("assets/"+elfName+"Elf.png").getImage();
            g.drawImage(display, 200, 100, 200, 400, null);
            g.setFont(new Font("Arial", Font.BOLD, 20));
            g.drawString("Good Job Youngling!", 500, 200);
            g.drawString("Double Click me to proceed", 450, 230);
        }
        else if(dead)
        {
             display = new ImageIcon("assets/Died.png").getImage();
             g.drawImage(display, -25, -70, 900, 630, null);
        }
    }
    public void mouseClicked(MouseEvent e)
    {
/* This method exits the level after the player has won or died and clicked on the screen. */
        if(won||dead)
        {
            if(won)
            map.completed[which]=true;

            map.repaint();
            map.walking.start();
            whichScreen.show(masterPanel, "Game Map");
        }
    }
    public void mousePressed(MouseEvent e){}
    public void mouseReleased(MouseEvent e){}
    public void mouseEntered(MouseEvent e){}
    public void mouseExited(MouseEvent e){}
    class Backbar extends JPanel implements ActionListener
    {
/* This class creates a panel on the Right side with a timer and a set of buttons to pick the difficulty*/
        int time;
        Timer timer = new Timer(200, this);
        ButtonGroup group = new ButtonGroup();
        Color dark = new Color(23, 172, 83);
        Color light = new Color(253, 255, 178);
        JToggleButton[] difficulty = new JToggleButton[4];
        JLabel instructions;
        public Backbar()
        {
/*This constructor initializes the neccessary components on the sidebar as well as set aesthetics*/
            setBackground(Color.RED);
            setLayout(new FlowLayout(FlowLayout.CENTER, 5, 20));
            setPreferredSize(new Dimension(200, 600));
            timer.start();

            difficulty[0]=new JToggleButton("EASY");
            difficulty[1]=new JToggleButton("MEDIUM");
            difficulty[2]=new JToggleButton("HARD");
            difficulty[3]=new JToggleButton("INSTRUCTIONS");

            for(int whichButton=0; whichButton<4; whichButton++)
            {
                difficulty[whichButton].setPreferredSize(new Dimension(170, 23));
                difficulty[whichButton].setBackground(light);
                difficulty[whichButton].setForeground(dark);
                difficulty[whichButton].addActionListener(this);
                group.add(difficulty[whichButton]);
                add(difficulty[whichButton]);
            }

            difficulty[0].setSelected(true);
            instructions = new JLabel("");
            instructions.setHorizontalAlignment(JLabel.CENTER);
            instructions.setForeground(light);
            instructions.setFont(new Font("Arial", Font.BOLD, 20));
            add(instructions);
        }

        public void paintComponent(Graphics g)
        {
/* This method creates the clock that shows the user how long they have to play before winning. */
            super.paintComponent(g);
            g.setColor(Color.BLACK);
            g.fillOval(53, 420, 100, 100);
            g.setColor(Color.WHITE);
            g.fillOval(57, 424, 92, 92);
            g.setColor(Color.GREEN);
            g.fillArc(57, 424, 92, 92, 90, -360+time);
        }

        public void actionPerformed(ActionEvent e)
        {
/* This method changes the level according to user actions. It also increases the time based on the timer.  */
                String pressed = e.getActionCommand();
            if(e.getSource()==timer)
            {
                time+=2;
                if(time>=360 && which!=1)
                {
                    dead=true;
                    killIt();
                }
            }
            else
            {
                time = 0;
                pickDifficulty.show(play, e.getActionCommand());

                if(pressed.equals("EASY"))
                {
                    picked=1;
                    timer.start();
                }
                else if(pressed.equals("MEDIUM"))
                {
                    picked=2;
                    timer.start();
                }
                else if(pressed.equals("HARD"))
                {
                    picked=3;
                    timer.start();
                }
                else timer.stop();
                restart();
            }

            repaint();
        }
    }
    class ShowInstruct extends JPanel
    {
/*This class shows the for the level when called. */
        Image content;
        public ShowInstruct()
        {
            content = new ImageIcon("assets/Instruct"+which+".png").getImage();
        }
        public void paintComponent(Graphics g)
        {
            super.paintComponent(g);
            g.fillRect(0, 0, 600, 570);
            g.drawImage(content, 0, 0, getWidth(), getHeight(), null);
        }
    }
}
class Level1 extends Levels
{
//This class creates level 1
    Image mach = new ImageIcon("assets/Machine.jpg").getImage();
    Image bull = new ImageIcon("assets/Bullet.png").getImage();
    Image chains = new ImageIcon("assets/Chains.png").getImage();
    Grid[]set = new Grid[3];
    public Level1()
    {
/* This sets the background, creates the side panel to select the level and size of the elf,initializes the field variables, and adds the listeners. */
        super("Red", 1);
        String[]labels={"EASY","MEDIUM","HARD"};
        options.instructions.setText("<html><div style='text-align: center;'>"+"<html>SURVIVE<br/><br/>Avoid bullets<br/>and chains</html>"+"</div></html>");
        options.timer.stop();

        for(int index=0; index<3; index++)
        {
            set[index]=new Grid(index+1);
            play.add(set[index], labels[index]);
        }
        pickDifficulty.show(play, "EASY");
    }
    public void restart()
    {
/* This method restarts every version of the level when the user picks a different difficulty*/
        options.timer.stop();
        for(int index=0; index<3; index++)
        set[index].reset();
    }
    class Grid extends JPanel implements KeyListener, MouseListener, ActionListener, FocusListener
    {
/* Each instance of this class holds a different difficulty of level 1.*/
        boolean jumper,squat,running;
        Polygon playButton;
        int yAdd, time, level;
        int score;
        int [] giftX, giftY;
        JLabel showScore;
        Timer move = new Timer(10, this);
        int[] bullet, chain;

        public Grid(int num)
        {
/* This constructor creates the needed listeners, the textbox to show how many presents that the user has collected, and initializes variables.*/
            setLayout(new FlowLayout(FlowLayout.LEFT));

            addKeyListener(this);
            addMouseListener(this);
            addFocusListener(this);

            setBackground(Color.GRAY);
            jumper = dead = squat = running = false;
            level = num;

            showScore = new JLabel("");
            showScore.setForeground(Color.WHITE);
            showScore.setFont(new Font("Arial", Font.BOLD, 50));
            add(showScore);

            bullet = new int[level*7];
            chain = new int[level*7];
            pics();

            move.start();
            move.stop();

            int[]arr1={250, 450, 250};
            int[]arr2={150, 250, 350};
            playButton = new Polygon(arr1, arr2, 3);
        }
        public void pics()
        {
/* This method sets the location of the obstacles and initializes the pictures. */
            bullet[0]=chain[0]=750;
            giftX= new int[5*level];
            giftY= new int[5*level];

            for(int num=1; num<level*7; num++)
            {
                bullet[num]=(int)((num+1)*(Math.random()+1)*750)+1000*num;
                chain[num]=(int)((num+1)*(1+Math.random())*400)+500*num;
            }
            for(int index=0; index<5*level; index++)
            {
                giftX[index]=(int)(Math.random()*4000)+800;
                giftY[index]=280-(int)(Math.random()*180);
                for(int check=0; check<level*7; check++)
                {
                    if(giftX[index]>=chain[check]-70 && giftX[index]<=chain[check]+170)
                    {
                        giftX[index]=(int)(Math.random()*4000)+800;
                        check=-1;
                    }
                }
            }
        }
        public void reset()
        {
//This method restarts the clock in case of level change.
            time=0;
            move.stop();
            score=0;
            showScore.setText("");
            running=false;
         }
        public void paintComponent(Graphics g)
        {
/* Based on booleans, this method determines whether to paint the screen for winning, for dying, or painting the level. */
            super.paintComponent(g);

             g.setColor(dark);
             g.fillRect(0, 400, 3000, 200);

             for(int machines = 1; machines<10; machines++)
             g.drawImage(mach, machines*300-time, 300, 200, 100, null);

             for(int index = 0; index<7*level; index++)
             {
                 g.drawImage(bull, bullet[index]-time*2, 300, 100, 50, null);
                g.drawImage(chains, chain[index]-time, 0, 100, 350, null);
             }

             for(int index=0; index<5*level; index++)
             g.drawImage(present, giftX[index]-time, giftY[index], 50, 50, null);

             elf(g);
             g.setColor(Color.WHITE);

             if(running)
             {
                 g.fillRect(15, 515, 5, 20);
                 g.fillRect(23, 515, 5, 20);
             }
             else
             {
                 g.fillPolygon(playButton);
                 g.setColor(Color.BLACK);
                 g.setFont(new Font("Arial", Font.BOLD, 40));
                 g.drawString("PLAY", 280, 265);
             }
        }
    public void elf(Graphics g2)
    {
/* This method paints the elf according to the size determined by the user and commands. */
        int jump = yAdd;
        if(yAdd<-200) jump= -400-jump ;
        if(squat)
        g2.drawImage(elf[4], 50, 400, 150, 80, null);
        else
        {
          if(time/30%2==0)
          g2.drawImage(elf[2], 50, 280+jump, 150, 152, null);
          else
          g2.drawImage(elf[3], 50, 280+jump, 150, 150, null);
        }
        boolean deadCheck=false;
        for(int index=0; index<7*level; index++)
        {if(bullet[index]-time*2>=50 && bullet[index]-time*2<=150
           && -80>=jump-150 && -50<=jump && !squat)
        {deadCheck = true;}
        if(chain[index]-time>=50 && chain[index]-time<=125 && !squat)
        deadCheck = true;
        }

        if(options.time>=360)
        {
            won=true;
            dead=false;
            map.numGifts+=score;
        }
        else if(deadCheck)
        {
            dead=true;
        }
        if(dead ||won) killIt();

        for(int index=0; index<5*level; index++)
        if(giftX[index]-time>=50 && giftX[index]-time<=125 && giftY[index]-50<=430+jump && giftY[index]>=280+jump)
        {
            score+=5;
            showScore.setText(""+score);
            giftX[index]=-200;
        }

    }
    public void keyPressed(KeyEvent evt)
    {
/* This method determines whether the user choses to jump or squat. */
        int code = evt.getKeyCode();
        if(code==KeyEvent.VK_UP || code==KeyEvent.VK_SPACE || code==KeyEvent.VK_W)
        {jumper= true;}
        if(code==KeyEvent.VK_DOWN || code==KeyEvent.VK_S)
        {squat = true;
        jumper = false; yAdd=0;
       }
    }
    public void actionPerformed(ActionEvent evt)
    {
/* This timer moves the obstacles towards the character and changes the jump. */
       time+=2;
       if(jumper)yAdd-= 3;
       if(yAdd<-400) {jumper = false; yAdd=0; }
       repaint();
    }
    public void keyTyped(KeyEvent evt){}
    public void keyReleased(KeyEvent evt)
    {
/* This method stops the elf from squatting when the user releases the down button*/
        squat=false;
    }
    public void mouseClicked(MouseEvent evt)
    {
/* This method exits the program when they click the end screen and plays or pauses the game if the user clicks the pause button. */
        grabFocus();
        if(evt.getX()<=30 && evt.getY()>=370)
        {running=false; }
        if(playButton.contains(evt.getX(), evt.getY()))running=true;
        if(running){move.start(); options.timer.start();}
        else {move.stop();options.timer.stop();}
        repaint();
    }
    public void mouseExited(MouseEvent evt) {}
    public void mouseEntered(MouseEvent evt) {}
    public void mousePressed(MouseEvent evt) {}
    public void mouseReleased(MouseEvent evt) {}
    public void focusGained(FocusEvent e) {}
    public void focusLost(FocusEvent e)
    {
//This method stops the game when the user clicks away
        options.timer.stop();
        move.stop();
        running = false;
        repaint();
    }
  }
}
class Level2 extends Levels
 {
/* This class creates and runs level 2 when called*/
     Grid[]set = new Grid[3];
     public Level2()
     {
/* This constructor creates a card for each level and adds them to an overall panel before adding the options panel*/
        super("Yellow", 2);
        String[]labels = {"EASY","MEDIUM", "HARD"};
        for(int x=0; x<3; x++)
        {
            set[x]= new Grid(x+3);
            play.add(set[x], labels[x]);
        }
        options.instructions.setText("<html><div style='text-align: center;'>"+"<html>Turn All<br/>Buttons Green!</html>"+"</div></html>");
        pickDifficulty.show(play, "EASY");
     }
     public void start()
     {
/*This method starts the clock*/
         options.timer.start();
     }
     public void restart()
     {
/* This method restarts each level when a different difficulty is selected. */
         for(int index=0; index<3; index++)
         set[index].restart();
     }
 class Grid extends JPanel implements MouseListener
 {
/* Each instance of this class is a different difficulty for level 2. */
     int[][]list3={ {0, 1, 5}, {1, 4, 7}, {1, 2, 3, 6}, {3, 4, 6},
                    {4, 7, 8}, {4 , 5, 8}, {2, 6, 7}, {5, 7, 8}, {8, 5, 2} };

     int[][]list4={ {0, 1, 2, 6}, {1, 5, 9, 13}, {1, 2, 3, 6}, {3, 4, 7, 8},
        {4, 8, 11, 12}, {0, 5, 6, 7, 9}, {1, 5, 6, 7, 11}, {6, 7, 11, 12},
        {3, 4, 7, 8}, {8, 9, 13}, {0, 5, 9, 10}, {0, 5, 6, 10}, {3, 6, 7, 10},
        {4, 9, 13, 14}, {14, 10, 6},  { 15, 14, 11, 8} };

     int[][]list5={ {0, 1, 2, 7}, {1, 6, 11, 16}, {1, 2, 3, 7}, {3, 4, 8, 9},
        {4, 9, 13, 14}, {0, 5, 6, 7, 10}, {1, 5, 6, 7, 11}, {6, 7, 12, 13},
        {3, 4, 7, 8}, {8, 9, 14}, {0, 5, 10, 11}, {0, 5, 6, 11}, {3, 7, 8, 12},
        {4, 9, 13, 14}, {14, 17, 18, 19},  {10, 15, 16, 20}, {10, 11, 12, 16},
        {13, 16, 17, 18}, {12, 13, 18, 19}, {8, 9, 14, 19}, {15, 16, 20, 21},
        {15, 16, 17, 21}, {18, 21, 22, 23}, {13, 14, 18, 23}, {13, 18, 19, 24}};

     int[][][] master = {list3, list4, list5};
     int level, wait;
     boolean[]green;
     Button[] buttons;
     public Grid(int num)
     {
/* This method creates all the clickable panels and scrambles them before displying them to the audience. */
         setBackground(light);
         level = num;
         buttons = new Button[level*level];
         green = new boolean[level*level];
         setLayout(new GridLayout(num, num, 10, 10));
         addMouseListener(this);
         for(int index=0; index<num*num; index++)
         {
             green[index] = true;
             buttons[index] = new Button(index);
             add(buttons[index]);
         }
         for(int initial=0; initial<num*num; initial++)
         scramble(initial);
     }
     public void scramble(int clicked)
     {
/*This method changes multiple buttons according to the single one that the user clicked */
         int []arr= master[level-3][clicked];
         for(int index=0; index<arr.length; index++)
         green[arr[index]]=(!green[arr[index]]);
         won = true;
         for(int index=0; index<level*level; index++)
         {buttons[index].repaint(); if(!green[index]) won=false;}
         if(won)
         {
             map.numGifts+= picked*20;
             killIt();
         }
     }
     public void restart()
     {
/* This method restarts the panel if it is selected again so users cannot switch a panel and go back to it to get more time */
         removeAll();
         for(int index=0; index<level*level; index++)
         {
             green[index] = true;
             buttons[index] = new Button(index);
             buttons[index].setBackground(Color.GREEN);
         }
         for(int numAdd=0; numAdd<level*level; numAdd++)
         add(buttons[numAdd]);
         for(int initial=0; initial<level*level; initial++)
         scramble(initial);
     }
     public void paintComponent(Graphics g)
     {
/* This panel paints the elf that the user is trying to free and paints the picture for losing the level according to a boolean. */
        super.paintComponent(g);
     }
     public void mousePressed(MouseEvent e){}
     public void mouseReleased(MouseEvent e){}
     public void mouseClicked(MouseEvent e)
     {
/* This method determines which panel the user has clicked and inputs it to the scramble method */
         int x = e.getX();
         int y = e.getY();
         int row = (int)(x/(getWidth()/level));
         int column = (int)(y/(getHeight()/level));
         if(row==level)row--;
         if(column==level)column--;
         scramble(row+(column*level));
     }
     public void mouseEntered(MouseEvent e){}
     public void mouseExited(MouseEvent e){}
     class Button extends JPanel implements ActionListener
     {
/* Each instance of this class is one of the square buttons in the game. It allows me to animate the tiny pictures. */
         int whichOne; boolean stand;
         Timer workOut = new Timer(200, this);
         Image[] elf = { new ImageIcon("assets/StandingElf.png").getImage(),
             new ImageIcon("assets/CrouchingElf.png").getImage()};
         Image[] krampus = {new ImageIcon("assets/StandingKrampus.png").getImage(),
             new ImageIcon("assets/CrouchingKrampus.png").getImage()};

         public Button(int get)
         {
/* This contructor tells the button which index it needs to check to determine whether it is red or green*/
             whichOne=get;
             workOut.start();
         }
         public void paintComponent(Graphics g)
         {
/* This method either paints the panel red with krampus or green with an elf depending on its corresponding boolean*/
             super.paintComponent(g);
             if(green[whichOne])
             {
                 setBackground(Color.GREEN);
                 if(stand)
                 g.drawImage(elf[0], 25, 25, getWidth()-50, getHeight()-50, null);
                 else
                 g.drawImage(elf[1], 25, 25, getWidth()-50, getHeight()-75, null);
             }
             else
             {
                 setBackground(Color.RED);
                 if(!stand)
                    g.drawImage(krampus[0], 25, 25, getWidth()-50, getHeight()-50, null);
                 else
                    g.drawImage(krampus[1], 25, 25, getWidth()-50, getHeight()-75, null);
             }
         }
         public void actionPerformed(ActionEvent e)
         {
/* This method changes whether the character is standing or squatting based on
a timer */
            stand=!stand;repaint();}
     }
 }
}
class Level3 extends Levels
{
/* This class creates and runs level 3 when called. */
    Color darkBrown = new Color(122, 98, 56);
    Grid[]set = new Grid[3];
    public Level3()
    {
/* This constructor creates three instances of the Box class, one for each level. It then adds the options panel which allows the user to switch levels. */
       super("White", 3);
       String[]labels = {"EASY","MEDIUM", "HARD"};
        for(int x=0; x<3; x++)
        {
            set[x]= new Grid(x+1);
            play.add(set[x], labels[x]);
        }
        options.instructions.setText("<html><div style='text-align: center;'>"+"<html>Put the Box<br/>Back Together!</html>"+"</div></html>");
        pickDifficulty.show(play, "EASY");
    }
    public void start()
    {
/* This method starts the level */
        options.timer.start();
    }
    public void restart()
    {
/* This method resets each level once a different button is selected*/
        for(int index=0; index<3; index++)
        set[index].reset();
    }
    class Grid extends JPanel implements MouseListener, MouseMotionListener
    {
/* Each instance of this class is a different difficulty for level 3. */
        Polygon[] pieces;
        int[][]answers;
        int xAdd, yAdd;
        int select;
        boolean[]inPlace;
        Color brown = new Color(255, 218, 142);
        Color[] colors = {Color.BLUE, Color.RED, Color.YELLOW, Color.ORANGE, Color.PINK, Color.WHITE};
        public Grid(int level)
        {
/* This constructor adds the required listeners and creates multiple arrays. The boolean array tells whether the piece is in place. The polygon array holds the pieces. The answers array holds the location of the top left corner of the rectangle that bounds the piece when it is in place. It then initializes each according to the level passed into it. */
         setBackground(Color.CYAN);
         addMouseListener(this);
         addMouseMotionListener(this);
         inPlace = new boolean[level+3];
         pieces = new Polygon[level+3];
         answers= new int[level+3][2];
         for(int index=0; index<level+3; index++)
         {
             inPlace[index]=false;
             pieces[index]=null;
         }
         if(level==1)
         {
             int[][]xPieces=
             {{0, 264, 264, 88, 88, 0}, {0, 176, 176, 88, 88, 0},
             {0, 88, 88, 352, 352, 264, 264, 0},{0, 264, 264, 352, 352, 0}};
             int[][]yPieces=
             { {0, 0, 70, 70, 140, 140},{0, 0, 280, 280, 70, 70},
               {70, 70, 0, 0, 70, 70, 140, 140}, {70, 70, 0, 0, 140, 140}};
             for(int index=0; index<level+3; index++)
             pieces[index]= new Polygon(xPieces[index], yPieces[index], xPieces[index].length);

             answers[0][0]=65; answers[0][1]=65;
             answers[1][0]=329; answers[1][1]=65;
             answers[2][0]=65; answers[2][1]=135;
             answers[3][0]=65; answers[3][1]=205;
         }
         else if(level==2)
         {
             int[][]xPieces=
             {{0, 88, 88, 176, 176, 88, 88, 0}, {0, 352, 352, 264, 264, 0},
             {0, 88, 88, 176, 176, 264, 264, 176, 176, 0},
             {0, 88, 88, 176, 176, 88, 88, 0}, {0, 176, 176, 0}};
             int[][]yPieces=
             { {0, 0, 70, 70, 140, 140, 210, 210},{0, 0, 140, 140, 70, 70},
               {140, 140, 70, 70, 0, 0, 140, 140, 210, 210},
               {0, 0, 70, 70, 210, 210, 140, 140}, {0, 0, 70, 70}};
             for(int index=0; index<level+3; index++)
             pieces[index]= new Polygon(xPieces[index], yPieces[index], xPieces[index].length);

             answers[0][0]=65; answers[0][1]=65;
             answers[1][0]=153; answers[1][1]=65;
             answers[2][0]=65; answers[2][1]=135;
             answers[3][0]=329; answers[3][1]=135;
             answers[4][0]= 241; answers[4][1]=275;
         }
         else
         {
             int[][]xPieces=
             { {0, 176, 176, 88, 88, 0}, {0, 264, 264, 176, 176, 88, 88, 0},
               {0, 88, 88, 176, 176, 88, 88, 0}, {0, 88, 88, 0},
               {0, 176, 176, 264, 264, 0}, {0, 88, 88, 0}};
             int[][]yPieces=
             { {0, 0, 70, 70, 140, 140}, {0, 0, 70, 70, 140, 140, 70, 70},
               {70, 70, 0, 0, 140, 140, 210, 210}, {0, 0, 140, 140},
               {70, 70, 0, 0, 140, 140}, {0, 0, 210, 210}};
             for(int index=0; index<level+3; index++)
             pieces[index]= new Polygon(xPieces[index], yPieces[index], xPieces[index].length);

             answers[0][0]=65; answers[0][1]=65;
             answers[1][0]=241; answers[1][1]=65;
             answers[2][0]=65; answers[2][1]=135;
             answers[3][0]=241; answers[3][1]=135;
             answers[4][0]=153; answers[4][1]=205;
             answers[5][0]=417; answers[5][1]=135;
         }
         for(int index=0; index<level+3; index++)
         pieces[index].translate(100*index, 300);
        }
        public void paintComponent(Graphics g)
        {
/* This method paints the box, calls the method that paints the polygons, and determines if all the pieces are in place. If they are all in place and the user has won, the method paints the completed image. If the user runs out of time, it paints an image that indicates their loss.  */
            super.paintComponent(g);
            g.setColor(darkBrown);
            g.fillRect(50, 50, 470, 310);
            g.setColor(brown);
            g.fillRect(65, 65, 440, 280);
            piecePainter(g);
            won = true;
            for(int index=0; index<pieces.length; index++)
            if(!inPlace[index]) won=false;
            if(won)
            {
                map.numGifts+= picked*10;
                killIt();
            }
            else if(dead)
            {
             Image display = new ImageIcon("assets/Died.png").getImage();
             g.drawImage(display, -25, -43, 863, 590, null);
            }
        }
        public void piecePainter(Graphics g2)
        {
/* This method paints the pieces. */
          g2.setColor(Color.GREEN);
          for(int index=0; index<pieces.length; index++)
          {
              if(inPlace[index])
              g2.fillPolygon(pieces[index]);
          }
          for(int index=0; index<pieces.length; index++)
          {
              g2.setColor(colors[index]);
              if(!inPlace[index])
              g2.fillPolygon(pieces[index]);
          }
        }
        public void reset()
        {
/* Resets the panel when the level is switched*/
            for(int index=0; index<pieces.length; index++)
            {
                int xloc = (int)(pieces[index].getBounds().getX());
                int yloc = (int)(pieces[index].getBounds().getY());
                pieces[index].translate(-1*(xloc-100*index), -1*(yloc-300));
                inPlace[index]=false;
            }
        }
        public void mouseMoved(MouseEvent e){}
        public void mouseDragged(MouseEvent e)
        {
/* This method gets the coordinates where the piece is being dragged to and translates the polygon according to the difference. */
            if(select!=pieces.length)
            {
                int x = e.getX();
                int y = e.getY();

                pieces[select].translate(x-xAdd, y-yAdd);
                xAdd= x; yAdd= y;

                repaint();
            }
        }
        public void mouseClicked(MouseEvent e){}
        public void mousePressed(MouseEvent e)
        {
/* This method determines which polygon was clicked and sets its index as a field variable. */
            int x = e.getX();
            int y = e.getY();

            select = pieces.length;
            for(int index=0; index<pieces.length; index++)
            if(pieces[index].contains(x, y) && !inPlace[index])
            {
                select=index;
                xAdd = x; yAdd= y;
            }
        }
        public void mouseReleased(MouseEvent e)
        {
/* This method determines if the user has released the piece in the correct location. If the user is around 10 pixels off, the method translates it to the correct location. */
            if(select!=pieces.length)
            {
                int x = (int)(pieces[select].getBounds().getX());
                int y = (int)(pieces[select].getBounds().getY());

                if(answers[select][0]<x+20 && answers[select][0]>x-20 && answers[select][1]<y+20 && answers[select][1]>y-20)
                {
                    inPlace[select]=true;
                    pieces[select].translate(-1*(x-answers[select][0]),
                    -1*(y-answers[select][1]));
                }
            }
            select = pieces.length;
            repaint();
        }
        public void mouseEntered(MouseEvent e){}
        public void mouseExited(MouseEvent e){}
    }
}
class Level4 extends Levels
{
/* This class holds the entirety of Level 4 for easy calling. */
    Grid[]set = new Grid[3];
    public Level4()
    {
/* The constructor called creates all the options for the game and then this constructor fills the set in the card layout with the different levels */
        super("Green", 4);
        String[]labels = {"EASY","MEDIUM", "HARD"};
        for(int x=0; x<3; x++)
        {
            set[x]= new Grid(10*(x+1));
            play.add(set[x], labels[x]);
        }
        pickDifficulty.show(play, "EASY");
    }
    public void restart()
    {
/* Resets every grid when the level is switched*/
        for(int index=0; index<3; index++)
            set[index].reset();
        options.instructions.setText("Score "+(picked*10)+" Points!");
    }
    class Grid extends JPanel implements ActionListener, MouseListener
    {
/* Each instance of this class holds either easy medium or hard. */
        Timer side= new Timer(10, this);
        Color lightBlue = new Color(220, 255, 255);
        Image present = new ImageIcon("assets/Gift.png").getImage();
        JLabel displayScore;

        int time2, quota, score, numShots;
        int[]priorShots=new int[0];
        int yAdd=450;
        Polygon arrow;
        boolean flip = false;
        boolean shoot = false;
        Color darkBrown = new Color(122, 98, 56);
        public Grid(int num)
        {
/* This constructor sets the background, listeners, the label that will display the score, the arrow that will point to where the user will shoot, and starts the timer that is used to run the game. */
            setBackground(Color.CYAN);
            addMouseListener(this);
            setLayout(new FlowLayout(FlowLayout.LEFT, 10, 10));

            displayScore=new JLabel("");
            displayScore.setFont(new Font("Arial", Font.BOLD, 50));
            displayScore.setForeground(darkBrown);
            options.instructions.setText("Score 10 Points!");

            add(displayScore);
            side.start();

            quota = num;
            int[] arr1 = {0, -10, 60, 50, 75, 25, -25};
            int[] arr2 ={390, 440, 440, 390, 390, 365, 390};
            arrow = new Polygon(arr1, arr2, 7);
        }
        public void paintComponent(Graphics g)
        {
/* This method draws the target, the arrow, and the projectile that the user can shoot. */
            super.paintComponent(g);

            g.setColor(dark);
            g.fillRect(0, 300, 600, 600);
            for(int x=23; x>-1;x--)
            {
                g.setColor(new Color(dark.getRed()-x, dark.getGreen()-x, dark.getBlue()-x));
                g.fillRect(0, getHeight()+(23-4*x), 600, 4);
            }
            for(int x=50; x>-1; x--)
            {
                g.setColor(new Color(dark.getRed()+x, dark.getGreen()+x, dark.getBlue()+x));
                g.fillRect(0, 410-(50-4*x), 600, 4);
            }
            for(int index=1; index<8; index++)
            {
                if(index%2==0) g.setColor(Color.WHITE);
                else g.setColor(Color.RED);
                g.fillOval((index*25)+100, index*25, 400-(index*50), 400-(index*50));
            }

            g.setColor(light);
            g.fillPolygon(arrow);

            g.setColor(Color.BLACK);
            for(int index=0; index<priorShots.length; index++)
            {g.drawLine(priorShots[index], 195, priorShots[index], 205);
            g.drawLine(priorShots[index]-5, 200, priorShots[index]+5, 200);}

            if(!shoot)
            g.drawImage(present, time2-100, yAdd, 225, 112, null);

            if(shoot)
            g.drawImage(present, time2-100+(125-yAdd/4), yAdd-yAdd/50, yAdd/2, yAdd/4, null);

        }
        public void actionPerformed(ActionEvent e)
        {
/* This method handles the timer. It changes the x position of the arrow before the click and its y position afterwards. It then calls the method to score the shot.*/
            if(time2>550 || time2<0) flip=!flip;
            if(!shoot)
                { if(flip) {arrow.translate(1, 0); time2++;}
                  else {arrow.translate(-1, 0); time2--;}
                }
            if(shoot && yAdd>175)
            {
                yAdd--;
                if(side.getDelay()>0 && yAdd%30==0)
                side.setDelay(side.getDelay()+1);
            }
            else if (shoot)
            scoring();
            repaint();
        }
        public void scoring()
        {
/* This method adds the points of the user's last shot to the overall score. It then resets all variables to prepare for the next shot. */
            int centerX = time2+25;
            int add = 0;
            if(centerX>275 && centerX<325)add=10; //Bullseye
            else if(centerX>250 && centerX<350)add=8; //6
            else if(centerX>225 && centerX<375)add=7; //5
            else if(centerX>200 && centerX<400)add=5; //4
            else if(centerX>175 && centerX<425)add=4; //3
            else if(centerX>150 && centerX<450)add=3; //2
            else if(centerX>125 && centerX<475)add=2; //1

            score+=add;
            displayScore.setText(""+score);
            options.timer.start();
            arrow.translate(-1*time2, 0);
            shoot=false;
            yAdd=450;
            time2=0;
            flip=false;
            side.setDelay(10);
            if(add!=0)
            {
                int[] interm = new int[priorShots.length];
                for(int index=0; index<priorShots.length; index++)
                interm[index] = priorShots[index];
                priorShots = new int[interm.length+1];

                for(int index=0; index<interm.length; index++)
                priorShots[index]=interm[index];
                priorShots[priorShots.length-1]= centerX;
            }
            if(score>=quota)
            {
                won=true;
                map.numGifts+= picked*10;
                killIt();
            }
        }
        public void reset()
        {
/* Resets the frame when a level is switched. */
            score=0;
            displayScore.setText("");
            arrow.translate(-1*time2, 0);
            priorShots = new int[0];
            time2=0;
        }
        public void mouseClicked(MouseEvent e)
        {
/* This method starts the process of shooting when the user clicks the mouse. */
            options.timer.stop();
            shoot=true;
            side.setDelay(1);
            repaint();
        }
        public void mousePressed(MouseEvent e){}
        public void mouseReleased(MouseEvent e){}
        public void mouseExited(MouseEvent e){}
        public void mouseEntered(MouseEvent e){}
    }
 }
 class Level5 extends JPanel implements ActionListener, KeyListener, MouseListener, FocusListener
 {
//This is the final battle level where the user has to fight krampus as santa
     Timer timer = new Timer(15, this);
     Color lightBlue = new Color(144, 255, 255);
     Font font = new Font("Arial", Font.BOLD, 50);
     Image[]santa, krampus;
     int jump = -1;
     int xAdd, yAdd, timeB, win;
     Person santaRect; Person krampRect;
     JLabel words;
     public Level5()
     {
//This constructor adds the necessary listeners and two characters to the screen
         addMouseListener(this);
         addFocusListener(this);
         addKeyListener(this);
         setLayout(null);
         setBackground(lightBlue);

         words = new JLabel("Click Anywhere to Play");
         words.setFont(font);
         words.setForeground(Color.RED);
         words.setBounds(100, 225, 600, 50);
         words.setOpaque(false);

         santaRect = new Person("Santa");
         krampRect = new Person("Krampus");

         timer.start();

         santaRect.setBounds(0, 170, 300, 300);
         krampRect.setBounds(500, 140, 300, 300);

         add(santaRect);
         add(krampRect);
         add(words);
     }
     public void actionPerformed(ActionEvent e)
     {
//This method changes the height of the panels that hold the characters if the user jumps
        timeB+=2;
        if(timeB%10==0)
        krampActions(timeB/10);
        krampRect.magic=300;

        if(jump>0)
        santaRect.jump(jump);
        if(timeB%100==0 && santaRect.magic<300) santaRect.magic++;
        if(timeB%100==0 && santaRect.health<300)  santaRect.health++;
        repaint();
     }
     public void keyPressed(KeyEvent e)
     {
//This method interprets user movement and attacks
        int code = e.getKeyCode();
        int santX = santaRect.getX();
        if(code==KeyEvent.VK_UP)
            jump=timeB;
        else if(code==KeyEvent.VK_DOWN)
            santaRect.pos=1;
        else if((code==KeyEvent.VK_RIGHT && santX<=500)||(code==KeyEvent.VK_LEFT && santX>=0))
            mover(santaRect, code);
        else if(code==KeyEvent.VK_D)
            attack(santaRect, krampRect, 4);
        else if(code==KeyEvent.VK_A)
            attack(santaRect, krampRect, 6);
        else if(code==KeyEvent.VK_S && santaRect.magic>=50)
            santaRect.shotPrep(santX+150, santaRect.getY()+150, santX<krampRect.getX());
        else if(code==KeyEvent.VK_W && santaRect.magic>=100)
        {
            santaRect.magic-=100;
            krampRect.hoTime=timeB;
        }
        santaRect.repaint();
     }
     public void keyReleased(KeyEvent e)
     {
//This method changes the santa to stand when not moving
         santaRect.pos=0;
         santaRect.repaint();
     }
     public void mover(Person alias, int num)
     {
//This method moves the person panel passed into it according to the code passed into it
         Rectangle bounds = alias.getBounds();
         if(num==KeyEvent.VK_RIGHT)
         bounds.translate(7, 0);
         else bounds.translate(-7, 0);
         alias.setBounds(bounds);
         alias.pos=2;
     }
     public void attack(Person attack, Person vic, int first)
     {
//This method deals with setting the position for punching and kicking. It also decreases the victim's health
         if(attack.getX()<vic.getX()) attack.pos=first;
         else attack.pos=first+1;
         vic.health-=10;
     }
     public void krampActions(int time)
     {
//This method decides what Krampus will be doing according to the time
         while(time>200) time-=200;
         if(time<20)
            mover(krampRect, KeyEvent.VK_LEFT);
         else if(time<50)
            krampRect.pos=0;
         else if(time<80 && time%10==0)
            krampRect.shotPrep(krampRect.getX()+150, krampRect.getY()+150, krampRect.getX()<santaRect.getX());
         else if(time<100){}
         else if(time<120)
            mover(krampRect, KeyEvent.VK_RIGHT);
         else if(time<150)
            krampRect.pos=0;
         else if(time==152)
            santaRect.hoTime=timeB;
         else if(time<200){}
     }
     public void keyTyped(KeyEvent e){}
     public void mousePressed(MouseEvent e){}
     public void mouseClicked(MouseEvent e)
     {
//This method makes the key listener work
         grabFocus();
         if(win!=0)
             whichScreen.show(masterPanel, "Game Map");

     }
     public void mouseReleased(MouseEvent e){}
     public void mouseEntered(MouseEvent e){}
     public void mouseExited(MouseEvent e){}
     public void paintComponent(Graphics g)
     {
//This method paints the background and projectiles
         super.paintComponent(g);
         g.setColor(Color.BLACK);
         g.fillRect(0, 400, 800, 200);
         background(g);
         santaRect.shooter(g);
         krampRect.shooter(g);
         if(win!=0)
         {
           santaRect.setVisible(false);
           krampRect.setVisible(false);
           Image show;
           if(win==1)
            show = new ImageIcon("assets/EndOfLevel.png").getImage();
           else
            show = new ImageIcon("assets/Died.png").getImage();
           g.drawImage(show, 0, 0, getWidth(), getHeight(), null);
           map.walking.start();
         }

     }
     public void background(Graphics g2)
     {
//This method creates the background
         for(int color=0; color<=111; color++)
         {
             g2.setColor(new Color(144+color, 255-color, 255-color));
             g2.fillRect(790-(111-color)*8, 0, 8, 400);
         }
         for(int color=0; color<=255; color++)
         {
             g2.setColor(new Color(0, 255-color, 0));
             g2.fillRect(color*3, 400, 3, 200);
         }
     }
     public void focusLost(FocusEvent e)
     {
//This method decides if the user has to click to regain focus
       words.setVisible(true);
       santaRect.setVisible(false);
       krampRect.setVisible(false);
       timer.stop();
       repaint();
     }
     public void focusGained(FocusEvent e)
     {
//This method decides if the user has to click to regain focus
       words.setVisible(false);
       santaRect.setVisible(true);
       krampRect.setVisible(true);
       timer.start();
       repaint();
     }
      public void someoneDead()
      {
//This method determines who has died and sets the system to proceed as a winner or loser
           timer.stop();
           if(krampRect.health<=0)
           {
               map.numGifts+=100;
               win=1;
           }
           else win=2;
           repaint();
      }
     class Person extends JPanel
     {
//This class creates either santa or krampus
      Image[] stages = new Image[8];
      int pos = 0;
      int[][]shots = new int[0][4];
      String name;
      double health = 300;
      int magic = 300;
      int hoTime = -1;
      Health hp;
      Image projectile, ho;
      Image present = new ImageIcon("assets/Gift.png").getImage();
      Image boom = new ImageIcon("assets/Blast.png").getImage();
      Person other;
      public Person(String which)
      {
//This constructor creates the health/mp bards and initializes the Images
          super();
          setLayout(new BorderLayout());
          setVisible(false);

          hp = new Health();
          hp.setPreferredSize(new Dimension(0, 20));
          add(hp, BorderLayout.NORTH);

          setPreferredSize(new Dimension(300, 300));
          String[]title ={"Standing", "Squat", "Right", "Left", "RightPunch", "LeftPunch", "RightKick", "LeftKick"};
          for(int index=0; index<title.length; index++)
          stages[index]=new ImageIcon("assets/"+title[index] + which + ".png").getImage();
          if(which.equals("Santa"))
          {
              stages[0]=new ImageIcon("assets/Standing1Santa.png").getImage();
              name="Santa";
              projectile = present;
              ho= boom;
          }
          else
          {
            projectile = boom;
            ho= present;
            name="Krampus";
          }
            setOpaque(false);
      }
      public void paintComponent(Graphics g)
      {
//This method paints the character in its designated position
          super.paintComponent(g);
          if(name.equals("Santa")) other = krampRect;
          else other = santaRect;
          if(pos==0)
          g.drawImage(stages[0], 50, 30, 220, 270, null);
          else if(pos==1)
          g.drawImage(stages[1], 0, 130, 300, 170, null);
          else if(pos==2)
          {
              if(timeB/30%2==0)
              g.drawImage(stages[2], 0, 30, 300, 270, null);
              else
              g.drawImage(stages[3], 0, 30, 300, 270, null);
          }
          else
              g.drawImage(stages[pos], 0, 30, 300, 270, null);

          if(hoTime>0)
          {
              for(int index=0; index<300; index++)
              g.drawImage(ho, (int)(Math.random()*270), (int)(Math.random()*270), 20, 20, null);
              health-=0.5;
              hp.repaint();
              if(hoTime+300<=timeB) {hoTime=-1;}
          }
      }
      public void jump(int start)
      {
//This method moves the panel so that the character jumps in accordance with a physics equation
          int elapse = timeB-start;
          int height = (int)(5*elapse+0.0002*elapse*elapse);
          setBounds(getX(), height, 300, 300);
          if(170<=height) jump=-1;
      }
      public void shotPrep(double xGet, double yGet, boolean right)
     {
//This method creates a present projectile when the santa shoots
         int x = (int)(xGet);
         int y = (int)(yGet);
         int[][]interm = shots;
         shots = new int[interm.length+1][4];
         for(int index=0; index<interm.length; index++)
         shots[index]=interm[index];

         shots[interm.length][0]=x;
         shots[interm.length][1]=y;

         if(right) shots[interm.length][2] = 1;
         else shots[interm.length][2] = -1;
         shots[interm.length][3]= timeB;
     }
     public void shooter(Graphics g2)
     {
//This method paints projectiles
         for(int index=0; index<shots.length; index++)
         {
             int elapse = timeB-shots[index][3];
             if(elapse==0){magic-=75; }
             g2.drawImage(projectile, shots[index][0]+shots[index][2]*elapse, shots[index][1]+(int)(elapse*-1.5+0.004*Math.pow(elapse, 2)), 50, 50, null);
             if(shots[index][2]*elapse+shots[index][0]<=other.getX()+200 &&
             shots[index][2]*elapse+shots[index][0]>=other.getX()+100 && other.pos!=1)
             {
                 removeShot(index);
                 other.health-=20;
             }
             else if(elapse>=300)
             removeShot(index);
         }
     }
     public void removeShot(int get)
     {
//This method removes a projectile when it reaches its maximum limit
         int[][] interm = shots;
         shots = new int[shots.length-1][4];
         for(int index=0; index< shots.length; index++)
         shots[index]=interm[index+1];
         repaint();
     }
      class Health extends JPanel
      {
//This class paints the health and mp bars of each character
          public Health()
          {
//This constructor sets the background or the health/mp bar and starts the timer that will regenerate its health
              setBackground(Color.BLACK);
          }
          public void paintComponent(Graphics g)
          {
//This method paints the health/magna points bar
              super.paintComponent(g);
              g.setColor(Color.RED);
              if(health<=0) someoneDead();
              g.fillRect(300-(int)health, 0, 300, 10);
              g.setColor(Color.BLUE);
              g.fillRect(300-magic, 10, 300, 10);
          }
      }
    }
 }
}
