package classe;

import javax.swing.JFrame;
import javax.swing.JLabel;

import jade.wrapper.StaleProxyException;

import java.awt.GridLayout;

import javax.swing.JButton;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Rectangle;
import java.awt.Dimension;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Gui extends javax.swing.JFrame {

	public Gui() {
		initComponents();
	}

	@SuppressWarnings("unchecked")
	private void initComponents() {
		Background = new javax.swing.JLabel();
		lbl = new javax.swing.JLabel();
		/*
		 * button = new JButton("New button"); button.addActionListener(new
		 * ActionListener() { public void actionPerformed(ActionEvent e) { Object[]
		 * argsVV1 = { Main.frame, 580, 580, 60, 120, "/images/unnamed.png", 0, 0,
		 * Main.container }; try { Main.container.createNewAgent("VoitureV1",
		 * VoitureAgent.class.getName(), argsVV1).start(); } catch (StaleProxyException
		 * e1) { // TODO Auto-generated catch block e1.printStackTrace(); } } });
		 * button.setSize(new Dimension(20, 10)); button.setMinimumSize(new
		 * Dimension(20, 10)); button.setMaximumSize(new Dimension(20, 10));
		 * button.setPreferredSize(new Dimension(20, 10)); button.setBounds(new
		 * Rectangle(1140, 100, 100, 0)); button.setLayout(new
		 * GridLayout(150,150,200,0)); button.setSize(100,50); Background.add(button);
		 */
		setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
		setMaximumSize(new java.awt.Dimension(1000, 1000));
		setResizable(false);
		setPreferredSize(new java.awt.Dimension(1255, 730));
		getContentPane().setLayout(null);
		Background.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/city.JPG"))); // NOI18N
		Background.setMinimumSize(new java.awt.Dimension(1100, 1100));
		Background.setPreferredSize(new java.awt.Dimension(1100, 1100));

		Background.setBounds(0, -160, 1040, 1000);
		setContentPane(Background);


		getContentPane().add(lbl, BorderLayout.SOUTH);

//        setContentPane(button);

		pack();
	}

	private javax.swing.JLabel Background;
	private javax.swing.JLabel lbl;

	private javax.swing.JButton button;
}
