/**
 * Cette méthode "Ajax" lance la suppression d'un tuple en base de données.
 *
 * Le paramètre passé est l'identifiant du tuple à supprimer.
 */
function suppr ()
	{
	// Confirmation
	if (window.confirm("Voulez-vous vraiment supprimer cet élément ?"))
		{
		var id_tuple = this.getAttribute("value");

		// Objet XMLHttpRequest.
		var xhr = new XMLHttpRequest();
		
		// Requête au serveur avec les paramètres éventuels.
		xhr.open("GET","SupprMsgAjax?id_tuple=" + id_tuple);

		// On précise ce que l'on va faire quand on aura reçu la réponse du serveur.
		xhr.onload = function()
			{
			// Si la requête http s'est bien passée.
			if (xhr.status === 200)
				{
				// On vérifie si la suppression en base de données s'est bien passée.
				// Pour cela le retour de la servlet dans le fichier xml doit être
				// l'id passé initialement en paramètre. Sinon il s'agit d'un
				// message d'erreur envoyé par le serveur.
				var msg = xhr.responseXML.getElementsByTagName("id")[0].firstChild.nodeValue;

				if (id_tuple === msg)
					{
					// Elément html que l'on va supprimer.
					var elt = document.getElementById("tr_" + id_tuple);
					elt.parentNode.removeChild(elt);
					}
				else
					// On affiche le message d'erreur.
					window.alert (msg);
				}
			};

		// Envoie de la requête.
		xhr.send(null);
		}
	}


/**
 * Lancement après le chargement du DOM.
 */
document.addEventListener("DOMContentLoaded", () => {

	var liste = document.getElementsByClassName("commande");

	for (var i=0; i<liste.length; i++)
		liste[i].addEventListener("click",suppr);

});
