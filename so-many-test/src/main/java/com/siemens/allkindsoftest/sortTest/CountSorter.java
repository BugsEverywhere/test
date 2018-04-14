package com.siemens.allkindsoftest.sortTest;

/**
 * Created by Chen Zhuo on 2017/10/31.
 */
public class CountSorter {

    public static void main(String[] args){

        int[] array = {1,4,6,5,9,1,1,2,2,8,5,3,1};

        sort(array);

    }

    public static void sort(int[] a) {
        int len = a.length;
        final int MAX = 256;
        int[] c = new int[MAX];
        int[] b = new int[MAX];

        printArray("Input array: ",a);
        System.out.println("pause");

        //此处c数组各个位置的元素记录了该位置下标的数字在a数组中出现的次数
        for (int i = 0; i < len; i++) {
            c[a[i]]++;
        }

        printArray("Input array: ",a);
        printArray("Array c: ",c);
        System.out.println("pause");

        //此时c数组各个位置的元素记录了该位置下标的数字及比它小的所有数字在a数组中出现的次数之和
        for (int i = 1; i < MAX; i++) {
            c[i] += c[i-1];
        }

        printArray("Input array: ",a);
        printArray("Array c: ",c);
        System.out.println("pause");

        //因为此时c数组上每一个元素的值代表该元素下标的数字以及小于它的所有数字出现过的次数之和，
        //因此对于a数组中任何一个数字x，其在排序后的新数组中最后一次出现肯定就在c[x]-1的位置，
        //将新数组中该位置填上该数字后，将c[x]减去一个，代表下次再出现该数字，则是该数字在新数组
        //中倒数第二次出现，比刚才这次往前提了一位。这样就按照所有数字出现的次数，将他们从小大大
        //填入了新数组
        for (int i = len - 1; i >= 0; i--) {
            b[c[a[i]] - 1] = a[i];
            c[a[i]]--;
        }

        printArray("Input array: ",a);
        printArray("Array c: ",c);
        printArray("Array b: ",b);
        System.out.println("pause");


        for (int i = 0; i < len; i++) {
            a[i] = b[i];
        }

        printArray("Output array: ",a);
        printArray("Array c: ",c);
        printArray("Array b: ",b);
        System.out.println("pause");
    }

    public static void printArray(String annotation,int[] inputArray){

        System.out.print(annotation+": ");

        for(int singleNum:inputArray){

            System.out.print(singleNum+",");

        }

        System.out.print("\n");

    }
}
