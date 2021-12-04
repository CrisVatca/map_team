package service;

import domain.Prietenie;
import domain.Utilizator;
import repository.db.PrietenieDbRepository;
import repository.db.UtilizatorDbRepository;
import repository.file.AbstractFileRepository;
import utils.Graf;

import java.util.List;
import java.util.Objects;

public class Service {
    private final AbstractFileRepository<Long, Utilizator> repository;
    private PrietenieDbRepository repoPrietenie;
    private UtilizatorDbRepository repoUtilizator;

    public Service(AbstractFileRepository<Long, Utilizator> repository,PrietenieDbRepository repoPrietenie,UtilizatorDbRepository repoUtilizator) {
        this.repository = repository;
        this.repoPrietenie = repoPrietenie;
        this.repoUtilizator = repoUtilizator;
    }

    public void saveUtilizator(String firstName, String lastName) {
        Utilizator utilizator = new Utilizator(firstName, lastName);
        this.repository.save(utilizator);
        this.repoUtilizator.save(utilizator);
    }

    public void deleteUtilizator(Long id) {
        try {
            this.repository.delete(id);
            this.repoUtilizator.delete(id);
        } catch (IllegalArgumentException e) {
            throw e;
        }
    }


    public Iterable<Utilizator> printAll() {
        return this.repository.findAll();
    }

    public void addFriend(Long id1, Long id2, Long id) {
        try {
            boolean ok = true;
            if (!Objects.equals(id1, id2)) {
                Utilizator utilizator1 = this.repository.findOne(id1);
                Utilizator utilizator2 = this.repository.findOne(id2);
                for (Utilizator user : utilizator1.getFriends()) {
                    if (Objects.equals(user.getId(), id2)) {
                        ok = false;
                        break;
                    }
                }
                for (Utilizator user : utilizator2.getFriends()) {
                    if (Objects.equals(user.getId(), id1)) {
                        ok = false;
                        break;
                    }.
                }
                if (ok) {
                    utilizator1.makeFriend(utilizator2);
                    utilizator2.makeFriend(utilizator1);
                    this.repository.saveFriendsToFile();
                }
            }
        } catch (NullPointerException e) {
            throw e;
        }
    }

    public void deleteFriend(Long id1, Long id2, Long id) {
        Utilizator utilizator1 = this.repository.findOne(id1);
        Utilizator utilizator2 = this.repository.findOne(id2);
        this.repository.deleteOneFriend(id2, utilizator1);
        this.repository.deleteOneFriend(id1, utilizator2);
        this.repository.saveFriendsToFile();
        this.repoPrietenie.delete(id);
    }

    public int getNrOfConnectedComponents() {
        Graf graph = new Graf(this.repository);
        return graph.getNrOfConnectedComponents();
    }

    public List<Long> getLargestConnectedComponent() {
        Graf graph = new Graf(this.repository);
        return graph.getLargestConnectedComponent().stream().toList();
    }

    public Utilizator getById(Long x) {
        return this.repository.findOne(x);
    }
}
