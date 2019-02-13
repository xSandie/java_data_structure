class Sum {
    public static int sum(int[] arr){
        return sum(arr,0);
    }

    //arr[l,n)，长度为n
    private static int sum(int[] arr, int l){
        if (arr.length == l){
//            l是最后一个元素后的一个元素
            return 0;
        }
        return arr[l] + sum(arr,l+1);
    }

    public static void main(String[] args){
        int[] arr = {1,2,3,4,5,6};
        System.out.println(sum(arr));
    }
}