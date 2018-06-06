package model;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the employer database table.
 * 
 */
@Entity
@NamedQuery(name="Employer.findAll", query="SELECT e FROM Employer e")
public class Employer implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int identifiant;

	private String type_de_contrat;

	public Employer() {
	}

	public int getIdentifiant() {
		return this.identifiant;
	}

	public void setIdentifiant(int identifiant) {
		this.identifiant = identifiant;
	}

	public String getType_de_contrat() {
		return this.type_de_contrat;
	}

	public void setType_de_contrat(String type_de_contrat) {
		this.type_de_contrat = type_de_contrat;
	}

}