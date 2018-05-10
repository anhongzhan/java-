/**
 * swing编程   实现图片轮换
 */
package com.anhongzhan;


import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import javax.swing.*;

public class question02{
    
    JFrame jFrame1 = new JFrame();
    int number = 0;
    ImageIcon Icon;
    JPanel jPanel;
    JLabel jLabel;
    JButton jButton1,jButton2;
    Dimension d = Toolkit.getDefaultToolkit().getScreenSize(); 
    
    File dirFile;
    public static void main(String[] args){
        try {
            @SuppressWarnings("unused")
            question02 q = new question02();
        } catch (Exception e) {
            // TODO: handle exception
        }
    }
    
    private question02(){
        dirFile = new File("images");
      //判断该文件或目录是否存在，不存在时在控制台输出提醒  
        if (!dirFile.exists()) {  
            System.out.println("do not exit");  
            return ;  
        }  
        //判断如果不是一个目录，就判断是不是一个文件时文件则输出文件路径  
        if (!dirFile.isDirectory()) {  
            if (dirFile.isFile()) {  
                try {
                    System.out.println(dirFile.getCanonicalFile());  
                } catch (Exception e) {
                    // TODO: handle exception
                    e.printStackTrace();
                } 
            }  
            return ;  
        }  
        
        
        String[] fileList = dirFile.list();
        jButton1 = new JButton("上一张");
        jButton2 = new JButton("下一张");
        
        draw("images/"+fileList[number]);
        
        jButton1.addActionListener(new ActionListener() {           
            @Override
            public void actionPerformed(ActionEvent e) {
                // TODO Auto-generated method stub
                JFrame jFrame = new JFrame(fileList[number]);
                jFrame1.dispose();
                if(number > 1){
                    number--;
                    jLabel = new JLabel(new ImageIcon("images/"+fileList[number]));
                }else {
                    jLabel = new JLabel(new ImageIcon("images/"+fileList[number]));
                }
                jLabel.setSize(d);
                jFrame.setTitle(fileList[number]);
                jPanel = new JPanel();
                jPanel.add(jLabel);
                jPanel.setSize(d.width,d.height);
                jFrame.add(jPanel,BorderLayout.CENTER);
                jFrame.add(jButton1,BorderLayout.WEST);
                jFrame.add(jButton2,BorderLayout.EAST);
                jFrame.setVisible(true);
                jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                jFrame.setSize(d.width, d.height);
                jFrame1 = jFrame;
            }
        });    
        jButton2.addActionListener(new ActionListener() {
            
            @Override
            public void actionPerformed(ActionEvent e) {
                // TODO Auto-generated method stub
                JFrame jFrame = new JFrame();
                jFrame1.dispose();
                if(number<fileList.length){
                    number++;
                    jLabel = new JLabel(new ImageIcon("images/"+fileList[number]));
                }else {
                    jLabel = new JLabel(new ImageIcon("images/"+fileList[number]));
                }
                jLabel.setSize(d);
                jFrame.setTitle(fileList[number]);
                jPanel = new JPanel();
                jPanel.add(jLabel);
                jPanel.setSize(d.width,d.height);
                jFrame.add(jPanel,BorderLayout.CENTER);
                jFrame.add(jButton1,BorderLayout.WEST);
                jFrame.add(jButton2,BorderLayout.EAST);
                jFrame.setVisible(true);
                jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                jFrame.setSize(d.width, d.height);
                jFrame1 = jFrame;
            }
        });
        
    }
    
    public void draw(String string){
        JFrame jFrame = new JFrame();
        jFrame1 = jFrame;
        jFrame.setTitle(string);
        jLabel = new JLabel(new ImageIcon(string));
        jLabel.setSize(d);
        jPanel = new JPanel();
        jPanel.add(jLabel);
        jPanel.setSize(d.width,d.height);
        jFrame.add(jPanel,BorderLayout.CENTER);
        jFrame.add(jButton1,BorderLayout.WEST);
        jFrame.add(jButton2,BorderLayout.EAST);
        jFrame.setVisible(true);
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jFrame.setSize(d);
    }
    
   
}
