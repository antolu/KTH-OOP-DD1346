import java.lang.Math;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.HashSet;
import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;
import key.*;

public class Model {
    private int                 L = 10;
    public final double         DIM = 250;
    private int                 gridElementSize = 10;
    public HashSet<Particle>    particles = new HashSet<Particle>();
    public HashSet<Particle>    stuckParticles = new HashSet<Particle>();
    private Grid                grid;
    private double              circleRadius = 100;

    public Model(int numberOfParticles) {
        for (int i = 0; i < numberOfParticles; i++) {
            particles.add(new Particle());
        }

        grid = new Grid(DIM, gridElementSize);
    }

    public void updatePosition() {
        for (Iterator<Particle> iterator = particles.iterator() ; iterator.hasNext();) {
            Particle particle = iterator.next();
            particle.updatePosition();
            checkForCollision(particle, iterator);
        }
    }

    public int getStep() {
        return L;
    }

    public void setStep(int L) {
        this.L = L;
    }

    private void checkForCollision(Particle particle, Iterator<Particle> iterator) {
        if (Math.abs(particle.x) >= 245 || Math.abs(particle.y) >= 245) {
            grid.addParticle(particle);
            stuckParticles.add(particle);
            iterator.remove();
            return;
        } 

        if (approximatelyEqual(circleRadius, Math.hypot(particle.x, particle.y))) {
            grid.addParticle(particle);
            stuckParticles.add(particle);
            iterator.remove();
            return;
        }

        int xCell = (int) Math.ceil(particle.x) / gridElementSize;
        int yCell = (int) Math.ceil(particle.y) / gridElementSize;
        ArrayList<Grid.Cell> cells = grid.getCellArea(xCell, yCell);
        for (Grid.Cell cell : cells) {
            for (Particle secondParticle : cell.getCellContent()) {
                if (getDistance(particle, secondParticle) <= 2) {
                    grid.addParticle(particle);
                    stuckParticles.add(particle);
                    iterator.remove();
                    return;
                }
            }
        }
    }

    private double getDistance(Particle particle1, Particle particle2) {
        return Math.sqrt(Math.pow(particle1.x - particle2.x, 2) + Math.pow(particle1.y - particle2.y, 2));
    }

    private boolean approximatelyEqual(double firstValue, double secondValue) {
        double eps = 0.2;
        return Math.abs(firstValue - secondValue) < eps;
    }

    public class Particle {
        public double x;
        public double y;

        public Particle() {
            this(240 * ThreadLocalRandom.current().nextDouble(-1, 1),
                    240 * ThreadLocalRandom.current().nextDouble(-1, 1));
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