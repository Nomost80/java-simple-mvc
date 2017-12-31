import java.util.Observable;

public class Car extends Observable implements Runnable {
	// Cette énumération est là pour illustrer un changement d'état provoquer par l'utilisateur (la vue)
	private CarMode carMode = CarMode.NORMAL;
	// La vitesse du moteur, c'est la partie du modèle qui va évoluer dans le temps en fonction du mode de la voiture
	private int speed = 0;

	@Override
	public void run() {
		while (true) { // On fait en sorte que ce soit exécuter en boucle pour que notre modèle ne cesse d'évoluer

			// Quand tu seras en mode sport tu verras que sur la JFrame la vitesse évolue plus vite
			if (carMode == CarMode.NORMAL)
				this.speed += 10;
			else if (carMode == CarMode.SPORT)
				this.speed += 30;

			/*
				Ces deux méthodes permettent d'indiquer qu'un changement d'état du modèle à eu lieu
				En effet on vient de modifier la vitesse du moteur.
				Suite à l'appel de ces deux méthodes, la méthode update dans carView va être exécuté
			 */
			this.setChanged();
			this.notifyObservers();

			/*
				On met en pause ce thread pour éviter que la vitesse évolue trop vite pour qu'on ai le temps d'observer
				les différents patterns. Le InterruptedException est là parce qu'on peut mettre un thread de l'état wait
				à actif ce qui exécutera ce le catch
			 */
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	// Getter qu'utilise la vue pour afficher la vitesse dans un label
	public int getSpeed() {
		return speed;
	}

	// Geter qu'utilise la vue pour afficher le mode dans un label, et pour ajouter les modes de la voiture dans la liste
	public CarMode getCarMode() {
		return carMode;
	}

	// Setter qu'utilise le controleur pour changer le mode
	public void setCarMode(CarMode carMode) {
		this.carMode = carMode;
	}
}
