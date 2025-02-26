package com.DevSuperior.DSClient.dto;


import com.DevSuperior.DSClient.entities.Client;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.PastOrPresent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDate;

public class ClientDTO {

    private static final Logger log = LoggerFactory.getLogger(ClientDTO.class);
    // Atributos
    private Long id;
    @NotBlank(message = "Campo Obrigatório")
    private String name;
    private String cpf;
    private Double income;
    @PastOrPresent(message = "Data deve ser no passado")
    private LocalDate brithDate;
    private Integer children;


    // Construtores
    public ClientDTO() {
    }

    public ClientDTO(Long id, String name, String cpf, Double income, LocalDate brithDate, Integer children) {
        this.id = id;
        this.name = name;
        this.cpf = cpf;
        this.income = income;
        this.brithDate = brithDate;
        this.children = children;
    }

    public ClientDTO(Client client){
        id = client.getId();
        name = client.getName();
        cpf = client.getCpf();
        income = client.getIncome();
        brithDate = client.getBrithDate();
        children = client.getChildren();
    }

    // Getters
    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getCpf() {
        return cpf;
    }

    public Double getIncome() {
        return income;
    }

    public LocalDate getBrithDate() {
        return brithDate;
    }

    public Integer getChildren() {
        return children;
    }
}
