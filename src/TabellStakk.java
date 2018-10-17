import java.util.*;

public class TabellStakk<T> implements Stakk<T> {
    private T[] a;                     // en T-tabell
    private int antall;                // antall verdier på stakken

    public TabellStakk()               // konstruktør - tabellengde 8
    {
        this(8);
    }

    @SuppressWarnings("unchecked")     // pga. konverteringen: Object[] -> T[]
    public TabellStakk(int lengde)     // valgfri tabellengde
    {
        if (lengde < 0)
            throw new IllegalArgumentException("Negativ tabellengde!");

        a = (T[]) new Object[lengde];     // oppretter tabellen
        antall = 0;                      // stakken er tom
    }

    @Override
    public void leggInn(T verdi) {
        if (antall == a.length) {
            a = Arrays.copyOf(a, antall == 0 ? 1 : antall * 2);
        }
        a[antall] = verdi;
        antall++;
    }

    @Override
    public T kikk() {
        if (antall == 0) {
            throw new NoSuchElementException("Stakken er tom");
        }
        return a[antall - 1];
    }

    @Override
    public T taUt() {
        if (antall == 0) {
            throw new NoSuchElementException("Stakken er tom");
        }
        antall--;
        T temp = a[antall];
        a[antall] = null;
        return temp;
    }

    @Override
    public int antall() {
        return antall;
    }

    @Override
    public boolean tom() {
        return antall == 0;
    }

    @Override
    public void nullstill() {
        while (antall > 0) {
            a[antall - 1] = null;
            antall--;
        }
    }

    public String toString() {
        StringJoiner s = new StringJoiner(", ", "[", "]");
        for (int i = antall - 1; i >= 0; i--)
            s.add(a[i] == null ? "null" : a[i].toString());
        return s.toString();
    }


    public void snu() {
        TabellStakk<T> b = new TabellStakk<>();
        int n = this.antall - 1;

        while (n > 0) {
            T t = this.taUt();
            for(int i = 0; i < n; i++) {
                b.leggInn(this.taUt());
            }
            b.leggInn(t);
            while (!b.tom()) this.leggInn(b.taUt());
            n--;
        }
    }


    public void kopier(TabellStakk<T> b) {
        T t;
        int n = this.antall;
        while (n > 0) {
            for(int i = 0; i < n; i++) {
                b.leggInn(this.taUt());
            }
            t = b.kikk();
            for(int i = 0; i < n; i++) {
                this.leggInn(b.taUt());
            }
            b.leggInn(t);
            n--;
        }
    }

    public void sorter(Comparator<? super T> c) {
        TabellStakk<T> b = new TabellStakk<>(antall);
        T maks;
        int n = antall;

        while (n > 0) {
            maks = this.taUt();

            for (int i = 0; i < n-1; i++) {
                if(c.compare(this.kikk(),maks) > 0) {
                    b.leggInn(maks);
                    maks = this.taUt();
                }
                else {
                    b.leggInn(this.taUt());
                }
            }
            this.leggInn(maks);
            while (!b.tom()) this.leggInn(b.taUt());
            n--;
        }
    }
}
