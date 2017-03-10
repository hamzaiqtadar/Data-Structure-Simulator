import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;


public class datastructures {

	private JFrame frame;


	/**
	 * Create the application.
	 */
	public datastructures() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setResizable(false);frame.setSize(501, 441);
		frame.getContentPane().setBackground(Color.WHITE);
		frame.getContentPane().setLayout(null);
		
		JButton btnStacks = new JButton("Stacks");
		btnStacks.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				stacks s=new stacks();
				frame.dispose();
			}
		});
		btnStacks.setBounds(200, 106, 89, 23);
		frame.getContentPane().add(btnStacks);
		
		JButton btnHeaps = new JButton("Heaps");
		
		btnHeaps.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				heaps d= new heaps();
				frame.dispose();
			}
		});
		btnHeaps.setBounds(200, 176, 89, 23);
		frame.getContentPane().add(btnHeaps);
		
		JButton btnQueues = new JButton("Queues");
		btnQueues.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				queues k= new queues();
				frame.dispose();
			}
		});
		btnQueues.setBounds(200, 249, 89, 23);
		frame.getContentPane().add(btnQueues);
		
		JButton btnBack = new JButton("Back");
		btnBack.setBounds(10, 11, 55, 23);
		frame.getContentPane().add(btnBack);
		
		JButton btnMergeSort = new JButton("Merge Sort");
		btnMergeSort.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				MergeSort m= new MergeSort();
				frame.dispose();
			}
		});
		btnMergeSort.setBounds(200, 319, 89, 23);
		frame.getContentPane().add(btnMergeSort);
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}
}
