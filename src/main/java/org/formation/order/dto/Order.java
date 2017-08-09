package org.formation.order.dto;

public class Order {

		private int id;
		private String status;
		
			
		
		public Order() {
			super();
			
		}

		public Order(int id) {
			super();
			this.id = id;
		}

		public int getId() {
			return id;
		}
		
		public void setId(int id) {
			this.id = id;
		}
		
		public String getStatus() {
			return status;
		}
		
		public void setStatus(String status) {
			this.status = status;
		}
		
		
		
}
