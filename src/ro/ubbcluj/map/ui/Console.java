package ro.ubbcluj.map.ui;

import ro.ubbcluj.map.domain.Prietenie;
import ro.ubbcluj.map.domain.Retea;
import ro.ubbcluj.map.domain.Utilizator;
import ro.ubbcluj.map.domain.validators.ValidationException;
import ro.ubbcluj.map.service.PrieteniiService;
import ro.ubbcluj.map.service.UtilizatorService;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;

public class Console {
    UtilizatorService utilizatorService;
    PrieteniiService prieteniiService;

    public Console(UtilizatorService utilizatorService, PrieteniiService prieteniiService) {
        this.utilizatorService = utilizatorService;
        this.prieteniiService = prieteniiService;
    }

    public void run_meniu() {
        int ok = 1;

        while (ok == 1) {
            System.out.println("1.Adaugare utilizator.");
            System.out.println("2.Stergere utilizator.");
            System.out.println("3.Toti utilizatorii.");
            System.out.println("4.Adaugare prietenie.");
            System.out.println("5.Stergere prietenie.");
            System.out.println("6.Toate prieteniile.");
            System.out.println("7.Numarul de comunitati.");
            System.out.println("8.Cea mai sociabila comunitate.");
            System.out.println("0.Iesire.");

            System.out.println("Dati optiunea:");

            String optiune = null;
            Scanner S = new Scanner(System.in);
            optiune = S.nextLine();

            if (Objects.equals(optiune, "1")) {
                uiAddUser();

            } else if (Objects.equals(optiune, "2")) {
                uiDeleteUser();
            } else if (Objects.equals(optiune, "3")) {
                uiAllUsers();
            } else if (Objects.equals(optiune, "4")) {
                uiAddFriendship();
            } else if (Objects.equals(optiune, "5")) {
                uiDeleteFriendship();
            } else if (Objects.equals(optiune, "6")) {
                uiAllFriendships();
            } else if (Objects.equals(optiune, "7")) {
                uiNrComunitati();
            } else if (Objects.equals(optiune, "8")) {
                uiComunitateSociabila();
            } else if (Objects.equals(optiune, "0")) {
                ok = 0;
            } else System.out.println("Optiune invalida.");
        }
    }

    public void uiAddUser() {
        Scanner S = new Scanner(System.in);
        try {
            System.out.println("first name=: ");
            String firstName = null;
            firstName = S.nextLine();
            System.out.println("last name=:");
            String lastName = null;
            lastName = S.nextLine();
//            System.out.println("id=:");
//            long id = 0;
//            id = S.nextInt();
            Utilizator u = new Utilizator(firstName, lastName);
//            u.setId(id);
            utilizatorService.addUser(u);
        } catch (ValidationException e) {
            System.out.println(e.toString());
        }
    }

    public void uiDeleteUser() {
        Scanner S = new Scanner(System.in);
        try {
            System.out.println("id=:");
            long id = 0;
            id = S.nextInt();
            utilizatorService.deleteUser(id);
        } catch (ValidationException e) {
            System.out.println(e.toString());
        }
    }

    public void uiAllUsers() {
        utilizatorService.getAll().forEach(System.out::println);
    }

    public void uiAddFriendship() {
        Scanner S = new Scanner(System.in);
        try {
//            System.out.println("id prietenie=:");
//            long id = 0;
//            id = S.nextInt();
            System.out.println("id1=:");
            long id1 = 0;
            id1 = S.nextInt();
            System.out.println("id2=:");
            long id2 = 0;
            id2 = S.nextInt();
            Prietenie p = new Prietenie(id1, id2);
//            p.setId(id);
            prieteniiService.addUser(p);
        } catch (ValidationException e) {
            System.out.println(e.toString());
        }
    }

    public void uiDeleteFriendship() {
        Scanner S = new Scanner(System.in);
        try {
            System.out.println("id=:");
            long id = 0;
            id = S.nextInt();
            prieteniiService.deleteUser(id);
        } catch (ValidationException e) {
            System.out.println(e.toString());
        }
    }

    public void uiAllFriendships() {
        prieteniiService.getAll().forEach(System.out::println);
    }

    public void uiNrComunitati() {
        List<Prietenie> l = new ArrayList<>();
        for (Prietenie p : prieteniiService.getAll())
            l.add(p);
        Retea r = new Retea(utilizatorService.getAll(), l);
        //System.out.println(r.getNrNoduri());
        System.out.println("Numarul comunitatilor:");
        System.out.println(r.nrComponenteConexe());
    }

    public void uiComunitateSociabila() {
        List<Prietenie> l = new ArrayList<>();
        for (Prietenie p : prieteniiService.getAll())
            l.add(p);
        Retea r = new Retea(utilizatorService.getAll(), l);
        List<Utilizator> comunitateSociabila = r.comunitateSociabila();
        System.out.println("Utilizatorii din cea mai sociabila comunitate:");
        for (Utilizator i : comunitateSociabila)
            System.out.println(i);
    }

}
