package gen;

public class Main {
    static String start = "https://daohang.qq.com/";
    public static void main(String[] args) {
        final long startTime = System.currentTimeMillis();

        new Search(start);

        final long endTime = System.currentTimeMillis();
        System.out.println("Finished in: " + (endTime - startTime)/1000 );
    }

}
