package edu.mit.csail.sls.wami;

import java.util.Map;

import javax.servlet.http.HttpSession;

import org.w3c.dom.Document;

import edu.mit.csail.sls.wami.app.IApplicationController;
import edu.mit.csail.sls.wami.jsapi.ClientControlledApplication;
import edu.mit.csail.sls.wami.recognition.IRecognitionResult;

/**
 * NOTE:
 * 
 * This sample code doesn't actually do anything different than the super-class.
 * 
 * This application just provides an example of how you might override methods
 * of a ClientControlledApplication to perform operations which should probably
 * be performed on the server-side. For example, perhaps you need to look
 * something up in a database, and do not want to force your client to make
 * another request. Fortunately, Wami makes client-server communication simple
 * via the app controller.
 * 
 * @author imcgraw
 */
public class MyWamiApplication extends ClientControlledApplication {

	private IApplicationController controller;

	@Override
	public void initialize(IApplicationController appController,
			HttpSession session, Map<String, String> paramMap) {
		this.controller = appController;
	};

	@Override
	public void onRecognitionResult(IRecognitionResult result) {
		Document doc = getMessageOnlyServerCanCompute();

		if (doc != null) {
			controller.sendMessage(doc);
		} else {
			super.onRecognitionResult(result);
		}
	}

	private Document getMessageOnlyServerCanCompute() {
		// Just an example...
		return null;
	}
}
