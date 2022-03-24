package com.company.GUI;

import com.company.Moss.Moss;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

public class MainFrame extends JFrame{
    public String fileNames = "";
    public String path ="";
    public Moss moss = new Moss();
   public void createWindow() {
        JFrame frame = new JFrame("Select Files");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        createUI(frame);
        frame.setSize(560, 200);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    public void createUI(final JFrame frame){
        JPanel panel = new JPanel();
        LayoutManager layout = new FlowLayout();
        panel.setLayout(layout);
        JButton AddFilesButton = new JButton("Add Files");
        JButton RunMossButton  = new JButton("Run Moss");
        final JLabel label = new JLabel();
        //Moss Action
        RunMossButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                MossResults mossResults = new MossResults();
                try {
                    moss.runMoss();
                } catch (IOException ex) {
                    ex.printStackTrace();
                } catch (Exception exception) {
                    exception.printStackTrace();
                }
            }
        });

        //Select Action
        AddFilesButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser fileChooser = new JFileChooser();
                fileChooser.setMultiSelectionEnabled(true);
                fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
                int option = fileChooser.showOpenDialog(frame);
                if(option == JFileChooser.APPROVE_OPTION){
                    File[] files = fileChooser.getSelectedFiles();
                    java.io.File f = fileChooser.getSelectedFile();
                    path = f.getPath();
                    for(File file: files){
                        fileNames += file.getName() + " ";
                    }
                    moss.setFilesName(fileNames);
                    moss.setPath(path);
                    label.setText("File(s) Selected: " + fileNames);
                }else{
                    label.setText("Nothing Selected");
                }
            }
        });

        panel.add(AddFilesButton);
        panel.add(RunMossButton);
        panel.add(label);
        frame.getContentPane().add(panel, BorderLayout.CENTER);
    }

    public String getFileNames(){
       return fileNames;
    }
    public String getPath(){return path;}
}

//TODO: Organize, make it more Professional, and add Zip file feature