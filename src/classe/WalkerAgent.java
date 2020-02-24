package classe;
import java.awt.Point;
import java.awt.Rectangle;
import java.util.Random;

import jade.wrapper.AgentContainer;
import jade.wrapper.AgentController;
import jade.wrapper.StaleProxyException;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

import jade.core.Agent;
import jade.core.ProfileImpl;
import jade.core.Runtime;
import jade.core.behaviours.CyclicBehaviour;
import jade.core.behaviours.ParallelBehaviour;
import jade.core.behaviours.TickerBehaviour;
import jade.lang.acl.ACLMessage;
import jade.wrapper.AgentController;

public class WalkerAgent extends Agent  {
	

	private Gui frame;
	private JLabel lbl;
	private boolean etat;
	private static int parameter = 1;
	private Random r;
	private int a;

	@Override
	protected void setup() {

		final Object[] args = getArguments();
		r = new Random();
		frame = (Gui) args[0];
		lbl = new JLabel();
		lbl.setBounds((Integer) args[1], (Integer) args[2], (Integer) args[3], (Integer) args[4]);
		lbl.setIcon(new ImageIcon(getClass().getResource((String) args[5]))); // NOI18N

		frame.getContentPane().add(lbl);

		ParallelBehaviour comportementparallele = new ParallelBehaviour(ParallelBehaviour.WHEN_ANY);
		
		//comportement de réception des messages (cyclic )
		comportementparallele.addSubBehaviour(new CyclicBehaviour() {

			@Override
			public void action() {
				ACLMessage msg = receive();
				if (msg != null) {
					if (msg.getContent().equals("RED")) {

						System.out.println("RED");

						etat = true;
					} else if (msg.getContent().equals("GREEN")) {
						System.out.println("GREEN");

						etat = false;
					}

				} else
					block();

			}
		});
		
		//comportement de gestion des mouvements selon l'état de message (Feu rouge )
		comportementparallele.addSubBehaviour(new TickerBehaviour(this, 80) {
			@Override
			protected void onTick() {
				//hh2 right
				if (((Integer) args[6] == 1) && (Integer) args[7] == 1) {

					if (etat) //avancamant c'set le feu rouge de la voie est rouge 
						lbl.setLocation(lbl.getLocation().x + 10, lbl.getLocation().y);
					if (!etat && lbl.getX() <= 340) {//avancement c'est le feu est vert et on a pas encore arriver au passage  
						lbl.setLocation(lbl.getLocation().x + 10, lbl.getLocation().y);
					}// avancement c'set le feu vert et on a dépassé le passage 
					if (!etat && lbl.getX() >= 370) {
						lbl.setLocation(lbl.getLocation().x + 10, lbl.getLocation().y);
					}// retour en arriére c'est on est dans le passage et le feu devient vert
					if (!etat && lbl.getX() >= 340 && lbl.getX() <= 370 ) {

						lbl.setLocation(lbl.getLocation().x-10, lbl.getLocation().y );
					}
				}
				//hh1 left
				if (((Integer) args[6] == 1) && (Integer) args[7] == 0) {

					if (etat)
						lbl.setLocation(lbl.getLocation().x - 10, lbl.getLocation().y);
					if (!etat && lbl.getX() <= 580) {
						lbl.setLocation(lbl.getLocation().x - 10, lbl.getLocation().y);
					}
					if (!etat && lbl.getX() >= 740) {
						lbl.setLocation(lbl.getLocation().x - 10, lbl.getLocation().y);
					}
					if (!etat && lbl.getX() >= 580 && lbl.getX() <= 740 ) {

						lbl.setLocation(lbl.getLocation().x+10, lbl.getLocation().y );
					}
				}
				//vv1 up
				if (((Integer) args[6] == 0) && (Integer) args[7] == 0) {
						if (etat) {

							lbl.setLocation(lbl.getLocation().x, lbl.getLocation().y - 10);

						}
						if (!etat && lbl.getY() <= 280) {

							lbl.setLocation(lbl.getLocation().x, lbl.getLocation().y -10 );
						}
						if (!etat && lbl.getY() >= 480) {

							lbl.setLocation(lbl.getLocation().x, lbl.getLocation().y-10 );
						}
						if (!etat && lbl.getY() >= 280 && lbl.getY() <= 480 ) {

							lbl.setLocation(lbl.getLocation().x, lbl.getLocation().y+10 );
						}
					}

				//vv2 down
				if (((Integer) args[6] == 0) && (Integer) args[7] == 1) {

					if (etat)
						lbl.setLocation(lbl.getLocation().x, lbl.getLocation().y + 10);
					if (!etat && lbl.getY() <= 150) {
						lbl.setLocation(lbl.getLocation().x, lbl.getLocation().y + 10);
					}
					if (!etat && lbl.getY() >= 260) {
						lbl.setLocation(lbl.getLocation().x, lbl.getLocation().y + 10);
					}
					if (!etat && lbl.getY() >= 150 && lbl.getY() <= 260 ) {

						lbl.setLocation(lbl.getLocation().x, lbl.getLocation().y -10 );
					}
				}

			}
		});
		
		//autre cycle autres passagers 
		comportementparallele.addSubBehaviour(new CyclicBehaviour() {

			@Override
			public void action() {

				if (((Integer) args[6] == 0) && (Integer) args[7] == 0) {
					if (lbl.getY() <= -1) {
						lbl.setBounds(700, 580, 50, 50);
					}

				} else if (((Integer) args[6] == 0) && (Integer) args[7] == 1) {
					if (lbl.getY() >= 590) {
						lbl.setBounds(400, 0, 50, 50);
					}
				} else if (((Integer) args[6] == 1) && (Integer) args[7] == 0) {
					if (lbl.getX() <= 0) {
						lbl.setBounds(910, 200, 120, 60);
					}
				}
				else if (((Integer) args[6] == 1) && (Integer) args[7] == 1) {
					if (lbl.getX() >= 920) {
						lbl.setBounds(10, 450, 120, 60);
					}
				} else 
					block();

			}
		});
		
		

		addBehaviour(comportementparallele);
	}

}
