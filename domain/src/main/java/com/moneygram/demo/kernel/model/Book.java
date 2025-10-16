package com.moneygram.demo.kernel.model;

public record Book(
        String bookId,
        String title,
        String author,
        int year,
        int edition
) {

    public static Builder builder(){
        return new Builder();
    }

    public Builder toBuilder(){
        return new Builder(this);
    }

    public static final class Builder {
        private String bookId;
        private String title;
        private String author;
        private int year;
        private int edition;

        private Builder() {
        }

        private Builder(Book other) {
            this.bookId = other.bookId();
            this.title = other.title();
            this.author = other.author();
            this.year = other.year();
            this.edition = other.edition();
        }

        public Builder bookId(String bookId) {
            this.bookId = bookId;
            return this;
        }

        public Builder title(String title) {
            this.title = title;
            return this;
        }

        public Builder author(String author) {
            this.author = author;
            return this;
        }

        public Builder year(int year) {
            this.year = year;
            return this;
        }

        public Builder edition(int edition) {
            this.edition = edition;
            return this;
        }

        public Book build() {
            return new Book(bookId, title, author, year, edition);
        }
    }
}
