package livredor.metier;


/**
 * Classe Message d'or.
 */
public class MessageDor implements Comparable<MessageDor>
{
	/*---------*/
	/* Données */
	/*---------*/

	private long id;
	private String message;
	private String pseudo;


	/*---------------*/
	/* Constructeurs */
	/*---------------*/

	public MessageDor () {}

	public MessageDor (String msg, String pseudo)
		{
		this.message = msg;
		this.pseudo = pseudo;
		}

	public MessageDor (long id, String msg, String pseudo)
		{
		this.id = id;
		this.message = msg;
		this.pseudo = pseudo;
		}


	/*----------*/
	/* Méthodes */
	/*----------*/

	public long getId () { return this.id; }

	public void setId (long id) { this.id = id; }

	public String getMessage () { return this.message; }

	public void setMessage (String message) { this.message = message; }

	public String getPseudo () { return this.pseudo; }

	public void setPseudo (String pseudo) { this.pseudo = pseudo; }

	@Override
	public String toString() { return this.getId() + " - " + this.getPseudo() + " - " + this.getMessage(); }

	@Override
	public int compareTo(MessageDor t) { return Long.compare(this.id, t.id); }

} /*----- Fin de la classe MessageDor -----*/
