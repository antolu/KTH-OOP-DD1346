import java.lang.Math;
import java.util.concurrent.ThreadLocalRandom;

public class Model {
    private double L = 1;
    public Particle[] particles;

    public Model(int numberOfParticles) {
        particles = new Particle[numberOfParticles];

        for (int i = 0; i < numberOfParticles; i++) {
            particles[i] = new Particle();
        }
    }

    public void updatePosition() {

    }

    public double getStep() {
        return L;
    }

    public void setStep(double L) {
        this.L = L;
    }

    public class Particle {
        public double x;
        public double y;

        public Particle() {
            this(200 * ThreadLocalRandom.current().nextDouble(-1, 1), 
                 200 * ThreadLocalRandom.current().nextDouble(-1, 1)); 
        }

        public Particle(double x, double y) {
            this.x = x;
            this.y = y;
        }

        private void updatePosition() {
            double theta = 2 * Math.PI * Math.random();
            x = x + L * Math.cos(theta);
            y = y + L * Math.sin(theta);
        }
    }
}