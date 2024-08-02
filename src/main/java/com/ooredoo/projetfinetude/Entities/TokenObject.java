package com.ooredoo.projetfinetude.Entities;
public class TokenObject {
	private String username;
	private Long idUtilisateur;
	private String role;

	public String getUsername() {return username;}

	public void setUsername(String username) {this.username = username;}
	public Long getIdUtilisateur() {return idUtilisateur;}
	public void setIdUtilisateur(Long idUtilisateur) {this.idUtilisateur = idUtilisateur;}
	public String getRole() {return role;}
	public void setRole(String role) {this.role = role;}
	public TokenObject(String username, Long idUtilisateur, String role) {
		this.username = username;
		this.idUtilisateur = idUtilisateur;
		this.role = role; }

	public TokenObject() {
	}
}
