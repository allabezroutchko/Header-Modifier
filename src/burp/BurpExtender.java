package burp;

public class BurpExtender implements IBurpExtender
{

	public void registerExtenderCallbacks (IBurpExtenderCallbacks callbacks) {
		HeaderModifier plugin = new HeaderModifier();
		plugin.registerExtenderCallbacks(callbacks);
	}

}
