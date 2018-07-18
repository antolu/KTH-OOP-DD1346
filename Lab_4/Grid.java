import java.util.Hashtable;
import java.lang.Math;
import key.*;
import java.util.ArrayList;

public class Grid extends Hashtable<Key, Cell> {   
    private ArrayList<Key> keys = new ArrayList<Key>();
    private final int gridWidth;
    private final int gridElementSize;

    public Grid(double DIM, int gridElementSize) {
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

    public Cell getCell(int x, int y) {
        return this.get(new Key (x, y));
    }

    public void addParticle(Model.Particle particle) {
        int xCell = (int) Math.ceil(particle.x) / gridElementSize;
        int yCell = (int) Math.ceil(particle.y) / gridElementSize;
        if (Math.abs(xCell) > gridWidth || Math.abs(yCell) > gridWidth) {
            return;
        }
        this.get(new Key(xCell, yCell)).add(particle);
    }

    public void clearGrid() {
        for (Key key : keys) {
            this.get(key).clear();
        }
    }

    public ArrayList<Cell> getCellArea(int x, int y) {
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