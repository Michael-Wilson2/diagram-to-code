package diagram.BoxDecorators;
import code.ClassDescription;
import diagram.Box;
import diagram.BoxDecorator;
import diagram.DiagramElement;
import diagram.Emojis;

import java.awt.*;

public class Observable extends BoxDecorator {
  public Observable(int w, int h, Box box) {
    super(w, h, box);
  }

  @Override
  public void draw(Graphics g) {
    super.draw(g);
    drawEmoji(Emojis.GLOBE_EMOJI, g);
  }

  @Override
  public ClassDescription updateDescription(ClassDescription description) {
    description.addImport("java.beans.PropertyChangeSupport");
    description.setExtension("PropertyChangeSupport");
    description.setConstructorBody(String.format(
        "super(new Object());%n"
    ));

    return description;
  }

  @Override
  public void addConnection(DiagramElement connection) {
    BoxDecorator decoratorConnection = (BoxDecorator) connection;
    if (decoratorConnection instanceof Observer) {
      connections.add(decoratorConnection);
    }
  }
}
