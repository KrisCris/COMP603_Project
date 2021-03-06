package view.planList;

import controller.interfaces.PlanController;
import controller.myPlan.MakePlanController;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import model.*;
import view.*;
import view.myPlan.MyPlanPanel;

/**
 *
 * @author ThinkPad
 * @author Pingchuan
 */
public class CreatePlanPanel extends GroundPanelTemplate {

    private User user;
    private StudyPlan selectedPlan;
    private JFrame selectedPlanFrame;
    private JLabel selectedBookName;
    private JLabel selectedBookTotalNumber;
    private JTabbedPane optionTabs;
    private JButton confirmBtn;
    private JList makePlanListPart1;
    private JList makePlanListPart2;
    private String wordQuantity;
    private String dayQuantity;

    MakePlanTabPanel makePlanTabPanelPart1;
    MakePlanTabPanel makePlanTabPanelPart2;

    private boolean isEdit = false;
    JFrame myPlanFrame;

    public JTabbedPane getOptionTabs() {
        return optionTabs;
    }

    public String getWordQuantity() {
        return wordQuantity;
    }

    public String getDayQuantity() {
        return dayQuantity;
    }

    public StudyPlan getSelectedPlan() {
        return selectedPlan;
    }

    public CreatePlanPanel(User user, StudyPlan selectedPlan) {
        super(GroundPanelTemplate.BACK);
        this.user = user;
        this.selectedPlan = selectedPlan;
        setProperty();
        addComponents();
        this.isEdit = false;
    }

    public CreatePlanPanel(User user, StudyPlan selectedPlan, JFrame myPlanFrame) {
        super(GroundPanelTemplate.BACK);
        this.user = user;
        this.selectedPlan = selectedPlan;
        this.isEdit = true;
        this.myPlanFrame = myPlanFrame;
        setProperty();
        addComponents();

    }

    public void setProperty() {
        setLayout(new GridBagLayout());
    }

