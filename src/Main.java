public class Main {

    public static void main(String[] args) {
        System.out.println("Kapittel 4 yay");

        TabellStakk<Integer> stakk = new TabellStakk<>(10);
        stakk.leggInn(2);
        stakk.leggInn(3);
        stakk.leggInn(4);
        System.out.println(stakk.toString());
    }
}
