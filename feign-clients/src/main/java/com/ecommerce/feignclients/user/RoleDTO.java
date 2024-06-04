package com.ecommerce.feignclients.user;


public class RoleDTO {
	private Long id;
	private String name;
	
	//constructeurs
		public RoleDTO() {}
		
		public RoleDTO(Long id, String name) {
			this.id = id;
			this.name = name;
		}
		
		// Getters & Setters
		// id
		public Long getId() {
			return id;
		}
		
		public void setId(Long id) {
			this.id = id;
		}
		
		// libelle
		public String getName() {
			return name;
		}
		
		public void setName(String name) {
			this.name = name;
		}
}
