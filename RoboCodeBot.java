package teste;
import robocode.*;
import robocode.AdvancedRobot;
import robocode.HitRobotEvent;
import robocode.ScannedRobotEvent;
//import java.awt.Color;

// API help : https://robocode.sourceforge.io/docs/robocode/robocode/Robot.html

/**
 * RoboCodeBot - a robot by (your name here)
 */
public class RoboCodeBot extends AdvancedRobot{

	// Gire o robô para o meio do campo de batalha (hellen)
        turnRadarRight(Double.POSITIVE_INFINITY);
	/**
	 * run: RoboCodeBot's default behavior
	 */
	public void run() {
		// Initialization of the robot should be put here

		// After trying out your robot, try uncommenting the import at the top,
		// and the next line:

		// setColors(Color.red,Color.blue,Color.green); // body,gun,radar

		// Robot main loop
		while(true) {
			//samara - fica indo pra frente na velocidade maxima
			ahead(10000);
			setMaxVelocity(5);

			// Execute a verificação de colisão (hellen)
            		avoidCollision();

			// Gire a arma e atire no meio do campo de batalha (hellen)
            		turnGunRight(getHeading() - getGunHeading());
            		fire(1); // Define a potência do tiro (de 0.1 a 3)
            		execute(); // Executa o tiro
		}
	}

	/**
	 * onScannedRobot: What to do when you see another robot
	 */
	public void onScannedRobot(ScannedRobotEvent e) {
		// Replace the next line with any behavior you would like
		fire(1);
	}

	/**
	 * onHitByBullet: What to do when you're hit by a bullet
	 */
	public void onHitByBullet(HitByBulletEvent e) {
		// Replace the next line with any behavior you would like
		back(10);
	}
	
	/**
	 * onHitWall: What to do when you hit a wall
	 */
	public void onHitWall(HitWallEvent e) {
		//samara - vira em 90 graus
		setTurnRight(5);
	}	

	//quando encosta em um robo 
	public void onHitRobot(HitRobotEvent e) {
		if (e.getBearing() > -10 && e.getBearing() < 10) {
			fire(3);
		}
		if (e.isMyFault()) {
			turnRight(10);
		}
	}

	// Override o método onScannedRobot() para reagir quando um robô for detectado (hellen)
	    @Override
	    public void onScannedRobot(ScannedRobotEvent event) {
	        // Gire a arma em direção ao robô detectado
	        turnGunRight(getHeading() - getGunHeading() + event.getBearing());
	        // Atire no robô detectado
	        fire(1);
	    }

	// Método para evitar colisões (hellen)
	    private void avoidCollision() {
	        // Verifique se estamos muito próximos de uma parede
	        if (getX() < 50 || getX() > getBattleFieldWidth() - 50 ||
	                getY() < 50 || getY() > getBattleFieldHeight() - 50) {
	            // Se estivermos muito próximos, faça um giro aleatório para escapar
	            setTurnRight(90);
	            setAhead(100);
	            execute(); // Executa o movimento
	        }
}
