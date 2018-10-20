package br.com.acoaapi.model.entity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Entity
public class LitersPerMonth {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @NotNull(message = "response.error.empty.flowrate")
    private Double flowRate;

    @NotNull(message = "response.error.empty.datecollection")
    @Temporal(TemporalType.DATE)
    private Date dateCollection;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "account_id")
    private Account account;

    public LitersPerMonth() {
    }

    public LitersPerMonth(Double flowRate, Date dateCollection, Account account) {
        this.flowRate = flowRate;
        this.dateCollection = dateCollection;
        this.account = account;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Double getFlowRate() {
        return flowRate;
    }

    public void setFlowRate(Double flowRate) {
        this.flowRate = flowRate;
    }

    public Date getDateCollection() {
        return dateCollection;
    }

    public void setDateCollection(Date dateCollection) {
        this.dateCollection = dateCollection;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }
}
