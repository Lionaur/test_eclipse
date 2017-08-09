package org.formation.order.bo;



import java.sql.SQLException;

import org.formation.order.bo.exception.BOException;
import org.formation.order.dao.OrderDAO;
import org.formation.order.dto.Order;

public class OrderBOImpl implements OrderBO {
	
	private OrderDAO dao;
	
	
	
	@Override
	public boolean cancelOrder(int id) throws BOException {
		try {
			Order order = dao.read(id);
			order.setStatus("cancelled");
			int res = dao.update(order);
			if(res==0) {
				return false;
			}
		} catch (SQLException e) {
			throw new BOException(e);
		}
		return true;
	}

	@Override
	public boolean deleteOrder(int id) throws BOException{
		try {
			int res=dao.delete(id);
			if(res==0) {
				return false;
			}
		} catch (SQLException e) {
			throw new BOException(e);
		}
		return true;
	}

	@Override
	public boolean placeOrder(Order order) throws BOException {
		try {
			int res = dao.create(order);
			if(res==0) {
				return false;
			}
		} catch (SQLException e) {
			throw new BOException(e);
		}
		return true;
	}

	public OrderDAO getDao() {
		return dao;
	}

	public void setDao(OrderDAO dao) {
		this.dao = dao;
	}
	
	

}
