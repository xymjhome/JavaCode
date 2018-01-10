import java.util.Arrays;

public class Demo_FuncParam {
    public static void main(String[] args) {
        int[] arr = {100,3,4,666,77,29};
        System.out.println(Arrays.toString(arr));
        Arrays.sort(arr);
        System.out.println(Arrays.toString(arr));

        int i = 100;
        Integer bigI = i;
        System.out.println(bigI);

        //-127到127是byte的取值范围，如果在这个取值范围内，自动装箱不会新建对象，而是冲常量池中进行获取
        //超过byte的范围就会在新创建对象
        Integer i1 = 127;
        Integer i2 = 127;
        System.out.println(i1 == i2); //true
        System.out.println(i1.equals(i2));//true
        System.out.println("**********************");
        Integer i3 = 128;
        Integer i4 = 128;
        System.out.println(i3 == i4);//false
        System.out.println(i3.equals(i4));//true
        System.out.println("**********************");
        //bubleSort(arr);
        //pritf(arr);

    }

    private static void pritf(int[] arr) {
        for (int i: arr) {
            System.out.print(i + "   ");
        }
    }

    //参数是数组，是引用类型，可以改变数组内的值
    public static void bubleSort(int[] arr)
    {
        int n = arr.length;
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
               if(arr[j] > arr[j+1])
               {
                   swap(arr,j,j+1);
               }
            }
        }
    }

    private static void swap(int[] arr, int i, int j) {
        int tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }
}
