package fr.insset.gestionQCM.presentation;

import java.io.IOException;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import fr.insset.gestionQCM.dao.entity.Auteur;

import fr.insset.gestionQCM.dao.entity.Groupe;
import fr.insset.gestionQCM.metier.GroupeMetier;
import fr.insset.gestionQCM.metier.UserMetier;
import fr.insset.gestionQCM.utils.Logout;
import fr.insset.gestionQCM.utils.SessionUtil;





@ManagedBean
@SessionScoped
public class GroupeBean implements Serializable {

	private static final long serialVersionUID = 1L;
	public Logger log = Logger.getLogger(UserBean.class);
	private String NomGroupe;
	private Date dateCreation;

	private Integer idUser;
	
	private UserMetier metier;
	
	private List<Groupe> listeGroupes ; 
	

	
	private String username;

	private int  nbGroupes;
	
	private int idGroupe;
	
	public GroupeBean() {
		super();
	}

	
	@PostConstruct
	public void initBean(){
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(new String[]{"config/config.xml"});
		UserMetier metier = (UserMetier) context.getBean("metier"); 
		this.setMetier(metier);
		context.close();
		
		HttpSession hs = SessionUtil.getSession();
		this.idUser = (Integer) hs.getAttribute("idUser");
		this.username = (String) hs.getAttribute("username");

		Auteur a = metier.getAuteur(idUser);
		listeGroupes = a.getListGroupes();
		nbGroupes = listeGroupes.size();
	}


	public void addGroupe() throws IOException{
		
		if(NomGroupe.trim().isEmpty()){
			FacesContext.getCurrentInstance().addMessage("inputNameGroupe", new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erreur !", "Le titre du groupe est invalide !."));
		}
		else {
			
			
			String date= new SimpleDateFormat("dd-MM-yyyy").format(new Date());
			Groupe gp = new Groupe();
			gp.setId_Auteur(idUser);
			gp.setDateCreation(date);
			gp.setNomGroupe(NomGroupe);
			gp.setChatAuth(false);
			ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(new String[]{"config/config.xml"});
			GroupeMetier metier = (GroupeMetier) context.getBean("groupeMetier"); 
			context.close();
			metier.addGroupe(gp);
			ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
			ec.redirect(((HttpServletRequest) ec.getRequest()).getRequestURI());
		}
	}
	
	public void deleteGroupe() throws IOException{
		
		ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
		Map<String, String> param = ec.getRequestParameterMap(); 
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(new String[]{"config/config.xml"});
		GroupeMetier metier = (GroupeMetier) context.getBean("groupeMetier"); 
		context.close();
		metier.deleteGroupe(Integer.valueOf(param.get("id")));
		ec.redirect(((HttpServletRequest) ec.getRequest()).getRequestURI());
		
	}
	
	public void showGroupe(){
		
		ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
		Map<String, String> param = ec.getRequestParameterMap(); 
		
		HttpSession hs = SessionUtil.getSession();
		hs.setAttribute("idGroupe", Integer.valueOf(param.get("idgrp")));
		hs.setAttribute("NomGroupe", param.get("Nomgrp"));
		
		try {
			ec.redirect("groupetudiantlist.xhtml");
		} catch (IOException e) {
			
			e.printStackTrace();
		}
		FacesContext.getCurrentInstance().responseComplete();
		
	}
	
	public void logout(){
		Logout.logOut();
	}
	
	public String getNomGroupe() {
		return NomGroupe;
	}


	public void setNomGroupe(String nomGroupe) {
		NomGroupe = nomGroupe;
	}


	public Date getDateCreation() {
		return dateCreation;
	}


	public void setDateCreation(Date dateCreation) {
		this.dateCreation = dateCreation;
	}


	public int getIdUser() {
		return idUser;
	}


	public void setIdUser(int idUser) {
		this.idUser = idUser;
	}


	public List<Groupe> getListeGroupes() {
		return listeGroupes;
	}


	public void setListeGroupes(List<Groupe> listeGroupes) {
		this.listeGroupes = listeGroupes;
	}


	public UserMetier getMetier() {
		return metier;
	}


	public void setMetier(UserMetier metier) {
		this.metier = metier;
	}


	public String getUsername() {
		return username;
	}


	public void setUsername(String username) {
		this.username = username;
	}


	public int getNbGroupes() {
		return nbGroupes;
	}


	public void setNbGroupes(int nbGroupes) {
		this.nbGroupes = nbGroupes;
	}


	public int getIdGroupe() {
		return idGroupe;
	}


	public void setIdGroupe(int idGroupe) {
		this.idGroupe = idGroupe;
	}






}







