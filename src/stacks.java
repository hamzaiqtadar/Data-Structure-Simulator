import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Graphics;
import java.io.File;
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.SwingWorker;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.awt.BorderLayout;


public class stacks implements MouseListener{
	int size=10;
	private JFrame frame;
	private JLabel[] eles =new JLabel[size];
	private JLabel[] link= new JLabel[size];
	int stack[]=new int[size];
	static int cnt=10;
	private JPanel panel;
	private JTextField textField;
	private JLabel label,label_1;
	private JButton button,button_1;
	private JLabel lblShow,label_2;
	/**
	 * Create the application.
	 */
	public stacks() {

		for(int i=0;i<size;i++){
			eles[i]=new JLabel("");
			link[i]=new JLabel("");
			link[i].setVisible(false);
			link[i].setIcon(new ImageIcon(stacks.class.getResource("/rr/ic_action_arrow_bottom.png")));
			eles[i].setFont(new Font("Comic Sans MS", Font.PLAIN, 15));
			eles[i].setHorizontalAlignment(SwingConstants.CENTER);
			eles[i].setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
		}
		initialize();
	}

	private void initialize() {
		frame = new JFrame();
		panel = new stp();
		panel.setLayout(null);
		frame.setTitle("Stacks");
		for(int i=0;i<size;i++){
			eles[i].setBounds(150, 55+(62*i), 35, 35);
			link[i].setBounds(eles[i].getX(),eles[i].getY()+32,32,32);
			if(i<size-1)
				panel.add(link[i]);
			panel.add(eles[i]);
		}
		frame.setResizable(false);
		frame.getContentPane().setBackground(Color.WHITE);
		frame.getContentPane().setLayout(new BorderLayout(0, 0));


		frame.getContentPane().add(panel, BorderLayout.CENTER);


		label = new JLabel("");
		label.setIcon(new ImageIcon(stacks.class.getResource("/rr/ic_action_arrow_right.png")));
		//	label.setIcon(new ImageIcon(alldetails.class.getResource("/rr/add.png")));
		label.setSize(48, 48);
		label.setVisible(false);
		label.setBounds(93,eles[size-1].getY()-8, 48, 48);
		panel.add(label);

		label_1 = new JLabel("TOP");
		label_1.setHorizontalAlignment(SwingConstants.CENTER);
		label_1.setFont(new Font("Comic Sans MS", Font.PLAIN, 17));
		label_1.setBackground(Color.WHITE);
		label_1.setBounds(label.getX()-48,label.getY(), 48, 48);
		label_1.setVisible(false);
		panel.add(label_1);
		button = new JButton("Push");
		button.addMouseListener(this);
		button.setFont(new Font("Tahoma", Font.PLAIN, 14));
		button.setBounds(266, 234, 89, 33);
		panel.add(button);

		textField = new JTextField();
		textField.setHorizontalAlignment(SwingConstants.CENTER);
		textField.setFont(new Font("Tahoma", Font.PLAIN, 15));
		textField.setColumns(10);
		textField.setBounds(269, 178, 86, 45);
		panel.add(textField);

		button_1 = new JButton("Pop");
		button_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		button_1.setBounds(266, 321, 89, 33);
		button_1.addMouseListener(this);
		panel.add(button_1);

		label_2 = new JLabel("");
		label_2.setHorizontalAlignment(SwingConstants.CENTER);
		label_2.setFont(new Font("Tahoma", Font.PLAIN, 14));
		label_2.setBorder(BorderFactory.createLineBorder(Color.GRAY, 1));
		label_2.setBounds(266, 365, 86, 45);
		panel.add(label_2);

		JButton btnBack = new JButton("Back");
		btnBack.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				datastructures o= new datastructures();
				frame.dispose();
			}
		});
		btnBack.setBounds(10, 11, 55, 23);
		panel.add(btnBack);
		
		lblShow = new JLabel("SHow");
		lblShow.setForeground(Color.BLUE);
		lblShow.setFont(new Font("Comic Sans MS", Font.PLAIN, 18));
		lblShow.setHorizontalAlignment(SwingConstants.CENTER);
		lblShow.setBounds(215, 434, 200, 50);
		panel.add(lblShow);
		frame.setSize(850, 700);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);

		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	void pop(){
		
		if(cnt==size-1){
			
			label_2.setText(eles[cnt].getText());
			eles[cnt].setText("");
			eles[cnt].setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
			cnt++;
			return;
		}
		cnt++;
		label.setLocation(93,eles[cnt].getY()-8);
		label_1.setLocation(label.getX()-48,label.getY());
		label_2.setText(eles[cnt-1].getText());
		try {
			Thread.sleep(500);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		eles[cnt-1].setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
		link[cnt-1].setVisible(false);
		eles[cnt-1].setText("");
		
	}
	void push(int index, int ele){
		
		if(isEmpty()){
			cnt--;
			eles[cnt].setText(""+ele);
			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			eles[cnt].setBorder(BorderFactory.createLineBorder(Color.BLUE, 3));
			label.setVisible(true);
			label_1.setVisible(true);
			return;
		}
		cnt--;
		eles[cnt].setText(""+ele);
		eles[cnt].setBorder(BorderFactory.createLineBorder(Color.BLUE, 3));
		try {
			Thread.sleep(500);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		link[cnt].setVisible(true);
		try {
			Thread.sleep(500);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}label.setLocation(93,eles[cnt].getY()-8);
		label_1.setLocation(label.getX()-48,label.getY());
	}
	boolean isEmpty(){
			
		return cnt==size;
	}
	boolean isFull(){
		return cnt==0;
	}
	void dopop(){
		SwingWorker<Void, Integer> worker = new SwingWorker<Void, Integer>() {

			@Override
			protected Void doInBackground() throws Exception {
				// TODO Auto-generated method stub
				pop();
				return null;
			}

			@Override
			protected void process(List<Integer> arg0) {
				// TODO Auto-generated method stub
				super.process(arg0);
			}
			
		};
		worker.execute();
	}
	void dopush(final int p){
		
		SwingWorker<Void, Integer> worker = new SwingWorker<Void, Integer>() {

			@Override
			protected Void doInBackground() throws Exception {
				push(cnt,p);
				return null;
			}

			@Override
			protected void done() {
				// TODO Auto-generated method stub
				super.done();
			}

		};
		worker.execute();
	}

	@Override
	public void mouseClicked(MouseEvent me) {
		Object obj1 = me.getSource();
		if(obj1==button){
			if(!textField.getText().toString().equals("")){
				if(!isFull()){
				int t= Integer.parseInt(textField.getText().toString());
				dopush(t);
				textField.setText("");
				textField.requestFocus();
				}
				else
					lblShow.setText("Stack is Full");
			}
		}
		if(obj1==button_1){
			
			if(!isEmpty()){
				dopop();
			}
		}
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}
}
class stp extends JPanel 
{

	private static final long serialVersionUID = 1L;
	//creating references variables for images       
	BufferedImage bimg;

	//contructor
	stp()
	{
		//setting logo and background
		try
		{
			bimg = ImageIO.read(new File("resources/stacks.PNG"));
		}
		catch (Exception e){}    
	}

	//drawing background
	public void paintComponent(Graphics g)
	{
		//super.paintComponent(g);

		g.drawImage(bimg,0,0,null);

	}

}//ends Mainp here

