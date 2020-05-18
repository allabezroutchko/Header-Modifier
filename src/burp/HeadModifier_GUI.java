package burp;

import burp.IBurpExtenderCallbacks;
import burp.ITab;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.HashMap;
import java.util.concurrent.Flow;

public class HeadModifier_GUI extends JPanel implements ITab {
    private String extensionName = null;
    IBurpExtenderCallbacks callback = null;
    private JCheckBox isEnabled , burpScanner,burpExtender;
    public JTextArea headerName_Text, headerValue_Text;
    private JLabel headerName_Label,headerValue_Label;
    public JRadioButton addHeader, modifyHeader, removeHeader;
    private JPanel namePanel, valuePanel,pluginPanel, radioButtonPanel,toolPanel;
    HashMap<String, Boolean> toolSelection=null;

    public HeadModifier_GUI(String extensionName,IBurpExtenderCallbacks callbacks){
        this.toolSelection = new HashMap<String, Boolean>();
        this.init();
        this.callback = callbacks;
        this.extensionName = extensionName;
        this.callback.customizeUiComponent(this);
        this.callback.addSuiteTab(this);

        this.revalidate();
        this.doLayout();
    }

    /*Init Methods : START*/
    private void init(){
        this.initLabels();
        this.initCheckBoxes();
        this.initRadioButtons();
        this.initTextArea();
        this.add(addMainPanel());
    }

    private void initTextArea(){
        /*Initialization*/
        this.headerName_Text = new JTextArea(1,30);
        this.headerValue_Text= new JTextArea(8,30);
        this.headerValue_Text.setLineWrap(true);
        headerName_Text.setLineWrap(true);
    }

    private void initLabels(){
        /*Initialization*/
        this.headerName_Label = new JLabel();
        this.headerValue_Label = new JLabel();

        this.headerName_Label.setText("Header Name: ");
        this.headerValue_Label.setText("Header Value: ");

        this.headerName_Label.setAlignmentX(LEFT_ALIGNMENT);
        this.headerValue_Label.setAlignmentX(LEFT_ALIGNMENT);
    }

    private void initCheckBoxes(){
        this.isEnabled = new JCheckBox();
        this.isEnabled.setSelected(false);
        this.isEnabled.setText("Enable Extension");
        this.isEnabled.setAlignmentX(CENTER_ALIGNMENT);

        this.burpExtender = new JCheckBox();
        this.burpExtender.setSelected(false);
        this.burpExtender.setText("Extender");
        this.toolSelection.put("Extender",false);
        this.burpExtender.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if(e.getStateChange()==ItemEvent.SELECTED){
                    toolSelection.replace("Extender",true);
                }else if (e.getStateChange()==ItemEvent.DESELECTED){
                    toolSelection.replace("Extender",false);
                }
            }
        });

        this.burpScanner = new JCheckBox();
        this.burpScanner.setSelected(false);
        this.burpScanner.setText("Scanner");
        this.toolSelection.put("Scanner",false);
        this.burpScanner.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if(e.getStateChange()==ItemEvent.SELECTED){
                    toolSelection.replace("Scanner",true);
                }else if (e.getStateChange()==ItemEvent.DESELECTED){
                    toolSelection.replace("Scanner",false);
                }
            }
        });
    }

    private void initRadioButtons(){
        /*Initialization*/
        this.addHeader = new JRadioButton("Add Header",false);
        this.addHeader.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if (addHeader.isSelected()){
                    headerValue_Text.setEnabled(true);
                }
            }
        });

        this.removeHeader = new JRadioButton("Remove Header",false);
        this.removeHeader.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if(removeHeader.isSelected()){
                    headerValue_Text.setText(null);
                    headerValue_Text.setEnabled(false);
                }
            }
        });

        this.modifyHeader = new JRadioButton("Modify Header", false);
        this.modifyHeader.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if(modifyHeader.isSelected()){
                    headerValue_Text.setEnabled(true);
                }
            }
        });

        ButtonGroup group=new ButtonGroup();
        group.add(addHeader);
        group.add(removeHeader);
        group.add(modifyHeader);
    }
    /*Init Methods : FINISH*/

    /*Add Methods : START*/

    private JPanel addMainPanel(){
        this.pluginPanel = new JPanel();
        BoxLayout layout = new BoxLayout(pluginPanel,BoxLayout.Y_AXIS);
        pluginPanel.setLayout(layout);
        pluginPanel.add(new JLabel("  "));
        pluginPanel.add(this.addRadioButtons());
        pluginPanel.add(new JLabel("  "));
        pluginPanel.add(this.addNamePanel());
        pluginPanel.add(new JLabel("  "));
        pluginPanel.add(this.addvaluePanel());
        pluginPanel.add(new JLabel("  "));
        pluginPanel.add(this.addToolPanel());
        pluginPanel.add(new JLabel("  "));
        pluginPanel.add(this.isEnabled);
        return pluginPanel;
    }

    private JPanel addToolPanel(){
        this.toolPanel = new JPanel();
        this.toolPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        this.toolPanel.add(new JLabel("Burp Tools: "));
        this.toolPanel.add(this.burpExtender);
        this.toolPanel.add(this.burpScanner);
        return this.toolPanel;
    }

    public JPanel addvaluePanel() {
        this.valuePanel = new JPanel();
        valuePanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        valuePanel.add(headerValue_Label);
        valuePanel.add(headerValue_Text);
        return valuePanel;
    }

    public JPanel addNamePanel(){
        this.namePanel = new JPanel();
        namePanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        namePanel.add(headerName_Label);
        namePanel.add(headerName_Text);
        return namePanel;
    }

    private JPanel addRadioButtons(){
        this.radioButtonPanel = new JPanel();
        FlowLayout layout = new FlowLayout(FlowLayout.CENTER);
        radioButtonPanel.setLayout(layout);
        radioButtonPanel.add(addHeader);
        radioButtonPanel.add(removeHeader);
        radioButtonPanel.add(modifyHeader);
        return radioButtonPanel;
    }

    /*Add Methods : FINISH*/

    public boolean isEnabled(){
        return isEnabled.isSelected();
    }

    @Override
    public String getTabCaption()
    {
        return this.extensionName;
    }

    @Override
    public Component getUiComponent()
    {
        return this;
    }
}
