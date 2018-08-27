package sort;

public class Search {

    public static void main(String[] args) {

    }


    /**
     * 顺序查找
     *
     * @param searchKey
     * @param array
     * @return
     */
    public static int orderSearch(int searchKey, int[] array) {
        for (int i = 0; i < array.length; i++) {
            if (searchKey == array[i]) {
                return i;
            }
        }
        return -1;
    }

    /**
     * 二分查找又称折半查找，它是一种效率较高的查找方法。
     * 【二分查找要求】：1.必须采用顺序存储结构 2.必须按关键字大小有序排列。
     *
     * @param array     有序数组 *
     * @param searchKey 查找元素 *
     * @return searchKey的数组下标，没找到返回-1
     */
    public static int binarySearch(int searchKey, int[] array) {
        int low = 0;
        int high = array.length - 1;
        while (low <= high) {
            int middle = (low + high) / 2;
            if (searchKey == array[middle]) {
                return middle;
            } else if (searchKey < array[middle]) {
                high = middle - 1;
            } else {
                low = middle + 1;
            }
        }
        return -1;
    }


}
