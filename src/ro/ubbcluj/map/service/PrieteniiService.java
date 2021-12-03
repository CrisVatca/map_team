package ro.ubbcluj.map.service;

import ro.ubbcluj.map.domain.Prietenie;
import ro.ubbcluj.map.domain.Utilizator;
import ro.ubbcluj.map.domain.validators.ValidationException;
import ro.ubbcluj.map.repository.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class PrieteniiService {
    private Repository<Long, Prietenie> repo;
    private Repository<Long, Utilizator> repo_utilizatori;

    public PrieteniiService(Repository<Long, Prietenie> repo, Repository<Long, Utilizator> repo_utilizatori) {
        this.repo = repo;
        this.repo_utilizatori = repo_utilizatori;
    }

    Utilizator u1,u2;
    public Prietenie addUser(Prietenie user) {
        int ok1 = 0, ok2 = 0;
        for (Utilizator u : repo_utilizatori.findAll()) {
            if (Objects.equals(u.getId(), user.getId1())) {
                ok1 = 1;
                u1=u;
            }
            if (Objects.equals(u.getId(), user.getId2())) {
                ok2 = 1;
                u2=u;
            }
        }

        for (Prietenie p : repo.findAll()) {
            if (Objects.equals(user.getId2(), p.getId1()) && Objects.equals(user.getId1(), p.getId2()))
                throw new ValidationException("This friendship already exists.");
            if (Objects.equals(user.getId1(), p.getId1()) && Objects.equals(user.getId2(), p.getId2()))
                throw new ValidationException("This friendship already exists.");
        }

        if (ok1 != 1) {
            throw new ValidationException("The id of the first user doesn't exists.");
        }
        else if (ok2 != 1) {
            throw new ValidationException("The id of the second user doesn't exists.");
        }
        else{
            for (Utilizator u : repo_utilizatori.findAll()) {
                if (Objects.equals(u.getId(), user.getId1())) {
                    List<Utilizator> utilizators = new ArrayList<>();
                    if(u.getFriends()!=null){
                        utilizators = u.getFriends();
                    }
                    utilizators.add(u2);
                    u.setFriends(utilizators);
                    repo_utilizatori.update(u);
                }
            }
            for (Utilizator u : repo_utilizatori.findAll()) {
                if (Objects.equals(u.getId(), user.getId2())) {
                    List<Utilizator> utilizators = new ArrayList<>();
                    if(u.getFriends()!=null){
                        utilizators = u.getFriends();
                    }
                    utilizators.add(u1);
                    u.setFriends(utilizators);
                }
            }
        }

        Prietenie task = repo.save(user);

        return task;
    }

    public Prietenie deleteUser(long id) {
        Prietenie task = repo.delete(id);

        return task;
    }

    public Iterable<Prietenie> getAll() {
        return repo.findAll();
    }

}
