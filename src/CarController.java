public class CarController {
    private Car car;
    private CarView carView;

    public CarController(Car car, CarView carView) {
        this.car = car;
        this.carView = carView;
    }

    public void listenView() {
        // Listener qui permet de détecter un changement de mode dans la liste (JComboBox)
        // C'est pour cela que le controleur a besoin de la vue.
        this.carView.getModeSelector().addItemListener(itemEvent -> {
            // Suite à un changement de mode on set la propriété du model correspondant au mode
            // C'est pour cela que le controleur a besoin du model
            this.changeMode((CarMode)itemEvent.getItem());
        });
        // Pour la syntaxe particulière du -> {} C'est une façon simplifié d'écrire une méthode anonyme (sans nom)
        // itemEvent étant un paramètre qu'on va utiliser pour savoir ce que l'utilisateur a choisi dans la liste
    }

    // La méthode qui appelle directement le setter du modèle
    private void changeMode(CarMode carMode) {
        this.car.setCarMode(carMode);
    }
}