package dk.kea.jpa.model;


import javax.persistence.*;

// entitetsklasse i jpa - tabel kan specificeres
@Entity
@Table(name = "notes")
public class Notes {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;

	// kolonnenavn kan specificeres
	private String description;

    //FetchType Lazy og Eager - hente ved tilgang til eller straks, når parent hentes
	@OneToOne
	//@JoinColumn(name = "recipe_id")
	private Recipe recipe;

	public Notes() {
	}

	public Notes(String description, Recipe recipe) {
		this.description = description;
		this.recipe = recipe;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Recipe getRecipe() {
		return recipe;
	}

	public void setRecipe(Recipe recipe) {
		this.recipe = recipe;
	}
}
