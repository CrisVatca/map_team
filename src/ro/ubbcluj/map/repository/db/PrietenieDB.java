package ro.ubbcluj.map.repository.db;

import ro.ubbcluj.map.domain.Prietenie;
import ro.ubbcluj.map.domain.Utilizator;
import ro.ubbcluj.map.domain.validators.Validator;
import ro.ubbcluj.map.repository.Repository;

import java.sql.*;
import java.util.HashSet;
import java.util.Set;

public class PrietenieDB implements Repository<Long, Prietenie> {
    private String url;
    private String username;
    private String password;
    private Validator<Prietenie> validator;

    public PrietenieDB(String url, String username, String password, Validator<Prietenie> validator) {
        this.url = url;
        this.username = username;
        this.password = password;
        this.validator = validator;
    }

    @Override
    public Prietenie findOne(Long aLong) {

        return null;
    }

    @Override
    public Iterable<Prietenie> findAll() {
        Set<Prietenie> users = new HashSet<>();
        try (Connection connection = DriverManager.getConnection(url, username, password);
             PreparedStatement statement = connection.prepareStatement("SELECT * from friendships");
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                Long id = resultSet.getLong("id");
                Long idUser1 = resultSet.getLong("id_user1");
                Long idUser2 = resultSet.getLong("id_user2");

                Prietenie prietenie = new Prietenie(idUser1, idUser2);
                prietenie.setId(id);
                users.add(prietenie);
            }
            return users;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return users;
    }

    @Override
    public Prietenie save(Prietenie entity) {
        String sql = "insert into friendships (id_user1, id_user2 ) values (?, ?)";

        try (Connection connection = DriverManager.getConnection(url, username, password);
             PreparedStatement ps = connection.prepareStatement(sql)) {

            ps.setLong(1, entity.getId1());
            ps.setLong(2, entity.getId2());

            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Prietenie delete(Long aLong) {

        String sql = "delete from friendships where id=?";

        try (Connection connection = DriverManager.getConnection(url, username, password);
             PreparedStatement ps = connection.prepareStatement(sql)) {

            ps.setLong(1, aLong);

            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Prietenie update(Prietenie entity) {
        return null;
    }

}
