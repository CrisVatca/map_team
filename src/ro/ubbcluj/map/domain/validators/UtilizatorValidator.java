package ro.ubbcluj.map.domain.validators;

import ro.ubbcluj.map.domain.Utilizator;

public class UtilizatorValidator implements Validator<Utilizator> {
    @Override
    public void validate(Utilizator entity) throws ValidationException {

        if (!entity.getFirstName().matches("[a-zA-Z]+")) {
            throw new ValidationException("First name must contain only letters.");
        }
        if (!entity.getLastName().matches("[a-zA-Z]+")) {
            throw new ValidationException("Last name must contain only letters.");
        }
    }
}
