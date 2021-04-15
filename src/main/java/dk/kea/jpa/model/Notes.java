package dk.kea.jpa.model;


// entitetsklasse i jpa - tabel kan specificeres
public class Notes {

	private Long id;

	// kolonnenavn kan specificeres
	private String description;

    //FetchType Lazy og Eager - hente ved tilgang til eller straks, når parent hentes

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
