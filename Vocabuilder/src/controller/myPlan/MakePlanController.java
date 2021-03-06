/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.myPlan;

import controller.interfaces.UserController;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import model.User;
import view.main.MainView;
import view.myPlan.MyPlanPanel;
import view.planList.CreatePlanPanel;

/**
 *
 * @author ThinkPad
 */
public class MakePlanController implements ActionListener {

    private User user;
    private CreatePlanPanel createPlanPanel;
    private JFrame selectedPlanFrame;
    private MyPlanPanel myPlanPanel;

    //login first time
    public MakePlanController(User user, CreatePlanPanel createPlanPanel, JFrame selectedPlanFrame) {
        this.user = user;
        this.createPlanPanel = createPlanPanel;
        this.selectedPlanFrame = selectedPlanFrame;
    }

    //change after login
    public MakePlanController(User user, MyPlanPanel myPlanPanel) {
        this.user = user;
        this.myPlanPanel = myPlanPanel;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        //change after login
        if (myPlanPanel != null && myPlanPanel.getSelectedPlan() != null) {
            new UserController().changeStudyPlan(user, myPlanPanel.getSelectedPlan());
            myPlanPanel.getMyPlanFrame().dispose();
            new MainView(user);

            return;
        }

        //login first time
        if (createPlanPanel.getDayQuantity() == null && createPlanPanel.getWordQuantity() == null) {
            JOptionPane.showMessageDialog(null, "please decide an ideal schedule!!");
            return;
        }

        if (createPlanPanel.getOptionTabs().getSelectedIndex() == 1) {
            if (createPlanPanel.getDayQuantity() != null) {
                new UserController().activateStudyPlanByDay(user, createPlanPanel.getSelectedPlan().getStudyPlanName(), Integer.parseInt(createPlanPanel.getDayQuantity().split(" day")[0]));
            } else {
                JOptionPane.showMessageDialog(null, "please decide an ideal schedule!!");
                return;
            }
        } else {
            if (createPlanPanel.getWordQuantity() != null) {
                new UserController().activateStudyPlanByNum(user, createPlanPanel.getSelectedPlan().getStudyPlanName(), Integer.parseInt(createPlanPanel.getWordQuantity().split(" word")[0]));
            } else {
                JOptionPane.showMessageDialog(null, "please decide an ideal schedule!!");
                return;
            }
        }
        selectedPlanFrame.dispose();
        new MainView(user);
    }
}
