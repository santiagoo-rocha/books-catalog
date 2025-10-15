package com.moneygram.infrastructure.adapter.out.repository.dto;

public record BookEntity(
        String bookId,
        String title,
        String author,
        int year,
        int edition
){

    public static Builder builder(){
        return new Builder();
    }

    public static final class Builder {
        private String bookId;
        private String title;
        private String author;
        private int year;
        private int edition;

        private Builder() {
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

        public BookEntity build() {
            return new BookEntity(bookId, title, author, year, edition);
        }
    }
}
