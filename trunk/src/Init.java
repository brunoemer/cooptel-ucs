import persistence.ConnectionSingleton;
import view.Desktop;
import view.FormLogin;


public class Init {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		ConnectionSingleton.getInstance();
                FormLogin f = new FormLogin();
		f.setVisible(true);
		/*
                Desktop d = new Desktop();
		d.initComponents();
		d.setVisible(true);
                 * 
                 */
	}

}
