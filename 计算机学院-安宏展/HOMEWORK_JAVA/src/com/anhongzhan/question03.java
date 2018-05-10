/**
 * GUI  和清明节有关的图
 */
package com.anhongzhan;

import java.awt.Graphics;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class question03 extends JFrame{
    
    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    MyPanel mPanel;
    public static void main(String[] args) {
        @SuppressWarnings("unused")
        question03 q = new question03();
    }
    
    public question03(){
        mPanel = new MyPanel();
        this.add(mPanel);
        
        this.setSize(500, 500);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
    }
}

class MyPanel extends JPanel{
    
    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    @Override
    public void paint(Graphics g) {
        // TODO Auto-generated method stub
        super.paint(g);
        
        g.drawLine(180, 50, 280, 50);
        
        g.drawLine(180, 50, 180, 250);
        
        g.drawLine(180, 250, 280, 250);
        
        g.drawLine(280, 50, 280, 250);
        
        g.drawLine(100, 250, 360, 250);//长横线
        
        g.drawLine(100, 250, 50,300);
        
        g.drawLine(360, 250, 310,300);
        
        g.drawLine(50, 300, 310,300);
        
        g.drawLine(50, 300, 50,330);
        
        g.drawLine(310, 300, 310,330);
        
        g.drawLine(360, 280, 360, 250);
        
        g.drawLine(360, 280, 310, 330);
        
        g.drawLine(200, 40, 180, 50);
        
        g.drawLine(280, 50, 300, 40);
        
        g.drawLine(200, 40, 300, 40);
        
        g.drawLine(300, 250, 300, 40);
        
        g.drawLine(50,330,310,330);
        
        g.drawLine(150, 280, 150, 180);
        
        g.drawLine(210, 280, 210, 180);
        
        g.drawLine(270, 280, 270, 180);
    }
}
