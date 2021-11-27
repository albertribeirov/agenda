package com.agenda.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.Serializable;
import java.time.LocalTime;

@Entity
public class Agendamento implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalTime inicioAgenda;
    private LocalTime finalAgenda;

    public Agendamento() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalTime getInicioAgenda() {
        return inicioAgenda;
    }

    public void setInicioAgenda(LocalTime inicioAgenda) {
        this.inicioAgenda = inicioAgenda;
    }

    public LocalTime getFinalAgenda() {
        return finalAgenda;
    }

    public void setFinalAgenda(LocalTime finalAgenda) {
        this.finalAgenda = finalAgenda;
    }

    public Agendamento atualizarAgendamento(Agendamento agendamento) {
        this.setFinalAgenda(agendamento.getFinalAgenda());
        this.setInicioAgenda(agendamento.getInicioAgenda());
        return this;
    }

}
