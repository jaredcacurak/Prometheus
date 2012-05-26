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
import javax.swing.Box;
import javax.swing.border.BevelBorder;
import javax.swing.SwingConstants;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JButton;
import java.awt.SystemColor;
import javax.swing.UIManager;


public class Load_Show extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Load_Show frame = new Load_Show();
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
	public Load_Show() {
		setTitle("Prometheus- Load Show");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 650, 500);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(204, 102, 0));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		JLabel lblLoadShowOptions = new JLabel("Load Show Options");
		lblLoadShowOptions.setForeground(Color.BLACK);
		lblLoadShowOptions.setHorizontalAlignment(SwingConstants.CENTER);
		lblLoadShowOptions.setFont(new Font("Arial Black", Font.BOLD, 24));
		
		JLabel label = new JLabel("Current Wave File: ");
		label.setForeground(Color.BLACK);
		label.setFont(new Font("Arial Black", Font.BOLD, 12));
		
		JLabel label_1 = new JLabel("Current Script File: ");
		label_1.setForeground(Color.BLACK);
		label_1.setFont(new Font("Arial Black", Font.BOLD, 12));
		
		JLabel lblwave = new JLabel("(empty)");
		lblwave.setForeground(Color.BLACK);
		lblwave.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblwave.setBackground(Color.WHITE);
		
		JLabel lblscript = new JLabel("(empty)");
		lblscript.setForeground(Color.BLACK);
		lblscript.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblscript.setBackground(Color.WHITE);
		
		JButton btnBackMain = new JButton("Back to Main Menu");
		btnBackMain.setForeground(Color.BLACK);
		btnBackMain.setBackground(UIManager.getColor("Button.background"));
		btnBackMain.setFont(new Font("Arial Black", Font.BOLD, 12));
		
		JButton btnEditInventory = new JButton("Edit Inventory");
		btnEditInventory.setForeground(Color.BLACK);
		btnEditInventory.setFont(new Font("Arial Black", Font.BOLD, 12));
		btnEditInventory.setBackground(UIManager.getColor("Button.background"));
		
		JButton btnDatabaseEditor = new JButton("Database Editor");
		btnDatabaseEditor.setForeground(Color.BLACK);
		btnDatabaseEditor.setFont(new Font("Arial Black", Font.BOLD, 12));
		btnDatabaseEditor.setBackground(UIManager.getColor("Button.background"));
		
		JButton btnLoadWaveFile = new JButton("Load Wave File / Extract Cues");
		btnLoadWaveFile.setForeground(Color.BLACK);
		btnLoadWaveFile.setFont(new Font("Arial Black", Font.BOLD, 12));
		btnLoadWaveFile.setBackground(UIManager.getColor("Button.background"));
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(190)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addComponent(label, GroupLayout.PREFERRED_SIZE, 148, GroupLayout.PREFERRED_SIZE)
								.addComponent(label_1, GroupLayout.PREFERRED_SIZE, 148, GroupLayout.PREFERRED_SIZE))
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addComponent(lblscript, GroupLayout.PREFERRED_SIZE, 211, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblwave, GroupLayout.PREFERRED_SIZE, 211, GroupLayout.PREFERRED_SIZE)))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(164)
							.addComponent(lblLoadShowOptions)))
					.addContainerGap(65, Short.MAX_VALUE))
				.addGroup(Alignment.TRAILING, gl_contentPane.createSequentialGroup()
					.addContainerGap(441, Short.MAX_VALUE)
					.addComponent(btnBackMain)
					.addContainerGap())
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(173)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(btnLoadWaveFile, GroupLayout.PREFERRED_SIZE, 255, GroupLayout.PREFERRED_SIZE)
							.addContainerGap())
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
								.addComponent(btnDatabaseEditor, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 255, Short.MAX_VALUE)
								.addComponent(btnEditInventory, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 255, Short.MAX_VALUE))
							.addGap(196))))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblLoadShowOptions)
					.addGap(30)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(label, GroupLayout.PREFERRED_SIZE, 26, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblwave, GroupLayout.PREFERRED_SIZE, 15, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(label_1, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblscript, GroupLayout.PREFERRED_SIZE, 15, GroupLayout.PREFERRED_SIZE))
					.addGap(40)
					.addComponent(btnLoadWaveFile, GroupLayout.PREFERRED_SIZE, 35, GroupLayout.PREFERRED_SIZE)
					.addGap(34)
					.addComponent(btnEditInventory, GroupLayout.PREFERRED_SIZE, 35, GroupLayout.PREFERRED_SIZE)
					.addGap(34)
					.addComponent(btnDatabaseEditor, GroupLayout.PREFERRED_SIZE, 35, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED, 52, Short.MAX_VALUE)
					.addComponent(btnBackMain, GroupLayout.PREFERRED_SIZE, 38, GroupLayout.PREFERRED_SIZE)
					.addContainerGap())
		);
		contentPane.setLayout(gl_contentPane);
	}
}
