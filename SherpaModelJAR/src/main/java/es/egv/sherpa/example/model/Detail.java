package es.egv.sherpa.example.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;

/**
 * Entity implementation class for Entity: Detail
 *
 */
@Entity
@SequenceGenerator(name="DETAILSEQ", sequenceName="DETAIL_SEQ", initialValue=10, allocationSize=3)
public class Detail implements Serializable {

	   
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="DETAILSEQ")
	private Integer iddetail;
	private String postalcode;
	private String city;
	
	private static final long serialVersionUID = 1L;

	public Detail() {
		super();
	}   
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Detail [iddetail=");
		builder.append(iddetail);
		builder.append(", postalcode=");
		builder.append(postalcode);
		builder.append(", city=");
		builder.append(city);
		builder.append("]");
		return builder.toString();
	}
	/**
	 * @return the iddetail
	 */
	public Integer getIddetail() {
		return iddetail;
	}
	/**
	 * @param iddetail the iddetail to set
	 */
	public void setIddetail(Integer iddetail) {
		this.iddetail = iddetail;
	}
	public String getPostalcode() {
		return this.postalcode;
	}

	public void setPostalcode(String postalcode) {
		this.postalcode = postalcode;
	}   
	public String getCity() {
		return this.city;
	}

	public void setCity(String city) {
		this.city = city;
	}
   
}
