package burp;

import burp.IBurpExtenderCallbacks;
import burp.ITab;

import javax.swing.*;
import java.awt.*;
import java.util.concurrent.Flow;

public class HeadModifier_GUI extends JPanel implements ITab {
    private String extensionName = null;
    IBurpExtenderCallbacks callback = null;
    private JCheckBox isEnabled;
    public JTextArea headerName_Text, headerValue_Text;
    private JLabel headerName_Label,headerValue_Label,extensionName_Label;
    public JRadioButton addHeader, modifyHeader, removeHeader;
    private JPanel namePanel, valuePanel,pluginPanel, radioButtonPanel;

    public HeadModifier_GUI(String extensionName,IBurpExtenderCallbacks callbacks){
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
        this.headerValue_Text= new JTextArea(3,30);
        this.headerValue_Text.setLineWrap(true);
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
    }

    private void initRadioButtons(){
        /*Initialization*/
        this.addHeader = new JRadioButton("Add Header",false);
        this.removeHeader = new JRadioButton("Remove Header",false);
        this.modifyHeader = new JRadioButton("Modify Header", false);
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
        pluginPanel.add(this.isEnabled);
        return pluginPanel;
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
