public class Main1 {
    public static void main(String[] args) {
        int[][] arr = new int[5][5];
        fillRandom(arr);
        printColored(arr);
        printMinMaxAvg(arr);
    }

    private static void printMinMaxAvg(int[][] arr) {
        int min = Integer.MAX_VALUE;
        int max = Integer.MIN_VALUE;
        int avg = 0;
        int count = 0;
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[i].length; j++) {
                if(arr[i][j] < min) {
                    min = arr[i][j];
                }
                if(arr[i][j] > max) {
                    max = arr[i][j];
                }
                avg += arr[i][j];
                count++;
            }
        }
        avg /= count;
        System.out.println("Минимум: " + min);
        System.out.println("Максимум: " + max);
        System.out.println("Среднее: " + avg);
    }

    private static void printColored(int[][] arr) {
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[i].length; j++) {
                String randomColor = "\u001B[3" + getRandomInt(0, 7) + "m";
                System.out.printf(randomColor + "%3d ", arr[i][j]);
            }
            System.out.println();
        }
        // reset
        System.out.print("\u001B[37m");
    }

    private static void fillRandom(int[][] arr) {
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[i].length; j++) {
                arr[i][j] = getRandomInt(-10, 10);
            }
        }
    }

    private static int getRandomInt(int from, int to) {
        int range = to - from;
        long currentTime = System.currentTimeMillis();
        int random = Math.abs((int) currentTime ^ (int) (Math.random() * 1_000_003));
        return random % range + from + 1;
    }
}