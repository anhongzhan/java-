/**
 * ���߳�����
 */

package com.anhongzhan;

/**
 * ���ֱ�Ӽ���׳˺͵Ļ�̫���ˣ����Լ����ʱ��ÿ��0.1�����һ�Σ�ÿ��1.0���ȡһ��
 * @author AnHongzhan
 */
public class question05 {

    public static void main(String[] args) {
        ComputeThread computeThread = new ComputeThread();
        computeThread.start();
        ReadThread readThread = new ReadThread();
        readThread.start();
    }
}

class ComputeThread extends Thread {// ����׳�
    double sum = 0;// �洢�׳˺�
    static int i = 0;// �洢�׳˺ͽ���
    static String stringSum = "";// �洢�׳˺͵��ַ���
    static String stringResult = "";// �洢�׳˽�����ַ���

    double method(int n) {// ����׳�
        double result = 1;// �׳˽��
        for (int i = 1; i <= n; i++) {
            result *= i;
        }
        return result;
    }

    @Override
    public void run() {
        // TODO Auto-generated method stub

        while (i < 30) {// ����׳˺�
            i++;
            sum += method(i);
            stringResult = String.valueOf(sum);// ���׳˺ʹ洢���ַ�����
            
           try {// 0.5-1���ȡһ���߳�
                Thread.sleep((int) (Math.random() * 500 + 500));
            } catch (InterruptedException ex) {

            }

        }

    }
}

class ReadThread extends Thread {// ��ȡ�׳�
    public ReadThread() {// ��ʼ��

    }

    @Override
    public void run() {
        // TODO Auto-generated method stub
        while (true) {
            System.out.println(ComputeThread.stringResult);
            try {
                Thread.sleep(1000);// 1.0���ȡһ���߳�
            } catch (Exception e) {
                // TODO: handle exception
            }
        }
    }
}