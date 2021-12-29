public class NBody {

	public static double readRadius(String FileName) {
		In in = new In(FileName);
		int num = in.readInt();
		double rad = in.readDouble();
		return rad;

	}

	public static Planet[] readPlanets(String FileName) {
		In in = new In(FileName);
		int num = in.readInt();
		Planet readPlanet[] = new Planet[num];
		double rad = in.readDouble();
		int i = 0;
		for (i = 0; i < num; i++) {
			double xxPos = in.readDouble();
			double yyPos = in.readDouble();
			double xxVel = in.readDouble();
			double yyVel = in.readDouble();
			double mass = in.readDouble();
			String imgFileName = in.readString();
			Planet planet = new Planet(xxPos, yyPos, xxVel, yyVel, mass, imgFileName);
			readPlanet[i] = planet;

		}
		return readPlanet;

	}


	public static void main(String[] args) {
		/* draw the background from -100 to 100 */
		double T = Double.parseDouble(args[0]);
		double dt = Double.parseDouble(args[1]);
		String filename = args[2];
		double radius = readRadius(filename);
		String backGround = "images/starfield.jpg";

		StdDraw.setScale(-radius, radius);
		StdDraw.picture(0, 0, backGround, radius * 2, radius * 2);
		StdDraw.show();

		Planet[] readPlanet = NBody.readPlanets(filename);
		In in = new In(filename);
		int num = in.readInt();
		for(int k = 0; k < num ; k++){
			readPlanet[k].draw();
		}

		/** read the array planet then calculate the scale to ensure the X and Y
		 , use Planet.draw **/


		double[] xForces = new double[num];
		double[] yForces = new double[num];
		for (double t = 0.0; t <= T; ) {
			StdDraw.enableDoubleBuffering();
			StdDraw.picture(0, 0, backGround, radius * 2, radius * 2);
			for (int i = 0; i < num; i++) {
				xForces[i] = readPlanet[i].calcNetForceExertedByX(readPlanet);
				yForces[i] = readPlanet[i].calcNetForceExertedByY(readPlanet);
				readPlanet[i].update(dt, xForces[i], yForces[i]);
				readPlanet[i].draw();
			}
			StdDraw.show();
			StdDraw.pause(10);
			t = t + dt;
		}

		StdOut.printf("%d\n", readPlanet.length);
		StdOut.printf("%.2e\n", radius);
		for (int i = 0; i < readPlanet.length; i++) {
			StdOut.printf("%11.4e %11.4e %11.4e %11.4e %11.4e %12s\n",
					readPlanet[i].xxPos, readPlanet[i].yyPos, readPlanet[i].xxVel,
					readPlanet[i].yyVel, readPlanet[i].mass, readPlanet[i].imgFileName);
		}
	}

}



