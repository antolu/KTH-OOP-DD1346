import java.util.Hashtable;
import java.lang.Math;
import key.*;
import java.util.ArrayList;

public class Grid {

    private Hashtable<Key, Cell> grid;
    private ArrayList<Key> keys = new ArrayList<Key>();
    private final int gridWidth;
    private final int gridElementSize;

    public Grid(double DIM, int gridElementSize) {
        grid = new Hashtable<Key, Cell>();
        gridWidth = (int) DIM / gridElementSize;
        this.gridElementSize = gridElementSize;
        for (int i = -gridWidth; i <= gridWidth; i++) {
            for (int j = -gridWidth; j <= gridWidth; j++) {
                Key key = new Key(i, j);
                grid.put(key, new Cell());
                keys.add(key);
            }
        }
    }

    public Cell get(int x, int y) {
        return grid.get(new Key (x, y));
    }

    public void addParticle(Model.Particle particle) {
        int xCell = (int) Math.ceil(particle.x) / gridElementSize;
        int yCell = (int) Math.ceil(particle.y) / gridElementSize;
        if (Math.abs(xCell) > gridWidth || Math.abs(yCell) > gridWidth) {
            return;
        }
        grid.get(new Key(xCell, yCell)).addCellContent(particle);
    }

    public void clearGrid() {
        for (Key key : keys) {
            grid.get(key).clear();
        }
    }

    public ArrayList<Cell> getCellArea(int x, int y) {
        ArrayList<Cell> retArr = new ArrayList<Cell>();
        for (int i = -1; i <= 1; i++) {
            for (int j = -1; j <= 1; j++) {
                Cell cell = get(x + i, y + j);
                if (cell != null) {
                    retArr.add(cell);
                }
            }
        }
        return retArr;
    }

    public class Cell {
        private ArrayList<Model.Particle> cellContent;
    
        public Cell() {
            cellContent = new ArrayList<Model.Particle>();
        }
    
        public void addCellContent(Model.Particle particle) {
            cellContent.add(particle);
        }
    
        public ArrayList<Model.Particle> getCellContent() {
            return cellContent;
        }

        public void clear() {
            cellContent.clear();
        }

        public Boolean isEmpty() {
            return cellContent.isEmpty();
        }
    }
    
}