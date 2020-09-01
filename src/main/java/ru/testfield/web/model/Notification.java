package ru.testfield.web.model;

public class Notification {

    private String text;

    private NotificationType type;

    public enum NotificationType {

        SUCCESS("alert-success"),
        INFO("alert-info"),
        WARNING("alert-warning"),
        DANGER("alert-danger");

        private String alertClass;

        NotificationType(String alertClass) {
            this.alertClass=alertClass;
        }

        @Override
        public String toString() {
            return this.alertClass;
        }
    }

    public Notification(NotificationType type, String text) {
        this.text = text;
        this.type = type;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public NotificationType getType() {
        return type;
    }

    public void setType(NotificationType type) {
        this.type = type;
    }
}
