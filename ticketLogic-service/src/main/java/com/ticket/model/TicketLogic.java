package com.ticket.model;

import java.util.List;



import com.commons.entity.Detalles;
import com.commons.entity.Ticket;




public class TicketLogic {
	final Double  tax=0.15;
	private Ticket ticket;
	

	
	public TicketLogic(Ticket ticket){
		this.ticket=ticket;
	}
	
	public void calcAll(){
		this.ticket.setImpuesto(tax);
		calcSubTotal();
		calcTotal();
		
	}
	
	public void calcTotal() {
		Double subtotal = this.ticket.getSubtotal();
		this.ticket.setTotal(subtotal*(1.0+this.tax));
	}
	
	public void calcSubTotal() {
		List<Detalles> detalles = this.ticket.getDetalles();
		Double total=0.0;
		for(Detalles detalle : detalles) {
			total+=detalle.getCantidad() *detalle.getInventario().getPrecio();
		}
		this.ticket.setSubtotal(total);
		
	}

	public Ticket getTicket() {
		return ticket;
	}

	public void setTicket(Ticket ticket) {
		this.ticket = ticket;
	}
	
	
	
	
	

}
