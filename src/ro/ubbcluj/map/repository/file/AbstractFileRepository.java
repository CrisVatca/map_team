package ro.ubbcluj.map.repository.file;

import ro.ubbcluj.map.domain.Entity;
import ro.ubbcluj.map.domain.validators.Validator;
import ro.ubbcluj.map.repository.memory.InMemoryRepository;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.StandardOpenOption;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public abstract class AbstractFileRepository<ID, E extends Entity<ID>> extends InMemoryRepository<ID, E> {
    String fileName;

    public AbstractFileRepository(String fileName, Validator<E> validator) {
        super(validator);
        this.fileName = fileName;
        loadData();

    }

    private void loadData() {
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = br.readLine()) != null) {
                E e = extractEntity(Arrays.asList(line.split(";")));
                super.save(e);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    /**
     * extract entity  - template method design pattern
     * creates an entity of type E having a specified list of @code attributes
     *
     * @param attributes
     * @return an entity of type E
     */
    public abstract E extractEntity(List<String> attributes);
    ///Observatie-Sugestie: in locul metodei template extractEntity, puteti avea un factory pr crearea instantelor entity

    public abstract String createEntityAsString(E entity);

    @Override
    public E save(E entity) {
        E e = super.save(entity);
        if (e == null) {
            writeToFile(entity);
        }
        return e;
    }

    protected void writeToFile(E entity) {
        try (BufferedWriter br = new BufferedWriter(new FileWriter(fileName, true))) {
            br.write(createEntityAsString(entity));
            br.newLine();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void removeLine(String lineContent) throws IOException {
        File file = new File(fileName);
        List<String> out = Files.lines(file.toPath())
                .filter(line -> !line.contains(lineContent))
                .collect(Collectors.toList());
        Files.write(file.toPath(), out, StandardOpenOption.WRITE, StandardOpenOption.TRUNCATE_EXISTING);
    }

    @Override
    public E delete(ID id) {
        E e = super.delete(id);
        if (e != null) {
            try {
                removeLine(createEntityAsString(e));
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }

        return e;
    }

    @Override
    public E update(E entity) {
        ///TODO
        E e = super.update(entity);
        if (e == null) {
            writeToFile(entity);
        }
        return e;
    }
}

