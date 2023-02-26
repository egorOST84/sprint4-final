package ru.praktikumservices.qascooter.constants;

public class OrderData {
    private final String firstName;
    private final String lastName;
    private final String address;
    private final String metroStationName;
    private final String phoneNumber;
    private final String commentToDelivery;

    public OrderData() {
        this.firstName = "Алкексей";
        this.lastName = "Алексеев";
        this.address = "Санкт-Петербург, Невский проспект, 100";
        this.metroStationName = "Белорусская";
        this.phoneNumber = "+791512345678";
        this.commentToDelivery = "Комментарий к заказу";
    }

    public OrderData(String firstName, String lastName, String address, String metroStationName, String phoneNumber, String commentToDelivery) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.metroStationName = metroStationName;
        this.phoneNumber = phoneNumber;
        this.commentToDelivery = commentToDelivery;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getAddress() {
        return address;
    }

    public String getMetroStationName() {
        return metroStationName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getCommentToDelivery() {
        return commentToDelivery;
    }
}
