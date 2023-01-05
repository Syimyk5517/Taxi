package implService;

import dao.Database;
import model.Client;
import model.Taxi;
import services.ClientService;

import java.time.LocalDate;
import java.util.*;

public class ClientServiceImpl implements ClientService {

    Database database=new Database();
    @Override
    public String addClient(Client client) {
        database.getClients().add(client);
        return "Client successfully added";
    }

    @Override
    public String addClient(List<Client> clients) {
        database.getClients().addAll(clients);
        return "Clients successfully added";
    }

    @Override
    public List<Client> getClientByName(String name) {
        return database.getClients().stream().filter(s -> s.getFullname().endsWith(name)).toList();
    }

    @Override
    public Client removeClientById(Long id) {
        for (Client client : database.getClients()) {
            if (client.getId().equals(id)) {
                database.getClients().remove(client);
                System.out.println("Client successfully deleted!");
                return client;
            }
        }

        return null;
    }

    @Override
    public Taxi orderTaxi(Long clientId, String taxiType) {
        for (Client client : database.getClients()) {
            if (Objects.equals(client.getId(), clientId)) {
                for (Taxi taxi : database.getTaxis()) {
                    if (taxi.getTaxiType().name().equals(taxiType)) {
                        int t = client.getMoney().intValue();
                        if (t >= taxi.getTaxiType().getPriceForLanding().intValue()) {
                            return taxi;
                        } else {
                            System.out.println("Not enough money");
                        }
                    } else {
                        System.out.println("You make wrong order");
                    }
                }
            } else {
                System.out.println("Wrong id");
            }
        }
        return null;
    }
    @Override
    public Map<Integer, Client> getClientAge() {
        Map<Integer,Client>clientMap=new HashMap<>();
        for(Client client : database.getClients()){
            clientMap.put(LocalDate.now().minusYears(client.getDateOfBirth().getYear()).getYear(),client);
        }
        return clientMap;
    }

    @Override
    public void universalSorting(String word) {
        switch (word){
            case "FullName"->
                    database.getClients().stream().sorted(Comparator.comparing(Client::getFullname)).toList().forEach(System.out::println);
            case "dateOfBirth"->
                    database.getClients().stream().sorted(Comparator.comparing(Client::getDateOfBirth)).toList().forEach(System.out::println);
            case "Money"->
                    database.getClients().stream().sorted(Comparator.comparing(Client::getMoney)).toList().forEach(System.out::println);
            default -> System.out.println("Error!!!");
    }
}}
