package ro.ubbcluj.map.repository.file;

import ro.ubbcluj.map.domain.Prietenie;
import ro.ubbcluj.map.domain.Utilizator;
import ro.ubbcluj.map.domain.validators.Validator;

import java.util.List;

public class PrietenieFile extends AbstractFileRepository<Long, Prietenie> {
    public PrietenieFile(String fileName, Validator<Prietenie> validator) {
        super(fileName, validator);
    }

    @Override
    public Prietenie extractEntity(List<String> attributes) {
        long id1 = Long.parseLong(attributes.get(1));
        long id2 = Long.parseLong(attributes.get(2));
        Prietenie p = new Prietenie(id1, id2);
        p.setId(Long.parseLong(attributes.get(0)));
        return p;
    }

    @Override
    public String createEntityAsString(Prietenie entity) {
        return entity.getId().toString() + ";" + entity.getId1().toString() + ";" + entity.getId2().toString();
    }
}