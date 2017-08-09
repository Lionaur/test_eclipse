package org.formation.order.bo.test;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import org.formation.order.bo.OrderBOImpl;
import org.formation.order.bo.exception.BOException;
import org.formation.order.dao.OrderDAO;
import org.formation.order.dto.Order;
import org.junit.Before;
import org.junit.Test;


import java.sql.SQLException;

import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

public class OrderBOImplTest {
	
	private static final int ORDER_ID =123;
	
	@Mock
	OrderDAO dao;
	private OrderBOImpl bo;
	
	@Before
	public void setup() {
		MockitoAnnotations.initMocks(this);
		bo = new OrderBOImpl();
		bo.setDao(dao);
	}


	@Test
	public void placeOrder_Should_Create_An_Order() throws SQLException, BOException {
		Order order = new Order(ORDER_ID);
		when(dao.create(any(Order.class))).thenReturn(new Integer(1));
		boolean res = bo.placeOrder(order);
		assertTrue(res);
		verify(dao).create(order);
	}
	
	@Test
	public void placeOrder_Should_not_Create_An_Order() throws SQLException, BOException{
		Order order = new Order(ORDER_ID);
		when(dao.create(order)).thenReturn(new Integer(0));
		boolean res = bo.placeOrder(order);
		assertFalse(res);
		verify(dao).create(order);
	}
	
	
	@Test(expected = BOException.class)
	public void placeOrder_Should_Throws_BOException() throws SQLException, BOException {
		Order order = new Order(ORDER_ID);
		when(dao.create(any(Order.class))).thenThrow(new SQLException());
		bo.placeOrder(order);
	}
	
	@Test
	public void cancelOrder_Should_Cancel_The_Order() throws SQLException, BOException {
		Order order = new Order(ORDER_ID);
		when(dao.read(ORDER_ID)).thenReturn(order);
		when(dao.update(order)).thenReturn(1);
		boolean res = bo.cancelOrder(ORDER_ID);
		assertTrue(res);
		verify(dao).read(ORDER_ID);
		verify(dao).update(order);
	}
	
	@Test
	public void cancelOrder_Should_NOT_Cancel_The_Order() throws SQLException, BOException {
		Order order = new Order();
		when(dao.read(order.getId())).thenReturn(order);
		when(dao.update(order)).thenReturn(new Integer(0));
		boolean res = bo.cancelOrder(order.getId());
		assertFalse(res);
		verify(dao).read(order.getId());
		verify(dao).update(order);
		
	}
	
	
	@Test(expected = BOException.class)
	public void cancelOrder_Should_Throws_BOExceptionOnRead() throws SQLException, BOException {
		when(dao.read(anyInt())).thenThrow(new SQLException());
		bo.cancelOrder(anyInt());
	}
	
	@Test(expected = BOException.class)
	public void cancelOrder_Should_Throws_BOExceptionOnUpdate() throws SQLException, BOException {
		Order order = new Order();
		when(dao.read(anyInt())).thenReturn(order);
		when(dao.update(order)).thenThrow(new SQLException());
		bo.cancelOrder(anyInt());
	}
	
	@Test
	public void deleteOrder_Deletes_The_Order() throws SQLException, BOException {
		when(dao.delete(ORDER_ID)).thenReturn(1);
		boolean res = bo.deleteOrder(ORDER_ID);
		assertTrue(res);
		verify(dao).delete(ORDER_ID);
	}
	

	
}
