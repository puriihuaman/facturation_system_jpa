package views;

import javax.swing.*;
import java.awt.*;

public class UI extends JFrame {
	public UI() {
		nimbusUi();

		setTitle("UI");
		setBounds(500, 300, 400, 400);
		setLayout(new BorderLayout());
		setFont(new Font("Roboto", Font.PLAIN, 12));

		// JPanel menus = new JPanel();
		// menus.setLayout(new FlowLayout());
		// add(menus, BorderLayout.NORTH);
		// JPanel dashboard = new Dashboard();
		// add(dashboard, BorderLayout.SOUTH);
	}

	private void nimbusUi() {
		try {
			for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
				if ("Nimbus".equals(info.getName())) {
					UIManager.setLookAndFeel(info.getClassName());
					break;
				}
			}
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException |
						 UnsupportedLookAndFeelException ex) {
			ex.printStackTrace();
		}
	}
}
