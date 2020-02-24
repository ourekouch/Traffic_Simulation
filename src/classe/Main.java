package classe;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Point;
import java.awt.Toolkit;
import java.util.Timer;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import org.omg.CORBA.Bounds;

import jade.core.Agent;
import jade.core.Profile;
import jade.core.ProfileImpl;
import jade.core.Runtime;
import jade.core.behaviours.CyclicBehaviour;
import jade.lang.acl.ACLMessage;
import jade.util.ExtendedProperties;
import jade.util.leap.Properties;
import jade.wrapper.AgentContainer;
import jade.wrapper.AgentController;
import jade.wrapper.ContainerController;
import jade.wrapper.StaleProxyException;

public class Main {

	public static Gui frame;
	public static ContainerController container;
	public Main() throws StaleProxyException {
		// TODO Auto-generated constructor stub
		frame = new Gui();


		container = new Plateforme().getAContainer();

		// CAR verticle 1
		Object[] argsVV1 = { frame, 620, 580, 60, 120, "/images/Car3.png", 0, 0, container };
		container.createNewAgent("CarUp", CarAgent.class.getName(), argsVV1).start();

		// CAR verticle 2
		Object[] argsVV2 = { frame, 450, 0, 60, 120, "/images/Car1.png", 0, 1, container };
		container.createNewAgent("CarDown", CarAgent.class.getName(), argsVV2).start();

		// CAR horizontal 1
		Object[] argsVH1 = { frame, 910, 265, 120, 60, "/images/Car4.png", 1, 0, container };
		container.createNewAgent("CarLeft", CarAgent.class.getName(), argsVH1).start();

		// CAR horizontal 2
		Object[] argsVH2 = { frame, 10, 390, 120, 60, "/images/Car2.png", 1, 1, container };
		container.createNewAgent("CarRight", CarAgent.class.getName(), argsVH2).start();

		// Lights vertical 1 down right
		Object[] argsV1 = { frame, 680, 450, 30, 60, "/images/RRvertical.png", "/images/VVvertical.png",
				0, "CarUp",1,"Avatar4" };
		container.createNewAgent("FeuRougeDownRight", LightsAgent.class.getName(), argsV1).start();

		// Lights vertical 2 up left 
		Object[] argsV2 = { frame, 415, 200, 30, 60, "/images/RRvertical.png", "/images/VVvertical.png", 
				0, "CarDown" ,1,"Avatar3" };
		container.createNewAgent("FeuRougeUpLeft", LightsAgent.class.getName(), argsV2).start();

		// Lights horizontal up right
		Object[] argsH1 = { frame, 670, 240, 60, 30, "/images/RRhorizontal.png", "/images/VVhorizontal.png",
				1, "CarLeft" ,1,"Avatar1"};
		container.createNewAgent("FeuRougeUpRight", LightsAgent.class.getName(), argsH1).start();

		// Lights 2horizontal down left
		Object[] argsH2 = { frame, 390, 440, 60, 30, "/images/RRhorizontal.png", "/images/VVhorizontal.png",
				1, "CarRight",1,"Avatar2"  };
		container.createNewAgent("FeuRougeDownLeft", LightsAgent.class.getName(), argsH2).start();

		// avatarV up
		Object[] Avatar1 = { frame, 700, 580, 50, 50, "/images/avatar.png", 0, 0, container };			
		container.createNewAgent("Avatar1", WalkerAgent.class.getName(), Avatar1).start();
		// avatarV down
		Object[] Avatar2 = { frame, 400, 0, 50, 50, "/images/avatar.png", 0, 1, container };			
		container.createNewAgent("Avatar2", WalkerAgent.class.getName(), Avatar2).start();
		// avatarH left
		Object[] Avatar3 = { frame, 910, 200, 50, 50, "/images/avatar.png", 1, 0, container };			
		container.createNewAgent("Avatar3", WalkerAgent.class.getName(), Avatar3).start();
		// avatarH right
		Object[] Avatar4 = { frame, 10, 450, 50, 50, "/images/avatar.png", 1, 1, container };			
		container.createNewAgent("Avatar4", WalkerAgent.class.getName(), Avatar4).start();
	}

	public static void main(String[] args) throws StaleProxyException {
		// TODO Auto-generated method stub

		Main window = new Main();
		window.frame.setVisible(true);
	}

}
