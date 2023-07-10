package com.solvd.laba.model;

public class Professors {
    private int id;
    private String firstName;
    private String lastName;
    private int dateOfBirthId;
    private int emailId;
    private int facultiesId;
    private Professors(Builder builder) {
        this.id = builder.id;
        this.firstName = builder.firstName;
        this.lastName = builder.lastName;
        this.dateOfBirthId = builder.dateOfBirthId;
        this.emailId = builder.emailId;
        this.facultiesId = builder.facultiesId;
    }
    public static class Builder {
        private int id;
        private String firstName;
        private String lastName;
        private int dateOfBirthId;
        private int emailId;
        private int facultiesId;
        public Builder (){}
        public Builder id(int id) {
            this.id = id;
            return this;
        }

        public Builder firstName(String firstName) {
            this.firstName = firstName;
            return this;
        }

        public Builder lastName(String lastName) {
            this.lastName = lastName;
            return this;
        }

        public Builder dateOfBirthId(int dateOfBirthId) {
            this.dateOfBirthId = dateOfBirthId;
            return this;
        }

        public Builder emailId(int emailId) {
            this.emailId = emailId;
            return this;
        }

        public Builder facultiesId(int facultiesId) {
            this.facultiesId = facultiesId;
            return this;
        }

        public Professors build() {
            return new Professors(this);
        }
    }

    @Override
    public String toString() {
        return "Professors{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", dateOfBirthId=" + dateOfBirthId +
                ", emailId=" + emailId +
                ", facultiesId=" + facultiesId +
                '}';
    }
}
