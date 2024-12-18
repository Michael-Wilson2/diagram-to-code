package project.diagram.DiagramElements;
import project.code.ClassDescription;
import project.code.CodeCreator;

import java.awt.*;

/** A box decoration for visually representing a factory class.
 *
 * @author Michael Wilson
 * @author Andrew Kulakovsky
 * @version 1.0
 */
public class Factory extends BoxDecorator {
  public Factory(int w, int h) {
    super(w, h);
  }

  @Override
  public void draw(Graphics g) {
    super.draw(g);
    drawEmoji(FACTORY_EMOJI, g);
  }

  @Override
  public ClassDescription updateDescription(ClassDescription description) {
    description.addMethod(String.format(
        "public %s create() {%n" +
        CodeCreator.TAB + "return new %s();%n" +
        "}", CodeCreator.PRODUCT_NAME_PLACEHOLDER, CodeCreator.PRODUCT_NAME_PLACEHOLDER
    ));

    if (diagramElement != null) {
      return diagramElement.updateDescription(description);
    } else {
      return description;
    }
  }

  @Override
  public void addConnection(DiagramElement connection) {
    BoxDecorator decoratorConnection = (BoxDecorator) connection;
    if (!connections.contains(decoratorConnection) && decoratorConnection instanceof Product) {
      connections.add(decoratorConnection);
      decoratorConnection.addConnection(this);
    }
  }
}
