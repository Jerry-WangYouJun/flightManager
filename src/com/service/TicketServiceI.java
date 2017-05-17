package com.service;

import java.util.List;

import com.core.model.Grid;
import com.pojo.Ticket;

public interface TicketServiceI {

	List<Ticket> findTicketDicMaps();

	Grid findTicketList(Ticket ticket);

	Integer addTicket(Ticket ticket);

}
