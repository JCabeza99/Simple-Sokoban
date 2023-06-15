package es.upm.pproject.sokoban.view;

import java.awt.Font;

import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class TitlePanel extends JPanel{

    private JLabel level;

    public TitlePanel() {
        
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        JLabel title = new JLabel("SOKOBAN");
        title.setAlignmentX(CENTER_ALIGNMENT);
        title.setFont(new Font("GEORGIA", Font.BOLD, 24));
        title.setLabelFor(this);

        level = new JLabel("");
        level.setAlignmentX(CENTER_ALIGNMENT);
        level.setFont(new Font("GEORGIA", Font.PLAIN, 24));
        level.setLabelFor(this);

        add(title);
        add(level);

        setVisible(true);
        
    }

    public void setLevel(String name) {
        this.level.setText(name);
    }
}
