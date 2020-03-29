public class Body {
    /** Instance variables. */
    public double xxPos;
    public double yyPos;
    public double xxVel;
    public double yyVel;
    public double mass;
    public String imgFileName;

    /** First constructor. */
    public Body(double xP, double yP, double xV, double yV, double m, String img) {
        xxPos = xP;
        yyPos = yP;
        xxVel = xV;
        yyVel = yV;
        mass = m;
        imgFileName = img;
    }
    
    /** Second constructor. */
    public Body(Body b) {
        xxPos = b.xxPos;
        yyPos = b.yyPos;
        xxVel = b.xxVel;
        yyVel = b.yyVel;
        mass = b.mass;
        imgFileName = b.imgFileName;
    }
    
    /** Calculate the distance between this and b2. */
    public double calcDistance(Body b2) {
        double dx = this.xxPos - b2.xxPos;
        double dy = this.yyPos - b2.yyPos;
        double r = Math.sqrt(dx * dx + dy * dy);
        return r;
    }

    /** Calculate the force exerted on this by b2. */
    public double calcForceExertedBy(Body b2) {
        double g = 6.67e-11;
        double r = this.calcDistance(b2);
        double f = g * this.mass * b2.mass / r / r;
        return f;
    }

    /** Calculate the force exerted on this by b2 in the X direction. */
    public double calcForceExertedByX(Body b2) {
        double f = this.calcForceExertedBy(b2);
        double dx = b2.xxPos - this.xxPos;
        double r = this.calcDistance(b2);
        double fx = f * dx / r;
        return fx;
    }

    /** Calculate the force exerted on this by b2 in the Y direction. */
    public double calcForceExertedByY(Body b2) {
        double f = this.calcForceExertedBy(b2);
        double dy = b2.yyPos - this.yyPos;
        double r = this.calcDistance(b2);
        double fy = f * dy / r;
        return fy;
    }

    /** Calculate the net X force exerted on this by all bodies in the array. */
    public double calcNetForceExertedByX(Body[] bodyarray) {
        double fX = 0;
        for (Body b: bodyarray) {
            if (! this.equals(b)) {
                fX += this.calcForceExertedByX(b);
            }
        }
        return fX;
    }

    /** Calculate the net Y force exerted on this by all bodies in the array. */
    public double calcNetForceExertedByY(Body[] bodyarray) {
        double fY = 0;
        for (Body b: bodyarray) {
            if (! this.equals(b)) {
                fY += this.calcForceExertedByY(b);
            }
        }
        return fY;
    }

    /** Update the body’s position and velocity instance variables. */
    public void update(double dt, double fX, double fY) {
        double ax = fX / this.mass;
        double ay = fY / this.mass;
        this.xxVel += ax * dt;
        this.yyVel += ay * dt;
        this.xxPos += this.xxVel * dt;
        this.yyPos += this.yyVel * dt;
    }

    /** Draw this at its appropriate position. */
    public void draw() {
        StdDraw.picture(this.xxPos, this.yyPos, "images/" + this.imgFileName);
    }
}