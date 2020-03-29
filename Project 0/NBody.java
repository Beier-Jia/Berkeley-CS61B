public class NBody {
    /** Return a double corresponding to the radius of the universe in a given file. */
    public static double readRadius(String fileName) {
        In in = new In(fileName);
        int number = in.readInt();
        double radius = in.readDouble();
        return radius;
    }

    /** Return an array of Bodys corresponding to the bodies in a given file. */
    public static Body[] readBodies(String fileName) {
        In in = new In(fileName);
        int number = in.readInt();
        double radius = in.readDouble();
        Body[] planets = new Body[number];
        for (int i = 0; i < number; i++) {
            double xxPos = in.readDouble();
            double yyPos = in.readDouble();
            double xxVel = in.readDouble();
            double yyVel = in.readDouble();
            double mass = in.readDouble();
            String imgFileName = in.readString();
            planets[i] = new Body(xxPos, yyPos, xxVel, yyVel, mass, imgFileName);
        }
        return planets;
    }

    /** Draw the initial universe state. */
    public static void main(String[] args) {
        /** Collect all needed input. */
        double T = Double.parseDouble(args[0]);
        double dt = Double.parseDouble(args[1]);
        String filename = args[2];
        Body[] planets = NBody.readBodies(filename);
        double radius = NBody.readRadius(filename);

        /** Draw the background. */
        StdDraw.setScale(-radius, radius);
        StdDraw.clear();
        StdDraw.picture(0, 0, "images/starfield.jpg");
		
        /** Draw all bodies. */
        for (Body planet: planets) {
            planet.draw();
        }

        StdDraw.enableDoubleBuffering();

        double t = 0;
        while (t <= T) {
            /** Create an xForces array and yForces array. */
            double[] xForces = new double[planets.length];
            double[] yForces = new double[planets.length];

            /** Calculate the net x and y forces for each Body. */
            for (int i = 0; i < planets.length; i++) {
                xForces[i] = planets[i].calcNetForceExertedByX(planets);
                yForces[i] = planets[i].calcNetForceExertedByY(planets);
            }

            /** Update each body. */
            for (int i = 0; i < planets.length; i++) {
                planets[i].update(dt, xForces[i], yForces[i]);
            }

            /** Draw the background image. */
            StdDraw.picture(0, 0, "images/starfield.jpg");

            /** Draw all bodies. */
            for (Body planet: planets) {
                planet.draw();
            }

            /** Show the offscreen buffer. */
            StdDraw.show();

            /** Pause the animation for 10 milliseconds. */
            StdDraw.pause(10);
            
            t += dt;
        }
        StdOut.printf("%d\n", planets.length);
        StdOut.printf("%.2e\n", radius);
        for (int i = 0; i < planets.length; i++) {
            StdOut.printf("%11.4e %11.4e %11.4e %11.4e %11.4e %12s\n", planets[i].xxPos, planets[i].yyPos, planets[i].xxVel, planets[i].yyVel, planets[i].mass, planets[i].imgFileName);   
        }
    }
}
