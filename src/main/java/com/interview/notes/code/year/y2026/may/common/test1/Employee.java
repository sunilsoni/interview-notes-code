package com.interview.notes.code.year.y2026.may.common.test1;

 record Employee(
        int id,
        String name,
        String email,
        String department,
        double salary) {

    public static Builder builder() {
        return new Builder();
    }

    public static void main(String[] args) {
        Employee emp = Employee.builder()
                .id(101)
                .name("John")
                .email("john@test.com")
                .department("IT")
                .salary(80000)
                .build();

        System.out.println(emp);
    }

    public static class Builder {
        private int id;
        private String name;
        private String email;
        private String department;
        private double salary;

        public Builder id(int id) {
            this.id = id;
            return this;
        }

        public Builder name(String name) {
            this.name = name;
            return this;
        }

        public Builder email(String email) {
            this.email = email;
            return this;
        }

        public Builder department(String department) {
            this.department = department;
            return this;
        }

        public Builder salary(double salary) {
            this.salary = salary;
            return this;
        }

        public Employee build() {
            return new Employee(id, name, email, department, salary);
        }
    }
}