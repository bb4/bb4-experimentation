// Copyright by Barry G. Becker, 2005-2018. Licensed under MIT License: http://www.opensource.org/licenses/MIT
package com.barrybecker4.experimentation.colormixer

import com.barrybecker4.ui.util.GUIUtil
import javax.swing._
import java.awt._


/**
  * @author Barry Becker
  */
object MixPanel {
  /* // mix like light
     public static final int ADDITIVE_MIX = 0;
     // mix like paint
     public static final int SUBTRACTIVE_MIX = 1;
     // composite with over operator
     public static final int OVER_MIX = 2;
     // composite with under operator
     public static final int OVER_MIX = 3;
     // composite with under operator
     public static final int OVER_MIX = 4;
     // composite with under operator
     public static final int OVER_MIX = 5;
     // composite with under operator
     public static final int OVER_MIX = 6;
     // composite with under operator
     public static final int UNDER_MIX = 7;

     public static final int NUM_TYPES = 8;
     */
  private val FONT = new Font(GUIUtil.DEFAULT_FONT_FAMILY, Font.BOLD, 16)
}

class MixPanel(var colorA: Color, var opacityA: Float, var colorB: Color, var opacityB: Float, var rule: Int, var label: String) extends JPanel {

  setColors(colorA, opacityA, colorB, opacityB)
  private var opacity = .4f

  def setColors(colorA: Color, opacityA: Float, colorB: Color, opacityB: Float): Unit = {
    this.colorA = colorA
    this.colorB = colorB
    this.opacityA = opacityA
    this.opacityB = opacityB
    this.setDoubleBuffered(false)
    this.invalidate()
    this.repaint()
  }

  def setOpacity(op: Float): Unit = 
    opacity = op

  override def paint(g: Graphics): Unit = paintComponent(g)

  override protected def paintComponent(g: Graphics): Unit = {
    super.paintComponents(g)
    val g2 = g.asInstanceOf[Graphics2D]
    g2.setColor(this.getBackground)
    g2.fillRect(1, 1, this.getWidth, this.getHeight)
    g2.setColor(colorA)
    g2.fillRect(10, 1, 100, 30)
    val opacity = this.opacity
    var composite: AlphaComposite = AlphaComposite.getInstance(rule, opacity)
    /*
            switch (type) {
                case ADDITIVE_MIX :
                    //mixColor = interpColors(colorA, colorB);
                    //g2.setComposite(AlphaComposite.Dst);
                    composite = AlphaComposite.getInstance(AlphaComposite.SRC_ATOP, opacity);
                    System.out.println("add");
                    break;
                case SUBTRACTIVE_MIX :
                    //mixColor = interpColors(colorA, colorB);
                    composite = AlphaComposite.getInstance(AlphaComposite.SRC_IN, opacity);
                    //g2.setComposite(AlphaComposite.DstAtop);
                    System.out.println("sub");
                    break;
                case OVER_MIX :
                    //mixColor = interpColors(colorA, colorB);
                    //g2.setComposite(AlphaComposite.DstIn);
                    composite = AlphaComposite.getInstance(AlphaComposite.SRC_OUT, opacity);
                    System.out.println("over");
                    break;
                case UNDER_MIX :
                    g2.setComposite(AlphaComposite.DstOver);
                    composite = AlphaComposite.getInstance(AlphaComposite.SRC_OVER, opacity);
                    //mixColor = interpColors(colorA, colorB);
                    //g2.setColor(mixColor);
                    //g2.fillRect(60, 10, 50, 50);
                    System.out.println("under");
                    break;
                default:
                    assert (false):"invalide type="+type;
            }  */
    g2.setComposite(composite)
    g2.setColor(colorB)
    g2.fillRect(40, 12, 100, 30)
    g2.setComposite(AlphaComposite.Src)
    g2.setFont(MixPanel.FONT)
    g2.setColor(Color.BLACK)
    g2.drawString(label, 160, 20)
  }
}