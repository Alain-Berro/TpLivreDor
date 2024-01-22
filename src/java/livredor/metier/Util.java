package livredor.metier;


public class Util
{
	/**
	 * Retourne la chaîne de caractères checked="checked" si num appratient
	 * à la liste, "" sinon.
	 */
	public static String isChecked (String[] liste, long num)
		{
		if (liste != null)
			{
			int cpt = 0;
			while (cpt < liste.length && !liste[cpt].equals(num+"")) cpt++;

			if (cpt != liste.length)
				return " checked=\"checked\" ";
			}

		return "";
		}

} /*----- Fin de la classe Utilitaire -----*/
