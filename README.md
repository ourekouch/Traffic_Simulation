# Traffic_Simulation
Traffic simulation on a simple road network using Jade Plateforme for implementation of agents and their behaviors as well as the interactions between them.

# Description of behaviors
In this project, each component has behaviors to help the good circulation of cars.

• The red lights [1 behavior]:
They send messages to avatars and cars either "RED" or "GREEN" so that they move forward or stop depending on the state of the lights

 
• Because [3 behaviors]:
It moves in the road with monitoring of the state of Red Light, also generally it
Receives messages, and manages movements according to the state of the red lights.

 
- Pedestrians [3 behaviors]:
he walks on the sidewalks and checks the state of the red lights before crossing the road, generally he receives the messages, and manages the movements according to the state of the red lights.
