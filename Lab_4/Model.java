import java.lang.Math;
import java.util.Hashtable;
import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;
import key.*;

public class Model {
    private int L = 10;
    public final double DIM = 250;
    private int gridElementSize = 10;
    public ArrayList<Particle> particles = new ArrayList<Particle>();
    public ArrayList<Particle> stuckParticles = new ArrayList<Particle>();
    private Grid grid;

    public Model(int numberOfParticles) {
        for (int i = 0; i < numberOfParticles; i++) {
            particles.add(new Particle());
        }

        grid = new Grid(DIM, gridElementSize);
    }

    public void updatePosition() {
        for (Particle particle : particles) {
            particle.updatePosition();
            checkForCollision(particle);
        }
        // grid.clearGrid();
    }

    public int getStep() {
        return L;
    }

    public void setStep(int L) {
        this.L = L;
    }

    private void addStuckToGrid() {
        for (Particle particle : stuckParticles) {
            grid.addParticle(particle);
        }
    }

    private void checkForCollision(Particle particle) {
        if (particle.reachedBorder) {
            return;
        }
        if (Math.abs(particle.x) >= 245 || Math.abs(particle.y) >= 245) {
            particle.reachedBorder = true;
            grid.addParticle(particle);
            return;
        }

        int xCell = (int) Math.ceil(particle.x) / gridElementSize;
        int yCell = (int) Math.ceil(particle.y) / gridElementSize;
        ArrayList<Grid.Cell> cells = grid.getCellArea(xCell, yCell);
        for (Grid.Cell cell : cells) {
            for (Particle secondParticle : cell.getCellContent()) {
                if (getDistance(particle, secondParticle) <= 2) {
                    particle.reachedBorder = true;
                    grid.addParticle(particle);
                    return;
                }
            }
        }
    }

    private double getDistance(Particle particle1, Particle particle2) {
        return Math.sqrt(Math.pow(particle1.x - particle2.x, 2) + Math.pow(particle1.y - particle2.y, 2));
    }

    public class Particle {
        public double x;
        public double y;
        private Boolean reachedBorder = false;

        public Particle() {
            this(200 * ThreadLocalRandom.current().nextDouble(-1, 1),
                    200 * ThreadLocalRandom.current().nextDouble(-1, 1));
        }

        public Particle(double x, double y) {
            this.x = x;
            this.y = y;
        }

        private void updatePosition() {
            if (reachedBorder)
                return;

            double theta = 2 * Math.PI * Math.random();
            x = x + L * Math.cos(theta);
            y = y + L * Math.sin(theta);
        }

        public Boolean hasReachedBorder() {
            return reachedBorder;
        }
    }
}