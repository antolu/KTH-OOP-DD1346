import java.lang.Math;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.HashSet;
import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;
import key.*;

public class Model {
    private int                 L                   = 10;
    public final double         DIM                 = 250;
    private int                 gridElementSize     = 10;
    public HashSet<Particle>    particles           = new HashSet<Particle>();
    public HashSet<Particle>    stuckParticles      = new HashSet<Particle>();
    private Grid                grid;
    private double              circleRadius        = 100;

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

    public ArrayList<double[]> getPositions() {
        ArrayList<double[]> retArray = new ArrayList<double[]>(particles.size());
        for (Particle particle: particles) {
            retArray.add(new double[] {particle.x, particle.y});
        }
        return retArray;
    }

    public ArrayList<double[]> getStuckPositions() {
        ArrayList<double[]> retArray = new ArrayList<double[]>(stuckParticles.size());
        for (Particle particle: stuckParticles) {
            retArray.add(new double[] {particle.x, particle.y});
        }
        return retArray;
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
        ArrayList<Cell> cells = grid.getCellArea(xCell, yCell);
        for (Cell cell : cells) {
            for (Particle secondParticle : cell) {
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

    private class Particle {
        private double x;
        private double y;

        private Particle() {
            this(240 * ThreadLocalRandom.current().nextDouble(-1, 1),
                    240 * ThreadLocalRandom.current().nextDouble(-1, 1));
        }

        private Particle(double x, double y) {
            this.x = x;
            this.y = y;
        }

        private void updatePosition() {
            double theta = 2 * Math.PI * Math.random();
            x = x + L * Math.cos(theta);
            y = y + L * Math.sin(theta);
        }
    }

    private class Grid extends Hashtable<Key, Cell> {   
        private ArrayList<Key> keys = new ArrayList<Key>();
        private final int gridWidth;
        private final int gridElementSize;
    
        private Grid(double DIM, int gridElementSize) {
            gridWidth = (int) DIM / gridElementSize;
            this.gridElementSize = gridElementSize;
            for (int i = -gridWidth; i <= gridWidth; i++) {
                for (int j = -gridWidth; j <= gridWidth; j++) {
                    Key key = new Key(i, j);
                    this.put(key, new Cell());
                    keys.add(key);
                }
            }
        }
    
        private Cell getCell(int x, int y) {
            return this.get(new Key (x, y));
        }
    
        private void addParticle(Particle particle) {
            int xCell = (int) Math.ceil(particle.x) / gridElementSize;
            int yCell = (int) Math.ceil(particle.y) / gridElementSize;
            if (Math.abs(xCell) > gridWidth || Math.abs(yCell) > gridWidth) {
                return;
            }
            this.get(new Key(xCell, yCell)).add(particle);
        }
    
        private void clearGrid() {
            for (Key key : keys) {
                this.get(key).clear();
            }
        }
    
        private ArrayList<Cell> getCellArea(int x, int y) {
            ArrayList<Cell> retArr = new ArrayList<Cell>();
            for (int i = -1; i <= 1; i++) {
                for (int j = -1; j <= 1; j++) {
                    Cell cell = this.getCell(x + i, y + j);
                    if (cell != null) {
                        retArr.add(cell);
                    }
                }
            }
            return retArr;
        }
    }

    private class Cell extends ArrayList<Particle> {
    }    
}