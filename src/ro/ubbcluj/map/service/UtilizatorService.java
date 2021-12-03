package ro.ubbcluj.map.service;

import ro.ubbcluj.map.domain.Prietenie;
import ro.ubbcluj.map.domain.Utilizator;
import ro.ubbcluj.map.domain.validators.ValidationException;
import ro.ubbcluj.map.repository.Repository;

import java.util.ArrayList;
import java.util.List;

public class UtilizatorService {
    private Repository<Long, Utilizator> repo;
    private Repository<Long, Prietenie> repo_prietenie;

    public UtilizatorService(Repository<Long, Utilizator> repo, Repository<Long, Prietenie> repo_prietenie) {
        this.repo = repo;
        this.repo_prietenie = repo_prietenie;
    }

    public Utilizator addUser(Utilizator user) {
        int ok = 0;
        for (Utilizator u : repo.findAll()) {
            if (u.getId() == user.getId())
                ok = 1;
        }

        if (ok == 1)
            throw new ValidationException("This id already exists.");

        Utilizator task = repo.save(user);

        return task;
    }

    public Utilizator deleteUser(long id) {
        Utilizator task = repo.delete(id);
        List<Long> l = new ArrayList<>();
        for (Prietenie p : repo_prietenie.findAll()) {
            if (p.getId1() == id) {
                l.add(p.getId());
            }

            if (p.getId2() == id) {
                l.add(p.getId());
            }
        }
        for (long i : l)
            repo_prietenie.delete(i);

        return task;
    }

    public Iterable<Utilizator> getAll() {
        return repo.findAll();
    }

}