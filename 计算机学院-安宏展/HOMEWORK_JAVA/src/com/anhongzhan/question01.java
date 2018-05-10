/**
 * 按步长k输出数组中元素。已输出的元素要跳过去，不要重复输出。
 */
package com.anhongzhan;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class question01 {
    public static void main(String[] args){
    
        @SuppressWarnings("resource")
        Scanner scanner = new Scanner(System.in);
        System.out.print("请输入xxx个整数：");
        int n = scanner.nextInt();//数组长度
        System.out.print("请输入步长：");
        int x = scanner.nextInt();//步长
        List<c> list = new ArrayList<>();
        int i=0;
        while(i<n){
            list.add(new c(scanner.nextInt(),false));
            i++;
        }
        
        System.out.print("数组的内容是：");
        for(int j=0;j<n;j++)
            System.out.print(list.get(j).number+" ");
        System.out.println();
        
        System.out.print("按步长输出的序列是： ");
        
        int k=0;//已经输出的个数
        int now = 0;//当前所在位置
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
