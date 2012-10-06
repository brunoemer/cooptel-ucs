import persistence.ConnectionSingleton;


public class Init {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		ConnectionSingleton.getInstance();
		System.out.println("foi");
	}

}
