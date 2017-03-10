import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JLabel;

import java.awt.Color;
import java.awt.BorderLayout;
import java.awt.Graphics;
import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.SwingWorker;
import javax.swing.Timer;

import java.awt.Font;

import javax.swing.SwingConstants;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.List;

import javax.swing.JFormattedTextField;
import java.awt.event.MouseAdapter;
import org.eclipse.wb.swing.FocusTraversalOnArray;
import java.awt.Component;


public class heaps implements MouseListener  {
	private static int heap=15;
	int cnt=0;
	private JFrame frmMinHeap;
	private  JLabel [] datashow = new JLabel[heap];
	private  JLabel [] heapshow = new JLabel[heap];
	private  JLabel [] indexshow = new JLabel[heap];
	static int data[];
	private JFormattedTextField textField;
	private JButton btnBuildHeap,btnEnterNumber,btnRemoveMin;
	private static JPanel panel;
	private static int timer =1000;
	static BufferedImage bimg = null;
	SwingWorker<Void, Integer> worker;
	Timer t;
	Thread Hp;
	private JButton btnBack;
	private JLabel lblSwap_1,lblSwap;
	private JLabel lblStatement, lblMin;

	/**
	 * Create the application.
	 */
	public heaps() {
		data= new int[heap];
		for(int i=0;i<heap;i++){
			indexshow[i]= new JLabel(""+i);
			heapshow[i]= new JLabel("0"+i);
			datashow[i]= new JLabel("");
			datashow[i].setFont(new Font("Tahoma", Font.PLAIN, 15));
			datashow[i].setHorizontalAlignment(SwingConstants.CENTER);
			indexshow[i].setHorizontalAlignment(SwingConstants.CENTER);
			data[i]=heap-i;
		}
		//heapify();
		initialize();

	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmMinHeap = new JFrame();
		frmMinHeap.setBackground(Color.WHITE);
		frmMinHeap.getContentPane().setBackground(Color.WHITE);
		//JLayeredPane lp = frmMinHeap.getLayeredPane();
		frmMinHeap.setTitle("Min Heap");
		frmMinHeap.setResizable(false);

		//Mainp r= new Mainp();
		panel = new Mainp();
		panel.setBackground(Color.WHITE);


		for(int i=0;i<heap;i++){
			datashow[i].setBounds(40*(i+1), 100, 40, 40);
			indexshow[i].setBounds(40*(i+1), 80, 40, 20);
			datashow[i].setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
			panel.add(datashow[i]);
			panel.add(indexshow[i]);

		}
		heapshow[0].setBounds(datashow[7].getX(), datashow[7].getY()+80, 40, 40);
		heapshow[1].setBounds(datashow[7].getX()-160, datashow[7].getY()+160, 40, 40);
		heapshow[2].setBounds(datashow[7].getX()+160, datashow[7].getY()+160, 40, 40);
		heapshow[3].setBounds(heapshow[1].getX()-80, heapshow[1].getY()+80, 40, 40);
		heapshow[4].setBounds(heapshow[1].getX()+80, heapshow[1].getY()+80, 40, 40);
		heapshow[5].setBounds(heapshow[2].getX()-80, heapshow[2].getY()+80, 40, 40);
		heapshow[6].setBounds(heapshow[2].getX()+80, heapshow[2].getY()+80, 40, 40);
		heapshow[7].setBounds(heapshow[3].getX()-35, heapshow[3].getY()+80, 40, 40);
		heapshow[8].setBounds(heapshow[3].getX()+35, heapshow[3].getY()+80, 40, 40);
		heapshow[9].setBounds(heapshow[4].getX()-35, heapshow[4].getY()+80, 40, 40);
		heapshow[10].setBounds(heapshow[4].getX()+35, heapshow[4].getY()+80, 40, 40);
		heapshow[11].setBounds(heapshow[5].getX()-35, heapshow[5].getY()+80, 40, 40);
		heapshow[12].setBounds(heapshow[5].getX()+35, heapshow[5].getY()+80, 40, 40);
		heapshow[13].setBounds(heapshow[6].getX()-35, heapshow[6].getY()+80, 40, 40);
		heapshow[14].setBounds(heapshow[6].getX()+35, heapshow[6].getY()+80, 40, 40);
		drawheap();
		//frmMinHeap.getContentPane().setLayout(new BorderLayout(0, 0));
		panel.setBackground(Color.WHITE);
		//r.add(panel);
		//frmMinHeap.getContentPane().add(r, BorderLayout.CENTER);

		frmMinHeap.getContentPane().add(panel, BorderLayout.CENTER);
		panel.setLayout(null);
		btnEnterNumber = new JButton("Enter Number");
		btnEnterNumber.setFont(new Font("Comic Sans MS", Font.PLAIN, 13));
		btnEnterNumber.addMouseListener(this);

		btnEnterNumber.setBounds(678, 151, 116, 34);
		panel.add(btnEnterNumber);
		textField = new JFormattedTextField();
		textField.setFont(new Font("Tahoma", Font.PLAIN, 15));
		textField.setHorizontalAlignment(SwingConstants.CENTER);

		textField.setBounds(678, 100, 116, 40);
		panel.add(textField);
		textField.setColumns(10);
		textField.requestFocus();

		lblMin = new JLabel("");
		lblMin.setBorder(BorderFactory.createLineBorder(Color.GRAY, 1));
		lblMin.setHorizontalAlignment(SwingConstants.CENTER);
		lblMin.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblMin.setBounds(678, 321, 116, 34);
		panel.add(lblMin);

		btnBuildHeap = new JButton("Build Heap");
		btnBuildHeap.setFont(new Font("Comic Sans MS", Font.PLAIN, 13));
		btnBuildHeap.setBounds(678, 214, 116, 34);
		panel.add(btnBuildHeap);
		btnBuildHeap.addMouseListener(this);

		btnRemoveMin = new JButton("Remove Min");
		btnRemoveMin.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				if(cnt==1){
					cnt--;
					lblMin.setText(""+data[cnt]);
					heapshow[cnt].setText("");
					datashow[cnt].setText("");
				}
				else if(cnt!=0)
					removem(1);
			}
		});
		btnRemoveMin.setFont(new Font("Comic Sans MS", Font.PLAIN, 13));
		btnRemoveMin.setBounds(678, 276, 116, 34);
		panel.add(btnRemoveMin);

		lblStatement = new JLabel("");
		lblStatement.setForeground(Color.BLUE);
		lblStatement.setHorizontalAlignment(SwingConstants.LEFT);
		lblStatement.setFont(new Font("Comic Sans MS", Font.PLAIN, 15));
		lblStatement.setBounds(665, 372, 143, 37);
		panel.add(lblStatement);

		lblSwap = new JLabel("");
		lblSwap.setForeground(Color.BLUE);
		lblSwap.setFont(new Font("Comic Sans MS", Font.PLAIN, 15));
		lblSwap.setHorizontalAlignment(SwingConstants.CENTER);
		lblSwap.setBounds(665, 420, 182, 37);
		panel.add(lblSwap);

		btnBack = new JButton("Back");
		btnBack.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {

				datastructures k= new datastructures();
				frmMinHeap.dispose();
			}
		});
		btnBack.setBounds(10, 10, 61, 23);
		panel.add(btnBack);

		lblSwap_1 = new JLabel("");
		lblSwap_1.setFont(new Font("Comic Sans MS", Font.PLAIN, 15));
		lblSwap_1.setForeground(Color.BLUE);
		lblSwap_1.setHorizontalAlignment(SwingConstants.RIGHT);
		lblSwap_1.setBounds(678, 452, 143, 34);
		panel.add(lblSwap_1);
		panel.setFocusTraversalPolicy(new FocusTraversalOnArray(new Component[]{textField, btnEnterNumber, lblMin, btnBuildHeap, btnRemoveMin, lblStatement, lblSwap, btnBack, lblSwap_1}));
		frmMinHeap.setSize(900, 700);
		frmMinHeap.setLocationRelativeTo(null);
		frmMinHeap.setVisible(true);
		textField.requestFocusInWindow();
		frmMinHeap.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	}
	void drawheap(){
		for(int i=0;i<heap;i++){
			heapshow[i].setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));

			heapshow[i].setFont(new Font("Tahoma", Font.BOLD, 15));
			heapshow[i].setHorizontalAlignment(SwingConstants.CENTER);
			heapshow[i].setText("");
			panel.add(heapshow[i]);
			//panel.repaint();
		}
	}
	int left(int parent){
		int l = 2 * parent + 1;
		if (l < cnt)
			return l;
		else
			return -1;
	}

	int right(int parent){
		int r = 2 * parent + 2;
		if (r < cnt)
			return r;
		else
			return -1;
	}

	public int parent(int child){
		int p = (child - 1)/2;
		if (child == 0)
			return -1;
		else
			return p;
	}
	void DeleteMin()
	{
		if (cnt == 0)
		{
			//cout<<"Heap is Empty"<<endl;
			return;
		}
		data[0] = data[cnt];
		//heap.pop_back();
		// heapifydown(0);

		// cout<<"Element Deleted"<<endl;
	}

	void pushele(int a){
		data[cnt]=a;
		datashow[cnt].setText(""+a);
		datashow[cnt].setBorder(BorderFactory.createLineBorder(Color.BLUE, 2));
		heapshow[cnt].setText(""+a);
	}

	@Override
	public void mouseClicked(MouseEvent me) {
		// TODO Auto-generated method stub

		Object obj1 = me.getSource();
		if (obj1 == btnEnterNumber){
			if(!textField.getText().toString().equals("")){
				int t=Integer.parseInt(textField.getText().toString());
				pushele(t);
				textField.setText("");
				textField.requestFocus();
				cnt++;
				if(cnt==(heap)){
					btnEnterNumber.removeMouseListener(this);
					btnBuildHeap.addMouseListener(this);

				}

			}
		}
		if (obj1 == btnBuildHeap){
			//drawheap(panel);
			btnBuildHeap.removeMouseListener(this);
			//cnt=15;
			lblStatement.setText("Heapify-Up");
			dow(cnt);

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

	void dow(final int in){
		worker = new SwingWorker<Void, Integer>() {
			@Override
			protected Void doInBackground() throws Exception {
				for (int i=0;i<in;i++){
					heapifyup(in-i-1);
				}
				for (int i=0;i<in;i++){
					heapifyup(in-i-1);

				}
				//heapifydown(0);
				return null;
			}
			@Override
			protected void process(List<Integer> chunks) {

			}

			@Override
			protected void done() {
				lblStatement.setText("");
				btnBuildHeap.addMouseListener(heaps.this);
				for(int i=0;i<cnt;i++)
					System.out.print(data[i]+" - ");

			}
		};
		worker.execute();


	}
	void heapifyup(final int in){
		if (in >= 0 && parent(in) >= 0)
		{
			heapshow[parent(in)].setBorder(BorderFactory.createLineBorder(Color.RED, 3));
			heapshow[in].setBorder(BorderFactory.createLineBorder(Color.RED, 3));
			datashow[parent(in)].setBorder(BorderFactory.createLineBorder(Color.RED, 3));
			datashow[in].setBorder(BorderFactory.createLineBorder(Color.RED, 3));
			lblSwap.setText("Is Parent "+data[parent(in)]+" > Child "+data[in]);
			if(data[parent(in)] > data[in]){


				try {
					Thread.sleep(timer);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				heapshow[parent(in)].setBorder(BorderFactory.createLineBorder(Color.GREEN, 3));
				heapshow[in].setBorder(BorderFactory.createLineBorder(Color.GREEN, 3));
				datashow[parent(in)].setBorder(BorderFactory.createLineBorder(Color.GREEN, 3));
				datashow[in].setBorder(BorderFactory.createLineBorder(Color.GREEN, 3));
				lblSwap_1.setText("Swap "+data[parent(in)]+" and "+data[in]);
				try {
					Thread.sleep(timer);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				int temp = data[in];
				data[in] = data[parent(in)];
				data[parent(in)] = temp;
				//System.out.println("data in "+data[in]+"  data par "+data[parent(in)]);

				//panel.validate();
				heapshow[parent(in)].setText(""+data[parent(in)]);
				panel.repaint();
				heapshow[(in)].setText(""+data[(in)]);
				panel.repaint();
				datashow[parent(in)].setText(""+data[parent(in)]);
				panel.repaint();
				datashow[(in)].setText(""+data[(in)]);
				panel.repaint();
				panel.repaint();
				try {
					Thread.sleep(timer);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				//frmMinHeap.repaint();
				datashow[parent(in)].setBorder(BorderFactory.createLineBorder(Color.BLUE, 2));
				datashow[in].setBorder(BorderFactory.createLineBorder(Color.BLUE, 2));
				heapshow[parent(in)].setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
				heapshow[in].setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
				lblSwap_1.setText("Heapify");
				heapifyup(parent(in));
			}
			else{
				try {
					Thread.sleep(timer);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				lblSwap.setText("");
				lblSwap_1.setText("");
				heapshow[parent(in)].setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
				heapshow[in].setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
				datashow[parent(in)].setBorder(BorderFactory.createLineBorder(Color.BLUE, 2));
				datashow[in].setBorder(BorderFactory.createLineBorder(Color.BLUE, 2));
			}
		}
	}
	void removem(final int ty){
		SwingWorker<Void, Integer> worker = new SwingWorker<Void, Integer>() {
			@Override
			protected Void doInBackground() throws Exception {
				if(ty==1){
					cnt--;
					heapshow[0].setBorder(BorderFactory.createLineBorder(Color.RED, 3));
					heapshow[cnt].setBorder(BorderFactory.createLineBorder(Color.RED, 3));
					datashow[0].setBorder(BorderFactory.createLineBorder(Color.RED, 3));
					datashow[cnt].setBorder(BorderFactory.createLineBorder(Color.RED, 3));
					try {
						Thread.sleep(timer);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					lblMin.setText(heapshow[0].getText());
					heapshow[0].setText(heapshow[cnt].getText());
					datashow[0].setText(datashow[cnt].getText());
					heapshow[cnt].setForeground(Color.RED);heapshow[cnt].setText("X");
					datashow[cnt].setForeground(Color.RED);datashow[cnt].setText("X");
					heapshow[0].setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
					heapshow[cnt].setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
					datashow[0].setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
					datashow[cnt].setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
					data[0]=data[cnt];
					//cnt--;

				}
				heapifydown(0);
				return null;
			}

			@Override
			protected void process(List<Integer> chunks) {

			}

			@Override
			protected void done() {
				//btnBuildHeap.addMouseListener(heaps.this);
				lblStatement.setText("");
				for(int i=0;i<cnt;i++)
					System.out.print(data[i]+" - ");
			}
		};
		worker.execute();
	}
	void heapifydown(int in){
		int child = left(in);
		int child1 = right(in);
		if (child >= 0 && child1 >= 0 ){
			heapshow[child].setBorder(BorderFactory.createLineBorder(Color.RED, 3));
			heapshow[child1].setBorder(BorderFactory.createLineBorder(Color.RED, 3));
			datashow[child].setBorder(BorderFactory.createLineBorder(Color.RED, 3));
			datashow[child1].setBorder(BorderFactory.createLineBorder(Color.RED, 3));
			try {
				Thread.sleep(timer-50);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if( data[child] > data[child1])

			{
				
				heapshow[child].setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
				datashow[child].setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
				child = child1;
				heapshow[child].setBorder(BorderFactory.createLineBorder(Color.GREEN, 3));
				datashow[child].setBorder(BorderFactory.createLineBorder(Color.GREEN, 3));
				
			}
			else{
				heapshow[child1].setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
				datashow[child1].setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
				heapshow[child].setBorder(BorderFactory.createLineBorder(Color.GREEN, 3));
				datashow[child].setBorder(BorderFactory.createLineBorder(Color.GREEN, 3));
			}
		}
		
		if (child > 0)
		{
			heapshow[in].setBorder(BorderFactory.createLineBorder(Color.GREEN, 3));
			datashow[in].setBorder(BorderFactory.createLineBorder(Color.GREEN, 3));
			try {
				Thread.sleep(timer-100);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			int temp = data[in];
			data[in] = data[child];
			data[child] = temp;
			heapshow[in].setText(""+data[in]);
			heapshow[child].setText(""+data[child]);
			datashow[in].setText(""+data[in]);
			datashow[child].setText(""+data[child]);
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			heapshow[in].setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
			datashow[in].setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
			heapshow[child].setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
			datashow[child].setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
			try {
				Thread.sleep(300);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			heapifydown(child);
		}
	}
}
class Mainp extends JPanel 
{

	private static final long serialVersionUID = 1L;
	//creating references variables for images       
	BufferedImage bimg;

	//contructor
	Mainp()
	{
		try
		{
			bimg = ImageIO.read(new File("resources/heaps.PNG"));
		}
		catch (Exception e){}    
	}

	public void paintComponent(Graphics g)
	{
		//super.paintComponent(g);

		g.drawImage(bimg,0,0,null);

	}

}//ends Mainp here
