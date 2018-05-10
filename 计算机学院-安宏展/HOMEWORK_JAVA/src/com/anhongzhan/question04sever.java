package com.anhongzhan;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.EOFException;
import java.io.IOException;
import java.net.BindException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.List;

public class question04sever {

    boolean started = false;
    ServerSocket ss = null;

    //����һ���ͻ�������
    List<Client> clients = new ArrayList<Client>();

    //�������߳�
    public static void main(String[] args)  {
        new question04sever().start();
    }

    //���������̵߳�start�������ֻ���𲻶Ͻ����µĿͻ��˲�new�߳���֮����
    public void start(){
        //����һ��ServerSocket����ServerSocket���ڽ��ն˿ں�Ϊ8888�Ŀͻ���
        try {
            ss = new ServerSocket(8888);
            started = true;
        }catch (BindException e){
            System.out.println("�˿�ʹ����");
            System.out.println("��ص���س����������з�������");
            System.exit(0);
        }
        catch (IOException e) {
            e.printStackTrace();
        }

        try{
            //������������

            while (started){
                Socket s = ss.accept();
                Client c = new Client(s);
                System.out.println("һ���ͻ�����������");
                new Thread(c).start();
                //�����������һ���ͻ���
                clients.add(c);
            }
        }
        catch (IOException e) {     //��������ֱ�Ӵ�ӡ
            e.printStackTrace();
        }finally {  //���һ��ִ�йر���Դ
            try {
                ss.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    //һ���ͻ����̵߳�ʵ��
    class Client implements Runnable{

        private Socket s;
        private DataInputStream dis = null;
        private DataOutputStream dos = null;
        private boolean bConnected = false;

        public Client(Socket s){
            this.s = s;
            try {
                dis = new DataInputStream(s.getInputStream());
                dos = new DataOutputStream(s.getOutputStream());
                bConnected = true;
            }catch (IOException e){
                e.printStackTrace();
            }
        }

        public void send(String str){
            try {
                dos.writeUTF(str);
            } catch (IOException e) {
                clients.remove(this);
                System.out.println("�Է��˳��ˣ��Ҵ�list��ȥ���ˣ�");
            }
        }

        @Override
        public void run() {

            Client c = null;

            try {
                while (bConnected) {
                    String str = dis.readUTF();
                    System.out.println(str);

                    //���ͻ��˷�������Ϣ��ת�����ͻ���
                    for(int i = 0; i < clients.size(); i++){
                        c = clients.get(i);
                        c.send(str);
//                        System.out.println("������һ�仰!");
                    }
                    /*for(Iterator<Client> it = clients.iterator(); it.hasNext();){
                        Client c = it.next();
                        c.send(str);
                    }*/
                    /*
                    Iterator<Client> it = clients.iterator();
                    while(it.hasNext()){
                        Client c = it.next();
                    }
                    */
                }
            }catch (SocketException e){
                clients.remove(this);
                System.out.println("һ���ͻ����Ƴ�");
            }catch (EOFException e){    //��׽���ͻ��˹ر�ʱ���쳣���͹رշ�����
                System.out.println("�ͻ��˹ر��ˣ�");
            }
            catch (IOException e) {     //��������ֱ�Ӵ�ӡ
                e.printStackTrace();
            }finally {  //���һ��ִ�йر���Դ
                try {
                    if(dis != null) dis.close();
                    if(dos != null) dos.close();
                    if(s != null) s.close();

                } catch (IOException e1) {
                    e1.printStackTrace();
                }


            }
        }
    }
}