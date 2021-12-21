import java.awt.*;
import java.awt.event.*;
import java.applet.*;


public class Bicycle2 extends Applet implements ActionListener
{
  public Button Pedal = new Button("Pedal");
  public Button GearUp = new Button("+");
  public Button GearDown = new Button("-");

  int pos=0;
  Bike MyBike = new Bike(); //make a bike object

  //Initialize the applet
  public void init()
  {//set up the interface
    resize(600,60);
    add(Pedal);
    add(GearUp);
    add(GearDown);
    Pedal.addActionListener(this);
    GearUp.addActionListener(this);
    GearDown.addActionListener(this);
  }

  public void paint(Graphics g)
  {
    //let's make the bike move
    //first get its position
    MyBike.processBike(); //update the bike
    pos = MyBike.position/10;  //yuk, direct access to member data
    //finally let's draw the bike       ok it's doesn't look great, so make it better

    g.fillOval(pos,50,10,10);
    g.fillOval(pos+25,50,10,10);
    g.drawLine(pos+5,55,pos+30,55);
    g.drawLine(pos+30,55,pos+22,35);
     showStatus("xBike pos = "+MyBike.position+" Gear = "+MyBike.gear+" speed = " +MyBike.speed);
   repaint();
  } //end paint

  //handle the interface
  public void actionPerformed(ActionEvent ae)
  {
    if(ae.getSource()==Pedal)
      MyBike.pedal();

    if(ae.getSource()==GearUp)
      MyBike.gear++; //agh more tweaking with internal data gearUp();

    if(ae.getSource()==GearDown)
      MyBike.gear--;  //horrible, what happens if I get gear -1!?
  }

  //the DEFINITION of the CLASS Bike (we make an object called MyBike on line 20
  public class Bike
  {
    //member data
    private final int maxGear=3;
    public int speed=0;
    public int gear=1;
    public int position;
    private int count=0;
    //interface functions
    public void pedal()
    {
      speed+=gear;// = (speed *gear)+1;
      if (speed >200) speed = 200; //keep the speed to a sensible value by ensuring it doesn't go over 200
    }

    //called to continually update the position of the bike
    public void processBike()
    {
     count++;
     if (count>10)
     {
      speed=speed-1;
      count=0;
     }
      if (speed<0) speed=0;
      position = (position + speed);
      if(position>6000)
        position=0;  //10x because we div it by 10 to slow it down
    }
    //PUT YOUR NEW METHODS IN HERE
    //START BY WRITING ONE FOR THE BIKE'S POSITION AND DON@T FORGET TO CHANGE
    //THE LINE IN PAINT WHERE ITS VALUE IS USED

  }
  //Get parameter info

}//end applet