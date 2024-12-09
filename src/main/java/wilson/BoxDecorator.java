package wilson;

import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.util.ArrayList;

public class BoxDecorator extends DiagramElement {
  public static int DEFAULT_DECORATOR_RADIUS = 50;

  protected DiagramElement next;
  private Box box;
  private int xOffset;
  private int yOffset;
  private ArrayList<BoxDecorator> connections;

  public BoxDecorator(int x, int y, Box box) {
    super(x, y, DEFAULT_DECORATOR_RADIUS, DEFAULT_DECORATOR_RADIUS);

    this.box = box;

    this.xOffset = box.bounds.x - x;
    this.yOffset = box.bounds.y - y;
    this.connections = new ArrayList<BoxDecorator>();
  }

  public BoxDecorator(int x, int y, int w, int h, DiagramElement next) {
    super(x, y, w, h);
    this.next = next;
  }

  @Override
  public void draw(Graphics g) {
    g.setColor(Color.BLACK);
    for (BoxDecorator boxDecorator : connections) {
      g.drawLine(getRelativeX(), getRelativeY(), boxDecorator.getRelativeX(), boxDecorator.getRelativeY());
    }
    g.setColor(Color.YELLOW);
    g.fillOval(getRelativeX(), getRelativeY(), bounds.width, bounds.height);
  }

  @Override
  public boolean occupies(int x, int y) {
    Ellipse2D ellipse = new Ellipse2D.Double(getRelativeX(), getRelativeY(), bounds.width, bounds.height);
    return ellipse.contains(x, y);
  }

  @Override
  public void addConnection(DiagramElement connection) {
    if (!(connection instanceof BoxDecorator)) {
      System.out.println("Unexpected pls fix"); // TODO: make this a logger or exception
      return;
    }
    connections.add((BoxDecorator) connection);
  }

  public int getRelativeX() {
    return box.bounds.x - xOffset;
  }

  public int getRelativeY() {
    return box.bounds.y - yOffset;
  }

  public void add(DiagramElement element) {
    this.next = element;
  }

  public DiagramElement getNext() {
    return this.next;
  }
}
