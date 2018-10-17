import java.util.Comparator;

public class Main {

    public static void main(String[] args) {
        System.out.println("Kapittel 4 yay");

        TabellStakk<Integer> stakk = new TabellStakk<>(10);
        stakk.leggInn(2);
        stakk.leggInn(7);
        stakk.leggInn(5);
        stakk.leggInn(3);
        stakk.leggInn(1);
        stakk.leggInn(9);
        System.out.println(stakk.toString());

        stakk.sorter(Integer::compareTo);

        System.out.println(stakk.toString());

    }
}
