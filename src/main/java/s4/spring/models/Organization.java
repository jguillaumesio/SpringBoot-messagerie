package s4.spring.models;
 
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
 
@Entity
@Table(name="organization")
public class Organization {
	@Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private int id;
     
    private String name;
    private String domain;
    private String aliases;
     
    @OneToMany(cascade=CascadeType.ALL,mappedBy="organization")
    private List<Groupe> groupes;

	public String getName() {
		return name = ((this.name == null) ? "N/A" : this.name);
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDomain() {
		return domain = ((this.domain == null) ? "N/A" : this.domain);
	}

	public void setDomain(String domain) {
		this.domain = domain;
	}

	public String getAliases() {
		return aliases = ((this.aliases == null) ? "N/A" : this.name);
	}

	public void setAliases(String aliases) {
		this.aliases = aliases;
	}

	public List<Groupe> getGroupes() {
		return groupes = ((this.groupes == null) ? null : this.groupes);
	}

	public void setGroupes(List<Groupe> groupes) {
		this.groupes = groupes;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}


}