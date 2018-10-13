package ca.utoronto.utm.paint;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Observable;


public class PaintModel extends Observable {
	private int virsiontime = -1;
	private ArrayList<Point> points=new ArrayList<Point>();
	private ArrayList<Point> fakepoints=new ArrayList<Point>();
	private ArrayList<Shape> shapes = new ArrayList<Shape>();
	private ArrayList<Shape> fakeshapes = new ArrayList<Shape>();
	private HashMap virsionshapes = new HashMap();
	private HashMap virsionpoints = new HashMap();
	private ArrayList<Point> temppoints= new ArrayList<Point>();
	private ArrayList<Shape> tempshapes = new ArrayList<Shape>();
	private Shape selectedShape = null;
	private Shape copiedShape = null;
	private int undotime = 0;
	
	
	public void addSelectedShape(Shape shape) {
		this.selectedShape = shape;
	}
	
	public Shape getSelectShape() {
		
		return this.selectedShape;
	}
	
	public void addPoint(Point p){
		this.points.add(p);
		cleanup();
		this.setChanged();
		this.notifyObservers();
	}
	
	
	public ArrayList<Point> getPoints(){
		return points;
	}
	
	public void addShapes(Shape shape) {
		this.shapes.add(shape);
		cleanup();
	
		this.setChanged();
		this.notifyObservers();
	}
	
	public ArrayList<Shape> getShape(){
		return shapes;
	}
	
	public void ClearAll() {
		this.points = new ArrayList<Point>();
		this.shapes = new ArrayList<Shape>();
		this.selectedShape = null;
	}
	
	private void cleanup() {
		Shape newest = this.shapes.get(this.shapes.size()-1);
		this.shapes.removeIf(p ->p.equals(newest));
		this.shapes.add(newest);
	}
	
	public void update() {
		this.virsiontime++;
	
		this.tempshapes = new ArrayList<>();
		this.temppoints = new ArrayList<>();
		Shape tempshape = null;
		for (Shape k:this.shapes) {
			try {
				tempshape = (Shape) k.clone();
				this.tempshapes.add(tempshape);
			} catch (CloneNotSupportedException e) {
				e.printStackTrace();
			}
		}
		Point temppoint = null;
		for (Point p: this.points) {
			try {
				temppoint = (Point) p.clone();
				this.temppoints.add(temppoint);
			} catch (CloneNotSupportedException e) {
				e.printStackTrace();
			}
		}
	
	
		this.virsionshapes.put(this.virsiontime, this.tempshapes);
		this.virsionpoints.put(this.virsiontime, this.temppoints);

		
	}
	public void Undo() {
		
		if (this.virsionshapes.size() <=1) {
			
			this.shapes = new ArrayList<Shape>();
			this.points = new ArrayList<Point>();
			this.virsionshapes = new HashMap<Integer,ArrayList>();
			this.virsionpoints = new HashMap<Integer,ArrayList>();
			this.virsiontime = 0;
		}else {
			this.tempshapes = new ArrayList<>();
			this.temppoints = new ArrayList<>();
			Shape tempshape = null;
			ArrayList<Shape> kk = (ArrayList<Shape>) this.virsionshapes.get(this.virsiontime-1);
			ArrayList<Point> pp = (ArrayList<Point>) this.virsionpoints.get(this.virsiontime-1);
			for (Shape k: kk) {
				try {
					tempshape = (Shape) k.clone();
					this.tempshapes.add(tempshape);
				} catch (CloneNotSupportedException e) {
					e.printStackTrace();
				}
			}
			Point temppoint = null;
			for (Point p: pp) {
				try {
					temppoint = (Point) p.clone();
					this.temppoints.add(temppoint);
				} catch (CloneNotSupportedException e) {
					e.printStackTrace();
				}
			}
			this.virsionshapes.remove(this.virsiontime);
			this.virsionpoints.remove(this.virsiontime);
			this.shapes = this.tempshapes;
			this.points = this.temppoints;
			this.virsiontime = this.virsiontime-1;
		}
		
		this.setChanged();
		this.notifyObservers();
		
	}
	
	public void select(int x, int y) {
		for(Shape shape : this.shapes) {
			if (shape.getClass().getSimpleName().equals("Square") || shape.getClass().getSimpleName().equals("Rectangle1") || shape.getClass().getSimpleName().equals("Circle")) {
				if (shape.havePoint(x, y)) {
					this.selectedShape = shape;
				}
			}
		}
	}
	
	public void copy(Shape shape) {
		try {
			this.copiedShape = (Shape) shape.clone();
		} catch (CloneNotSupportedException e) {
			e.printStackTrace();
		}

	}
	
	public void paste() {
		if (this.copiedShape != null) {
			Point start = new Point(this.copiedShape.getStart().getX() + 10, this.copiedShape.getStart().getY() + 10);
			this.copiedShape.setStart(start);
			this.addShapes(this.copiedShape);
			this.copiedShape = null;
		}
	}
}