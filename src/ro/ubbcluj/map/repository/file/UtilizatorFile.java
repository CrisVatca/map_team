package ro.ubbcluj.map.repository.file;

import ro.ubbcluj.map.domain.Utilizator;
import ro.ubbcluj.map.domain.validators.Validator;

import java.util.List;

public class UtilizatorFile extends AbstractFileRepository<Long, Utilizator> {

    public UtilizatorFile(String fileName, Validator<Utilizator> validator) {
        super(fileName, validator);
    }

    @Override
    public Utilizator extractEntity(List<String> attributes) {
        Utilizator u = new Utilizator(attributes.get(1), attributes.get(2));
        u.setId(Long.parseLong(attributes.get(0)));
        return u;
    }

    @Override
    public String createEntityAsString(Utilizator entity) {
        return entity.getId().toString() + ";" + entity.getFirstName() + ";" + entity.getLastName()
                + ";" + entity.getFriends();
    }
}