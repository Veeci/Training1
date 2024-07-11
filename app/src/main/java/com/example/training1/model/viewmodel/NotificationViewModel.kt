import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

data class Notification(val title: String, val content: String, val timestamp: Long)

class NotificationViewModel : ViewModel() {
    private val _notifications = mutableStateOf<List<Notification>>(emptyList())
    val notifications: State<List<Notification>> = _notifications

    fun addNotification(notification: Notification) {
        viewModelScope.launch {
            val updatedNotifications = _notifications.value.orEmpty() + notification
            _notifications.value = updatedNotifications
        }
    }
}
