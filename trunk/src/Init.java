import persistence.ConnectionSingleton;
import view.Desktop;


public class Init {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		ConnectionSingleton.getInstance();
		Desktop d = new Desktop();
		d.initComponents();
		d.setVisible(true);
	}

}
