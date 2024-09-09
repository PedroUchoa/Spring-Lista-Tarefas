package com.pedro.Lista_Tarefas.models;


public enum Status {

    PENDENTE("pendente"),
    FAZENDO("fazendo"),
    FEITO("feito");

    private String status;

    Status(String status){
        this.status = status;
    }

    public String getStatus(){
        return status;
    }

}
