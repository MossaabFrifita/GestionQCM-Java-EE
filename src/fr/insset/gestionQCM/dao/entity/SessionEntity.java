package fr.insset.gestionQCM.dao.entity;


import static javax.persistence.GenerationType.IDENTITY;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


@Entity
@Table(name = "session", catalog = "qcm_db")
public class SessionEntity implements java.io.Serializable {


	private static final long serialVersionUID = 1L;
	private int idSession;
	private int nbEssai;
	private Date  dateDeb;
	private Date dateFin;
	private Date duree;
	private boolean showResult;

	private int idQcm;
	
	private Qcm qcm;

	public SessionEntity() {
	}

	public SessionEntity (int nbEssai, Date dateDeb, Date dateFin, Date duree, boolean showResult,
			 int idQcm) {
	
		this.nbEssai = nbEssai;
		this.dateDeb = dateDeb;
		this.dateFin = dateFin;
		this.duree = duree;
		this.showResult = showResult;

		this.idQcm = idQcm;
	}

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "id_session", unique = true, nullable = false)
	public int getIdSession() {
		return idSession;
	}

	public void setIdSession(int idSession) {
		this.idSession = idSession;
	}

	@Column(name = "NbEssai", nullable = false)
	public int getNbEssai() {
		return this.nbEssai;
	}



	public void setNbEssai(int nbEssai) {
		this.nbEssai = nbEssai;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "DateDeb", nullable = false)
	public Date getDateDeb() {
		return this.dateDeb;
	}

	public void setDateDeb(Date dateDeb) {
		this.dateDeb = dateDeb;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "DateFin", nullable = false)
	public Date getDateFin() {
		return this.dateFin;
	}

	public void setDateFin(Date dateFin) {
		this.dateFin = dateFin;
	}
	
	@Temporal(TemporalType.TIME)
	@Column(name = "Duree", nullable = false)
	public Date getDuree() {
		return this.duree;
	}

	public void setDuree(Date duree) {
		this.duree = duree;
	}

	@Column(name = "ShowResult", nullable = false)
	public boolean isShowResult() {
		return this.showResult;
	}

	public void setShowResult(boolean showResult) {
		this.showResult = showResult;
	}



	@Column(name = "id_qcm", nullable = false)
	public int getIdQcm() {
		return this.idQcm;
	}

	public void setIdQcm(int idQcm) {
		this.idQcm = idQcm;
	}

	@ManyToOne
	@JoinColumn(name="id_qcm", referencedColumnName="id_qcm", insertable=false, updatable=false)
	public Qcm getQcm() {
		return qcm;
	}

	public void setQcm(Qcm qcm) {
		this.qcm = qcm;
	}

}
