public class Main {

    public static void main(String[] args) {
        /*
            Le modèle est indépendant il n'a pas besoin de la vue et du controleur pour exister
         */
        Car car = new Car();

        /*
            La vue ne peut exister via le modèle, la vue observe forcément quelque chose cependant via le DP Observer
            l'injection ne se fait pas via le constructeur mais via la méthode update dans carView qui en paramètre
            comprend le nouvel état du modèle
         */
        CarView carView = new CarView();

        /*
            Le controleur a besoin de la vue et du modèle, la raison est ci-dessous
         */
        CarController carController = new CarController(car, carView);

        /*
            Cette méthode possèdent les listeners de la vue, par exemple l'appui sur un boutton ou une touche
            puis elle va appeler la bonne méthode du modèle en fonction de l'action voulu par l'utilisateur.
            La vue ne doit pas directement provoquer un changement d'état du modèle, elle a juste le droit de récupérer
            les valeurs du modèle ce qu'on fait dans la vue via les getters ce qui ne casse pas ce principe.
         */
        carController.listenView();

        /*
            La vue observe désormais le modèle
         */
        car.addObserver(carView);

        /*
            Création d'un thread pour que le modèle évolue en même temps que la vue affiche ses changements d'états
            Typiquement la vue étant le tableau de bord de la voiture et le modèle étant le moteur
            On a donc deux threads : le principal qui est crée automatiquement au lancement du programme qui sera
            occupé avec la vue (la JFrame) et ce thread qui sera occupé à varier la vitesse du moteur
         */
        Thread thread = new Thread(car);

        /*
            Lancement de la tâche, une tâche étant une classe qui implémente Runnable
            et qui possède donc une méthode run qui fait partie du contrat.
            C'est la méthode run qui est lancé lors du thread.start()
         */
        thread.start();
    }
}
