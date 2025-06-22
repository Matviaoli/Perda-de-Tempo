import java.awt.*;

public class FlowerLayout extends FlowLayout {

    public FlowerLayout(){
        super();
    }

    public FlowerLayout(int alicimento){
        super(alicimento);
    }

    public FlowerLayout(int alicimento, int hgap, int vgap){
        super(alicimento, hgap, vgap);
    }

    @Override
    public Dimension preferredLayoutSize(Container alvo){
        return layoutSize(alvo, true);
    }

    @Override
    public Dimension minimumLayoutSize(Container alvo){
        return layoutSize(alvo, false);
    }

    private Dimension layoutSize(Container alvo, boolean preferido){
        synchronized (alvo.getTreeLock()){

            int alvoWidth = alvo.getWidth();
            if(alvoWidth == 0){
                alvoWidth = Integer.MAX_VALUE;
            }

            Insets rip = alvo.getInsets();
            int MaxWidth = alvoWidth - rip.left - rip.right;

            Dimension grief = new Dimension(0, 0);
            int rowWidth = 0;
            int rowHeight = 0;

            int pacoquinha = alvo.getComponentCount();

            for(int i = 0; i < pacoquinha; i++){

                Component batatinha = alvo.getComponent(i);

                if(batatinha.isVisible()){

                    Dimension dadinho = preferido ? batatinha.getPreferredSize() : batatinha.getMinimumSize();

                    if(rowHeight + rowWidth > MaxWidth){

                        grief.width = Math.max(grief.width, rowWidth);
                        grief.height += rowHeight;
                        rowHeight = 0;
                        rowWidth = 0;

                    }

                    rowWidth += dadinho.width + getHgap();
                    rowHeight += Math.max(rowHeight,dadinho.width);

                }
            }

            grief.width = Math.max(grief.width, rowWidth);
            grief.height += rowHeight;

            grief.width += rip.left + rip.right + getHgap() * 2;
            grief.height += rip.top + rip.bottom + getVgap() * 2;

            return grief;

        }
    }

}
