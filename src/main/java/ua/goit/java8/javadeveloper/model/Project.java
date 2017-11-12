package ua.goit.java8.javadeveloper.model;

import java.math.BigDecimal;

/**
 * Created by Taras on 11.11.2017.
 */
public class Project {
    private Long id;
    private String name;
    private Long customer_id;
    private Long company_id;
    private BigDecimal costs;

    public Project() {
    }

    public Project(Long id, String name, Long customer_id, Long company_id, BigDecimal costs) {
        this.id = id;
        this.name = name;
        this.customer_id = customer_id;
        this.company_id = company_id;
        this.costs = costs;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getCustomer_id() {
        return customer_id;
    }

    public void setCustomer_id(Long customer_id) {
        this.customer_id = customer_id;
    }

    public Long getCompany_id(){
        return company_id;
    }

    public void setCompany_id(Long company_id){
        this.company_id = company_id;
    }

    public BigDecimal getCosts() {
        return costs;
    }

    public void setCosts(BigDecimal costs) {
        this.costs = costs;
    }

    public Project withId(Long id){
        this.id = id;
        return this;
    }

    public Project withName(String name){
        this.name = name;
        return this;
    }

    public Project withCustomer_id(Long customer_id){
        this.customer_id = customer_id;
        return this;
    }

    public Project withCompany_id(Long company_id){
        this.company_id = company_id;
        return this;
    }

    public Project withCosts(BigDecimal costs){
        this.costs = costs;
        return this;
    }

    @Override
    public String toString() {
        return "Project{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", customer_id='" + customer_id + '\'' +
                ", company_id='" + company_id + '\'' +
                ", costs=" + costs +
                '}';
    }
}
