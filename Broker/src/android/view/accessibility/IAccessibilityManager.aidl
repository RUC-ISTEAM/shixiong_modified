package android.view.accessibility;

//import android.accessibilityservice.AccessibilityServiceInfo;
//import android.accessibilityservice.IAccessibilityServiceConnection;
//import android.accessibilityservice.IAccessibilityServiceClient;
//import android.view.accessibility.AccessibilityEvent;
//import android.view.accessibility.AccessibilityNodeInfo;
//import android.view.accessibility.IAccessibilityInteractionConnection;
import android.view.accessibility.IAccessibilityManagerClient;
//import android.view.IWindow;

interface IAccessibilityManager {
	int addClient(IAccessibilityManagerClient client);
//	boolean sendAccessibilityEvent(in AccessibilityEvent uiEvent);
//	List<AccessibilityServiceInfo> getInstalledAccessibilityServiceList();
	
//	List<AccessibilityServiceInfo> getEnabledAccessibilityServiceList(int feedbackType);

//	void interrupt();

//	int addAccessibilityInteractionConnection(IWindow windowToken, in IAccessibilityInteractionConnection connection);

//	void removeAccessibilityInteractionConnection(IWindow windowToken);

//	void registerUiTestAutomationService(IAccessibilityServiceClient client, in AccessibilityServiceInfo info);

//	void unregisterUiTestAutomationService(IAccessibilityServiceClient client);
}