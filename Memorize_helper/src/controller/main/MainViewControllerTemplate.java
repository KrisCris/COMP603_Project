/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.main;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import model.User;

/**
 *
 * @author ThinkPad
 */
public abstract class MainViewControllerTemplate implements ActionListener {
    protected JFrame mainView;
    protected User user;
    
    public MainViewControllerTemplate(JFrame mainView, User user) {
        this.mainView = mainView;
        this.user = user;
    }
}
