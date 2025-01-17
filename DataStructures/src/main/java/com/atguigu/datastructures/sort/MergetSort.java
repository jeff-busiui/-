package com.atguigu.datastructures.sort;

import cn.hutool.core.date.DateUtil;
import com.atguigu.datastructures.util.CommonUtils;

/**
 * @author sunbaojin
 * @date 2020/5/17 22:15
 */
public class MergetSort {

    public static void main(String[] args) {
        int numSize = 8000000;
        // 创建一个数值
        int[] arr1 = {11, 2, 3, 8, 5, 9};
        int[] arr2 = {2, 2, 3, 6, 7, 8};
        int[] arr3 = new int[numSize];

        int[] arr4 = {2, 9, 8, 7, 5, 10, 7, 6, 9, 2, 3, 6, 7, 8};
        // 生成随机数
        for (int i = 0; i < arr3.length; i++) {
            arr3[i] = (int) (Math.random() * numSize);
        }

        // 要执行的数值
        int[] zxArr = arr3;
        boolean printFlag = false;

        CommonUtils.printString("排序前,开始时间:" + DateUtil.now());
        // 排序前
        if (printFlag) {
            CommonUtils.printIntOneArray(zxArr);
        }
        int temp[] = new int[zxArr.length]; //归并排序需要一个额外空间
        mergeSort(zxArr, 0, zxArr.length - 1,temp);
        CommonUtils.printString("排序后,结束时间:" + DateUtil.now());
        if (printFlag) {
            CommonUtils.printIntOneArray(zxArr);
        }

    }

    //分+合方法
    public static void mergeSort(int[] arr, int left, int right, int[] temp) {
        if(left < right) {
            int mid = (left + right) / 2; //中间索引
            //向左递归进行分解
            mergeSort(arr, left, mid, temp);
            //向右递归进行分解
            mergeSort(arr, mid + 1, right, temp);
            // 打印下left 和right的值
//            System.out.println("left："+left+"--->right:"+right);
            //合并
            merge(arr, left, mid, right, temp);

        }
    }

    //合并的方法
    /**
     *
     * @param arr 排序的原始数组
     * @param left 左边有序序列的初始索引
     * @param mid 中间索引
     * @param right 右边索引
     * @param temp 做中转的数组
     */
    public static void merge(int[] arr, int left, int mid, int right, int[] temp) {

        int i = left; // 初始化i, 左边有序序列的初始索引
        int j = mid + 1; //初始化j, 右边有序序列的初始索引
        int t = 0; // 指向temp数组的当前索引

        //(一)
        //先把左右两边(有序)的数据按照规则填充到temp数组
        //直到左右两边的有序序列，有一边处理完毕为止
        while (i <= mid && j <= right) {//继续
            //如果左边的有序序列的当前元素，小于等于右边有序序列的当前元素
            //即将左边的当前元素，填充到 temp数组
            //然后 t++, i++
            if(arr[i] <= arr[j]) {
                temp[t] = arr[i];
                t += 1;
                i += 1;
            } else { //反之,将右边有序序列的当前元素，填充到temp数组
                temp[t] = arr[j];
                t += 1;
                j += 1;
            }
        }

        //(二)
        //把有剩余数据的一边的数据依次全部填充到temp
        while( i <= mid) { //左边的有序序列还有剩余的元素，就全部填充到temp
            temp[t] = arr[i];
            t += 1;
            i += 1;
        }

        while( j <= right) { //右边的有序序列还有剩余的元素，就全部填充到temp
            temp[t] = arr[j];
            t += 1;
            j += 1;
        }


        //(三)
        //将temp数组的元素拷贝到arr
        //注意，并不是每次都拷贝所有
        t = 0;
        int tempLeft = left; //
        //第一次合并 tempLeft = 0 , right = 1 //  tempLeft = 2  right = 3 // tL=0 ri=3
        //最后一次 tempLeft = 0  right = 7
        while(tempLeft <= right) {
            arr[tempLeft] = temp[t];
            t += 1;
            tempLeft += 1;
        }

    }

}

