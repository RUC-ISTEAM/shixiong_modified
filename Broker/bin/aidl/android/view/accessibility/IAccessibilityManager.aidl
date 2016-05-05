package android.view.accessibility;


//import android.view.accessibility.BrokerAccessibilityEvent;
import android.view.accessibility.IAccessibilityManagerClient;

interface IAccessibilityManager {
	int addClient(IAccessibilityManagerClient client);
//	boolean sendAccessibilityEvent(in BrokerAccessibilityEvent uiEvent);
//	List<AccessibilityServiceInfo> getInstalledAccessibilityServiceList();
	
//	List<AccessibilityServiceInfo> getEnabledAccessibilityServiceList(int feedbackType);

	void interrupt();

//	int addAccessibilityInteractionConnection(IWindow windowToken, in IAccessibilityInteractionConnection connection);

//	void removeAccessibilityInteractionConnection(IWindow windowToken);

//	void registerUiTestAutomationService(IAccessibilityServiceClient client, in AccessibilityServiceInfo info);

//	void unregisterUiTestAutomationService(IAccessibilityServiceClient client);
}