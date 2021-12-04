import domain.Utilizator;
import domain.validators.UtilizatorValidator;
import repository.Repository;
import repository.db.PrietenieDbRepository;
import repository.db.UtilizatorDbRepository;
import repository.file.AbstractFileRepository;
import repository.file.UtilizatorFile;
import service.Service;
import ui.UI;

public class Main {
    public static void main(String[] args){
        //Student s1 = new Student("Dan", 4.5f);
       // Student s2 = new Student("Ana", 8.5f);
       // Student s3 = new Student("Dan", 4.5f);
        //Student s4 = new Student("Andrei", 1.1f);
        //Student s5 = new Student("Barbu", 10.0f);

        //Repository<Long, Utilizator> repo = new UtilizatorFile("data/users.csv", new UtilizatorValidator());
        //repo.findAll().forEach(System.out::println);
        //repo.findAll().forEach(x->System.out.println(x));
        //Utilizator u = new Utilizator("Cristina", "Vatca");
        //u.setId(4L);
        //repo.save(u);

        AbstractFileRepository<Long, Utilizator> repoFile = new UtilizatorFile("data/users.csv", "data/prietenie.csv", new UtilizatorValidator());
        UtilizatorValidator validator = new UtilizatorValidator();
        UtilizatorDbRepository repoUtilizator = new UtilizatorDbRepository("jdbc:postgresql://localhost:5432/db","postgres","compunere",validator);
        PrietenieDbRepository repoPrietenie = new PrietenieDbRepository("jdbc:postgresql://localhost:5432/db","postgres","compunere");
        Service service = new Service(repoFile,repoPrietenie,repoUtilizator);
        UI ui = new UI(service);
        ui.menu();
    }
}
