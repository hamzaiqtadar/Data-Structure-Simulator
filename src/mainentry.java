import java.awt.EventQueue;

import javax.swing.JFrame;

import java.awt.Color;

import javax.swing.JPanel;

import java.awt.BorderLayout;

import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.UIManager;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;


public class mainentry {

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				
				try {
					UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
					mainentry window = new mainentry();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public mainentry() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setSize(501, 441);
		frame.getContentPane().setBackground(Color.WHITE);
		frame.setLocationRelativeTo(null);
		JPanel panel = new JPanel();
		panel.setBackground(Color.WHITE);
		frame.getContentPane().add(panel, BorderLayout.CENTER);
		panel.setLayout(null);
		
		JButton btnDataStructures = new JButton("Data Structures");
		btnDataStructures.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				datastructures gh= new datastructures();
				frame.dispose();
			}
		});
		btnDataStructures.setBounds(183, 120, 116, 23);
		panel.add(btnDataStructures);
		
		JButton btnInstructions = new JButton("Instructions");
		btnInstructions.setBounds(183, 187, 116, 23);
		panel.add(btnInstructions);
		
		JButton btnAbout = new JButton("About");
		btnAbout.setBounds(183, 255, 116, 23);
		panel.add(btnAbout);
		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}
