package com.agenda.controller;

import com.agenda.model.Agendamento;
import com.agenda.repository.AgendamentoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/agendamento")
public class AgendamentoController {

    @Autowired
    private AgendamentoRepository agendamentoRepository;

    @GetMapping
    public List<Agendamento> getAllAgendamentos() {
        return agendamentoRepository.findAll();
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public ResponseEntity<Agendamento> getAgendamentoById(@PathVariable Long id) {
        Optional<Agendamento> agendamento = agendamentoRepository.findById(id);
        return ResponseEntity.of(agendamento);
    }

    @PostMapping
    @ResponseBody
    @Transactional
    public Agendamento criarAgendamento(@RequestBody Agendamento agendamentoRequest) {
        return agendamentoRepository.save(agendamentoRequest);
    }

    @PutMapping("/{id}")
    @ResponseBody
    @Transactional
    public ResponseEntity<Agendamento> updateAgendamento(@PathVariable(name = "id") Long id, @RequestBody Agendamento agendamentoForm) {
        Optional<Agendamento> agendamentoFromDatabase = agendamentoRepository.findById(id);

        if (agendamentoFromDatabase.isPresent()) {
            Agendamento agendamentoUpdated = agendamentoFromDatabase.get().atualizarAgendamento(agendamentoForm);
            return ResponseEntity.ok(agendamentoUpdated);
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<Object> deleteAgendamento(@PathVariable Long id) {
        Optional<Agendamento> agendamento = agendamentoRepository.findById(id);
        agendamento.ifPresent(value -> agendamentoRepository.delete(value));
        return ResponseEntity.ok().build();
    }
}
