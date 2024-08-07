
import javax.swing.*;

import views.Dashboard;
import views.Login;

public class Main {

	public static void main(String[] args) {
		nimbusUi();

		JFrame frame = new Dashboard();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
	}

	private static void nimbusUi() {
		try {
			for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
				if ("Nimbus".equals(info.getName())) {
					UIManager.setLookAndFeel(info.getClassName());
					break;
				}
			}
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException
			| UnsupportedLookAndFeelException ex) {
			ex.printStackTrace();
		}
	}
}
