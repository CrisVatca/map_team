package repository.file;

import domain.Entity;
import domain.validators.Validator;
import repository.db.PrietenieDbRepository;
import repository.db.UtilizatorDbRepository;
import repository.memory.InMemoryRepository;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

///Aceasta clasa implementeaza sablonul de proiectare Template Method; puteti inlucui solutia propusa cu un Factori (vezi mai jos)
public abstract class AbstractFileRepository<ID, E extends Entity<ID>> extends InMemoryRepository<ID,E> {
    String fileName;
    String fileName1;
    public AbstractFileRepository(String fileName,String fileName1, Validator<E> validator) {
        super(validator);
        this.fileName=fileName;
        this.fileName1=fileName1;
        loadData();
        loadPrietenie();
    }

    private void loadData() {
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String line;
            while((line = br.readLine()) != null){
                E e = extractEntity(Arrays.asList(line.split(";")));
                super.save(e);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private void loadPrietenie() {
        try (BufferedReader bf = new BufferedReader(new FileReader(fileName1))) {
            String line;
            while ((line = bf.readLine()) != null) {
                crearePrietenie(Arrays.asList(line.split(";")));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     *  extract entity  - template method design pattern
     *  creates an entity of type E having a specified list of @code attributes
     * @param attributes
     * @return an entity of type E
     */
    public abstract E extractEntity(List<String> attributes);
    ///Observatie-Sugestie: in locul metodei template extractEntity, puteti avea un factory pr crearea instantelor entity

    public abstract String createEntityAsString(E entity);

    protected abstract void crearePrietenie(List<String> attributes);

    public abstract void writeToFriendshipFile(E entity);

    public abstract void deleteOneFriend(ID id, E entity);

    @Override
    public E save(E entity){
        E e = super.save(entity);
        writeToFile(entity);
        writeToFriendshipFile(entity);
        return e;
    }

    protected void writeToFile(E entity){
        try (BufferedWriter br = new BufferedWriter(new FileWriter(fileName, true))) {
            br.write(createEntityAsString(entity));
            br.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void saveFriendsToFile() {
        try {
            new FileWriter(this.fileName1).close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        ArrayList<ID> keyList = new ArrayList<>(entities.keySet());
        for (ID key : keyList) {
            writeToFriendshipFile(entities.get(key));
        }
    }

    @Override
    public E delete(ID id) {
        E entity = entities.get(id);
        if (entity == null) {
            throw new IllegalArgumentException("Entitatea pe care doriti sa o stergeti nu exista!");
        }
        return entities.remove(id);
    }

}
