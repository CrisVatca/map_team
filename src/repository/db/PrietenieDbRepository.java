package repository.db;

import domain.Prietenie;
import repository.Repository;



import java.sql.*;
import java.util.HashSet;
import java.util.Set;

public class PrietenieDbRepository implements Repository<Long, Prietenie> {
    private String url;
    private String username;
    private String password;

    public PrietenieDbRepository(String url, String username, String password) {
        this.url = url;
        this.username = username;
        this.password = password;
    }


    @Override
    public Prietenie findOne(Long id) {
        String sql = "select * from prietenie where id=?";

        try (Connection connection = DriverManager.getConnection(url, username, password);
             PreparedStatement ps = connection.prepareStatement(sql)) {

            ps.setLong(1, id);

            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Iterable<Prietenie> findAll() {
        Set<Prietenie> prietenii = new HashSet<>();
        try (Connection connection = DriverManager.getConnection(url, username, password);
             PreparedStatement statement = connection.prepareStatement("SELECT * from prietenie");
             ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                Long idu = resultSet.getLong("idu");
                Long idp = resultSet.getLong("idp");
                Long id = resultSet.getLong("id");

                Prietenie prietenie = new Prietenie(idu, idp);
                prietenie.setId(id);
                prietenii.add(prietenie);
            }
            return prietenii;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return prietenii;
    }

    @Override
    public Prietenie save(Prietenie entity) {

        String sql = "insert into prietenie (idu, idp,id) values (?,?,?)";

        try (Connection connection = DriverManager.getConnection(url, username, password);
             PreparedStatement ps = connection.prepareStatement(sql)) {

            ps.setLong(1, entity.getIdU());
            ps.setLong(2, entity.getIdP());
            ps.setLong(3, entity.getId());

            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Prietenie delete(Long id) {
        String sql = "delete from prietenie where id=?";
        try (Connection connection = DriverManager.getConnection(url, username, password);
             PreparedStatement ps = connection.prepareStatement(sql))
        {
            ps.setLong(1, id);

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

