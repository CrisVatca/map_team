package ro.ubbcluj.map.domain;

import java.util.ArrayList;
import java.util.List;

public class Retea {
    private Iterable<Utilizator> users;
    private List<Prietenie> relatii;

    public Retea(Iterable<Utilizator> users, List<Prietenie> relatii) {
        this.users = users;
        this.relatii = relatii;
    }

    public int getNrNoduri() {
        long[] l = new long[relatii.size() * 2];
        int nr = 0;
        for (Prietenie p : relatii) {
            int ok1 = 0, ok2 = 0;
            long idU1 = 0, idU2 = 0;
            if (p.getId1() != null)
                idU1 = p.getId1();
            if (p.getId2() != null)
                idU2 = p.getId2();
            for (long i : l) {
                if (p.getId1() != null && p.getId1() == i)
                    ok1 = 1;
                if (p.getId2() != null && p.getId2() == i)
                    ok2 = 1;
            }
            if (ok1 == 0) {
                l[nr++] = idU1;
            }
            if (ok2 == 0) {
                l[nr++] = idU2;
            }
        }
        return nr;
    }

    public int nrComponenteConexe() {
        List<Prietenie> rel = relatii;
        int nrNod = getNrNoduri();
        int cont = 0;

        for (Prietenie p : rel) {
            long[] l = new long[nrNod];
            int nr = 1;
            if (p.getId1() != null) {
                l[0] = p.getId1();
                p.setId1(null);
            }

            if (p.getId2() != null) {
                l[1] = p.getId2();
                p.setId2(null);
            }
            for (long j : l) {
                for (Prietenie p1 : rel) {
                    if (p1.getId1() != null && j == p1.getId1()) {
                        l[nr++] = p1.getId2();
                        p1.setId2(null);
                        p1.setId1(null);

                    }
                    if (p1.getId2() != null && j == p1.getId2()) {
                        l[nr++] = p1.getId1();
                        p1.setId2(null);
                        p1.setId1(null);
                    }

                }

            }
            if (l[0] != 0)
                cont++;

        }
        return cont;
    }

    public List<Utilizator> comunitateSociabila() {
        List<Prietenie> rel = relatii;
        int nrNod = getNrNoduri();
        int cont = 0;
        long[] lista_finala = new long[nrNod];
        int maxim = 0;

        for (Prietenie p : rel) {
            long[] l = new long[nrNod];
            int nr = 2;
            if (p.getId1() != null) {
                l[0] = p.getId1();
                p.setId1(null);
            }

            if (p.getId2() != null) {
                l[1] = p.getId2();
                p.setId2(null);
            }
            for (long j : l) {
                for (Prietenie p1 : rel) {
                    if (p1.getId1() != null && j == p1.getId1()) {
                        l[nr++] = p1.getId2();
                        p1.setId2(null);
                        p1.setId1(null);
                    }
                    if (p1.getId2() != null && j == p1.getId2()) {
                        l[nr++] = p1.getId1();
                        p1.setId2(null);
                        p1.setId1(null);
                    }
                }
            }
            cont = 0;
            for (long i : l)
                if (i != 0) {
                    cont++;
                }
            int nrListaNoua = 0;
            if (cont > maxim) {
                lista_finala = new long[cont];
                maxim = cont;
                for (long i : l) {
                    if (i != 0)
                        lista_finala[nrListaNoua++] = i;
                }
            }
        }
        List<Utilizator> comunitateFinala = new ArrayList<>();
        for (long i : lista_finala)
            for (Utilizator u : users)
                if (u.getId() == i)
                    comunitateFinala.add(u);
        return comunitateFinala;
    }
}
