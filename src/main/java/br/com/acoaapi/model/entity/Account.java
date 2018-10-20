package br.com.acoaapi.model.entity;

import br.com.acoaapi.helper.AccountSerializerHelper;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;

@JsonSerialize(using = AccountSerializerHelper.class)
@Entity
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @NotNull(message = "response.error.empty.price")
    private Double price;

    @OneToMany(mappedBy = "account", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<User> userList;

    @OneToMany(mappedBy = "account", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<Device> deviceList;

    @OneToMany(mappedBy = "account", fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
    private List<LitersPerHour> litersPerHourList;

    @OneToMany(mappedBy = "account", fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
    private List<LitersPerDay> litersPerDayList;

    @OneToMany(mappedBy = "account", fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
    private List<LitersPerMonth> litersPerMonthList;

    public Account() {
    }

    public Account(Double price, List<User> userList) {
        this.price = price;
        this.userList = userList;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public List<User> getUserList() {
        return userList;
    }

    public void setUserList(List<User> userList) {
        this.userList = userList;
    }

    public List<Device> getDeviceList() {
        return deviceList;
    }

    public void setDeviceList(List<Device> deviceList) {
        this.deviceList = deviceList;
    }

    public List<LitersPerHour> getLitersPerHourList() {
        return litersPerHourList;
    }

    public void setLitersPerHourList(List<LitersPerHour> litersPerHourList) {
        this.litersPerHourList = litersPerHourList;
    }

    public List<LitersPerDay> getLitersPerDayList() {
        return litersPerDayList;
    }

    public void setLitersPerDayList(List<LitersPerDay> litersPerDayList) {
        this.litersPerDayList = litersPerDayList;
    }

    public List<LitersPerMonth> getLitersPerMonthList() {
        return litersPerMonthList;
    }

    public void setLitersPerMonthList(List<LitersPerMonth> litersPerMonthList) {
        this.litersPerMonthList = litersPerMonthList;
    }
}
