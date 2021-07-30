package com.rakitov.texteditor;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * @author Stanislav Rakitov
 */
public class TextEditor extends JFrame implements ActionListener {

  JTextArea textArea;
  JScrollPane scrollPane;
  JSpinner fontSizeSpinner;
  JLabel fontLabel;
  JButton fontColorButton;
  JComboBox fontBox;


  public TextEditor() throws HeadlessException {
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    this.setTitle("Bro text editor");
    this.setSize(500, 500);
    this.setLayout(new FlowLayout());
    this.setLocationRelativeTo(null);

    this.textArea = new JTextArea();

    textArea.setLineWrap(true);
    textArea.setWrapStyleWord(true);
    textArea.setFont(new Font("Arial", Font.PLAIN, 20));

    scrollPane = new JScrollPane(textArea);
    scrollPane.setPreferredSize(new Dimension(450, 450));
    scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);

    fontLabel = new JLabel("Font: ");

    fontSizeSpinner = new JSpinner();
    fontSizeSpinner.setPreferredSize(new Dimension(50,25));
    fontSizeSpinner.setValue(20);
    fontSizeSpinner.addChangeListener(e -> textArea.setFont(new Font(textArea.getFont().getFamily(), Font.PLAIN,
                                                                 (Integer) fontSizeSpinner.getValue())));

    fontColorButton = new JButton("Color");
    fontColorButton.addActionListener(this);

    String[] fonts =
            GraphicsEnvironment.getLocalGraphicsEnvironment().getAvailableFontFamilyNames();
    fontBox = new JComboBox(fonts);
    fontBox.addActionListener(this);
    fontBox.setSelectedItem("Arial");
    this.add(fontLabel);
    this.add(fontSizeSpinner);
    this.add(fontColorButton);
    this.add(fontBox);
    this.add(scrollPane);
    this.setVisible(true);


  }

  @Override
  public void actionPerformed(ActionEvent e) {
    if (e.getSource() == fontColorButton){
      JColorChooser colorChooser = new JColorChooser();

      Color color = colorChooser.showDialog(null, "Choose a color", Color.BLACK);
      textArea.setForeground(color);
    }

    if (e.getSource() == fontBox){
      textArea.setFont(new Font((String) fontBox.getSelectedItem(), Font.PLAIN,
                                textArea.getFont().getSize()));
    }
  }
}