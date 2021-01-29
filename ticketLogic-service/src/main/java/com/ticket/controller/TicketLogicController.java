package com.ticket.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.commons.entity.Ticket;
import com.commons.entity.Usuario;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.ticket.service.ITicketsLogicService;

@RefreshScope
@RestController
public class TicketLogicController {
	
	@Autowired
	@Qualifier("ServiceFeign")
	private ITicketsLogicService service;
	
	@GetMapping("/findByUsuario/{usuario}")
	@ResponseStatus(HttpStatus.CREATED)
	public List<Ticket> findByUsuario(@PathVariable (name = "usuario") String usuario) {
		return service.findByUsername(usuario);
	}

	//@HystrixCommand(fallbackMethod = "metodoAlternativo")
	@GetMapping("/findById/{id}")
	@ResponseStatus(HttpStatus.CREATED)
	public Optional<Ticket> findById(@PathVariable (name = "id") Long id) {
		return service.findById(id);
	}
	
	@DeleteMapping("/deleteTicket/{id}")
	@ResponseStatus(HttpStatus.CREATED)
	public void deleteTicket(@PathVariable (name = "id") Long id) {
		service.delete(id);
	}
	
	@PutMapping("/updateTicket")
	@ResponseStatus(HttpStatus.CREATED)
	public Ticket update(@RequestBody Ticket  ticket){
		return service.update(ticket);
	}
	
	@PostMapping("/newTicket")
	@ResponseStatus(HttpStatus.CREATED)
	public Ticket newTicket(@RequestBody Ticket  ticket) {
		return service.save(ticket);
	}
	
//	public Ticket metodoAlternativo(Integer id) {
//			
//			Ticket ticket = new Ticket();
//			Usuario user = new Usuario();
//			ticket.setUsuario(user);
//			ticket.setId((long) 0);
//			ticket.setImpuesto(666.0);
//			ticket.setSubtotal(666.0);
//			ticket.setTotal(666.0);
//			ticket.getUsuario().setUsername("ID NOT FOUND");;
//			
//			return ticket;
//	}

}
