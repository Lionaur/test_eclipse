package org.formation.order.bo;

import org.formation.order.bo.exception.BOException;
import org.formation.order.dto.Order;

public interface OrderBO {

		public boolean placeOrder(Order order) throws BOException;
		public boolean cancelOrder(int id) throws BOException;
		public boolean deleteOrder(int id) throws BOException;
}
