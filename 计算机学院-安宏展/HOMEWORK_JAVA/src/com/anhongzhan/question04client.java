package com.anhongzhan;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.EOFException;
import java.io.IOException;
import java.net.Socket;
import java.net.SocketException;
import java.net.UnknownHostException;

public class question04client extends Frame{

    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    Socket s;
    DataOutputStream dos = null;
    DataInputStream dis = null;
    private boolean bConnected = false;

    TextField tfTxt = new TextField();
    TextArea taContent = new TextArea();

    Thread trecv = new Thread(new RecvThread());

    public static void main(String[] args) {

        new question04client().launchFrame();
    }

    public void launchFrame(){
        setLocation(400, 300);  //��������������ʼλ��
        this.setSize(300, 300); //�����������Ĵ�С
        add(tfTxt, BorderLayout.SOUTH); //�����ʾ��
        add(taContent, BorderLayout.NORTH); //��������
        pack();  //�������������������õ�����趨���ڵĴ�С ʹ֮��������������õ��������
        this.addWindowListener(new WindowAdapter() {    //��Ӵ��ڹر��¼�
            @Override
            public void windowClosing(WindowEvent e) {
                disconnect();
                System.exit(0);
            }
        });
        tfTxt.addActionListener(new TFListener());  //Ϊ�����ı���󶨼�����
        setVisible(true);
        connect();

        trecv.start();
    }

    //����ͻ���TCP��Socket��IP��ַΪ127.0.0.1���˿ں�Ϊ8888
    //�ÿͻ������ںͷ��������ӣ�������ӳɹ�������������ӡ�������
    public void connect(){
        try {
            s = new Socket("127.0.0.1", 8888);
            dos = new DataOutputStream(s.getOutputStream());
            dis = new DataInputStream(s.getInputStream());
            System.out.println("�����ӣ�");
            bConnected = true;
            //�����Կͻ��˵Ľ����߳�
//            new Thread(new RecvThread()).start();
        }catch (UnknownHostException e){
            e.printStackTrace();
        }catch(IOException e){
            e.printStackTrace();
        }
    }

    //�ر����ӣ���Դ����
    public void disconnect(){
        try {
            dos.close();
            dis.close();    //�ȰѶ�ȡͨ���ر�
            s.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        /*
        try{
            bConnected = false;
            trecv.join();
        }catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            try {
                dos.close();
                dis.close();    //�ȰѶ�ȡͨ���ر�
                s.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        */
    }

    /*�����ı���ļ����������ڽ������ı�������������ݸ��Ƶ���ʾ����*/
    private class TFListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            String str = tfTxt.getText().trim();
//            taContent.setText(str);
            tfTxt.setText("");  //��������

            try {
//                DataOutputStream dos = new DataOutputStream(s.getOutputStream());
                dos.writeUTF(str);
                dos.flush();
                //dos.close();  //д��һ�о͹��ˣ�����ֻ�����һ��
            }
            catch (IOException e1){
                e1.printStackTrace();
            }
        }
    }

    //�ͻ��˵Ľ����߳�
    private class RecvThread implements Runnable {
        @Override
        public void run() {
            try {
                while(bConnected){
                    String str = dis.readUTF();
//                    System.out.println(str);
                    taContent.setText(taContent.getText() + str + '\n');
                }
            } catch (SocketException e){
                System.out.println("�˳��ˣ�bye bye!");
            } catch (EOFException e){
                System.out.println("�˳��ˣ�bye bye!");
            }
            catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}