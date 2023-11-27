package com.jyula.jyulaapi.core.providers;

import lombok.Getter;

public interface MailSenderProvider {
    SendMailResponse send(SendMailRequest request) throws SendMailException;

    @Getter
    class SendMailRequest {
        private final String from;
        private final String to;
        private final String subject;
        private final String content;

        private SendMailRequest(String from, String to, String subject, String content) {
            this.from = from;
            this.to = to;
            this.subject = subject;
            this.content = content;
        }

        public Builder toBuilder() {
            return new Builder(from, to, subject, content);
        }

        public static Builder builder() {
            return new Builder();
        }

        public static final class Builder {
            private String from;
            private String to;
            private String subject;
            private String content;

            private Builder() {
            }

            private Builder(String from, String to, String subject, String content) {
                this.from = from;
                this.to = to;
                this.subject = subject;
                this.content = content;
            }

            public Builder from(String from) {
                this.from = from;
                return this;
            }

            public Builder to(String to) {
                this.to = to;
                return this;
            }

            public Builder subject(String subject) {
                this.subject = subject;
                return this;
            }

            public Builder content(String content) {
                this.content = content;
                return this;
            }

            public SendMailRequest build() {
                return new SendMailRequest(from, to, subject, content);
            }
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            SendMailRequest that = (SendMailRequest) o;

            if (!from.equals(that.from)) return false;
            if (!to.equals(that.to)) return false;
            if (!subject.equals(that.subject)) return false;
            return content.equals(that.content);
        }

        @Override
        public int hashCode() {
            int result = from.hashCode();
            result = 31 * result + to.hashCode();
            result = 31 * result + subject.hashCode();
            result = 31 * result + content.hashCode();
            return result;
        }

        @Override
        public String toString() {
            return "SendMailRequest{" +
                    "from='" + from + '\'' +
                    ", to='" + to + '\'' +
                    ", subject='" + subject + '\'' +
                    ", content='" + content + '\'' +
                    '}';
        }
    }

    record SendMailResponse(String id) {
    }

    class SendMailException extends RuntimeException {
        public SendMailException(SendMailRequest request, Exception ex) {
            super("Error at sending email, data=" + request.toString() + ", error=" + ex.getMessage());
        }
    }
}
