import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import java.awt.SystemColor;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JTextField;
import javax.swing.JProgressBar;
import javax.swing.UIManager;


public class Load_Wave extends JFrame {

	private JPanel contentPane;
	private JTextField txtfile;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Load_Wave frame = new Load_Wave();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Load_Wave() {
		setTitle("Prometheus- Load Wave File");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 650, 500);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(204, 102, 0));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		JLabel lblLoadWaveFile = new JLabel("Load Wave File");
		lblLoadWaveFile.setHorizontalAlignment(SwingConstants.CENTER);
		lblLoadWaveFile.setForeground(Color.BLACK);
		lblLoadWaveFile.setFont(new Font("Arial Black", Font.BOLD, 24));
		
		JButton btnBackToLoad = new JButton("Back to Load Show Options");
		btnBackToLoad.setForeground(Color.BLACK);
		btnBackToLoad.setFont(new Font("Arial Black", Font.BOLD, 11));
		btnBackToLoad.setBackground(UIManager.getColor("Button.background"));
		
		JLabel label = new JLabel("Current Wave File: ");
		label.setForeground(Color.BLACK);
		label.setFont(new Font("Arial Black", Font.BOLD, 12));
		
		JLabel lblwave = new JLabel("(empty)");
		lblwave.setForeground(Color.BLACK);
		lblwave.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblwave.setBackground(Color.WHITE);
		
		JButton btnchoose = new JButton("Choose Wave File");
		btnchoose.setForeground(Color.BLACK);
		btnchoose.setFont(new Font("Arial Black", Font.BOLD, 10));
		btnchoose.setBackground(UIManager.getColor("Button.background"));
		
		txtfile = new JTextField();
		txtfile.setColumns(10);
		
		JButton btnLoadFileAnd = new JButton("Load File and Extract Cues");
		btnLoadFileAnd.setForeground(Color.BLACK);
		btnLoadFileAnd.setFont(new Font("Arial Black", Font.BOLD, 12));
		btnLoadFileAnd.setBackground(UIManager.getColor("Button.background"));
		
		JLabel lblsuccess = new JLabel("Wave File and Cue Points have been successfully loaded! ");
		lblsuccess.setHorizontalAlignment(SwingConstants.CENTER);
		lblsuccess.setForeground(Color.BLACK);
		lblsuccess.setFont(new Font("Arial Black", Font.PLAIN, 10));
		
		JProgressBar progressBar = new JProgressBar();
		
		JLabel lblwait = new JLabel("Please Wait...");
		lblwait.setFont(new Font("Tahoma", Font.BOLD, 11));
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap(397, Short.MAX_VALUE)
					.addComponent(btnBackToLoad)
					.addContainerGap())
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(173)
					.addComponent(lblLoadWaveFile, GroupLayout.PREFERRED_SIZE, 271, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(180, Short.MAX_VALUE))
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(211)
							.addComponent(label, GroupLayout.PREFERRED_SIZE, 148, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(133)
							.addComponent(txtfile, GroupLayout.PREFERRED_SIZE, 226, GroupLayout.PREFERRED_SIZE)))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addComponent(btnchoose)
						.addComponent(lblwave, GroupLayout.PREFERRED_SIZE, 211, GroupLayout.PREFERRED_SIZE))
					.addContainerGap(44, Short.MAX_VALUE))
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addContainerGap()
							.addComponent(progressBar, GroupLayout.PREFERRED_SIZE, 156, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(lblwait, GroupLayout.PREFERRED_SIZE, 89, GroupLayout.PREFERRED_SIZE)
							.addGap(20))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(169)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addComponent(lblsuccess, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addComponent(btnLoadFileAnd, GroupLayout.PREFERRED_SIZE, 297, GroupLayout.PREFERRED_SIZE))))
					.addContainerGap(129, Short.MAX_VALUE))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblLoadWaveFile, GroupLayout.PREFERRED_SIZE, 35, GroupLayout.PREFERRED_SIZE)
					.addGap(30)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(label, GroupLayout.PREFERRED_SIZE, 26, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblwave, GroupLayout.PREFERRED_SIZE, 15, GroupLayout.PREFERRED_SIZE))
					.addGap(34)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnchoose, GroupLayout.PREFERRED_SIZE, 27, GroupLayout.PREFERRED_SIZE)
						.addComponent(txtfile, GroupLayout.PREFERRED_SIZE, 26, GroupLayout.PREFERRED_SIZE))
					.addGap(91)
					.addComponent(btnLoadFileAnd, GroupLayout.PREFERRED_SIZE, 35, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING, false)
						.addComponent(lblwait, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(progressBar, GroupLayout.DEFAULT_SIZE, 21, Short.MAX_VALUE))
					.addGap(18, 25, Short.MAX_VALUE)
					.addComponent(lblsuccess, GroupLayout.PREFERRED_SIZE, 26, GroupLayout.PREFERRED_SIZE)
					.addGap(24)
					.addComponent(btnBackToLoad, GroupLayout.PREFERRED_SIZE, 38, GroupLayout.PREFERRED_SIZE)
					.addContainerGap())
		);
		contentPane.setLayout(gl_contentPane);
	}
}
