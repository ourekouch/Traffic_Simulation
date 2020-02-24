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

public class CarAgent extends Agent {

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

		comportementparallele.addSubBehaviour(new CyclicBehaviour() {

			@Override
			public void action() {
				ACLMessage msg = receive();
				if (msg != null) {
					if (msg.getContent().equals("RED")) {

						System.out.println("RED");

						etat = false;
					} else if (msg.getContent().equals("GREEN")) {
						System.out.println("GREEN");

						etat = true;
					}

				} else
					block();

			}
		});
		
		//comportement de mouvement de la voiture
		comportementparallele.addSubBehaviour(new TickerBehaviour(this, 50) {
			@Override
			protected void onTick() {

				if (((Integer) args[6] == 1) && (Integer) args[7] == 1) {

					if (etat) // avancement c'est le feu est vert
						lbl.setLocation(lbl.getLocation().x + 10, lbl.getLocation().y);
					if (!etat && lbl.getX() <= 280) {//avancement aussi c'est le feu est rouge et on'a pas encore arrivé au passage
						lbl.setLocation(lbl.getLocation().x + 10, lbl.getLocation().y);
					}//avancement aussi c'set le feu est rouge et on'a dépassé déja les feu rouge
					if (!etat && lbl.getX() >= 370) {
						lbl.setLocation(lbl.getLocation().x + 10, lbl.getLocation().y);
					}//marche arriére c'est on a arréter dans le  passage et le feu est rouge 
					if (!etat && lbl.getX() >= 280 && lbl.getX() <= 370 ) {

						lbl.setLocation(lbl.getLocation().x-5, lbl.getLocation().y );
					}
				}

				if (((Integer) args[6] == 1) && (Integer) args[7] == 0) {

					if (etat)// avancement c'est le feu est vert
						lbl.setLocation(lbl.getLocation().x - 10, lbl.getLocation().y);
					if (!etat && lbl.getX() <= 580) {//avancement aussi c'set le feu est rouge et on'a pas encore arrivé au passage
						lbl.setLocation(lbl.getLocation().x - 10, lbl.getLocation().y);
					}//avancement aussi c'set le feu est rouge et on'a dépassé déja les feu rouge
					if (!etat && lbl.getX() >= 740) {
						lbl.setLocation(lbl.getLocation().x - 10, lbl.getLocation().y);
					}//marche arriére c'est on a arréter dans le  passage et le feu est rouge 
					if (!etat && lbl.getX() >= 580 && lbl.getX() <= 740 ) {

						lbl.setLocation(lbl.getLocation().x+5, lbl.getLocation().y );
					}
				}//possibilité de changer le chemin parfois 

				if (((Integer) args[6] == 0) && (Integer) args[7] == 0) {

					if ((a == 1) && lbl.getLocation().getY() == 390) {

						lbl.setBounds(lbl.getX() + 10, 390, 120, 60);
						lbl.setIcon(new ImageIcon(getClass().getResource("/images/Car2.png")));

					} else if ((a == 2) && lbl.getLocation().getY() == 270) {

						lbl.setBounds(lbl.getX() - 10, 270, 120, 60);
						lbl.setIcon(new ImageIcon(getClass().getResource("/images/Car4.png")));
					} 

					else {
						if (etat) {

							lbl.setLocation(lbl.getLocation().x, lbl.getLocation().y - 10);

						}
						if (!etat && lbl.getY() <= 280) {

							lbl.setLocation(lbl.getLocation().x, lbl.getLocation().y - 10);
						}
						if (!etat && lbl.getY() >= 480) {

							lbl.setLocation(lbl.getLocation().x, lbl.getLocation().y - 10);
						}
						if (!etat && lbl.getY() >= 280 && lbl.getY() <= 480 ) {

							lbl.setLocation(lbl.getLocation().x, lbl.getLocation().y+5 );
						}
					}

				}

				if (((Integer) args[6] == 0) && (Integer) args[7] == 1) {

					if (etat)
						lbl.setLocation(lbl.getLocation().x, lbl.getLocation().y + 10);
					if (!etat && lbl.getY() <= 100) {
						lbl.setLocation(lbl.getLocation().x, lbl.getLocation().y + 10);
					}
					if (!etat && lbl.getY() >= 260) {
						lbl.setLocation(lbl.getLocation().x, lbl.getLocation().y + 10);
					}
					if (!etat && lbl.getY() >= 100 && lbl.getY() <= 260 ) {

						lbl.setLocation(lbl.getLocation().x, lbl.getLocation().y -5 );
					}
				}

			}
		});
	
		//initialisation des nouvels voitures lorque nos voitures ont dépassées les marges de notre carte de simulation 
		comportementparallele.addSubBehaviour(new CyclicBehaviour() {

			@Override
			public void action() {

				if (((Integer) args[6] == 0) && (Integer) args[7] == 0) {
					if (lbl.getY() <= -1) {

						lbl.setBounds(620, 580, 60, 120);
						a = r.nextInt(4) + 1;
					} else if (lbl.getX() <= 0) {
						lbl.setBounds(620, 580, 60, 120);
						lbl.setIcon(new ImageIcon(getClass().getResource((String) args[5])));
						a = r.nextInt(4) + 1;
					} else if (lbl.getX() >= 920) {
						lbl.setBounds(620, 580, 60, 120);
						lbl.setIcon(new ImageIcon(getClass().getResource((String) args[5])));
						a = r.nextInt(4) + 1;
					} else if (lbl.getY() >= 590) {
						lbl.setBounds(620, 580, 60, 120);
						lbl.setIcon(new ImageIcon(getClass().getResource((String) args[5])));
						a = r.nextInt(4) + 1;
					}

				} else if (((Integer) args[6] == 0) && (Integer) args[7] == 1) {
					if (lbl.getY() >= 590) {
						lbl.setBounds(450, 0, 60, 120);
					}
				} else if (((Integer) args[6] == 1) && (Integer) args[7] == 1) {
					if (lbl.getX() >= 920) {
						lbl.setBounds(10, 390, 120, 60);
					}
				} else if (((Integer) args[6] == 1) && (Integer) args[7] == 0) {
					if (lbl.getX() <= 0) {
						lbl.setBounds(910, 265, 120, 60);
					}
				} else
					block();

			}
		});

		addBehaviour(comportementparallele);
	}

}
