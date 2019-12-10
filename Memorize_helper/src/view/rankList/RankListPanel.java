/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view.rankList;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import model.*;
import view.*;

/**
 *
 * @author ThinkPad
 */
public class RankListPanel extends GroundPanelTemplate {

    private JFrame mainView;
    private RankListInfo rankListInfo;

    public RankListPanel(JFrame mainView, RankListInfo rankListInfo) {
        super(GroundPanelTemplate.BACK);
        this.mainView = mainView;
        this.rankListInfo = rankListInfo;
//        setBorder(new TitledBorder("RankListPanel"));
        setProperty();
        addComponents();
    }

    public void setProperty() {
        setLayout(new GridBagLayout());
    }

    public void addComponents() {
        JFrame rankListFrame = new JFrame("Rankings");
        setSize(rankListFrame, 720, 720);
        rankListFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        rankListFrame.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
//                super.windowClosing(e);
                mainView.setVisible(true);
            }
        });

        //top fill label
        add(new JLabel(), new GridBagTool().setGridx(0).setGridy(0).setGridwidth(1).setGridheight(1).setWeightx(1).setWeighty(0.05));

        JLabel lbl_rLP_rankList = new JLabel("Rankings", SwingConstants.CENTER);
        add(lbl_rLP_rankList, new GridBagTool().setFill(GridBagConstraints.VERTICAL).setAnchor(GridBagConstraints.CENTER).setGridx(0).setGridy(1).setGridwidth(1).setGridheight(1).setWeightx(1).setWeighty(0.1));

        JPanel jPanel = new GroundPanelTemplate(GroundPanelTemplate.FORE);
        jPanel.setLayout(new GridLayout(1, 3));
        
        JList list_rLP_rankListPart1 = new ListInScrollTemplate(rankListInfo.getRankListInfo(RankListInfo.NUMBERS));
        jPanel.add(list_rLP_rankListPart1);
        
        JList list_rLP_rankListPart2 = new ListInScrollTemplate(rankListInfo.getRankListInfo(RankListInfo.NAMES));
        jPanel.add(list_rLP_rankListPart2);
        
        JList list_rLP_rankListPart3 = new ListInScrollTemplate(rankListInfo.getRankListInfo(RankListInfo.WORDS));
        jPanel.add(list_rLP_rankListPart3);

        JScrollPane jScrollPane = new JScrollPane(jPanel);
        add(jScrollPane, new GridBagTool().setGridx(0).setGridy(2).setGridwidth(1).setGridheight(1).setWeightx(1).setWeighty(0.85));

        rankListFrame.add(this);
        rankListFrame.setVisible(true);
    }
}
