package concurrent;

public class Wget {
    public static void main(String[] args) {
        try {
            int index = 0;
            while (index < 101) {
                System.out.print("\rLoading : " + index++ + "%");
                Thread.sleep(1000);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
