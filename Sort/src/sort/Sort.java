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
        //bubblingSort(a);
        //quickSort(a, 0, a.length -1);
        //selectSort(a);
        insertSort(a);
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
    public static void bubblingSort(int[] number) {
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


    public static void quickSort(int[] number, int low, int high) {
        if (low < high) {
            int mid = getMiddle(number, low, high);
            quickSort(number, low, mid - 1);
            quickSort(number, mid + 1, high);
        }
    }

    /**
     * 快排
     *
     * @param numbers 数组
     * @param low     低位
     * @param high    高位
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
        numbers[low] = temp; //中轴记录到尾
        return low;
    }

    /**
     * 选择排序
     * 在未排序序列中找到最小元素，存放到排序序列的起始位置
     *
     * @param numbers
     */
    private static void selectSort(int[] numbers) {
        int temp;
        for (int i = 0; i < numbers.length; i++) {
            int k = i;
            for (int j = numbers.length - 1; j > i; j--) {
                if (numbers[j] < numbers[k]) {
                    k = j;
                }
            }
            temp = numbers[i];
            numbers[i] = numbers[k];
            numbers[k] = temp;
        }
    }

    /**
     * 插入排序
     * <p>
     * 从第一个元素开始，该元素可以认为已经被排序
     * 取出下一个元素，在已经排序的元素序列中从后向前扫描
     * 如果该元素（已排序）大于新元素，将该元素移到下一位置
     * 重复步骤3，直到找到已排序的元素小于或者等于新元素的位置
     * 将新元素插入到该位置中
     * 重复步骤2
     *
     * @param numbers 待排序数组
     */
    private static void insertSort(int[] numbers) {
        int temp;
        int j;
        for (int i = 0; i < numbers.length; i++) {
            temp = numbers[i];
            for (j = i; j > 0 && temp < numbers[j - 1]; j--) {
                numbers[j] = numbers[j - 1];
            }
            numbers[j] = temp;
        }
    }






    public static void printArr(int[] number) {
        for (int i = 0; i < number.length; i++) {
            System.out.print(number[i] + " ");
        }
        System.out.println();
    }
}
