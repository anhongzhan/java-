/**
 * ������k���������Ԫ�ء��������Ԫ��Ҫ����ȥ����Ҫ�ظ������
 */
package com.anhongzhan;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class question01 {
    public static void main(String[] args){
    
        @SuppressWarnings("resource")
        Scanner scanner = new Scanner(System.in);
        System.out.print("������xxx��������");
        int n = scanner.nextInt();//���鳤��
        System.out.print("�����벽����");
        int x = scanner.nextInt();//����
        List<c> list = new ArrayList<>();
        int i=0;
        while(i<n){
            list.add(new c(scanner.nextInt(),false));
            i++;
        }
        
        System.out.print("����������ǣ�");
        for(int j=0;j<n;j++)
            System.out.print(list.get(j).number+" ");
        System.out.println();
        
        System.out.print("����������������ǣ� ");
        
        int k=0;//�Ѿ�����ĸ���
        int now = 0;//��ǰ����λ��
        while(k<n)
        {
            for(int z=0;z<x-1;z++)
            {
                while(list.get(now).isVisited)
                    now = (now+1)%n;
                now = (now+1)%n;
                while(list.get(now).isVisited)
                    now = (now+1)%n;
            }
            k++;
            System.out.print(list.get(now).number+" ");
            list.get(now).isVisited = true;
        }
    }
    
}

class c
{
    int number;
    boolean isVisited;
    
    public c(int number,boolean isVisited){
        this.number = number;
        this.isVisited = isVisited;
        
    }
}
