package es.upm.pproject.sokoban.view;

import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.filechooser.FileNameExtensionFilter;

import es.upm.pproject.sokoban.controller.ControllerInterface;
import es.upm.pproject.sokoban.model.ScoreInterface;

public class ButtonPanel extends JPanel {

    ControllerInterface controller;

    JLabel levelScore;

    JLabel TotalScore;

    public ButtonPanel() {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setBorder(new EmptyBorder(10, 10, 10, 10));

        JButton undoButton = new JButton("Undo");
        undoButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                controller.undo();
            }
        });

        JButton restartLevel = new JButton("Restart level");
        restartLevel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                controller.reStartLevel();
            }
        });

        JButton restartGame = new JButton("Restart game");
        restartGame.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                controller.reStartGame();
            }
        });

        JButton save = new JButton("Save");
        save.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                controller.save(filehandler(true));
            }
        });

        JButton load = new JButton("Load");
        load.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                controller.load(filehandler(false));
            }
        });

        levelScore = new JLabel("level score = 0");
        levelScore.setFont(new Font("GEORGIA", Font.BOLD, 24));

        TotalScore = new JLabel("Total score = 0");
        TotalScore.setFont(new Font("GEORGIA", Font.BOLD, 24));

        Box buttonBox = Box.createVerticalBox();
        buttonBox.add(Box.createVerticalGlue());
        buttonBox.add(undoButton);
        buttonBox.add(Box.createVerticalStrut(20));
        buttonBox.add(restartLevel);
        buttonBox.add(Box.createVerticalStrut(20));
        buttonBox.add(restartGame);
        buttonBox.add(Box.createVerticalStrut(20));
        buttonBox.add(save);
        buttonBox.add(Box.createVerticalStrut(20));
        buttonBox.add(load);
        buttonBox.add(Box.createVerticalStrut(20));
        buttonBox.add(levelScore);
        buttonBox.add(Box.createVerticalStrut(20));
        buttonBox.add(TotalScore);
        buttonBox.add(Box.createVerticalGlue());

        add(buttonBox);

        setVisible(true);
    }

    public void setController(ControllerInterface controller) {
        this.controller = controller;
    }

    public void setLevelScore(ScoreInterface score) {
        levelScore.setText("level score = " + score.getLevelScore());
        TotalScore.setText("Total score = " + score.getTotalScore());
    }

    private File filehandler(boolean save) {
        JFileChooser fileChooser = new JFileChooser("");
        int choice;
        if (save) {
            choice = fileChooser.showSaveDialog(this);
            fileChooser.setSelectedFile(new File("savedGame-" + LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMdd-HHmmss")) +".json"));
        } else {
            choice = fileChooser.showOpenDialog(this);
        }
        FileNameExtensionFilter filter = new FileNameExtensionFilter("JSON File", "json");
        fileChooser.setFileFilter(filter);
        fileChooser.setAcceptAllFileFilterUsed(false);
        if (choice == JFileChooser.APPROVE_OPTION) {
            return fileChooser.getSelectedFile();
        }
        return null;
    }
}
