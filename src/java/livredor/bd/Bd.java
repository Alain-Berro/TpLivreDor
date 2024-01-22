package livredor.bd;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import livredor.metier.MessageDor;


/**
 * Classe en charge de la base de données.
 */
public class Bd
{
	/*---------*/
	/* Données */
	/*---------*/

	private static Connection cx;

	/*----- Données de connexion -----*/
	private static final String URL ="jdbc:mysql://localhost:3307/ensg_berro";
	private static final String LOGIN = "berro";
	private static final String PASSWORD = "berro";


	/*----------*/
	/* Méthodes */
	/*----------*/

	/**
	 * Crée la connexion avec la base de données.
	 */
	private static void connexion() throws ExceptionLivreDor
		{
		/*----- Chargement du pilote pour la BD -----*/
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			}
		catch (ClassNotFoundException ex)
			{
			throw new ExceptionLivreDor("Exception connexion() : Pilote MySql introuvable - " + ex.getMessage());
			}

		/*----- Ouverture de la connexion -----*/
		try {
			Bd.cx = DriverManager.getConnection(URL,LOGIN,PASSWORD);
			}
		catch (SQLException ex)
			{
			throw new ExceptionLivreDor("Exception connexion() : Problème de connexion à la base de données - " + ex.getMessage());
			}
		}


	/**
	 * Méthode pour enregistrer un message.
	 *
	 * Cette méthode utilise un PreparedStatement. Dans ce cas, il n'est pas
	 * possible d'avoir un retour sur la clé auto-générée par la BD.
	 */
	public static int enregistrerPrepared (MessageDor msg) throws ExceptionLivreDor
		{
		/*----- Création éventuelle de la connexion à la base de données -----*/
		if (Bd.cx == null)
			Bd.connexion();

		/*----- Requête SQL -----*/
		String sql = "INSERT INTO Message(Texte,Pseudo) VALUES (?, ?)";
		int nb;

		/*----- Ouverture de l'espace de requête -----*/
		try (PreparedStatement st = Bd.cx.prepareStatement(sql))
			{
			/*----- Insertion du message -----*/
			st.setString(1, msg.getMessage());
			st.setString(2, msg.getPseudo());

			nb = st.executeUpdate();
			}
		catch (SQLException ex)
			{
			throw new ExceptionLivreDor("Exception enregistrerPrepared() : " + ex.getMessage());
			}

		return nb;
		}


	/**
	 * Méthode pour lire les messages.
	 */
	public static MessageDor lireMessage (String id) throws ExceptionLivreDor
		{
		/*----- Création éventuelle de la connexion à la base de données -----*/
		if (Bd.cx == null)
			Bd.connexion();

		/*----- Requête SQL -----*/
		String sql = "SELECT NumMsg, Texte, Pseudo FROM Message WHERE NumMsg = ?";

		/*----- Ouverture de l'espace de requête -----*/
		try (PreparedStatement st = Bd.cx.prepareStatement(sql))
			{
			st.setLong(1, Long.parseLong(id));

			/*----- Exécution de la requête -----*/
			try (ResultSet rs = st.executeQuery())
				{
				/*----- Lecture du contenu du ResultSet -----*/
				rs.next();
				return new MessageDor(rs.getLong(1), rs.getString(2), rs.getString(3));
				}
			}
		catch (SQLException ex)
			{
			throw new ExceptionLivreDor("Exception lireMessage() : " + ex.getMessage());
			}
		}


	/**
	 * Méthode pour lire les messages.
	 */
	public static ArrayList<MessageDor> lireMessages () throws ExceptionLivreDor
		{
		/*----- Création éventuelle de la connexion à la base de données -----*/
		if (Bd.cx == null)
			Bd.connexion();

		/*----- Liste des messages -----*/
		ArrayList<MessageDor> liste = new ArrayList<>();

		/*----- Requête SQL -----*/
		String sql = "SELECT NumMsg, Texte,Pseudo FROM Message ORDER BY NumMsg";

		/*----- Ouverture de l'espace de requête -----*/
		try (PreparedStatement st = Bd.cx.prepareStatement(sql))
			{
			/*----- Exécution de la requête -----*/
			try (ResultSet rs = st.executeQuery())
				{
				/*----- Lecture du contenu du ResultSet -----*/
				while (rs.next())
					liste.add(new MessageDor(rs.getLong(1), rs.getString(2), rs.getString(3)));
				}
			}
		catch (SQLException ex)
			{
			throw new ExceptionLivreDor("Exception lireMessages() : " + ex.getMessage());
			}

		return liste;
		}


	/**
	 * Méthode pour modifier un message.
	 */
	public static void modifierMessage (MessageDor m) throws ExceptionLivreDor
		{
		/*----- Création éventuelle de la connexion à la base de données -----*/
		if (Bd.cx == null)
			Bd.connexion();

		/*----- Requête SQL -----*/
		String sql = "UPDATE Message SET Pseudo = ?, Texte = ? WHERE NumMsg = ?";

		/*----- Ouverture de l'espace de requête -----*/
		try (PreparedStatement st = Bd.cx.prepareStatement(sql))
			{
			/*----- Modification -----*/
			st.setString(1, m.getPseudo());
			st.setString(2, m.getMessage());
			st.setLong(3, m.getId());

			st.executeUpdate();
			}
		catch (SQLException ex)
			{
			throw new ExceptionLivreDor("Exception modifierMessage() : " + ex.getMessage());
			}
		}


	/**
	 * Méthode pour effacer une liste de messages.
	 */
	public static void effacerMessage (String[] liste) throws ExceptionLivreDor
		{
		/*----- Création éventuelle de la connexion à la base de données -----*/
		if (Bd.cx == null)
			Bd.connexion();

		/*----- Requête SQL -----*/
		String sql = "DELETE FROM Message WHERE NumMsg = ?";

		/*----- Ouverture de l'espace de requête -----*/
		try (PreparedStatement st = Bd.cx.prepareStatement(sql))
			{
			/*----- Suppression -----*/
			for (String lst : liste)
				{
				st.setLong(1, Long.parseLong(lst));
				st.executeUpdate();
				}
			}
		catch (SQLException ex)
			{
			throw new ExceptionLivreDor("Exception effacerMessage() : " + ex.getMessage());
			}
		}


	/**
	 * Méthode pour effacer un message d'identifiant 'id'.
	 */
	public static void effacerMessage (String id) throws ExceptionLivreDor
		{
		/*----- Création éventuelle de la connexion à la base de données -----*/
		if (Bd.cx == null)
			Bd.connexion();

		/*----- Requête SQL -----*/
		String sql = "DELETE FROM Message WHERE NumMsg = ?";

		/*----- Ouverture de l'espace de requête -----*/
		try (PreparedStatement st = Bd.cx.prepareStatement(sql))
			{
			/*----- Suppression -----*/
			st.setLong(1, Long.parseLong(id));
			st.executeUpdate();
			}
		catch (SQLException ex)
			{
			throw new ExceptionLivreDor("Exception effacerMessage : " + ex.getMessage());
			}
		}


	/*----------------------------*/
	/* Programme principal (test) */
	/*----------------------------*/

	public static void main (String[] s)
		{
		try {
			Bd.connexion();

			/*----- Enregistement d'un message avec récupération de la clé primaire générée -----*/
//			MessageDor m = new MessageDor("Bonjour", "Main de \"Bd.java\"");
//			Bd.enregistrerPrepared(m);
//
//			System.out.println("Id du nouveau message : " + m.getId());

			/*----- Suppression de message -----*/
//			String[] tab = { "6083", "6084" };
//			Bd.effacerMessage(tab);

			/*----- Lecture des messages -----*/
			ArrayList<MessageDor> l = Bd.lireMessages();
			l.forEach(System.out::println);
			}
		catch (ExceptionLivreDor ex)
			{
			System.out.println(ex.getMessage());
			}
		}

} /*----- Fin de la classe Bd -----*/
