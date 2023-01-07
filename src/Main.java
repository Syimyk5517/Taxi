import enums.Gender;
import enums.TaxiType;
import model.Client;
import model.Driver;
import model.License;
import model.Taxi;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.*;

public class Main {
    public static void main(String[] args) {


        System.out.println("TAXI");

        Client client = new Client(1L,"MAlikov Nurjigit", LocalDate.of(2001,10,21),"0505505505", BigDecimal.valueOf(15000));
        Client client1 = new Client(2L,"Samatova Aijan", LocalDate.of(1999,7,12),"0503500500", BigDecimal.valueOf(18000));
        Client client2 = new Client(3L,"Akmatzhanov Zahid", LocalDate.of(2000,8,15),"0777777777", BigDecimal.valueOf(14000));
        Client client3 = new Client(4L,"Zhumabek uulu Syimyk", LocalDate.of(1997,11,1),"0778787878", BigDecimal.valueOf(20000));
        Client client4 = new Client(5L,"Nematila kyzy Saltanat", LocalDate.of(2004,10,28),"0555565758", BigDecimal.valueOf(12000));
       List<Client> clientList = new  ArrayList<>();
       clientList.add(client2);
       clientList.add(client3);
       clientList.add(client);
       clientList.add(client4);
        /*~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
        License license = new License(667L,LocalDate.of(2014,4,4),LocalDate.of(2024,4,4));
        License license1 = new License(667L,LocalDate.of(2015,5,6),LocalDate.of(2025,5,6));
        License license2 = new License(667L,LocalDate.of(2018,6,24),LocalDate.of(2028,6,24));
        /*~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
        Taxi t1 = new Taxi(111L, "Toyota", "111", "black", LocalDate.of(2019, 2, 2), TaxiType.COMFORT);
        Taxi t2 = new Taxi(222L, "Lexus", "222", "white", LocalDate.of(2020, 2, 2), TaxiType.COMFORT);
        Taxi t3 = new Taxi(333L, "Tiko", "333", "pink", LocalDate.of(2022, 2, 2), TaxiType.BUSINESS);
        Taxi t4 = new Taxi(444L, "BMW", "444", "blue", LocalDate.of(2012, 2, 2), TaxiType.BUSINESS);
        Taxi t5 = new Taxi(555L, "Mers", "555", "black", LocalDate.of(2018, 2, 2), TaxiType.STANDART);
        Taxi t6 = new Taxi(666L, "BMW", "666", "yellow", LocalDate.of(2016, 2, 2), TaxiType.STANDART);
        List<Taxi> taxis = new ArrayList<>(List.of(t1, t2, t3, t4, t5, t6));
        /*~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
        Driver driver1 = new Driver(34L,"Bek","Abdinabiev", Gender.MALE,"07788585748",license,new BigDecimal(12133),t1);
        Driver driver2 = new Driver(34L,"Kyrstan","Erkinbayev", Gender.MALE,"07788585458",license1,new BigDecimal(1233),t2);
        Driver driver3 = new Driver(34L,"Baiysh","Orozaliev", Gender.MALE,"07788857458",license2,new BigDecimal(1213543),t3);
        List<Driver> drivers = new ArrayList<>();
        drivers.add(driver2);
        drivers.add(driver3);
        /*~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
        ClientServiceImpl clients = new ClientServiceImpl();
        TaxiServiceImpl taxi = new TaxiServiceImpl();
        DriverServiceImpl driver = new DriverServiceImpl();
        while (true) {
            System.out.println("""
                ~~~~~~~~~ClientService~~~~~~~~
                1: Add client.
                2: Add clients.
                3: Get client by name.
                4: Remove client by id.
                5: Order taxi.
                6: Get clients with age.
                7: Universal sorting.
                ~~~~~~~~TaxiService~~~~~~~
                8: Add taxi.
                9: Add taxis.
                10: Find by initial letter.
                11: Grouping.
                12: Filter by taxiType.
                13: Update.
                ~~~~~~~~DriverService~~~~~~
                14: Add driver.
                15: Add drivers.
                16: Find by id.
                17: Find by name.
                18: Assign Taxi ToDriver.
                19: Change taxi or driver.
                20: Get driver by taxi model.
                21: Update. 
                """);
            int number = new Scanner(System.in).nextInt();
            switch (number) {

                case 1 -> System.out.println(clients.addClient(client1));
                case 2 -> System.out.println(clients.addClient(clientList));
                case 3 -> {
                    System.out.print("Write the name: ");
                    String name = new Scanner(System.in).next();
                    System.out.println(clients.getClientByName(name));
                }
                case 4 -> {
                    System.out.print("Write the id: ");
                    Long id = new Scanner(System.in).nextLong();
                    System.out.println(clients.removeClientById(id));
                }
                case 5 -> {
                    System.out.print("Write the client's id: ");
                    Long id = new Scanner(System.in).nextLong();
                    System.out.print("Write the taxi type: ");
                    String taxiType = new Scanner(System.in).next();
                    System.out.println(clients.orderTaxi(id, taxiType));
                }
                case 6 -> System.out.println(clients.getClientAge());
                case 7 -> {
                    System.out.println("Choose that what you want change: id,fullName,dateOfBirth,money");
                    String word = new Scanner(System.in).next();
                    clients.universalSorting(word);
                }
                case 8 -> System.out.println(taxi.add(t1));
                case 9 -> System.out.println(taxi.add(taxis));
                case 10 -> {
                    System.out.print("Write the model of car: ");
                    String model = new Scanner(System.in).next();
                    System.out.println(taxi.findByInitialLetter(model));
                }
                case 11 -> System.out.println(taxi.grouping());
                case 12 -> {
                    System.out.print("Write the taxi type: ");
                    String taxitype = new Scanner(System.in).next();
                    System.out.println(taxi.filterByTaxiType(taxitype));
                }
                case 13 -> {
                    System.out.print("Write the taxi's id:");
                    Long id = new Scanner(System.in).nextLong();
                    taxi.update(id);
                }
                case 14 -> System.out.println(driver.add(driver1));
                case 15 -> System.out.println(driver.add(drivers));
                case 16 -> {
                    System.out.print("Write the id:");
                    Long id = new Scanner(System.in).nextLong();
                    System.out.println(driver.findById(id));
                }
                case 17 -> {
                    System.out.print("Write the driver's name:");
                    String name = new Scanner(System.in).next();
                    System.out.println(driver.findByName(name));
                }
                case 18 -> {
                    System.out.print("Write the taxi model:");
                    String taxiModel = new Scanner(System.in).next();
                    System.out.print("Write the driver's id: ");
                    Long id = new Scanner(System.in).nextLong();
                    System.out.println(driver.assignTaxiToDriver(taxiModel, id));
                }
                case 19 -> {
                    System.out.print("Write the driver's id: ");
                    Long id = new Scanner(System.in).nextLong();
                    System.out.print("Write the taxi's id: ");
                    Long id1 = new Scanner(System.in).nextLong();
                    System.out.println(driver.changeTaxiOrDriver(id, id1));
                }
                case 20 -> {
                    System.out.print("Write the model: ");
                    String model = new Scanner(System.in).next();
                    System.out.println(driver.getDriverByTaxiModel(model));
                }
                case 21 -> {
                    System.out.print("Write the driver's name: ");
                    String name = new Scanner(System.in).next();
                    driver.update(name);
                }
                default -> {
                    try {
                        throw new Exception("No such number");
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        }

    }
}