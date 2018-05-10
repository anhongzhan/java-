/**
 * 多线程问题
 */

package com.anhongzhan;

/**
 * 如果直接计算阶乘和的话太大了，所以计算得时候每隔0.1秒计算一次，每隔1.0秒读取一次
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

class ComputeThread extends Thread {// 计算阶乘
    double sum = 0;// 存储阶乘和
    static int i = 0;// 存储阶乘和进度
    static String stringSum = "";// 存储阶乘和的字符串
    static String stringResult = "";// 存储阶乘结果的字符串

    double method(int n) {// 计算阶乘
        double result = 1;// 阶乘结果
        for (int i = 1; i <= n; i++) {
            result *= i;
        }
        return result;
    }

    @Override
    public void run() {
        // TODO Auto-generated method stub

        while (i < 30) {// 计算阶乘和
            i++;
            sum += method(i);
            stringResult = String.valueOf(sum);// 将阶乘和存储到字符串中
            
           try {// 0.5-1秒读取一次线程
                Thread.sleep((int) (Math.random() * 500 + 500));
            } catch (InterruptedException ex) {

            }

        }

    }
}

class ReadThread extends Thread {// 读取阶乘
    public ReadThread() {// 初始化

    }

    @Override
    public void run() {
        // TODO Auto-generated method stub
        while (true) {
            System.out.println(ComputeThread.stringResult);
            try {
                Thread.sleep(1000);// 1.0秒读取一次线程
            } catch (Exception e) {
                // TODO: handle exception
            }
        }
    }
}