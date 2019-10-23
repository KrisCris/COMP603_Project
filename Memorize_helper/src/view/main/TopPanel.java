/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view.main;

import controller.main.*;
import java.awt.*;
import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.Document;
import model.*;
import view.*;
import view.search.SearchResultListPanel;

/**
 *
 * @author ThinkPad
 */
public class TopPanel extends MainViewViewTemplate {

    private JPanel searchPanel;

    public TopPanel(JFrame mainView, User user) {
        super(mainView, user);
    }

    private void setSize() {
        Toolkit toolkit = Toolkit.getDefaultToolkit();
        Dimension screenSize = toolkit.getScreenSize();
        int screenWidth = (int) screenSize.getWidth();
        int screenHeight = (int) screenSize.getHeight();
        int frameWidth = 1280;
        int frameHeight = 120;
        setBounds((screenWidth - frameWidth) / 2, (screenHeight - frameHeight) / 2, frameWidth, frameHeight);
    }

    @Override
    public void setProperty() {
        //        setSize();
        setLayout(new GridBagLayout());
//        setBorder(new TitledBorder("TopPanel"));
        setOpaque(true);
        setBackground(Color.decode("#EEECE8"));
    }

    @Override
    public void addComponents() {
        //left fill label
        add(new JLabel(), new GridBagTool().setGridx(0).setGridy(0).setGridwidth(1).setGridheight(1).setWeightx(0.05).setWeighty(1));
        //right fill label
        add(new JLabel(), new GridBagTool().setGridx(4).setGridy(0).setGridwidth(1).setGridheight(1).setWeightx(0.05).setWeighty(1));
        //middle fill label
        add(new JLabel(), new GridBagTool().setGridx(2).setGridy(0).setGridwidth(1).setGridheight(1).setWeightx(0.3).setWeighty(1));

        addProfilePanel();

        addSearchPanel();
    }

    private void addProfilePanel() {
        JPanel profilePanel = new JPanel();

        JLabel lbl_tP_username = new JLabel("你好，" + user.getUsername());
//        lbl_tP_username.setBorder(new TitledBorder("lbl_tP_username"));
        profilePanel.add(lbl_tP_username);

        JButton btn_tP_rankList = new JButton("排行榜");
        btn_tP_rankList.addActionListener(new ShowRankListController(mainView, user));
        profilePanel.add(btn_tP_rankList);

        add(profilePanel, new GridBagTool().setFill(GridBagConstraints.NONE).setAnchor(GridBagConstraints.WEST).setGridx(1).setGridy(0).setGridwidth(1).setGridheight(1).setWeightx(0.3).setWeighty(1));

    }

    private void addSearchPanel() {
        searchPanel = new JPanel();
//        searchPanel.setBorder(new TitledBorder("searchPanel"));

        JLabel lbl_tP_searchTip = new JLabel("查词");
        searchPanel.add(lbl_tP_searchTip);

        JTextField tf_tP_keyword = new JTextField(15);

        tf_tP_keyword.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                new SearchResultListPanel(tf_tP_keyword.getText(), user);
                mainView.dispose();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
            }
        });
        searchPanel.add(tf_tP_keyword);

        add(searchPanel, new GridBagTool().setFill(GridBagConstraints.NONE).setAnchor(GridBagConstraints.EAST).setGridx(3).setGridy(0).setGridwidth(1).setGridheight(1).setWeightx(0.3).setWeighty(1));
    }
}