    public void addComponents() {
        selectedPlanFrame = new JFrame("Schedule the Plan");
        setSize(selectedPlanFrame, 720, 720);
        selectedPlanFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        selectedPlanFrame.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                super.windowClosing(e);
                if (e.getID() == WindowEvent.WINDOW_CLOSING && user.getCurrentStudyPlan() == null) {
                    if (JOptionPane.showConfirmDialog(null,
                            "Do you want to leave without scheduling the plan?", "Close Window?",
                            JOptionPane.YES_NO_OPTION,
                            JOptionPane.QUESTION_MESSAGE) == JOptionPane.YES_OPTION) {
                        System.exit(0);
                    }
                    new CreatePlanPanel(user, selectedPlan);
                } else {
                    new MyPlanPanel(user, new MyPlanInfo(user));
                }
            }
        });

        //left fill label
        add(new JLabel(), new GridBagTool().setGridx(0).setGridy(0).setGridwidth(1).setGridheight(5).setWeightx(0.05).setWeighty(1));
        //right fill label
        add(new JLabel(), new GridBagTool().setGridx(2).setGridy(0).setGridwidth(1).setGridheight(5).setWeightx(0.05).setWeighty(1));
        //top fill label
        add(new JLabel(), new GridBagTool().setGridx(1).setGridy(0).setGridwidth(1).setGridheight(1).setWeightx(0.9).setWeighty(0.05));
        //bottom fill label
        add(new JLabel(), new GridBagTool().setGridx(1).setGridy(4).setGridwidth(1).setGridheight(1).setWeightx(0.9).setWeighty(0.05));

        addMakePlanPanel();

        confirmBtn = new JButton();
        confirmBtn.setText("OK");

        if (this.isEdit) {
            confirmBtn.addActionListener(new ActionListener() {
                CreatePlanPanel that = CreatePlanPanel.this;

                @Override
                public void actionPerformed(ActionEvent e) {
                    if (that.makePlanListPart1.getSelectedValue() == null && that.makePlanListPart2.getSelectedValue() == null) {
                        JOptionPane.showMessageDialog(null, "please decide an ideal schedule!!");
                        return;
                    }
                    int num = 0;
                    int day = 0;
                    String str = "";
                    Component c = that.optionTabs.getSelectedComponent();
                    if (c.equals(that.makePlanTabPanelPart1)) {
                        if (that.makePlanListPart1.getSelectedValue() != null) {
                            str = (String) that.makePlanListPart1.getSelectedValue();
                            String[] strs = str.split(" ");
                            str = strs[strs.length - 2];
                            num = Integer.parseInt(str);
                            day = -1;
                        } else {
                            JOptionPane.showMessageDialog(null, "please decide an ideal schedule!!");
                            return;
                        }
                    } else {
                        if (that.makePlanListPart2.getSelectedValue() != null) {
                            str = (String) that.makePlanListPart2.getSelectedValue();
                            String[] strs = str.split(" ");
                            str = strs[strs.length - 2];
                            num = -1;
                            day = Integer.parseInt(str);
                        } else {
                            JOptionPane.showMessageDialog(null, "please decide an ideal schedule!!");
                            return;
                        }
                    }

                    PlanController pc = new PlanController();
                    pc.editPlan(that.user, that.selectedPlan.getStudyPlanName(), num, day);
                    that.selectedPlanFrame.dispose();
                    pc.updateTodayPlanInfo(user);
                    new MyPlanPanel(user, new MyPlanInfo(user));
                }
            });
        } else {
            confirmBtn.addActionListener(new MakePlanController(user, this, selectedPlanFrame));
        }

        add(confirmBtn, new GridBagTool().setFill(GridBagConstraints.HORIZONTAL).setGridx(1).setGridy(3).setGridwidth(1).setGridheight(1).setWeightx(0.9).setWeighty(0.1));

        selectedPlanFrame.add(this);
        selectedPlanFrame.setVisible(true);
    }

    public void addMakePlanPanel() {
        JPanel topJPanel = new JPanel(new GridLayout(2, 1));
        topJPanel.setOpaque(true);
        topJPanel.setBackground(new Color(238, 236, 232));

        selectedBookName = new JLabel(selectedPlan.getStudyPlanName(), SwingConstants.CENTER);
        selectedBookName.setFont(new Font("FACE_SYSTEM", Font.PLAIN, 20));
        topJPanel.add(selectedBookName);

        selectedBookTotalNumber = new JLabel(String.valueOf(selectedPlan.getTotalNumber()) + " words", SwingConstants.CENTER);
        selectedBookTotalNumber.setFont(new Font("FACE_SYSTEM", Font.PLAIN, 15));
        topJPanel.add(selectedBookTotalNumber);

        add(topJPanel, new GridBagTool().setFill(GridBagConstraints.NONE).setAnchor(GridBagConstraints.CENTER).setGridx(1).setGridy(1).setGridwidth(1).setGridheight(1).setWeightx(0.9).setWeighty(0.1));

        optionTabs = new JTabbedPane();

        makePlanTabPanelPart1 = new MakePlanTabPanel(0);
        makePlanTabPanelPart2 = new MakePlanTabPanel(1);

        optionTabs.addTab("based on DAILY TASK", makePlanTabPanelPart1);
        optionTabs.addTab("based on LEARNING DURATION", makePlanTabPanelPart2);

        add(optionTabs, new GridBagTool().setGridx(1).setGridy(2).setGridwidth(1).setGridheight(1).setWeightx(0.9).setWeighty(0.7));
    }

    class MakePlanTabPanel extends JPanel {

        private int option;

        public MakePlanTabPanel(int option) {
            this.option = option;
            setProperty();
            addComponents();
        }

        public void setProperty() {
            setLayout(new GridLayout(1, 1));
        }

        public void addComponents() {
            if (option == 0) {
                String s[] = null;
                if (selectedPlan.getTotalNumber() % 5 == 0) {
                    s = new String[selectedPlan.getTotalNumber() / 5];
                    for (int i = 0; i < s.length; i++) {
                        s[i] = String.format("%50s", String.valueOf(5 * (i + 1) + " words"));
                    }
                } else {
                    s = new String[selectedPlan.getTotalNumber() / 5 + 1];
                    for (int i = 0; i < s.length - 1; i++) {
                        s[i] = String.format("%50s", String.valueOf(5 * (i + 1) + " words"));
                    }
                    s[s.length - 1] = String.format("%50s", String.valueOf(selectedPlan.getTotalNumber() + " words"));
                }
                makePlanListPart1 = new ListInScrollTemplate(s);
                makePlanListPart1.setEnabled(true);
                makePlanListPart1.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
                makePlanListPart1.addListSelectionListener(new ListSelectionListener() {
                    @Override
                    public void valueChanged(ListSelectionEvent e) {
                        if (makePlanListPart1.getValueIsAdjusting()) {
                            wordQuantity = makePlanListPart1.getSelectedValue().toString().trim();
                        }
                    }
                });
                JScrollPane jScrollPane = new JScrollPane(makePlanListPart1);
                add(jScrollPane);
            } else if (option == 1) {
                String s[] = new String[selectedPlan.getTotalNumber()];
                for (int i = 0; i < s.length; i++) {
                    s[i] = String.format("%50s", (i + 1) + (i == 0 ? " day" : " days"));
                }
                makePlanListPart2 = new ListInScrollTemplate(s);
                makePlanListPart2.setEnabled(true);
                makePlanListPart2.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
                makePlanListPart2.addListSelectionListener(new ListSelectionListener() {
                    @Override
                    public void valueChanged(ListSelectionEvent e) {
                        if (makePlanListPart2.getValueIsAdjusting()) {
                            dayQuantity = makePlanListPart2.getSelectedValue().toString().trim();
                        }
                    }
                });
                JScrollPane jScrollPane = new JScrollPane(makePlanListPart2);
                add(jScrollPane);
            }
        }
    }
}
