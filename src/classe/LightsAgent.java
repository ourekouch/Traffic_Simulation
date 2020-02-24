package classe;

import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

import jade.core.AID;
import jade.core.Agent;
import jade.core.behaviours.OneShotBehaviour;
import jade.core.behaviours.TickerBehaviour;
import jade.lang.acl.ACLMessage;

public class LightsAgent extends Agent {

	private Gui frame;
	private JLabel lbl;

	@Override

	protected void setup() {

		final Object[] args = getArguments();

		frame = (Gui) args[0];
		lbl = new JLabel();

		lbl.setIcon(new javax.swing.ImageIcon(getClass().getResource((String) args[5])));
		frame.add(lbl);
		lbl.setBounds((int) args[1], (int) args[2], (int) args[3], (int) args[4]);

		frame.getContentPane().add(lbl, BorderLayout.SOUTH);

		addBehaviour(new OneShotBehaviour() {

			@Override
			public void action() {
				// TODO Auto-generated method stub
				//initialiation de l'etat 
				if ((int) args[7] == 0) {
					ACLMessage msg = new ACLMessage(ACLMessage.INFORM);
					msg.setContent("GREEN");
					lbl.setIcon(new ImageIcon(getClass().getResource((String) args[6])));

					msg.addReceiver(new AID((String) args[8], AID.ISLOCALNAME));

					send(msg);
				}
				//vérifier l'existence de l'avatar qu'on doit informer par l'etat de feu rouge
				if ((int) args[9] == 0) {
					ACLMessage msg = new ACLMessage(ACLMessage.INFORM);
					msg.setContent("GREEN");
					lbl.setIcon(new ImageIcon(getClass().getResource((String) args[6])));
					msg.addReceiver(new AID((String) args[10], AID.ISLOCALNAME));
					send(msg);
				}
				
			}
		});
		//changement des états de Feu rouge selon la position voie vertical ou horizontal
		addBehaviour(new TickerBehaviour(this, 5200) {

			public int p = (int) args[7];

			@Override
			protected void onTick() {
				p++;
				// TODO Auto-generated method stub

				System.out.println(p + "\n");
				ACLMessage msg = new ACLMessage(ACLMessage.INFORM);
				if ((p % 2) == 0) {
					msg.setContent("RED");
					lbl.setIcon(new javax.swing.ImageIcon(getClass().getResource((String) args[5])));

				} else {
					msg.setContent("GREEN");

					lbl.setIcon(new javax.swing.ImageIcon(getClass().getResource((String) args[6])));
				}	
				msg.addReceiver(new AID((String) args[8], AID.ISLOCALNAME));

				send(msg);
				//informer walker
				if ((int) args[9] == 1) {
					msg.addReceiver(new AID((String) args[10], AID.ISLOCALNAME));
					send(msg);
				}

			}

		});

	}

}
