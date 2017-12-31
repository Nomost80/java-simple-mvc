import javax.swing.*;
import java.util.Observable;
import java.util.Observer;

public class CarView implements Observer {
	private JFrame frame;
	private JPanel panel;
	private JComboBox<CarMode> modeSelector;
	private JLabel speedLabel;
	private JLabel modeLabel;

	public CarView() {
		this.frame = new JFrame("Tableau de bord");
		this.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.frame.setSize(500, 500);
		this.frame.setLocationRelativeTo(null);
		this.frame.setVisible(true);
		this.panel = new JPanel();
		this.modeSelector = new JComboBox<>();
		this.modeSelector.addItem(CarMode.NORMAL);
		this.modeSelector.addItem(CarMode.SPORT);
		this.speedLabel = new JLabel();
		this.modeLabel = new JLabel();
		this.panel.add(this.modeSelector);
		this.panel.add(this.speedLabel);
		this.panel.add(this.modeLabel);
		this.frame.setContentPane(this.panel);
	}

	// Méthode appelé à chaque changement d'état du modèle
	@Override
	public void update(Observable arg0, Object arg1) {
		// On caste l'Observable qui est en fait la voiture en un objet de type voiture
		Car car = (Car)arg0;
		System.out.println(car.getSpeed());
		// On modifie la valeur des labels tout simplement
		this.speedLabel.setText("Vitesse du moteur : " + Integer.toString(car.getSpeed()));
		this.modeLabel.setText("Mode de la voiture : " + car.getCarMode().toString());
	}

	// Getter qu'utilise le controleur pour ajouter un eventListener sur la liste (ComboBox)
	public JComboBox<CarMode> getModeSelector() {
		return modeSelector;
	}
}
