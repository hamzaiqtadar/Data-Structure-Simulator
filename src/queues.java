import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.SwingWorker;

import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.JButton;
import javax.swing.JTextField;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;


public class queues {

	private JFrame frame;
	int size=10,count=0;
	int F=0,B=size-1;
	private JLabel Q[]= new JLabel[size];
	private JLabel Qp[]= new JLabel[size];
	private JPanel panel;
	private JLabel front,back,ft,bt,lblDeq;
	private JTextField txtEq;
	JButton btnDequeue,btnEnqueue;
	public queues() {
		for(int i=0;i<size;i++){
			Q[i]=new JLabel("");
			Qp[i]=new JLabel("");
			Qp[i].setVisible(false);
			//Qp[i].setIcon(new ImageIcon(stacks.class.getResource("/rr/ic_action_arrow_top.png")));
			Q[i].setHorizontalAlignment(SwingConstants.CENTER);
		}
		ft= new JLabel("Front");
		ft.setHorizontalAlignment(SwingConstants.RIGHT);
		ft.setFont(new Font("Comic Sans MS", Font.BOLD, 15));
		bt= new JLabel("Back");
		bt.setHorizontalAlignment(SwingConstants.RIGHT);
		bt.setFont(new Font("Comic Sans MS", Font.BOLD, 15));
		front= new JLabel("");
		back= new JLabel("");
		initialize();
	}

