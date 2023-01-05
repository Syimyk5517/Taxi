package dao;

import model.Client;
import model.Driver;
import model.Taxi;

import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;

public class Database {
    private Set<Client> clients = new LinkedHashSet<>();
    private Set<Driver> drivers = new HashSet<>();
    private LinkedHashSet<Taxi> taxis = new LinkedHashSet<>();

    public Database(Set<Client> clients, Set<Driver> drivers, LinkedHashSet<Taxi> taxis) {
        this.clients = clients;
        this.drivers = drivers;
        this.taxis = taxis;
    }

    public Set<Client> getClients() {
        return clients;
    }

    public void setClients(Set<Client> clients) {
        this.clients = clients;
    }

    public Set<Driver> getDrivers() {
        return drivers;
    }

    public void setDrivers(Set<Driver> drivers) {
        this.drivers = drivers;
    }

    public LinkedHashSet<Taxi> getTaxis() {
        return taxis;
    }

    public void setTaxis(LinkedHashSet<Taxi> taxis) {
        this.taxis = taxis;
}}
