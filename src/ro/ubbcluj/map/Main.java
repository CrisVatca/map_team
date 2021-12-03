package ro.ubbcluj.map;

import ro.ubbcluj.map.domain.Prietenie;
import ro.ubbcluj.map.domain.Retea;
import ro.ubbcluj.map.domain.Utilizator;
import ro.ubbcluj.map.domain.validators.PrietenieValidator;
import ro.ubbcluj.map.domain.validators.UtilizatorValidator;
import ro.ubbcluj.map.domain.validators.ValidationException;
import ro.ubbcluj.map.repository.Repository;
import ro.ubbcluj.map.repository.db.PrietenieDB;
import ro.ubbcluj.map.repository.db.UtilizatorDB;
import ro.ubbcluj.map.repository.file.PrietenieFile;
import ro.ubbcluj.map.repository.file.UtilizatorFile;
import ro.ubbcluj.map.service.PrieteniiService;
import ro.ubbcluj.map.service.UtilizatorService;
import ro.ubbcluj.map.ui.Console;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        // write your code here

        /*Repository<Long, Utilizator> repo = new UtilizatorFile("data/users.csv",new UtilizatorValidator());
        repo.findAll().forEach(System.out::println);
        //repo.findAll().forEach(x->System.out.println(x));

        Utilizator u1 = new Utilizator("Andreea","Suteu");
        u1.setId(5L);
        repo.save(u1);

        Utilizator u2 = new Utilizator("DA","Da");
        u2.setId(8L);
        repo.save(u2);

        try{
            repo.delete(u2.getId());
        }catch (ValidationException e){
            System.out.println(e.toString());
        }


        System.out.println("-------------------------");
        repo.findAll().forEach(System.out::println);

        Utilizator u5 = new Utilizator("sf","sfs");
        u5.setId(23L);
        List<Utilizator> l1 = new ArrayList<>();
        l1.add(u2);
        l1.add(u1);
        u5.setFriends(l1);
        repo.save(u5);*/



        /*Repository<Long, Prietenie> repo1 = new PrietenieFile("data/prietenii.csv",new PrietenieValidator());
        repo1.findAll().forEach(System.out::println);
        //repo.findAll().forEach(x->System.out.println(x));

        Prietenie p1 = new Prietenie(1L,2L);
        p1.setId(3L);
        repo1.save(p1);

        Prietenie p2 = new Prietenie(6L,8L);
        p2.setId(6L);
        try {
            repo1.save(p2);
        }catch (ValidationException e){
            System.out.println(e.toString());
        }

        Prietenie p3 = new Prietenie(2L,3L);
        p3.setId(14L);
        Prietenie p4 = new Prietenie(7L,2L);
        p4.setId(24L);
        Prietenie p5 = new Prietenie(9L,4L);
        p5.setId(34L);

        List<Prietenie> lp = new ArrayList<>();
        lp.add(p1);
        lp.add(p2);
        lp.add(p3);
        lp.add(p4);
        lp.add(p5);
        Retea r = new Retea(0,lp);
        System.out.println(r.getNrNoduri());
        System.out.println(r.nrComponenteConexe());

        UtilizatorService s1 = new UtilizatorService(repo);
        PrieteniiService s2 = new PrieteniiService(repo1,repo);

        Utilizator u10 = new Utilizator("DscA","Dasa");
        u10.setId(38L);
        s1.addUser(u10);
        s1.deleteUser(u10.getId());
        System.out.println("service user");
        System.out.println(s1.getAll());

        Console c= new Console(s1,s2);
        c.run_meniu();*/

        //repo.delete(u2.getId());

        //repo.findAll().forEach(System.out::println);

        /*Utilizator u4 = new Utilizator("DAp","Dap");
        u4.setId(18L);
        repo.save(u4);

        try {
            Utilizator u3 = new Utilizator("D5A","Da");
            u3.setId(12L);
            repo.save(u3);
        }catch (ValidationException e){
            System.out.println(e.toString());
        }*/

        /*Repository<Long, Utilizator> repo = new UtilizatorFile("data/users.csv", new UtilizatorValidator());
        Repository<Long, Prietenie> repo1 = new PrietenieFile("data/prietenii.csv", new PrietenieValidator());

        UtilizatorService s1 = new UtilizatorService(repo, repo1);
        PrieteniiService s2 = new PrieteniiService(repo1, repo);

        Console c = new Console(s1, s2);
        c.run_meniu();*/

        /*Repository<Long,Utilizator> repoDB = new UtilizatorDB("jdbc:postgresql://localhost:5432/academic",
                "postgres","Andreea10",new UtilizatorValidator());
        repoDB.findAll().forEach(System.out::println);

//        Utilizator u1 = new Utilizator("popa","maria");
//        repoDB.save(u1);*/

        Repository<Long,Prietenie> repoDBPrietenie = new PrietenieDB("jdbc:postgresql://localhost:5432/academic",
                "postgres","Andreea10",new PrietenieValidator());

//        Prietenie p1 = new Prietenie(1L,3L);
//        repoDBPrietenie.save(p1);

        repoDBPrietenie.findAll().forEach(System.out::println);


        /*Repository<Long,Utilizator> repo1 = new UtilizatorDB("jdbc:postgresql://localhost:5432/academic",
                "postgres","Andreea10",new UtilizatorValidator());
        Repository<Long,Prietenie> repo2 = new PrietenieDB("jdbc:postgresql://localhost:5432/academic",
                "postgres","Andreea10",new PrietenieValidator());
        UtilizatorService s1 = new UtilizatorService(repo1, repo2);
        PrieteniiService s2 = new PrieteniiService(repo2, repo1);

        Console c = new Console(s1, s2);
        c.run_meniu();*/


    }
}
