package wilson;

import java.awt.*;
import java.util.ArrayList;

public abstract class DiagramElement {
  protected boolean isSelected;
  protected Rectangle bounds;
  protected ArrayList<DiagramElement> connections;

  public DiagramElement(int x, int y, int w, int h) {
    this.bounds = new Rectangle(x, y, w, h);
    this.connections = new ArrayList<>();
  }

  public abstract void draw(Graphics g);

  public abstract boolean occupies(int x, int y);

  public void setSelected(boolean isSelected) {
    this.isSelected = isSelected;
  }

  public boolean isSelected() {
    return this.isSelected;
  }

  public void setPosition(int x, int y) {
    this.bounds.setLocation(x - bounds.width / 2, y - bounds.height / 2);
  }

  public void addConnection(DiagramElement connection) {
    connections.add(connection);
  }
}
