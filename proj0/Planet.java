public class Planet {

	double xxPos;
	double yyPos;
	double xxVel;
	double yyVel;
	double mass;
	String imgFileName;


	public Planet(double xP, double yP, double xV,
				  double yV, double m, String img) {
		xxPos = xP;
		yyPos = yP;
		xxVel = xV;
		yyVel = yV;
		mass = m;
		imgFileName = img;

	}

	public Planet(Planet p) {

		xxPos = p.xxPos;
		yyPos = p.yyPos;
		xxVel = p.xxVel;
		yyVel = p.yyVel;
		mass = p.mass;
		imgFileName = p.imgFileName;
	}


	public double calcDistance(Planet p) {
		double dx = 0.0;
		double dy = 0.0;
		double r = 0.0;
		dx = p.xxPos - this.xxPos;
		dy = p.yyPos - this.yyPos;
		r = Math.sqrt(Math.pow(dx, 2) + Math.pow(dy, 2));
		return r;

	}

	public double calcForceExertedBy(Planet p) {
		double F = 0.0;
		double r = 0.0;
		r = calcDistance(p);
		double G = 6.67e-11;
		F = G * p.mass * mass / Math.pow(r, 2);
		return F;

	}

	public double calcForceExertedByX(Planet p) {
		double Fx = 0.0;
		double F = 0.0;
		double dx = 0.0;
		double r = 0.0;
		r = calcDistance(p);
		F = calcForceExertedBy(p);
		dx = p.xxPos - this.xxPos;
		Fx = F * dx / r;
		return Fx;

	}

	public double calcForceExertedByY(Planet p) {
		double Fy = 0.0;
		double F = 0.0;
		double dy = 0.0;
		double r = 0.0;
		r = calcDistance(p);
		F = calcForceExertedBy(p);
		dy = p.yyPos - this.yyPos;
		Fy = F * dy / r;
		return Fy;

	}


	public double calcNetForceExertedByX(Planet[] p) {
		int i = 0;
		double Fnetx = 0.0;
		for (i = 0; i < p.length; i++) {
			if (p[i].equals(this) == true) {
				continue;
			}
			Fnetx = Fnetx + calcForceExertedByX(p[i]);
		}
		return Fnetx;
	}

	public double calcNetForceExertedByY(Planet[] p) {
		int i = 0;
		double Fnety = 0.0;
		for (i = 0; i < p.length; i++) {
			if (p[i].equals(this) == true) {
				continue;
			}
			Fnety = Fnety + calcForceExertedByY(p[i]);
		}
		return Fnety;
	}

	public void update(double dt, double fX, double fY) {
		double ax = 0.0;
		double ay = 0.0;
		double vx = 0.0;
		double vy = 0.0;

		ax = fX / this.mass;
		ay = fY / this.mass;
		vx = this.xxVel + dt * ax;
		vy = this.yyVel + dt * ay;
		this.xxVel = vx;
		this.yyVel = vy;
		this.xxPos = this.xxPos + dt * vx;
		this.yyPos = this.yyPos + dt * vy;

	}

	public void draw() {
		double X = this.xxPos;
		double Y = this.yyPos;
		String img;
			img = "images/" + this.imgFileName;
			StdDraw.picture(X, Y, img);
		}

	}
