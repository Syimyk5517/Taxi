import dao.Database;
import model.Driver;
import model.License;
import model.Taxi;
import services.DriverService;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

import static java.sql.DriverManager.getDrivers;

public class DriverServiceImpl implements DriverService {
    Database database = new Database();
    @Override
    public Driver add(Driver driver) {
        Driver driver1 = null;
        for (Driver d: database.getDrivers()) {
            if (!d.getId().equals(driver.getId())){
                driver1 = driver;
                database.getDrivers().add(driver);
            }else {
                System.out.println("Driver c таким логином уже существует!");
            }
        }
        return driver1;
    }

    @Override
    public List<Driver> add(List<Driver> drivers) {
        database.getDrivers().addAll(drivers);
        return database.getDrivers().stream().toList();
    }

    @Override
    public Driver findById(Long id) {
        return database.getDrivers().stream().filter(x->x.getId().equals(id)).findAny().get();
    }

    @Override
    public List<Driver> findByName(String name) {
        return database.getDrivers().stream().filter(x-> x.getName().equals(name)).toList();
    }

    @Override
    public String assignTaxiToDriver(String taxiName, Long driverId) {
        model.Driver driver = null;
        Taxi taxi = null;
        for (model.Driver d : database.getDrivers()) {
            if (driverId.equals(d.getId())){
                driver = d;
            }
        }
        for (Taxi t : database.getTaxis()) {
            if (t.getModel().equalsIgnoreCase(taxiName)){
                taxi = t;
            }
        }
        if (driver != null){
            if (taxi != null){
                driver.setTaxi(taxi);
                return "Taxi " + taxi.getModel() + " successfully assigned to driver " + driver.getName();
            }else {
                return "Taxi not found!";
            }
        } else {
            return "Driver not found!";
        }
    }

    @Override
    public String changeTaxiOrDriver(Long driverId, Long taxiId) {
        Driver driver = database.getDrivers()
                .stream()
                .filter(x -> x.getId().equals(driverId)).findAny().get();
        Taxi taxi = database.getTaxis()
                .stream()
                .filter(x -> x.getId().equals(taxiId)).findAny().get();
        if (driver.getTaxi() != null){
            for (Driver d : database.getDrivers()) {
                if (!d.getTaxi().equals(taxi)) {
                    driver.setTaxi(taxi);
                    return "Taxi successfully changed!";
                } else {
                    return "Taxi " + taxi.getModel() + " is busy";
                }
            }
        } else {
            return "Вы не можете изменит taxi driver'ра, так как у driver не имеется taxi";
        }
        return "";
    }

    @Override
    public List<Driver> getDriverByTaxiModel(String model) {
        return database.getDrivers().stream().filter(x-> x.getTaxi().getModel().equalsIgnoreCase(model)).toList();
    }

    @Override
    public void update(String driverName) {
        Driver driver = database.getDrivers()
                .stream()
                .filter(x -> x.getName().equalsIgnoreCase(driverName)).findAny().get();

        System.out.printf("""
            1.ID: %s
            2.Name: %s
            3.Surname: %s
            4.Gender: %s
            5.Phone number: %s
            6.License: %s
            7.Money: %s
            _______________________________________""");
        int num = new Scanner(System.in).nextInt();
        switch (num){
            case 1:
                System.out.print("You can't change id!");
                break;
            case 2:
                System.out.print("Write new name: ");
                String name = new Scanner(System.in).nextLine();
                driver.setName(name);
                break;
            case 3:
                System.out.print("Write new surname: ");
                String surname = new Scanner(System.in).nextLine();
                driver.setSurname(surname);
                break;
            case 4:
                System.out.println("You can't change gender!");
                break;
            case 5:
                System.out.print("Write new phone number: ");
                String phoneNumber = new Scanner(System.in).nextLine();
                driver.setPhoneNumber(phoneNumber);
                break;
            case 6:
                System.out.println("License\nid: ");
                Long id = new Scanner(System.in).nextLong();
                System.out.println("date of issue(yyyy-mm-dd): ");
                String dateOfIssue = new Scanner(System.in).nextLine();
                String[] array = dateOfIssue.split("-");
                int year = Integer.parseInt(array[0]);
                int month = Integer.parseInt(array[1]);
                int day = Integer.parseInt(array[2]);

                System.out.println("expiration date(yyyy-mm-dd):");
                String dateOfIssue1 = new Scanner(System.in).nextLine();
                String[] array1 = dateOfIssue1.split("-");
                int year1 = Integer.parseInt(array[0]);
                int month1 = Integer.parseInt(array[1]);
                int day1 = Integer.parseInt(array[2]);

                driver.setLicense(new License(id, LocalDate.of(year, month, day), LocalDate.of(year1,month1,day1)));
                break;
            case 7:
                System.out.print("Write new money: ");
                BigDecimal money = new Scanner(System.in).nextBigDecimal();
                driver.setMoney(money);
                break;
        }

    }
}
