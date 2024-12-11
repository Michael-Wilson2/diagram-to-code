package wilson.BoxDecorators;
import code.ClassDescription;
import wilson.*;

import java.awt.*;

public class ChainNode extends BoxDecorator {
  public ChainNode(int w, int h, Box box) {
    super(w, h, box);
  }

  @Override
  public void draw(Graphics g) {
    super.draw(g);
    drawEmoji(Emojis.CHAIN_EMOJI, g);
  }

  @Override
  public ClassDescription updateDescription(ClassDescription description) {


    return description;
  }

  @Override
  public void addConnection(DiagramElement connection) {
    // TODO: Log this is impossible
  }
}