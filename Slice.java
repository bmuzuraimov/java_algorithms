// Driver class
class Slice {
    public static void main(String[] args)
    {
        new Slice().runOnce();
    }
    void runOnce(){
        int[][] sliced_2d_array;
        int[][][] sliced_3d_array;
        int r1, r2, c1, c2, w, h;
        int[][] to_2d_arr = {{11, 12, 13, 14},
                {21, 22, 23, 24},
                {31, 32, 33, 34}};

        int[][] to_3d_arr = {{11, 12, 13, 14, 15, 16},
                {21, 22, 23, 24, 25, 26},
                {31, 32, 33, 34, 35, 36},
                {41, 42, 43, 44, 45, 46}};

        System.out.println("Original Array looks like:");
        print_array(to_2d_arr);
        r1 = 1; r2 = 2; c1 = 0; c2 = 2;
        sliced_2d_array = slice_2d_array(to_2d_arr, r1, c1, r2, c2);
        print_array(sliced_2d_array, r1, c1, r2, c2);
        r1 = 1; r2 = 1; c1 = 1; c2 = 3;
        sliced_2d_array = slice_2d_array(to_2d_arr, r1, c1, r2, c2);
        print_array(sliced_2d_array, r1, c1, r2, c2);
        w = 3; h = 2;
        sliced_3d_array = slice_3d_array(to_3d_arr, h, w);
        print_array(sliced_3d_array, w, h);
        w = 6; h = 2;
        sliced_3d_array = slice_3d_array(to_3d_arr, 2, 6);
        print_array(sliced_3d_array, w, h);
    }
    public int[][] slice_2d_array(int[][] array, int r1, int c1, int r2, int c2){
        int col_length = (c2 - c1 > 0) ? array[0].length - (c2 - c1)+1 : 1;
        int row_length = (r2 - r1 > 0) ? array.length - (r2 - r1) : 1;
        int[][] new_array = new int[row_length][col_length];
        for(int i = r1, row_cnt = 0; i <= r2; i++, row_cnt++){
            for(int j = c1, col_cnt = 0; j <= c2; j++, col_cnt++){
                new_array[row_cnt][col_cnt] = array[i][j];
            }
        }
        return new_array;
    }
    public int[][][] slice_3d_array(int[][] array, int height, int width){
        // Slices = total elements divided by boxes elements in box (height * width)
        int slices = (array.length * array[0].length) / (height * width);
        int[][][] new_array = new int[slices][height][width];
        for(int dim_1 = 0, col_start = 0, row_start = 0, sign = 1; dim_1 < new_array.length; dim_1++){
            for(int dim_2 = 0, row = row_start; dim_2 < height; dim_2++, row++){
                for(int dim_3 = 0, col = col_start; dim_3 < width; dim_3++){
                    new_array[dim_1][dim_2][dim_3] = array[row][col++];
                }
            }
            col_start += (array[0].length-width) * sign;
            sign *= -1;
            row_start = ((dim_1+1) * width / array[0].length) * 2;
        }
        return new_array;
    }
    void print_array(int[][] array, int r1, int c1, int r2, int c2){
        System.out.printf("Slice from (%d, %d) to (%d, %d)\n", r1, c1, r2, c2);
        print_array(array);
    }
    void print_array(int[][] array){
        for(int[] i : array){
            for(int j : i)
                System.out.print(j+" ");
            System.out.println();
        }
        System.out.println();
    }
    void print_array(int[][][] array, int width, int height){
        System.out.printf("Slice the array into %d-by-%d arrays.\n", height, width);
        int cnt = 0;
        for(int[][] i : array){
            System.out.println("----- Slice "+cnt++ + "-----");
            for(int[] j : i){
                for(int k : j){
                    System.out.print(k+" ");
                }
                System.out.println();
            }
        }
        System.out.println();
    }
}