	private void initialize() {
		frame = new JFrame();
		frame.setBackground(Color.WHITE);
		frame.getContentPane().setBackground(Color.WHITE);
		
			front .setIcon(new ImageIcon(stacks.class.getResource("/rr/ic_action_arrow_right.png")));
			
		
		back.setIcon(new ImageIcon(stacks.class.getResource("/rr/ic_action_arrow_right.png")));
		panel = new dp();
		for(int i=0;i<4;i++){
			Q[i].setBounds(100+(i*62), 200, 30, 30);
			if(i<3){
			Qp[i].setIcon(new ImageIcon(stacks.class.getResource("/rr/ic_action_arrow_rightr.png")));
			Qp[i].setBounds(100+(i*62)+30, 200, 32, 32);
			panel.add(Qp[i]);
			}else{
				Qp[i].setIcon(new ImageIcon(stacks.class.getResource("/rr/ic_action_arrow_bottoms.png")));
				Qp[i].setBounds(100+(i*62), 200+30, 32, 32);
				panel.add(Qp[i]);
			}
			Q[i].setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
			Q[i].setFont(new Font("Comic Sans MS", Font.PLAIN, 15));
			panel.add(Q[i]);
		}
		Q[4].setBounds(100+(3*62), 200+62, 30, 30);
		Q[4].setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
		Q[4].setFont(new Font("Comic Sans MS", Font.PLAIN, 15));
		Qp[4].setIcon(new ImageIcon(stacks.class.getResource("/rr/ic_action_arrow_bottoms.png")));
		Qp[4].setBounds(100+(3*62), 200+30+62, 32, 32);
		panel.add(Qp[4]);
		Q[9].setBounds(100+(0*62), 200+62, 30, 30);
		Q[9].setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
		Q[9].setFont(new Font("Comic Sans MS", Font.PLAIN, 15));
		panel.add(Q[4]);
		Qp[9].setIcon(new ImageIcon(stacks.class.getResource("/rr/ic_action_arrow_tops.png")));
		Qp[9].setBounds(100+(0*62), 200+30, 32, 32);
		panel.add(Qp[9]);
		panel.add(Q[9]);
		for(int i=5;i<9;i++){
			Q[i].setBounds(100+((size-i-2)*62), 200+62+62, 30, 30);
			Q[i].setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
			Q[i].setFont(new Font("Comic Sans MS", Font.PLAIN, 15));
			panel.add(Q[i]);
			if(i<8){
				Qp[i].setIcon(new ImageIcon(stacks.class.getResource("/rr/ic_action_arrow_lefts.png")));
				Qp[i].setBounds(100+(size-i-2)*62-30, 200+62+62, 32, 32);
				panel.add(Qp[i]);
			}
			else{
				Qp[i].setIcon(new ImageIcon(stacks.class.getResource("/rr/ic_action_arrow_tops.png")));
				Qp[i].setBounds(100+(0*62), 200+30+62, 32, 32);
				panel.add(Qp[i]);
			}
		}
		front.setBounds(Q[0].getX()-48,Q[0].getY()-10,48,48); ft.setBounds(front.getX()-48,front.getY(),48,48); 
		 panel.add(front);panel.add(ft);
		 back.setBounds(Q[size-1].getX()-48,Q[size-1].getY()-10,48,48); bt.setBounds(back.getX()-48,back.getY(),48,48);
		 panel.add(back);panel.add(bt);
		frame.setSize(900, 700);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(new BorderLayout(0, 0));
		
		frame.getContentPane().add(panel, BorderLayout.CENTER);
		panel.setLayout(null);
		
		JLabel lblNull = new JLabel("NULL");
		lblNull.setForeground(Color.RED);
		lblNull.setFont(new Font("Comic Sans MS", Font.BOLD | Font.ITALIC, 15));
		lblNull.setHorizontalAlignment(SwingConstants.CENTER);
		lblNull.setBounds(95, 10, 49, 20);
		panel.add(lblNull);
		
		 btnEnqueue = new JButton("Enqueue");
		btnEnqueue.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				if(!txtEq.getText().toString().equals("")){
					if(!isFull()){
						int t=Integer.parseInt(txtEq.getText().toString());
						
						doEnQ(t);
					}
				}
			}
		});
		btnEnqueue.setBounds(95, 537, 89, 23);
		panel.add(btnEnqueue);
		
		btnDequeue = new JButton("Dequeue");
		btnDequeue.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				if(count>0){
					lblDeq.setText(Q[F].getText());
				btnDequeue.setEnabled(false);
				doDQ();
				}
			}
		});
		btnDequeue.setBounds(213, 537, 89, 23);
		panel.add(btnDequeue);
		
		txtEq = new JTextField();
		txtEq.setFont(new Font("Tahoma", Font.PLAIN, 15));
		txtEq.setHorizontalAlignment(SwingConstants.CENTER);
		txtEq.setText("");
		txtEq.setBounds(98, 496, 86, 30);
		panel.add(txtEq);
		txtEq.setColumns(10);
		
		 lblDeq = new JLabel("Deq");
		 lblDeq.setFont(new Font("Comic Sans MS", Font.BOLD, 15));
		lblDeq.setHorizontalAlignment(SwingConstants.CENTER);
		lblDeq.setBounds(216, 497, 86, 27);
		panel.add(lblDeq);
	}
	void doEnQ(final int E){
		SwingWorker<Void, Integer> worker = new SwingWorker<Void, Integer>() {

			@Override
			protected Void doInBackground() throws Exception {
				EnQ(E);
				return null;
			}

			@Override
			protected void process(List<Integer> arg0) {
				// TODO Auto-generated method stub
				super.process(arg0);
			}

			@Override
			protected void done() {
				// TODO Auto-generated method stub
				super.done();
			}
			 
		};
		worker.execute();
	}
	void EnQ(int E){
		B=(B+1)%size;
		Q[B].setText(""+E);
		count++;
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(B>=0 && B<=3){
			if( count>1){
				Qp[B-1].setVisible(true);
				System.out.print("b>0 c>0");
				try {
					Thread.sleep(300);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if(B==F && B!=0){
				back.setLocation(Q[B].getX(),Q[B].getY()-48);
				bt.setLocation(back.getX(),back.getY()-24);
				back.setIcon(new ImageIcon(stacks.class.getResource("/rr/ic_action_arrow_bottom.png")));
			}
//			if(F!=0){
//				Qp[B-1].setVisible(true);
//				try {
//					Thread.sleep(300);
//				} catch (InterruptedException e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				}
//			}
			else{
			back.setLocation(Q[B].getX(),Q[B].getY()-48);
			bt.setLocation(back.getX(),back.getY()-48);
			back.setIcon(new ImageIcon(stacks.class.getResource("/rr/ic_action_arrow_bottom.png")));
			}
		}
		if(B>=5 && B<9){
			if(count>1){
				Qp[B-1].setVisible(true);
				try {
					Thread.sleep(300);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if(B==F){
				back.setLocation(Q[B].getX(),Q[B].getY()+30);
				bt.setLocation(back.getX(),back.getY()+24);
				back.setIcon(new ImageIcon(stacks.class.getResource("/rr/ic_action_arrow_top.png")));
			}else{
			back.setLocation(Q[B].getX(),Q[B].getY()+30);
			bt.setLocation(back.getX(),back.getY()+48);
			back.setIcon(new ImageIcon(stacks.class.getResource("/rr/ic_action_arrow_top.png")));
			}
		}
		if(B==9){
			if(true){
				Qp[B-1].setVisible(true);
				try {
					Thread.sleep(300);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			back.setIcon(new ImageIcon(stacks.class.getResource("/rr/ic_action_arrow_right.png")));
			back.setBounds(Q[size-1].getX()-48,Q[size-1].getY()-10,48,48); bt.setBounds(back.getX()-48,back.getY(),48,48);
		}
		if(B==4){
			if(true){
				Qp[B-1].setVisible(true);
				try {
					Thread.sleep(300);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			back.setIcon(new ImageIcon(stacks.class.getResource("/rr/ic_action_arrow_lefts.png")));
			back.setBounds(Q[4].getX()+30,Q[4].getY()-10,48,48); bt.setBounds(back.getX()+48,back.getY()+10,48,48);
		}
		
	}
	void doDQ(){
		SwingWorker<Void, Integer> worker = new SwingWorker<Void, Integer>() {

			@Override
			protected Void doInBackground() throws Exception {
				// TODO Auto-generated method stub
				DQ();
				return null;
			}

			@Override
			protected void done() {
				btnDequeue.setEnabled(true);
				super.done();
			}

			@Override
			protected void process(List<Integer> arg0) {
				// TODO Auto-generated method stub
				super.process(arg0);
			}
			
		};
		worker.execute();
	}
	int DQ(){
		int y=F;
		F=(F+1)%size;
		count--;
		if(F==0){
			front.setBounds(Q[0].getX()-48,Q[0].getY()-10,48,48); ft.setBounds(front.getX()-48,front.getY(),48,48); 
			try {
				Thread.sleep(300);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			Q[y].setText("");
			Qp[y].setVisible(false);
		}
		if(F>=1 && F<=3){
			Q[F-1].setText(""); Qp[F-1].setVisible(false);
			try {
				Thread.sleep(300);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if(F!=B){
				front.setLocation(Q[F].getX(),Q[F].getY()-48);
				ft.setLocation(front.getX(),front.getY()-48);
				front.setIcon(new ImageIcon(stacks.class.getResource("/rr/ic_action_arrow_bottom.png")));
				Q[y].setText("");
				Qp[y].setVisible(false);
			}
			else{
				front.setLocation(Q[F].getX(),Q[F].getY()-48);
				ft.setLocation(front.getX(),front.getY()-48-30);
				front.setIcon(new ImageIcon(stacks.class.getResource("/rr/ic_action_arrow_bottom.png")));
				Q[y].setText("");
				Qp[y].setVisible(false);
			}
			
		}
		if(F>=5 && F<9){
			Q[F-1].setText(""); Qp[F-1].setVisible(false);
			try {
				Thread.sleep(300);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if(F!=B){
				front.setLocation(Q[F].getX(),Q[F].getY()+30);
				ft.setLocation(front.getX(),front.getY()+48);
				front.setIcon(new ImageIcon(stacks.class.getResource("/rr/ic_action_arrow_tops.png")));
				Q[y].setText("");
				Qp[y].setVisible(false);
			}
			else{
				front.setLocation(Q[F].getX(),Q[F].getY()+30);
				ft.setLocation(front.getX(),front.getY()+48+30);
				front.setIcon(new ImageIcon(stacks.class.getResource("/rr/ic_action_arrow_tops.png")));
				Q[y].setText("");
				Qp[y].setVisible(false);
			}
		}
		if(F==4){
			if(F!=B){
				front.setLocation(Q[F].getX()+30,Q[F].getY()-10);
				ft.setLocation(front.getX()+30,front.getY());
				front.setIcon(new ImageIcon(stacks.class.getResource("/rr/ic_action_arrow_lefts.png")));
				try {
					Thread.sleep(300);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				Q[y].setText("");
				Qp[y].setVisible(false);
			}
			else{
				front.setLocation(Q[F].getX()+30,Q[F].getY());
				ft.setLocation(front.getX()+30,front.getY()-30);
				front.setIcon(new ImageIcon(stacks.class.getResource("/rr/ic_action_arrow_lefts.png")));
				try {
					Thread.sleep(300);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				Q[y].setText("");
				Qp[y].setVisible(false);
			}
		}
		if(F==9){
			if(F!=B){
				front.setLocation(Q[F].getX()-45,Q[F].getY()-10);
				ft.setLocation(front.getX()-30,front.getY());
				front.setIcon(new ImageIcon(stacks.class.getResource("/rr/ic_action_arrow_rightr.png")));
				try {
					Thread.sleep(300);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				Q[y].setText("");
				Qp[y].setVisible(false);
			}
			else{
				front.setLocation(Q[F].getX()-32,Q[F].getY()-10);
				ft.setLocation(front.getX()-48,front.getY()-10);
				front.setIcon(new ImageIcon(stacks.class.getResource("/rr/ic_action_arrow_rightr.png")));
				try {
					Thread.sleep(300);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				Q[y].setText("");
				Qp[y].setVisible(false);
			}
		}
		
		return 1;
	}
	boolean isFull(){
		return count==size;
	}
}

class dp extends JPanel 
{

	private static final long serialVersionUID = 1L;
	//creating references variables for images       
	BufferedImage bimg;

	//contructor
	dp()
	{
		try
		{
			bimg = ImageIO.read(new File("resources/queues.PNG"));
		}
		catch (Exception e){}    
	}

	public void paintComponent(Graphics g)
	{
		//super.paintComponent(g);

		g.drawImage(bimg,0,0,null);

	}

}//ends Mainp here

