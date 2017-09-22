package es.egv.sherpa.example.model;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.SequenceGenerator;

/**
 * Entity implementation class for Entity: Master
 *
 */
@Entity
@SequenceGenerator(name="MASTERSEQ", sequenceName="MASTER_SEQ", initialValue=10, allocationSize=5)
public class Master implements Serializable {

	   
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="MASTERSEQ")
	private Integer idmaster;
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Master [idmaster=");
		builder.append(idmaster);
		builder.append(", username=");
		builder.append(username);
		builder.append(", detail=");
		builder.append(detail);
		builder.append("]");
		return builder.toString();
	}

	private String username;
	
	@OneToOne(cascade=CascadeType.ALL)
	@PrimaryKeyJoinColumn(name="iddetail")
	private Detail detail;
	private static final long serialVersionUID = 1L;

	/**
	 * @return the detail
	 */
	public Detail getDetail() {
		return detail;
	}
	/**
	 * @param detail the detail to set
	 */
	public void setDetail(Detail detail) {
		this.detail = detail;
	}
	public Master() {
		super();
	}   
	public Integer getIdmaster() {
		return this.idmaster;
	}

	public void setIdmaster(Integer idmaster) {
		this.idmaster = idmaster;
	}   
	public String getUsername() {
		return this.username;
	}

	public void setUsername(String username) {
		this.username = username;
	}
   
}
