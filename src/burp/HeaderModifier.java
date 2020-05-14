package burp;

import java.util.ArrayList;
import burp.*;

public class HeaderModifier implements IBurpExtender, IHttpListener
{
    private String version = "v1.0";
    IExtensionHelpers helpers = null;
    private IBurpExtenderCallbacks callback= null;
    final String extensionName = "Header Modifier";
    HeadModifier_GUI gui = null;


    public void registerExtenderCallbacks (IBurpExtenderCallbacks callbacks) {
        this.callback = callbacks;
        this.helpers = callbacks.getHelpers();
        callback.setExtensionName("Header Modifier");
        callbacks.registerHttpListener(this);
        this.gui = new HeadModifier_GUI(this.extensionName,callbacks);
    }

    @Override
    public void processHttpMessage(int toolFlag, boolean messageIsRequest, IHttpRequestResponse messageInfo) {
        if ((messageIsRequest)&&(gui.isEnabled())&&((toolFlag==callback.TOOL_EXTENDER)||(toolFlag==callback.TOOL_SCANNER))){
            String requestBody = null;
            String requestString = new String(messageInfo.getRequest());
            IRequestInfo request = helpers.analyzeRequest(messageInfo);
            ArrayList<String> header = (ArrayList<String>) request.getHeaders();
            requestBody = requestString.substring(request.getBodyOffset());
            if (gui.addHeader.isSelected()){
                header = addHeader(header,gui.headerName_Text.getText(), gui.headerValue_Text.getText());
            }else if (gui.removeHeader.isSelected()){
                header = removeHeader(header,gui.headerName_Text.getText());
            }else if(gui.modifyHeader.isSelected()){
                header = removeHeader(header,gui.headerName_Text.getText());
                header = addHeader(header,gui.headerName_Text.getText(), gui.headerValue_Text.getText());
            }
            byte[] newRequest = helpers.buildHttpMessage(header, requestBody.getBytes());
            messageInfo.setRequest(newRequest);
        }
    }


    private ArrayList<String> removeHeader(ArrayList<String> header,String headerName){
        for (int i =0;i<header.size();i++){
            if (header.get(i).startsWith(headerName)){
                header.remove(i);
            }
        }
        return header;
    }

    private ArrayList<String>  addHeader(ArrayList<String> header,String headerName,String headerValue){
        String newHeader = headerName+":"+" "+headerValue;
        header.add(newHeader);
        return header;
    }

}