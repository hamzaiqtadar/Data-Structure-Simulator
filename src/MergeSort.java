import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.SwingWorker;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.util.List;
import java.util.Random;

import javax.swing.JButton;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;

import javax.swing.JTextPane;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;


public class MergeSort {
	private int length=16;
	private JFrame frmMergeSort;
	private JLabel arr[][]=new JLabel[6][length];
	private int barr[]=new int[length];
	int ini=80;
	int timer =1000;
	static int Y=0;
	public MergeSort() {
		Random t = new Random();
		for(int i=0;i<length;i++){
			int num= t.nextInt(100);
			barr[i]=num;
			arr[0][i]= new JLabel(""+num);
			arr[0][i].setFont(new Font("Tahoma", Font.PLAIN, 15));
			arr[0][i].setHorizontalAlignment(SwingConstants.CENTER);
		}
		
		
			//System.out.println("Y "+Y);
		
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmMergeSort = new JFrame();
		frmMergeSort.setTitle("Merge Sort");
		frmMergeSort.setSize(850, 700);
		frmMergeSort.setLocationRelativeTo(null);
		frmMergeSort.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JPanel panel = new bk();
		
		for(int i=0;i<length;i++){
			for(int j=0;j<5;j++){
				if(j!=0){
					arr[j][i]= new JLabel("");
				arr[j][i].setVisible(false);
				}
			arr[j][i].setBounds( ini+40*i,90*(j+1), 40, 60);
			arr[j][i].setFont(new Font("Tahoma", Font.PLAIN, 15));
			arr[j][i].setHorizontalAlignment(SwingConstants.CENTER);
			//indexshow[i].setBounds(40*(i+1), 80, 40, 20);
			arr[j][i].setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
			panel.add(arr[j][i]);
			//panel.add(arr[i]);
			//ini=0;
			}
		}

		panel.setBackground(Color.WHITE);
		frmMergeSort.getContentPane().add(panel, BorderLayout.CENTER);
		panel.setLayout(null);
		
		JButton btnStart = new JButton("Start");
		btnStart.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				msr();
			}
		});
		btnStart.setBounds(641, 39, 89, 23);
		panel.add(btnStart);
		
		JButton BACK = new JButton("Back");
		BACK.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				frmMergeSort.dispose();
				datastructures o= new datastructures();
			}
		});
		
		BACK.setBounds(10, 11, 64, 28);
		panel.add(BACK);
		
		frmMergeSort.setVisible(true);
	}
	void msr(){
		SwingWorker<Void, Integer> worker = new SwingWorker<Void, Integer>() {
			@Override
			protected Void doInBackground() throws Exception {
				Mergesort(barr,0);
				for (int i = 0; i < length; i++){
					System.out.print(barr[i]+" - ");
					}
				return null;
			}

			@Override
			protected void process(List<Integer> chunks) {

			}

			@Override
			protected void done() {
				
			}
		};
		worker.execute();
	}
		void Mergesort(int A[], int s){
			int T[]=new int[length];
			mergesort(A, T, 0, length-1);
			////mergesort(A, T, 0, length-1,Y);
		}
		void mergesort(int A[],int T[], int s, int e){
			
			
			
			if (s<e){			// if starting is less than ending index
				int k = (e + s) / 2;
				//txtpnGg.setText(txtpnGg.getText().concat("\nDivide in Half"));
				for(int i=s;i<=k;i++){
					
					arr[Y][i].setBorder(BorderFactory.createLineBorder(Color.GREEN, 2));
					
				}
				for(int i=k+1;i<=e;i++){
					arr[Y][i].setBorder(BorderFactory.createLineBorder(Color.RED, 2));
				}
					Y++;
				//System.out.println("Y =="+Y);
				try {
					Thread.sleep(500);
				} catch (InterruptedException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				for(int i=s;i<=k;i++){
					arr[Y][i].setVisible(true);
				arr[Y][i].setText(arr[Y-1][i].getText());
				arr[Y-1][i].setText("X");
				}
				try {
					Thread.sleep(500);
				} catch (InterruptedException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				// mid point of the array w.r.t start and end
				//txtpnGg.setText(txtpnGg.getText().concat("\nFirst Half"));
			mergesort(A,T, s, k);
//				for(int i=s;i<=k;i++){
//					arr[Y][i].setVisible(false);
//				arr[Y-1][i].setText(""+A[i]);
//				}
				for(int i=k+1;i<=e;i++){
					arr[Y][i].setVisible(true);
				arr[Y][i].setText(arr[Y-1][i].getText());
				arr[Y-1][i].setText("X");
				}
				try {
					Thread.sleep(500);
				} catch (InterruptedException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				//txtpnGg.setText(txtpnGg.getText().concat("\nSecond Half"));
			mergesort(A,T, k + 1, e);
//				for(int i=k+1;i<=e;i++){
//					arr[Y][i].setVisible(false);
//					arr[Y-1][i].setText(""+A[i]);
//				}
				//Y--;
				merge(A,T, s, k, e);
				Y--;
				
			}
		
		}
		void merge(int A[], int T[],int a, int p, int b){
			//System.out.println("Y =="+Y);
			int  lw = a,	
					k=a,		
					cn=p+1;		
						
				while ((lw <= p) && (cn <= b)){	
					if (A[lw] <= A[cn]){	
						T[k] = A[lw];
						arr[Y][cn].setBorder(BorderFactory.createLineBorder(Color.MAGENTA, 3));
						arr[Y][lw].setBorder(BorderFactory.createLineBorder(Color.MAGENTA, 3));
						arr[Y-1][k].setBorder(BorderFactory.createLineBorder(Color.BLUE, 3));
						try {
							Thread.sleep(timer);
						} catch (InterruptedException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
						arr[Y][lw].setText("X");
						try {
							Thread.sleep(200);
						} catch (InterruptedException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
						arr[Y-1][k].setText(""+A[lw]);
						arr[Y][lw].setBorder(BorderFactory.createLineBorder(Color.GREEN, 2));
						arr[Y][cn].setBorder(BorderFactory.createLineBorder(Color.GREEN, 2));
						arr[Y-1][k].setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
						lw++;		
						//System.out.println("while if");
					}
					else{
						T[k] = A[cn];
						arr[Y][k].setBorder(BorderFactory.createLineBorder(Color.MAGENTA, 3));
						arr[Y][cn].setBorder(BorderFactory.createLineBorder(Color.MAGENTA, 3));
						arr[Y-1][k].setBorder(BorderFactory.createLineBorder(Color.BLUE, 3));
						try {
							Thread.sleep(timer);
						} catch (InterruptedException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
						arr[Y-1][k].setText(""+A[cn]);
						try {
							Thread.sleep(500);
						} catch (InterruptedException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
						arr[Y][cn].setText("X");
						arr[Y][k].setBorder(BorderFactory.createLineBorder(Color.GREEN, 2));
						arr[Y][cn].setBorder(BorderFactory.createLineBorder(Color.GREEN, 2));
						arr[Y-1][k].setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
						cn++;	
						//System.out.println("while else");
					}
					k++;		
				}
				if (lw > p){		
									
					for (int i = cn; i <= b; i++){
						//System.out.println("lw >p");
						T[k] = A[i];
						try {
							Thread.sleep(timer);
						} catch (InterruptedException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
						arr[Y-1][k].setText(""+A[i]);
						k++;
					}
				}
				else {				//else insert element of 1st half in temp array
					for (int i = lw; i <= p; i++){
						T[k] = A[i];
						try {
							Thread.sleep(timer);
						} catch (InterruptedException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
						arr[Y-1][k].setText(""+A[i]);
						k++;
					}
				}
				for (int i = a; i <= b; i++){	// now insert back the merged elements from temp to
					A[i] = T[i];	
					// orignal array
					
					arr[Y][i].setVisible(false);
//					arr[Y-1][i].setBorder(BorderFactory.createLineBorder(Color.BLUE, 2));
				}
		}
}
class bk extends JPanel 
{

	private static final long serialVersionUID = 1L;
	//creating references variables for images       
	BufferedImage bimg;

	//contructor
	bk()
	{
		//setting logo and background
		try
		{
			bimg = ImageIO.read(new File("resources/merge.PNG"));
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
