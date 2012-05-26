import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Label;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;
import com.jgoodies.forms.factories.FormFactory;
import javax.swing.JLabel;
import net.miginfocom.swing.MigLayout;
import javax.swing.BoxLayout;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.Color;
import javax.swing.Box;
import javax.swing.border.BevelBorder;
import javax.swing.border.EtchedBorder;
import java.awt.GridLayout;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.Canvas;
import javax.swing.JButton;
import javax.swing.UIManager;


public class Main_Menu extends JFrame {

	private JPanel contentPane;

	
	//Launch the application.
	
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Main_Menu frame = new Main_Menu();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	
	//Create the frame.
	 
	public Main_Menu() {
		setForeground(new Color(0, 0, 0));
		setBackground(new Color(102, 102, 102));
		setTitle("Prometheus- Main Menu");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 650, 500);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(204, 102, 0));
		contentPane.setBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(0, 0, 0), null));
		setContentPane(contentPane);
		
		Box horizontalBox = Box.createHorizontalBox();
		horizontalBox.setBorder(new BevelBorder(BevelBorder.LOWERED, new Color(0, 0, 0), new Color(0, 0, 0), null, null));
		
		JLabel lblbottom = new JLabel("Prometheus Software V1.0 2012");
		lblbottom.setForeground(new Color(0, 0, 0));
		lblbottom.setLabelFor(this);
		lblbottom.setFont(new Font("Viner Hand ITC", Font.BOLD, 15));
		lblbottom.setHorizontalAlignment(SwingConstants.CENTER);
		
		JButton btnLoad = new JButton("Load Show");
		btnLoad.setFont(new Font("Arial Black", Font.BOLD, 12));
		btnLoad.setForeground(UIManager.getColor("inactiveCaptionText"));
		btnLoad.setBackground(UIManager.getColor("inactiveCaptionText"));
		
		JButton btnArm = new JButton("Network Testing / Arm Slaves");
		btnArm.setFont(new Font("Arial Black", Font.BOLD, 12));
		btnArm.setBackground(UIManager.getColor("inactiveCaptionText"));
		btnArm.setForeground(UIManager.getColor("inactiveCaptionText"));
		
		JButton btnRun = new JButton("Run Show");
		btnRun.setFont(new Font("Arial Black", Font.BOLD, 12));
		btnRun.setBackground(UIManager.getColor("inactiveCaptionText"));
		
		JButton btnExit = new JButton("EXIT");
		btnExit.setFont(new Font("Arial Black", Font.BOLD, 12));
		btnExit.setBackground(UIManager.getColor("inactiveCaptionText"));
		
		JLabel lblCurrentWaveFile = new JLabel("Current Wave File: ");
		lblCurrentWaveFile.setForeground(Color.BLACK);
		lblCurrentWaveFile.setFont(new Font("Arial Black", Font.BOLD, 12));
		
		JLabel lblCurrentScriptFile = new JLabel("Current Script File: ");
		lblCurrentScriptFile.setForeground(Color.BLACK);
		lblCurrentScriptFile.setFont(new Font("Arial Black", Font.BOLD, 12));
		
		JLabel lblempty = new JLabel("(empty)");
		lblempty.setForeground(Color.BLACK);
		lblempty.setBackground(Color.WHITE);
		
		JLabel label = new JLabel("(empty)");
		label.setForeground(Color.BLACK);
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap(163, Short.MAX_VALUE)
					.addComponent(horizontalBox, GroupLayout.PREFERRED_SIZE, 315, GroupLayout.PREFERRED_SIZE)
					.addGap(152))
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap(178, Short.MAX_VALUE)
					.addComponent(lblbottom, GroupLayout.PREFERRED_SIZE, 286, GroupLayout.PREFERRED_SIZE)
					.addGap(166))
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(194)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
								.addComponent(btnExit, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 253, Short.MAX_VALUE)
								.addComponent(btnRun, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 253, Short.MAX_VALUE)
								.addComponent(btnArm, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 253, Short.MAX_VALUE)
								.addComponent(btnLoad, GroupLayout.DEFAULT_SIZE, 253, Short.MAX_VALUE))
							.addGap(183))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING, false)
								.addComponent(lblCurrentWaveFile, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addComponent(lblCurrentScriptFile, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
							.addGap(18)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING, false)
								.addComponent(label, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addComponent(lblempty, GroupLayout.DEFAULT_SIZE, 211, Short.MAX_VALUE))
							.addGap(62))))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addComponent(horizontalBox, GroupLayout.PREFERRED_SIZE, 48, GroupLayout.PREFERRED_SIZE)
					.addGap(52)
					.addComponent(btnLoad)
					.addGap(31)
					.addComponent(btnArm)
					.addGap(30)
					.addComponent(btnRun)
					.addGap(43)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblCurrentWaveFile, GroupLayout.PREFERRED_SIZE, 26, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblempty))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblCurrentScriptFile, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE)
						.addComponent(label))
					.addPreferredGap(ComponentPlacement.RELATED, 30, Short.MAX_VALUE)
					.addComponent(btnExit)
					.addGap(18)
					.addComponent(lblbottom))
		);
		
		JLabel lbltitle = new JLabel("   Prometheus Pyrotechnics");
		lbltitle.setLabelFor(this);
		lbltitle.setForeground(new Color(0, 0, 0));
		horizontalBox.add(lbltitle);
		lbltitle.setFont(new Font("Viner Hand ITC", Font.BOLD, 22));
		lbltitle.setHorizontalAlignment(SwingConstants.CENTER);
		contentPane.setLayout(gl_contentPane);
	}
}
