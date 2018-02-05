import java.awt.*;
import java.awt.event.*;
import java.awt.geom.Line2D;
import java.util.Random;

import javax.swing.JApplet;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class rtRace extends JPanel {
	JFrame f = new JFrame("Race");
	Anim pic = new Anim();
	int toLaX = 720;
	int toLaY = 720;
	int toNeX = 720;
	int toNeY = 720;
	int rabLaX = 720;
	int rabLaY = 720;
	int rabNeX = 720;
	int rabNeY = 720;
	int toState = 1;
	int rabState = 1;
	boolean toFirstRound = true;
	boolean rabFirstRound = true;
	boolean toRun = false;
	boolean rabRun = false;
	int toPos = 1;
	int rabPos = 1;
	int block = 2;
	int nowToStep = block;
	int nowRabStep = block;
	Random rand =new Random(25);
	int i,i2;
	boolean havePrint = false;
	JButton startButton = new JButton("Start");
	JButton endButton = new JButton("Stop");
	
	
	JPanel panel = new JPanel(); // 创建一个JPanel的实例    


	public void actionPerformed(ActionEvent e){
		String buttonName = e.getActionCommand();
		if (buttonName.equals("startButton"))
    	  System.out.println("press");
    }
	
	public void init() {
		pic.setPreferredSize(new Dimension(50,50));
		f.add(startButton,BorderLayout.SOUTH); 
		f.add(endButton,BorderLayout.SOUTH); 
		startButton.addMouseListener(new MouseListener(){
            public void mouseClicked(MouseEvent e) {
                System.out.println("BANG !!!!!\nAND THEY'RE OFF !!!!! ");
		    	havePrint = false;
                toRun = true;
                rabRun = true;
		    	toFirstRound = true;
		    	rabFirstRound = true;
                toPos = 1;
                rabPos = 1;
//                System.out.println(toRun);
            }
            public void mousePressed(MouseEvent e) {}
            public void mouseReleased(MouseEvent e) {}
            public void mouseEntered(MouseEvent e) {}
            public void mouseExited(MouseEvent e) {}
        });

		endButton.addMouseListener(new MouseListener(){
            public void mouseClicked(MouseEvent e) {
//                System.out.println("");
                toRun = false;
                rabRun = false;
            }
            public void mousePressed(MouseEvent e) {}
            public void mouseReleased(MouseEvent e) {}
            public void mouseEntered(MouseEvent e) {}
            public void mouseExited(MouseEvent e) {}
        });
		
		f.add(pic);
		

	    panel.setBackground(Color.gray);
	    panel.add(startButton); // 将JButton实例添加到JPanel中  
	    panel.add(endButton); // 将JButton实例添加到JPanel中  

	    f.add(panel, BorderLayout.SOUTH); // 将JPanel实例添加到JFrame的南侧
		
		setBackground(Color.WHITE);
		
		
		KeyAdapter keyact = new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
//                if(e.getKeyCode()==KeyEvent.VK_SPACE)
//                {
//                	try   
//        		    {   
//        		    Thread.currentThread().sleep(10);//毫秒  
//        			pic.repaint(); 
//        		    }   
//        		    catch(Exception e1){}  
//                }
            }
        };
		
		f.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) { System.exit(0); } 
		});
		pic.addKeyListener(keyact);
        f.addKeyListener(keyact);
		f.setBackground(Color.white);
		f.pack();
		f.setSize(new Dimension(800, 850));
		f.setVisible(true);
		while(toNeX < 800){
			{
			    try   
			    {   
			    Thread.currentThread().sleep(100);//毫秒  
			    if(toRun&&rabRun){pic.repaint(); }
				
			    }   
			    catch(Exception e){}  
			}
		}
	}
	public static void main(String s[]) {
		new rtRace().init();
	}
	
	class Anim extends JPanel{
		void setStep(){
			
			i2=rand.nextInt(100);
			if (i2<20){nowRabStep = 0;}
			else if (i2<40){nowRabStep = 8*block;}
			else if (i2<50){nowRabStep = -10*block;}
			else if (i2<75){nowRabStep = 1*block;}
			else {nowRabStep = -2*block;}
		}
		
		void toGetPos(int pos){
			if (0>=pos){
				toNeX = 720;
				toNeY = toNeX;
			}
			if (0<pos && pos<160){
				toNeX = 720 - pos * 2;
				toNeY = toNeX;
			}
			if (pos>=160){
				toNeX = 390 - (pos - 160)*2;
				toNeY = 390 + (pos - 160)*2;
			}
		}
		
		void toSetPos(){
			i=rand.nextInt(100);
			if (toPos>=320){
				toFirstRound = false;
				toPos = 320;
			}
			if (toPos<0&&!toFirstRound){
				toRun=false;
			}
			if (toFirstRound&&toRun){
				if (i<45){toPos += 3;}
				else if(i<70){toPos -= 6;}
				else {toPos += 1;}
			}else{
				if (i<45){toPos -= 3;}
				else if(i<70){toPos += 6;}
				else {toPos -= 1;}
			}
			
		}
		
		void rabGetPos(int pos){
			if (0>=pos){
				rabNeX = 720;
				rabNeY = rabNeX;
			}
			if (0<pos && pos<160){
				rabNeX = 720 - pos * 2;
				rabNeY = rabNeX;
			}
			if (pos>=160){
				rabNeX = 390 - (pos - 160)*2;
				rabNeY = 390 + (pos - 160)*2;
			}
		}
		
		void rabSetPos(){
			i2=rand.nextInt(100);
			if (rabPos>=320){
				rabFirstRound = false;
				rabPos = 320;
			}
			if (rabPos<0&&!rabFirstRound){
				rabRun=false;
			}
			if (rabFirstRound){
				if(rabRun){
					if (i2<20){rabPos += 0;}
					else if (i2<40){rabPos += 8;}
					else if (i2<50){rabPos -= 10;}
					else if (i2<75){rabPos += 1;}
					else {rabPos -= 2;}
				}
			}else{
				if(rabRun){
					if (i2<20){rabPos += 0;}
					else if (i2<40){rabPos -= 8;}
					else if (i2<50){rabPos += 10;}
					else if (i2<75){rabPos -= 1;}
					else {rabPos += 2;}
				}
			}
			
		}
		
		@Override
		public void paint(Graphics g){
			super.paint(g);
			//	画线
		    g.setColor(java.awt.Color.GRAY);
		    g.drawLine(400, 400, 800, 800);
		    g.drawLine(400, 400, 0, 800);
		    //	画乌龟
		    g.setColor(java.awt.Color.green);
		    g.drawString("Tortoise", toNeX, toNeY);
//		    g.fillOval(toNeX, toNeY, 20, 20);
		    //	画兔子
		    g.setColor(java.awt.Color.red);
		    g.drawString("Hare", rabNeX, rabNeY);
		    toLaX = toNeX;
		    toLaY = toNeY;
		    rabLaX = rabNeX;
		    rabLaY = rabNeY;
		    toGetPos(toPos);
		    toSetPos();
		    rabGetPos(rabPos);
		    rabSetPos();
		    
		    if(rabRun && toPos<rabPos){
			    for (int a = 0; a< toPos; a++){
			    	System.out.print(" ");
			    }
			    System.out.print("T");
			    if(toFirstRound){System.out.print("1");}
			    else{System.out.print("2");}
			    for (int a = toPos+1; a< rabPos; a++){
			    	System.out.print(" ");
			    }
			    System.out.print("H");
			    if(rabFirstRound){System.out.print("1");}
			    else{System.out.print("2");}
			    for (int a = rabPos; a< 320; a++){
			    	System.out.print(" ");
			    }
		    	System.out.println(" ");
		    }
		    if(rabRun && toPos>rabPos){
			    for (int a = 0; a< rabPos; a++){
			    	System.out.print(" ");
			    }
			    System.out.print("H");
			    if(rabFirstRound){System.out.print("1");}
			    else{System.out.print("2");}
			    for (int a = rabPos+1; a< toPos; a++){
			    	System.out.print(" ");
			    }
			    System.out.print("T");
			    if(toFirstRound){System.out.print("1");}
			    else{System.out.print("2");}
			    for (int a = toPos; a< 320; a++){
			    	System.out.print(" ");
			    }
		    	System.out.println(" ");
		    }
		    if(rabRun && toPos==rabPos){
		    	for (int a = 0; a< rabPos; a++){
			    	System.out.print(" ");
			    }
		    	System.out.print("OUCH!!!");
		    	for (int a = rabPos+1; a< 320; a++){
			    	System.out.print(" ");
			    }
		    }

		    if(toPos<=0&&!toFirstRound&&!havePrint){
		    	System.out.println("TORTOISE WINS!!!YAY!!!");
		    	havePrint = true;
		    }
		    if(rabPos<=0&&!rabFirstRound&&!havePrint){
		    	System.out.println("HARE WINS!YUCH!");
		    	havePrint = true;
		    }
		    
		}
	}
}