package android.content;

import android.content.IIntentReceiver;
import android.content.Intent;

/** @hide */
interface IIntentSender {
    int send(int code, in Intent intent, String resolvedType,
            IIntentReceiver finishedReceiver, String requiredPermission);
}