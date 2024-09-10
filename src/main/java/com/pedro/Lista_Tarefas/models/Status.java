package com.pedro.Lista_Tarefas.models;


public enum Status {

    PENDENTE("pendente"),
    EM_ANDAMENTO("em_andamento"),
    FEITO("feito");

    private String status;

    Status(String status){
        this.status = status;
    }

    public String getStatus(){
        return status;
    }

}
