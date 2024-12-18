package project.diagram.DiagramElements;

import project.code.ClassDescription;

import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.util.ArrayList;

/** Abstract decorator class in decorator pattern. Represents a box decorator that decorates a box in the diagram.
 *
 * @author Michael Wilson
 * @author Andrew Kulakovsky
 * @version 1.0
 */
public abstract class BoxDecorator extends DiagramElement {
  public static String EYES_EMOJI = "\uD83D\uDC40";
  public static String GLOBE_EMOJI = "\uD83C\uDF10";
  public static String CROWN_EMOJI = " \uD83D\uDC51";
  public static String PALETTE_EMOJI = "\uD83C\uDFA8";
  public static String SPARKLE_EMOJI = new String(Character.toChars(0x2728));
  public static String CHAIN_EMOJI = "\uD83D\uDD17";
  public static String BRAIN_EMOJI = "\uD83E\uDDE0";
  public static String FACTORY_EMOJI = "\uD83C\uDFED";
  public static String BOX_EMOJI = "\uD83D\uDCE6";

  public static int DEFAULT_DECORATOR_RADIUS = 50;
  protected ArrayList<BoxDecorator> connections;
  protected DiagramElement diagramElement;

  public BoxDecorator(int x, int y) {
    super(x, y, DEFAULT_DECORATOR_RADIUS, DEFAULT_DECORATOR_RADIUS);
    this.connections = new ArrayList<>();
  }

  @Override
  public void draw(Graphics g) {
    if (diagramElement != null) {
      diagramElement.draw(g);
    }

    // Draw circle
    g.setColor(new Color(0xf0be5f));
    g.fillOval(bounds.x, bounds.y, bounds.width, bounds.height);

    // Draw connections
    g.setColor(Color.BLACK);
    for (BoxDecorator boxDecorator : connections) {
      g.drawLine((int) bounds.getCenterX(), (int) bounds.getCenterY(),
              (int) boxDecorator.bounds.getCenterX(), (int) boxDecorator.bounds.getCenterY());
    }
  }

  @Override
  public DiagramElement occupies(int x, int y) {
    Ellipse2D ellipse = new Ellipse2D.Double(bounds.x, bounds.y, bounds.width, bounds.height);
    if (ellipse.contains(x, y)) {
      return this;
    }
    if (diagramElement != null) {
      return diagramElement.occupies(x, y);
    }
    return null;
  }

  @Override
  public ClassDescription updateDescription(ClassDescription description) {
    return this.diagramElement.updateDescription(description); // should traverse linked list w/ decorator pattern
  }

  @Override
  public void move(Point pointerDelta) {
    if (diagramElement != null) {
      diagramElement.move(pointerDelta);
    }
    super.move(pointerDelta);
  }

  public abstract void addConnection(DiagramElement connection);

  public ArrayList<BoxDecorator> getConnections() {
    return connections;
  }

  public void drawEmoji(String emoji, Graphics g) {
    g.setFont(new Font("Segoe UI Emoji", Font.PLAIN, 20));
    int offsetX = -10;
    int offsetY = 8;
    g.drawString(emoji, (int) bounds.getCenterX() + offsetX, (int) bounds.getCenterY() + offsetY);
  }

  public void add(DiagramElement diagramElement) {
    this.diagramElement = diagramElement;
  }

  public DiagramElement getNext() {
    return this.diagramElement;
  }
}
