package com.example.jetbrainsbackenddeveloperaccountservice.exception;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.time.LocalDateTime;
import java.util.Objects;

public class CustomErrorMessage {

    private final LocalDateTime timestamp;
    private final int status;
    private final String error;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private final String message;
    private final String path;

    public CustomErrorMessage(LocalDateTime timestamp, int status, String error, String message, String path) {
        this.timestamp = timestamp;
        this.status = status;
        this.error = error;
        this.message = message;
        this.path = path;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public int getStatus() {
        return status;
    }

    public String getError() {
        return error;
    }

    public String getMessage() {
        return message;
    }

    public String getPath() {
        return path;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CustomErrorMessage that = (CustomErrorMessage) o;

        if (status != that.status) return false;
        if (!Objects.equals(timestamp, that.timestamp)) return false;
        if (!Objects.equals(error, that.error)) return false;
        if (!Objects.equals(message, that.message)) return false;
        return Objects.equals(path, that.path);
    }

    @Override
    public int hashCode() {
        int result = timestamp != null ? timestamp.hashCode() : 0;
        result = 31 * result + status;
        result = 31 * result + (error != null ? error.hashCode() : 0);
        result = 31 * result + (message != null ? message.hashCode() : 0);
        result = 31 * result + (path != null ? path.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "CustomErrorMessage{" +
                "timestamp=" + timestamp +
                ", status='" + status + '\'' +
                ", error='" + error + '\'' +
                ", message='" + message + '\'' +
                ", path='" + path + '\'' +
                '}';
    }

     public static class Builder {

        private LocalDateTime timestamp;
        private int status;
        private String error;
        private String message;
        private String path;

        public Builder() {
        }

         public Builder setTimestamp(LocalDateTime timestamp) {
            this.timestamp = timestamp;
            return this;
        }

         public Builder setStatus(int status) {
            this.status = status;
            return this;
        }

         public Builder setError(String error) {
            this.error = error;
            return this;
        }

         public Builder setMessage(String message) {
            this.message = message;
            return this;
        }

         public Builder setPath(String path) {
            this.path = path;
            return this;
        }

         public CustomErrorMessage build() {
            return new CustomErrorMessage(this.timestamp, this.status, this.error, this.message, this.path);
        }
    }
}
