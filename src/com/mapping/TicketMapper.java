package com.mapping;

import java.util.List;

import com.pojo.Ticket;

public interface TicketMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Ticket ticket);

    int insertSelective(Ticket ticket);

    Ticket selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Ticket ticket);

    int updateByPrimaryKey(Ticket ticket);

	List<Ticket> selectAll();

	List<Ticket> selectTicketByWhere(Ticket ticket);

	Long selectTicketCountByWhere(Ticket ticket);
}