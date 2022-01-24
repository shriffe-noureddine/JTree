package com.company;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JTree;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;

public class Trees {

    public static void main(String[] args) {

        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                JFrame F = new JFrame();
                F.setTitle("Swing JTree");
                F.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

                JPanel P = new JPanel(new BorderLayout());

                DefaultMutableTreeNode root = new DefaultMutableTreeNode("Root");

                DefaultMutableTreeNode fruit = new DefaultMutableTreeNode("Fruits");
                root.add(fruit);

                fruit.add(new DefaultMutableTreeNode("Oranges"));
                fruit.add(new DefaultMutableTreeNode("Bananas"));
                fruit.add(new DefaultMutableTreeNode("Apples"));

                // ADD THE VEGETABLES
                DefaultMutableTreeNode vegetable = new DefaultMutableTreeNode("Vegetables");
                root.add(vegetable);

                vegetable.add(new DefaultMutableTreeNode("Cucumber"));
                vegetable.add(new DefaultMutableTreeNode("Salad"));
                vegetable.add(new DefaultMutableTreeNode("Pumpkin"));

                final JTree T = new JTree(root);
                final JLabel L = new JLabel("-");
                L.setHorizontalAlignment(JLabel.CENTER);
                P.add(T, BorderLayout.WEST);
                P.add(L, BorderLayout.CENTER);

                // ADD LISTENER TO DISPLAY THE PATH ON PANEL
                T.addTreeSelectionListener(new TreeSelectionListener() {
                    @Override
                    public void valueChanged(TreeSelectionEvent tse) {
                        L.setText(tse.getPath().toString());
                    }
                });

                F.getContentPane().add(P);

                JPanel Panel2 = new JPanel(new GridLayout(1, 2));
                final JTextField A = new JTextField();
                JButton B = new JButton("add as child");
                B.addActionListener(new ActionListener() {

                    @Override
                    public void actionPerformed(ActionEvent e) {
                        // ADD CODE HERE
                        String str = A.getText();
                        DefaultMutableTreeNode node = new DefaultMutableTreeNode(str);
                        DefaultTreeModel model = (DefaultTreeModel) T.getModel();
                        model.insertNodeInto(node, (DefaultMutableTreeNode) T.getLastSelectedPathComponent(), 0);
                        model.reload(node);

                        // NEEDED FOR REFRESH
                        // DefaultTreeModel model = (DefaultTreeModel) T.getModel();
                        // model.reload(node);

                        T.repaint();
                    }
                });

                Panel2.add(A);
                Panel2.add(B);
                F.getContentPane().add(Panel2, BorderLayout.SOUTH);

                F.setSize(400, 400);
                F.setVisible(true);
            }
        });
    }
}
