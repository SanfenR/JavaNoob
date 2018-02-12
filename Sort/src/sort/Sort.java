package sort;


import java.util.Scanner;

public class Sort {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int num = sc.nextInt();
        int[] a = new int[num];
        for (int i = 0; i < num; i++) {
            a[i] = sc.nextInt();
        }
        printArr(a);
        quick(a, 0, a.length -1);
        printArr(a);
    }


    /**
     * 冒泡排序
     * 比较相邻的元素。如果第一个比第二个大，就交换他们两个。
     * 对每一对相邻元素作同样的工作，从开始第一对到结尾的最后一对。在这一点，最后的元素应该会是最大的数。
     * 针对所有的元素重复以上的步骤，除了最后一个。
     * 持续每次对越来越少的元素重复上面的步骤，直到没有任何一对数字需要比较。
     *
     * @param number 需要排序的整型数组
     */
    public static void bubbling(int[] number) {
        int swap;
        for (int i = 0; i < number.length - 1; i++) {
            for (int j = 0; j < number.length - 1 - i; j++) {
                if (number[j] > number[j + 1]) {
                    swap = number[j];
                    number[j] = number[j + 1];
                    number[j + 1] = swap;
                    printArr(number);
                }
            }
        }
    }


    public static void quick(int[] number, int low, int high) {
        if (low < high) {
            int mid = getMiddle(number, low, high);
            quick(number, low, mid -1);
            quick(number, mid + 1, high);
        }
    }

    /**
     * 快排
     *
     * @param numbers 数组
     * @param low 低位
     * @param high 高位
     * @return
     */
    private static int getMiddle(int[] numbers, int low, int high) {
        int temp = numbers[low];
        while (low < high) {
            while (low < high && numbers[high] >= temp) {
                high--;
            }
            numbers[low] = numbers[high];//比中轴小的记录移到低端
            while (low < high && numbers[low] < temp) {
                low++;
            }
            numbers[high] = numbers[low]; //比中轴大的记录移到高端
        }
        numbers[low] = temp ; //中轴记录到尾
        return low;
    }


    public static void printArr(int[] number) {
        for (int i = 0; i < number.length; i++) {
            System.out.print(number[i] + " ");
        }
        System.out.println();
    }
}
